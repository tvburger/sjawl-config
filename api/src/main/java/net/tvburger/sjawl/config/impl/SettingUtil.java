package net.tvburger.sjawl.config.impl;

import net.tvburger.sjawl.config.InvalidSettingException;
import net.tvburger.sjawl.config.Specification;

import java.util.List;

public final class SettingUtil {

    public static boolean prefixMatches(List<String> fieldPrefix, List<String> field) {
        for (int i = 0; i < fieldPrefix.size(); i++) {
            if (!fieldPrefix.get(i).equals(field.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean sameField(List<String> field1, List<String> field2) {
        if (field1.size() != field2.size()) {
            return false;
        }
        for (int i = 0; i < field1.size(); i++) {
            if (!field1.get(i).equals(field2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private SettingUtil() {
    }

}
