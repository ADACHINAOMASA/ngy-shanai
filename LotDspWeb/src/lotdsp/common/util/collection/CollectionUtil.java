package lotdsp.common.util.collection;

import java.util.Collection;

public final class CollectionUtil {

	private CollectionUtil(){}

	public static boolean isNullOrEmpty(final Collection<?> col){
		return (col==null || col.isEmpty());
	}

	public static boolean exist(final Collection<?> col){
		return !isNullOrEmpty(col);
	}

}
