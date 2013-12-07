package eecs285.proj4.wumpus;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainMenuFrame extends JFrame {


    JButton playButton;
    JButton helpButton;
    JButton quitButton;
    JButton scoreButton;
    JLabel titleImage;
    
    static BorderLayout mainLayout = new BorderLayout();
    static BorderLayout buttonLayout = new BorderLayout();
    static FlowLayout northLayout = new FlowLayout();
    static FlowLayout centerLayout = new FlowLayout();
    static BorderLayout middleButtonLayout = new BorderLayout();
    
    JPanel pane = new JPanel(mainLayout);
    JPanel middleButtonPane = new JPanel(middleButtonLayout);
    JPanel buttonPane = new JPanel(buttonLayout);
    JPanel centerPane = new JPanel(centerLayout); //buttons and image
    JPanel northPane = new JPanel(northLayout);
    
    HelpWindow helpWindow = new HelpWindow();
    ScoreWindow scoreWindow = new ScoreWindow();


    
    public MainMenuFrame() {
        super("Hunt The Wumpus!");
        
        initialize();
    }
    private void initialize(){
        //initialize the components I need
        playButton = new JButton("Play");
        helpButton = new JButton("Help");
        quitButton = new JButton("Quit");
        scoreButton = new JButton("High Scores");
        
        
        //set up the layouts

        //FlowLayout southLayout = new FlowLayout();
        
        //put layouts into their panes
        //JPanel northPane = new JPanel(northLayout);
        //JPanel southPane = new JPanel(southLayout);
        
        //size the window
        setBounds(0,0,1000,600);
        
        //close on "x"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // inherit main frame
        Container con = this.getContentPane(); 
        
        // add the panel to frame
        con.add(pane); 
        
        //Configure my components
        playButton.setPreferredSize(new Dimension(200,50));
        helpButton.setPreferredSize(new Dimension(200,50));
        quitButton.setPreferredSize(new Dimension(200,50));
        scoreButton.setPreferredSize(new Dimension(200,50));

        
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
        
        scoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                score();
            }
        });

        
        //add components to their proper panes
        buttonPane.add(playButton, BorderLayout.NORTH);
        middleButtonPane.add(helpButton, BorderLayout.NORTH);
        middleButtonPane.add(scoreButton, BorderLayout.CENTER);
        buttonPane.add(middleButtonPane, BorderLayout.CENTER);
        buttonPane.add(quitButton, BorderLayout.SOUTH);
        
        middleButtonLayout.setVgap(25);
        buttonLayout.setVgap(25);
        
        centerLayout.setVgap(150);
        ImageIcon wumpusIcon = null;
        try{        
            wumpusIcon = new ImageIcon(getClass().getResource("wumpus.jpg"));
            titleImage.setIcon(wumpusIcon);
        }catch(Exception e){
            System.out.println("can't find image: " + e);
        }

         
        centerPane.add(buttonPane);
        //northPane.add(titleImage);
        pane.add(centerPane, BorderLayout.CENTER);
        
        setVisible(true); // display this frame
    }
    private void help(){
        helpWindow.setVisible(true);
    };
    
    private void play(){
        new GameWindow(scoreWindow);
        setVisible(false);
    };
    
    private void score(){
        scoreWindow.setVisible(true);  
    };
    

    
}
