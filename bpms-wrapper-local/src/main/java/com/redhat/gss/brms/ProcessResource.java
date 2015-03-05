package com.redhat.gss.brms;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;

@Path("/")
public class ProcessResource {

	@Inject
	KieContainerFactory factory;

	@QueryParam("groupId")
	String groupId;
	@QueryParam("artifactId")
	String artifactId;
	@QueryParam("version")
	String version;

	// TODO: Add support for process parameters
	@POST
	@Path("/process/{processId}/start")
	public Response startProcess(@PathParam("processId") String processId) {
		KieSession kieSession = factory.getKieContainer(groupId, artifactId,
				version).newKieSession();
		kieSession.startProcess(processId);
		kieSession.destroy();
		return Response.ok("Process started with success").build();
	}

	@GET
	@Path("/process/instances")
	public Response getInstances() {
		KieSession kieSession = factory.getKieContainer(groupId, artifactId,
				version).newKieSession();
		Collection<ProcessInstance> processesInstances = kieSession
				.getProcessInstances();
		kieSession.destroy();
		return Response.ok(processesInstances).build();
	}

}
