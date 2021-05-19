package HomeWork_03;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class HomeWork_03 {

    public static void main(String[] args) {
//        checkTask1();
//        checkTask2();
        solution();
    }
    public static void checkTask1() {
        //Task #1
        Game game1 = new Game();
        game1.guessTheNumber();

    }

    public static void checkTask2() {
        //Task #2
        String[] words = {"apple", "orange", "lemon", "banana", "apricot",
                "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
                "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Game game2 = new Game();
        game2.guessTheWord(words);
    }

//1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
// При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
// После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).

    public static class Game {
        int number;
        String word;
        int tryCounter = 3;

        public void guessTheNumber() {
            Scanner scan = new Scanner(System.in);
            number = new Random().nextInt(10);
            while (tryCounter > 0) {
                System.out.println("Угадай число от 0 до 9: ");
                int numIn = scan.nextInt();
                if (numIn == number) {
                    System.out.println("Вы угадали! Это число: " + number);
                    if (tryAgain()) {
                        tryCounter = 3;
                        number = new Random().nextInt(10);
                        continue;
                    } else {
                        break;
                    }
                } else if (number < numIn) {
                    tryCounter--;
                    if (tryCounter <= 0) {
                        System.out.println("Вы проиграли");
                        if (tryAgain()) {
                            tryCounter = 3;
                            number = new Random().nextInt(10);
                            continue;
                        } else {
                            break;
                        }
                    }
                    System.out.println("Введенное число больше загаданного");
                    System.out.println("Осталось попыток: " + tryCounter);
                } else {
                    tryCounter--;
                    if (tryCounter <= 0) {
                        System.out.println("Вы проиграли");
                        if (tryAgain()) {
                            tryCounter = 3;
                            number = new Random().nextInt(10);
                            continue;
                        } else {
                            break;
                        }
                    }
                    System.out.println("Введенное число меньше загаданного");
                    System.out.println("Осталось попыток: " + tryCounter);
                }
            }
            System.out.println("Игра окончена");
        }

        public boolean tryAgain() {
            System.out.println("Повторить игру еще раз?");
            try {
                Scanner scan = new Scanner(System.in);
                int answer = scan.nextInt();
                if (answer == 0) {
                    return false;
                } else {
                    return true;
                }
            }
            catch (NoSuchElementException e){
                System.out.println("Не корректный ввод, введите 1, если хотите повторить игру " +
                        "и 0 если хотите закончить");
                return tryAgain();
            }
        }

//    2 * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot",
//     "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
//     "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
//    При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
//    сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
//    Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
//            apple – загаданное
//    apricot - ответ игрока
//    ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
//    Для сравнения двух слов посимвольно, можно пользоваться:
//    String str = "apple";
//    str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
//    Играем до тех пор, пока игрок не отгадает слово
//    Используем только маленькие буквы

        public void guessTheWord(String[] words) {
            tryCounter = 1;
            Scanner scan = new Scanner(System.in);
            word = words[new Random().nextInt(words.length)];
            while (true) {
                System.out.println("Попытка №" + tryCounter);
                System.out.println("Угадай слово: ");
                String wordIn = scan.nextLine();
                if (word.equals(wordIn)) {
                    System.out.println("Вы угадали! Это слово: " + word);
                    if (tryAgain()) {
                        word = words[new Random().nextInt(words.length)];
                        tryCounter = 1;
                        continue;
                    }else{
                        break;
                    }
                } else {
                    showChars(wordIn);
                    tryCounter++;
                }
            }
            System.out.println("Игра окончена");
        }

        public void showChars(String wordIn) {
            StringBuilder sb = new StringBuilder();
            sb.append("###############");
            int position = 0;
            wordIn = wordIn.toLowerCase(Locale.ROOT);
                while (position < word.length()) {
                    while (position < wordIn.length()) {
                        if (word.charAt(position) == wordIn.charAt(position)) {
                            sb.setCharAt(position, word.charAt(position));
                            break;
                        }else{
                            break;
                        }
                    }
                    position++;
                }
            System.out.println(sb);
        }
    }

    public static void solution(){
        String s = "Предложение       один   Теперь    предложение   два       Предложение         три     ";
        String s1 = s.replaceAll(" +", " ");
        System.out.println(s1);

        StringBuilder s2 = new StringBuilder(s1);

        for (int i = 1; i < s2.length(); i++) {
            if (s1.charAt(i) >= 'А' && s1.charAt(i) <= 'Я'){
                CharSequence charSequence = ". ";
//                s2.append(charSequence);
                s2.setCharAt(i - 1, '.');
            }
        }
        s2.append(".");
        System.out.println(s2);
    }

}