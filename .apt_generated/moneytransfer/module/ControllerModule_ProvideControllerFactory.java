package moneytransfer.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import moneytransfer.controller.ApiController;
import moneytransfer.controller.context.ApiContext;
import moneytransfer.service.ApiService;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ControllerModule_ProvideControllerFactory implements Factory<ApiController> {
  private final ControllerModule module;

  private final Provider<ApiContext> apiContextProvider;

  private final Provider<ApiService> serviceProvider;

  public ControllerModule_ProvideControllerFactory(
      ControllerModule module,
      Provider<ApiContext> apiContextProvider,
      Provider<ApiService> serviceProvider) {
    this.module = module;
    this.apiContextProvider = apiContextProvider;
    this.serviceProvider = serviceProvider;
  }

  @Override
  public ApiController get() {
    return provideInstance(module, apiContextProvider, serviceProvider);
  }

  public static ApiController provideInstance(
      ControllerModule module,
      Provider<ApiContext> apiContextProvider,
      Provider<ApiService> serviceProvider) {
    return proxyProvideController(module, apiContextProvider.get(), serviceProvider.get());
  }

  public static ControllerModule_ProvideControllerFactory create(
      ControllerModule module,
      Provider<ApiContext> apiContextProvider,
      Provider<ApiService> serviceProvider) {
    return new ControllerModule_ProvideControllerFactory(
        module, apiContextProvider, serviceProvider);
  }

  public static ApiController proxyProvideController(
      ControllerModule instance, ApiContext apiContext, ApiService service) {
    return Preconditions.checkNotNull(
        instance.provideController(apiContext, service),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
