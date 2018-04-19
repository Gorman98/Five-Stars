package ser210.quinnipiac.edu.finalproject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsFragment extends Fragment {
    private ConstraintLayout settingsFrag;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.fragment_settings, container, false);
        settingsFrag = (ConstraintLayout) v.findViewById(R.id.settingsFragment);

        settingsFrag.setBackgroundColor(getResources().getColor(MainActivity.color));

        Button whiteButton = (Button) v.findViewById(R.id.whiteButton);
        Button blueButton = (Button) v.findViewById(R.id.blueButton);
        Button greenButton = (Button) v.findViewById(R.id.greenButton);

        whiteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view = v;
                view.setEnabled(false);
                //run function that checks sql for username and password
                MainActivity.setColorVal(0);
                settingsFrag.setBackgroundColor(getResources().getColor(MainActivity.color));
            }
        });
        greenButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view = v;
                view.setEnabled(false);
                //run function that checks sql for username and password
                MainActivity.setColorVal(1);
                settingsFrag.setBackgroundColor(getResources().getColor(MainActivity.color));
            }
        });
        blueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view = v;
                view.setEnabled(false);
                //run function that checks sql for username and password
                MainActivity.setColorVal(2);
                settingsFrag.setBackgroundColor(getResources().getColor(MainActivity.color));
            }
        });
        return v;
    }
}
