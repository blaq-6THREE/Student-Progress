package com.blaq.hylton.studentprogress;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProgressTracker.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProgressTracker#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgressTracker extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress_tracker, container, false);

        return view;
    }
}
