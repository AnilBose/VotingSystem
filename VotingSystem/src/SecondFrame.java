import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class SecondFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecondFrame frame = new SecondFrame();
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
	public SecondFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("Admin Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				dispose();
				AdminLogin admin=new AdminLogin();
				admin.setVisible(true);
			}
		});
		btnNewButton.setBounds(12, 130, 128, 38);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Voter Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Login userlogin=new Login();
				userlogin.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(370, 130, 118, 38);
		contentPane.add(btnNewButton_1);
		
		JButton btnCandidateRegistration = new JButton("Voter registration");
		btnCandidateRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				SignupPage signup=new SignupPage();
				signup.setVisible(true);
			}
		});
		btnCandidateRegistration.setFont(new Font("Dialog", Font.BOLD, 10));
		btnCandidateRegistration.setBounds(12, 320, 143, 38);
		contentPane.add(btnCandidateRegistration);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/user-icon.png"));
		lblNewLabel.setBounds(12, 0, 128, 128);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("New label");
		label.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/Users-Add-User-icon.png"));
		label.setBounds(360, 0, 128, 128);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("New label");
		label_1.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/signup-icon.png"));
		label_1.setBounds(22, 187, 128, 128);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/list-icon.png"));
		label_2.setBounds(360, 187, 128, 128);
		contentPane.add(label_2);
		
		JButton btnVotersList = new JButton("Voters List");
		btnVotersList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				VotersList voter=new VotersList();
				voter.setVisible(true);
			}
		});
		btnVotersList.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVotersList.setBounds(360, 320, 128, 38);
		contentPane.add(btnVotersList);
	}
}
