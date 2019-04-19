package com.python.cat.splash.funny;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.apkfuns.logutils.LogUtils;
import com.python.cat.commonlib.utils.ToastHelper;
import com.python.cat.splash.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FunnyFragment extends Fragment {


    public FunnyFragment() {
        // Required empty public constructor
    }

    public static FunnyFragment newInstance() {

        Bundle args = new Bundle();

        FunnyFragment fragment = new FunnyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_funny, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BigLayout bigLayout = view.findViewById(R.id.funny_big);

        bigLayout.setOnClickListener(v -> {
            LogUtils.e("big");
            ToastHelper.show(requireContext(), "big");
        });

        view.findViewById(R.id.funny_view)
                .setOnClickListener(v -> {

                    LogUtils.e("small");
                    ToastHelper.show(requireContext(), "small...");
                });

        ToggleButton btn = view.findViewById(R.id.funny_button);

        btn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            bigLayout.setDisableTouch(isChecked);
        });
    }
}
