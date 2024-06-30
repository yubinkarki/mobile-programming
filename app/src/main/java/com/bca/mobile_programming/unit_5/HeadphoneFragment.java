package com.bca.mobile_programming.unit_5;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bca.mobile_programming.R;

public class HeadphoneFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle b) {
        Log.d("Fragment", "HeadphoneFragment - onCreateView");
        return inflater.inflate(R.layout.unit_5_headphone, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("Fragment", "HeadphoneFragment - onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle b) {
        super.onCreate(b);
        Log.d("Fragment", "HeadphoneFragment - onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragment", "HeadphoneFragment - onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment", "HeadphoneFragment - onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment", "HeadphoneFragment - onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragment", "HeadphoneFragment - onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment", "HeadphoneFragment - onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment", "HeadphoneFragment - onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment", "HeadphoneFragment - onDetach");
    }
}
