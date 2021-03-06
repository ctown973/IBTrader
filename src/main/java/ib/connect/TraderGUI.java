package ib.connect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.SwingUtilities;

import ib.connect.gui.TraderPanel;
import ib.connect.marketdata.MarketDataController;

public class TraderGUI implements OrderControllerInterface {

	
	private MarketDataController mdController;
	private ApiController orderController;
	
	public TraderGUI(Properties properties) {
	
		orderController = new ApiController(properties, mdController, this);
		mdController = new MarketDataController(orderController);

	}
	
	public void start() {
		orderController.start();
	}
	
	public void initializeGUI() {
		SwingUtilities.invokeLater(()-> {
			TraderPanel panel = new TraderPanel(mdController, orderController);
			panel.initialize();
		});
		
	}
	
	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.load(TraderGUI.class.getClassLoader().getResourceAsStream("ib.properties"));
			TraderGUI controller = new TraderGUI(props);
			controller.start();
			controller.initializeGUI();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
