
package wormholetimetrave;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class StarSystem {
    int id;
    List<Wormhole> wormholes;

    public StarSystem(int id) {
        this.id = id;
        this.wormholes = new ArrayList<>();
    }
}

class Wormhole {
    int source;
    int destination;
    int timeDifference;

    public Wormhole(int source, int destination, int timeDifference) {
        this.source = source;
        this.destination = destination;
        this.timeDifference = timeDifference;
    }
}

public class WormholeTimeTrave {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < cases; i++) {
            int starSystems = scanner.nextInt();
            int wormholes = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            List<StarSystem> systemList = new ArrayList<>();
            for (int j = 0; j < starSystems; j++) {
                systemList.add(new StarSystem(j));
            }

            for (int j = 0; j < wormholes; j++) {
                int source = scanner.nextInt();
                int destination = scanner.nextInt();
                int timeDifference = scanner.nextInt();
                scanner.nextLine(); 

                systemList.get(source).wormholes.add(new Wormhole(source, destination, timeDifference));
            }

            boolean isPossible = checkTimeTravelPossibility(systemList);
            if (isPossible) {
                System.out.println("possible");
            } else {
                System.out.println("not possible");
            }
        }
    }

    private static boolean checkTimeTravelPossibility(List<StarSystem> systemList) {
        int starSystems = systemList.size();

       
        for (int i = 0; i < starSystems; i++) {
            int[] distances = new int[starSystems];
            for (int j = 0; j < starSystems; j++) {
                distances[j] = Integer.MAX_VALUE;
            }

            distances[i] = 0;

     
            for (int j = 0; j < starSystems - 1; j++) {
                for (StarSystem system : systemList) {
                    for (Wormhole wormhole : system.wormholes) {
                        int source = wormhole.source;
                        int destination = wormhole.destination;
                        int timeDifference = wormhole.timeDifference;

                        if (distances[source] != Integer.MAX_VALUE && distances[source] + timeDifference < distances[destination]) {
                            distances[destination] = distances[source] + timeDifference;
                        }
                    }
                }
            }

    
            for (StarSystem system : systemList) {
                for (Wormhole wormhole : system.wormholes) {
                    int source = wormhole.source;
                    int destination = wormhole.destination;
                    int timeDifference = wormhole.timeDifference;

                    if (distances[source] != Integer.MAX_VALUE && distances[source] + timeDifference < distances[destination]) {
                        return true; 
                    }
                }
            }
        }

        return false; 
    }
}
