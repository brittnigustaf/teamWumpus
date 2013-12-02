package eecs285.proj4.wumpus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("serial")
public class ScoreWindow extends JFrame {
    
    String url = "http://localhost/scores.php";
    HttpEntity content = null;
    
    JButton backBtn = new JButton("Back");
    BorderLayout mainLayout = new BorderLayout();
    
    JPanel btnPane = new JPanel();
    JPanel scorePane = new JPanel();
    JPanel pane = new JPanel(mainLayout);
    JTextArea scoreText = new JTextArea();
    
    
    
    ScoreWindow(){
        super("High Scores");
        initialize();
    }
    
    void initialize(){
        setBounds(0,0,800,600);
        
        //make it so I don't actually close on "x"
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // inherit main frame
        Container con = this.getContentPane(); 
        
        // add the panel to frame
        con.add(pane); 
        
        //scoreText.setText("Scores Go Here");
        getScores();
        scorePane.add(scoreText);
        
        backBtn.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
                
            }
        });
        btnPane.add(backBtn);
        
        pane.add(scorePane, BorderLayout.NORTH);
        pane.add(btnPane, BorderLayout.SOUTH);
    }
    
    public void getScores(){
       try {
    	  
          HttpClient httpclient = new DefaultHttpClient();  
          HttpResponse response = httpclient.execute(new HttpGet(url)); 
    
          content = response.getEntity();  
        } catch (Exception e) {  
          System.out.println("[GET REQUEST]\tNetwork exception\t" +  e);  
        }  
    
    
    String response = null;
	try {
		response = EntityUtils.toString(content);
	} catch (Exception e) {
		 System.out.println("[GET REQUEST]\tNetwork exception\t" + e);
	}
    //System.out.println("response = " + response);

    String output = "";
    String title;
    title = "NAME\t\tSCORE";
    
    SimpleAttributeSet titleAttr = new SimpleAttributeSet();
	StyleConstants.setFontFamily(titleAttr, "Verdana");
	StyleConstants.setForeground(titleAttr, Color.BLACK);
	
    if(response.length() == 0){
    	output += "\n \t NO HIGH SCORES";
    }
    else{
	    String[] lines = response.split("<br>");
	    for(String line: lines){
	    	String name_score[] = line.split(" ");
	    	output += "\n\n" + name_score[0] + "\t\t" + name_score[1];
	    }
    }
	SimpleAttributeSet scoreAttr = new SimpleAttributeSet();
	StyleConstants.setFontFamily(scoreAttr, "Courier New Italic");
	StyleConstants.setForeground(scoreAttr, Color.BLACK);
	
	try {
		scoreText.getDocument().insertString(0, output, scoreAttr);
	} catch (Exception e) {
		System.out.println("Bad string format" + e);
	}

	scoreText.setEditable(false);
    return;
    }

}
