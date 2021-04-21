package ib.connect.client.network;

import java.util.ArrayList;

public interface ContractLookuper {
	ArrayList<ContractDetails> lookupContract(Contract contract);
}