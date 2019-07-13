package moneytransfer;


import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.javalin.ForbiddenResponse;
import io.javalin.Javalin;
import io.javalin.json.JavalinJson;
import moneytransfer.component.ApiComponent;
import moneytransfer.component.DaggerApiComponent;
import moneytransfer.controller.ApiController;
import moneytransfer.model.Response;

public class Application {
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main (String args[]) {
		
		final Gson gson = new GsonBuilder().create();
	    JavalinJson.setFromJsonMapper(gson::fromJson);
	    JavalinJson.setToJsonMapper(gson::toJson);
		    	    
		final ApiComponent component = DaggerApiComponent.create();
	    final ApiController controller = component.controller();
	    	  
	final Javalin app = Javalin.create()
	        
	        .start(7000);
	
	app.get("/", context -> {
	      throw new ForbiddenResponse();
	    });
	
	app.get("/health", context ->
    context
        .status(200)
        .json(Response.builder().message("OK").build())
			);

	app.routes(() -> {
	     path("/transaction", () -> {
	        post(controller::transferMoney);
	      });
	    });
	
	app.exception(Exception.class, (exception, context) -> {
	      context.status(500);
	      LOG.error("Unexpected Error", exception);
	    });
}
}
