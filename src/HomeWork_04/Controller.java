package HomeWork_04;

import HomeWork_04.core.GameObject;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public AnchorPane background;
    public VBox visualField;


    /**
     * Инициализации контроллера декларативным методом.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundInit();
    }

    /**
     * Инициализация заднего фона.
     */
    private void backgroundInit() {
        background.setPrefWidth(800);
        background.setPrefHeight(600);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("HomeWork_04/resources/background.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        background.setBackground(new Background(backgroundImage));
    }

    /**
     * Инициализацияи массива с элементами игрового поля.
     */
    public void fieldInit(Game game) {
        visualField = new VBox(2);
        visualField.setLayoutX(170);
        visualField.setLayoutY(170);
        for (int i = 0; i < game.getSize(); i++) {
            HBox line = new HBox();
            visualField.getChildren().add(line);
            for (int j = 0; j < game.getSize(); j++) {
                GameObject fieldCell = new GameObject(' ');
                fieldCell.setFitWidth(60);
                fieldCell.setFitHeight(60);
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
    }

    /**
     * Метод служит для отрисовки визуальной формы игрового поля.
     */
    public void brushTheField(Stage stage, Parent sceneBuilder) {
        Group group = new Group(sceneBuilder, visualField);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();
    }
}
