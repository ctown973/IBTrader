package ib.connect.client;

import java.io.IOException;

import ib.connect.client.network.EClientSocket;
import ib.connect.client.network.EJavaSignal;
import ib.connect.client.network.EMessage;
import ib.connect.client.network.EReader;
import ib.connect.client.network.EReaderSignal;
import ib.connect.client.network.EWrapper;

public class IBConnection extends EClientSocket {

	private static final int TEST_PORT = 7497;
	private static final int PRODUCTION_PORT = 7496;
	
	public IBConnection(EWrapper wrapper, EReaderSignal reader) {
		super(wrapper, reader);
		
	}
	
	public void start (boolean isTest) {

		eConnect("127.0.0.1", (isTest ? TEST_PORT : PRODUCTION_PORT), 1);
		IBMessageProcesser processor = new IBMessageProcesser();
		processor.startMsgProcessing();
	}
	
	class IBMessageProcesser {
		public IBMessageProcesser() {
			
		}
		
		public void startMsgProcessing() {
			final EReaderSignal signal = new EJavaSignal();
			final EReader reader = new EReader(IBConnection.this, signal);
			reader.start();
			
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("starting message processor thread...");
					while (isConnected()) {
						System.out.println("WAITING for signal ");
						signal.waitForSignal();
			
						try {
							reader.processMsgs();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("ENDING message processor thread...");
				}
			});
			
			t.start();
		}
	}

	@Override
	protected void sendMsg(EMessage msg) throws IOException {
		// TODO Auto-generated method stub
		super.sendMsg(msg);
		
		byte[] buf = msg.getRawData();
		System.out.println("sendMsg: "+new String(buf, 0 ,buf.length));
		
	}
	
	
	
}
