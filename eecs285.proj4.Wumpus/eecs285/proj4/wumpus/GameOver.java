package eecs285.proj4.wumpus;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/** Purpose: to provide closure to the winner
 * a small box pops up to congratulate the winner
 * and then it closes everything
 * 
 * @author Jessica DeVriese
 */
public class GameOver {
  //member variables
  JFrame over;
  JLabel announce;
  JLabel question;
  JButton yes;
  JButton no;
  
  JPanel top;
  JPanel bot;
  
  gameSubmit submit;
  gameEnder end;
  int Score;
  
  
  GameOver(int inScore){

    announce = new JLabel("Game Over");
    question = new JLabel("Would you like to submit score?");
    
    yes = new JButton("Yes");
    no = new JButton("No");
    
    end = new gameEnder();
    submit = new gameSubmit();
    
    yes.addMouseListener(end);
    no.addMouseListener(submit);
    
    over = new JFrame("Game Over");
    over.setLayout(new BorderLayout());
    formWindow();
    
    over.setVisible(true);
  }
  
  void formWindow(){
    //EFF: adds all components to JPannels and then JFrame
    //MOD: top, bot, over
	  
	top = new JPanel(new GridLayout(2,5));
	bot = new JPanel(new GridLayout(1,5));
    
    top.add(new JLabel(""));
    top.add(new JLabel(""));
    top.add(announce);
    top.add(new JLabel(""));
    top.add(new JLabel(""));
    top.add(new JLabel(""));
    top.add(new JLabel(""));
    top.add(question);
    top.add(new JLabel(""));
    top.add(new JLabel(""));
    
    bot.add(new JLabel(""));
    bot.add(yes);
    bot.add(new JLabel(""));
    bot.add(no);
    bot.add(new JLabel(""));
    
    over.add(top, BorderLayout.NORTH);
    over.add(bot, BorderLayout.SOUTH);
    over.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    over.pack();
  }
  
  public class gameSubmit extends MouseAdapter{
    //EFF: extends MouseAdapter
    public void mouseClicked(MouseEvent e){
      //EFF: only called when to submit
       
    }
  }
  
  public class gameEnder extends MouseAdapter{
	    //EFF: extends MouseAdapter
	    public void mouseClicked(MouseEvent e){
	      //EFF: only called when totally end
	    	
	    	System.exit(1);
	       
	    }
	  }

}
