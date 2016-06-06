package com.rsm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.rsm.algorithms.GeneticAlgorithm;
import com.rsm.algorithms.GreedyAlgorithm;
import com.rsm.algorithms.RandomAlgorithm;

import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JProgressBar;

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
		frame.setBounds(100, 100, 783, 382);
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
		panel_2.setBounds(26, 133, 299, 114);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton btnSitPeople = new JButton("Sit people by algorithm:");
		btnSitPeople.setBounds(0, 0, 299, 23);
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

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(77, 107, 46, 14);
		panel_2.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(424, 133, 177, 98);
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

		JLabel lblGeneticAlgorithm = new JLabel("Genetic Algorithm:");
		lblGeneticAlgorithm.setBounds(0, 75, 95, 14);
		panel_1.add(lblGeneticAlgorithm);

		JLabel lbGeneticAlgorithmGoalValue = new JLabel("-");
		lbGeneticAlgorithmGoalValue.setBounds(105, 75, 46, 14);
		panel_1.add(lbGeneticAlgorithmGoalValue);

		JButton btnChooseSatisfactionMatrix = new JButton("Choose satisfaction matrix file");
		btnChooseSatisfactionMatrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(
						"D:/" + System.getProperty("file.separator") + "programowanie" +System.getProperty("file.separator") + "java" +System.getProperty("file.separator") +"rsm"); // default
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

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Genetic Algorithm settings",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(32, 258, 211, 64);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lbPopulationSize = new JLabel("Population size");
		lbPopulationSize.setBounds(6, 19, 93, 14);
		panel_3.add(lbPopulationSize);

		JLabel lbGenerationsNumber = new JLabel("Generations Number");
		lbGenerationsNumber.setBounds(6, 43, 110, 14);
		panel_3.add(lbGenerationsNumber);

		JSpinner spinnerPopulation = new JSpinner();
		spinnerPopulation.setModel(new SpinnerNumberModel(new Integer(50), new Integer(1), null, new Integer(10)));
		spinnerPopulation.setBounds(126, 16, 79, 17);
		panel_3.add(spinnerPopulation);

		JSpinner spinnerGenerationsNumber = new JSpinner();
		spinnerGenerationsNumber
				.setModel(new SpinnerNumberModel(new Integer(50), new Integer(1), null, new Integer(10)));
		spinnerGenerationsNumber.setBounds(126, 40, 79, 17);
		panel_3.add(spinnerGenerationsNumber);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(259, 284, 146, 14);
		frame.getContentPane().add(progressBar);

		btnSitPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				numberOfTables = (Integer) spinnerTableAmount.getValue();
				tableCapacity = (Integer) spinnerTableCapacity.getValue();

				String msg = controller.checkAreTablesDataValid(numberOfPersons, numberOfTables, tableCapacity);
				if (!msg.equals("Ok!")) {
					JOptionPane.showMessageDialog(btnSitPeople, msg);
				}

				else {
					f.setNumberOfTables(numberOfTables);
					f.setTableCapacity(tableCapacity);
					f.addTablesToFacade();

					if (btnGreedyAlgorithm.isSelected()) {						
						Algorithms algorithm = new Algorithms(f);
						double d = algorithm.perform(new GreedyAlgorithm(f));
						double dr = Math.round(d * 100.0) / 100.0;

						lbGreedyGoalFunValue.setText(String.valueOf(dr));
					}
					if (btnRandomAlgorithm.isSelected()) {						
						Algorithms algorithm = new Algorithms(f);
						double d = algorithm.perform(new RandomAlgorithm(f));
						double dr = Math.round(d * 100.0) / 100.0;

						lbRandomGoalFunValue.setText(String.valueOf(dr));
					}
					if (btnGeneticAlgorithm.isSelected()) {						
						Algorithms algorithm = new Algorithms(f);
						GeneticAlgorithm ga = new GeneticAlgorithm();
						ArrayList<Facade> facades = ga.getPopulation();
						int numberOfPopulaiton = (Integer) spinnerPopulation.getValue();
						int numberOfGenerations = (Integer) spinnerGenerationsNumber.getValue();
						
										
						
						for(int i=0;i<numberOfPopulaiton;++i)
						{
							Facade ff = new Facade(f.satisfactionMatrix.matrix,tableCapacity,numberOfTables);
							Algorithms alg = new Algorithms(ff);
							alg.perform(new RandomAlgorithm(ff));
							facades.add(ff);
						}
						
						for(int i=0;i<numberOfGenerations;++i){
							algorithm.perform(ga);	
							
							
							//progressBar.setValue((int)((((double)i)/numberOfGenerations)*100.0));
							
						}
						double dr = Math.round(ga.getBestSet().goalFunction() * 100.0) / 100.0;

						lbGeneticAlgorithmGoalValue.setText(String.valueOf(dr));
					}
				}

			}
		});

	}
}
