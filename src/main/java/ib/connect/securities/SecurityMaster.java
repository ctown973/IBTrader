package ib.connect.securities;

import java.util.HashMap;
import java.util.Map;

import com.ib.client.Contract;

public class SecurityMaster {
	
	private Map<String, SecurityDefinition> securityMap = new HashMap<String, SecurityDefinition>();

	private static SecurityMaster securityMaster;
	
	private static Object lock = new Object();
	
	 enum SecurityType {
		STOCK("STO"),
		OPTION("OPT");
		 
		 private String name;
		
		SecurityType(String name) {
			this.name = name;
		}
		
		public String getName( ) {
			return this.name();
		}
	}
	 

	 
	 public static SecurityMaster getSecurityMaster() {
		 if (securityMaster == null) {
			 synchronized(lock) {
				 if (securityMaster == null) {
					 System.out.println("creating security master");
					 securityMaster = new SecurityMaster();
				 }
			 }
		 }
		 
		 return securityMaster;
	 }
	 
	 private SecurityMaster() {
		 System.out.println("creating blank security! ");
		 securityMap.put("aaa", new SecurityDefinition());
	 }
	 
	 public static SecurityDefinition getEmptySecurity() { 
		 SecurityDefinition def = getSecurityMaster().getSecurityDefinition("aaa");
		 System.out.println("got definition: "+def);
		 return def;
	 }
	 
	 public SecurityDefinition getSecurityDefinition(String securityId) {
		 System.out.println("getting security definition for: "+securityId);
		 if (!securityMap.containsKey(securityId)) {
			 return addSecurityDefinition(securityId, securityId, SecurityType.STOCK);
		 }
		 return securityMap.get(securityId);
	 }
	 
	 public SecurityDefinition addSecurityDefinition(String securityId, String symbol, SecurityType type) {
		 if (!securityMap.containsKey(securityId)) {
			 SecurityDefinition definition = new SecurityDefinition();
			 definition.setSecurityID(securityId);
			 definition.setMinSize(1);
			 definition.setExchangeSecurityID(securityId);
			 definition.setSymbol(symbol);
			 definition.setType(type);
			 definition.buildContract();
			 securityMap.put(securityId, definition);
		 }
		 
		 return securityMap.get(securityId);
	 }
	 
	 public void setSecurityContract(String securityId, Contract contract) throws SecurityNotFoundException {
		 SecurityDefinition definition = securityMap.get(securityId);
		 if (definition != null) {
			 definition.setContract(contract);
		 } 
		 throw new SecurityNotFoundException("Security not found for "+securityId);
	 }
	
}
