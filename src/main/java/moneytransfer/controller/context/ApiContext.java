package moneytransfer.controller.context;

import io.javalin.Context;

public interface ApiContext {
	
	String pathParam(Context context, String param);

	String formParam(Context context, String param);

	void json(Context context, Object object);

	void json(Context context, Object object, int status);

}
