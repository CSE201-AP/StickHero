package com.example.stickhero.environment;

import com.example.stickhero.sprite.CanMove;
import com.example.stickhero.sprite.MovementAnimator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class BackgroundImage extends HBox implements Pannable {
    private final MovementAnimator movementAnimator;
    private final double depth;

    public BackgroundImage(Image image, double depth) {
        movementAnimator = new CanMove(this, 1/depth);
        this.depth = depth;

        ImageView tile1 = new ImageView(image);
        tile1.fitHeightProperty().bind(this.heightProperty());
        tile1.setPreserveRatio(true);
        setMargin(tile1, new Insets(0, -1, 0, 0));  // Fix for white strip

        ImageView tile2 = new ImageView(image);
        tile2.fitHeightProperty().bind(this.heightProperty());
        tile2.setPreserveRatio(true);


        getChildren().addAll(tile1, tile2);
    }

    @Override
    public void panHorizontal(double offset) {
        double imageWidth = getBoundsInLocal().getWidth() / 2;
        if (getTranslateX() + offset < -imageWidth) {
            double switchX = -imageWidth;
            double partialPanOffset = switchX - getTranslateX();
            EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setTranslateX(0);
                    movementAnimator.getAfterHandlers().remove(this);
                    panHorizontal(offset - partialPanOffset);
                }
            };
            movementAnimator.getAfterHandlers().add(handler);
            panHorizontal(partialPanOffset);
        } else {
            movementAnimator.moveBy(new Point2D(offset, 0));
        }
    }

    @Override
    public void panHorizontalRelative(double offset) {
        panHorizontal(offset/depth);
    }
}