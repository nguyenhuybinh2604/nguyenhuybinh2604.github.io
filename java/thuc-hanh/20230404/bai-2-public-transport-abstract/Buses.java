public class Buses extends Vehicles {
    static final double speedBus = 20;

    public Buses() {
    }

    @Override
    public double getTime() {
        return (super.distance / speedBus);
    }

}
