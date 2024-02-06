package main.Ressources.Java;

import javafx.scene.layout.StackPane;

public class Square extends StackPane {

    int x, y;
    boolean occupied;
    String name;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.occupied = false;
    }

    @Override
    public String toString() {
        return "Square";
    }

    public void setName(String name) {
        this.name = name;
    }
}
