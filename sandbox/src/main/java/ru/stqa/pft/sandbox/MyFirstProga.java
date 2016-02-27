package ru.stqa.pft.sandbox;

public class MyFirstProga {
   public static void main(String[] args) {
       System.out.println("Hello World!");

       Point p1 = new Point();
       p1.x = 2;
       p1.y = 6;
       Point p2 = new Point();
       p2.x = 5;
       p2.y = 2;
       System.out.println("Расстояние между двумя точками (" +
               p1.x + "," + p1.y + ") и (" + p2.x + "," + p2.y + ") = " + distance(p1, p2));

	}

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x-p1.x,2)+Math.pow(p2.y-p1.y,2));
    }
}