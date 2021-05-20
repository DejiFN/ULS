package login;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login1 extends JFrame {

	private JPanel contentPane;
	private JTextField User;
	private JPasswordField Pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login1 frame = new Login1();
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
	public Login1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Page");
		lblNewLabel.setBounds(143, 11, 137, 40);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(101, 42, -64, 40);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setBounds(10, 42, 129, 40);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_2);
		
		User = new JTextField();
		User.setBounds(10, 79, 395, 28);
		contentPane.add(User);
		User.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setBounds(10, 118, 129, 40);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_2_1);
		
		// runs the validate login function and takes the user to the welcome page 
		JButton a = new JButton("Login");
		a.setBounds(10, 212, 103, 23);
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 validateLogin();
			}
		});
		contentPane.add(a);
		
		// takes the user to the sign up page
		JButton b = new JButton("Sign up");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
				new signup().setVisible(true);
			}
		});
		b.setBounds(327, 212, 89, 23);
		contentPane.add(b);
		
		Pass = new JPasswordField();
		Pass.setBounds(10, 161, 395, 28);
		contentPane.add(Pass);
	}
	
	// function to validate userlogin with the database  
	public void validateLogin() {
		String username=User.getText();
		String password=Pass.getText();
		
		try{
			 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/log", "root", "deji");   //connection should be changed to the users'own local host,root and password 
			 
			 String url = "select USER_NAME,  PASSWORD from details where USER_NAME = '"+username+"' and PASSWORD = '"+password+"'" ; //DML Query  to check if the user has a login detail in the database 
			
			 Statement state = connection.createStatement();
			ResultSet  set = state.executeQuery(url);
			 
			if (set.next()) {
				new welcome().setVisible(true);
				dispose();
			} else {
				
				 JOptionPane.showMessageDialog(this, "Please check Username and Password or Signup ");
			}
			
			}
			catch(Exception e) {
			 
			}
	} 
	
	}
	
	

