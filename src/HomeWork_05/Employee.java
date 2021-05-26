package HomeWork_05;

public class Employee {
    public static void main(String[] args) {
        Employee[] employees = createArray();
        infoAboutEmployee(employees, 30);
    }
    //1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
    private String fullname;
    private String function;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    //2. Конструктор класса должен заполнять эти поля при создании объекта.
    public Employee(String fullname, String function, String email, String phoneNumber, int salary, int age) {
        this.fullname = fullname;
        this.function = function;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }
    //3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
    @Override
    public String toString() {
        return "Employee{ \n" +
                "-fullname = " + fullname + ":\n" +
                "-function = " + function + "\n" +
                "-email = " + email + "\n" +
                "-phoneNumber = " + phoneNumber + "\n" +
                "-salary = " + salary + "\n" +
                "-age = " + age +
                '}';
    }
    //4. Создать массив из 5 сотрудников.
    public static Employee[] createArray(){
       Employee[] employees = new Employee[5];
       employees[0] = new Employee("Hamilton Cowie", "junior java developer", "ham@gmail.com", "8123456789", 60000, 23);
       employees[1] = new Employee("Kevin Maccalum", "middle java developer", "mac@gmail.com", "8923457789", 120000, 30);
       employees[2] = new Employee("Allan Donaldson", "senior java developer", "don@gmail.com", "8928556755", 240000, 37);
       employees[3] = new Employee("Nigel Dunn", "project manager", "dun@gmail.com", "8566459900", 240000, 28);
       employees[4] = new Employee("Calum Christie", "devops", "chr@gmail.com", "8953451874", 170000, 43);
       return employees;
    }
    //5. С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
    /**
     * Задайте нужный вам фильтр и получите инфо о сотрудниках.
     * @param age - Укажите минимальный возраст сотрудника попадающего под отбор.
     */
    public static void infoAboutEmployee(Employee[] employees, int age){
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].age >= age){
                System.out.println(employees[i]);
            }
        }
    }
}
