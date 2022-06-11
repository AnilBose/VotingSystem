import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	PreparedStatement pst=null;
    ResultSet rs=null;
    Statement st=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUsername = new JLabel("username");
		
		JLabel lblPassword = new JLabel("password");
		
		passwordField = new JPasswordField();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login ");
		btnNewButton.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/secrecy-icon.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String Query="Select * from admin where username='"+textField.getText()+"' and password='" +passwordField.getText()+"'";
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
                        "root", "rootpasswordgiven");

                   st =connection.createStatement();
                   rs=st.executeQuery(Query);
                    if (rs.next()) {
                    	ResultPage result=new ResultPage();
    					result.setVisible(true);
    					dispose();
                      JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1);;
                }
            }
			
		});
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/arrow-back-icon.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				SecondFrame signup=new SecondFrame();
				signup.setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/User-Interface-Login-icon.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(28, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGap(165))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addComponent(btnNewButton)
					.addGap(92)
					.addComponent(btnNewButton_1)
					.addGap(22))
		);
		contentPane.setLayout(gl_contentPane);
	}
}