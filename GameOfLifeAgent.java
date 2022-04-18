import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import jade.lang.acl.*;

class GameOfLifeBehaviour extends Behaviour{

    private ColloredJButton matrix[][];

    public GameOfLifeBehaviour(ColloredJButton m[][]){
        matrix = m;
    }

    public void action(){
        while(true){

            ACLMessage msg = myAgent.receive();

            if(msg==null){
                try{
                    Thread.sleep(1000);
                }catch(Exception ex){}
                continue;
            }

            System.out.println("Comportamento do agente ativado");

            // espera uma mensagem e pinta o quadrado

            //someone whants to say that a state changed
            if(msg.getPerformative() == ACLMessage.INFORM){

                AID sender = msg.getSender();
                String name = sender.getLocalName();


                int cord1 = Integer.parseInt(name.split("-")[0]);
                int cord2 = Integer.parseInt(name.split("-")[1]);

                System.out.println("Sender " + name + " sended the message " + msg.getContent());
                if(msg.getContent().equals("ALIVE"))
                    matrix[cord1][cord2].setBackground(Color.green);
                else
                    matrix[cord1][cord2].setBackground(Color.white);
            }
         }
    }

    public boolean done()
    {
        return true;
    }
}

// Just the default Button 
class ColloredJButton extends  JButton implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e){
        // TODO figure out how to make users set colors
        //this.setBackground(Color.green);
    }
}

public class GameOfLifeAgent extends Agent{

    private ColloredJButton matrix[][];
    private JFrame frame;

    void setupGui(){

        //TODO(make this parameters come from agent arguments)
        int W = 10, H = 10;

        ColloredJButton button;
        matrix = new ColloredJButton[W][H];

        frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for(int i = 0; i<W; ++i){
            for(int j = 0; j<H; ++j){
                button = new ColloredJButton();
                button.addActionListener(button);

                matrix[i][j] = button;
                frame.add(button);
            }
        }

        frame.setLayout(new GridLayout(W, H));
        frame.setSize(1000, 800);
        frame.setVisible(true);
    }


	protected void setup(){
        setupGui();
        addBehaviour(new GameOfLifeBehaviour(matrix));
    }
}

