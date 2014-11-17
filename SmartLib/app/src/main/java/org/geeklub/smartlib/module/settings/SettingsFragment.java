package org.geeklub.smartlib.module.settings;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import org.geeklub.smartlib.R;

/**
 * Created by Vass on 2014/11/12.
 */
public class SettingsFragment extends PreferenceFragment
    implements Preference.OnPreferenceChangeListener {

  private Preference versionPreference;

  @Override public boolean onPreferenceChange(Preference preference, Object newValue) {
    return false;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    addPreferencesFromResource(R.xml.settings);

    versionPreference = findPreference(getString(R.string.settings_key_version));

    try {
      setVersion();
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }


  }

  private void setVersion() throws PackageManager.NameNotFoundException {

    PackageInfo packageInfo = getActivity().getPackageManager()
        .getPackageInfo(getActivity().getPackageName(), PackageManager.GET_CONFIGURATIONS);

    versionPreference.setTitle(packageInfo.versionName);
  }
}
