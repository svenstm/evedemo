package com.askcs.evedemo.agents;

import com.almende.eve.agent.Agent;
import com.almende.eve.rpc.annotation.Name;

public class PersonalAgent extends Agent {

	
	public void init(@Name("name") String name) {
		setName(name);
	}
	
	public String getName() {
		return getState().get("name", String.class);
	}
	
	public void setName(String name) {
		getState().put("name", name);
	}
}
