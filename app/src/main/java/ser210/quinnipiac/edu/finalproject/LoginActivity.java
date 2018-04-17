package ser210.quinnipiac.edu.finalproject;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class LoginActivity extends Activity {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.loginActivity, new LoginFragment());
        mFragmentTransaction.commit();
    }
}
