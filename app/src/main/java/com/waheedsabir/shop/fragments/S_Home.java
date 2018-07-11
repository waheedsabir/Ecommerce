package com.waheedsabir.shop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waheedsabir.shop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class S_Home extends Fragment {


    public S_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.s__home, container, false);
    }

}
