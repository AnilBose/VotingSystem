import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;

public class VotersList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VotersList frame = new VotersList();
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
	public VotersList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("show voters list");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
	                    java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "rootpasswordgiven");

	                    String query = "select * from account";
	                    PreparedStatement pst = connection.prepareStatement(query);
	                   ResultSet rs =pst.executeQuery();
	                   table.setModel(DbUtils.resultSetToTableModel(rs));
			}
				 catch (Exception exception) {
	                    exception.printStackTrace();
	                }
			}
	});
		
		scrollPane = new JScrollPane();
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				SecondFrame second=new SecondFrame();
				second.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(176)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
					.addGap(90)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
					.addGap(7))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addComponent(btnNewButton_1))
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
					.addGap(7))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
