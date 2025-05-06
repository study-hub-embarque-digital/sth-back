package com.studyhub.sth.libs.application;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FieldUtilities {
    public static boolean isNumericType(Field field) {
        return field.getType() == byte.class || field.getType() == short.class || field.getType() == int.class ||
                field.getType() == long.class || field.getType() == float.class || field.getType() == double.class ||
                field.getType() == char.class || field.getType() == Byte.class || field.getType() == Short.class || field.getType() == Integer.class ||
                field.getType() == Long.class || field.getType() == Float.class || field.getType() == Double.class;
    }

    public static boolean isStringType(Field field) {
        return field.getType() == String.class;
    }

    public static boolean isListType(Field field) {
        return field.getType().isArray() || field.getType().equals(List.class);
    }

    public static boolean isBooleanType(Field field) {
        return field.getType() == boolean.class || field.getType() == Boolean.class;
    }

    public static boolean isDateType(Field field) {
        return field.getType() == Date.class;
    }

    public static boolean isUUIDType(Field field) {
        return field.getType() == UUID.class;
    }
}
