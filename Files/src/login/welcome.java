package login;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

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

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;

public class welcome extends JFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcome frame = new welcome();
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

	
	public welcome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 479);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 551, 277);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		scrollPane.setColumnHeaderView(horizontalGlue);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		scrollPane.setRowHeaderView(verticalStrut);
		
		Component verticalGlue = Box.createVerticalGlue();
		scrollPane.setColumnHeaderView(verticalGlue);
		
		JButton load = new JButton("Show Data");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//connection should be changed to the users'own local host,root and password 
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/log", "root", "deji");
					String sql = "select * from details";  //DML Query to show data in the database 
					
					PreparedStatement pst = connection.prepareStatement(sql);
					ResultSet rs=pst.executeQuery();
					
					//using an external lib to help convert the database table into jtable
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		load.setBounds(586, 152, 168, 42);
		getContentPane().add(load);
		
		JLabel lblNewLabel = new JLabel("Welcome ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		lblNewLabel.setBounds(333, 11, 238, 42);
		getContentPane().add(lblNewLabel);
		
		JLabel lblUserloginInformation = new JLabel("User-login Information");
		lblUserloginInformation.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		lblUserloginInformation.setBounds(177, 112, 302, 31);
		getContentPane().add(lblUserloginInformation);
		
		
		// logs out the user
		JButton btnSignOut = new JButton("Sign out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login1().setVisible(true);
				dispose();
			}
		});
		btnSignOut.setBounds(586, 373, 168, 42);
		getContentPane().add(btnSignOut);
	}
}
