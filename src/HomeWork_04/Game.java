package HomeWork_04;

import HomeWork_04.Player.Ai;
import HomeWork_04.Player.Human;
import HomeWork_04.Player.Player;
import HomeWork_04.core.GameObject;
import HomeWork_04.core.Sides;
import HomeWork_04.core.HistoryTurn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Game extends Application {
    //Глобальные переменные
    public Game game;
    public Stage stage;
    public Human human;
    public Ai ai;
    public boolean isHumanTurn;
    public int winnersLine = 4; //Количество одинаковых знаков в линию необходимых для победы
    public int dangerLine = winnersLine - 2; //Количество одинаковых знаков в линию необходимых для победы
    public GameObject[][] gameField;
    public ArrayList<HistoryTurn> gameHistory; //лог всех сделанных ходов в игре

    //Инкапсулированные
    private int SIZE = 5;
    private boolean endGame;
    private int roundCount;
    private Controller mainController;

    public boolean isEndGame() {
        return endGame;
    }

    public int getSize() {
        return SIZE;
    }

    public int getRoundCount() {
        return roundCount;
    }

    /**
     * Точка входа в игру.
     */
    @Override
    public void start(Stage stage) {
        Game game = new Game();
        game.gameInit(stage);
        game.gameScreenInit();
    }

    /**
     * Настройки экрана игры
     * @param stage
     */
    private void stageSettings(Stage stage) {
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setResizable(false);
    }
    /**
     * Декларативная инициализация игрового экрана.
     */
    private void gameScreenInit() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameScreen.fxml")));
            mainController.fieldInit(this);
            mainController.brushTheField(this.stage, root);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    /**
     * Инициализация всех глобальных переменных необходимх для успешного запуска игры.
     */
    public void gameInit(Stage stage) {
        this.game = new Game();
        this.human = new Human();
        this.ai = new Ai();
        this.gameField = new GameObject[SIZE][SIZE];
        this.stage = stage;
        stageSettings(stage);
        this.roundCount = 1;
        this.isHumanTurn = true;
        this.mainController = new Controller();
        gameHistory = new ArrayList<>();
    }

    /**
     * Метод в котором описывается механика в рамках одного игрового хода. Метод вызывается каждый раз,
     * когда один из игроков заканчивает свой ход.
     */
    public void gameTurn(int x, int y, char symbol) {
        Player owner = isHumanTurn ? human : ai;
        gameHistory.add(new HistoryTurn(x, y, symbol, owner));
        if (isEndGame()) {
            return;
        }
        roundCount++;
        checkDZFromClick(x, y, symbol);
        switchTurn();
        if (!isHumanTurn) {
            ai.turn(this);
        }
    }

    /**
     * Нижеописанные методы  "check*" сканируют последний ход любого игрока на предмет выигрышной линии
     * или линии на которой может возникнуть потенциальная победа.
     * При возникновении второго варианта, метод переводит ai в режим опасности и передает ему
     * координаты линии в которой опасность.
     * @return почти все методы возвращают булево, давая таким образом понять, успешно закончился поиск или нет.
     */
    public void checkDZFromClick(int x, int y, char symbol) {
        if (checkThisHorizontal(x, y, symbol)) {
            ai.setDangerZone(x, y, Sides.HORIZONTAL);
            return;
        }
        if (checkThisVertical(x, y, symbol)) {
            ai.setDangerZone(x, y, Sides.VERTICAL);
            return;
        }
        if (checkThisDiagonalULR(x, y, symbol)) {
            ai.setDangerZone(x, y, Sides.DIAGONAL_ULR);
            return;
        }
        if (checkThisDiagonalDLR(x, y, symbol)) {
            ai.setDangerZone(x, y, Sides.DIAGONAL_DLR);
            return;
        }
    }

    public boolean checkThisDiagonalULR(int x, int y, char symbol) {
        int dangerCombo = 0;
        int space = 0;
        //Вверх вправо
        for (int i = y, j = x; i >= 0 && j < SIZE; i--, j++) {
            if (gameField[i][j].getSymbol() == symbol) {
                dangerCombo++;
            }
            if (gameField[i][j].getSymbol() == ' ') {
                space++;
            }
        }
        //Вниз влево
        dangerCombo--;
        for (int i = y, j = x; i < SIZE && j >= 0; i++, j--) {
            if (gameField[i][j].getSymbol() == symbol) {
                dangerCombo++;
            }
            if (gameField[i][j].getSymbol() == ' ') {
                space++;
            }
        }
        if (dangerCombo == winnersLine) {
            victory();
        }
        if (dangerCombo == dangerLine && space > 0) {
            return true;
        }
        return false;
    }

    public boolean checkThisDiagonalDLR(int x, int y, char symbol) {
        int dangerCombo = 0;
        int space = 0;
        //Вверх влево
        for (int i = y, j = x; i >= 0 && j >= 0; i--, j--) {
            if (gameField[i][j].getSymbol() == symbol) {
                dangerCombo++;
            }
            if (gameField[i][j].getSymbol() == ' ') {
                space++;
            }
        }
        //Вниз вправо
        dangerCombo--;
        for (int i = y, j = x; i < SIZE && j < SIZE; i++, j++) {
            if (gameField[i][j].getSymbol() == symbol) {
                dangerCombo++;
            }
            if (gameField[i][j].getSymbol() == ' ') {
                space++;
            }
        }
        if (dangerCombo == winnersLine) {
            victory();
        }
        if (dangerCombo >= dangerLine && space > 0) {
            return true;
        }
        return false;
    }

    public boolean checkThisVertical(int x, int y, char symbol) {
        int dangerCombo = 0;
        int space = 0;
        for (int i = 0; i < SIZE; i++) {
            if (gameField[i][x].getSymbol() == symbol) {
                dangerCombo++;
            }
            if (gameField[i][x].getSymbol() == ' ') {
                space++;
            }
            if (dangerCombo == winnersLine) {
                victory();
            }
            if (dangerCombo == dangerLine && space > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkThisHorizontal(int x, int y, char symbol) {
        int dangerCombo = 0;
        int space = 0;
        for (int j = 0; j < SIZE; j++) {
            if (gameField[y][j].getSymbol() == symbol) {
                dangerCombo++;
            }
            if (gameField[y][j].getSymbol() == ' ') {
                space++;
            }
            if (dangerCombo == winnersLine) {
                victory();
            }
            if (dangerCombo == dangerLine && space > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     *  Событие наступающее при чтей либо победе
     */
    public void victory() {
        endGame = true;
        if (isHumanTurn) {
            System.out.println("Победил человек!");
        } else {
            System.out.println("Победил компьютер");
        }
    }

    /**
     * Переключатель хода с текущего игрока на другого.
     */
    public void switchTurn() {
        this.isHumanTurn = !isHumanTurn;
    }
}
