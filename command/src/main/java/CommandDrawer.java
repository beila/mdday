import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;

public class CommandDrawer extends Application {
    private Group canvas;
    private Deque<List<Node>> history = new ArrayDeque<>();
    private Deque<List<Node>> forwardHistory = new ArrayDeque<>();
    private Command nextCommand;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);

        Rectangle background = addGradientBackground(root, scene);
        background.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> doCommand(e.getX(), e.getY()));

        setupLayout(root);

        canvas = new Group();
        root.getChildren().add(canvas);

        primaryStage.show();
    }

    private void setupLayout(Group root) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        root.getChildren().add(grid);

        addCommandButtons(grid, 0, 0);
        addStateButtons(grid, 1, 0);
    }

    private void addStateButtons(GridPane grid, int columnIndex, int rowIndex) {
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.TOP_RIGHT);
        grid.add(hbBtn, columnIndex, rowIndex);

        Button btn = new Button("Undo");
        btn.setOnAction(event -> undoCommand());
        hbBtn.getChildren().add(btn);

        btn = new Button("Redo");
        btn.setOnAction(event -> redoCommand());
        hbBtn.getChildren().add(btn);
    }

    private synchronized void redoCommand() {
        List<Node> c = forwardHistory.pollLast();
        if (null == c) {
            return;
        }
        history.add(new ArrayList<>(canvas.getChildren()));
        canvas.getChildren().clear();
        canvas.getChildren().addAll(c);
    }

    private synchronized void undoCommand() {
        List<Node> c = history.removeLast();
        if (null == c) {
            return;
        }
        forwardHistory.add(new ArrayList<>(canvas.getChildren()));
        canvas.getChildren().clear();
        canvas.getChildren().addAll(c);
    }

    private synchronized void doCommand(double x, double y) {
        if (null == nextCommand) {
            return;
        }

        history.add(new ArrayList<>(canvas.getChildren()));
        forwardHistory.clear();

        canvas.getChildren().add(nextCommand.getNode(x, y));
    }

    private void addCommandButtons(GridPane grid, int columnIndex, int rowIndex) {
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.TOP_LEFT);
        grid.add(hbBtn, columnIndex, rowIndex);

        Button btn = new Button("White Circle");
        btn.setOnAction(event -> nextCommand = new CircleCommand());
        hbBtn.getChildren().add(btn);

        btn = new Button("Black Circle");
        btn.setOnAction(event -> nextCommand = new BlackCircleCommand());
        hbBtn.getChildren().add(btn);
    }

    private Rectangle addGradientBackground(Group root, Scene scene) {
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
        return colors;
    }
}
