package HomeWork_04;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MenuScreen {
    //Группировки
    private BorderPane borderPane;
    private ToggleGroup buttonsGroup = new ToggleGroup();

    //Элементы управления
    private RadioButton gameMode1 = new RadioButton("Human VS Human");
    private RadioButton gameMode2 = new RadioButton("Human VS AI");
    private Button startButton = new Button("START GAME");
    private Slider size;
    private Slider winLine;

    //Константы
    private final int MIN_SIZE = 3;
    private final int MAX_SIZE = 10;

    public MenuScreen(Game game, Stage stage) {
        Stage stageOptions = new Stage();
        stageOptions.setTitle("Game settings");
        startButtonInit(game, stage);
        gameModeButtonsInit();
        slidersInit();

        VBox vBox = new VBox(3, new Label("Choose game mode"), gameMode1, gameMode2, startButton,
                new Label("Set game field size"), size,
                new Label("Set winner line"), winLine);

        borderPane = new BorderPane(vBox);

        vBox.setPrefWidth(stage.getWidth()/2);
        vBox.setPrefHeight(stage.getHeight()/2);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(borderPane);
        stageOptions.setScene(scene);
        stageOptions.show();
    }

    private void slidersInit(){
        size = new Slider(MIN_SIZE, MAX_SIZE, MIN_SIZE);
        size.setShowTickLabels(true);
        size.setShowTickMarks(true);
        size.setBlockIncrement(1.0);
        size.setMinorTickCount(0);
        size.setMajorTickUnit(1);
        size.setSnapToTicks(true);
        size.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                winLine.setMax(size.getValue());
            }
        });
        winLine = new Slider(MIN_SIZE, size.getMax(), MIN_SIZE);
        winLine.setShowTickLabels(true);
        winLine.setShowTickMarks(true);
        winLine.setBlockIncrement(1.0);
        winLine.setMinorTickCount(0);
        winLine.setMajorTickUnit(1);
        winLine.setSnapToTicks(true);
    }

    private void startButtonInit(Game game, Stage stage){
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startGame(game, stage);
            }
        });
    }

    private void gameModeButtonsInit(){
        gameMode1.setToggleGroup(buttonsGroup);
        gameMode2.setToggleGroup(buttonsGroup);
        gameMode1.fire();
    }

    private void startGame(Game game, Stage stage){
        if (buttonsGroup.getSelectedToggle() == gameMode1) {
            new GameScreen(game, stage, GameMode.H_V_H);
        }else {
            new GameScreen(game, stage, GameMode.H_V_A);

        }
    }
}
