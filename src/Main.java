import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static final int roundDigits = 4;
	public static int shinyChance = 30000; // 1 in this number
	
	public static Settings settings = new Settings();
	public static JButton add1 = new JButton();
	public static JButton add3 = new JButton();
	public static JButton add5 = new JButton();
	public static int count = 0;
	public static JLabel current_count_name = new JLabel();
	public static JLabel current_count = new JLabel();
	public static JLabel current_shiny_chance_name = new JLabel();
	public static JLabel current_shiny_chance_name2 = new JLabel();
	public static JLabel current_shiny_chance = new JLabel();
	
	public static JMenuBar menuBar = new JMenuBar();
	
	private static JMenu options = new JMenu();
	
	public static JMenuItem reset = new JMenuItem("reset");
	public static JMenuItem set_count_to = new JMenuItem("set_count_to");
	public static JMenuItem set_shiny_chance = new JMenuItem("set_shiny_chance_to");
	public static JMenuItem exit = new JMenuItem("exit");
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				new Main();
				
			}
		});
		
	}
	
	public Main() {
		
		setVisible(true);
		
		load();
		count = Integer.valueOf(Settings.getSetting("count"));
		shinyChance = Integer.valueOf(Settings.getSetting("shiny_chance"));
		
		setLayout(null);

		setSize(400, 240);
		setResizable(false);
		setTitle("Shiny Counter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		add(add1);
		add(add3);
		add(add5);
		add(current_count_name);
		add(current_count);
		add(current_shiny_chance_name);
		add(current_shiny_chance_name2);
		add(current_shiny_chance);
		
		add1.setText("+1");
		add3.setText("+3");
		add5.setText("+5");
		current_count_name.setText("Current count:");
		current_count.setText(Settings.getSetting("count"));
		current_shiny_chance_name.setText("Chance to have");
		current_shiny_chance_name2.setText("seen a Shiny:");
		current_shiny_chance.setText("0.0000%");
		updateChances();
		
		add1.setBounds(100,20,50,50);
		add3.setBounds(170,20,50,50);
		add5.setBounds(240,20,50,50);
		setLabelFont(current_count_name);
		setCountFont(current_count);
		setLabelFont(current_shiny_chance_name);
		setLabelFont(current_shiny_chance_name2);
		setCountFont(current_shiny_chance);
		current_count_name.setBounds(100,80,200,40);
		current_shiny_chance_name.setBounds(86,90,200,80);
		current_shiny_chance_name2.setBounds(102,110,200,80);
		current_count.setBounds(230,80,200,40);
		current_shiny_chance.setBounds(230,120,200,40);
		
		add1.addActionListener(this);
		add3.addActionListener(this);
		add5.addActionListener(this);
		setButtonFont(add1);
		setButtonFont(add3);
		setButtonFont(add5);
		
		setJMenuBar(menuBar);
		
		options.setText("Options");
		menuBar.add(options);
		
		reset.setText("Reset");
		reset.setActionCommand("reset");
		options.add(reset);
		set_count_to.setText("Set Count To");
		set_count_to.setActionCommand("set_count_to");
		options.add(set_count_to);
		set_shiny_chance.setText("Set Shiny Chance To");
		set_shiny_chance.setActionCommand("set_shiny_chance");
		options.add(set_shiny_chance);
		options.addSeparator();
		exit.setText("Exit");
		exit.setActionCommand("exit");
		options.add(exit);
		
		reset.addActionListener(this);
		set_count_to.addActionListener(this);
		set_shiny_chance.addActionListener(this);
		exit.addActionListener(this);
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("./ShinyCounter.png"));
		    this.setIconImage(img);
		} catch (IOException e) {
		}
		
	}
	
	public static void setButtonFont(JButton l) {
		
		l.setFont(new Font(l.getFont().getName(),
				Font.BOLD,l.getFont().getSize()+2));
		
	}
	
	public static void setLabelFont(JLabel l) {
		
		l.setFont(new Font(l.getFont().getName(),
				Font.PLAIN,l.getFont().getSize()+5));
		
	}
	
	public static void setCountFont(JLabel l) {
		
		l.setFont(new Font(l.getFont().getName(),
				Font.BOLD,l.getFont().getSize()+5));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String name = e.getActionCommand();
		
		switch(name) {
		case "+1":
			count += 1;
			updateLabels();
			break;
		case "+3":
			count += 3;
			updateLabels();
			break;
		case "+5":
			count += 5;
			updateLabels();
			break;
		case "reset":
			count = 0;
			updateLabels();
			break;
		case "set_count_to":
			this.setCountPopup();
			break;
		case "set_shiny_chance":
			this.setShinyChancePopup();
			break;
		case "exit":
			System.exit(0);
			break;
		}
		
	}
	
	public void setCountPopup() {
		
		String ret = JOptionPane.showInputDialog(this, "",
					"Set count to:", JOptionPane.PLAIN_MESSAGE);
		
		if(ret != null) {
			if(isNumeric(ret)) {
				count = Integer.parseInt(ret);
				updateLabels();
			} else {
				JOptionPane.showMessageDialog(this, "Please enter a valid number between 0 and 2147483647.",
						"Error", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
	}
	
	public void setShinyChancePopup() {

		String ret = JOptionPane.showInputDialog(this,
				"Current Shiny Chance = 1 in " + Integer.toString(shinyChance),
				"Set Shiny Chance To 1 in:", JOptionPane.PLAIN_MESSAGE);
		
		if(ret != null) {
			if(isNumeric(ret)) {
				int newShinyChance = Integer.parseInt(ret);
				if(newShinyChance > 0 && newShinyChance < Math.pow(2,30)) {
					shinyChance = newShinyChance;
					settings.changeSetting("shiny_chance", Integer.toString(shinyChance));
					updateLabels();
					JOptionPane.showMessageDialog(this,
						"Shiny Chance succesfully set to 1 in " + Integer.toString(newShinyChance),
						"Shiny Chance", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Please enter a valid number between 1 and 1073741823.",
						"Error", JOptionPane.PLAIN_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Please enter a valid number between 1 and 1073741823.",
						"Error", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		
	}
	
	public static boolean isNumeric(String strNum) {
		
	    try {
	        int num = Integer.parseInt(strNum);
		    if(num >= 0) {
			    return true;
		    } else {
		    	return false;
		    }
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    
	}
	
	public static void updateLabels() {
		
		current_count.setText(Integer.toString(count));
		updateChances();
		settings.changeSetting("count", Integer.toString(count));
		write();
		
	}
	
	public static void updateChances() {
		
		double chance = 1-Math.pow((1.0-1.0/shinyChance),count);
		String chance_rounded = Double.toString(chance*100);
		String chance_str = "";
		if(chance_rounded.length() >= roundDigits+2) {
			chance_str = chance_rounded.substring(0,roundDigits+2);
		} else {
			chance_str = chance_rounded;
		}
		
		current_shiny_chance.setText(chance_str + "%");
		
	}
	

	public static void load() {
		
		try {
			File settings_file = new File("./settings.txt");
			if (settings_file.createNewFile()) {
				FileWriter w = new FileWriter("./settings.txt");
				w.write(settings.toString());
				w.close();
			} else {
				Scanner read_settings = new Scanner(settings_file);
				String settings_to_load = "";
				while(read_settings.hasNextLine()) {
					settings_to_load += read_settings.nextLine();
					settings_to_load += System.lineSeparator();
				}
				settings_to_load = settings_to_load.substring(0, settings_to_load.length() - System.lineSeparator().length());
				read_settings.close();
				settings = new Settings(settings_to_load);
			}
		} catch (IOException e) {
			System.out.println("An IO error occurred.");
			e.printStackTrace();
		}
		
	}
	
	public static void write() {
		
		try {
			FileWriter w = new FileWriter("./settings.txt");
			w.write(settings.toString());
			w.close();
		} catch (IOException e) {
			System.out.println("An IO error occurred.");
			e.printStackTrace();
		}
		
	}

}
