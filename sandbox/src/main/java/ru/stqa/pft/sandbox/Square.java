package ru.stqa.pft.sandbox;

/**
 * Created by Hermit on 05.03.2016.
 */
public class Square {

    public double l;

    public Square(double l) {
        this.l = l;
    }

    public double area() {
        return this.l * this.l;
    }
}
