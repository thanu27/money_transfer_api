package moneytransfer.component;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import moneytransfer.controller.ApiController;
import moneytransfer.controller.context.ApiContext;
import moneytransfer.module.ControllerModule;
import moneytransfer.module.ControllerModule_ProvideApiContextFactory;
import moneytransfer.module.ControllerModule_ProvideControllerFactory;
import moneytransfer.module.ControllerModule_ProvideServiceFactory;
import moneytransfer.service.ApiService;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerApiComponent implements ApiComponent {
  private Provider<ApiContext> provideApiContextProvider;

  private Provider<ApiService> provideServiceProvider;

  private Provider<ApiController> provideControllerProvider;

  private DaggerApiComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ApiComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideApiContextProvider =
        DoubleCheck.provider(
            ControllerModule_ProvideApiContextFactory.create(builder.controllerModule));
    this.provideServiceProvider =
        DoubleCheck.provider(
            ControllerModule_ProvideServiceFactory.create(builder.controllerModule));
    this.provideControllerProvider =
        DoubleCheck.provider(
            ControllerModule_ProvideControllerFactory.create(
                builder.controllerModule, provideApiContextProvider, provideServiceProvider));
  }

  @Override
  public ApiController controller() {
    return provideControllerProvider.get();
  }

  public static final class Builder {
    private ControllerModule controllerModule;

    private Builder() {}

    public ApiComponent build() {
      if (controllerModule == null) {
        this.controllerModule = new ControllerModule();
      }
      return new DaggerApiComponent(this);
    }

    public Builder controllerModule(ControllerModule controllerModule) {
      this.controllerModule = Preconditions.checkNotNull(controllerModule);
      return this;
    }
  }
}
