 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

import javax.sound.sampled.*; 




class Calculator extends JFrame implements ActionListener
{
	private JPanel calcPad;
	private JTextField calcOutput;
	private ArrayList<JButton> buttons;
	private String out;
	private double lhs;
	private String operator;
	
	/*
	 * Constructs a new Calculator
	 */
	
	public Calculator()
	{
		setSize(400,400);
		setTitle("CALCULATOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		out="";
		operator ="";
		
		calcOutput = new JTextField(100);
		calcPad = new JPanel();
		calcOutput.setHorizontalAlignment(JTextField.RIGHT);
		
		calcOutput.setPreferredSize(new Dimension(400,50));
		calcOutput.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
		
		
		calcOutput.setEditable(false);
		
		
		calcPad.setLayout(new GridLayout(5,5,3,3));
		
		calcPad.setBackground(Color.RED);
		
		music();
		
		
		buttons =  new ArrayList<JButton>();
		
		buttons.add(new JButton("7"));
		buttons.add(new JButton("8"));
		buttons.add(new JButton("9"));
		buttons.add(new JButton("/"));
		buttons.add(new JButton("Sqrt"));
		buttons.add(new JButton("4"));
		buttons.add(new JButton("5"));
		buttons.add(new JButton("6"));
		buttons.add(new JButton("*"));
		buttons.add(new JButton("CE"));
		buttons.add(new JButton("1"));
		buttons.add(new JButton("2"));
		buttons.add(new JButton("3"));
		buttons.add(new JButton("-"));
		buttons.add(new JButton("Clr"));
		buttons.add(new JButton("0"));
		buttons.add(new JButton("."));
		buttons.add(new JButton("%"));
		buttons.add(new JButton("+"));
		buttons.add(new JButton("="));
		buttons.add(new JButton("sin"));
		buttons.add(new JButton("cos"));
		buttons.add(new JButton("tan"));
		buttons.add(new JButton("abs"));
		buttons.add(new JButton("^2"));
		
		
		for(JButton j: buttons)
		{
			calcPad.add(j);
			j.setBackground(Color.BLACK);
			j.setForeground(Color.WHITE);
			j.addActionListener(this);
			j.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		}
		
		 
			
		setLayout(new BorderLayout());
		add(calcOutput,BorderLayout.NORTH);
		add(calcPad,BorderLayout.CENTER);
		
		
		
		
		
		
		setVisible(true);
	}
	
	/*
	 * Checks if it is a number 
	 * @param j a JButton
	 * Return boolean if it is a number
	 */
	
	public boolean isNumber(JButton j)
	{
		return Character.isDigit(j.getLabel().charAt(0));
	}
	
	/*
	 * Checks if it is an operator 
	 * @param j a JButton
	 * Return boolean if it is an operator 
	 */
	public boolean isOperator (JButton j)
	{
		return 	j.getLabel().equals("/") ||
				j.getLabel().equals("+") ||
				j.getLabel().equals("*") ||
				j.getLabel().equals("-");
	}

	/*
	 * Functionality of the calculator, performs an action based on button pressed 
	 * @param e an ActionEvent of what button is pressed
	 */
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton)e.getSource();
		
			try 
				{
	   				File file = new File("sound.wav");
	   				Clip clip = AudioSystem.getClip();
   					clip.open(AudioSystem.getAudioInputStream(file));
   					clip.start();
  				} 
  
  				catch (Exception f) 
  				{
   					System.out.println(f.getMessage());
  				}
		
