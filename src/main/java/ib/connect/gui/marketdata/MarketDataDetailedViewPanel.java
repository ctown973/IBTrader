package ib.connect.gui.marketdata;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ib.connect.OrderAcceptor;
import ib.connect.marketdata.SubscriptionInterface;
import ib.connect.securities.SecurityDefinition;
import ib.connect.securities.SecurityMaster;

public class MarketDataDetailedViewPanel {

	private JFrame frame;
	private SubscriptionInterface subscriptionInterface;
	private OrderAcceptor orderAcceptListener;
	public MarketDataDetailedViewPanel(SubscriptionInterface subInterface) {
		this.subscriptionInterface = subInterface;
	}
	
	public void initialize() {
		frame = new JFrame("Market Detailed View");
		frame.setSize(900,600);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS) );
		
		JPanel marketDataViewPanel = new JPanel();
		marketDataViewPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints =new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.BASELINE_LEADING, GridBagConstraints.BOTH, new Insets(5, 5,5, 5), 2, 2);
		for (int i = 1; i <= 12; i++) {
			MarketDataTable table = new MarketDataTable(orderAcceptListener, subscriptionInterface);
			SecurityDefinition definition = SecurityMaster.getEmptySecurity();
			System.out.println("definition: " +definition);
			table.initialize(SecurityMaster.getEmptySecurity());
			marketDataViewPanel.add(table.getPanel(), constraints);
			constraints.gridx +=1;
			System.out.println("Making market data table");
			if (i % 4 == 0) {
				constraints.gridy++;
				constraints.gridx = 0;
			}
		}
		frame.add(marketDataViewPanel);
		
//		setFrameVisible();
	}
	
	public void setFrameVisible() {
		frame.setVisible(!frame.isVisible());
	}
}
