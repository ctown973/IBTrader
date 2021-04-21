package ib.connect.client.network;

interface EClientMsgSink {
	void serverVersion(int version, String time);
	void redirect(String host);
}
