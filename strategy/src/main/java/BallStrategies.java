import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BallStrategies extends Application {

    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;
    private static final int NUM_BALLS = 4;
    private Group root;
    private EventHandler<? super MouseEvent> pressHandler = new EventHandler<MouseEvent>() {
        private int order = 0;

        @Override
        public void handle(MouseEvent event) {
            ((Ball) root.getChildren().stream()
                    .filter(c -> c instanceof Ball)
                    .skip(order = (order + 1 ) % NUM_BALLS)
                    .findAny().get()).move(Math.random() * WIDTH, Math.random() * HEIGHT);
        }
    };

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);

        addGradientBackground(root, scene);

        addBalls();

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, pressHandler);
        primaryStage.show();
    }

    private void addBalls() {
        Color color[] = new Color[] {
                Color.web("white", 1),
                Color.web("orange", 1),
                Color.web("red", 1),
                Color.web("black", 1),
        };
        for (int i = 0; i < NUM_BALLS; ++i) {
            root.getChildren().add(
                    new Ball(color[i], Math.random() * WIDTH, Math.random() * HEIGHT, new LinearInterpolator()));
        }
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

}
