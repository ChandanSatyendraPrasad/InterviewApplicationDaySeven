package chandan.prasad.interviewapplicationdayseven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bsimple, btough;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Codes to add Action Bar

        LinearLayout front_ll =  findViewById(R.id.front_page_titlebar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.frontpage_title_bar);

        bsimple = findViewById(R.id.bsq);
        btough = findViewById(R.id.btq);

        bsimple.setOnClickListener(this);
        btough.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bsq:

                Intent i = new Intent(MainActivity.this, SimpleQuestion.class);
                startActivity(i);

                break;
            case R.id.btq:

                Intent j = new Intent(MainActivity.this, ToughQuestion.class);
                startActivity(j);
                break;


        }

    }
}
