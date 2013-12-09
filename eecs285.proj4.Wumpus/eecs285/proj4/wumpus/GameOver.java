package eecs285.proj4.wumpus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


/** Purpose: to provide closure to the winner
 * a small box pops up to congratulate the winner
 * and then it closes everything
 * 
 * @author Jessica DeVriese
 */
public class GameOver {
  //member variables
  JFrame over;
  JLabel nameLabel;
  JLabel announce;
  JLabel question;
  JButton yes;
  JButton no;
  JTextField nameField;
  
  
  JPanel announcePane = new JPanel(new FlowLayout());  
  JPanel top;
  JPanel bot;
  
  JPanel submitScreen;
  FlowLayout submitLayout;
  JPanel questionPane = new JPanel(new FlowLayout());
  JPanel namePane = new JPanel(new FlowLayout());

  
  gameSubmit submit;
  gameEnder end;
  Integer Score;
  
  
  GameOver(int inScore, String eventName){

	Score = inScore;
    
	announce = new JLabel(eventName);
    question = new JLabel("Would you like to submit your score of " + Score + "?");
    
    nameLabel = new JLabel("Player Name");
    nameField = new JTextField();
    
    yes = new JButton("Yes");
    no = new JButton("No");
    
    end = new gameEnder();
    submit = new gameSubmit();
    
    yes.addMouseListener(submit);
    no.addMouseListener(end);
    
    over = new JFrame("Game Over");
    over.setLayout(new BorderLayout());
    over.setPreferredSize(new Dimension(500, 175));
    formWindow();
    
    over.setVisible(true);
  }
  
  void formWindow(){
    //EFF: adds all components to JPannels and then JFrame
    //MOD: top, bot, over
    
    
	top = new JPanel(new BorderLayout());
	bot = new JPanel(new FlowLayout());
    
	announcePane.add(announce);
    top.add(announcePane, BorderLayout.NORTH);
    
    questionPane.add(question);
    top.add(questionPane, BorderLayout.CENTER);
        
    ((FlowLayout) namePane.getLayout()).setHgap(25);
    nameField.setPreferredSize(new Dimension(100, 25));
    namePane.add(nameLabel);
    namePane.add(nameField);
    
    top.add(namePane, BorderLayout.SOUTH);
    
    
    bot.add(yes);
    bot.add(no);
    
    over.add(top, BorderLayout.NORTH);
    over.add(bot, BorderLayout.SOUTH);
    over.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    over.pack();
  }
  
  public class gameSubmit extends MouseAdapter{
    //EFF: extends MouseAdapter
    public void mouseClicked(MouseEvent e){

		String url = "http://localhost/scores.php";
 
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
 
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("name", nameField.getText()));
		urlParameters.add(new BasicNameValuePair("score", Score.toString()));
 
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String subText = "";
		try {
			System.out.println(post.toString());
			HttpResponse response = client.execute(post);
			subText = "Score Submitted!";
		} catch (Exception e1) {
			e1.printStackTrace();
			subText = "Something went wrong... sorry!";

		}
		finally{
    		submitScreen = new JPanel();
    		
    		//JLabel subLabel = new JLabel();
    		announce.setText(subText);
    		top.remove(questionPane);
    		top.remove(namePane);
    		bot.remove(yes);
    		bot.remove(no);
    		JButton endBtn = new JButton();
    		
    		endBtn.setText("OK");
    	    endBtn.addMouseListener(end);
    	    endBtn.setPreferredSize(new Dimension(100,25));
    		
            //submitScreen.removeAll();
    		//submitScreen.setLayout(new BorderLayout());
    		//submitScreen.add(subLabel, BorderLayout.NORTH);
    	    bot.setLayout(new BorderLayout());
    	    JPanel flowPanel = new JPanel(new FlowLayout());
    	    flowPanel.add(endBtn);
    	    bot.add(BorderLayout.CENTER, flowPanel);

    		
    		
    		//over.add(submitScreen, BorderLayout.CENTER);
    		over.repaint();
		}
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
