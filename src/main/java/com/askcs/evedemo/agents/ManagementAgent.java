package com.askcs.evedemo.agents;

import java.util.HashMap;
import java.util.Map;

import com.almende.eve.agent.Agent;
import com.almende.eve.agent.AgentHost;
import com.almende.eve.rpc.annotation.Access;
import com.almende.eve.rpc.annotation.AccessType;
import com.almende.eve.rpc.annotation.Name;
import com.almende.util.TypeUtil;

@Access(AccessType.PUBLIC)
public class ManagementAgent extends Agent {

	public Map<String, String> agents = null;
	
	@Override
	protected void onCreate() {
		setAgents(new HashMap<String, String>());
	}
	
	public void createPersonalAgent(@Name("name") String name) throws Exception {
		
		AgentHost host = getAgentHost();
		Map<String, String> agents = getAgents();
		if(agents.containsKey(name))
			throw new Exception("Agent already exists");
		
		PersonalAgent agent = host.createAgent(PersonalAgent.class, name);
		agent.init(name);
		
		agents.put(name, agent.getId());
		setAgents(agents);
	}
	
	public Map<String, String> getAgents() {
		return getState().get("agents", new TypeUtil<Map<String, String>>() {});
	}
	
	public void setAgents(Map<String, String> agents) {
		getState().put("agents", agents);
	}
}
