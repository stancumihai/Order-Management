package org.stancuMihai;

import org.stancuMihai.model.Client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

//        ConnectionFactory.doSth();
        Class<?> reflect = Client.class;
//        System.out.println(reflect.getSimpleName());
//        System.out.println(Modifier.isPublic(reflect.getModifiers()));
        Method[] methods = reflect.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                System.out.println(method.getName() + " :getter");
            } else if (method.getName().startsWith("set")) {
                System.out.println(method.getName() + " :setter");
            }
            for (Class<?> parameter : method.getParameterTypes()) {
                System.out.println("Type: " + parameter.getSimpleName());
            }
        }

//        Constructor<?> constructor = null;
//        Object constructor2 = null;
//        constructor = reflect.getConstructor(Client.class);
//        constructor2 = reflect.getConstructor(int.class, String.class).newInstance(12, "Andrei");
//        Class<?>[] constructParam = constructor.getParameterTypes();
//        for (Class<?> parameter : constructParam) {
//            System.out.println(parameter.getSimpleName());
//        }
        // Client client = (Client) constructor.newInstance(constructParam);

    }

    public static void doSth() {
        System.out.println("da");
    }
}
