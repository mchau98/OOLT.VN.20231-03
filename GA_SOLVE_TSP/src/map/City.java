package map;

import java.util.Random;

public class City {

    private int x;
    private int y;

    // Constructs a randomly placed city
    public City() {
        Random rand = new Random();

        int rand_int1 = rand.nextInt(1, 500);
        int rand_int2 = rand.nextInt(1, 500);
        setX(rand_int1);
        setY(rand_int2);
    }
    // Gets city's x coordinate
    public int getX() {
        return this.x;
    }

    // Gets city's y coordinate
    public int getY() {
        return this.y;
    }

    // Sets city's x coordinate
    private void setX(int x) {
        this.x = x;
    }

    // Sets city's y coordinate
    private void setY(int y) {
        this.y = y;
    }

    // Gets the distance to given city
    public double distanceTo(City city) {
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));

        return distance;
    }
}
