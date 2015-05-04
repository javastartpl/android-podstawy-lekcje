package pl.javastart.ap.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("framgent", "super.onAttach(activity)");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("framgent", "onCreate(Bundle savedInstanceState)");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("framgent", "onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("framgent", "onActivityCreated(Bundle savedInstanceState)");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("framgent", " onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("framgent", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("framgent", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("framgent", "onStop");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("framgent", "onSaveInstanceState(Bundle outState)");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("framgent", "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("framgent", "onDetach()");
    }
}
