package moneytransfer.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import moneytransfer.service.ApiService;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ControllerModule_ProvideServiceFactory implements Factory<ApiService> {
  private final ControllerModule module;

  public ControllerModule_ProvideServiceFactory(ControllerModule module) {
    this.module = module;
  }

  @Override
  public ApiService get() {
    return provideInstance(module);
  }

  public static ApiService provideInstance(ControllerModule module) {
    return proxyProvideService(module);
  }

  public static ControllerModule_ProvideServiceFactory create(ControllerModule module) {
    return new ControllerModule_ProvideServiceFactory(module);
  }

  public static ApiService proxyProvideService(ControllerModule instance) {
    return Preconditions.checkNotNull(
        instance.provideService(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
