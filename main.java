import jade.core.Agent;
import jade.core.Runtime;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
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

        String arg = (String)args[0];
        String[] argsSize = arg.split(" ");
        String[] qntdPixelAgents = argsSize[0].split("-");

        AgentController dummy;

        try {

            dummy = cc.createNewAgent("GUI", "GameOfLifeAgent", args);

            dummy.start();

            for(int i = 0; i < Integer.parseInt(qntdPixelAgents[0]); ++i){
                for(int j = 0; j < Integer.parseInt(qntdPixelAgents[1]); ++j){
                    AgentController agent = cc.createNewAgent(Integer.toString(i) + "-" + Integer.toString(j), "GridElementAgent", null);
                    agent.start();
                }
            }

        } catch (StaleProxyException e) {

            e.printStackTrace();

        }
        
        while (true) {
        	MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CANCEL);
        	ACLMessage msg = this.receive(mt);
           if(msg != null) {
        	   System.out.println("MAIN dies!");
        	   this.doDelete();
        	   break;
           }
        }
    }
}
