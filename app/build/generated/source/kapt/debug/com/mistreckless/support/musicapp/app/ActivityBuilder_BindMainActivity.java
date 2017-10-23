package com.mistreckless.support.musicapp.app;

import android.app.Activity;
import com.mistreckless.support.musicapp.ui.PerActivity;
import com.mistreckless.support.musicapp.ui.main.MainActivity;
import com.mistreckless.support.musicapp.ui.main.MainActivityFragmentProvider;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = ActivityBuilder_BindMainActivity.MainActivitySubcomponent.class)
public abstract class ActivityBuilder_BindMainActivity {
  private ActivityBuilder_BindMainActivity() {}

  @Binds
  @IntoMap
  @ActivityKey(MainActivity.class)
  abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(
      MainActivitySubcomponent.Builder builder);

  @Subcomponent(modules = MainActivityFragmentProvider.class)
  @PerActivity
  public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {}
  }
}
