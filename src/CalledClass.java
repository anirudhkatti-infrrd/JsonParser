public class CalledClass {
    private int id;
    private String name;
    private String operation;
    private int a;
    private int b;

    /**
     * The ID setter method
     *
     * @param id
     */
    void setId(int id) {
        //System.out.print("inside id setter\n");
        this.id = id;
    }

    void setA(int a) {
        this.a = a;
    }

    void setB(int b) {
        if (b == 0 && operation.equals("div"))
            System.out.println("Division by zero");
        else
            this.b = b;
    }

    void getId() {
        System.out.print("inside id getter\n");
        System.out.println(id);
    }

    void setName(String name) {
        //System.out.print("inside name setter\n");
        this.name = name;
    }

    void getName() {
        System.out.print("inside name getter\n");
        System.out.println(name);
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

}