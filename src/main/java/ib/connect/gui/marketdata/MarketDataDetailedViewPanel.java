package ib.connect.gui.marketdata;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ib.connect.marketdata.SubscriptionInterface;

public class MarketDataDetailedViewPanel {

	private JFrame frame;
	private SubscriptionInterface subscriptionInterface;
	public MarketDataDetailedViewPanel(SubscriptionInterface subInterface) {
		this.subscriptionInterface = subInterface;
	}
	
	public void initialize() {
		frame = new JFrame("Market Detailed View");
		frame.setSize(700,300);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS) );
		GridBagConstraints constraints = new GridBagConstraints(0,0,1,1,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 3, 3);
		
		JPanel marketDataTable = new JPanel();
		marketDataTable.setLayout(new GridBagLayout());
	}
}
