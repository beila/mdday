/**
* Created by hojin.ghim on 2014-09-18.
*/
class LinearInterpolator implements Interpolator {
    @Override
    public double[] getPoints(double start, double end, int steps) {
        double[] series = new double[steps];
        double xDelta = (end - start) / steps;
        for(int i = 0; i < series.length; ++i) {
            series[i] = start + xDelta * i;
        }
        series[series.length - 1] = end;
        return series;
    }
}
