import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Font;

public class FirstPage extends JFrame {

	private JPanel contentPane;
	private static JLabel lblLoadingModules;
	private  JLabel lblNewLabel;
	private static JProgressBar progressBar_2;
	private JLabel lblNewLabel_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int x;
					FirstPage frame = new FirstPage();
					frame.setVisible(true);
					try {
						
				for(x=0;x<=100;x++)
				{
					FirstPage.progressBar_2.setValue(x);
					Thread.sleep(50);
					FirstPage.lblLoadingModules.setText(Integer.toString(x)+"%");
					if(x==100) {
						SecondFrame second=new SecondFrame();
						second.setVisible(true);
						frame.dispose();
					}
				}
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
	}

	/**
	 * Create the frame.
	 */
	public FirstPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	  
	  lblNewLabel_1 = new JLabel("VOTING SYSTEM");
	  lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 40));
	  lblNewLabel_1.setBounds(154, 22, 378, 43);
	  contentPane.add(lblNewLabel_1);
	 
	  lblLoadingModules = new JLabel("");
	  lblLoadingModules.setForeground(Color.BLACK);
	  lblLoadingModules.setBounds(12, 294, 140, 26);
	  contentPane.add(lblLoadingModules);
		
	 lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/home/anil/eclipse-workspace/VotingSystem/image/indian-flag-1079100_1280(1).jpg"));
		lblNewLabel.setBounds(0, 0, 666, 400);
		contentPane.add(lblNewLabel);
		
		 progressBar_2 = new JProgressBar();
		progressBar_2.setForeground(Color.BLACK);
		progressBar_2.setBounds(12, 332, 612, 14);
		contentPane.add(progressBar_2);
		
	}
}
