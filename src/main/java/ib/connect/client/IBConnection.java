package ib.connect.client;

import java.io.IOException;

import com.ib.client.EClientSocket;
import com.ib.client.EJavaSignal;
import com.ib.client.EReader;
import com.ib.client.EReaderSignal;
import com.ib.client.EWrapper;

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
					while (IBConnection.this.isConnected()) {
						signal.waitForSignal();
						try {
							reader.processMsgs();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			
			t.start();
		}
	}
}
