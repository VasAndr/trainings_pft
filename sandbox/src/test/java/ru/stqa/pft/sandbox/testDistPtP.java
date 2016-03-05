package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Hermit on 05.03.2016.
 */
public class testDistPtP {
    @Test
    public void tests() {
        Point p1 = new Point(3, 5);
        Point p2 = new Point(6, 1);

        Assert.assertEquals(p2.distance(p1), p1.distance(p2));
        Assert.assertEquals(p1.distance(p2), 5.0);
        Assert.assertEquals(p2.distance(p1), 5.0);

    }
}
