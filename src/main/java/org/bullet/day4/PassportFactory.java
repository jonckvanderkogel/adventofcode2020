package org.bullet.day4;

import io.vavr.Tuple2;

import java.lang.reflect.Field;
import java.util.List;

public class PassportFactory {
    public static Passport createPassport(List<Tuple2<String,Object>> tuples) {
        Passport passport = new Passport();

        tuples.stream()
                .forEach(tuple -> {
                    Field field = getField(passport, tuple._1());
                    setValueToField(passport, field, tuple._2());
                });

        return passport;
    }

    private static Field getField(Passport passport, String fieldName) {
        Field field;
        try {
            field = passport.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(String.format("Field %s not present in passport", fieldName));
        }
    }

    private static void setValueToField(Passport passport, Field field, Object value) {
        try {
            field.set(passport, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(String.format("Cannot set value %s on field %s", value, field.getName()));
        }
    }
}
