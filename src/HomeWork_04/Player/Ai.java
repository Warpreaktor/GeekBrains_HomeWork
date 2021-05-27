package HomeWork_04.Player;

import HomeWork_04.Game;
import HomeWork_04.core.Sides;

import java.util.Random;

public class Ai extends Player {
    private char symbol = 'O';
    private int lastTurnX, lastTurnY;
    private int lastTurnHumanX, lastTurnHumanY;
    private boolean danger = false;
    private DangerZone dangerZone;

    public boolean isDanger() {
        return danger;
    }

    @Override
    public void turn(Game game) {
        if (game.getRoundCount() < 2) {
            strategyRandom(game);
            return;
        }
        if (danger) {
            if (strategyDanger(game) == false) {
                strategyRandom(game);
                return;
            }
            return;
        }
        if (strategyNeighborhood(game) == false) {
            strategyRandom(game);
            return;
        }
    }

    public void strategyRandom(Game game) {
        while (!game.isEndGame()) {
            int x = new Random().nextInt(game.getSize());
            int y = new Random().nextInt(game.getSize());
            if (game.gameField[y][x].getSymbol() == ' ') {
                game.gameField[y][x].setSymbol(symbol);
                game.gameTurn(x, y, symbol);
                lastTurnX = x;
                lastTurnY = y;
                break;
            }
        }
    }

    /**
     * Стратегия позволяет ai установить свой символ где-то рядом с тем котрый он поставилв свой последний НЕ danger ход.
     *
     * @return если все соседние клетки оказались заняты возвращает false.
     */
    public boolean strategyNeighborhood(Game game) {
        for (int i = lastTurnY - 1; i < lastTurnY + 2; i++) {
            for (int j = lastTurnX - 1; j < lastTurnX + 2; j++) {
                if (i >= game.gameField.length) {
                    continue;
                }
                if (i < 0) {
                    continue;
                }
                if (j >= game.gameField[lastTurnY].length) {
                    continue;
                }
                if (j < 0) {
                    continue;
                }
                if (game.gameField[i][j].getSymbol() != ' ') {
                    return false;
                } else {
                    game.gameField[i][j].setSymbol(symbol);
                    game.gameTurn(j, i, symbol);
                    lastTurnX = j;
                    lastTurnY = i;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Поведенческая модель, которая срабатывает в момент когда у человека есть вероятность выыигрыша.
     */
    public boolean strategyDanger(Game game) {
        int x = dangerZone.x;
        int y = dangerZone.y;
        char zSymbol = game.human.getSymbol();
        switch (dangerZone.side) {
            case HORIZONTAL:
                for (int i = 0; i < game.getSize(); i++) {
                    if (i - 1 >= 0 && game.gameField[y][i].getSymbol()
                            == zSymbol && game.gameField[y][i - 1].getSymbol() == ' ') {
                        game.gameField[y][i - 1].setSymbol(symbol);
                        danger = false;
                        game.gameTurn(i - 1, y, symbol);
                        return true;
                    }
                    if (i + 1 < game.getSize() && game.gameField[y][i].getSymbol()
                            == zSymbol && game.gameField[y][i + 1].getSymbol() == ' ') {
                        game.gameField[y][i + 1].setSymbol(symbol);
                        game.gameTurn(i + 1, y, symbol);
                        danger = false;
                        return true;
                    }
                }
                return false;
            case VERTICAL:
                for (int i = 0; i < game.getSize(); i++) {
                    if (i - 1 >= 0 && game.gameField[i][x].getSymbol()
                            == zSymbol && game.gameField[i - 1][x].getSymbol() == ' ') {
                        game.gameField[i - 1][x].setSymbol(symbol);
                        danger = false;
                        game.gameTurn(x, i - 1, symbol);
                        return true;
                    }
                    if (i + 1 < game.getSize() && game.gameField[i][x].getSymbol()
                            == zSymbol && game.gameField[i + 1][x].getSymbol() == ' ') {
                        game.gameField[i + 1][x].setSymbol(symbol);
                        game.gameTurn(x, i + 1, symbol);
                        danger = false;
                        return true;
                    }
                }
                return false;
            case DIAGONAL_ULR:
                for (int i = y, j = x; i >= 0 && j < game.getSize(); i--, j++) {
                    if (i + 1 < game.getSize() && j - 1 >= 0 && game.gameField[i][j].getSymbol() == zSymbol
                            && game.gameField[i + 1][j - 1].getSymbol() == ' ') {
                        game.gameField[i + 1][j - 1].setSymbol(symbol);
                        danger = false;
                        game.gameTurn(j - 1, i + 1, symbol);
                        return true;
                    }
                }
                for (int i = y, j = x; i < game.getSize() && j >= 0; i++, j--) {
                    if (i - 1 >= 0 && j + 1 < game.getSize() && game.gameField[i][j].getSymbol() == zSymbol
                            && game.gameField[i - 1][j + 1].getSymbol() == ' ') {
                        game.gameField[i - 1][j + 1].setSymbol(symbol);
                        game.gameTurn(j + 1, i - 1, symbol);
                        danger = false;
                        return true;
                    }
                }
            case DIAGONAL_DLR:
                for (int i = y, j = x; i < game.getSize() && j < game.getSize(); i++, j++) {
                    if (i - 1 >= 0 && j - 1 >= 0
                            && game.gameField[i][j].getSymbol() == zSymbol
                            && game.gameField[i - 1][j - 1].getSymbol() == ' ') {
                        game.gameField[i - 1][j - 1].setSymbol(symbol);
                        danger = false;
                        game.gameTurn(j - 1, i - 1, symbol);
                        return true;
                    }
                }
                for (int i = y, j = x; i < game.getSize() && j < game.getSize(); i--, j--) {
                    if (i + 1 < game.getSize() && j + 1 < game.getSize()
                            && game.gameField[i][j].getSymbol() == zSymbol
                            && game.gameField[i + 1][j + 1].getSymbol() == ' ') {
                        game.gameField[i + 1][j + 1].setSymbol(symbol);
                        danger = false;
                        game.gameTurn(j + 1, i + 1, symbol);
                        return true;
                    }
                }
        }
        return false;
    }

    /**
     * Стратегия, позволяющая ai продолжать выстраивать свою победную линию исходя из своего последнего хода.
     *
     * @return
     */
    public boolean strategyContinues() {
        return false;
    }


    public void setDangerZone(int x, int y, Sides side) {
        this.danger = true;
        this.dangerZone = new DangerZone(x, y, side);
    }

    public char getSymbol() {
        return symbol;
    }

    private class DangerZone {
        int x;
        int y;
        Sides side;

        public DangerZone(int x, int y, Sides side) {
            this.x = x;
            this.y = y;
            this.side = side;
        }
    }
}