		if (isNumber(button))
		{
			out+= button.getText();
			calcOutput.setText(out);
	
		}
		if(button.getText().equals("."))
		{
			if(!out.contains("."))
				out+= ".";
			calcOutput.setText(out);
		}
		if(button.getText().equals("Clr"))
		{
			lhs = 0;
			out="";
			calcOutput.setText(out);
		}
		if(button.getText().equals("CE"))
		{
			out="";
			calcOutput.setText(out);
		}
		if (isOperator(button))
		{
			if (!out.equals(""))
			{
				
				
				if(!operator.equals(""))
				{
					double solve = 0;
				  
				  switch(operator)
				  {
				    case "+": solve = lhs + Double.parseDouble(out); break;
				    case "-": solve = lhs - Double.parseDouble(out); break;
				    case "*": solve = lhs * Double.parseDouble(out); break;
				    case "/": solve = lhs / Double.parseDouble(out); break;
				    
				  }
				  
				  
				  
				  calcOutput.setText(solve + "");
				  lhs = solve;
				  out = "";	
				  	
				  operator=button.getText();
				}	
				else
				{
					operator=button.getText();
					lhs = Double.parseDouble(out);
					out = "";
					calcOutput.setText("" + lhs);
				}	
			}
			
		}
		if (button.getText().equals("="))
		{
		  double solve = 0;
		  
		  switch(operator)
		  {
		    case "+": solve = lhs + Double.parseDouble(out); break;
		    case "-": solve = lhs - Double.parseDouble(out); break;
		    case "*": solve = lhs * Double.parseDouble(out); break;
		    case "/": solve = lhs / Double.parseDouble(out); break;
		    
		  }
		  
		  
		  
		  calcOutput.setText(solve + "");
		  operator = "";
		  lhs = solve;
		  out = "" + solve;
		  
				try 
				{
	   				File file = new File("equals.wav");
	   				Clip clip = AudioSystem.getClip();
   					clip.open(AudioSystem.getAudioInputStream(file));
   					clip.start();
  				} 
  
  				catch (Exception f) 
  				{
   					System.out.println(f.getMessage());
  				}
		  
		  
		  
		}
		if (button.getText().equals("Sqrt"))
		{
			if (!out.equals(""))
			{
			  out = calcOutput.getText();
			  lhs = Math.sqrt(Double.parseDouble(out));
			  out = "" + lhs;
			  calcOutput.setText(lhs + "");	
			}
		  
		}
		if(button.getText().equals("%"))
		{
			if (!out.equals(""))
			{
			
				double solve = 0;
			  	double percent= Double.parseDouble(out)/100*lhs;
			  
			  switch(operator)
			  {
			    case "+": solve = lhs + percent; break;
			    case "-": solve = lhs - percent; break;
			    case "*": solve = lhs * percent; break;
			    case "/": solve = lhs / percent; break;
			  }
			  
			  out = "";
			  
			  calcOutput.setText(solve + "");
			  operator = "";
			  lhs = solve;	
			}	
		  
		}
		
		if(button.getText().equals("sin"))
		{
			if (!calcOutput.equals(""))
			{
			  out = calcOutput.getText();
			  lhs = Math.sin(Double.parseDouble(out));
			  out = "" + lhs;
			  calcOutput.setText(lhs + "");	
			}	
		}
		if(button.getText().equals("cos"))
		{
			if (!calcOutput.equals(""))
			{
			  out = calcOutput.getText();
			  lhs = Math.cos(Double.parseDouble(out));
			  out = "" + lhs;
			  calcOutput.setText(lhs + "");	
			}	
		}
		if(button.getText().equals("tan"))
		{
			if (!calcOutput.equals(""))
			{
			  out = calcOutput.getText();
			  lhs = Math.tan(Double.parseDouble(out));
			  out = "" + lhs;
			  calcOutput.setText(lhs + "");	
			}	
		}
		if(button.getText().equals("abs"))
		{
			if (!calcOutput.equals(""))
			{
			  out = calcOutput.getText();
			  lhs = Math.abs(Double.parseDouble(out));
			  out = "" + lhs;
			  calcOutput.setText(lhs + "");	
			}	
		}
		if(button.getText().equals("^2"))
		{
			if (!calcOutput.equals(""))
			{
			  out = calcOutput.getText();
			  lhs = Math.pow(Double.parseDouble(out),2 );
			  out = "" + lhs;
			  calcOutput.setText(lhs + "");	
			}	
		}
		
		
	
	}
	
	private static void music() 
	{
		
		try 
		{
	   		File file = new File("song2.wav");
	   		Clip clip = AudioSystem.getClip();
   			clip.open(AudioSystem.getAudioInputStream(file));
			
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);              
      
   		
  		} 
  
  		catch (Exception e) 
  		{
   		System.out.println(e.getMessage());
  		}
 
    }

	
}