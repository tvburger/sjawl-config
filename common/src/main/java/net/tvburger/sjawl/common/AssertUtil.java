package net.tvburger.sjawl.common;

import java.io.File;

public final class AssertUtil {

    public static void assertNotEmpty(String string) {
        AssertUtil.assertNotNull(string);
        if (string.isEmpty()) {
            throw new IllegalArgumentException("No empty string allowed!");
        }
    }

    public static void assertNotNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("No null allowed!");
        }
    }

    public static void assertIsDirectory(File file) {
        assertNotNull(file);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Must be a directory!");
        }
    }

    private AssertUtil() {
    }

}
