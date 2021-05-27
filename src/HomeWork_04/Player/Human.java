package HomeWork_04.Player;

import HomeWork_04.Game;

public class Human extends Player{
    private char symbol = 'X';

    @Override
    public void turn(Game game) {
        //ничего не делает
    }

    public char getSymbol() {
        return symbol;
    }
}
