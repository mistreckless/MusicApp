// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.mistreckless.support.musicapp.ui.wall;

import com.mistreckless.support.musicapp.domain.repository.UserRepository;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class WallPresenterProviderFactory_Factory
    implements Factory<WallPresenterProviderFactory> {
  private final Provider<UserRepository> arg0Provider;

  public WallPresenterProviderFactory_Factory(Provider<UserRepository> arg0Provider) {
    this.arg0Provider = arg0Provider;
  }

  @Override
  public WallPresenterProviderFactory get() {
    return new WallPresenterProviderFactory(arg0Provider.get());
  }

  public static Factory<WallPresenterProviderFactory> create(
      Provider<UserRepository> arg0Provider) {
    return new WallPresenterProviderFactory_Factory(arg0Provider);
  }
}