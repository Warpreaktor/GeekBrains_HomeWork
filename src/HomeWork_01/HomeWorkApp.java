package HomeWork_01;

class HomeWork_01 {
    //#1 Создать пустой проект в IntelliJ IDEA и прописать метод main();
    public static void main(String[] args) {
        //#2 Создать переменные всех пройденных типов данных, и инициализировать их значения;
        byte b = 127;
        short s = 22222;
        int i = 3333333;
        long l = 44444444444444L;

        float f = 45.5f;
        double d = 5765.5;

        char c = 'C';
        String str = "Я учу Java";

    /*----- Test block --------*/

//        expression(123, 44, 678,12);
//        printInteger(i);
//        isNegative(-80);
//        myNameIs("Aleksey");
//        isLeapYear(2002);
    }

    //#3 Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,где a, b, c, d – входные параметры этого метода;
    public static int expression(int a, int b, int c, int d){
        return a * (b + (c / d));
    }

    //#4 Написать метод, принимающий на вход два числа, и проверяющий что их
    // сумма лежит в пределах от 10 до 20(включительно), если да – вернуть true, в противном случае – false;
    public static boolean checkSum(int num){
        if (num >= 10 && num <= 20){
            return true;
        }else return false;
    }

    //#5 Написать метод, которому в качестве параметра передается целое число,
    // метод должен напечатать в консоль положительное ли число передали, или отрицательное;
    // Замечание: ноль считаем положительным числом.
    public static void printInteger(int i){
        if (i >= 0) {
            System.out.println("Положительное");
        }
        else {
            System.out.println("Отрицательное");
        }
    }

    //#6 Написать метод, которому в качестве параметра передается целое число,
    // метод должен вернуть true, если число отрицательное;
    public static boolean isNegative(int i){
        if (i < 0) {
            return true;
        }else return false;
    }

    //#7 Написать метод, которому в качестве параметра передается строка, обозначающая имя,
    // метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
    public static void myNameIs(String name){
        System.out.println("Привет, " + name + "!");
    }

    //#8 Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный
    public static void isLeapYear(int year) {
        if ((year % 400 != 0) && (year % 100 == 0)) {
            System.out.println("количество дней в году:" + " " + 365);
        } else if ((year % 400 == 0 && (year % 100 == 0) || (year % 4 == 0))){
            System.out.println("количество дней в году:" + " " + 366);
        } else {
            System.out.println("количество дней в году:" + " " + 365);
        }
    }
}
