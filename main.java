import jade.core.Agent;
import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;

public class main extends Agent {
    protected void setup() {
        Object[] mainArgs = getArguments();
        System.out.println("Alo Mundo! " + (String)mainArgs[0]);
        System.out.println("Meu nome: "+ getLocalName());

        Runtime rt = Runtime.instance();

        Profile p = new ProfileImpl();

        ContainerController cc = rt.createAgentContainer(p);

        Object args[] = new Object[1];

        args[0] = (String)mainArgs[0];

        AgentController dummy;

        try {

            dummy = cc.createNewAgent("GameOfLife", "GameOfLife", args);

            dummy.start();

        } catch (StaleProxyException e) {

            e.printStackTrace();

        }
    }
}