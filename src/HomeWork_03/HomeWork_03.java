package HomeWork_03;

import java.util.Random;
import java.util.Scanner;

public class HomeWork_03 {
    public static void main(String[] args) {
        //Task #1
        Game game = new Game();
        game.guessTheNumber();


    }
//1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
// При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
// После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).

    public static class Game {
        int number;
        int tryCounter = 3;

        public void guessTheNumber() {
            number = new Random().nextInt(10);
            while (tryCounter > 0) {
                System.out.println("Угадай число: ");
                Scanner scan = new Scanner(System.in);
                int numIn = scan.nextInt();
                if (numIn == number) {
                    System.out.println("Вы выиграли!");
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
                        tryAgain();
                    }
                    System.out.println("Введенное число больше загаданного");
                    if (tryCounter <= 0){
                        System.out.println("Вы проиграли");
                    }
                } else {
                    tryCounter--;
                    System.out.println("Введенное число меньше загаданного");
                    if (tryCounter <= 0){
                        System.out.println("Вы проиграли");
                        tryAgain();
                    }
                }
            }
            System.out.println("Game Over");
        }

        public boolean tryAgain() {
            System.out.println("Повторить игру еще раз?");
            Scanner scan = new Scanner(System.in);
            int answer = scan.nextInt();
            if (answer == 0) {
                return false;
            } else {
                return true;
            }
        }
    }
}