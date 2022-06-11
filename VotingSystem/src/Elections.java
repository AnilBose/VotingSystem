import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import com.toedter.calendar.JDayChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Elections extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    Connection con=null;
    PreparedStatement pst=null;
    ResultSet Ru=null;
    Statement st=null;

    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Elections frame = new Elections();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	int Eid;
	Statement St1=null;
	ResultSet Rs1=null;
	private JTable table;
	private void Eleccount() {
		
		try {
			St1=con.createStatement();
			Rs1=St1.executeQuery("select MAX(Eid) from ElectionTb1");
			Rs1.next();
			Eid=Rs1.getInt(1)+1;
	}catch(Exception Ex) {
		
	}
	}
	private void DisplayElections()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
		st=con.createStatement();
		Ru=st.executeQuery("select * from ElectionTbl");
		table.setModel(DbUtils.resultSetToTableModel(Ru));
	}catch (Exception e) {
		
	}
	
	}


	/**
	 * Create the frame.
	 */
	public Elections() {
	initComponents();
	DisplayElections();
	}
	
	public void initComponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Elections");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 48));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(247, 0, 790, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(44, 75, 69, 30);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(116, 81, 143, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(120, 148, 139, 24);
		contentPane.add(dateChooser);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(44, 148, 69, 30);
		contentPane.add(lblDate);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Button-Add-icon.png"));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					try {
						
						 Eleccount();
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
                   PreparedStatement Add=con.prepareStatement("insert into ElectionTbl values(?,?,?)");
                   Add.setInt(1, Eid);
                   Add.setString(2, textField.getText());
                   Add.setString(3,String.valueOf(dateChooser.getDate().toString()));
                   int row=Add.executeUpdate();
                   JOptionPane.showMessageDialog(btnNewButton,"election added successfully");
                   con.close();
                   DisplayElections();
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(btnNewButton ,e1);
					
				}
				}
			
		});
		btnNewButton.setBounds(44, 405, 117, 39);
		contentPane.add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Actions-window-close-icon.png"));
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
			String cell=table.getModel().getValueAt(row, 0).toString();
			String sql="Delete from ElectionTbl where Eid="+cell;
			try {
				pst=con.prepareStatement(sql);
				pst.execute();
				JOptionPane.showMessageDialog(null,"Deleted successfully");
				DisplayElections();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,e);
				
			}
			}
		});

		btnDelete.setBounds(394, 405, 117, 39);
		contentPane.add(btnDelete);
		
		JButton btnBack = new JButton("Back");
		btnBack.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Arrow-Back-4-icon.png"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				ResultPage result=new ResultPage();
				result.setVisible(true);
			}
		});
		btnBack.setBounds(225, 405, 117, 39);
		contentPane.add(btnBack);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(333, 74, 655, 288);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			int key=-1;
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int MyIndex=table.getSelectedRow();
				key=Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				textField.setText(model.getValueAt(MyIndex, 1).toString());
			}
		});
		scrollPane.setViewportView(table);
		
	}
}
