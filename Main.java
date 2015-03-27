import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Skill;

@ScriptManifest(author = "Ark",
category = Category.THIEVING,
description = "Steals from stalls, Depending on level, and sells for profit!",
name = "UThief by Ark",
servers = { "Ultimate Scape" },
version = 2.5)

public class Main extends Script implements Paintable {

	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	public long startTime;

	public static boolean powerT = false;
	public static int stallID;
	public static int actID;


	//Paint variables
	private final Color color1 = new Color( 229, 255, 59 );
	private final Font font2 = new Font( "Arial", 0, 14 );

	public static int expGained;
	public static int currentGold;
	public static int startExp;
	public static int startGP;

	public static Image img1;

	//GUI
	Gui x = new Gui();
	public boolean guiWait = true;
	
	/***************************************************************************************************************************************/
	
	public boolean onExecute() {
		x.setVisible(true);
		while (x.isRunning && guiWait) {
			Time.sleep(200);
		}

		img1 = getImage( "http://i.imgur.com/aCQiD9M.png" );
		startTime = System.currentTimeMillis();
		startExp = Skill.THIEVING.getExperience();
		startGP = Inventory.getCount(true, 996);

		strategies.add(new Relog());
		strategies.add(new Teleport());
		strategies.add(new Steals());
		strategies.add(new Sells());

		provide(strategies);

		return true;
	}
	/***************************************************************************************************************************************/
	public static Image getImage( String url )
	{
		try {
			return ImageIO.read( new URL( url ) );
		} catch( IOException e ) {
			return null;
		}
	}
	/***************************************************************************************************************************************/
	public String addDecimals(int i)
	{
		DecimalFormat x = new DecimalFormat("#,###");


		return "" + x.format(i);
	}
	/***************************************************************************************************************************************/
	@Override
	public void paint( Graphics arg0 )
	{
		final int expGained = Skill.THIEVING.getExperience() - startExp;
		final int currentGP =  Inventory.getCount(true, 996) - startGP;

		Graphics2D g = (Graphics2D) arg0;
		g.drawImage(img1, 4, 23, null);
		g.setFont(font2);
		g.setColor(color1);
		g.drawString(addDecimals(expGained), 91, 62);
		g.drawString(addDecimals(currentGP),91,75);
		g.drawString(runTime(startTime), 91, 91);

	}
	/***************************************************************************************************************************************/

	/***************************************************************************************************************************************/
	public static String runTime(long i) {

		DecimalFormat nf = new DecimalFormat("00");
		long millis = System.currentTimeMillis() - i;
		long hours = millis / (1000 * 60 * 60);
		millis -= hours * (1000 * 60 * 60);
		long minutes = millis / (1000 * 60);
		millis -= minutes * (1000 * 60);
		long seconds = millis / 1000;

		return nf.format(hours) + ":" + nf.format(minutes) + ":"
		+ nf.format(seconds);
	}
	/***************************************************************************************************************************************/
	public class Gui extends JFrame {

		private static final long serialVersionUID = -6241803601296202605L;
		public boolean isRunning = true;
		private JPanel contentPane;

		public void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Gui frame = new Gui();
						frame.setVisible(true);
					} catch (Exception e) {
						System.out.println("Line 136");
					}
				}
			});
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Gui() {
			setTitle("USThiever by Ark");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 248, 167);
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

			final JCheckBox checkBox = new JCheckBox("Steal by Lvl");
			checkBox.setBounds(172, 59, 125, 23);
			contentPane.add(checkBox);

			JButton btnStart = new JButton("Start Script");
			btnStart.setBounds(88, 94, 117, 25);
			contentPane.add(btnStart);

			final JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bread", "Silk", "Fur", "Silver", "Spice", "Gems"}));
			comboBox.setBounds(26, 58, 117, 24);
			contentPane.add(comboBox);

			//Start Button
			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkBox.isSelected()) {
						powerT = true;
					} else {
						if(comboBox.getSelectedIndex() == 0) {
							//Bread
							stallID = 1616;
							actID = 34384;
						} else if (comboBox.getSelectedIndex() == 1) {
							//Silk
							stallID = 1615;
							actID = 34383;
						} else if (comboBox.getSelectedIndex() == 2) {
							//Fur
							stallID = 1619;
							actID = 34387;
						} else if (comboBox.getSelectedIndex() == 3) {
							//Silver
							stallID = 1614;
							actID = 34382;
						} else if (comboBox.getSelectedIndex() == 4) {
							//Spice
							stallID = 1618;
							actID = 34386;
						} else if (comboBox.getSelectedIndex()== 5) {
							//Gems
							stallID = 1617;
							actID = 34385;
						}
					}
					guiWait = false;
					isRunning = false;
					x.dispose();
				}

			});
			btnStart.setBounds(55, 81, 95, 23);
			contentPane.add(btnStart);

		}
	}

}

