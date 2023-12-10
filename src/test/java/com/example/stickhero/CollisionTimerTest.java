package com.example.stickhero;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import java.util.HashMap;

public class CollisionTimerTest extends ApplicationTest {

    private Rectangle rectangle1;
    private boolean colliding = false;

    @Override
    public void start(Stage stage) {
        rectangle1 = new Rectangle(0, 0, 10, 10);
        Rectangle rectangle2 = new Rectangle(50, 0, 10, 10);
        CollisionTimer collisionTimer = new CollisionTimer(rectangle1, new HashMap<>());
        collisionTimer.getCallbacks().put(rectangle2, () -> {
            colliding = true;
        });
        collisionTimer.getCallbacks().put(rectangle1, () -> {
            colliding = false;
        });
        collisionTimer.start();
        stage.setScene(new Scene(new StackPane(rectangle1, rectangle2), 100, 100));
//        stage.show();
    }

    @Test
    public void testCollidesOnCompleteOverlap() {
        rectangle1.relocate(50, 0);
        sleep(100);
        Assertions.assertThat(colliding).isTrue();
    }

    @Test
    public void testCollidesOnPartialOverlap() {
        rectangle1.relocate(48, 8);
        sleep(100);
        Assertions.assertThat(colliding).isTrue();
    }

    @Test
    public void testDoesNotCollideOnNoOverlap() {
        rectangle1.relocate(100, 0);
        sleep(100);
        Assertions.assertThat(colliding).isFalse();
    }

    @Test
    public void testCollidesOnEdgeOverlap() {
        rectangle1.relocate(50, 10);
        sleep(100);
        Assertions.assertThat(colliding).isTrue();
    }
}