package chandan.prasad.interviewapplicationdayseven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SimpleQuestion extends AppCompatActivity implements View.OnClickListener {
    TextView tvquestion, tvanswer, tvtotallength_yy, tvpresentindex_xx;

    ImageButton bleft, bshow, bright;

    String[] simple_question, simple_answers;

    int index;

    private static final String default_answer = "Press \"A\" Button for the Answer";

    // Variables & object of TTS
    TextToSpeech ttsobject;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        // Codes to add Action Bar

        LinearLayout questions_ll = (LinearLayout) findViewById(R.id.question_page_titlebar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.questions_title_bar);

        //Objects are mapped
        ImageButton bspeak = findViewById(R.id.bspeak);
        ImageButton bstop_mute = findViewById(R.id.bstop_mute);
        TextView tv_category = findViewById(R.id.tv_questions_titlebar);
        tv_category.setText("Simple Questions");


        // Initialization of TextView
        tvquestion = findViewById(R.id.tvquestion);
        tvanswer = findViewById(R.id.tvanswer);
        tvpresentindex_xx = findViewById(R.id.tvxx);
        tvtotallength_yy = findViewById(R.id.tvyy);

        // Initialization of Buttons
        bleft = findViewById(R.id.bleft);
        bshow = findViewById(R.id.bshowanswer);
        bright = findViewById(R.id.bright);

        // Importing The string array from Values folder
        simple_question = getResources().getStringArray(R.array.simple_ques);
        simple_answers = getResources().getStringArray(R.array.simple_ans);

        // Onclick Listener for 3 Buttons and also SPEAK, MUTE Buttons
        bleft.setOnClickListener(this);
        bshow.setOnClickListener(this);
        bright.setOnClickListener(this);
        bspeak.setOnClickListener(this);
        bstop_mute.setOnClickListener(this);

        // Setting Values to our Variable and 4 TextViews
        index = 0;
        tvquestion.setText(simple_question[index]);
        tvanswer.setText(default_answer);
        tvpresentindex_xx.setText(String.valueOf(index + 1));
        tvtotallength_yy.setText("/" + String.valueOf(simple_question.length));

        // TTS object and Listener initialization
        ttsobject = new TextToSpeech(SimpleQuestion.this,
                new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {

                        if (status == TextToSpeech.SUCCESS) {

                            result = ttsobject.setLanguage(Locale.US);

                        } else {

                            Toast.makeText(getApplicationContext(),
                                    "Feature not Supported in Your Device",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bleft:
                tvanswer.setText(default_answer);
                index--;

                if (index == -1) {

                    index = simple_question.length - 1;
                    tvquestion.setText(simple_question[index]);

                    tvpresentindex_xx.setText(String.valueOf(index + 1));
                } else {
                    tvquestion.setText(simple_question[index]);

                    tvpresentindex_xx.setText(String.valueOf(index + 1));
                }

                break;

            case R.id.bshowanswer:

                tvanswer.setText(simple_answers[index]);

                break;

            case R.id.bright:
                tvanswer.setText(default_answer);

                index++;

                if (index == simple_question.length) {

                    index = 0;
                    tvquestion.setText(simple_question[index]);

                    tvpresentindex_xx.setText(String.valueOf(index + 1));

                } else {
                    tvquestion.setText(simple_question[index]);

                    tvpresentindex_xx.setText(String.valueOf(index + 1));
                }
                break;

            case R.id.bspeak:

                if (result == TextToSpeech.LANG_NOT_SUPPORTED
                        || result == TextToSpeech.LANG_MISSING_DATA) {

                    Toast.makeText(getApplicationContext(),
                            "Feature not Supported in Your Device",
                            Toast.LENGTH_SHORT).show();

                } else {


                    if (tvanswer.getText().toString().equals(default_answer)) {


                    } else {
                        ttsobject.speak(simple_answers[index],
                                TextToSpeech.QUEUE_FLUSH, null);
                    }
                }

                break;

            case R.id.bstop_mute:

                if (ttsobject != null) {

                    ttsobject.stop();

                }

                break;

        }
    }
}