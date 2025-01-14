import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Train {
    private int trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private List<Ticket> bookedTickets;

    public Train(int trainNumber, String trainName, String source, String destination, int totalSeats) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.bookedTickets = new ArrayList<>();
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookTicket(Ticket ticket) {
        if (availableSeats > 0) {
            bookedTickets.add(ticket);
            availableSeats--;
        }
    }

    public void cancelTicket(Ticket ticket) {
        bookedTickets.remove(ticket);
        availableSeats++;
    }
}

class Passenger {
    private String name;
    private int age;
    private String gender;

    public Passenger(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}

class Ticket {
    private int ticketId;
    private Train train;
    private Passenger passenger;
    private String travelDate;

    public Ticket(int ticketId, Train train, Passenger passenger, String travelDate) {
        this.ticketId = ticketId;
        this.train = train;
        this.passenger = passenger;
        this.travelDate = travelDate;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Train getTrain() {
        return train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getTravelDate() {
        return travelDate;
    }
}

class ReservationSystem {
    private Map<Integer, Train> trains;
    private Map<Integer, Ticket> tickets;
    private int ticketCounter;

    public ReservationSystem() {
        this.trains = new HashMap<>();
        this.tickets = new HashMap<>();
        this.ticketCounter = 1;
    }

    public void addTrain(Train train) {
        trains.put(train.getTrainNumber(), train);
    }

    public Train getTrain(int trainNumber) {
        return trains.get(trainNumber);
    }

    public Ticket bookTicket(int trainNumber, Passenger passenger, String travelDate) {
        Train train = trains.get(trainNumber);
        if (train != null && train.getAvailableSeats() > 0) {
            Ticket ticket = new Ticket(ticketCounter++, train, passenger, travelDate);
            train.bookTicket(ticket);
            tickets.put(ticket.getTicketId(), ticket);
            return ticket;
        }
        return null;
    }

    public boolean cancelTicket(int ticketId) {
        Ticket ticket = tickets.get(ticketId);
        if (ticket != null) {
            Train train = ticket.getTrain();
            train.cancelTicket(ticket);
            tickets.remove(ticketId);
            return true;
        }
        return false;
    }

    public void showAvailableTrains() {
       
        for (Train train : trains.values()) {
            System.out.println("Train Number: " + train.getTrainNumber() +
                    ", Train Name: " + train.getTrainName() +
                    ", From: " + train.getSource() +
                    ", To: " + train.getDestination() +
                    ", Available Seats: " + train.getAvailableSeats());
        }
    }
}

public class trainreservation {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();

        Train train1 = new Train(101, "Express A", "CityX", "CityY", 100);
        Train train2 = new Train(102, "Express B", "CityY", "CityZ", 150);

        reservationSystem.addTrain(train1);
        reservationSystem.addTrain(train2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Show Available Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationSystem.showAvailableTrains();
                    break;
                case 2:
                    System.out.print("Enter Train Number: ");
                    int trainNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Passenger Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Passenger Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Passenger Gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter Travel Date (yyyy-mm-dd): ");
                    String travelDate = scanner.nextLine();

                    Passenger passenger = new Passenger(name, age, gender);
                    Ticket ticket = reservationSystem.bookTicket(trainNumber, passenger, travelDate);

                    if (ticket != null) {
                        System.out.println("Ticket booked successfully. Ticket ID: " + ticket.getTicketId());
                    } else {
                        System.out.println("Failed to book ticket. No available seats.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Ticket ID: ");
                    int ticketId = scanner.nextInt();
                    if (reservationSystem.cancelTicket(ticketId)) {
                        System.out.println("Ticket cancelled successfully.");
                    } else {
                        System.out.println("Failed to cancel ticket. Invalid Ticket ID.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}