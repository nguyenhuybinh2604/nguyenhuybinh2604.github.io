public class Airplanes extends Vehicles {
    static final double speedAirplane = 80;

    public Airplanes() {
    }

    @Override
    public double getTime() {
        return (super.distance / speedAirplane);
    }
}
