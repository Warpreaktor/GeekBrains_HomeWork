package HomeWork_04;

import HomeWork_04.core.GameObject;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameScreen {
    private Group visualGroup = new Group();
    public BorderPane background;
    public VBox visualField;

    private double borderPadding = 100;
    private Game game;
    private Stage stage;

    /**
     * Инициализации контроллера.
     */
    public GameScreen(Game game, Stage stage, GameMode gameMode) {
        this.game = game;
        this.stage = stage;
        backgroundInit();
        fieldInit();
        brushTheField();
    }

    /**
     * Инициализация заднего фона.
     */
    private void backgroundInit() {
        background = new BorderPane();
        double width = stage.getWidth();
        double height = stage.getHeight();
        background.setPrefWidth(width);
        background.setPrefHeight(height);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("HomeWork_04/resources/background.jpg"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        background.setBackground(new Background(backgroundImage));
        visualGroup.getChildren().add(background);
    }

    /**
     * Инициализацияи массива с элементами игрового поля.
     */
    public void fieldInit() {
        visualField = new VBox(2);
//        visualField.setLayoutX(background.getLayoutX()+borderPadding);
//        visualField.setLayoutY(background.getLayoutY()+borderPadding);
        for (int i = 0; i < game.getSize(); i++) {
            HBox line = new HBox();
            visualField.getChildren().add(line);
            for (int j = 0; j < game.getSize(); j++) {
                GameObject fieldCell = new GameObject(' ');
                fieldCell.setFitWidth(background.getLayoutY()/10);
                fieldCell.setFitHeight(background.getLayoutY()/10);
                fieldCell.setXpos(j);
                fieldCell.setYpos(i);
                line.getChildren().add(fieldCell);
                game.gameField[i][j] = fieldCell;
                fieldCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (game.isHumanTurn && !game.isEndGame()) {
                            if (fieldCell.getSymbol() == ' ') {
                                fieldCell.setSymbol(game.human.getSymbol());
                                game.gameTurn(fieldCell.getXpos(), fieldCell.getYpos(), fieldCell.getSymbol());
                            }
                        }
                    }
                });
            }
        }
        background.setCenter(visualField);
        visualField.setAlignment(Pos.CENTER);
    }

    /**
     * Метод служит для отрисовки визуальной формы игрового поля.
     */
    public void brushTheField() {
        Group group = new Group(background);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();
    }

}
