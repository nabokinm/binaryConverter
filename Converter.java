package Question1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * Driver to test MyGui object
 * @author Maxim Nabokin
 *
 */
public class Converter {

	public static void main(String[] args) {
		
		MyGUI test = new MyGUI();

	}
	
}
/**
 * This class allows to create swing application
 * with all the functionality and GUI to convert binary number 
 * to a decimal format and vise versa.
 */
class MyGUI extends JFrame{
	//variables
	
	//main frame
	private JFrame theWindow;
	
	//buttons
	private JButton doIt; 
	private JButton clear;
	private JRadioButton binaryToNum;
	private JRadioButton numToBinary;
	
	//labels
	private JLabel myLabel;
	private JLabel result;
	
	//panels
	private JPanel topPanel;
	private JPanel bottomPanel;
	
	//textfields
	private JTextField decimal;
	private JTextField numResult;
	
	//constructor
	
	/**
	 * Creates MyGui object which is a jframe with two pannels.
	 * Main fraim is formated using BorderLayout. One pannel is 
	 * placed on the north(top), the other is placed on the 
	 * south(bottom). Each panel itself is formaated by
	 * gridlayout.
	 */
	public MyGUI(){
		
		
		//initialize variables
	theWindow= new JFrame("Number Converter");
	theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	doIt= new JButton("Do it");
	clear= new JButton("Clear");
	binaryToNum=new JRadioButton("Binary to Decimal");
	numToBinary=new JRadioButton("Decimal to Binary");
	
	myLabel= new JLabel("1 - Select the mode:");
	result = new JLabel("2 - Enter a number, then click Do it:");
	
	decimal= new JTextField(20);
	numResult=new JTextField(20);
	
	//add radio button to a group 
	ButtonGroup group = new ButtonGroup();
	group.add(binaryToNum);
	group.add(numToBinary);
	
	
	
	//set layout of the frame, set sizes
	theWindow.setLayout(new BorderLayout());
	theWindow.setLocation(500, 300);
	theWindow.setSize(250, 400);
	
	//create the top part
	topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(6,1));
	
	//add top part to the frame
	theWindow.add(topPanel, BorderLayout.NORTH);
	
	//add all fields to the top panel
	topPanel.add(myLabel, 0, 0);
	topPanel.add(binaryToNum, 1 , 1);
	topPanel.add(numToBinary, 2, 2);
	topPanel.add(decimal, 3, 3);
	topPanel.add(result, 4, 4);
	topPanel.add(numResult, 5, 5);
	
	//create bottom panel with the buttons
	bottomPanel = new JPanel();
	bottomPanel.setLayout(new GridLayout(1,1));
	
	theWindow.add(bottomPanel, BorderLayout.SOUTH);
	
	bottomPanel.add(doIt, 0, 0);
	bottomPanel.add(clear, 0, 1);
	
	//Add event listeners
	doIt.addActionListener(new DoListener());
	clear.addActionListener(new ClearListener());
	
	theWindow.setVisible(true);
	
	}
	
	/**
	 * This class implements action when a user pushes "Do it"
	 * button - program converts decimal to binary and vise
	 * versa. If input is incorrect the appropriate message is shown.
	 *
	 */
	private class DoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//read user input
			String input=decimal.getText();
			
			//validate user input
			if(!isNumber(input)){
			numResult.setText("Wrong number.Try again!");
			return;
			}
			
			//convert binaryy to decimal
			if(binaryToNum.isSelected()&&input != ""){
				
				//if input is not a binary number
				if(!input.matches("^[01]+$")){
					numResult.setText("Wrong number.Try again!");
					return;
				}
				
				numResult.setText(binarytoDecimal
						(input));
			}
			
			//convert decimal to binaryy 
			if(numToBinary.isSelected()&&numToBinary.getText() != ""){
				numResult.setText(decimalToBinary
						(Long.valueOf(input)));
			}
			
		}
		
	}
	/**
	 * This method checks if a user input is a number and it
	 * is greater than 0.
	 * @param user input
	 * @return boolean
	 */
	private boolean isNumber(String input)
	{
		
		try
		{
			Double.parseDouble(input);
			//if a number <0 return false
			if(Double.parseDouble(input)<0){
			return false;	
			}
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	/**
	 * This class listens for "Clear" button. If it is pushed
	 * both text fields are set to be empty.
	 *
	 */
	private class ClearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			decimal.setText("");
			numResult.setText("");
		}
		
	}

/**
 *This method converts a number to its binary representation
 *using toBinaryString method.
 * @param user input
 */
public static String decimalToBinary(double x){
	
	return(
			String.valueOf
			(
			Long.toBinaryString((long) x)
			)
		  );
}

/**
 *This method converts a binary number to decimal number
 * @param user input
 */
public static String binarytoDecimal(String x){
	int decimal = Integer.parseInt(x, 2);
	
	String result =String.valueOf(decimal);
	
	return result;
}
}
