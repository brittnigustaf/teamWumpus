package eecs285.proj4.wumpus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;

@SuppressWarnings("serial")
public class ScoreWindow extends JFrame {
    
    String url = "http://localhost/285/score.php";
    InputStream content = null;
    
    JButton backBtn = new JButton("Back");
    BorderLayout mainLayout = new BorderLayout();
    
    JPanel btnPane = new JPanel();
    JPanel scorePane = new JPanel();
    JPanel pane = new JPanel(mainLayout);
    JLabel scoreText = new JLabel();
    
    
    
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
        
        scoreText.setText("Scores Go Here");
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
    
    public Object getScores(){
        try {  
          HttpClient httpclient = new DefaultHttpClient();  
          HttpResponse response = httpclient.execute(new HttpGet(url)); 
    
          content = response.getEntity().getContent();  
        } catch (Exception e) {  
          System.out.println("[GET REQUEST]\tNetwork exception\t" +  e);  
        }  
    
    
    String response = content.toString();
    
    return response;
    }

}
