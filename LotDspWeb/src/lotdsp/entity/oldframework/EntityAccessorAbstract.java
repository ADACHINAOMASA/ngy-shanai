package lotdsp.entity.oldframework;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;

import nis.framework.jpa.EmContext;
import nis.framework.properties.AppProperties;

@SuppressWarnings("rawtypes")
public abstract class EntityAccessorAbstract<E extends EntityAbstract, K extends EntityKeyAbstract>
	implements EntityAccessor<E, K> {

	private static final String JNDI_NAME = AppProperties.getDataSourceJndiDefaultName();
	private EntityManager em = null;

	public EntityManager getEntityManager() {
		if (em == null) {
			em = EmContext.get();
		}
		return em;
	}

	/**
	 * getReferenceの挙動　＝　IDのみ取得、他属性は遅延フェッチ
	 * 利用者側から見るとfindと動作は変わらない
	 */
	public E getReference(K key){
		try{
			E entity = getEntityManager().getReference(getEntityClass(), key);
//			if (entity != null && "0".equals(getDeleteFlg(entity))) {
//				return entity;
//			}
			return null;
		}catch(EntityNotFoundException e){
			return null;
		}
	}

	/**
	 * 存在するかどうか？
	 */
	public boolean exist(K key) {
		return find(key) != null;
	}

	public E find(K key) {
		return find(key, true);
	}

	public E find(K key, boolean deleteCheck) {
		try{
			E entity = getEntityManager().find(getEntityClass(), key);
				if (entity != null) {
					return entity;
				}
			return null;
		}catch(EntityNotFoundException e){
			return null;
		}
	}

	/**
	 * 参照、データが存在しない場合はキーだけの空クラスを返す。
	 * 重要でない名称だけを取得するようなロジックで利用、ヌルチェックが不要になる。
	 */
	public E refer(K key){
		E entity = find(key, true);
		if (entity == null) {
			entity = newInstance(key);
		}
		return entity;
	}

	public List<E> findByQuery(String queryName, Map<String, Object> parameters) {
		return findByQuery(queryName, parameters, -1);
	}

	public List<E> findByQuery(String queryName, Map<String, Object> parameters, boolean deleteCheck) {
		return findByQuery(queryName, parameters, -1, deleteCheck);
	}

	public List<E> findByQuery(String queryName, Map<String, Object> parameters, int maxResult) {
		return findByQuery(queryName, parameters, maxResult, true);
	}

	public List<E> findByQuery(String queryName, Map<String, Object> parameters, int maxResult, boolean deleteCheck) {
		Query query = getEntityManager().createNamedQuery(queryName);
		for (String name : parameters.keySet()) {
			query.setParameter(name, parameters.get(name));
		}
		if (maxResult > 0) {
			query.setMaxResults(maxResult);
		}
		List<E> l = new ArrayList<E>();
		for (Object result : query.getResultList()) {
			E entity = getEntityClass().cast(result);
				l.add(entity);
		}
		return l;
	}

	public E findByQuerySingleResult(String queryName, Map<String, Object> parameters) {
		return findByQuerySingleResult(queryName, parameters, true);
	}

	public E findByQuerySingleResult(String queryName, Map<String, Object> parameters, boolean deleteCheck) {
		Query query = getEntityManager().createNamedQuery(queryName);
		for (String name : parameters.keySet()) {
			query.setParameter(name, parameters.get(name));
		}
		try{
			E entity = getEntityClass().cast(query.getSingleResult());
				if (entity != null) {
					return entity;
			}
		}catch(NoResultException e){

		}
		return null;
	}

	public E create(K key) {
		E entity = getEntityManager().find(getEntityClass(), key);
		entity = newInstance(key);
		getEntityManager().persist(entity);
		return entity;
	}

	private E newInstance(K key) {
		try {
			return getEntityClass().getConstructor(key.getClass()).newInstance(key);
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

	public void remove(E entity) {
		getEntityManager().remove(entity);
	}

	public void flush() {
		getEntityManager().flush();
	}

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
		flush();
		getEntityManager().refresh(entity);
	}

	public void refreshAll(Object entity) {
		refreshAll(entity, new ArrayList<Object>());
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
		flush();
		getEntityManager().refresh(entity);
		tmpList.add(entity);

		List<String> names = new ArrayList<String>();
		for (Field f : entity.getClass().getDeclaredFields()) {
			if (isJoinAnnotation(f.getAnnotations())) {
				if (f.isAccessible()) {
					try {
						refreshAll(f.get(entity), tmpList);
					}
					catch (IllegalAccessException x) {
					}
				}
				else {
					names.add(f.getName());
				}
			}
		}
		for (PropertyDescriptor desc : PropertyUtils.getPropertyDescriptors(entity.getClass())) {
			if (names.contains(desc.getName()) || isJoinAnnotation(desc.getReadMethod().getAnnotations())) {
				try {
					refreshAll(desc.getReadMethod().invoke(entity, new Object[0]), tmpList);
				}
				catch (InvocationTargetException x) {
				}
				catch (IllegalAccessException x) {
				}
			}
		}
		tmpList.remove(entity);
	}

	private boolean isJoinAnnotation(Annotation[] annotations) {
		for (Annotation a : annotations) {
			if (a instanceof ManyToOne ||
					a instanceof OneToMany ||
					a instanceof OneToOne ||
					a instanceof ManyToMany) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 指定されたSQLでエンティティを取得する
	 * @param sql
	 * @param parameters
	 * @param maxResult
	 * @return
	 */
	public List<E> findByQueryText(String sql, Map<String, Object> parameters, int maxResult) {
		//動的にクエリを作成
		Query query = getEntityManager().createQuery(sql);
		//パラ―メータをクエリにセット
		for (String name : parameters.keySet()) {
			query.setParameter(name, parameters.get(name));
		}
		if (maxResult > 0) {
			query.setMaxResults(maxResult);
		}
		//結果セットをエンティティにキャストし、エンティティリストを返す
		List<E> l = new ArrayList<E>();
		for (Object result : query.getResultList()) {
			E entity = getEntityClass().cast(result);
			l.add(entity);
		}
		return l;
	}
	/**
	 * 指定されたSQLでエンティティを取得する
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public List<E> findByQueryText(String sql, Map<String, Object> parameters) {
		return findByQueryText(sql, parameters, -1);
	}

	public E findByQueryTextSingle(String sql, Map<String, Object> parameters) {
		//動的にクエリを作成
		Query query = getEntityManager().createQuery(sql);
		//パラ―メータをクエリにセット
		for (String name : parameters.keySet()) {
			query.setParameter(name, parameters.get(name));
		}
		//結果セットをエンティティにキャストし、エンティティリストを返す
		List<E> l = new ArrayList<E>();
		for (Object result : query.getResultList()) {
			E entity = getEntityClass().cast(result);
			l.add(entity);
		}
		if(l.size()==0){
			return null;
		}
		return l.get(0);
	}

	/**
	 * エンティティを取得する
	 * 取得できない場合、新規作成する
	 * @param key
	 * @return
	 */
	public E getOrCreate(K key){
		E entity=find(key);
		if(entity==null){
			entity=create(key);
		}
		return entity;
	}

	public boolean exists(K key){
		if(find(key) == null){
			return false;
		}
		return true;
	}

	public List<E> getAll(){
		return getAll(true);
	}

	public List<E> getAll(boolean deleteCheck){
		return getOrderByAll(deleteCheck, "t.key");
	}

	public List<E> getOrderByAll(String...orderBy){
		return getOrderByAll(true, orderBy);
	}

	public List<E> getOrderByAll(boolean deleteCheck, String...orderBy){
		StringBuffer query = new StringBuffer();
		query.append("SELECT t FROM ");
		query.append(getEntityClass().getName() + " t");
		if(deleteCheck && EntityControlAbstract.class.isAssignableFrom(getEntityClass())){
			query.append(" WHERE t.deleteFlg <> '1' ");
		}
		query.append(" ORDER BY ");
		for(String token : orderBy){
			query.append(token);
			query.append(",");
		}
		query.deleteCharAt(query.length() - 1);
		Map<String, Object> parameters = new HashMap<String, Object>();
		return findByQueryText(query.toString(), parameters);
	}

	protected abstract Class<E> getEntityClass();

}
