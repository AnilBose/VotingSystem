import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;


	/**
	 * Launch the application.
	 */
	public class SignupPage extends JFrame {
	    private static final long serialVersionUID = 1L;
	    private JPanel contentPane;	    
	    private JButton btnNewButton;
	    private JTextField textField;
	    private JTextField textField_1;
	    private JTextField textField_2;
	    private JTextField textField_4;
	    private JTextField textField_5;
	    private JPasswordField passwordField_1;
	    Connection con=null;
	    PreparedStatement pst=null;
	    ResultSet rs=null;
	    Statement st=null;
	    private JComboBox comboBox;
	    int Vid;
		Statement St1=null;
		ResultSet Rs1=null;


	    /**
	     * Launch the application.
	     */
	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    SignupPage frame = new SignupPage();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	    public SignupPage() {
	    	initcomponents();
	    	GetElections();
	    }
	    private void Votercount() {
			
			try {
				St1=con.createStatement();
				Rs1=St1.executeQuery("select MAX(Cid) from CandidateTb1");
				Rs1.next();
				Vid=Rs1.getInt(1)+1;
		}catch(Exception Ex) {
			
		}
		}
	    private void GetElections() {
			// TODO Auto-generated method stub
			try {
			 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
			st=con.createStatement();
			String query="select * from ElectionTbl";
			rs=st.executeQuery(query);
			while(rs.next())
			{
				String ElectId=rs.getString("Eid");
				comboBox.addItem(ElectId);
			}
			}catch (Exception e) {
		}
		}

	    /**
	     * Create the frame.
	     * @return 
	     */

	    public void initcomponents() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(450, 190, 500, 700);
	        setResizable(true);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        
	        JLabel lblNewRegistration = new JLabel("    New Registration");
	        lblNewRegistration.setFont(new Font("Dialog", Font.BOLD, 25));
	        
	        JLabel lblFirstName = new JLabel("First Name");
	        
	        JLabel lblLastName = new JLabel("Last Name");
	        
	        JLabel lblUsername = new JLabel("Username");
	        
	        JLabel lblPassword = new JLabel("Password");
	        
	        JLabel lblEmail = new JLabel("Email");
	        
	        JLabel lblMobileNo = new JLabel("Aadhar No");
	        
	        textField = new JTextField();
	        textField.setColumns(10);
	        
	        textField_1 = new JTextField();
	        textField_1.setColumns(10);
	        
	        textField_2 = new JTextField();
	        textField_2.setColumns(10);
	        
	        textField_4 = new JTextField();
	        textField_4.setColumns(10);
	        
	        textField_5 = new JTextField();
	        textField_5.setColumns(10);
	        
	        passwordField_1 = new JPasswordField();
	        
	        JButton btnNewButton_1 = new JButton("Register");
	        btnNewButton_1.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Ok-icon.png"));
	        btnNewButton_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		
	        		String firstName =   textField.getText();               

	                String msg = "" + firstName;
	                msg += " \n";
	                

	                try {
	                	Votercount();
	                    java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");

	                    PreparedStatement Add=con.prepareStatement("insert into account values(?,?,?,?,?,?,?,?)");
	                    Add.setInt(1, Vid);
	                    Add.setString(2,textField.getText() );
	                    Add.setString(3,textField_1.getText());
	                    Add.setString(4,textField_2.getText());
	                    Add.setString(5,(passwordField_1.getText()));
	                    Add.setString(6,textField_4.getText());
	                    Add.setString(7, textField_5.getText());
	                    Add.setInt(8,Integer.valueOf(comboBox.getSelectedItem().toString()));
	                    int row=Add.executeUpdate();
	                    if (row == 0) {
	                        JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
	                    } else {
	                        JOptionPane.showMessageDialog(btnNewButton,
	                            "Welcome, " + msg + "Your account is sucessfully created");
	                    }
	                    connection.close();
	                } catch (Exception exception) {
	                    exception.printStackTrace();
	                }
	            }
	        });
	        
	        JButton btnNewButton_2 = new JButton("Back");
	        btnNewButton_2.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/arrow-back-icon.png"));
	        btnNewButton_2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		
	        		dispose();
					SecondFrame signup=new SecondFrame();
					signup.setVisible(true);
	        	}
	        });
	        
	        JLabel label = new JLabel("");
	        
	        JLabel lblElection = new JLabel("Election");
	        
	        comboBox = new JComboBox();
	        
	        GroupLayout gl_contentPane = new GroupLayout(contentPane);
	        gl_contentPane.setHorizontalGroup(
	        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(gl_contentPane.createSequentialGroup()
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
	        				.addGroup(gl_contentPane.createSequentialGroup()
	        					.addContainerGap()
	        					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE))
	        				.addGroup(gl_contentPane.createSequentialGroup()
	        					.addGap(94)
	        					.addComponent(lblNewRegistration, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	        			.addGap(40)
	        			.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap())
	        		.addGroup(gl_contentPane.createSequentialGroup()
	        			.addGap(37)
	        			.addComponent(label)
	        			.addContainerGap(453, Short.MAX_VALUE))
	        		.addGroup(gl_contentPane.createSequentialGroup()
	        			.addGap(25)
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	        				.addComponent(lblMobileNo, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(lblElection, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	        				.addComponent(comboBox, 0, 194, Short.MAX_VALUE)
	        				.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
	        			.addGap(162))
	        		.addGroup(gl_contentPane.createSequentialGroup()
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
	        				.addGroup(gl_contentPane.createSequentialGroup()
	        					.addGap(22)
	        					.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
	        				.addGroup(gl_contentPane.createSequentialGroup()
	        					.addGap(25)
	        					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
	        						.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
	        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	        						.addGroup(gl_contentPane.createSequentialGroup()
	        							.addPreferredGap(ComponentPlacement.RELATED)
	        							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	        								.addComponent(passwordField_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
	        								.addComponent(textField_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
	        								.addComponent(textField_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))
	        						.addGroup(gl_contentPane.createSequentialGroup()
	        							.addGap(9)
	        							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))))
	        			.addGap(162))
	        );
	        gl_contentPane.setVerticalGroup(
	        	gl_contentPane.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_contentPane.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(lblNewRegistration, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(textField, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
	        			.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
	        			.addGap(31)
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
	        			.addGap(30)
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
	        			.addGap(33)
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
	        				.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
	        			.addGap(30)
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(lblMobileNo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
	        			.addGap(18)
	        			.addComponent(label)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(lblElection, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
	        			.addGap(49)
	        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 37, Short.MAX_VALUE)
	        				.addComponent(btnNewButton_2))
	        			.addGap(29))
	        );
	        contentPane.setLayout(gl_contentPane);	      
	       
	    }
	}