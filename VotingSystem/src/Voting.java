import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Voting extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblVoteCounted;
	Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    ResultSet Ru=null;
    Statement st=null;
    JLabel cnamelbl;
    int key=-1;
	int ElecId;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Voting frame = new Voting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	int Vid;
	Statement St1=null;
	ResultSet Rs1=null;
	private void VCount() {
		
		try {
			St1=con.createStatement();
			Rs1=St1.executeQuery("select MAX(VoteId) from VotesTb1");
			Rs1.next();
			Vid=Rs1.getInt(1)+1;
	}catch(Exception Ex) {
		
	}
	}

	
	/**
	 * Create the frame.
	 */
	public Voting() {
		initcomponents();
		DisplayCandidates();
		lblVoteCounted.setVisible(false);
	}
	
	
	int VotingId;
	public Voting(int VoterId)
	{
		initcomponents();
		DisplayCandidates();
		lblVoteCounted.setVisible(false);
		VotingId=VoterId;
		//JOptionPane.showConfirmDialog(this, VotingId);
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
	private void initcomponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourCandidate = new JLabel("Your Candidate");
		lblYourCandidate.setBounds(32, 30, 124, 28);
		contentPane.add(lblYourCandidate);
		
		JLabel cnamelbl = new JLabel("Name");
		cnamelbl.setBounds(32, 89, 124, 28);
		contentPane.add(cnamelbl);
		
		
		JButton btnNewButton = new JButton("Vote");
		btnNewButton.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Hand-Touch-icon.png"));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(key==-1)
				{
					JOptionPane.showInputDialog(this," select your candidate");
				}else {
				
				try {
					
					 VCount();
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
              PreparedStatement Add=con.prepareStatement("insert into VotesTbl values(?,?,?,?)");
              Add.setInt(1, Vid);           
              Add.setInt(2, VotingId);
              Add.setInt(3, ElecId);
              Add.setInt(4, key);
              int row=Add.executeUpdate();
              JOptionPane.showMessageDialog(btnNewButton,"Vote counted");
              con.close();
              lblVoteCounted.setVisible(true);
              DisplayCandidates();
              btnNewButton.setVisible(false);
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(btnNewButton ,"you cannot vote twice");
			}
			}
			
			}
		});
		
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 15));
		btnNewButton.setBounds(296, 61, 143, 56);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(154, 186, 707, 232);
		contentPane.add(scrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int MyIndex=table.getSelectedRow();
					key=Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
					cnamelbl.setText(model.getValueAt(MyIndex, 1).toString());
					ElecId=Integer.valueOf(model.getValueAt(MyIndex, 4).toString());
			}
		});
		scrollPane.setViewportView(table);
		
	
		lblVoteCounted = new JLabel("vote counted!!!");
		lblVoteCounted.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/thumbs-up-icon.png"));
		lblVoteCounted.setFont(new Font("Dialog", Font.BOLD, 20));
		lblVoteCounted.setBounds(487, 61, 255, 45);
		contentPane.add(lblVoteCounted);
		
		JButton btnBack = new JButton("Back");
		btnBack.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/arrow-back-icon.png"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
				SecondFrame second=new SecondFrame();
				second.setVisible(true);
			}
		});
		btnBack.setBounds(744, 455, 117, 25);
		contentPane.add(btnBack);
		
		JButton btnViewResult = new JButton("View Result");
		btnViewResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(btnViewResult, "Result will publish soon");
				
			}
		});
		btnViewResult.setBounds(744, 12, 117, 25);
		contentPane.add(btnViewResult);
		
	}
	}


