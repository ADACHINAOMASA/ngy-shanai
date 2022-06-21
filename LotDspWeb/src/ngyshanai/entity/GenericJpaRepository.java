package ngyshanai.entity;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 * @author Leang-Say
 *
 * @param <T>
 * @param <ID>
 */
public class GenericJpaRepository<T, ID extends Serializable> implements GenericJpa<T, ID> {

	protected Class<T> entityClass;
	protected Class<ID> idClass;

	@Inject
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public GenericJpaRepository() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
		this.idClass = (Class<ID>) genericSuperclass.getActualTypeArguments()[1];
	}

	// private methods
	// --------------------------------------------------------------------------------------------------------------↓
	private T newEntityInstance(ID id) {
		try {
			return entityClass.getConstructor(id.getClass()).newInstance(id);
		} catch (IllegalArgumentException e) {
			throw new EntityException(e);
		} catch (SecurityException e) {
			throw new EntityException(e);
		} catch (InstantiationException e) {
			throw new EntityException(e);
		} catch (IllegalAccessException e) {
			throw new EntityException(e);
		} catch (InvocationTargetException e) {
			throw new EntityException(e);
		} catch (NoSuchMethodException e) {
			throw new EntityException(e);
		}
	}

	private ID newIdInstance(Object... params) {
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[params.length];

		int i = 0;
		for (Object o : params) {
			if (o instanceof String) {
				types[i] = String.class;
			} else if (o instanceof BigDecimal) {
				types[i] = BigDecimal.class;
			} else if (o instanceof Date) {
				types[i] = Date.class;
			} else if (o instanceof Timestamp) {
				types[i] = Timestamp.class;
			} else {
				types[i] = Object.class;
			}
			i++;
		}

		try {
			return idClass.getConstructor(types).newInstance(params);
		} catch (InstantiationException e) {
			throw new EntityException(e);
		} catch (IllegalAccessException e) {
			throw new EntityException(e);
		} catch (IllegalArgumentException e) {
			throw new EntityException(e);
		} catch (InvocationTargetException e) {
			throw new EntityException(e);
		} catch (NoSuchMethodException e) {
			throw new EntityException(e);
		} catch (SecurityException e) {
			throw new EntityException(e);
		}
	}

	private boolean isValid(T entity) {
		if (entity != null) {
			if (entity instanceof AbstractEntity) {
				return ((AbstractEntity) entity).isValid();
			}
			return true;
		}
		return false;
	}

	private void refreshAll(Object entity, List<Object> tmpList) {
		if (entity == null) {
			return;
		}
		if (entity instanceof Collection) {
			for (Object e : (Collection<?>) entity) {
				refreshAll(e, tmpList);
			}
			return;
		}
		if (entity.getClass().isArray()) {
			for (Object e : (Object[]) entity) {
				refreshAll(e, tmpList);
			}
			return;
		}
		if (tmpList.contains(entity)) {
			return;
		}
		em.flush();
		em.refresh(entity);
		tmpList.add(entity);

		List<String> names = new ArrayList<String>();
		for (Field f : entity.getClass().getDeclaredFields()) {
			if (isJoinAnnotation(f.getAnnotations())) {
				if (f.isAccessible()) {
					try {
						refreshAll(f.get(entity), tmpList);
					} catch (IllegalAccessException x) {
					}
				} else {
					names.add(f.getName());
				}
			}
		}

		for (PropertyDescriptor desc : PropertyUtils.getPropertyDescriptors(entity.getClass())) {
			if (names.contains(desc.getName()) || isJoinAnnotation(desc.getReadMethod().getAnnotations())) {
				try {
					refreshAll(desc.getReadMethod().invoke(entity, new Object[0]), tmpList);
				} catch (InvocationTargetException x) {
				} catch (IllegalAccessException x) {
				}
			}
		}
		tmpList.remove(entity);
	}

	private boolean isJoinAnnotation(Annotation[] annotations) {
		for (Annotation a : annotations) {
			if (a instanceof ManyToOne || a instanceof OneToMany || a instanceof OneToOne || a instanceof ManyToMany) {
				return true;
			}
		}
		return false;
	}

	// EntityManager
	// --------------------------------------------------------------------------------------------------------------//
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	// Entity : get,find,create...
	// --------------------------------------------------------------------------------------------------------------↓
	@Override
	public T create(T t) {
		em.persist(t);
		return t;
	}

	@Override
	public T read(ID id) {
		return em.find(entityClass, id);
	}

	@Override
	public T update(T t) {
		return em.merge(t);
	}

	@Override
	public void delete(T t) {
		t = em.merge(t);
		em.remove(t);
	}

	@Override
	public void flush() {
		em.flush();
	}

	@Override
	public void refresh(Object entity) {
		if (entity == null) {
			return;
		}
		if (entity instanceof Collection) {
			for (Object e : (Collection<?>) entity) {
				refresh(e);
			}
			return;
		}
		if (entity.getClass().isArray()) {
			for (Object e : (Object[]) entity) {
				refresh(e);
			}
			return;
		}
		em.flush();
		em.refresh(entity);
	}

	@Override
	public void refreshAll(Object entity) {
		refreshAll(entity, new ArrayList<Object>());
	}

	// ID : get,find,create....
	// --------------------------------------------------------------------------------------------------------------↓

	public T find(ID id, boolean validCheck) {
		try {
			T entity = read(id);
			if (entity != null) {
				if (validCheck) {
					if (isValid(entity)) {
						return entity;
					}
				} else {
					return entity;
				}
			}

		} catch (EntityNotFoundException e) {
			// e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			// throw new IllegalStateException(e);
		}
		return null;
	}

	public T find(ID id) {
		return find(id, false);
	}

	public T get(ID id) {
		return find(id, false);
	}

	public T create(ID id) {
		return create(newEntityInstance(id));
	}

	public void delete(ID id) {
		delete(newEntityInstance(id));
	}

	/**
	 * キーにより、エンティティを作成する。 無効なエンティティが存在する場合、強制的に削除する。
	 *
	 * @param id
	 * @return
	 */
	public T createForcely(ID id) {
		T entity = find(id);

		if (entity != null && !isValid(entity)) {
			delete(entity);
			flush();
		} else {
			entity = create(id);
		}
		return entity;
	}

	/**
	 * キーによるエンティティを取得する。存在しない場合、エンティティを作成する。
	 *
	 * @param id
	 * @return
	 */
	public T getOrCreate(ID id) {
		T entity = find(id);
		if (entity == null) {
			entity = create(id);
		}
		return entity;
	}

	/**
	 * キーによるエンティティを取得する。存在しない場合、エンティティを作成する。 ただし、無効なエンティティ（削除済）の場合、強制的に物理削除し新規作成を行う。
	 *
	 * @param id
	 * @return
	 */
	public T getOrCreateForcely(ID id) {
		T entity = find(id);

		if (entity == null) {
			entity = create(id);
		} else {
			if (!isValid(entity)) {
				delete(entity);
				flush();
				entity = create(id);
			}
		}
		return entity;
	}

	public boolean exist(ID id, boolean validCheck) {
		if (find(id, validCheck) == null) {
			return false;
		}
		return true;
	}

	public boolean exist(ID id) {
		return exist(id, false);
	}

	// LOCK
	// ----------------------------------------------------------------------------------------------↓
	public T getAndLock(ID id) {
		if (id == null) {
			throw new NullPointerException("ID is null");
		}
		T entity = em.find(entityClass, id, LockModeType.PESSIMISTIC_READ);
		return entity;
	}

	public T createAndLock(ID id) {
		if (id == null) {
			throw new NullPointerException("ID is null");
		}
		T entity = create(id);
		em.lock(entity, LockModeType.PESSIMISTIC_READ);
		return entity;
	}

	public T getAndLock(Object... keyParams) {
		T entity = find(newIdInstance(keyParams), false);
		if(entity==null) {
			return null;
		}
		em.lock(entity, LockModeType.PESSIMISTIC_READ);
		return entity;
	}

	public T createAndLock(Object... keyParams) {
		T entity = create(newEntityInstance(newIdInstance(keyParams)));
		if(entity==null) {
			return null;
		}
		em.lock(entity, LockModeType.PESSIMISTIC_READ);
		return entity;
	}

	// IDのParamsで、get,find,create....
	// --------------------------------------------------------------------------------------------------------------↓

	public T get(Object... keyParams) {
		return find(newIdInstance(keyParams), false);
	}

	public T create(Object... keyParams) {
		return create(newEntityInstance(newIdInstance(keyParams)));
	}

	public T getOrCreate(Object... keyParams) {
		return getOrCreate(newIdInstance(keyParams));
	}

	public void delete(Object... keyParams) {
		delete(newEntityInstance(newIdInstance(keyParams)));
	}

	public boolean exist(Object... keyParams) {
		return exist(newIdInstance(keyParams), false);
	}

	// findByNamedQuery
	// 例) namedQuery=findByTantoshaMei
	// -------------------------------------------------------------------------------------------------↓
	public List<T> findByNamedQuery(String namedQuery, Map<String, Object> parameters) {
		return findByNamedQuery(namedQuery, parameters, -1);
	}

	public List<T> findByNamedQuery(String namedQuery, Map<String, Object> parameters, boolean validCheck) {
		return findByNamedQuery(namedQuery, parameters, -1, validCheck, false);
	}

	public List<T> findByNamedQuery(String namedQuery, Map<String, Object> parameters, int maxResult) {
		return findByNamedQuery(namedQuery, parameters, maxResult, true, false);
	}

	public List<T> findByNamedQuery(String namedQuery, Map<String, Object> parameters, int maxResult,
			boolean validCheck, boolean isSingleResult) {
		Query query = em.createNamedQuery(namedQuery);

		for (String name : parameters.keySet()) {
			query.setParameter(name, parameters.get(name));
		}
		if (maxResult > 0) {
			query.setMaxResults(maxResult);
		}

		List<T> entityList = new ArrayList<T>();

		if (isSingleResult) {
			try {
				T entity = entityClass.cast(query.getSingleResult());
				if (validCheck) {
					if (isValid(entity)) {
						entityList.add(entity);
					}
				} else {
					if (entity != null) {
						entityList.add(entity);
					}
				}
			} catch (NoResultException e) {
			}
		} else {
			for (Object result : query.getResultList()) {
				T entity = entityClass.cast(result);
				if (validCheck) {
					if (isValid(entity)) {
						entityList.add(entity);
					}
				} else {
					entityList.add(entity);
				}
			}
		}

		return entityList;
	}

	public T findByNamedQuerySingleResult(String namedQuery, Map<String, Object> parameters) {
		return findByNamedQuerySingleResult(namedQuery, parameters, true);
	}

	public T findByNamedQuerySingleResult(String namedQuery, Map<String, Object> parameters, boolean validCheck) {
		List<T> entityList = findByNamedQuery(namedQuery, parameters, -1, validCheck, true);
		if (entityList == null || entityList.size() == 0) {
			return null;
		}
		return entityList.get(0);
	}

	// findByQuery
	// 例） sql=select t from tantosha t
	// -------------------------------------------------------------------------------------------------------------↓
	public List<T> findByQuery(String sql, Map<String, Object> parameters, int maxResult) {
		Query query = em.createQuery(sql);
		if (parameters != null && !parameters.isEmpty()) {
			for (String name : parameters.keySet()) {
				query.setParameter(name, parameters.get(name));
			}
		}
		if (maxResult > 0) {
			query.setMaxResults(maxResult);
		}
		List<T> entityList = new ArrayList<T>();
		for (Object result : query.getResultList()) {
			T entity = entityClass.cast(result);
			entityList.add(entity);
		}
		return entityList;
	}

	public List<T> findByQuery(String sql, Map<String, Object> parameters) {
		return findByQuery(sql, parameters, -1);
	}

	public List<T> findByQuery(String sql) {
		return findByQuery(sql, null, -1);
	}

	// findByQuerySingleResult
	// -------------------------------------------------------------------------------------------------------------↓
	public T findByQuerySingleResult(String sql, Map<String, Object> parameters) {
		Query query = em.createQuery(sql);
		if (parameters != null && !parameters.isEmpty()) {
			for (String name : parameters.keySet()) {
				query.setParameter(name, parameters.get(name));
			}
		}
		return entityClass.cast(query.getSingleResult());
	}

	public T findByQuerySingleResult(String sql) {
		return findByQuerySingleResult(sql, null);
	}

	// findByQuerySingleResult TypeQuery
	// -------------------------------------------------------------------------------------------------------------↓
	public Object findByQuerySigleResult(String sql, Class<?> paramClass, Map<String, Object> parameters) {
		Query query = em.createQuery(sql, paramClass);
		if (parameters != null && !parameters.isEmpty()) {
			for (String name : parameters.keySet()) {
				query.setParameter(name, parameters.get(name));
			}
		}
		return query.getSingleResult();
	}

	public Object findByQuerySigleResult(String sql, Class<?> paramClass) {
		return findByQuerySigleResult(sql, paramClass, null);
	}

	// findByNativeQuery 通常SQL文
	// -------------------------------------------------------------------------------------------------------------↓
	/*
	public List<T> findByNativeQuery(final String nativeSql, final Map<String, Object> parameters,final int maxResult) {

		Query query = em.createNativeQuery(nativeSql);

		if (parameters != null && !parameters.isEmpty()) {
			for (String name : parameters.keySet()) {
				query.setParameter(name, parameters.get(name));
			}
		}
		if (maxResult > 0) {
			query.setMaxResults(maxResult);
		}
		List<T> entityList = new ArrayList<T>();
		for (Object resultList : query.getResultList()) {
			T entity = entityClass.cast(resultList);
			entityList.add(entity);
		}

		return entityList;
	}

	public List<T> findByNativeQuery(final String nativeSql, final Map<String, Object> parameters) {
		return findByNativeQuery(nativeSql, parameters, -1);
	}

	public List<T> findByNativeQuery(final String nativeSql) {
		return findByNativeQuery(nativeSql, null, -1);
	}
	*/
		
}
