package ib.connect.client.network;

import java.io.IOException;

public interface ETransport {
	void send(EMessage msg) throws IOException;
}
