import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setTitle("USThiever by Ark");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsthiever = new JLabel("USThiever");
		lblUsthiever.setFont(new Font("FreeSerif", Font.BOLD, 20));
		lblUsthiever.setBounds(88, 12, 104, 34);
		contentPane.add(lblUsthiever);

		JLabel lblV = new JLabel("v2.5");
		lblV.setBounds(182, 23, 38, 15);
		contentPane.add(lblV);

		JCheckBox chckbxPowerthief = new JCheckBox("Steal by Lvl");
		chckbxPowerthief.setBounds(172, 59, 125, 23);
		contentPane.add(chckbxPowerthief);

		JButton btnStartScript = new JButton("Start Script");
		btnStartScript.setBounds(88, 94, 117, 25);
		contentPane.add(btnStartScript);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bread", "Silk", "Fur", "Silver", "Spice", "Gems"}));
		comboBox.setBounds(26, 58, 117, 24);
		contentPane.add(comboBox);
	}
}
