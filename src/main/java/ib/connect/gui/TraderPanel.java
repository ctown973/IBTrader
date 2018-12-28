package ib.connect.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;


import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.gui.WritableTableFormat;
import ca.odell.glazedlists.swing.DefaultEventTableModel;
import ib.connect.OrderAcceptor;
import ib.connect.OrderControllerInterface;
import ib.connect.gui.marketdata.MarketDataDetailedViewPanel;
import ib.connect.marketdata.SubscriptionInterface;
import ib.connect.order.Fill;

public class TraderPanel {

	
	private JFrame mainFrame; 
	private SubscriptionInterface subscriptionInterface;
	private OrderAcceptor orderAcceptor;
	
	private FillTable fillTable;
	private MarketDataDetailedViewPanel mdView;
	
	public TraderPanel(SubscriptionInterface subInterface, OrderAcceptor orderAcceptor) {
		
		this.subscriptionInterface = subInterface;
		this.orderAcceptor = orderAcceptor;
	}
	
	public void initialize() {
		mainFrame = new JFrame("Trader");
		mainFrame.setSize(450, 500);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {}			
			@Override
			public void windowIconified(WindowEvent arg0) {}			
			@Override
			public void windowDeiconified(WindowEvent arg0) {}		
			@Override
			public void windowDeactivated(WindowEvent arg0) {}	
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}	
			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(0);
			}	
			@Override
			public void windowActivated(WindowEvent arg0) {}
		});
		
		fillTable = new FillTable();
		fillTable.intitialize();
		
		mdView = new MarketDataDetailedViewPanel(subscriptionInterface);
		mdView.initialize();
		
		JMenu menu = new JMenu("Tools");
		JMenuItem fillTableItem = new JMenuItem("Fill Table");
		
		fillTableItem.addActionListener( (ActionEvent e)-> {	
				fillTable.setFrameVisible(true);		
		});
		menu.add(fillTableItem);
		
		JMenuItem marketDataTableItem = new JMenuItem("Market Data View");
		marketDataTableItem.addActionListener( (ActionEvent e)->{
			mdView.setFrameVisible();
		});
		menu.add(marketDataTableItem);
		
		JMenuBar bar = new JMenuBar();
		bar.add(menu);
		mainFrame.setJMenuBar(bar);
		mainFrame.setVisible(true);
	}
	
	private JFrame createGenericFrame(String name) {
		JFrame fillFrame = new JFrame(name);
		fillFrame.setSize(700, 300);
		fillFrame.setLayout(new BorderLayout());
		
		return fillFrame;
	}

	

	class FillTable extends JTable implements MouseListener {
		
		
		private DefaultEventTableModel<Fill> eventModel;
		private HashSet<String> processedKeys;
		private EventList<Fill> eventList;
		private RowFilter<DefaultEventTableModel<Fill>, Object> filter;
		
		private boolean enableAutoScroll = true;
		private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		
		private List<String> fillTableHeaders;
		private JPanel container;
		private JFrame frame;
		
		public FillTable() {
			processedKeys = new HashSet<String>();
			eventList = new BasicEventList<Fill>()	;
			fillTableHeaders = List.of("Symbol", "SecurityID", "Description", "Side", "Qty", "Price", "Time", "ClOrdID", "ExecID", "TradeType");
			
			eventModel = new DefaultEventTableModel<Fill>(eventList, new WritableTableFormat<Fill>() {

				@Override
				public int getColumnCount() {
					// TODO Auto-generated method stub
					return fillTableHeaders.size();
				}

				@Override
				public String getColumnName(int column) {
					return fillTableHeaders.get(column);
				}

				@Override
				public Object getColumnValue(Fill baseObject, int column) {
					// TODO Auto-generated method stub
					switch(column) {
					case 0:
						return baseObject.getSymbol();
					case 1:
						return baseObject.getSecurityID();
					case 2:
						return baseObject.getDescription();
					case 3:
						return baseObject.getSide();
					case 4:
						return baseObject.getSize();
					case 5:
						return baseObject.getDatetime();
					case 6:
						return baseObject.getClOrderID();
					case 7:
						return baseObject.getExecId();
					case 8: 
						return "N";
					default:
						return "";
					
						
						
					}
				}

				@Override
				public boolean isEditable(Fill baseObject, int column) {
					return false;
				}

				@Override
				public Fill setColumnValue(Fill baseObject, Object editedValue, int column) {
					return null;
				}				
			});
			
			setModel(eventModel);
		}
		
		public void intitialize() {
			frame = createGenericFrame("Fills");
			container = new JPanel();
			container.setLayout(new BorderLayout());
			JScrollPane pane = new JScrollPane(this);
			container.add(pane, BorderLayout.CENTER);
			frame.setLayout(new BorderLayout());
			frame.add(container, BorderLayout.CENTER);
		}
		
		public void setFrameVisible(boolean isVisible) {
			frame.setVisible(isVisible);
		}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
}
