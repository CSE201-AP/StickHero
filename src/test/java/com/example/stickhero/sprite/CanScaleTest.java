package com.example.stickhero.sprite;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class CanScaleTest extends ApplicationTest {

    private Rectangle rectangle;
    private ScaleAnimator scaleAnimator;
    private static final double SPEEDMS = 100;

    @Override
    public void start(Stage stage) {
        rectangle = new Rectangle(0, 0, 1, 1);
        scaleAnimator = new CanScale(rectangle, SPEEDMS);
        stage.setScene(new Scene(new StackPane(rectangle), 100, 100));
//        stage.show();
    }

    @BeforeEach
    public void setUp() {
        rectangle.setScaleX(1);
        rectangle.setScaleY(1);
    }

    @Test
    public void testScaleTo() {
        scaleAnimator.scaleTo(20, 20);
        sleep(500);
        assertEquals(new Point2D(20, 20), new Point2D(rectangle.getScaleX(), rectangle.getScaleY()));
    }

    @Test
    public void testScaleBy() {
        scaleAnimator.scaleBy(20, 20);
        sleep(500);
        assertEquals(new Point2D(21, 21), new Point2D(rectangle.getScaleX(), rectangle.getScaleY()));
    }

//    @Test
//    public void testMoveBy() {
//        rectangle.relocate(0, 0);
//        scaleAnimator.moveBy(new Point2D(50, 0));
//        sleep(500);
//        assertEquals(new Point2D(50, 0), new Point2D(rectangle.getTranslateX(), rectangle.getTranslateY()));
//    }
}