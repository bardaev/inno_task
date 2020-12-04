package part1.lesson07.task01;

import java.lang.reflect.Field;
import java.util.*;

public class CleanUpStream {
    public void cleanUp(Object object, Set<String> fieldsToCleanUp, Set<String> fieldsToOutput) {
        Class<?> cl = object.getClass();

        if (isMap(cl.getInterfaces())) {
            mapCleanOutput(object, fieldsToCleanUp, fieldsToOutput);
        } else {
            fieldCleanUp(object, fieldsToCleanUp, cl);
            fieldOutput(object, fieldsToOutput, cl);
        }
    }

    private void mapCleanOutput(Object object, Set<String> fieldsToCleanUp, Set<String> fieldsToOutput) {
        Map<?, ?> map = (Map<?, ?>) object;

        fieldsToCleanUp.stream()
                .forEach((k) -> {
                    if (!map.containsKey(k)) throw new IllegalArgumentException("Field not exist");
                    else map.remove(k);
                });

        fieldsToOutput.stream()
                .forEach((k) -> {
                    if (!map.containsKey(k)) throw new IllegalArgumentException("Field not exist");
                    else System.out.println(k);
                });
    }

    private void fieldOutput(Object object, Set<String> fieldsToOutput, Class<?> cl) {

        fieldsToOutput.stream()
                .map((c) -> {
                    try {
                        Field f = cl.getDeclaredField(c);
                        if (f.getType().isPrimitive()) {
                            return f.getName() + " " + f.getType().getCanonicalName() + ": " + String.valueOf(f.get(object));
                        } else {
                            if (f.get(object) == null) {
                                return f.getName() + " " + f.getType().getCanonicalName() + ": " + "null";
                            } else {
                                return f.getName() + " " + f.getType().getCanonicalName() + ": " + f.get(object).toString();
                            }
                        }
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new IllegalArgumentException("Field not exist");
                    }
                })
                .forEach(System.out::println);

    }

    private void fieldCleanUp(Object object, Set<String> fieldsToCleanUp, Class<?> cl) {

        fieldsToCleanUp.stream()
                .forEach((c) -> {
                    try {
                        Field f = cl.getDeclaredField(c);
                        f.setAccessible(true);
                        setDefaultValue(object, f);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new IllegalArgumentException("Field not exist");
                    }
                });
    }

    private void setDefaultValue(Object object, Field field) throws IllegalAccessException {
        if (field.getType().isPrimitive()) {
            if (field.getType().getName() == "boolean") {
                field.setBoolean(object, false);
            } else if (field.getType().getName() == "char") {
                field.setChar(object, '\u0000');
            } else {
                field.set(object, 0);
            }
        } else {
            field.set(object, null);
        }
    }

    private boolean isMap(Class<?>[] c) {
        for (Class<?> cl : c) {
            if (cl.getCanonicalName().equals("java.util.Map")) return true;
            return isMap(cl.getInterfaces());
        }

        return false;
    }
}