package org.stancuMihai.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Constructor<T> {

    public List<String> constructTypes(Class<T> type) {
        ArrayList<String> types = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            types.add(TypeMapper.map(field.getAnnotatedType().toString()));
        }
        return types;
    }

    public List<Method> constructMethods(List<String> types) throws NoSuchMethodException {
        List<Method> methods = new ArrayList<>();
        for (String s : types) {
            if (s.equals("Int")) {
                methods.add(ResultSet.class.getMethod("get" + s, int.class));
            } else if (s.equals("String")) {
                methods.add(ResultSet.class.getMethod("get" + s, String.class));
            } else {
                methods.add(ResultSet.class.getMethod("get" + s, float.class));
            }
        }
        return methods;
    }

    public List<String> constructFields(Class<T> type) {
        List<String> attributes = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            attributes.add(field.getName());
        }
        return attributes;
    }

    public String updateQuery(List<String> attributes, Class<T> type) {
        StringBuilder toBeFormed = new StringBuilder();
        for (int i = 1; i < attributes.size(); i++) {
            if (i == attributes.size() - 1) {
                toBeFormed.append(attributes.get(i)).append("=? ");
            } else {
                toBeFormed.append(attributes.get(i)).append("=?, ");
            }
        }
        return "update " + type.getSimpleName().toLowerCase(Locale.ROOT) + " set " + toBeFormed + " where id = ?";
    }

    public String insertQuery(List<String> attributes, Class<T> type) {
        StringBuilder attributesQueryPart = new StringBuilder();
        StringBuilder signQueryPart = new StringBuilder();

        for (int i = 1; i < attributes.size(); i++) {
            attributesQueryPart.append(attributes.get(i));
            signQueryPart.append("?");
            if (i != attributes.size() - 1) {
                attributesQueryPart.append(",");
                signQueryPart.append(",");
            }
        }

        return "insert into " + type.getSimpleName().toLowerCase(Locale.ROOT) +
                "(" + attributesQueryPart + ")" + " values " +
                "(" + signQueryPart + ")";

    }
}
