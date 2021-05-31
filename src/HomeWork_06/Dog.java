package HomeWork_06;

//1. Создать классы Собака и Кот с наследованием от класса Животное.

//3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.;
// плавание: кот не умеет плавать, собака 10 м.)

//4. * Добавить подсчет созданных котов, собак и животных.
//Идея реализации этого пункта заключается в том, чтобы при каждом вызове конкретного конструктора, инкрементировать
// соответствующий этому классу статический счетчик. При помощи созданного статического геттера getCatCounter()\getDogCounter()
// можно в любой момент получить количество суммарно созданных объектов за время работы программы.

public class Dog extends Animal implements Swimable {
    private String name;
    private static int dogsCounter;
    private boolean isDead;

    public Dog(String name) {
        this.name = name;
        dogsCounter++;
    }

    public static int getDogsCounter() {
        return dogsCounter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public void run(int meters) {
        if(isDead){
            System.out.printf("%s мёртв и больше не может бежать \n", name);
            dogsCounter--;
            return;
        }

        if (meters < 1) {
            System.out.printf("%s никуда не бежит. Попробуйте указать дистанцию побольше \n", getName());
            return;
        }
        if (meters <= 500) {
            System.out.printf("%s пробежал %d м. \n", name, meters);
        }
        if (meters > 500) {
            System.out.printf("%s не может пробежать более 200 метров \n", name);
        }
    }

    @Override
    public void swim(int meters) {
        if(isDead){
            System.out.printf("%s мёртв и больше не может плыть \n", name);
            return;
        }
        if (meters < 1) {
            System.out.printf("%s лишь намочил лапки. Укажите большее расстояние чтобы он смог плыть. \n", name);
            return;
        }
        if (meters <= 10) {
            System.out.printf("%s проплыл %d м. \n", name, meters);
        }
        if (meters > 10) {
            System.out.printf("Бедный %s не смог проплыть %d метров и утонул =( \n", name, meters);
            isDead = true;
        }
    }
}
