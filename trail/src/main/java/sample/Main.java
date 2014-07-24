package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    private Group root;
    private NavigableMap<Long, Circle> circleMap = new TreeMap<Long, Circle>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);

        addGradientBackground(root, scene);

        scene.addEventFilter(MouseEvent.MOUSE_MOVED, mouseMovementHandler);

        new Timer("StaleCircleRemover").scheduleAtFixedRate(timerHandler, 2000, 1000);

        primaryStage.show();
    }

    private void addGradientBackground(Group root, Scene scene) {
        @SuppressWarnings("SpellCheckingInspection") Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(),
                new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#f8bd55")),
                        new Stop(0.14, Color.web("#c0fe56")),
                        new Stop(0.28, Color.web("#5dfbc1")),
                        new Stop(0.43, Color.web("#64c2f8")),
                        new Stop(0.57, Color.web("#be4af7")),
                        new Stop(0.71, Color.web("#ed5fc2")),
                        new Stop(0.85, Color.web("#ef504c")),
                        new Stop(1, Color.web("#f2660f"))));
        colors.widthProperty().bind(scene.widthProperty());
        colors.heightProperty().bind(scene.heightProperty());
        root.getChildren().add(colors);
    }

    private TimerTask timerHandler = new TimerTask() {
        @Override
        public void run() {
            removeStaleCircle();
        }
    };

    private EventHandler<MouseEvent> mouseMovementHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            addCircle(event.getX(), event.getY());
        }
    };

    private Circle addCircle(double x, double y) {
        Circle circle = new Circle(10, Color.web("white", .3));
        circle.setCenterX(x);
        circle.setCenterY(y);
        root.getChildren().add(circle);
        addCircleInQueue(System.currentTimeMillis(), circle);
        return circle;
    }

    private void addCircleInQueue(long currentTime, Circle circle) {
        circleMap.put(currentTime, circle);
//        removeStaleCircle();
    }

    private synchronized void removeStaleCircle() {
        while (circleMap.size() > 1000) {
            Map.Entry<Long, Circle> firstEntry = circleMap.firstEntry();

            removeCircle(firstEntry.getValue());
            circleMap.remove(firstEntry.getKey());
        }

        Map<Long, Circle> olderCircleMap = circleMap.headMap(System.currentTimeMillis() - 2000);
        for (Map.Entry<Long, Circle> e: olderCircleMap.entrySet()) {
            removeCircle(e.getValue());
            circleMap.remove(e.getKey());
        }
    }

    private void removeCircle(final Circle circle) {
        Platform.runLater(new Runnable() {
            public void run() {
                root.getChildren().remove(circle);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
