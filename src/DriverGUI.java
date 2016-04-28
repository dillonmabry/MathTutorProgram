/** Dillon Mabry Student ID: 800854402
 * Math Tutor Program ITCS 3112
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DriverGUI {
	// frame fields
	private static JFrame frame;
	private static JPanel panel;
	private static JPanel numPanel;
	private static JLabel label;
	private static int FRAME_WIDTH = 700;
	private static int FRAME_HEIGHT = 700;
	private static String message = "";
	private final static JComboBox comboBox = new JComboBox();
	private final static JComboBox easyType = new JComboBox();
	private final static JComboBox modType = new JComboBox();
	private final static JComboBox diffType = new JComboBox();
	private final static JTextField textField = new JTextField(5);
	private final static List<String> history = new LinkedList<>();

	/**
	 * Constructor to set up GUI frame and alignments
	 */
	public DriverGUI() {
		frame = new JFrame("Java Tutor Program");
		panel = new JPanel(new BorderLayout());

		numPanel = new JPanel();
		message = "Java Tutor Program - Select Difficulty";
		label = new JLabel(message);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label, BorderLayout.CENTER);

		comboBox.addItem("Easy");
		comboBox.addItem("Moderate");
		comboBox.addItem("Difficult");
		easyType.addItem("Addition");

		easyType.addItem("Subtraction");
		easyType.addItem("Multiplication");
		easyType.addItem("Division");

		modType.addItem("Addition");
		modType.addItem("Subtraction");
		modType.addItem("Multiplication");
		modType.addItem("Division");

		diffType.addItem("Addition");
		diffType.addItem("Subtraction");
		diffType.addItem("Multiplication");
		diffType.addItem("Division");

		ActionListener comboListener = new ComboListener();
		comboBox.addActionListener(comboListener);
		comboBox.setPreferredSize(new Dimension(150, 30));

		easyType.setPreferredSize(new Dimension(150, 30));
		panel.add(comboBox, BorderLayout.NORTH);

		modType.setPreferredSize(new Dimension(150, 30));

		label.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
		frame.add(panel);
		frame.add(numPanel, BorderLayout.SOUTH);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Main method - Prints out recorded history at end
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String[] args) {
		DriverGUI init = new DriverGUI();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ListIterator<String> it = history.listIterator();
				while (it.hasNext()) {
					System.out.println(it.next());
				}
			}
		});
	}

	/**
	 * Combo class for listeners for choosing difficulty
	 * 
	 * @author Dillon
	 * 
	 */
	class ComboListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String item = (String) comboBox.getSelectedItem();
			if (item.equals("Easy")) {

				numPanel.removeAll();
				modType.removeAll();
				easyType.removeAll();
				diffType.removeAll();
				modType.setVisible(false);
				easyType.setVisible(true);
				diffType.setVisible(false);
				ActionListener easyListener = new EasyListener();
				easyType.addActionListener(easyListener);
				panel.setBackground(Color.CYAN);
				panel.add(easyType, BorderLayout.SOUTH);
				label.setText("Math Tutor Program - Easy");
			} else if (item.equals("Moderate")) {

				numPanel.removeAll();
				modType.removeAll();
				easyType.removeAll();
				diffType.removeAll();
				modType.setVisible(true);
				easyType.setVisible(false);
				diffType.setVisible(false);
				// TODO: Add moderate action listener
				ActionListener modListener = new modListener();
				modType.addActionListener(modListener);
				panel.setBackground(Color.ORANGE);
				panel.add(modType, BorderLayout.SOUTH);
				label.setText("Math Tutor Program - Moderate");
			} else if (item.equals("Difficult")) {
				numPanel.removeAll();
				modType.removeAll();
				easyType.removeAll();
				diffType.removeAll();
				modType.setVisible(false);
				easyType.setVisible(false);
				diffType.setVisible(true);
				// TODO: Add moderate action listener
				ActionListener diffListener = new diffListener();
				diffType.addActionListener(diffListener);
				// TODO: Add difficult action listener
				panel.setBackground(Color.RED);
				panel.add(diffType, BorderLayout.SOUTH);
				label.setText("Math Tutor Program - Difficult");
			}
			label.repaint();
			numPanel.repaint();
			frame.repaint();
			numPanel.setVisible(true);
			frame.setVisible(true);
		}
	}

	/**
	 * Easy Class for easy listeners
	 * 
	 * @author Dillon
	 *
	 */
	class EasyListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			numPanel.removeAll();
			String item = (String) easyType.getSelectedItem();
			if (item.equals("Addition")) {
				int randomNum = 1 + (int) (Math.random() * 50);
				int randomNum2 = 1 + (int) (Math.random() * 50);
				int solution = randomNum + randomNum2;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + randomNum);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("+");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + randomNum2);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Integer.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);

			} else if (item.equals("Subtraction")) {
				int randomNum = 1 + (int) (Math.random() * 50);
				int randomNum2 = 1 + (int) (Math.random() * 50);
				int smaller = Math.min(randomNum, randomNum2);
				int larger = Math.max(randomNum, randomNum2);
				int solution = smaller - larger;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + smaller);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("-");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + larger);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Integer.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);

			} else if (item.equals("Multiplication")) {
				int randomNum = 1 + (int) (Math.random() * 12);
				int randomNum2 = 1 + (int) (Math.random() * 12);
				int solution = randomNum * randomNum2;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + randomNum);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("x");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + randomNum2);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Integer.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);
			} else if (item.equals("Division")) {
				int randomNum = 1 + (int) (Math.random() * 50);
				int randomNum2 = 1 + (int) (Math.random() * 50);
				double smaller = Math.min(randomNum, randomNum2);
				double larger = Math.max(randomNum, randomNum2);
				double solution = larger / smaller;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + (int) larger);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("÷");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + (int) smaller);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Double.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);
			}
			label.repaint();
			numPanel.repaint();
			frame.repaint();
			numPanel.setVisible(true);
			frame.setVisible(true);
		}
	}

	/**
	 * Moderate class for moderate difficulty
	 * 
	 * @author Dillon
	 *
	 */
	class modListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			numPanel.removeAll();
			String item = (String) modType.getSelectedItem();
			if (item.equals("Addition")) {
				int randomNum = 10 + (int) (Math.random() * 100);
				int randomNum2 = 10 + (int) (Math.random() * 100);
				int solution = randomNum + randomNum2;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + randomNum);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("+");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + randomNum2);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Integer.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);

			} else if (item.equals("Subtraction")) {
				int randomNum = 10 + (int) (Math.random() * 100);
				int randomNum2 = 10 + (int) (Math.random() * 100);
				int smaller = Math.min(randomNum, randomNum2);
				int larger = Math.max(randomNum, randomNum2);
				int solution = smaller - larger;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + smaller);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("-");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + larger);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Integer.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);

			} else if (item.equals("Multiplication")) {
				int randomNum = 10 + (int) (Math.random() * 50);
				int randomNum2 = 10 + (int) (Math.random() * 50);
				int solution = randomNum * randomNum2;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + randomNum);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("x");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + randomNum2);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Integer.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);
			} else if (item.equals("Division")) {
				int randomNum = 10 + (int) (Math.random() * 50);
				int randomNum2 = 10 + (int) (Math.random() * 50);
				double smaller = Math.min(randomNum, randomNum2);
				double larger = Math.max(randomNum, randomNum2);
				double solution = larger / smaller;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + (int) larger);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("÷");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + (int) smaller);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Double.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);
			}
			label.repaint();
			numPanel.repaint();
			frame.repaint();
			numPanel.setVisible(true);
			frame.setVisible(true);
		}
	}

	/**
	 * Difficult class for difficult difficulty
	 * 
	 * @author Dillon
	 *
	 */
	class diffListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			numPanel.removeAll();
			String item = (String) diffType.getSelectedItem();
			if (item.equals("Addition")) {
				int randomNum = 100 + (int) (Math.random() * 1000);
				int randomNum2 = 100 + (int) (Math.random() * 1000);
				int solution = randomNum + randomNum2;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + randomNum);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("+");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + randomNum2);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Integer.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);

			} else if (item.equals("Subtraction")) {
				int randomNum = 100 + (int) (Math.random() * 1000);
				int randomNum2 = 100 + (int) (Math.random() * 1000);
				int smaller = Math.min(randomNum, randomNum2);
				int larger = Math.max(randomNum, randomNum2);
				int solution = smaller - larger;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + smaller);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("-");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + larger);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Integer.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);

			} else if (item.equals("Multiplication")) {
				int randomNum = 10 + (int) (Math.random() * 1000);
				int randomNum2 = 10 + (int) (Math.random() * 1000);
				int solution = randomNum * randomNum2;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + randomNum);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("x");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + randomNum2);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Integer.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);
			} else if (item.equals("Division")) {
				int randomNum = 10 + (int) (Math.random() * 1000);
				int randomNum2 = 10 + (int) (Math.random() * 1000);
				double smaller = Math.min(randomNum, randomNum2);
				double larger = Math.max(randomNum, randomNum2);
				double solution = larger / smaller;
				System.out.println(solution);
				JLabel num1 = new JLabel("" + (int) larger);
				num1.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num1);

				JLabel operator = new JLabel("÷");
				operator.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(operator);

				JLabel num2 = new JLabel("" + (int) smaller);
				num2.setFont(new Font("Sans-Serif", Font.BOLD, 100));
				numPanel.add(num2);

				numPanel.add(textField);
				JButton button = new JButton("Submit");

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = textField.getText();
						try {
							if (Double.valueOf(input) == solution && input != "") {
								JOptionPane.showMessageDialog(null, "Correct!");
								history.add(LocalDateTime.now() + " Correct answer recorded: " + input);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect!");
								history.add(LocalDateTime.now() + " Incorrect answer recorded: " + input);
							}
						} catch (NumberFormatException ex) {
							System.out.println("Number Format exception! Please enter an integer value.");
							JOptionPane.showMessageDialog(null,
									"Number Format exception! Please enter an integer value.");
							return;
						}
					}
				});

				numPanel.add(button);
			}
			label.repaint();
			numPanel.repaint();
			frame.repaint();
			numPanel.setVisible(true);
			frame.setVisible(true);
		}
	} // end of diff class

} // end of class
