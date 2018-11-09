package ib.connect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TraderGUI {

	
	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.load(TraderGUI.class.getClassLoader().getResourceAsStream("ib.properties"));
			OrderController controller = new OrderController(props);
			controller.start();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
