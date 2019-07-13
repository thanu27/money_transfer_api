package moneytransfer.controller.context;

import io.javalin.Context;

public class ApiContextImpl implements ApiContext{
	
	 @Override public String pathParam(Context context, String param) {
	    return context.pathParam(param);
	  }

	  @Override public String formParam(Context context, String param) {
	    return context.formParam(param);
	  }

	  @Override public void json(Context context, Object object) {
	    context.json(object);
	  }

	  @Override public void json(Context context, Object object, int status) {
	    context.status(status).json(object);
	  }

}
