package ru.stqa.pft.sandbox;

/**
 * Created by Hermit on 27.02.2016.
 */
public class DistPtP {

    public static void main(String[] args) {
        Point p1 = new Point(3, 5);
        Point p2 = new Point(6, 1);

        System.out.println("Расстояние между двумя точками (" +
                p1.x + "," + p1.y + ") и (" + p2.x + "," + p2.y + ") = " + p2.distance(p1));

    }
    /*public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x-p1.x,2)+Math.pow(p2.y-p1.y,2));
    } */
}
