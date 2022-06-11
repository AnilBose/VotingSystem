import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ManageVoters extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	private JPasswordField passwordField;
	Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    Statement st=null;
    JComboBox comboBox_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageVoters frame = new ManageVoters();
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
	public ManageVoters() {
	initcomponents();
	DisplayVoters();
	GetElections();

	}
	
	private void DisplayVoters()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
		st=con.createStatement();
		rs=st.executeQuery("select * from account");
		table.setModel(DbUtils.resultSetToTableModel(rs));
	}catch (Exception e) {
		e.printStackTrace();
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
			comboBox_1.addItem(ElectId);
		}
		}catch (Exception e) {
	}
	}
	int Vid;
	Statement St1=null;
	ResultSet Rs1=null;
private void Votercount() {
		
		try {
			St1=con.createStatement();
			Rs1=St1.executeQuery("select MAX(Vid) from account");
			Rs1.next();
			Vid=Rs1.getInt(1)+1;
	}catch(Exception Ex) {
		
	}
	}
	public void initcomponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 1000, 570);
		contentPane.add(panel);
		
		JLabel lblManageVoters = new JLabel("Manage Voters");
		lblManageVoters.setFont(new Font("Dialog", Font.BOLD, 48));
		lblManageVoters.setBackground(Color.WHITE);
		lblManageVoters.setBounds(247, 0, 790, 51);
		panel.add(lblManageVoters);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(22, 75, 91, 30);
		panel.add(lblFirstName);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(116, 81, 143, 24);
		panel.add(textField);
		
		JButton button = new JButton("Add");
		button.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Button-Add-icon.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
					Votercount();
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
              PreparedStatement Add=con.prepareStatement("insert into account values(?,?,?,?,?,?,?,?)");
              Add.setInt(1, Vid);
              Add.setString(2,textField.getText() );
              Add.setString(3,textField_1.getText());
              Add.setString(4,textField_2.getText());
              Add.setString(5,passwordField.getText());
              Add.setString(6,textField_4.getText());
              Add.setString(7,textField_5.getText());
              Add.setInt(8,Integer.valueOf(comboBox_1.getSelectedItem().toString()));
              int row=Add.executeUpdate();
              JOptionPane.showMessageDialog(button,"voter registered");
              con.close();
              DisplayVoters();
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(button,e1);
				
			}
                
			}
		});
		button.setBounds(49, 508, 117, 39);
		panel.add(button);
		
		JButton button_2 = new JButton("Delete");
		button_2.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Actions-window-close-icon.png"));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row=table.getSelectedRow();
				String cell=table.getModel().getValueAt(row, 0).toString();
				String sql="Delete from account where Vid="+cell;
				try {
					pst=con.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Deleted successfully");
					DisplayVoters();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
					
				}
			}
		});
		button_2.setBounds(378, 508, 117, 39);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Back");
		button_3.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Arrow-Back-4-icon.png"));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				ResultPage result=new ResultPage();
				result.setVisible(true);
			}
		});
		button_3.setBounds(217, 508, 117, 39);
		panel.add(button_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(116, 131, 143, 24);
		panel.add(textField_1);
		
	   comboBox_1 = new JComboBox();
		comboBox_1.setBounds(116, 410, 138, 24);
		panel.add(comboBox_1);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(22, 127, 91, 30);
		panel.add(lblLastName);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(22, 180, 91, 30);
		panel.add(lblUsername);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(116, 184, 143, 24);
		panel.add(textField_2);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(22, 242, 91, 30);
		panel.add(lblPassword);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(22, 295, 91, 30);
		panel.add(lblEmail);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(116, 299, 143, 24);
		panel.add(textField_4);
		
		JLabel lblMobileNo = new JLabel("Aadhar No");
		lblMobileNo.setBounds(22, 350, 91, 30);
		panel.add(lblMobileNo);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(116, 354, 143, 24);
		panel.add(textField_5);
		
		JLabel lblElection = new JLabel("Election");
		lblElection.setBounds(22, 404, 91, 30);
		panel.add(lblElection);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(333, 75, 631, 355);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			int key=-1;
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int MyIndex=table.getSelectedRow();
				key=Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				textField.setText(model.getValueAt(MyIndex, 1).toString());
				textField_1.setText(model.getValueAt(MyIndex, 2).toString());
				textField_2 .setText(model.getValueAt(MyIndex, 3).toString());
				passwordField.setText(model.getValueAt(MyIndex, 4).toString());
				textField_4.setText(model.getValueAt(MyIndex, 5).toString());
				textField_5.setText(model.getValueAt(MyIndex, 6).toString());
				comboBox_1.setSelectedItem(model.getValueAt(MyIndex, 7).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(116, 242, 143, 24);
		panel.add(passwordField);
	}
}
