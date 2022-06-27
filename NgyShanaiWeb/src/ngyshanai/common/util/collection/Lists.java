package ngyshanai.common.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Lists {

	public static <E> List<E> newArrayList() {
		return new ArrayList<E>();
	}

	public static <E> ArrayList<E> newArrayList(int initialArraySize) {
		return new ArrayList<E>(initialArraySize);
	}

	@SafeVarargs
	public static <E> List<E> newArrayList(E... elements) {
		List<E> list = new ArrayList<E>();
		Collections.addAll(list, elements);
		return list;
	}

	public static <E> List<E> toArrayList(Collection<E> col) {
		return new ArrayList<E>(col);
	}

	public static <E> List<E> newLinkedList() {
		return new LinkedList<E>();
	}

	@SafeVarargs
	public static <E> List<E> newLinkedList(E... elements) {
		List<E> list = new LinkedList<E>();
		Collections.addAll(list, elements);
		return list;
	}

	public static <E> List<E> toLinkedList(Collection<E> col) {
		return new LinkedList<E>(col);
	}

	public static boolean isNullOrEmpty(List<?> e) {
		return (e == null || e.isEmpty());
	}

	public static boolean isNotNullOrEmpty(List<?> e) {
		return !isNullOrEmpty(e);
	}

	public static boolean exist(List<?> e) {
		return !isNullOrEmpty(e);
	}
	
	public static <E> List<E> newArrayListIfNull(List<E> list) {
		if(list==null) {
			return new ArrayList<E>();
		}
		return list;
	}

}
