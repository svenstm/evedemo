package com.askcs.evedemo.agents;

import com.almende.eve.agent.Agent;
import com.almende.eve.rpc.annotation.Access;
import com.almende.eve.rpc.annotation.AccessType;
import com.almende.eve.rpc.annotation.Name;

@Access(AccessType.PUBLIC)
public class HelloWorldAgent extends Agent {

	@Override
	protected void onCreate() {
		getState().put("counter", 0);
	}
	
	public String say() {
		return "Hello World!";
	}
	
	public int count() {
		int counter = getState().get("counter", Integer.class);
		counter++;
		getState().put("counter", counter);
		
		return counter;
	}
	
	public void startCounter() {
		
	}
	
	public int add(@Name("number") int number) {
		int counter = getState().get("counter", Integer.class);
		counter+=number;
		getState().put("counter", counter);
		
		return counter;
	}
}
