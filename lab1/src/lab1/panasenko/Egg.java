package lab1.panasenko;

public class Egg extends Food {
    private String number;

    public Egg(String number) {
        super("Яйцо");
        this.number = number;
    }

    @Override
    public void consume() {
        System.out.println(this + " съедено");
    }

    @Override
    public String toString() {
        return super.toString() + " в количесте '" + number + "'";
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
