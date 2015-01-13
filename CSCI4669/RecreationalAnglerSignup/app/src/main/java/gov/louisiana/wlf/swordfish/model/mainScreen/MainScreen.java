package gov.louisiana.wlf.swordfish.model.mainScreen.;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import gov.louisiana.wlf.swordfish.model.FishermanRegistrationActivity;
import gov.louisiana.wlf.swordfish.model.R;

/**
 * Created by noah on 7/16/14.
 */
public class MainScreen extends Activity {

    ImageButton charterCaptain;
    ImageButton recreationalAngler;
    Button feedback;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen_main);

        charterCaptain = (ImageButton) findViewById(R.id.imageCharterCaptain);
        recreationalAngler = (ImageButton) findViewById(R.id.imageRecreationalAngler);
        feedback = (Button) findViewById(R.id.buttonFeedback);

        charterCaptain.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                Intent clickCharterCaptain = new Intent(MainScreen.this, FishermanRegistrationActivity.class);
                clickCharterCaptain.putExtra("type", "charter");
                startActivity(clickCharterCaptain);


            }
        });

        recreationalAngler.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {

                Intent clickRecreationAngler = new Intent(MainScreen.this, FishermanRegistrationActivity.class);
                clickRecreationAngler.putExtra("type", "recreational");
                startActivity(clickRecreationAngler);

            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent clickFeedback = new Intent(MainScreen.this, FeedbackApplication.class);
                startActivity(clickFeedback);

                System.out.print("---------------Feedback---------------------");

            }
        });
    }

}
