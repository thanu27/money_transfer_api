package moneytransfer.controller;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;
import moneytransfer.controller.context.ApiContext;
import moneytransfer.service.ApiService;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApiController_Factory implements Factory<ApiController> {
  private final Provider<ApiContext> apiContextProvider;

  private final Provider<ApiService> serviceProvider;

  public ApiController_Factory(
      Provider<ApiContext> apiContextProvider, Provider<ApiService> serviceProvider) {
    this.apiContextProvider = apiContextProvider;
    this.serviceProvider = serviceProvider;
  }

  @Override
  public ApiController get() {
    return provideInstance(apiContextProvider, serviceProvider);
  }

  public static ApiController provideInstance(
      Provider<ApiContext> apiContextProvider, Provider<ApiService> serviceProvider) {
    return new ApiController(apiContextProvider.get(), serviceProvider.get());
  }

  public static ApiController_Factory create(
      Provider<ApiContext> apiContextProvider, Provider<ApiService> serviceProvider) {
    return new ApiController_Factory(apiContextProvider, serviceProvider);
  }

  public static ApiController newApiController(ApiContext apiContext, ApiService service) {
    return new ApiController(apiContext, service);
  }
}
