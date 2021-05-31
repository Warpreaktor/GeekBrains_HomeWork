package HomeWork_06;


//1. Создать классы Собака и Кот с наследованием от класса Животное.

//3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.;
// плавание: кот не умеет плавать, собака 10 м.)

//4. * Добавить подсчет созданных котов, собак и животных.
//Идея реализации этого пункта заключается в том, чтобы при каждом вызове конкретного конструктора, инкрементировать
// соответствующий этому классу статический счетчик. При помощи созданного статического геттера getCatCounter()\getDogCounter()
// можно в любой момент получить количество суммарно созданных объектов за время работы программы.
public class Cat extends Animal {
    private String name;
    private static int catsCounter;

    public Cat(String name) {
        this.name = name;
        catsCounter++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public void run(int meters){
        if (meters < 1){
            System.out.printf("%s никуда не бежит. Попробуйте указать дистанцию побольше \n", name);
            return;
        }
        if (meters <= 200) {
            System.out.printf("%s пробежал %d м. \n", name, meters);
        }
        if (meters > 200) {
            System.out.printf("%s не может пробежать более 200 метров \n", name);
        }
    }

    public static int getCatsCounter() {
        return catsCounter;
    }
}
