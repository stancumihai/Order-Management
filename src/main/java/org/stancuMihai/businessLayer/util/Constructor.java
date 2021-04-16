package org.stancuMihai.businessLayer.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/***
 * Util class that eases some business logic
 * @param <T>
 */
public class Constructor<T> {

    /***
     *
     * @param type type of the class
     * @return it returns the types of all the fields in the class
     */
    public List<String> constructTypes(@org.jetbrains.annotations.NotNull Class<T> type) {
        ArrayList<String> types = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            types.add(TypeMapper.map(field.getAnnotatedType().toString()));
        }
        return types;
    }
    /***
     *
     * @param type type of the class
     * @return it returns the fields in the class
     */
    public List<String> constructFields(Class<T> type) {
        List<String> attributes = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            attributes.add(field.getName());
        }
        return attributes;
    }

    /***
     *
     * @param attributes the attributes of the class
     * @param type the T generic type of the class
     * @return it returns the update query constructed in SQL manner
     */
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

    /***
     *
     * @param attributes the attributes of the class
     * @param type the T generic type of the class
     * @return it returns the insert query constructed in SQL manner
     */
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
