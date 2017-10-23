package com.mistreckless.support.musicapp.ui.main;

import android.support.v4.app.Fragment;
import com.mistreckless.support.musicapp.ui.PerFragment;
import com.mistreckless.support.musicapp.ui.profile.Profile;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = MainActivityFragmentProvider_ProvideProfile.ProfileSubcomponent.class)
public abstract class MainActivityFragmentProvider_ProvideProfile {
  private MainActivityFragmentProvider_ProvideProfile() {}

  @Binds
  @IntoMap
  @FragmentKey(Profile.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      ProfileSubcomponent.Builder builder);

  @Subcomponent
  @PerFragment
  public interface ProfileSubcomponent extends AndroidInjector<Profile> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<Profile> {}
  }
}
