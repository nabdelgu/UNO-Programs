package gov.louisiana.wlf.swordfish.model.mainScreen;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by noah on 7/21/14.
 */
public class FeedbackApplication extends Activity {

    public static Spinner FEEDBACK_SPINNER;
    public static EditText SUBJECT;
    public static EditText DESCRIPTION;
    public static Button SAVE;
    public static TextView ERROR_MESSAGE;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_main);


        FEEDBACK_SPINNER = (Spinner) findViewById(R.id.spinnerFeedbackType);
        SUBJECT = (EditText) findViewById(R.id.editTextSubject);
        DESCRIPTION = (EditText) findViewById(R.id.editTextDescription);
        SAVE = (Button) findViewById(R.id.buttonSave);
        ERROR_MESSAGE = (TextView) findViewById(R.id.textDescriptionErrorMessage);

        ArrayAdapter<CharSequence> feedbackTypeAdapter = ArrayAdapter.createFromResource(this, R.array.feedback_type, android.R.layout.simple_spinner_item);
        FEEDBACK_SPINNER.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Do nothing.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing.
            }
        });
        FEEDBACK_SPINNER.setAdapter(feedbackTypeAdapter);


        SAVE.setOnClickListener(new OnClickListener() {


        public void onClick(View view) {

            boolean validApplication = DESCRIPTION.length() > 0;

            String feedbackType = FEEDBACK_SPINNER.getSelectedItem().toString();
            String subject = SUBJECT.getText().toString();
            String description = DESCRIPTION.getText().toString();

            System.out.print("FeedbackType " + feedbackType);
            System.out.print("subject " + subject);
            System.out.print("description " + description);

            if(validApplication) {

                Feedback feedback = new Feedback();

                feedback.setFeedbackType(feedbackType);
                feedback.setSubject(subject);
                feedback.setDescription(description);

                feedback.save();

                Toast.makeText(getApplicationContext(), "Your feedback has been submitted", Toast.LENGTH_SHORT).show();
                ERROR_MESSAGE.setText("");
                SUBJECT.setText("");
                DESCRIPTION.setText("");

            }
            else{

                Toast.makeText(getApplicationContext(), "Invalid feedback form", Toast.LENGTH_SHORT).show();
                ERROR_MESSAGE.setTextColor(Color.RED);
                ERROR_MESSAGE.setTextSize(9);
                ERROR_MESSAGE.setText("The Description: Cannot be left blank");
            }

        }

       });

    }

}
