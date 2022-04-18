import jade.core.*;
import jade.lang.acl.*;
import jade.core.behaviours.*;

import java.lang.Math;

interface GridElement {
	boolean isAlive();

	void canBeAlive();
}

class NotifyGuiBehaviour extends Behaviour {
	static final int SLEEP_SECS = 5;
	static final String GUIAgent = "GUI";

	private enum AgentState {
		DEAD, ALIVE
	};

	private AgentState state;

	public NotifyGuiBehaviour() {

		double randomN = Math.random();
		System.out.println(randomN);
		if (randomN < 0.5) {
			state = AgentState.ALIVE;
		} else
			state = AgentState.DEAD;

	}

	private String getStateString() {
		return (state == AgentState.ALIVE) ? "ALIVE" : "DEAD";
	}

	public void action() {

		ACLMessage msg;
		stateChanged();

		while (true) {

			notifyNeighbors();

			// Reply all queries about the state
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.QUERY_IF);

			msg = myAgent.receive(mt);
			while (msg != null) {

				System.out.println("I received a message QUERY_IF " + msg.getContent());
				ACLMessage replyMessage = msg.createReply();
				replyMessage.setPerformative(ACLMessage.INFORM_IF);
				replyMessage.setContent(getStateString());

				myAgent.send(replyMessage);

				msg = myAgent.receive(mt);
			}

			sleep();

			// Couting all response
			mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM_IF);
			int count = 0;
			msg = myAgent.receive(mt);
			while (msg != null) {

				System.out.println("I received a message " + msg.getContent());
				if (msg.getContent().equals("ALIVE"))
					count++;

				msg = myAgent.receive(mt);
			}

			// TODO remover
			// count = (int)(Math.random()*(5-1+1) + 1);

			System.out.println("Count: " + Integer.toString(count));

			if (state == AgentState.ALIVE || count <= 1 || count >= 4) {
				state = AgentState.DEAD;
				stateChanged();
			} else if (state == AgentState.DEAD || count == 3) {
				state = AgentState.ALIVE;
				stateChanged();
			}

			mt = MessageTemplate.MatchPerformative(ACLMessage.CANCEL);
			msg = myAgent.receive(mt);
			if (msg != null) {
				myAgent.doDelete();
				break;
			}
		}
	}

	private void stateChanged() {
		System.out.println("Agent " + myAgent.getLocalName() + " reporting new state to " + GUIAgent);

		// Update my state on GUI
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.setContent(getStateString());
		msg.addReceiver(new AID(GUIAgent, AID.ISLOCALNAME));

		myAgent.send(msg);
	}

	String generateAgentName(int x, int y) {
		return Integer.toString(x) + "-" + Integer.toString(y);
	}

	private void notifyNeighbors() {

		String name = myAgent.getLocalName();
		AID aidSendTo;

		String args[] = name.split("-");
		int cord1 = Integer.parseInt(args[0]);
		int cord2 = Integer.parseInt(args[1]);

		for (int i = -1; i <= 1; ++i)
			for (int j = -1; j <= 1; ++j) {
				ACLMessage message = new ACLMessage(ACLMessage.QUERY_IF);

				if (i != 0 || j != 0) {
					aidSendTo = new AID(generateAgentName(cord1 + i, cord1 + j), AID.ISLOCALNAME);

					System.out.println("Notificando " + aidSendTo.getLocalName());

					message.addReceiver(aidSendTo);
					message.setContent("ALIVE?");

					myAgent.send(message);
				}
			}

	}

	void sleep() {
		try {
			Thread.sleep(SLEEP_SECS * 1000);
		} catch (InterruptedException ex) {
		}
	}

	public boolean done() {
		return true;
	}
}

public class GridElementAgent extends Agent {
	protected void setup() {

		Object[] args = getArguments();

		// TODO will be greate to have system out in a logging structure
		System.out.println("GridElementAgent started with name: " + getLocalName());

		if (args != null && args.length > 0) {

			for (Object arg : args) {
				System.out.println("Argument: " + (String) arg);
			}
		}

		addBehaviour(new NotifyGuiBehaviour());
	}
}
