package ser210.quinnipiac.edu.finalproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Activity;

public class SettingsActivity extends Activity {

    private FragmentTransaction mFragmentTrainscation;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mFragmentManager = getFragmentManager();
        mFragmentTrainscation = mFragmentManager.beginTransaction();
        mFragmentTrainscation.add(R.id.settingsLayout, new SettingsFragment());
        mFragmentTrainscation.commit();
    }

}
