package iut.projet.hardestgame.activities;

import android.app.Activity;
import android.preference.PreferenceActivity;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import iut.projet.hardestgame.R;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final CheckBox cb = findViewById(R.id.musiqueCheckBox);
        if(Main2Activity.isMusic())
            cb.setChecked(true);
        else
            cb.setChecked(false);

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb.isChecked())
                    Main2Activity.setIsMusic(true);
                else
                    Main2Activity.setIsMusic(false);
            }
        });
    }
}
