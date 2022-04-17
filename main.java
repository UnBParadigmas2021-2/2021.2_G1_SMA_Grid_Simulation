import jade.core.Agent;
import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;

public class main extends Agent {
    protected void setup() {
        System.out.println("Alo Mundo! ");
        System.out.println("Meu nome: "+ getLocalName());

        Runtime rt = Runtime.instance();

        Profile p = new ProfileImpl();

        ContainerController cc = rt.createAgentContainer(p);

        Object reference = new Object();

        Object args[] = new Object[1];

        args[0] = reference;

        AgentController dummy;

        try {

            dummy = cc.createNewAgent("GameOfLife", "GameOfLife", args);

            dummy.start();

        } catch (StaleProxyException e) {

            e.printStackTrace();

        }
    }
}