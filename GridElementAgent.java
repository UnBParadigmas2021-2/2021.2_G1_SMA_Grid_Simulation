import jade.core.*;
import jade.lang.acl.*;
import jade.core.behaviours.*;

interface GridElement{
    boolean isAlive();
    void canBeAlive();
}

class NotifyGuiBehaviour extends Behaviour
{
    static final int SLEEP_SECS = 1;
    private enum AgentState {DEAD, ALIVE};
    private AgentState state;

    public NotifyGuiBehaviour()
    {
        state = AgentState.DEAD;
    }

    public void action(){ 
        while(true){


            ACLMessage msg = myAgent.receive();

            switch(state){
                case DEAD:
                    // verify if is a propose to be alive
                    if(msg != null && msg.getPerformative() == ACLMessage.PROPOSE){
                        String content = msg.getContent();

                        if(content == "BE ALIVE")
                            state = AgentState.ALIVE;
                    }
                case ALIVE:

                    // respond query if this is alive
                    if(msg != null && msg.getPerformative() == ACLMessage.QUERY_IF)
                    {
                        ACLMessage replyMessage = msg.createReply();

                        replyMessage.setContent((state == AgentState.ALIVE)? "ALIVE" : "DEAD");

                        myAgent.send(replyMessage);

                    }

                break;
            }

            System.out.println("Notifying gui about new state");

            // Update my state on GUI
            msg = new ACLMessage(ACLMessage.QUERY_IF);

            msg.setContent("This is a message");
            msg.addReceiver(new AID("GUI", AID.ISLOCALNAME));

            myAgent.send(msg);

            sleep();
        }

    }

    void sleep(){
        try{
            Thread.sleep(SLEEP_SECS*1000);
        }catch(InterruptedException ex){}
    }


    public boolean done(){
        return true;
    }
}

public class GridElementAgent extends Agent
{
    protected void setup(){

        Object[] args = getArguments();

        // TODO will be greate to have system out in a logging structure
        System.out.println("Grid Agent started");

        if(args != null && args.length > 0){

            for(Object arg : args){
                System.out.println("Argument: " + (String) arg);
            }
        }

        addBehaviour(new NotifyGuiBehaviour());
    }
}

