package moneytransfer.module;
import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;

import dagger.Module;
import moneytransfer.controller.ApiController;
import moneytransfer.controller.context.ApiContext;
import moneytransfer.controller.context.ApiContextImpl;
import moneytransfer.service.ApiService;
import moneytransfer.service.impl.ApiServiceImpl;
@Module
public class ControllerModule {
	
	  @Provides
	  @Singleton
	  ApiContext provideApiContext() {
	    return new ApiContextImpl();
	  }
	
	  @Inject
	  @Provides
	  @Singleton
	  ApiController provideController(
			final  ApiContext apiContext, 
			final ApiService service) {
		  return new ApiController(apiContext,service);
	  }
	  
	  @Inject
	  @Provides
	  @Singleton
	  ApiService provideService() {
		  return new ApiServiceImpl();		  
	  }


}
