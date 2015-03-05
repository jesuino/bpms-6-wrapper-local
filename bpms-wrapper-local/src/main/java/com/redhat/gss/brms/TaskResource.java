package com.redhat.gss.brms;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

// TODO: Actually finish this REST resource

@Path("/task/{taskId}")
public class TaskResource {

	@PathParam("taskId")
	Long taskId;

	@POST
	@Path("start")
	public Response start(Map<String, String> params) {
		return Response.ok("Task started with success").build();
	}

	@POST
	@Path("complete")
	public Response complet(Map<String, String> params) {

		return Response.ok("Task Completed with success").build();
	}

	@POST
	@Path("content")
	public Response addContent(Map<String, String> params) {
		return Response.ok("Content added with success").build();
	}

}
