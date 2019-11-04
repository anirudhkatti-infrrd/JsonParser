/**
 * @author Anirudh Katti
 */
public class MainClass {
    /**
     * This is a program mapping json to variables and returning objects.
     *
     * @param args
     */
    private CalledClass unknownObject;

    private void callMethod() {
        try {
            CalledClass object = new CalledClass();
            JsonParser parser = new JsonParser();
            object.setId(17);
            object.setName("Katz");
            System.out.println("-----------Before Change----------------");
            object.getId();
            object.getName();
            System.out.println("------------After Change----------------");
            unknownObject = parser.getData("{\"operation\":\"div\",\"a\":10,\"b\":0}", CalledClass.class);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainClass object = new MainClass();
        object.callMethod();
    }

}