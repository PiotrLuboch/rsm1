package com.rsm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class GUI {

	private JFrame frame;
	Controller controller = new Controller();

	Facade f;
	int numberOfTables;
	int tableCapacity;
	int numberOfPersons;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SwingUtilities.updateComponentTreeUI(frame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(26, 65, 184, 41);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNumberOfTables = new JLabel("Number of tables:");
		lblNumberOfTables.setBounds(0, 2, 95, 14);
		panel.add(lblNumberOfTables);

		JLabel lblNumber = new JLabel("Table capacity:");
		lblNumber.setBounds(0, 27, 79, 14);
		panel.add(lblNumber);

		JSpinner spinnerTableAmount = new JSpinner();
		spinnerTableAmount.setBounds(105, 0, 79, 17);
		panel.add(spinnerTableAmount);
		spinnerTableAmount.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));

		JSpinner spinnerTableCapacity = new JSpinner();
		spinnerTableCapacity.setBounds(105, 24, 79, 17);
		panel.add(spinnerTableCapacity);
		spinnerTableCapacity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(26, 133, 211, 105);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton btnSitPeople = new JButton("Sit people by algorithm:");
		btnSitPeople.setBounds(0, 0, 211, 23);
		panel_2.add(btnSitPeople);

		JRadioButton btnRandomAlgorithm = new JRadioButton("Random");
		btnRandomAlgorithm.setBounds(0, 30, 109, 23);
		panel_2.add(btnRandomAlgorithm);

		JRadioButton btnGreedyAlgorithm = new JRadioButton("Greedy");
		btnGreedyAlgorithm.setBounds(0, 56, 109, 23);
		panel_2.add(btnGreedyAlgorithm);

		JRadioButton btnGeneticAlgorithm = new JRadioButton("Genetic");
		btnGeneticAlgorithm.setBounds(0, 82, 109, 23);
		panel_2.add(btnGeneticAlgorithm);

		ButtonGroup group = new ButtonGroup();
		group.add(btnRandomAlgorithm);
		group.add(btnGreedyAlgorithm);
		group.add(btnGeneticAlgorithm);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(247, 134, 177, 64);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblRandom = new JLabel("Random:");
		lblRandom.setBounds(0, 25, 46, 14);
		panel_1.add(lblRandom);

		JLabel lblGreedyAlgorithm = new JLabel("Greedy Algorithm:");
		lblGreedyAlgorithm.setBounds(0, 50, 95, 14);
		panel_1.add(lblGreedyAlgorithm);

		JLabel lbRandomGoalFunValue = new JLabel("-");
		lbRandomGoalFunValue.setBounds(106, 25, 46, 14);
		panel_1.add(lbRandomGoalFunValue);

		JLabel lbGuestsNumber = new JLabel("-");
		lbGuestsNumber.setBounds(359, 15, 46, 14);
		frame.getContentPane().add(lbGuestsNumber);

		JLabel lbGreedyGoalFunValue = new JLabel("-");
		lbGreedyGoalFunValue.setBounds(105, 50, 46, 14);
		panel_1.add(lbGreedyGoalFunValue);

		JLabel lblGoalFunctionValue = new JLabel("Goal Function Value:");
		lblGoalFunctionValue.setBounds(0, 0, 152, 14);
		panel_1.add(lblGoalFunctionValue);
		lblGoalFunctionValue.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JButton btnChooseSatisfactionMatrix = new JButton("Choose satisfaction matrix file");
		btnChooseSatisfactionMatrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(
						System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"); // default
																												// catalog
				chooser.showSaveDialog(null);
				try {
					String path = chooser.getSelectedFile().getAbsolutePath();
					// String filename=chooser.getSelectedFile().getName();

					CSVFileReader fr = new CSVFileReader(path);
					ArrayList<ArrayList<Double>> tab = fr.readFile(",");
					f = new Facade(tab);
					numberOfPersons = f.getNumberOfGuests();

					lbGuestsNumber.setText(String.valueOf(f.getNumberOfGuests()));
					// JOptionPane.showMessageDialog(btnChooseSatisfactionMatrix,
					// f.getNumberofGuests().toString());
				} catch (Exception exception) {
					// exception.printStackTrace();
				}
			}
		});
		btnChooseSatisfactionMatrix.setBounds(26, 11, 211, 23);
		frame.getContentPane().add(btnChooseSatisfactionMatrix);

		JLabel lblPersonsNumber = new JLabel("Guestes number:");
		lblPersonsNumber.setBounds(263, 15, 88, 14);
		frame.getContentPane().add(lblPersonsNumber);

		btnSitPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				numberOfTables = (Integer) spinnerTableAmount.getValue();
				tableCapacity = (Integer) spinnerTableCapacity.getValue();

				String msg = controller.checkAreTablesDataValid(numberOfPersons, numberOfTables, tableCapacity);
				JOptionPane.showMessageDialog(btnSitPeople, msg);

				if (msg.equals("Ok!")) {
					f.setNumberOfTables(numberOfTables);
					f.setTableCapacity(tableCapacity);
					f.addTablesToFacade();

					if (btnGreedyAlgorithm.isSelected()) {
						Algorithms algorithm = new Algorithms(f);
						double d = algorithm.compute();
						double dr = Math.round(d * 100.0) / 100.0;

						lbGreedyGoalFunValue.setText(String.valueOf(dr));
					}
				}

			}
		});

	}
}
