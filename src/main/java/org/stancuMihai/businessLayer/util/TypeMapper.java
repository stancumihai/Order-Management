package org.stancuMihai.businessLayer.util;

/***
 * auxiliary for mapping types
 */
public class TypeMapper {

    public static String map(String type) {
        if (type.endsWith("String"))
            return "String";
        if (type.endsWith("Integer"))
            return "Int";
        else
            return "Float";
    }
}
