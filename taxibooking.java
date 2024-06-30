import java.util.*;
import java.lang.*;
class Taxi implements Cloneable{
private  int taxiid=0;
private char currentlocation='A';
private int pickuptime;
private int customerid;
private int droptime;
private char pickuplocation;
private char droplocation;
private int earnings;
@Override
public  Object clone() throws CloneNotSupportedException{ 
    return super.clone(); 
}
public int gettaxiid(){
    return taxiid;
}
public char getcurrentlocation(){
    return currentlocation;
}

public int getpickuptime(){
return pickuptime;
}

public int getdroptime(){
    return droptime;
}
public int getearnings(){
    return earnings;
}
public int getcustomerid(){
    return customerid;

}
public char getpickuplocation(){
return pickuplocation;
}

public char getdroplocation(){
    return droplocation;
}


public void settaxiid(int taxiid) {
    this.taxiid = taxiid;
}

public void setcurrentlocation(char curentlocation) {
    this.currentlocation = curentlocation;
}

public void setpickuptime(int pickuptime) {
    this.pickuptime = pickuptime;
}

public void setdroptime(int droptime) {
    this.droptime = droptime;
}

public void setpickuplocation(char pickuplocation) {
    this.pickuplocation = pickuplocation;
}

public void setdroplocation(char droplocation) {
    this.droplocation = droplocation;
}

public void setearnings(int earnings) {
    this.earnings = earnings;
}

public void setcustomerid(int customerid) {
    this.customerid = customerid;
}

}

public class taxibooking {


    ArrayList<Taxi> taxies=new ArrayList<>();
    ArrayList<Taxi> bookedhistory=new ArrayList<>();
int countercustomer=1;
int taxilimitsize=4;
int taxicount=0;
public void Showdetails(){
    System.out.println("----------------------------------------------------------------------------------------");
    for(Taxi t:bookedhistory){
        System.out.println("Taxi ID is :"+t.gettaxiid());
        System.out.println("Customer ID is :"+t.getcustomerid());
        System.out.println("Pickup location is :"+t.getpickuplocation());
        System.out.println("Drop location is :"+t.getdroplocation());
        System.out.println("Current location:"+t.getcurrentlocation());
        System.out.println("Pickup time :"+t.getpickuptime());
        System.out.println("Drop time :"+t.getdroptime());
        System.out.println("Earnings :"+t.getearnings());
        System.out.println("----------------------------------------------------------------------------------------");

    }
}
    public String booking(int pickuptime,char pickuplocation,char dropuplocation) throws CloneNotSupportedException{
          if(taxicount<taxilimitsize){
            taxies.add(new Taxi());
            taxicount++;
          }
  Taxi taxiready=null;
  int min=Integer.MAX_VALUE;
          for(Taxi t:taxies){
         if(t.getdroptime()<=pickuptime&&Math.abs(pickuplocation-dropuplocation)<=min){
            if(Math.abs(pickuplocation-dropuplocation)==min){
                if(t.getearnings()<taxiready.getearnings())
                taxiready=t;
            }
            else{
                taxiready=t;
                min=Math.abs(pickuplocation-dropuplocation);
            }
            
         }
          }
          if(taxiready!=null){
            taxiready.setcustomerid(countercustomer++);
            taxiready.setcurrentlocation(dropuplocation);
            taxiready.setpickuplocation(pickuplocation);
            taxiready.setdroplocation(dropuplocation);
            taxiready.setearnings(Math.abs(pickuplocation-dropuplocation)*200);
            taxiready.setpickuptime(pickuptime);
            taxiready.setdroptime(pickuptime+Math.abs(pickuplocation-dropuplocation));
            taxiready.settaxiid(taxies.indexOf(taxiready)+1);
            bookedhistory.add((Taxi)taxiready.clone());
          }

          if(taxiready!=null){
            return "taxi  "+taxiready.gettaxiid()+"  is booked";
          }
          else{
            return "taxi not available ";
          }
    }
    //the question there are generally five points are present A B C D E
    //Each point has the distance of 15 km between them and it takes 60 min to travel
    //if the taxi is available free at point A then we should book that taxi
    // if the taxi not free at A then we need to find the nearest point where taxi is available
    //for firs 5 km they charge 100 and for next 10 km they charge 10 rupees per km

public static void main(String[] args) throws CloneNotSupportedException{
Scanner sc=new Scanner(System.in);
taxibooking t=new taxibooking();
while(true){
    System.out.println("BOOK A TAXI IN THIS USER FRIENDLY INTERFACE ");
    System.out.println("1.Book a taxi");
    System.out.println("2.Show the details of taxi");
    System.out.println("3.Exit");
    int ch=sc.nextInt();
    switch(ch){
        case 1:
        System.out.print("Give the pickup time :  ");
        int time=sc.nextInt();
        System.out.println();
        System.out.print("Enter the pickup location:  ");
        char pickup=sc.next().charAt(0);
        System.out.println();
        System.out.print("Enter the drop location:  ");
        char dropup=sc.next().charAt(0);
        System.out.println();
        System.out.println(t.booking(time, pickup, dropup));
        break;
        case 2:
        System.out.println("DETAILS ARE:       ");
        t.Showdetails();
        break;
        case 3:
        return;

        default:
        System.out.println("unwanted option ");
        break;
    }

}

}    
}
