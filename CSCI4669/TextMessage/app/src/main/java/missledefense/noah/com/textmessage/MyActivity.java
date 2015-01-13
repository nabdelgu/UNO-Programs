package missledefense.noah.com.textmessage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MyActivity extends Activity {

    private Button sendMessage;
    private EditText phoneNumber;
    private EditText Message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        sendMessage = (Button) findViewById(R.id.sendMessage);
        phoneNumber = (EditText) findViewById(R.id.toPhoneNumber);
        Message = (EditText) findViewById(R.id.message);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //sendMessage();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setType()
            }
        });
    }

/**
    public void sendMessage() {
        Log.i("Send SMS", "");

        String number = phoneNumber.getText().toString();
        System.out.println(number);
        String message = Message.getText().toString();
        System.out.println(message);

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(getApplicationContext(), "Message sent.", Toast.LENGTH_LONG).show();

    }
**/
}



