package allen.util;

/**
 * Provide some util method for array. This is null safe util class.
 * */
public final class ArrayUtil {

    private ArrayUtil() {
    }

    /**
     * test equals of 2 bytes. null equals null.
     * */
    public static boolean equals(byte[] a, byte[] b) {
        if (a == null && b == null)
            return true;

        if (a == null || b == null)
            return false;

        if (a.length != b.length)
            return false;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * test if data start with prefix.
     * */
    public static boolean startWith(byte[] data, byte[] prefix) {
        if (data == null || prefix == null) {
            return false;
        }
        if (data.length < prefix.length) {
            return false;
        }
        for (int i = 0; i < prefix.length; i++) {
            if (data[i] != prefix[i]) {
                return false;
            }
        }
        return true;
    }
}
