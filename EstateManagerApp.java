import java.util.Scanner;

class AccommodationArea {
    protected String name;
    protected int occupants;
    protected boolean[] lights = new boolean[3]; // 3 lights

    public AccommodationArea(String name) {
        this.name = name;
        this.occupants = 0;
        for (int i = 0; i < lights.length; i++) {
            lights[i] = false;
        }
    }

    public void addOccupants(int n) {
        occupants += n;
    }

    public void removeOccupants(int n) {
        occupants = Math.max(0, occupants - n);
    }

    public void switchLightOn(int lightNumber) {
        if (lightNumber >= 1 && lightNumber <= 3) {
            lights[lightNumber - 1] = true;
        }
    }

    public void switchLightOff(int lightNumber) {
        if (lightNumber >= 1 && lightNumber <= 3) {
            lights[lightNumber - 1] = false;
        }
    }

    public void reportStatus() {
        System.out.println("\n--- Area Status Report ---");
        System.out.println("Area: " + name);
        System.out.println("Occupants: " + occupants);
        for (int i = 0; i < lights.length; i++) {
            System.out.println("Light " + (i + 1) + ": " + (lights[i] ? "ON" : "OFF"));
        }
        System.out.println("----------------------------\n");
    }
}

class GymArea extends AccommodationArea {
    public GymArea() {
        super("Gym Area");
    }
}

class SwimmingArea extends AccommodationArea {
    public SwimmingArea() {
        super("Swimming Area");
    }
}

public class EstateManagerApp {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            GymArea gym = new GymArea();
            SwimmingArea pool = new SwimmingArea();
            AccommodationArea activeArea = gym; // Default active area
            
            String choice = "";
            System.out.println("=== Speke Apartments Area Control ===");
            
            do {
                System.out.println("\nMenu:");
                System.out.println("S – Select active area (G = Gym, P = Swimming)");
                System.out.println("W – Add occupants");
                System.out.println("X – Remove occupants");
                System.out.println("Y – Switch ON light (1–3)");
                System.out.println("Z – Switch OFF light (1–3)");
                System.out.println("R – Report status");
                System.out.println("Q – Quit");
                System.out.print("Enter option: ");
                choice = input.nextLine().trim().toUpperCase();
                
                switch (choice) {
                    case "S" -> {
                        System.out.print("Select Area (G=Gym, P=Swimming): ");
                        String areaChoice = input.nextLine().trim().toUpperCase();
                    switch (areaChoice) {
                        case "G" -> {
                            activeArea = gym;
                            System.out.println("Gym Area selected.");
                        }
                        case "P" -> {
                            activeArea = pool;
                            System.out.println("Swimming Area selected.");
                        }
                        default -> System.out.println("Invalid selection. Please enter G or P.");
                    }
                    }
                        
                    case "W" -> {
                        int add = getValidInteger(input, "Enter number of occupants to add: ");
                        activeArea.addOccupants(add);
                        System.out.println(add + " occupants added to " + activeArea.name + ".");
                    }
                        
                    case "X" -> {
                        int remove = getValidInteger(input, "Enter number of occupants to remove: ");
                        activeArea.removeOccupants(remove);
                        System.out.println(remove + " occupants removed from " + activeArea.name + ".");
                    }
                        
                    case "Y" -> {
                        int onLight = getValidLightNumber(input, "Enter light number to switch ON (1–3): ");
                        activeArea.switchLightOn(onLight);
                        System.out.println("Light " + onLight + " switched ON in " + activeArea.name + ".");
                    }
                        
                    case "Z" -> {
                        int offLight = getValidLightNumber(input, "Enter light number to switch OFF (1–3): ");
                        activeArea.switchLightOff(offLight);
                        System.out.println("Light " + offLight + " switched OFF in " + activeArea.name + ".");
                    }
                        
                    case "R" -> activeArea.reportStatus();
                        
                    case "Q" -> System.out.println("Exiting program... Goodbye!");
                        
                    default -> System.out.println("Invalid option! Please try again.");
                }
            } while (!choice.equals("Q"));
        }
    }

    // Helper method for integer validation
    private static int getValidInteger(Scanner input, String prompt) {
        int n = -1;
        while (true) {
            System.out.print(prompt);
            try {
                n = Integer.parseInt(input.nextLine().trim());
                if (n >= 0) {
                    return n;
                } else {
                    System.out.println("Enter a positive integer!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
    }

    // Helper method for light number validation
    private static int getValidLightNumber(Scanner input, String prompt) {
        int n = -1;
        while (true) {
            System.out.print(prompt);
            try {
                n = Integer.parseInt(input.nextLine().trim());
                if (n >= 1 && n <= 3) {
                    return n;
                } else {
                    System.out.println("Light number must be between 1 and 3!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer between 1 and 3.");
            }
        }
    }
}
