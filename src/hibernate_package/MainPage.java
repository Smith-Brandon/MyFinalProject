package hibernate_package;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.*;

public class MainPage implements Runnable {
	public static Float divYield = null;
	public static int shares = 0;
	public static Float avgYield = null;
	public static Float price = null;
	public static String result;
	public static TreeMap<Integer, String> EntryMap;
	public MainPage() {
		
		// Create JFrame
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new GridLayout(7,2));

		// Set fields
		JLabel nameL = new JLabel("Stock Name: ");
		JTextField nameIn = new JTextField();
		JLabel symL = new JLabel("Stock Symbol: ");
		JTextField symbolIn = new JTextField();
		JLabel priceL = new JLabel("Stock Price: ");
		JTextField priceIn = new JTextField();
		JLabel avgL = new JLabel("Stock Average Yield: ");
		JTextField avgIn = new JTextField();
		JLabel divL = new JLabel("Stock Dividend Yield: ");
		JTextField divIn = new JTextField();
		JLabel sharL = new JLabel("Shares Purchased: ");
		JTextField sharIn = new JTextField();
		JButton submit = new JButton("Submit");

		// Format size of fields
		nameIn.setColumns(10);
		symbolIn.setColumns(10);
		priceIn.setColumns(10);
		avgIn.setColumns(10);
		divIn.setColumns(10);
		sharIn.setColumns(10);

		// Add submit action listener
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					// get information from fields
					String name = nameIn.getText();
					String symbol = symbolIn.getText();
					
					// Using a temp name for variables that need to be parsed
					String priceSt = priceIn.getText();
					String avgyieldSt = avgIn.getText();
					String divSt = divIn.getText();
					String shareSt = sharIn.getText();
					
					if(name.equals("") || name.equals("Please enter valid input!")) {
						nameIn.setText("Please enter valid input!");
						//nameIn.setForeground(Color.RED);
						return;
					} 
					else if(symbol.equals("") || symbol.equals("Please enter valid input!")) {
						symbolIn.setText("Please enter valid input!");
						//symbolIn.setForeground(Color.RED);
						return;
					} else if(priceSt.equals("") || priceSt.contains("[a-zA-Z]+")) {
						priceIn.setText("Please enter valid input!");
						//priceIn.setForeground(Color.RED);
						return;
					} else if(avgyieldSt.equals("")) {
						avgIn.setText("Please enter valid input!");
						//avgIn.setForeground(Color.RED);
						return;
					} else if(divSt.equals("")) {
						divIn.setText("Please enter valid input!");
						//divIn.setForeground(Color.RED);
						return;
					}  else if(shareSt.equals("")) {
						sharIn.setText("Please enter valid input!");
						//sharIn.setForeground(Color.RED);
						return;
					}
					
					// Parse information if necessary
					try {
					price = Float.parseFloat(priceSt);
					avgYield = Float.parseFloat(avgyieldSt);
					divYield = Float.parseFloat(divSt);
					shares = Integer.parseInt(shareSt);
					
					} catch(Exception p) {
						System.out.println("Invalid Input!!");
					}
					
					try {
					//Send to Hibernate Method to add this entry to the database
					HibernateMethods.setStocks(name, symbol, price, avgYield, divYield, shares);
					System.out.println("New entry has been successfully added to the database.");
					} catch(Exception h) {
						System.out.println("Entry was not added to the database");
					}
					
					// Add Collection Type ArrayList to be used in Buffered Writer
					ArrayList<String> list = new ArrayList<String>();
					list.add(0, name);
					list.add(1, symbol);
					list.add(2, priceSt);
					list.add(3, avgyieldSt);
					list.add(4, divSt);
					list.add(5, shareSt);
					
					// Add a entry to be appended to the clean.txt file
					BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/mormo.000/eclipse-workspace/MyProject/src/hibernate_package/savedJSON/clean.txt", true)  );  
					writer.newLine();   //Add new line
					writer.write(list.get(0));
					writer.newLine();   //Add new line
					writer.write(list.get(1));
					writer.newLine();   //Add new line
					writer.write(list.get(2));
					writer.newLine();   //Add new line
					writer.write(list.get(3));
					writer.newLine();   //Add new line
					writer.write(list.get(4));
					writer.newLine();   //Add new line
					writer.write(list.get(5));
					writer.newLine();   //Add new line
					writer.write(getDiv(price, divYield, shares).toString());
				    writer.close();
					
					//ArrayList to object
					JsonEntry jEntry = new JsonEntry();
					jEntry.setName(name);
					jEntry.setSymbol(symbol);
					jEntry.setPrice(price);
					jEntry.setAvgY(avgYield);
					jEntry.setDivY(divYield);
					jEntry.setShares(shares);
					result = Json.EntryToJSON(jEntry);
					System.out.println("New entry has been successfully added to the JSON file.");
					
					//After new entry is submitted exit frame
					frame.setVisible(false);
					Start.sframe.setVisible(true);
					frame.dispose();

				}
				catch (Exception q) {
					System.out.println("An error has occured within the try catch statement of the action handler."); 
				}
			}
		});		

		// Add elements to JFrame
		frame.getContentPane().add(nameL);
		frame.getContentPane().add(nameIn);
		frame.getContentPane().add(symL);
		frame.getContentPane().add(symbolIn);
		frame.getContentPane().add(priceL);
		frame.getContentPane().add(priceIn);
		frame.getContentPane().add(avgL);
		frame.getContentPane().add(avgIn);
		frame.getContentPane().add(divL);
		frame.getContentPane().add(divIn);
		frame.getContentPane().add(sharL);
		frame.getContentPane().add(sharIn);
		frame.getContentPane().add(submit);

		// Make adjustments
		frame.setSize(600, 150);
		frame.setVisible(true);
		frame.setTitle("Add A New Investment");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		Start.sframe.setVisible(true);
	}

	@Override
	public void run() {

	}
	// Add getDiv calculation 
	// Used by JUNIT
	public static Float getDiv(Float price, Float divY, int shares) {
		Float principal = (price * shares);
		Float divR = (principal * divY) / 100;
		
		return divR;
	}
}
