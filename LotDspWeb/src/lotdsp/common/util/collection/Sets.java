package lotdsp.common.util.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Sets {

	public static <E> Set<E> newHashSet() {
		return new HashSet<E>();
	}

	public static <E> Set<E> newHashSet(int size) {
		return new HashSet<E>(size);
	}

	@SafeVarargs
	public static <E> Set<E> newHashSet(E... elements) {
		Set<E> set = newHashSet(elements.length);
		Collections.addAll(set, elements);
		return set;
	}

	public static <E> Set<E> newLinkedHashSet() {
		return new LinkedHashSet<E>();
	}

	public static <E> Set<E> newLinkedHashSet(int size) {
		return new LinkedHashSet<E>(size);
	}

	public static <E extends Comparable<?>> Set<E> newTreeSet() {
		return new TreeSet<E>();
	}

	public static boolean isNullOrEmpty(final Set<?> set) {
		return (set == null || set.isEmpty());
	}

}
