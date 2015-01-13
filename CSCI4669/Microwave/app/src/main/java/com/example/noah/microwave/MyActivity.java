package com.example.noah.microwave;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MyActivity extends Activity  {

    public static final String TIMER = "color";

    public Button button0, button1, button2, button3, button4, button5, button6, button7, button8,button9, buttonStart, buttonStop, buttonReset;
    public TextView textViewTimer, testTextView;
    //Intent intent;
/*
    PlaceholderFragment fragment1;
    PlaceholderFragment fragment2;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
/*
        int visibility = getIntent().getIntExtra(TIMER, View.INVISIBLE);

        if (savedInstanceState == null){

          // final int currentVisibility = getIntent().getIntExtra(CURRENT_VISIBILITY, View.VISIBLE);

            if (findViewById(R.id.container2) != null) {

                fragment1 = PlaceholderFragment.newInstance(null, new PlaceholderFragment.CommChannel()
                {
                    @Override
                    public void causeChange() {

                    }
                });


            } else {
                fragment2 = PlaceholderFragment.newInstance(new PlaceholderFragment.CommChannel() {
                    @Override
                    public void causeChange() {
                        Intent intent = new Intent(MyActivity.this, MyActivity.class);
                        //intent.putExtra(MyActivity.CURRENT_VISIBILITY, View.INVISIBLE);
                        MyActivity.this.startActivity(intent);
                    }
                });

                getFragmentManager().beginTransaction()
                        .add(R.id.container2, fragment2)
                        .commit();
            }
        }

*/
    button0 = (Button) findViewById(R.id.buttonZero);
    button1 = (Button) findViewById(R.id.buttonOne);
    button2 = (Button) findViewById(R.id.buttonTwo);
    button3 = (Button) findViewById(R.id.buttonThree);
    button4 = (Button) findViewById(R.id.buttonFour);
    button5 = (Button) findViewById(R.id.buttonFive);
    button6 = (Button) findViewById(R.id.buttonSix);
    button7 = (Button) findViewById(R.id.buttonSeven);
    button8 = (Button) findViewById(R.id.buttonEight);
    button9 = (Button) findViewById(R.id.buttonNine);
    buttonStart = (Button) findViewById(R.id.buttonStart);
    buttonReset = (Button) findViewById(R.id.buttonReset);
    buttonStop = (Button) findViewById(R.id.buttonPause);

    textViewTimer = (TextView) findViewById(R.id.textViewTimer);
    testTextView = (TextView) findViewById(R.id.textView);
    final ArrayList<String> timer = new ArrayList<String>();

    //intent = new Intent(this, MyActivity.class);



        buttonReset.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        textViewTimer.setText("000");
                        testTextView.setText("");
                        findViewById(R.id.mc).setBackgroundColor(Color.TRANSPARENT);
                        timer.clear();
                    }
                }
        );




        button0.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        timer.add("0");
                        if (timer.size() == 1)
                            textViewTimer.setText(timer.get(0));
                        else if (timer.size() == 2){
                            textViewTimer.setText(timer.get(0) + timer.get(1));
                        }
                        else if(timer.size() == 3){
                            textViewTimer.setText(timer.get(0) + timer.get(1) + timer.get(2));
                        }else{
                            System.out.print("Something went wrong");
                        }


                    }
                }
        );

        button1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        timer.add("1");
                        if (timer.size() == 1)
                            textViewTimer.setText(timer.get(0));
                        else if (timer.size() == 2){
                            textViewTimer.setText(timer.get(0) + timer.get(1));
                        }
                        else if(timer.size() == 3){
                            textViewTimer.setText(timer.get(0) + timer.get(1) + timer.get(2));
                        }else{
                            System.out.print("Something went wrong");
                        }

                    }
                }
        );

        button2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        timer.add("2");
                        if (timer.size() == 1)
                            textViewTimer.setText(timer.get(0));
                        else if (timer.size() == 2){
                            textViewTimer.setText(timer.get(0) + timer.get(1));
                        }
                        else if(timer.size() == 3){
                            textViewTimer.setText(timer.get(0) + timer.get(1) + timer.get(2));
                        }else{
                            System.out.print("Something went wrong");
                        }

                    }
                }
        );

        button3.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        timer.add("3");
                        if (timer.size() == 1)
                            textViewTimer.setText(timer.get(0));
                        else if (timer.size() == 2){
                            textViewTimer.setText(timer.get(0) + timer.get(1));
                        }
                        else if(timer.size() == 3){
                            textViewTimer.setText(timer.get(0) + timer.get(1) + timer.get(2));
                        }else{
                            System.out.print("Something went wrong");
                        }

                    }
                }
        );

        button4.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        timer.add("4");
                        if (timer.size() == 1)
                            textViewTimer.setText(timer.get(0));
                        else if (timer.size() == 2){
                            textViewTimer.setText(timer.get(0) + timer.get(1));
                        }
                        else if(timer.size() == 3){
                            textViewTimer.setText(timer.get(0) + timer.get(1) + timer.get(2));
                        }else{
                            System.out.print("Something went wrong");
                        }

                    }
                }
        );

        button5.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        timer.add("5");
                        if (timer.size() == 1)
                            textViewTimer.setText(timer.get(0));
                        else if (timer.size() == 2){
                            textViewTimer.setText(timer.get(0) + timer.get(1));
                        }
                        else if(timer.size() == 3){
                            textViewTimer.setText(timer.get(0) + timer.get(1) + timer.get(2));
                        }else{
                            System.out.print("Something went wrong");
                        }

                    }
                }
        );

        button6.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        timer.add("6");
                        if (timer.size() == 1)
                            textViewTimer.setText(timer.get(0));
                        else if (timer.size() == 2){
                            textViewTimer.setText(timer.get(0) + timer.get(1));
                        }
                        else if(timer.size() == 3){
                            textViewTimer.setText(timer.get(0) + timer.get(1) + timer.get(2));
                        }else{
                            System.out.print("Something went wrong");
                        }

                    }
                }
        );

        button7.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        timer.add("7");
                        if (timer.size() == 1)
                            textViewTimer.setText(timer.get(0));
                        else if (timer.size() == 2){
                            textViewTimer.setText(timer.get(0) + timer.get(1));
                        }
                        else if(timer.size() == 3){
                            textViewTimer.setText(timer.get(0) + timer.get(1) + timer.get(2));
                        }else{
                            System.out.print("Something went wrong");
                        }

                    }
                }
        );

        button8.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        timer.add("8");
                        if (timer.size() == 1)
                            textViewTimer.setText(timer.get(0));
                        else if (timer.size() == 2){
                            textViewTimer.setText(timer.get(0) + timer.get(1));
                        }
                        else if(timer.size() == 3){
                            textViewTimer.setText(timer.get(0) + timer.get(1) + timer.get(2));
                        }else{
                            System.out.print("Something went wrong");
                        }

                    }
                }
        );

        button9.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        timer.add("9");
                        if (timer.size() == 1)
                            textViewTimer.setText(timer.get(0));
                        else if (timer.size() == 2){
                            textViewTimer.setText(timer.get(0) + timer.get(1));
                        }
                        else if(timer.size() == 3){
                            textViewTimer.setText(timer.get(0) + timer.get(1) + timer.get(2));
                        }else{
                            System.out.print("Something went wrong");
                        }

                    }
                }
        );

        try {

            buttonStart.setOnClickListener(
                    new Button.OnClickListener() {

                        public void onClick(View v)throws NumberFormatException{

                            String timeString = textViewTimer.getText().toString();
                            int timeValue = (Integer.parseInt(timeString.toString())) * 1000;

                            final CountDownTimer countDownTimer = new CountDownTimer(timeValue, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    textViewTimer.setText("" + millisUntilFinished / 1000);

                                }

                                public void onFinish() {
                                    textViewTimer.setText("000");


                                    FragmentManager Fm = getFragmentManager();
                                    FragmentTransaction FT = Fm.beginTransaction();
                                    Fragment MF = new Fragment();
                                    FT.add(R.id.mc, MF);
                                    findViewById(R.id.mc).setBackgroundColor(Color.BLUE);
                                    FT.commit();

                                }





                            }.start();

                            buttonStop.setOnClickListener(
                                    new Button.OnClickListener() {

                                         @Override
                                        public void onClick(View v) {
                                            countDownTimer.cancel();
                                        }
                                    });



                           textViewTimer.postDelayed(new Runnable() {
                                public void run() {
                                    testTextView.setText("The Microwave is finished");
                                }
                            }, timeValue);


                        }
                    }
            );
        }catch (NumberFormatException e){
            System.out.print(e.getMessage());
        }

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/
}




