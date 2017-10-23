package com.mistreckless.support.musicapp.ui.main;

import android.support.v4.app.Fragment;
import com.mistreckless.support.musicapp.ui.PerFragment;
import com.mistreckless.support.musicapp.ui.wall.Wall;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = MainActivityFragmentProvider_ProvideWall.WallSubcomponent.class)
public abstract class MainActivityFragmentProvider_ProvideWall {
  private MainActivityFragmentProvider_ProvideWall() {}

  @Binds
  @IntoMap
  @FragmentKey(Wall.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      WallSubcomponent.Builder builder);

  @Subcomponent
  @PerFragment
  public interface WallSubcomponent extends AndroidInjector<Wall> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<Wall> {}
  }
}
