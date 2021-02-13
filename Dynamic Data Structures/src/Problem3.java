/*	Name: Andy Zou  
 *	Course Code: ICS4U
 *	Date: 11-28-19
 *	Introduction: This code allows a deck of up to 25 cards to be put in a order that
 *	if the specific steps are followed, will result in an ordered deck of cards
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class Problem3 {
	public static int number = -1; //to store the number entered in the button on JFrames
	public static ImageIcon[] images = new ImageIcon [25]; //to store all the images
	public static JFrame window = new JFrame("Problem 3"); //to initialize the JFrame
	public static Deque <Integer> dq = new LinkedList <Integer> (); //to store the reordered list
	public static void createWindow(){ //creates the JFrame, no return value because it is starting the JFrame, no parameters because there are no values needed
		JPanel panel = new JPanel(new FlowLayout(100, 50, 50)); //Panel to display all the pictures
		for (int i = 0; i < 25; i ++) { //to display all the pictures
			ImageIcon tempImg = new ImageIcon (i + 1 + ".gif");
			images[i] = new ImageIcon (tempImg.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH));
			JLabel label = new JLabel(images[i]);
			panel.add(label);
		}
		JButton b1 = new JButton("Enter Deck Size between 1 and 25"); //button to enter the deck size
		b1.setBounds(50, 630, 300, 50);
		b1.addActionListener(new java.awt.event.ActionListener() { //changes what the button does once clicked
			@Override
			public void actionPerformed(java.awt.event.ActionEvent act) { //will run commands once clicked
				String name = JOptionPane.showInputDialog(window,"Please enter a number", null); //displays the popup window
				while (name != null) { //check for correct input
					try {
						number = Integer.parseInt(name);
						if (number > 26)
							name = JOptionPane.showInputDialog(window,"Please enter a number", null);
					}
					catch (NumberFormatException e) {
						name = null;
						name = JOptionPane.showInputDialog(window,"Please enter a number", null);
					}
					panel.removeAll();
					for (int i = number; i > 0; i --) { //reorders the number of cards that were requested
						dq.addFirst(i); //adds one card to the front
						if (i != 1)
							dq.addFirst(dq.removeLast()); //brings the last card to the top
						System.out.println(dq);
					}
					Iterator <Integer> iter = dq.iterator();
					panel.removeAll();
					while (iter.hasNext()) {
						int i = iter.next();
						ImageIcon tempImg = new ImageIcon (i + ".gif");
						images[i] = new ImageIcon (tempImg.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH));
						JLabel label = new JLabel(images[i]);
						panel.add(label);
						window.pack();
						window.repaint();
					}
					dq.clear();
					if (number > -1)
						break;
				}
			}
		});
		JButton b2 = new JButton("Exit"); //exit button
		b2.setBounds(440, 630, 300, 50);
		b2.addActionListener(new java.awt.event.ActionListener() { //change what the button does once clicked
			@Override
			public void actionPerformed(java.awt.event.ActionEvent act) {
				System.exit(0);//exit the window
			}
		});
		panel.add(b1);
		panel.add(b2);
		window.setPreferredSize(new Dimension (800, 800)); //all the code below adds the buttons, panels and sets visibility to true
		window.setSize(800, 800);
		window.add(b1);
		window.add(b2);
		window.add(panel);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		createWindow();

	}

}


