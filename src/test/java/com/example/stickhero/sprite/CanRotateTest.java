package com.example.stickhero.sprite;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class CanRotateTest extends ApplicationTest {

    private Rectangle rectangle;
    private RotationAnimator rotateAnimator;
    private static final double SPEEDMS = 100;

    @Override
    public void start(Stage stage) {
        rectangle = new Rectangle(0, 0, 1, 1);
        rotateAnimator = new CanRotate(rectangle, SPEEDMS);
        stage.setScene(new Scene(new StackPane(rectangle), 100, 100));
//        stage.show();
    }

    @BeforeEach
    public void setUp() {
        rectangle.setRotate(0);
    }

    @Test
    public void testRotateToSimple() {
        rotateAnimator.rotateTo(90);
        sleep(500);
        assertEquals(90, rectangle.getRotate(), 1e-6);
    }

    @Test
    public void testRotateBySimple() {
        rotateAnimator.rotateBy(90);
        sleep(500);
        assertEquals(90, rectangle.getRotate(), 1e-6);
    }
}