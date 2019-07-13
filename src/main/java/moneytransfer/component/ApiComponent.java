package moneytransfer.component;

import javax.inject.Singleton;
import dagger.Component;
import moneytransfer.controller.ApiController;
import moneytransfer.module.ControllerModule;

@Singleton
	@Component(modules = {
	    ControllerModule.class
	})	
public interface ApiComponent {
	ApiController controller();
	}
