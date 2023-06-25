
package airport;

import java.util.*;

class Location {
    int id;
    boolean hasAirport;
    List<Road> roads;

    public Location(int id) {
        this.id = id;
        this.hasAirport = false;
        this.roads = new ArrayList<>();
    }
}

class Road {
    int cost;
    Location from;
    Location to;

    public Road(int cost, Location from, Location to) {
        this.cost = cost;
        this.from = from;
        this.to = to;
    }
}

class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int t = 1; t <= T; t++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int A = scanner.nextInt();

            Map<Integer, Location> locations = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                locations.put(i, new Location(i));
            }

            for (int i = 0; i < M; i++) {
                int X = scanner.nextInt();
                int Y = scanner.nextInt();
                int C = scanner.nextInt();

                Location from = locations.get(X);
                Location to = locations.get(Y);
                from.roads.add(new Road(C, from, to));
                to.roads.add(new Road(C, to, from));
            }

            int minCost = findMinimumCost(locations, A);
            int numAirports = countAirports(locations);

            System.out.println("Case #" + t + ": " + minCost + " " + numAirports);
        }

        scanner.close();
    }

    private static int findMinimumCost(Map<Integer, Location> locations, int airportCost) {
        int minCost = 0;
        for (Location location : locations.values()) {
            if (!location.hasAirport) {
                minCost += airportCost;
                dfs(location);
            }
        }
        return minCost;
    }

    private static void dfs(Location location) {
        location.hasAirport = true;
        for (Road road : location.roads) {
            if (!road.to.hasAirport) {
                dfs(road.to);
            }
        }
    }

    private static int countAirports(Map<Integer, Location> locations) {
        int numAirports = 0;
        for (Location location : locations.values()) {
            if (location.hasAirport) {
                numAirports++;
            }
        }
        return numAirports;
    }
}
