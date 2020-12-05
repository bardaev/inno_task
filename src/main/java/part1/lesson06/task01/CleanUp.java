package part1.lesson06.task01;

import java.lang.reflect.Field;
import java.util.*;

public class CleanUp {
    public void cleanUp(Object object, Set<String> fieldsToCleanUp, Set<String> fieldsToOutput) {
        Class<?> cl = object.getClass();

        if (containsMap(cl.getInterfaces())) {
            mapCleanOutput(object, fieldsToCleanUp, fieldsToOutput);
        } else {
            fieldCleanUp(object, fieldsToCleanUp);
            fieldOutput(object, fieldsToOutput);
        }
    }

    private void mapCleanOutput(Object object, Set<String> fieldsToCleanUp, Set<String> fieldsToOutput) {
        Map<?, ?> map = (Map<?, ?>) object;

        Iterator<String> ftc = fieldsToCleanUp.iterator();
        Iterator<String> fto = fieldsToOutput.iterator();

        while (ftc.hasNext()) {
            String key = ftc.next();
            if (!map.containsKey(key)) {
                throw new IllegalArgumentException();
            } else {
                map.remove(key);
            }
        }

        while (fto.hasNext()) {
            String key = fto.next();
            if (!map.containsKey(key)) {
                throw new IllegalArgumentException();
            } else {
                System.out.println(key);
            }
        }
    }

    private void fieldOutput(Object object, Set<String> fieldsToOutput) {
        Class<?> cl = object.getClass();
        Iterator<String> iterator = fieldsToOutput.iterator();

        while (iterator.hasNext()) {
            try {
                Field f = cl.getDeclaredField(iterator.next());
                if (f.getType().isPrimitive()) {
                    System.out.println(f.getName() + " " + f.getType().getCanonicalName() + ": " + String.valueOf(f.get(object)));
                } else {
                    if (f.get(object) == null) {
                        System.out.println(f.getName() + " " + f.getType().getCanonicalName() + ": " + "null");
                    } else {
                        System.out.println(f.getName() + " " + f.getType().getCanonicalName() + ": " + f.get(object).toString());
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void fieldCleanUp(Object object, Set<String> fieldsToCleanUp) {
        Class<?> cl = object.getClass();
        Iterator<String> iterator = fieldsToCleanUp.iterator();
        String currentField = "";
        ArrayList<Field> fields = new ArrayList<>();

        while (iterator.hasNext()) {
            try {
                currentField = iterator.next();
                fields.add(cl.getDeclaredField(currentField));
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("Field " + currentField + " not exist");
            }
        }

        fields.stream()
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        setDefaultValue(object, field);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
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

    private boolean containsMap(Class<?>[] interfaces) {
        for (Class<?> cl : interfaces) {
            if (cl.getCanonicalName().equals("java.util.Map")) return true;
            return containsMap(cl.getInterfaces());
        }

        return false;
    }

}
