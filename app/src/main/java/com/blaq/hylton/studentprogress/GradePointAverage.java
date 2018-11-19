package com.blaq.hylton.studentprogress;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GradePointAverage extends Fragment
{
    private Spinner mSpinner;
    private Button mButton;
    private LinearLayout masterLinearLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_grade_point_average, container, false);

        mSpinner = view.findViewById(R.id.grades_spinner_1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.grade_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mButton = view.findViewById(R.id.button);
        masterLinearLayout = view.findViewById(R.id.linear_layout_master);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
//                TextView textView = new TextView(getContext());
//                textView.setText("New");
//
//                masterLinearLayout.addView(textView);
                createView(masterLinearLayout);
            }
        });

        return view;
    }

    private void createView(LinearLayout bossLayout)
    {
        //Create linear layout first
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        //Create EditText
        EditText courseEditText = new EditText(getContext());

        //Create Spinner and populate it
        Spinner spinner = new Spinner(getContext());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.grade_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Create EditText
        EditText creditEditText = new EditText(getContext());

        linearLayout.addView(courseEditText);
        linearLayout.addView(spinner);
        linearLayout.addView(creditEditText);

        bossLayout.addView(linearLayout);
    }
}
