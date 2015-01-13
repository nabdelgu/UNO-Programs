package com.example.noah.addingpicturefragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MyActivity extends Activity {

    public static final String CURRENT_VISIBILITY = "Visible";

    PlaceholderFragment fragment1;
    PlaceholderFragment fragment2;
    int visible = View.VISIBLE;
    int invisible = View.INVISIBLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        if (savedInstanceState == null){

            final int currentVisibility = getIntent().getIntExtra(CURRENT_VISIBILITY, View.VISIBLE);

            if (findViewById(R.id.container2) != null) {

                fragment1 = PlaceholderFragment.newInstance(currentVisibility, new PlaceholderFragment.CommChannel() {
                    @Override
                    public void causeChange() {
                        fragment2.changeVisibility(invisible);
                    }
                });

                fragment2 = PlaceholderFragment.newInstance(currentVisibility, new PlaceholderFragment.CommChannel() {
                    @Override
                    public void causeChange() {
                        fragment1.changeVisibility(invisible);
                    }
                });


                getFragmentManager().beginTransaction()
                        .add(R.id.container, fragment1)
                        .commit();

                getFragmentManager().beginTransaction()
                        .add(R.id.container2, fragment2)
                        .commit();
            } else {
                fragment1 = PlaceholderFragment.newInstance(currentVisibility, new PlaceholderFragment.CommChannel() {
                    @Override
                    public void causeChange() {
                        Intent intent = new Intent(MyActivity.this, MyActivity.class);
                        intent.putExtra(MyActivity.CURRENT_VISIBILITY, View.INVISIBLE);
                        MyActivity.this.startActivity(intent);
                    }
                });

                getFragmentManager().beginTransaction()
                        .add(R.id.container, fragment1)
                        .commit();
            }
        }
    }


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



}
