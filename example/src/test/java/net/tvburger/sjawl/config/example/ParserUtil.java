package net.tvburger.sjawl.config.example;

import net.tvburger.sjawl.config.InvalidSettingException;
import net.tvburger.sjawl.config.MissingSettingException;
import net.tvburger.sjawl.config.Specification;

import java.util.List;

public final class ParserUtil {

    public static int parseInt(List<String> field, Specification specification) throws MissingSettingException, InvalidSettingException {
        try {
            return Integer.parseInt(specification.getValue(String.class, field));
        } catch (NumberFormatException cause) {
            throw new InvalidSettingException(specification, specification.getSetting(field));
        }
    }

    public static boolean parseBoolean(List<String> field, Specification specification) throws MissingSettingException, InvalidSettingException {
        try {
            boolean bool;
            String value = specification.getValue(String.class, field);
            switch (value) {
                case "true":
                    bool = true;
                    break;
                case "false":
                    bool = false;
                    break;
                default:
                    throw new InvalidSettingException(specification, specification.getSetting(field));
            }
            return bool;
        } catch (NumberFormatException cause) {
            throw new InvalidSettingException(specification, specification.getSetting(field));
        }
    }

    private ParserUtil() {
    }

}
