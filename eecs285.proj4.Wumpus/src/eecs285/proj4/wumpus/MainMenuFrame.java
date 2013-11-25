package eecs285.proj4.wumpus;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainMenuFrame extends JFrame {


    JButton playButton;
    JButton helpButton;
    JButton quitButton;
    JLabel titleImage;
    
    static BorderLayout mainLayout = new BorderLayout();
    static BorderLayout buttonLayout = new BorderLayout();
    static FlowLayout northLayout = new FlowLayout();
    static FlowLayout centerLayout = new FlowLayout();

    JPanel pane = new JPanel(mainLayout);
    JPanel buttonPane = new JPanel(buttonLayout);
    JPanel centerPane = new JPanel(centerLayout); //buttons and image
    JPanel northPane = new JPanel(northLayout);
    
    HelpWindow helpWindow = new HelpWindow();


    
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

        //FlowLayout southLayout = new FlowLayout();
        
        //put layouts into their panes
        //JPanel northPane = new JPanel(northLayout);
        //JPanel southPane = new JPanel(southLayout);
        
        //size the window
        setBounds(0,0,1000,800);
        
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
        buttonPane.add(playButton, BorderLayout.NORTH);
        buttonPane.add(helpButton, BorderLayout.CENTER);
        buttonPane.add(quitButton, BorderLayout.SOUTH);
        
        buttonLayout.setVgap(25);
        centerLayout.setVgap(150);
        
        ImageIcon wumpusIcon = new ImageIcon("C:/Users/Luke/git/teamWumpus/eecs285.proj4.Wumpus/wumpus.jpg");
        
        //titleImage.setIcon(wumpusIcon);

         
        centerPane.add(buttonPane);
        //northPane.add(titleImage);
        pane.add(centerPane, BorderLayout.CENTER);
        
        setVisible(true); // display this frame
    }
    private void help(){
        helpWindow.setVisible(true);
    };
    
    private void play(){
        new GameWindow();
        setVisible(false);
    };

    
}
