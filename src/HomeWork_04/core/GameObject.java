package HomeWork_04.core;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameObject extends ImageView {
    private char symbol;
    private int x;
    private int y;

    public int getXpos() {
        return x;
    }

    public int getYpos() {
        return y;
    }

    public void setXpos(int x) {
        this.x = x;
    }

    public void setYpos(int y) {
        this.y = y;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        if (this.symbol == ' ') {
            this.symbol = symbol;
            setImage(symbol);
        }
    }

    public GameObject(char symbol) {
        this.symbol = symbol;
        setImage(symbol);
    }

    private void setImage(char symbol){
        switch (symbol) {
            case 'X':
                this.setImage(new Image("HomeWork_04/resources/cross.png"));
                break;
            case 'O':
                this.setImage(new Image("HomeWork_04/resources/circle.png"));
                break;
            case ' ':
                this.setImage(new Image("HomeWork_04/resources/space.png"));
                break;
        }
    }
}
