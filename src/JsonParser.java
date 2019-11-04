import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


class JsonParser {
    private Method method;
    private Class objectClassName;
    private Object objectInstance;
    <E> E getData(String json, Class <E> className) throws IllegalAccessException, InstantiationException {
        try {
            String name = className.getName();
            objectClassName = Class.forName(name);
            objectInstance = objectClassName.newInstance();
            jsonParser(json);
        } catch (IllegalAccessException | NoSuchFieldException | ClassCastException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return (E) objectInstance;
    }

    /**
     * This is the second method of the json parser and divides the data based on  '{}' brackets
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    void jsonParser(String json) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String words = json.substring(json.indexOf("{") + 1, json.indexOf("}"));
        jsonStageTwo(words);
    }

    /**
     * THis is the next stage of the json parsing and is done by the ',' symbol.
     * @param words
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    void jsonStageTwo(String words) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String secondSplit[] = words.split(",");
        jsonStageThree(secondSplit);
    }

    /**
     * This is the last stage of the json parsing and divides the key value here.
     * @param words
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    void jsonStageThree(String [] words) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        for (String word : words) {
            if (word != "" || word != " ") {
                String lastSplit[] = word.split(":");
                lastSplit[0] = lastSplit[0].replace("\"", "");
                lastSplit[1] = lastSplit[1].replace("\"", "");
                String methodName = "set"+lastSplit[0].substring(0, 1).toUpperCase() + lastSplit[0].substring(1);
                // Sets the field to the new value for this instance
                Field fieldType=objectClassName.getDeclaredField(lastSplit[0]);
                method = objectClassName.getDeclaredMethod(methodName, fieldType.getType());
                Class fieldName=fieldType.getType();
                if(fieldName==int.class) {
                    int currentNumber = Integer.parseInt(lastSplit[1]);
                    method.invoke(objectInstance, currentNumber);
                }else if(fieldName==Double.class) {
                    double currentNumber = Double.parseDouble(lastSplit[1]);
                    method.invoke(objectInstance, currentNumber);
                }else if(fieldName==float.class) {
                    Float currentNumber = Float.parseFloat(lastSplit[1]);
                    method.invoke(objectInstance, currentNumber);
                } else {
                    method.invoke(objectInstance, lastSplit[1]);
                }
            }
        }
    }
}