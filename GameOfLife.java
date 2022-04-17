import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import jade.lang.acl.*;

class MyBehavior2 extends Behaviour
{

    private MyJButton matrix[][];
    public MyBehavior2(MyJButton m[][]){
        matrix = m;
    }

    public void action()
    {
        while(true)
        {
            System.out.println("Comportamento do agente ativado");

            ACLMessage msg = myAgent.receive();

            if(msg != null)
            {
                AID sender = msg.getSender();
                System.out.println(msg.getContent());

                String name = sender.getLocalName();

                System.out.println("Name: " + name);
                if(name == "Grid1" || true){

                    System.out.println("Behaviour tried to set a elment to green");
                    matrix[0][0].setBackground(Color.green);

                }

            }else
                System.out.println("null msg");

            try{
                Thread.sleep(1000);
            }catch(Exception ex){}
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

        addBehaviour(new MyBehavior2(matrix));

        frame.setLayout(new GridLayout(W, H));
        frame.setSize(1000, 800);
        frame.setVisible(true);
    }
}

