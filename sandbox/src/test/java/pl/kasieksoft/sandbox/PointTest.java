package pl.kasieksoft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void shouldCalculateDistance(){
        Point point1 = new Point(1.0, 2.0);
        Point point2 = new Point(4.0, 6.0);

        Assert.assertEquals(point1.distance(point2),5.0);
    }

    @Test
    public void shouldCalculateDistanceFromNegativeCoordinates(){
        Point point1 = new Point(0.0, -1.0);
        Point point2 = new Point(-3.0, 3.0);

        Assert.assertEquals(point1.distance(point2),5.0);
    }

    @Test
    public void shouldCalculateDistanceForASinglePoint(){
        Point point = new Point(1.0, 2.0);

        Assert.assertEquals(point.distance(point),0.0);
    }
}
