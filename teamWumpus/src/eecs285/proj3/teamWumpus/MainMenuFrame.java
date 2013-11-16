package eecs285.proj3.teamWumpus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainMenuFrame extends JFrame {

    JPanel pane;
    JButton playButton;
    JButton helpButton;
    JButton quitButton;
    
    public MainMenuFrame() {
        super("Hunt The Wumpus!");
        
        initialize();
    }
    private void initialize(){
        //initialize the components I need
        playButton = new JButton("Play!");
        helpButton = new JButton("Help!");
        quitButton = new JButton("Quit!");
        
        
        //set up the layouts
        BorderLayout mainLayout = new BorderLayout();
        //FlowLayout northLayout = new FlowLayout();
        BorderLayout centerLayout = new BorderLayout();
        //FlowLayout southLayout = new FlowLayout();
        
        //put layouts into their panes
        //JPanel northPane = new JPanel(northLayout);
        JPanel centerPane = new JPanel(centerLayout);
        //JPanel southPane = new JPanel(southLayout);
        pane = new JPanel(mainLayout);
        
        //size the window
        setBounds(0,0,1000,800);
        
        //make it so I don't actually close on "x"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // inherit main frame
        Container con = this.getContentPane(); 
        
        // add the panel to frame
        con.add(pane); 
        
        //Configure my components
        playButton.setMaximumSize(new Dimension(300,150));
        helpButton.setMaximumSize(new Dimension(300,150));
        quitButton.setMaximumSize(new Dimension(300,150));
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                play();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(1);    
            }
        });

        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                help();
            }
        });

        
        //add components to their proper panes
        centerPane.add(playButton, BorderLayout.NORTH);
        centerPane.add(helpButton, BorderLayout.CENTER);
        centerPane.add(quitButton, BorderLayout.SOUTH);




        pane.add(centerPane, BorderLayout.CENTER);
        
        setVisible(true); // display this frame
    }
    private void help(){};
    
    private void play(){};

    
}
