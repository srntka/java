package lab1.panasenko;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        Food[] breakfast = new Food[20];
        int itemsSoFar = 0;
        for (String arg : args) {
            String[] parts = arg.split("/");
            switch (parts[0]) {
                case "Cheese" ->
                    // У сыра дополнительных параметров нет
                        breakfast[itemsSoFar] = new Cheese();
                case "Apple" ->

                        breakfast[itemsSoFar] = new Apple(parts[1]);
                case "Egg" ->

                        breakfast[itemsSoFar] = new Egg(parts[1]);
            }
            itemsSoFar++;
        }
        for (Food item : breakfast)
            if (item != null)
                item.consume();
            else break;
        Food targetFood = new Egg(""); // Эталонный объект
        //Эталонный объект — это экземпляр класса, созданный с целью сравнения других объектов с ним.
        //+ в том что не нужно переопределять equals для потомков
        int eggCount = countFood(breakfast, targetFood);
        System.out.println("Количество яиц: " + eggCount);

    }

    public static int countFood(Food[] breakfast, Food targetFood) {
        int count = 0;
        for (Food food : breakfast) {
            if (food != null && food.equals(targetFood)) {
                count++;
            }
        }
        return count;
    }

}

