package hibernate_package;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Start {
	public static JFrame sframe = new JFrame();
	
	public static void main(String[] args) {
		//Create ThreadPool
		ExecutorService myService = Executors.newFixedThreadPool(3);

		// Create JFrame		
		sframe.getContentPane().setLayout(new GridLayout(1, 2));

		//Create Buttons
		JButton newEntry = new JButton("Create New");
		JButton viewAll = new JButton("Print Entries In Console"); 

		// newEntry Action Listener
		viewAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ViewAll v1 = new ViewAll();
					myService.execute(v1);			
				}
				catch (Exception q) {
					// result.setText("Something went Wrong");
				}
			}
		});
		
		// viewAll Action Listener
		newEntry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MainPage m1 = new MainPage();
					myService.execute(m1);					
				}

				catch (Exception q) {
					// result.setText("Something went Wrong");
				}
			}
		});

		
		// Add buttons to frame
		sframe.getContentPane().add(newEntry);
		sframe.getContentPane().add(viewAll);

		// Make adjustments
		sframe.setSize(600, 150);
		sframe.setVisible(true);
		sframe.setTitle("Start Here");
		sframe.setResizable(false);
		sframe.setAlwaysOnTop(false);
		sframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myService.shutdown();
	}
}