package com.example.noah.microwave;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by noah on 10/11/14.
 */
public class FragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.microwave_finished, container,false);
       return v;
    }

    interface causeChange
    {
        public void causeChange(String message);
    }


}
