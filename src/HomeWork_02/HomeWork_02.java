package HomeWork_02;

public class HomeWork_02 {

    public static void main(String[] args) {
        setNegativeNumber();
        setIntegers();
        smallerThanSix();
        fillTheMatrix();

        //Test for task 5
        int[] arr = {-47,45, 64, 12, 44, -29, 20, 15, -10, -5, 80, 62};
        System.out.println(minInteger(arr));
        System.out.println(maxInteger(arr));
//
//        //Test for task 6
        int[] arr2 = {2, 5, 2, 1, 1, 1, 1, 1};
        System.out.println(checkBalance(arr2));

    }
    //1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    // С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static void setNegativeNumber(){
        int[] arr = {1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1){
                arr[i] = 0;
            }else{
                arr[i] = 1;
            }
            System.out.println(arr[i]);//for test
        }
    }
    //2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    public static void setIntegers(){
        int[] arr = new int[8];
        for (int i = 0, j = 0; i < arr.length; i++, j+=3) {
            arr[i] = j;
            System.out.println(arr[i]);//for test
        }
    }
    //3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;\
    public static void smallerThanSix(){
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6){
                arr[i] *= 2;
                System.out.println(arr[i]);
            }
        }
    }
    //4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    // и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
    public static void fillTheMatrix(){
        int[][] matrix = new int[10][10];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == i){
                    matrix[i][j] = 1;
                    System.out.print(matrix[i][j]);
                } else if (j == matrix.length-i-1) {
                    matrix[i][j] = 1;
                    System.out.print(matrix[i][j]);
                }else{
                    System.out.print(matrix[i][j]);
                }
            }
            System.out.println();
        }
    }
    //5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
    public static int minInteger(int[] arr){
        int min = arr[0];
        for(int itr: arr){
            if (itr < min){
                min = itr;
            }
        }
        return min;
    }
    public static int maxInteger(int[] arr){
        int max = arr[0];
        for(int itr: arr){
            if (itr > max){
                max = itr;
            }
        }
        return max;
    }
    //6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
    // если в массиве есть место, в котором сумма левой и правой части массива равны.
    // Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
    // граница показана символами ||, эти символы в массив не входят.

    /**
     * Идея такая: Берём первый элемент слева и сумму всех остальных элементов. Сравниваем. Если не равно,
     * то берём к первому левому элементу прибавляем второй по порядку и суммируем все остальные элементы кроме этих
     * двух, снова сравниваем обе получившиеся суммы. И так проверяем все эелементы.
     */
    public static boolean checkBalance(int[] arr){
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < arr.length-1; i++) {
            sumLeft+=arr[i];
            for (int j = arr.length-1; j > i; j--) {
                sumRight += arr[j];
            }
            if (sumLeft == sumRight){
                System.out.println("leftSide = " + sumLeft); // for test
                System.out.println("rightSide = " + sumRight); // for test
                return true;
            }
            sumRight = 0;
        }
        return false;
    }
}
