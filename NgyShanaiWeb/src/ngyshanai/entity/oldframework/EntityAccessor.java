package ngyshanai.entity.oldframework;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

public interface EntityAccessor<E, K> {

	public E find(K key);
	public List<E> findByQuery(String queryName, Map<String, Object> parameters);
	public List<E> findByQuery(String queryName, Map<String, Object> parameters, int maxResult);
	public E findByQuerySingleResult(String queryName, Map<String, Object> parameters);
	public E create(K key);

	public void remove(E entity);
	public void flush();
	public void refresh(Object entity);
	public void refreshAll(Object entity);
	public EntityManager getEntityManager();
	public E getReference(K key);

	public List<E> findByQueryText(String sql, Map<String, Object> parameters, int maxResult) ;
	public List<E> findByQueryText(String sql, Map<String, Object> parameters) ;
	public E getOrCreate(K key);
}
