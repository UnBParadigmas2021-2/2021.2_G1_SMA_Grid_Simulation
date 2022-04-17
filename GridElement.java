import jade.core.*;
import jade.lang.acl.*;
import jade.core.behaviours.*;

class NotifyGui extends Behaviour
{

    public void action()
    {
        while(true)
        {
            System.out.println("Notifying gui about new state");

            ACLMessage msg = new ACLMessage(ACLMessage.CFP);
            msg.setContent("This is a meessage");
            msg.addReceiver(new AID("GUI", AID.ISLOCALNAME));

            myAgent.send(msg);

            try{
                Thread.sleep(1000);
            }catch(Exception ex)
            {
            }
        }

    }

    public boolean done()
    {
        return true;
    }
}

public class GridElement extends Agent
{
    protected void setup(){

        Object[] args = getArguments();

        System.out.println("Grid Agent started");

        if(args != null && args.length > 0){

            for(Object arg : args){
                System.out.println("Argument: " + (String) arg);
            }
        }

        addBehaviour(new NotifyGui());
    }
}

