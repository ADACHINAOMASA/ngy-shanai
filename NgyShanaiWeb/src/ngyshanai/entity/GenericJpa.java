package ngyshanai.entity;

import java.io.Serializable;

/**
 * 
 * @author Leang-Say
 *
 * @param <T>
 * @param <ID>
 */
public interface GenericJpa<T, ID extends Serializable> {
	T create(T t);

	T read(ID id);

	T update(T t);

	void delete(T t);

	void flush();

	void refresh(T t);

	void refreshAll(T t);
}