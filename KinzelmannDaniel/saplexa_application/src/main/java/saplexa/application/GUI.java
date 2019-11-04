package main.java.saplexa.application;

import java.awt.*;
import javax.swing.*;


public class GUI {
	private static final Color TEXT_WHITE = new Color(200,200,200);
	private static final Color BG_DARK = new Color(30,30,30);
	private static final Color BG_DARKER = new Color(20,20,20);
	private static final boolean DarkMode = true;
	protected int width;
	protected int height;
	protected List Numbers = new List(5);
	protected List SpokenWords = new List(10);
	
	
	public GUI(int width, int height) {
	 this.width = width;
	 this.height = height;
	 
	 JFrame frame = new JFrame("SAPLEXA");
	 //frame.setPreferredSize(new Dimension(width,height));
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     //Set up the content pane.
     Container contentPane = frame.getContentPane();
     GridBagLayout layout = new GridBagLayout();
     GridBagConstraints GBC = new GridBagConstraints();
     contentPane.setLayout(layout);
     

     //Create and add the components. 
     //Main Label
     JLabel labelMain = new JLabel("recognized Input");
     labelMain.setFont(new Font("Arial",Font.BOLD,24));
     
     GBC.fill = GridBagConstraints.HORIZONTAL;
     GBC.gridx = 1;
     GBC.gridy = 0;
     GBC.gridwidth = 1;
     GBC.insets = new Insets(5,5,5,5);
     GBC.anchor = GridBagConstraints.PAGE_START;
     contentPane.add(labelMain,GBC);
     ///Numbers Label
     JLabel labelNumbers = new JLabel("Numbers");     
     labelNumbers.setFont(new Font("Arial",Font.PLAIN,16));
     //GBC.fill = GridBagConstraints.HORIZONTAL;
     GBC.gridx = 0;
     GBC.gridy = 1;
     GBC.gridwidth = 1;
     GBC.ipady = 50;
     GBC.ipadx = 100;
     contentPane.add(labelNumbers,GBC);
     //Numbers Text
     //GBC.fill = GridBagConstraints.HORIZONTAL;
     GBC.gridx = 1;
     GBC.gridy = 1;
     GBC.gridheight = GridBagConstraints.RELATIVE;
     GBC.gridwidth = 2;
     GBC.weighty = 0.3;
     contentPane.add(Numbers,GBC);
     
     //Words Label
     JLabel labelWords = new JLabel("Words");
     labelWords.setFont(new Font("Arial",Font.PLAIN,16));
     GBC.fill = GridBagConstraints.VERTICAL;
     GBC.gridx = 0;
     GBC.gridy = 2;
     GBC.gridheight = 1;
     GBC.gridwidth = 1;
     contentPane.add(labelWords,GBC);
     
     //Words Text
     
     GBC.gridx = 1;
     GBC.gridy = 2;
     GBC.gridwidth = 2;
     GBC.gridheight = GridBagConstraints.RELATIVE;
     GBC.ipady = 200;
     GBC.ipadx = 100;
     GBC.weighty = 0.7;
     contentPane.add(SpokenWords,GBC);

     
     if(DarkMode) {
    	 contentPane.setBackground(BG_DARKER);
    	 labelMain.setForeground(TEXT_WHITE);
    	 labelNumbers.setForeground(TEXT_WHITE);
    	 labelWords.setForeground(TEXT_WHITE);
    	 Numbers.setBackground(BG_DARK);
    	 SpokenWords.setBackground(BG_DARK);
    	 Numbers.setForeground(TEXT_WHITE);
    	 SpokenWords.setForeground(TEXT_WHITE);
    	 
     }
     //Display the window.
     frame.pack();
     frame.setVisible(true);
 }

	
	public void setText(String Text) {
		SpokenWords.add(Text);
	}
	public void addNumber(String Text) {
		Numbers.add(Text);
	}
};