package moneytransfer.module;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import moneytransfer.controller.ApiController;
import moneytransfer.controller.context.ApiContext;
import moneytransfer.service.ApiService;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ControllerModule_MembersInjector implements MembersInjector<ControllerModule> {
  private final Provider<ApiContext> apiContextProvider;

  private final Provider<ApiService> serviceProvider;

  public ControllerModule_MembersInjector(
      Provider<ApiContext> apiContextProvider, Provider<ApiService> serviceProvider) {
    this.apiContextProvider = apiContextProvider;
    this.serviceProvider = serviceProvider;
  }

  public static MembersInjector<ControllerModule> create(
      Provider<ApiContext> apiContextProvider, Provider<ApiService> serviceProvider) {
    return new ControllerModule_MembersInjector(apiContextProvider, serviceProvider);
  }

  @Override
  public void injectMembers(ControllerModule instance) {
    injectProvideController(instance, apiContextProvider.get(), serviceProvider.get());
    injectProvideService(instance);
  }

  public static ApiController injectProvideController(
      ControllerModule instance, ApiContext apiContext, ApiService service) {
    return instance.provideController(apiContext, service);
  }

  public static ApiService injectProvideService(ControllerModule instance) {
    return instance.provideService();
  }
}
