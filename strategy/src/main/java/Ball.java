import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Timer;
import java.util.TimerTask;

public class Ball extends Circle {

    public Ball(double x, double y) {
        super(10, Color.web("white", 1));
        setCenterX(x);
        setCenterY(y);
    }

    Timer timer = new Timer(true);

    class MovementTask extends TimerTask {
        private double x;
        private double y;

        public MovementTask(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            Platform.runLater(() -> {
                setCenterX(x);
                setCenterY(y);
            });
        }
    }

    public void move(double x, double y) {
        timer.cancel();
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new MovementTask(x, y), 30, 30);
    }
}
