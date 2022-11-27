import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ExaminationQuestions implements ActionListener {
	//Array to hold questions
	String[] questions = {
			                    "Which is not a type of polarization?" ,      
			                    "What is Gain?",
			                    "Does increase in Gain increase output?",
			                    "Which is not a procedure for RC loss?" ,      
			                    "What controls the screen display of RC?",
			                    "Reason for powering on TX before Aircraft?",
			                    "What is LOS?",
			                    "Which frequency is used by DataLink?",
			                    "Which frequency gives maximum Range?",
			                    "Primary purpose of RC/DL?"
			                  };
	//2D Array to hold possible options
	String[][] options = {
			               {"Linear", "Circular","Omni","Elliptical"},
	                       {"Unit for signal of TX", "Another name for RC","Unit for signal of RC","Rpas"},
	                       {"Yes", "No","Maybe","Increase is negligible"},
		                   {"Tilt 90 deg.", "Fail-Safe State","Decrease LOS","All is Correct"},
	                       {"TX", "RCL","DataLink","Reciever"},
	                       {"Possible Hijack", "The law of Rpas","Aircraft wont turn on","Prevents damage"},
	                       {"Line of sight", "Linear obstruction sight","Line of situation","All of the above"},
		                   {"443Mhz and 2.4Ghz", "5.8Ghz","443Ghz","DL doesnt need frequency"},
		                   {"Lower frequency", "Higher frequency","2.4Ghz","SHF Range"},
		                   {"RC-Signal", "DL-Telemetry","RC-Telemetry","A,B is correct"}
	                       
	                     };
	//Array of characters to hold correct answers:
	char[] answers = {
			          'C',
			          'A',
			          'B',
			          'C',
			          'C',
			          'A',
			          'A',
			          'A',
			          'A',
			          'D'
	                 };
	
	char guess;
	char answer;
	int index;
	int correct_guesses = 0;
	int total_questions = questions.length;
    int result;
	int seconds = 10;
	//Frame of clicking buttons
	JFrame frame = new JFrame();
	JTextField textField = new JTextField();
	JTextArea textArea = new JTextArea();
	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonD = new JButton();
	JLabel answer_labelA = new JLabel();
	JLabel answer_labelB = new JLabel();
	JLabel answer_labelC = new JLabel();
	JLabel answer_labelD = new JLabel();
	//timer labels
	JLabel time_Label = new JLabel();
	JLabel seconds_left = new JLabel();
	JTextField number_right = new JTextField();
	JTextField percentage = new JTextField();
	
	Timer timer = new Timer(4000,new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			seconds--;
			seconds_left.setText(String.valueOf(seconds));
			if(seconds<=0) {
				displayAnswer();
			}
		   }
	});
	//constructor
	public ExaminationQuestions(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650,650);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(null);
		frame.setResizable(true);
		
		textField.setBounds(0,0,650,50);
		textField.setBackground(new Color(25,25,25));
		textField.setForeground(new Color(25,255,50));
		textField.setFont(new Font("Ink free",Font.BOLD,30));
		textField.setBorder(BorderFactory.createBevelBorder(1));
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setEditable(false);
		textField.setText("Radio Link Exam");
		
		textArea.setBounds(0,50,650,50);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(new Color(25,25,25));
		textArea.setForeground(new Color(25,255,25));
		textArea.setFont(new Font("MV Boli",Font.BOLD,25));
		textArea.setBorder(BorderFactory.createBevelBorder(1));
		textArea.setEditable(false);
		
		buttonA.setBounds(0,100,100,100);
		buttonA.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setText("A");
		
		buttonB.setBounds(0,200,100,100);
		buttonB.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("B");
		
		buttonC.setBounds(0,300,100,100);
		buttonC.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("C");
		
		buttonD.setBounds(0,400,100,100);
		buttonD.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		buttonD.setText("D");
		
		answer_labelA.setBounds(125,100,500,100);
		answer_labelA.setBackground(new Color(50,50,50));
		answer_labelA.setForeground(new Color(25,255,0));
		answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,35));
	
		
		answer_labelB.setBounds(125,200,500,100);
		answer_labelB.setBackground(new Color(50,50,50));
		answer_labelB.setForeground(new Color(25,255,0));
		answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,35));
		
		answer_labelC.setBounds(125,300,500,100);
		answer_labelC.setBackground(new Color(50,50,50));
		answer_labelC.setForeground(new Color(25,255,0));
		answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,35));
		
		answer_labelD.setBounds(125,400,500,100);
		answer_labelD.setBackground(new Color(50,50,50));
		answer_labelD.setForeground(new Color(25,255,0));
		answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,35));
		
		seconds_left.setBounds(535,510,100,100);
		seconds_left.setBackground(new Color(25,25,25));
		seconds_left.setForeground(new Color(255,0,0));
		seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));
		
		time_Label.setBounds(535,475,100,25);
		time_Label.setBackground(new Color(50,50,50));
		time_Label.setForeground(new Color(255,0,0));
		time_Label.setFont(new Font("MV Boli",Font.PLAIN,16));
		time_Label.setHorizontalAlignment(JTextField.CENTER);
		time_Label.setText("timer >:D");
		
		number_right.setBounds(225,225,200,100);
		number_right.setBackground(new Color(25,25,25));
		number_right.setForeground(new Color(25,255,0));
		number_right.setFont(new Font("Ink Free",Font.BOLD,50));
		number_right.setBorder(BorderFactory.createBevelBorder(1));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);
		
		percentage.setBounds(225,225,200,100);
		percentage.setBackground(new Color(25,25,25));
		percentage.setForeground(new Color(25,255,0));
		percentage.setFont(new Font("Ink Free",Font.BOLD,50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);
		
		
		
		
		
		
		//frame.add(time_Label);
		//frame.add(seconds_left);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(answer_labelD);
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonD);
		frame.add(textArea);
		frame.add(textField);
		frame.setVisible(true);
		
		nextQuestion();
	}	
	//method for next question	
	
	public void nextQuestion() {
		if(index>=total_questions) {
			results();
		}
		else {
			textField.setText("Question "+(index+1));
			textArea.setText(questions[index]);
			answer_labelA.setText(options[index][0]);
			answer_labelB.setText(options[index][1]);
			answer_labelC.setText(options[index][2]);
			answer_labelD.setText(options[index][3]);
			timer.start();
		}
	//action listener method	
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		    buttonA.setEnabled(false);
		    buttonB.setEnabled(false);
		    buttonC.setEnabled(false);
		    buttonD.setEnabled(false);
		    
		    if(e.getSource() == buttonA) {
		    	answer = 'A';
		    	if(answer == answers[index]) {
		    		correct_guesses++;
		    		
		    	}
		    }
		    if(e.getSource() == buttonB) {
		    	answer ='B';
		    	if(answer == answers[index]) {
		    		correct_guesses++;
		    	}
		    }
		    if(e.getSource() == buttonC) {
		    	answer= 'C';
		    	if(answer == answers[index]) {
		    		correct_guesses++;
		    	}
		    }
		    if(e.getSource() == buttonD) {
		    	answer= 'D';
		    	if(answer == answers[index]) {
		    		correct_guesses++;
		    	}
			}
			displayAnswer();
	}
	public void displayAnswer() {
		
		timer.stop();
		
		buttonA.setEnabled(true);
		buttonB.setEnabled(true);
		buttonC.setEnabled(true);
		buttonD.setEnabled(true);
		
		if(answers[index] != 'A')
			answer_labelA.setForeground(new Color(25,255,0));
		if(answers[index] != 'B')
			answer_labelB.setForeground(new Color(25,255,0));
		if(answers[index] != 'C')
			answer_labelC.setForeground(new Color(25,255,0));
		if(answers[index] != 'D')
			answer_labelD.setForeground(new Color(25,255,0));
		
		Timer pause = new Timer(500,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				answer_labelA.setForeground(new Color(25,255,0));
				answer_labelB.setForeground(new Color(25,255,0));
				answer_labelC.setForeground(new Color(25,255,0));
				answer_labelD.setForeground(new Color(25,255,0));
				
				answer = ' ';
				seconds=10;
				seconds_left.setText(String.valueOf(seconds));
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				index++;
				nextQuestion();
			}
		});
		pause.setRepeats(false);
		pause.start();
	}
	
	public void results(){
		
		buttonA.setEnabled(true);
		buttonB.setEnabled(true);
		buttonC.setEnabled(true);
		buttonD.setEnabled(true);
		
		result = (int)((correct_guesses/(double)total_questions)*100);
		

		
		if(result >=75) {
		textField.setText("Congrats,You Passed!");
		}
		else {
			
			
			textField.setText("Sorry,You Failed.");
			
	}
		textArea.setText("");
		answer_labelA.setText("");
		answer_labelB.setText("");
		answer_labelC.setText("");
		answer_labelD.setText("");
		
		percentage.setText(result+"%");
	
		number_right.setText("("+correct_guesses+"/"+total_questions+")");
		
		
		frame.add(percentage);
		
		frame.add(number_right);
		
	
	}
}