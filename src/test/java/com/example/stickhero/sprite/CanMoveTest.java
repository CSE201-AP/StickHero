package com.example.stickhero.sprite;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import java.util.HashMap;
import java.util.Map;

public class CanMoveTest extends ApplicationTest {

    private Rectangle rectangle;
    private MovementAnimator movementAnimator;
    private static final double SPEEDMS = 100;

    @Override
    public void start(Stage stage) {
        rectangle = new Rectangle(0, 0, 10, 10);
        movementAnimator = new CanMove(rectangle, SPEEDMS);
        stage.setScene(new Scene(new StackPane(rectangle), 100, 100));
//        stage.show();
    }

    @BeforeEach
    public void setUp() {
        rectangle.relocate(0, 0);
    }

    @Test
    public void testMoveTo() {
        movementAnimator.moveTo(new Point2D(50, 0));
        sleep(500);
        assertEquals(new Point2D(50, 0), new Point2D(rectangle.getTranslateX(), rectangle.getTranslateY()));
    }

    @Test
    public void testMoveBy() {
        movementAnimator.moveBy(new Point2D(50, 0));
        sleep(500);
        assertEquals(new Point2D(50, 0), new Point2D(rectangle.getTranslateX(), rectangle.getTranslateY()));
    }
}