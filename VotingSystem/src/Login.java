import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel lblBack;
    private JPanel contentPane;
    PreparedStatement pst=null;
    ResultSet rs=null;
    Statement st=null;
    private JButton btnNewButton_1;
    private JLabel label;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
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
    public Login() {
    initcomponents();
    }
    public void initcomponents(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
        lblNewLabel.setBounds(215, 25, 151, 73);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(215, 133, 247, 42);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(215, 224, 247, 42);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Dialog", Font.PLAIN, 25));
        lblUsername.setBounds(51, 133, 151, 35);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Dialog", Font.PLAIN, 25));
        lblPassword.setBounds(51, 224, 151, 35);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/secrecy-icon.png"));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(51, 301, 151, 35);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	String Query="Select * from account where user_name='"+textField.getText()+"' and password='" +passwordField.getText()+"'";
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
                        "root", "rootpasswordgiven");

                   st =connection.createStatement();
                   rs=st.executeQuery(Query);
                    if (rs.next()) {
                    	new Voting(rs.getInt(1)).setVisible(true);
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

        contentPane.add(btnNewButton);

       
        
        btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/arrow-back-icon.png"));
        btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				SecondFrame signup=new SecondFrame();
				signup.setVisible(true);
			}
		});
        btnNewButton_1.setBounds(401, 333, 87, 25);
        contentPane.add(btnNewButton_1);
        
        label = new JLabel("");
        label.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Apps-Computer-Login-icon.png"));
        label.setBounds(63, 25, 96, 96);
        contentPane.add(label);
    }
}