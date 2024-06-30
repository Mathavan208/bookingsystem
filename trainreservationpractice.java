import java.util.*;
class Train{
    private int trainno;
    private String trainname;
    private String source;
    private String destination;
  private List<Ticket> bookedticket;

    private int totalseats;
    private int availableseats;


    Train(int trainno,String trainname,String source,String destination,int totalseats,int availableseats){
      this.trainno=trainno;
      this.trainname=trainname;
      this.source=source;
      this.destination=destination;
      this.totalseats=totalseats;
      this.availableseats=availableseats;
      this.bookedticket=new ArrayList<>();
    }
    public int gettrainno(){
        return trainno;
    }
    public String gettrainname(){
        return trainname;
    }
    public String getsource(){
        return source;
    }
    public String getdestination(){
        return destination;
    }
    public int getAvailableSeats(){
        return availableseats;
    }
    public int getTotalSeats(){
        return totalseats;
    }
    public int bookticket(Ticket ticket){
        if(availableseats>0){
            bookedticket.add(ticket);
            availableseats--;
            return 1;
        }
        return 0;
    }
    public int cancelticket(int ticketno){
        if(availableseats>0){
        bookedticket.remove(ticketno);
        availableseats++;
        return 1;
        }
        return 0;
    }
}
class Ticket{
private int ticketno;
private String destination;
private Train train;
private Passenger passenger;
private String date;
Ticket(int ticketno,String destination,Train train,Passenger passenger,String date){
    this.ticketno=ticketno;
    this.destination=destination;
    this.train=train;
    this.passenger=passenger;
    this.date=date;
}

public int getticketid(){

    return ticketno;
}
public String date(){
    return date;
}
public String getdestination(){
    return destination;
}
public Train getTrain(){
    return train;
}
public Passenger passenger(){
    return passenger;
}
}

class Passenger{
    private String passengername;
    private int age;
    private String gender;
    Passenger(String passengername,int age,String gender){
        this.passengername=passengername;
        this.age=age;
        this.gender=gender;
    }

    public String getname(){
        return passengername;
    }

    public int getage(){
return age;
    }
    public String getgender(){
return gender;
    }
}
class Reservationsystem{
    Map<Integer,Train> trains;
    Map<Integer,Ticket> tickets;
    private int ticketcounter=1;
    Reservationsystem(){
        trains=new HashMap<>();
        tickets=new HashMap<>();
    }
    public void addTrain(Train train){
    trains.put(train.gettrainno(),train);
    }
 public void showtrains(){
    for(int i:trains.keySet()){
      Train train=trains.get(i);
      System.out.println("Train name :"+train.gettrainname());
      System.out.println("Train number: "+train.gettrainno());
      System.out.println("Source of train: "+train.getsource());
      System.out.println("Destination of train: "+train.getdestination());
      System.out.println("Available seats :"+train.getAvailableSeats());

      System.out.println("--------------------------------------------------");
    }
 }
    public boolean bookticket(int trainno,Passenger passenger,String destination,String date){
        Train train=trains.get(trainno);
       Ticket ticket=new Ticket(ticketcounter++,destination,train,passenger,date);
      tickets.put(ticket.getticketid(),ticket);
        int flag=train.bookticket(ticket);
          if(flag==1){
            
            return true;
          }
          else {
            return false;
          }

    }
    public boolean cancelticket(int ticketno){
        Ticket ticket=tickets.get(ticketno);
        Train train=ticket.getTrain();
        int flag=0;
        if(ticket!=null){
            flag=train.cancelticket(ticketno);
            tickets.remove(ticketno);
        }
        if(flag==1){
            return true;
        }
        else{
            return false;
        }
    }


}
public class trainreservationpractice {
    public static void main(String[] args){
        Reservationsystem res=new Reservationsystem();
        Train train1=new Train(12107,"vaigai express","chennai-tambaram","dindigal",500,200);
        Train train2=new Train(12111,"kaveri express","chennai-tambaram","birur",300,200);

        res.addTrain(train1);
        res.addTrain(train2);

        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("IRCTC How can I help us ");
            System.out.println("1.View the trains that are present");
            System.out.println("2.Book the ticket");
            System.out.println("3.Cancel the ticket");
            System.out.println("4.exiting the system");
            int scan=sc.nextInt();

            switch(scan){
             case 1:
             res.showtrains();
             break;
             case 2:
             System.out.print("Enter the traino");
             int trainno=sc.nextInt();
             System.out.println();
            System.out.println("Enter the passenger details");
            System.out.print("Passenger name: ");
            String name=sc.next();
            System.out.println();
            System.out.print("Passenger age: ");
            int age=sc.nextInt();
            System.out.println();
            System.out.print("Passenger gender: (Male/female/other): ");
            String gender=sc.next();
            System.out.println();
            System.out.print("Enter the date: ");
            String date=sc.next();
             Passenger pass=new Passenger(name, age, gender);
            boolean b= res.bookticket(trainno,pass,"madurai",date);
            if(b){
                System.out.println("ticket booked");
            }
            else{
                System.out.println("not availble seats");
            }
            break;
            case 4:
            System.out.println("exiting the system");
            return;
            default:
            System.out.println("unwanted symbol");
            break;

            }
        }
    }
}
