
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Lift details are collected from the main method
    // activation method can activate a Lifts class.
    
    public static void activation(Lifts currentlift , int personCurrentFloor ,int PersonReachFloor , int liftCurrentFloor){
        int sleepTime= ( Math.abs(personCurrentFloor - liftCurrentFloor) + Math.abs(personCurrentFloor - PersonReachFloor) )*5 ;
        
        currentlift.liftCurrentFloor = PersonReachFloor; //update a current Floor of the Lift
        currentlift.sleepTime = sleepTime; // Calcualte How long it can to complete a task
         // Turn Lift into Sleep Mode 
        try
        {
            currentlift.start();
            
        } 
        catch(Exception e){};
        
        // Update a Details Description to the User
        System.out.println();
        System.out.println("_______________________________________________");
        

        System.out.println("-----> "+currentlift.LiftNo+" is assign to you");
        System.out.println(currentlift.LiftNo + " will reach your place in " + Math.abs(personCurrentFloor - liftCurrentFloor )*5+ " seconds");
        System.out.println(currentlift.LiftNo+" become active after "+ sleepTime+ " seconds");
        
        System.out.println("_______________________________________________");
        System.out.println();
    }

    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        ArrayList<Lifts> lifts = new ArrayList<Lifts>(); // Defaulty Create 3 Lifts
        Lifts l1 = new Lifts();
        Lifts l2 = new Lifts();
        Lifts l3 = new Lifts();

        lifts.add(l1);
        lifts.add(l2);
        lifts.add(l3);
        
        // Create Name for Each Lift like L-01 , L-02 , L-03
        int count=1;
        System.out.println("---------- AVAILABLE LIFTS ----------");
        for(int i=0 ; i < lifts.size() ; i++){
            String name="L-0"+count;
            lifts.get(i).LiftNo=name;
            System.out.println(lifts.get(i).LiftNo);
            count=count+1;
            
        }
        System.out.println("-----------------------------------");
        
    // While Condition Turns loop into infinity
    while(true){
        System.out.printf("Option 01: Use the Lift \nOption 02: Get Lift Details \n" );
        switch(sc.nextInt()){
        case 1:{ // Case 01 : get a details from Users and perform a Task
            
        System.out.printf("Enter a current floor number (0, 1, 2, or 3) : ");
        int personCurrentFloor=sc.nextInt();
        System.out.printf("Enter the floor you wish to reach : ");
        int PersonReachFloor=sc.nextInt();
        
        // check Users gives a Current information
        // Here Using 4 Floors ( 0 to 3 ) and 3 Lifts 
         if ( ( personCurrentFloor > 4 ) || ( personCurrentFloor < -1 ) || ( PersonReachFloor > 4 ) || ( PersonReachFloor < -1 ) ) {
            System.out.println(" --- Enter a Valid Floor Number ---");
            continue;
        }
        
        boolean available=false; // Check Lift is available or not
        int lesserNo=100; // find the nearest Lift
        Lifts currentlift=null; //present lift
        
        for(Lifts lift : lifts){
            if(lift.status==true && Math.abs(lift.liftCurrentFloor - personCurrentFloor) <= lesserNo)
            {   
                
                available=true;
                lesserNo=Math.abs(lift.liftCurrentFloor - personCurrentFloor); // find the nearest Lift
                currentlift=lift;  // get a Available Lift

            }
        }
     
        if(available==true){
            activation(currentlift , personCurrentFloor , PersonReachFloor , currentlift.liftCurrentFloor);
           
        }
        else    
            System.out.println("All Lifts are Busy Plase Try again later");
        
        // after complete a previously task values are changed into default
        
        lesserNo=100;   // after a loop it changed into default value 100 
        available=false;
        break;
        } //case 01
    
    case 2:{  // Case 02 Used to get Current Status of all the Lifts
        System.out.println();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("  Lift No         Status         Current_Floor        Reactive_Time");
        System.out.println("----------------------------------------------------------------------");
        for(Lifts lift : lifts){
            String status;
            int time;
            
            if(lift.status==true)
            {
                status="Available  ";
                time=0;
                
            }
            else
            {
                status="UnAvailable";
                time=lift.sleepTime;
            
            }     
            System.out.println("   "+lift.LiftNo+"         "+status+"            "+lift.liftCurrentFloor+"                    "+time);
        
        }
            
         System.out.println("----------------------------------------------------------------------");
         System.out.println();
        break;} //case 02
        } // switch 
        
    }//while loop
    } // main method
}

