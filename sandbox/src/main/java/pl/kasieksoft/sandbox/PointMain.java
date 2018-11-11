package pl.kasieksoft.sandbox;

public class PointMain {

    public static void main(String[] args) {
        Point point1 = new Point(2.0, 3.0);
        Point point2 = new Point(4.0, 5.0);

        System.out.println("Distance between points equals " + distance(point1, point2));
        System.out.println("Distance between points equals " + point1.distance(point2));
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }
}
