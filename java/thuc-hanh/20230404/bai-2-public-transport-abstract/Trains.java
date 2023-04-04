public class Trains extends Vehicles {
    static final double speedTrain = 40;

    public Trains() {
    }

    @Override
    public double getTime() {
        return (super.distance / speedTrain);
    }
}
