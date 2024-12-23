package lab1.panasenko;

public class Apple extends Food  {
    private String size;
    public Apple(String size) {
        super("Яблоко");
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + " размера '" + size + "' ";
    }

    public void consume() {
        System.out.println(this + " съедено");
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
