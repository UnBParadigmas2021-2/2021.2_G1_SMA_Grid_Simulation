import jade.core.*;
import jade.lang.acl.*;
import jade.core.behaviours.*;

import java.lang.Math;

interface GridElement{
    boolean isAlive();
    void canBeAlive();
}

class NotifyGuiBehaviour extends Behaviour
{
    static final int SLEEP_SECS = 1;
    static final String GUIAgent = "GUI";
    private enum AgentState {DEAD, ALIVE};
    private AgentState state;

    public NotifyGuiBehaviour()
    {

        double randomN = Math.random();
        System.out.println(randomN);
        if(randomN < 0.5){
            state = AgentState.ALIVE;
        }else
            state = AgentState.DEAD;

    }

    private String getStateString(){
        return (state == AgentState.ALIVE)? "ALIVE" : "DEAD";
    }

    public void action(){ 

        ACLMessage msg;
        stateChanged();

        while(true){

            // Reply all queries about the state
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.QUERY_IF);
            while((msg = myAgent.receive(mt)) != null){
                ACLMessage replyMessage = msg.createReply();
                replyMessage.setPerformative(ACLMessage.INFORM_IF);
                replyMessage.setContent(getStateString());

                myAgent.send(replyMessage);
            }

            // Couting all response
            mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM_IF);
            int count = 0;
            while((msg = myAgent.receive(mt)) != null){
                if(msg.getContent().equals("ALIVE"))
                    count++;

            }

            count = (int)(Math.random()*(5-1+1) + 1);
            System.out.println("Count: " + Integer.toString(count));

            if(state == AgentState.ALIVE || count <= 1 || count <=4){
               state = AgentState.DEAD;
               stateChanged();
            }else if(state == AgentState.DEAD || count == 3){
                state = AgentState.ALIVE;
                stateChanged();
            }

            sleep();
        }
    }

    private void stateChanged(){
        System.out.println("Agent " + myAgent.getLocalName() + " reporting new state to " + GUIAgent);

        // Update my state on GUI
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent(getStateString());
        msg.addReceiver(new AID(GUIAgent, AID.ISLOCALNAME));

        myAgent.send(msg);
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

