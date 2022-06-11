import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class ResultPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultPage frame = new ResultPage();
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
	public ResultPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Result");
		btnNewButton.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Search-Results-icon.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Calculation cal=new Calculation();
				cal.setVisible(true);
			}
		});
		
		JButton btnOther = new JButton("manage candidates");
		btnOther.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/administrator-icon.png"));
		btnOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Candidates can=new Candidates();
				can.setVisible(true);
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
		
		JButton btnManageVoters = new JButton("manage voters");
		btnManageVoters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				ManageVoters voter=new ManageVoters();
				voter.setVisible(true);
			}
		});
		btnManageVoters.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Hand-Touch-icon.png"));
		JButton btnManageElection = new JButton("manage election");
		btnManageElection.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Election-Polling-Box-icon.png"));
		btnManageElection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Elections election=new Elections();
				election.setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(398)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(46))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(145)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnManageElection, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnManageVoters, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnOther, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
					.addContainerGap(168, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(btnNewButton)
					.addGap(42)
					.addComponent(btnOther, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(btnManageVoters, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(43)
					.addComponent(btnManageElection, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
