package moneytransfer.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import moneytransfer.controller.context.ApiContext;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ControllerModule_ProvideApiContextFactory implements Factory<ApiContext> {
  private final ControllerModule module;

  public ControllerModule_ProvideApiContextFactory(ControllerModule module) {
    this.module = module;
  }

  @Override
  public ApiContext get() {
    return provideInstance(module);
  }

  public static ApiContext provideInstance(ControllerModule module) {
    return proxyProvideApiContext(module);
  }

  public static ControllerModule_ProvideApiContextFactory create(ControllerModule module) {
    return new ControllerModule_ProvideApiContextFactory(module);
  }

  public static ApiContext proxyProvideApiContext(ControllerModule instance) {
    return Preconditions.checkNotNull(
        instance.provideApiContext(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
