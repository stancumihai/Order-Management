package org.stancuMihai.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Construction<T> {

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
}
