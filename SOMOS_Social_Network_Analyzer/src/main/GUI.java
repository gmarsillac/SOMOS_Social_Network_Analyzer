package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI {
	/**
     * Create the GUI and show it. 
     * Shows file selection and calls CSV reader on file
     * Shows analysis methods selection and calls those methods
     */
	 private  JButton chooseButton;
	 private  JFileChooser fc;
	 private  JFrame frame;
	 
	 private  JCheckBox densityButton, degreeButton, distanceMatrixButton, distanceButton, diameterButton, freemanButton;
	 private  CSVscanner fileIn;
	 private Graph graph;
	 private JTextField inputField;
	 private JPanel inputPanel;
	 
    private void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("SOMOS SNA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel mainLabel = new JLabel("Main Label");
        mainLabel.setText("Choose a CSV file to Analyze:");	
        mainLabel.setPreferredSize(new Dimension(300, 60));
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.getContentPane().add(mainLabel, BorderLayout.NORTH);  
        
        inputPanel = new JPanel(new GridLayout(0, 2, 40, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        JLabel inputLabel = new JLabel("Input Label");
        inputLabel.setText("Input number of nodes:");
        inputLabel.setHorizontalAlignment(JLabel.CENTER);
        inputPanel.add(inputLabel);
        
        //Make graph size input box
        inputField = new JTextField(5);
        inputField.setPreferredSize(new Dimension(10,10));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        
        inputPanel.add(inputField);
                
        //make choose button
        chooseButton = new JButton("Choose File");  
		frame.getContentPane().add(chooseButton, BorderLayout.SOUTH);
		//button action listener
		chooseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(frame);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
		                File file = fc.getSelectedFile();
		                int graphSize =  Integer.parseInt(inputField.getText());
		                fileIn = new CSVscanner(file, graphSize);
		                graph = fileIn.getGraph();
		                chooseMeasure(mainLabel);
				                
		            } else {
		                //TODO: error
		            }
			}          
		});
		
		//make file chooser
		fc  = new JFileChooser();
		
		//add file filter for csv files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File","csv");
		fc.setFileFilter(filter);
		 
		frame.add(inputPanel);
        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void runGUI() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private void chooseMeasure(JLabel mainLabel) {
    	mainLabel.setText("Choose calculations:");
    	frame.remove(inputPanel);
    	frame.remove(chooseButton);
    	
    	 //Create the check boxes.
        densityButton = new JCheckBox("Density");
        densityButton.setSelected(true);
 
        degreeButton = new JCheckBox("Average Degree");
        degreeButton.setSelected(true);
 
        distanceMatrixButton = new JCheckBox("Distance Matrix");
        distanceMatrixButton.setSelected(true);
 
        distanceButton = new JCheckBox("Average Geodesic Distance");
        distanceButton.setSelected(true);
        
        diameterButton = new JCheckBox("Average Geodesic Distance");
        diameterButton.setSelected(true);
        
        freemanButton = new JCheckBox("Freeman Centralization");
        freemanButton.setSelected(true);
 
        /*
        //Register a listener for the check boxes.
        chinButton.addItemListener(this);
        glassesButton.addItemListener(this);
        hairButton.addItemListener(this);
        teethButton.addItemListener(this);
    	*/
        
        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        //add checkboxes to frame
        checkPanel.add(densityButton);
        checkPanel.add(degreeButton);
        checkPanel.add(distanceMatrixButton);
        checkPanel.add(distanceButton);
        checkPanel.add(diameterButton);
        checkPanel.add(freemanButton);
    	
    	//make choose button
        JButton runButton = new JButton("Run");  
		checkPanel.add(runButton);
		//button action listener
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//dialog for file save location and name
				//make file chooser
				JFileChooser saveChooser  = new JFileChooser();
				saveChooser.setDialogTitle("Set output file:");
				
				//add file filter for txt files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT file","txt");
				saveChooser.setFileFilter(filter);
				
				//default file name
				saveChooser.setSelectedFile(new File("SNA_output.txt"));
				
				int returnVal = saveChooser.showSaveDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File fileToSave = saveChooser.getSelectedFile();	                
					try {
						PrintStream o = new PrintStream(fileToSave);
						System.setOut(o);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					boolean printMatrix = false;
					if (true) {
		                if(densityButton.isSelected()) {
		                	System.out.println("Density: "+ graph.densityCalc());
		                }
		                if(degreeButton.isSelected()) {
		                	System.out.println("Average Degree: "+ graph.averageDegreeCalc());
		                }
		                if(freemanButton.isSelected()) {
		                	System.out.println("Freeman Centralization: " + graph.freemanCentralization());
		                }
		                if(distanceMatrixButton.isSelected() || distanceButton.isSelected() || diameterButton.isSelected()) {
		                	DistanceCalculator dc = new DistanceCalculator(graph, distanceMatrixButton.isSelected());
		                	if(distanceButton.isSelected()) {
		                		System.out.println("Average Geodesic Distance: " + dc.getDistance());
		                	}
		                	if(diameterButton.isSelected()) {
		                		System.out.println("Diameter: " + dc.getDiameter());
		                	}
		                }
		                /*if(distanceMatrixButton.isSelected()) {
		                	//System.out.println("Average Density: "+ graph.densityCalc());
		                	printMatrix = true;
		                	if(distanceButton.isSelected()) {
			                	DistanceCalculator dc = new DistanceCalculator(graph, printMatrix, true);
			                }
		                	else {
		                		DistanceCalculator dc = new DistanceCalculator(graph, printMatrix, false);
		                	}
		                }
		                else if(distanceButton.isSelected()) {
		                	DistanceCalculator dc = new DistanceCalculator(graph, printMatrix, true);
		                	
		                
		                }*/
		                
		            } else {
		                
		            }
				}
				frame.dispose();
			}          
		});
		
		frame.add(checkPanel);
		frame.pack();
        
    	frame.repaint();
		
    }
}

