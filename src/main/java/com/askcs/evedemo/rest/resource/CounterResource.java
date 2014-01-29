package com.askcs.evedemo.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.almende.eve.agent.AgentHost;
import com.askcs.evedemo.agents.HelloWorldAgent;
import com.askcs.evedemo.agents.ManagementAgent;

@Path("/")
public class CounterResource {

	@GET
	@Path("/count")
	public Response count(@QueryParam("times") Integer times) {
		
		if(times==null)
			times = 1;
		
		AgentHost host = AgentHost.getInstance();
		Integer count = 0;
		try {
			HelloWorldAgent agent = (HelloWorldAgent) host.getAgent("helloworld");
		
			for(int i=0; i<times; i++) {
				count = agent.count();
			}
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		return Response.ok(count.toString()).build();
	}
	
	@GET
	@Path("/agent/{name}")
	public Response createAgent(@PathParam("name") String name) {
		
		try {
			AgentHost host = AgentHost.getInstance();
			ManagementAgent agent = (ManagementAgent) host.getAgent("ma");
			
			agent.createPersonalAgent(name);
			
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		
		
		return Response.ok("").build();
	}
}
