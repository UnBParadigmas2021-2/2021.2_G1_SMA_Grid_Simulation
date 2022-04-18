import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import jade.lang.acl.*;

class MyBehavior extends Behaviour
{

    public void action()
    {
        while(true)
        {
            System.out.println("Comportamento do agente ativado");

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

            msg.addReceiver(new AID("Test", AID.ISLOCALNAME));

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



class MyJButton extends  JButton implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e){
        this.setBackground(Color.green);
    }
}

public class GameOfLife extends Agent{

    private MyJButton matrix[][];
    private JFrame frame;

	protected void setup(){
        Object[] args = getArguments();
        String arg = (String)args[0];
        String[] coords = arg.split(" ");


        addBehaviour(new MyBehavior());
        int W = 50, H = 50;
        MyJButton button;
        matrix = new MyJButton[W][H];

        frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for(int i = 0; i<W; ++i){
            for(int j = 0; j<H; ++j){

                button = new MyJButton();
                button.addActionListener(button);

                matrix[i][j] = button;
                frame.add(button);

            }
        }

        for (int i = 0; i < coords.length; i++){
            String[] coord = coords[i].split("-");
            int x = Integer.parseInt(coord[0]);
            int y = Integer.parseInt(coord[1]);
            matrix[x][y].setBackground(Color.green);
        }

        frame.setLayout(new GridLayout(W, H));
        frame.setSize(1000, 800);
        frame.setVisible(true);
    }
}

