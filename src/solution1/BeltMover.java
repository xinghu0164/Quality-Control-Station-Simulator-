package solution1;
/**
 * A belt-mover moves a belt along as often as possible, but only
 * when there is a bicycle on the belt not at the last position.
 */

public class BeltMover extends BicycleHandlingThread {

    // the belt to be handled
    protected Belt belt;
    protected boolean canMove = true;

    /**
     * Create a new BeltMover with a belt to move
     */
    public BeltMover(Belt belt) {
        super();
        this.belt = belt;
    }

    /**
     * Move the belt as often as possible, but only if there 
     * is a bicycle on the belt which is not in the last position.
     */
    public void run() {
        while (!isInterrupted()) {
            try {
            	
                // spend BELT_MOVE_TIME milliseconds moving the belt
                Thread.sleep(Params.BELT_MOVE_TIME);
                
               while(canMove == false){
            	   synchronized (belt){
            		   belt.wait();
            	   }
               }
                belt.move();
            } catch (OverloadException e) {
                terminate(e);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }

        System.out.println("BeltMover terminated");
    }
}
