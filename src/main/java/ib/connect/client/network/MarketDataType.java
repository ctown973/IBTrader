/* Copyright (C) 2013 Interactive Brokers LLC. All rights reserved.  This code is subject to the terms
 * and conditions of the IB API Non-Commercial License or the IB API Commercial License, as applicable. */

package ib.connect.client.network;

public class MarketDataType {
    // constants - market data types
    public static final int REALTIME   = 1;
    public static final int FROZEN     = 2;
    public static final int DELAYED    = 3;
    public static final int DELAYED_FROZEN = 4;

    public static String getField( int marketDataType) {
        switch( marketDataType) {
            case REALTIME:                    return "Real-Time";
            case FROZEN:                      return "Frozen";
            case DELAYED:                     return "Delayed";
            case DELAYED_FROZEN:              return "Delayed-Frozen";

            default:                          return "Unknown";
        }
    }

    public static String[] getFields(){
    	int totalFields = MarketDataType.class.getFields().length;
    	String [] fields = new String[totalFields];
    	for (int i = 0; i < totalFields; i++){
    		fields[i] = MarketDataType.getField(i + 1);
    	}
    	return fields;
    }
}
