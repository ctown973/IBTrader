package ib.connect;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.DeltaNeutralContract;
import com.ib.client.EJavaSignal;
import com.ib.client.EReaderSignal;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.SoftDollarTier;
import com.ib.client.TagValue;
import com.ib.client.Types.WhatToShow;

import ib.connect.client.IBConnection;
import ib.connect.marketdata.MarketDataInterface;

public class ApiController implements EWrapper, OrderAcceptor {

	IBConnection connection;
	
	private MarketDataInterface mdInterface;
	private OrderControllerInterface orderInterface;
	
	static int marketDataReqId = 0;
	
	public ApiController(Properties properties, MarketDataInterface mdInterface, OrderControllerInterface orderControllerInterface) {
		EReaderSignal readerSignal = new EJavaSignal();
		connection = new IBConnection(this, readerSignal);
		this.mdInterface = mdInterface;
		this.orderInterface = orderControllerInterface;
	}
	
	public void start() {
		connection.start(true);
		
	}
	@Override
	public void tickPrice(int tickerId, int field, double price, int canAutoExecute) {
		System.out.println("tickPrice: "+tickerId+ " field: "+field+" price: "+price+ " canAutoExecute: "+canAutoExecute);
	}

	@Override
	public void tickSize(int tickerId, int field, int size) {
		System.out.println("tickSize: "+tickerId+ " field: "+field +" size: "+size);
	}

