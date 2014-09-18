import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    public Ball(double x, double y) {
        super(10, Color.web("white", 1));
        setCenterX(x);
        setCenterY(y);
    }

    Task<Void> movementTask = new MovementTask(0, 0);

    class MovementTask extends Task<Void> {
        private final double x;
        private final double y;

        public MovementTask(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            setCenterX(x);
            setCenterY(y);
        }

        @Override
        protected Void call() throws Exception {
            Thread.sleep(1000);
            return null;
        }
    }

    public void move(double x, double y) {
        movementTask.cancel();
        movementTask = new MovementTask(x, y);
        Thread t = new Thread(movementTask);
        t.setDaemon(true);
        t.start();
    }
}
