package login;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField FNtf;
	private JTextField LNtf;
	private JButton REG;
	private JLabel EM;
	private JTextField EMtf;
	private JLabel UN;
	private JTextField UNtf;
	private JLabel PN;
	private JTextField PNtf;
	private JLabel PW;
	private JLabel AD;
	private JTextField ADtf;
	private JButton DEL;
	private JButton EX;
	private JLabel CP;
	private JPasswordField PWtf;
	private JPasswordField CPtf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sign-up Page");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		lblNewLabel.setBounds(176, 11, 292, 42);
		contentPane.add(lblNewLabel);

		JLabel FN = new JLabel("First Name");
		FN.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		FN.setBounds(10, 59, 120, 28);
		contentPane.add(FN);

		FNtf = new JTextField();
		FNtf.setBounds(79, 64, 158, 20);
		contentPane.add(FNtf);
		FNtf.setColumns(10);

		JLabel LN = new JLabel("Last Name");
		LN.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		LN.setBounds(10, 98, 120, 37);
		contentPane.add(LN);

		LNtf = new JTextField();
		LNtf.setColumns(10);
		LNtf.setBounds(79, 107, 158, 20);
		contentPane.add(LNtf);
		 
		//checks the passwords before then registering the user 
		REG = new JButton("Register");
		REG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkpass();

			}
		});
		REG.setBounds(27, 240, 145, 23);
		contentPane.add(REG);

		EM = new JLabel("Email");
		EM.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		EM.setBounds(10, 140, 120, 28);
		contentPane.add(EM);

		EMtf = new JTextField();
		EMtf.setColumns(10);
		EMtf.setBounds(79, 146, 158, 20);
		contentPane.add(EMtf);

		UN = new JLabel("Username");
		UN.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		UN.setBounds(293, 62, 113, 23);
		contentPane.add(UN);

		UNtf = new JTextField();
		UNtf.setColumns(10);
		UNtf.setBounds(365, 64, 158, 20);
		contentPane.add(UNtf);

		PN = new JLabel("Phone Number");
		PN.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		PN.setBounds(10, 179, 105, 28);
		contentPane.add(PN);

		PNtf = new JTextField();
		PNtf.setColumns(10);
		PNtf.setBounds(79, 203, 158, 20);
		contentPane.add(PNtf);

		PW = new JLabel("Password");
		PW.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		PW.setBounds(293, 102, 120, 28);
		contentPane.add(PW);

		AD = new JLabel("Address");
		AD.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		AD.setBounds(286, 179, 120, 28);
		contentPane.add(AD);

		ADtf = new JTextField();
		ADtf.setBounds(365, 179, 158, 50);
		contentPane.add(ADtf);
		ADtf.setColumns(10);
              
		// runs the delete function 
		DEL = new JButton("Delete");
		DEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		DEL.setBounds(204, 240, 145, 23);
		contentPane.add(DEL);
           
		//exits the application 
		EX = new JButton("Exit");
		EX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		EX.setBounds(375, 240, 145, 23);
		contentPane.add(EX);

		CP = new JLabel("Confirm Password");
		CP.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		CP.setBounds(252, 145, 97, 20);
		contentPane.add(CP);

		PWtf = new JPasswordField();
		PWtf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				blank ();
			}
		});
		PWtf.setBounds(365, 107, 158, 20);
		contentPane.add(PWtf);

		CPtf = new JPasswordField();
		CPtf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				check();
			}
		});
		CPtf.setBounds(365, 145, 158, 20);
		contentPane.add(CPtf);
	}

	// function to delete the user information 
	public void delete () {
		FNtf.setText("");
		LNtf.setText("");
		EMtf.setText("");
		UNtf.setText("");
		PWtf.setText("");
		ADtf.setText("");
		PNtf.setText("");
		CPtf.setText("");
	};
	
	
   // function to register the user 
	public void  register (){

		try{
			//connection should be changed to the users'own local host,root and password 
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/log", "root", "deji"); 
			String url= "insert into details values (?,?,?,?,?,?,?,?)";  //DML Query to insert data in the database 
			
           //using PreparedStatement to prevent mysql injection attacks 
			PreparedStatement ms = connection.prepareStatement(url);
			
			ms.setString(1, FNtf.getText());
			ms.setString(2, LNtf.getText());
			ms.setString(3, UNtf.getText());
			ms.setString(4, PWtf.getText());
			ms.setString(5, CPtf.getText());
			ms.setString(6, EMtf.getText());
			ms.setString(7, PNtf.getText());
			ms.setString(8, ADtf.getText());

			int i =ms.executeUpdate();
			if (i>0) {

				JOptionPane.showMessageDialog(this, "User has registered Sucessfully");
				new Login1().setVisible(true);
				dispose();
			}

		}
		catch(Exception e) {

		}
	} 

	
	
	//function to check if the two passwords not the same or not blank 
	public void checkpass() {

		String pass = PWtf.getText();
		String pass2 = CPtf.getText();

		if (pass.equals("")) {

			JOptionPane.showMessageDialog(this, "The password cannot be blank");

		} 
		else if(!pass.equals(pass2)) {

			JOptionPane.showMessageDialog(this, "The passwords are not the same");


		} else if (UNtf.getText().equals("")) {

			JOptionPane.showMessageDialog(this, "The username cannot be blank");
		}
		else {

			register ();
		}
	}
	
	// function to check if the two passwords are the same 
	public void check() {

		String pass = PWtf.getText();
		String pass2 = CPtf.getText();


		if(!pass.equals(pass2)) {

			JOptionPane.showMessageDialog(this, "The passwords are not the same");


		} 
	}
	
	
// function to stop the user from using a blank password 
	public void blank () {

		String pass = PWtf.getText();
		if (pass.equals("")) {

			JOptionPane.showMessageDialog(this, "The passwords cannot be blank");
		}

	}
}









