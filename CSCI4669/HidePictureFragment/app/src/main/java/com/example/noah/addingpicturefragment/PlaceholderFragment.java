package com.example.noah.addingpicturefragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by noah on 9/30/14.
 */
public class PlaceholderFragment extends Fragment{

    private static final String CURRENT_VISIBILITY = "Visible";

    public interface CommChannel {
        public void causeChange();
    }

    private CommChannel channel;

    public static PlaceholderFragment newInstance() {
        return PlaceholderFragment.newInstance(0, null);
    }

    public static PlaceholderFragment newInstance(int currentVisibility) {
        return PlaceholderFragment.newInstance(currentVisibility, null);
    }

    public static PlaceholderFragment newInstance(CommChannel channel) {
        return PlaceholderFragment.newInstance(0, channel);
    }

    public static PlaceholderFragment newInstance(int currentVisibility, CommChannel channel) {
        PlaceholderFragment fragment = new PlaceholderFragment();

        if (channel != null) {
            fragment.setChannel(channel);
        }

        if (currentVisibility != 0) {
            Bundle args = new Bundle();
            args.putInt(CURRENT_VISIBILITY, currentVisibility );

            fragment.setArguments(args);
        }

        return fragment;
    }


    public PlaceholderFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (this.channel == null) {
            this.channel = new CommChannel() {
                @Override
                public void causeChange() {
                    Intent intent = new Intent(getActivity(), MyActivity.class);
                    intent.putExtra(MyActivity.CURRENT_VISIBILITY, View.INVISIBLE);
                    getActivity().startActivity(intent);
                }
            };
        }
        else if (activity instanceof CommChannel) {
            this.channel = (CommChannel)activity;
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frament, container, false);

        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaceholderFragment.this.channel.causeChange();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {

            changeVisibility(getArguments().getInt(CURRENT_VISIBILITY, View.VISIBLE));
        }

    }

    public void changeVisibility(int currentVisibility) {

        ImageView image = (ImageView) getView().findViewById(R.id.imageDroid);
        image.setVisibility(currentVisibility);
    }




    public void setChannel(CommChannel channel) {
        this.channel = channel;
    }


}
