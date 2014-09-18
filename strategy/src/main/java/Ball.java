import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Timer;
import java.util.TimerTask;

public class Ball extends Circle {

    private static final int WHOLE_TIME = 1000;
    private static final int STEP_TIME = 30;

    public Ball(double x, double y) {
        super(10, Color.web("white", 1));
        setCenterX(x);
        setCenterY(y);
    }

    Timer timer = new Timer(true);

    class MovementTask extends TimerTask {
        static final int STEPS = WHOLE_TIME / STEP_TIME + 1;
        double[] xSeries = new double[STEPS];
        double[] ySeries = new double[STEPS];
        int step = 0;

        public MovementTask(double x1, double y1, double x2, double y2) {
            double xDelta = (x2 - x1) / STEPS;
            double yDelta = (y2 - y1) / STEPS;
            for(int i = 0; i < xSeries.length; ++i) {
                xSeries[i] = x1 + xDelta * i;
                ySeries[i] = y1 + yDelta * i;
            }
            xSeries[xSeries.length - 1] = x2;
            ySeries[ySeries.length - 1] = y2;
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
