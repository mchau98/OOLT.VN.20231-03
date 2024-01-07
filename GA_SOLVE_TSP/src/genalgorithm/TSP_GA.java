package genalgorithm;

import java.util.Scanner;

import map.City;
import map.Population;
import map.TourManager;

public class TSP_GA {

    public static void runGA(int numberOfCities, int populationSize, int generations) {
        // Tạo ngẫu nhiên các thành phố
        TourManager.clearCities(); // Xóa các thành phố cũ trước khi thêm mới
        for (int i = 0; i < numberOfCities; i++) {
            City city = new City();
            TourManager.addCity(city);
        }

        // Khởi tạo quần thể
        Population pop = new Population(populationSize, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Tiến hóa quần thể
        for (int i = 0; i < generations; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // In kết quả cuối cùng
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }

    public static void main(String[] args) {
        int numberOfCities = 0;
        int populationSize = 0;
        int generations = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of cities:");
        numberOfCities = scanner.nextInt();
        System.out.println("Enter population size:");
        populationSize = scanner.nextInt();
        System.out.println("Enter number of generations:");
        generations = scanner.nextInt();

        scanner.close();
        
        runGA(numberOfCities, populationSize, generations);
    }
}
