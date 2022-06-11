import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import com.toedter.calendar.JDayChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Candidates extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JComboBox comboBoxName;
	private JComboBox comboBox ;
	Connection con=null;
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
					Candidates frame = new Candidates();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Candidates() {
		initComponents();
		 GetElections();
		 DisplayCandidates();
	}
	private void DisplayCandidates()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
		st=con.createStatement();
		rs=st.executeQuery("select * from CandidateTbl");
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
			comboBoxName.addItem(ElectId);
		}
		}catch (Exception e) {
	}
	}
	int Cid;
	Statement St1=null;
	ResultSet Rs1=null;
private void Candcount() {
		
		try {
			St1=con.createStatement();
			Rs1=St1.executeQuery("select MAX(Cid) from CandidateTb1");
			Rs1.next();
			Cid=Rs1.getInt(1)+1;
	}catch(Exception Ex) {
		
	}
	}
	/**
	 * Create the frame.
	 */
	public void initComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(12, 0, 1000, 570);
		contentPane.add(panel);
		
		JLabel lblManageParties = new JLabel("Manage candidates");
		lblManageParties.setFont(new Font("Dialog", Font.BOLD, 48));
		lblManageParties.setBackground(Color.WHITE);
		lblManageParties.setBounds(247, 0, 790, 51);
		panel.add(lblManageParties);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(44, 75, 69, 30);
		panel.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(116, 81, 143, 24);
		panel.add(textField);

		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(44, 148, 69, 30);
		panel.add(lblAge);
		
		JButton button = new JButton("Add");
		button.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Button-Add-icon.png"));
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				
				try {	
					Candcount();
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
              PreparedStatement Add=con.prepareStatement("insert into CandidateTbl values(?,?,?,?,?)");
              Add.setInt(1, Cid);
              Add.setString(2,textField.getText() );
              Add.setInt(3,Integer.valueOf(textField_1.getText()));
              Add.setString(4,comboBox.getSelectedItem().toString());
              Add.setInt(5,Integer.valueOf(comboBoxName.getSelectedItem().toString()));
              int row=Add.executeUpdate();
              JOptionPane.showMessageDialog(button,"candidate registered");
              con.close();
              DisplayCandidates();
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(button,e1);
				
			}
                
			}
		});
			
		button.setBounds(44, 441, 117, 39);
		panel.add(button);
		
		JButton button_2 = new JButton("Delete");
		button_2.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Actions-window-close-icon.png"));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				String cell=table.getModel().getValueAt(row, 0).toString();
				String sql="Delete from CandidateTbl where Cid="+cell;
				try {
					pst=con.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Deleted successfully");
					DisplayCandidates();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
					
				}
			}
		});
		button_2.setBounds(388, 441, 117, 39);
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
		button_3.setBounds(219, 441, 117, 39);
		panel.add(button_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(116, 148, 143, 24);
		panel.add(textField_1);
		
		 comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboBox.setBounds(121, 214, 138, 24);
		panel.add(comboBox);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(44, 208, 69, 30);
		panel.add(lblGender);
		
		JLabel lblElection = new JLabel("Election");
		lblElection.setBounds(44, 270, 69, 30);
		panel.add(lblElection);
		
	    comboBoxName = new JComboBox();
		comboBoxName.setBounds(121, 273, 138, 24);
		panel.add(comboBoxName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(353, 71, 617, 304);
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
				comboBox.setSelectedItem(model.getValueAt(MyIndex, 3).toString());
				comboBoxName.setSelectedItem(model.getValueAt(MyIndex, 4).toString());
			}
		});
		scrollPane.setViewportView(table);
		
			
			
	}
}
