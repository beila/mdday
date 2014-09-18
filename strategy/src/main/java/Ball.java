import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Timer;
import java.util.TimerTask;

public class Ball extends Circle {

    private static final int WHOLE_TIME = 1000;
    private static final int STEP_TIME = 30;
    private final Interpolator interpolator;

    public Ball(Color color, double x, double y, Interpolator interpolator) {
        super(10, color);
        setCenterX(x);
        setCenterY(y);
        this.interpolator = interpolator;
    }

    Timer timer = new Timer(true);

    class MovementTask extends TimerTask {
        static final int STEPS = WHOLE_TIME / STEP_TIME + 1;
        double[] xSeries;
        double[] ySeries;
        int step = 0;

        public MovementTask(double x1, double y1, double x2, double y2) {
            xSeries = interpolator.getPoints(x1, x2, STEPS);
            ySeries = interpolator.getPoints(y1, y2, STEPS);
        }

        @Override
        public void run() {
            if (xSeries.length <= ++step) {
                cancel();
                return;
            }
            Platform.runLater(() -> {
                setCenterX(xSeries[step]);
                setCenterY(ySeries[step]);
            });
        }
    }

    public void move(double x, double y) {
        timer.cancel();
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new MovementTask(getCenterX(), getCenterY(), x, y), 0, STEP_TIME);
    }

}
