class Lifts extends Thread{
    String LiftNo;
    boolean status=true; // "active" or "inactive"; true=active and false=inactive
    int liftCurrentFloor=0;
    int sleepTime; // time in seconds to wait before activating lift
    
    public void run(){
        status=false;
        try{ Thread.sleep(sleepTime*1000);} catch(Exception e){};
        System.out.println(LiftNo+" is now active.");
        status=true;
    }
}
