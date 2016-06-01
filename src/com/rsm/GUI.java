package com.rsm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class GUI {

	private JFrame frame;
	Controller controller = new Controller();

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
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
		panel_2.setBounds(26, 133, 184, 105);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnSitPeople = new JButton("Sit people by algorithm:");
		btnSitPeople.setBounds(0, 0, 166, 23);
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
		btnSitPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				numberOfTables = (Integer) spinnerTableAmount.getValue();
				tableCapacity = (Integer) spinnerTableCapacity.getValue();
				
				controller.checkAreTablesDataValid(numberOfPersons, numberOfTables, tableCapacity);
				
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(238, 133, 152, 64);
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
				
				JFileChooser chooser = new JFileChooser();	
				chooser.showSaveDialog(null);
				try{
				String path=chooser.getSelectedFile().getAbsolutePath();
				//String filename=chooser.getSelectedFile().getName();
				
				JOptionPane.showMessageDialog(btnChooseSatisfactionMatrix, path);
				}
				catch (Exception exception) {
					//exception.printStackTrace();
				}
			}
		});
		btnChooseSatisfactionMatrix.setBounds(26, 11, 211, 23);
		frame.getContentPane().add(btnChooseSatisfactionMatrix);
		
		JLabel lblPersonsNumber = new JLabel("Persons number:");
		lblPersonsNumber.setBounds(263, 15, 88, 14);
		frame.getContentPane().add(lblPersonsNumber);
	}
}
