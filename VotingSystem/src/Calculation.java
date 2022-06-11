import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Calculation extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    Statement st=null;
    int key=-1;
    private JLabel lblName;
    private JLabel lblVotes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculation frame = new Calculation();
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
	public Calculation() {
		initcomponents();
		DisplayElections();
	}
	int WinnerId,Votes,Percentage;
	private void GetWinner() {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
			st=con.createStatement();
			String Query="select count(*) from VotesTbl where CandidateId>1";
			
			rs=st.executeQuery(Query);
			while(rs.next()) {
				//JOptionPane.showMessageDialog(this,""+rs.getInt(1));
				WinnerId=rs.getInt(1);
				
			}
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
	}
	
	private void GetWinnerData() {
		String Query="select CName from CandidateTbl where Cid="+WinnerId;
	Statement st;
	ResultSet rs;
	try {
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
		st=con.createStatement();
		rs=st.executeQuery(Query);
		if(rs.next())
		{
			lblName.setText(rs.getString("CName"));
		}
	}catch (Exception e) {
		
	}
	}
	
	
	private void GetVotes() {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
			st=con.createStatement();
			String Query="select count(CandidateId) from VotesTbl where CandidateId="+WinnerId;
			
			rs=st.executeQuery(Query);
			while(rs.next()) {
				
				Votes=rs.getInt(1);
				lblVotes.setText(Votes+" Votes");
			}
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
	}
	
	private void DisplayElections()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");
		st=con.createStatement();
		rs=st.executeQuery("select * from ElectionTbl");
		table.setModel(DbUtils.resultSetToTableModel(rs));
	}catch (Exception e) {
		
	}
	
	}
	public void initcomponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWinner = new JLabel("Winner Is");
		lblWinner.setFont(new Font("Dialog", Font.BOLD, 15));
		lblWinner.setBounds(37, 125, 84, 39);
		contentPane.add(lblWinner);
		
		 lblVotes = new JLabel("");
		lblVotes.setBounds(133, 186, 104, 27);
		contentPane.add(lblVotes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(121, 290, 764, 182);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			int key=-1;
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int MyIndex=table.getSelectedRow();
				key=Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				GetWinner();
				GetWinnerData();
				GetVotes();
			}
		});
		scrollPane.setViewportView(table);
		
	    lblName = new JLabel("");
		lblName.setBounds(133, 131, 104, 27);
		contentPane.add(lblName);
		
		JLabel lblWonBy = new JLabel("won by");
		lblWonBy.setBounds(68, 186, 53, 27);
		contentPane.add(lblWonBy);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/arrow-back-icon.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				ResultPage result=new ResultPage();
				result.setVisible(true);
			}
		});
		btnNewButton.setBounds(781, 503, 104, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setFont(new Font("Dialog", Font.BOLD, 48));
		lblResult.setBackground(Color.WHITE);
		lblResult.setBounds(372, 0, 790, 51);
		contentPane.add(lblResult);
	}
}