	@Override
	public void tickOptionComputation(int tickerId, int field, double impliedVol, double delta, double optPrice,
			double pvDividend, double gamma, double vega, double theta, double undPrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickGeneric(int tickerId, int tickType, double value) {
		System.out.println("tickGeneric: " +tickerId+ " tickType: "+tickType+ " value: "+value);
	}

	@Override
	public void tickString(int tickerId, int tickType, String value) {
		System.out.println("tickString: "+tickerId+" tickType: "+tickType+ " value: "+value);
	}

	@Override
	public void tickEFP(int tickerId, int tickType, double basisPoints, String formattedBasisPoints,
			double impliedFuture, int holdDays, String futureLastTradeDate, double dividendImpact,
			double dividendsToLastTradeDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void orderStatus(int orderId, String status, double filled, double remaining, double avgFillPrice,
			int permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {
		System.out.println("orderStatus: "+orderId+" status: "+status+ " filled: "+filled+ " remaining: "+remaining+ " avgFillPrice: "+avgFillPrice);
		System.out.println("\t permId: "+permId+ " parentId: "+parentId+ " lastFillPrice: "+lastFillPrice+" clientId: "+clientId+ " whyHeld: "+whyHeld);

	}

	@Override
	public void openOrder(int orderId, Contract contract, Order order, OrderState orderState) {
		System.out.println("openOrder: "+orderId+ " contract: "+contract.description()+ " order: "+order.toString()+ "\n\t orderState: "+orderState.toString());
	}

	@Override
	public void openOrderEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAccountValue(String key, String value, String currency, String accountName) {
		System.out.println("updateAccountValue: "+key+ " value: "+value+ " currency: "+currency+ " accountName: "+accountName );
	}

	@Override
	public void updatePortfolio(Contract contract, double position, double marketPrice, double marketValue,
			double averageCost, double unrealizedPNL, double realizedPNL, String accountName) {
		System.out.println("updatePortfolio: "+contract.toString()+ " position: "+position+ " marketPrice: "+ marketPrice+ " marketValue: "+marketValue);
	}

	@Override
	public void updateAccountTime(String timeStamp) {
		System.out.println("updateAccountTime: "+timeStamp);
	}

	@Override
	public void accountDownloadEnd(String accountName) {
		System.out.println("accountDownloadEnd: "+accountName);
	}

	@Override
	public void nextValidId(int orderId) {
		System.out.println("nextValidID: "+orderId);
	}

	@Override
	public void contractDetails(int reqId, ContractDetails contractDetails) {
		System.out.println("contractDetails: "+reqId+ " contractDetails: "+contractDetails.toString());
	}

	@Override
	public void bondContractDetails(int reqId, ContractDetails contractDetails) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contractDetailsEnd(int reqId) {
		System.out.println("contractDetailsEnd: "+reqId);
	}

	@Override
	public void execDetails(int reqId, Contract contract, Execution execution) {
		System.out.println("execDetails: "+reqId+ " contract: "+contract.toString()+ " execution: "+execution.toString());
	}

	@Override
	public void execDetailsEnd(int reqId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMktDepth(int tickerId, int position, int operation, int side, double price, int size) {
		System.out.println("updateMktDepth: "+tickerId+ " position: "+position+ " operation: "+operation+ " side: "+side+" price: "+price + " size: "+size);
	}

	@Override
	public void updateMktDepthL2(int tickerId, int position, String marketMaker, int operation, int side, double price,
			int size) {
		System.out.println("updateMktDepth: "+tickerId+ " position: "+position+ " operation: "+operation+ " side: "+side+" price: "+price + " size: "+size+ " marketMaker: "+marketMaker);
		
	}

	@Override
	public void updateNewsBulletin(int msgId, int msgType, String message, String origExchange) {
		// TODO Auto-generated method stub

	}

	@Override
	public void managedAccounts(String accountsList) {
		System.out.println("managedAccounts: "+accountsList);
	}

	@Override
	public void receiveFA(int faDataType, String xml) {
		// TODO Auto-generated method stub

	}

	@Override
	public void historicalData(int reqId, String date, double open, double high, double low, double close, int volume,
			int count, double WAP, boolean hasGaps) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scannerParameters(String xml) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scannerData(int reqId, int rank, ContractDetails contractDetails, String distance, String benchmark,
			String projection, String legsStr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scannerDataEnd(int reqId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void realtimeBar(int reqId, long time, double open, double high, double low, double close, long volume,
			double wap, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public void currentTime(long time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fundamentalData(int reqId, String data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deltaNeutralValidation(int reqId, DeltaNeutralContract underComp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickSnapshotEnd(int reqId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void marketDataType(int reqId, int marketDataType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commissionReport(CommissionReport commissionReport) {
		// TODO Auto-generated method stub

	}

	@Override
	public void position(String account, Contract contract, double pos, double avgCost) {
		System.out.println("position: "+account+ " contract: "+contract.toString()+ " pos: "+pos+ " avgCost: "+avgCost);
	}

	@Override
	public void positionEnd() {
		System.out.println("position end");
	}

	@Override
	public void accountSummary(int reqId, String account, String tag, String value, String currency) {
		System.out.println("account summarty: "+reqId+ " account: "+account+" tag: "+tag+" value: "+value+ " currency: "+currency);
	}

	@Override
	public void accountSummaryEnd(int reqId) {
		System.out.println("account summary end: "+reqId);
	}

	@Override
	public void verifyMessageAPI(String apiData) {
		System.out.println("verifyMessageAPI: "+apiData);
	}

	@Override
	public void verifyCompleted(boolean isSuccessful, String errorText) {
		System.out.println("verifyComplete: "+isSuccessful+ " errorText: "+errorText);
	}

	@Override
	public void verifyAndAuthMessageAPI(String apiData, String xyzChallange) {
		System.out.println("verifyAndAuthMessageAPI: "+apiData+ " xyzChallenge: "+xyzChallange);
	}

	@Override
	public void verifyAndAuthCompleted(boolean isSuccessful, String errorText) {
		System.out.println("verifyAndAuthenticateCompleted: "+isSuccessful+ " errorText: "+errorText);
	}

	@Override
	public void displayGroupList(int reqId, String groups) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayGroupUpdated(int reqId, String contractInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(Exception e) {
		e.printStackTrace();
	}

	@Override
	public void error(String str) {
		System.out.println("error: "+str);
	}

	@Override
	public void error(int id, int errorCode, String errorMsg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionClosed() {
		System.out.println("connection closed");
	}

	@Override
	public void connectAck() {
		System.out.println("connect ack");
	}

	@Override
	public void positionMulti(int reqId, String account, String modelCode, Contract contract, double pos,
			double avgCost) {
		// TODO Auto-generated method stub

	}

	@Override
	public void positionMultiEnd(int reqId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accountUpdateMulti(int reqId, String account, String modelCode, String key, String value,
			String currency) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accountUpdateMultiEnd(int reqId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void securityDefinitionOptionalParameter(int reqId, String exchange, int underlyingConId,
			String tradingClass, String multiplier, Set<String> expirations, Set<Double> strikes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void securityDefinitionOptionalParameterEnd(int reqId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDollarTiers(int reqId, SoftDollarTier[] tiers) {
		// TODO Auto-generated method stub

	}
	
	public void reqRealTimeBars(Contract contract, WhatToShow action, boolean rthOnly ) {
		int reqId = ++marketDataReqId;
		ArrayList<TagValue> realTimeBarsOption = new ArrayList<TagValue>();
		connection.reqRealTimeBars(reqId, contract, 0, action.toString(), rthOnly, realTimeBarsOption);
	}

	@Override
	public void sendOrder(Order order) {
		// TODO Auto-generated method stub
		
	}
	
	

}
