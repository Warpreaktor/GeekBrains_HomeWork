package HomeWork_06;

public class Main {
    public static void main(String[] args) {
        Dog bobik = new Dog("Бобик");
        Dog tusik = new Dog("Тузик");
        Dog rex = new Dog("Рекс");
        System.out.println("------------------");
        System.out.println("Всего собак = " + Dog.getDogsCounter());
        System.out.println("------------------");
        System.out.println("Собаки бегите и плывите!");
        bobik.run(600);
        tusik.run(350);
        tusik.swim(0);
        rex.swim(10);
        rex.swim(25);
        rex.run(100);

        System.out.println("------------------");
        System.out.println("Всего  осталось собак = " + Dog.getDogsCounter());
        System.out.println("------------------");

        System.out.println();
        System.out.println("------------------");
        System.out.println("Коты бегите и плывите!");
        Cat vaska = new Cat("Васька");
        Animal pushok = new Cat("Пушок");
        Cat murka = new Cat("Мурчаль");
        Cat busya = new Cat("Бегемот");

        vaska.run(300);
        pushok.run(-5);
        murka.run(100);
        busya.run(200);

        System.out.println("------------------");
        System.out.println("Всего котов = " + Cat.getCatsCounter());
    }
}
