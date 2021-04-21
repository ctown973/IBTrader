package ib.connect.client.network;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class PriceCondition extends ContractCondition {
	
	public static final OrderConditionType conditionType = OrderConditionType.Price;
	
	protected PriceCondition() { }
	
	private double m_price;
	private int m_triggerMethod;
	private static String[] mthdNames = new String[] { "default", "double bid/ask", "last", "double last", "bid/ask", "", "", "last of bid/ask", "mid-point" };

	@Override
	public String toString() {
		return toString(null);
	}

	public double price() {
		return m_price;
	}

	public void price(double m_price) {
		this.m_price = m_price;
	}

	@Override
	public String toString(ContractLookuper lookuper) {
		return strTriggerMethod() + " " + super.toString(lookuper);
	}

	public int triggerMethod() {
		return m_triggerMethod;
	}
	
	String strTriggerMethod() {		
		return mthdNames[triggerMethod()];
	}

	public void triggerMethod(int m_triggerMethod) {
		this.m_triggerMethod = m_triggerMethod;
	}

	@Override
	protected String valueToString() {
		return "" + m_price;
	}

	@Override
	protected void valueFromString(String v) {
		m_price = Double.parseDouble(v);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		super.readExternal(in);
		
		m_triggerMethod = in.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		out.writeInt(m_triggerMethod);
	}
	
}