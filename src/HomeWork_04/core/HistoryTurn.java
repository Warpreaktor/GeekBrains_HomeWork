package HomeWork_04.core;

import HomeWork_04.Player.Player;

public class HistoryTurn {
    public int x;
    public int y;
    public char symbol;
    public Player owner;

    public HistoryTurn(int x, int y, char symbol, Player owner) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.owner = owner;
    }

    @Override
    public String toString(){
        return String.format("%s %c на X:%d Y:%d", owner.getClass().getSimpleName(), symbol, x +1, y +1);
    }
}
