package com.bolsadeideas.springboot.app.util.objects;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ObjectsUtils {
	private static final char AT_SIGN = '@';

	public ObjectsUtils() {
		super();
	}

	
	public static boolean isEmptyNumber(Number... number) {
		
		return isEmpty(number) || number.length == 0;
	}
	
	public static boolean isEmpty(final Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof CharSequence) {
			return ((CharSequence) object).length() == 0;
		}
		if (object.getClass().isArray()) {
			return Array.getLength(object) == 0;
		}
		if (object instanceof Collection<?>) {
			return ((Collection<?>) object).isEmpty();
		}
		if (object instanceof Map<?, ?>) {
			return ((Map<?, ?>) object).isEmpty();
		}
		return false;
	}

	public static boolean isNotEmpty(final Object object) {
		return !isEmpty(object);
	}

	public static <T> T defaultIfNull(final T object, final T defaultValue) {
		return object != null ? object : defaultValue;
	}

	@SafeVarargs
	public static <T> T firstNonNull(final T... values) {
		if (values != null) {
			for (final T val : values) {
				if (val != null) {
					return val;
				}
			}
		}
		return null;
	}

	public static boolean anyNotNull(final Object... values) {
		return firstNonNull(values) != null;
	}

	public static boolean allNotNull(final Object... values) {
		if (values == null) {
			return false;
		}

		for (final Object val : values) {
			if (val == null) {
				return false;
			}
		}

		return true;
	}

	public static boolean equals(final Object object1, final Object object2) {
		if (object1 == object2) {
			return true;
		}
		if (object1 == null || object2 == null) {
			return false;
		}
		return object1.equals(object2);
	}

	public static boolean notEqual(final Object object1, final Object object2) {
		return !equals(object1, object2);
	}
	
	   public static String identityToString(final Object object) {
	        if (object == null) {
	            return null;
	        }
	        final String name = object.getClass().getName();
	        final String hexString = Integer.toHexString(System.identityHashCode(object));
	        final StringBuilder builder = new StringBuilder(name.length() + 1 + hexString.length());
	        // @formatter:off
	        builder.append(name)
	              .append(AT_SIGN)
	              .append(hexString);
	        // @formatter:off
	        return builder.toString();
	    }
	   

}
