package tsp;

import java.util.Random;

public class City {
    int x;
    int y;

    // Constructs a randomly placed city
    public City() {
        Random rand = new Random();

        int rand_int1 = rand.nextInt(1, 500);
        int rand_int2 = rand.nextInt(1, 500);
        this.x = rand_int1;
        this.y = rand_int2;
    }

    // Constructs a city at chosen x, y location
    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Gets city's x coordinate
    public int getX() {
        return this.x;
    }

    // Gets city's y coordinate
    public int getY() {
        return this.y;
    }

    // Gets the distance to given city
    public double distanceTo(City city) {
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));

        return distance;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }
}