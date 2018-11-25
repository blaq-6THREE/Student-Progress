package com.blaq.hylton.studentprogress;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    private Spinner mSpinner1;
    private Spinner mSpinner2;
    private Spinner mSpinner3;
    private Spinner mSpinner4;

    private Button mButton;
    private LinearLayout masterLinearLayout;
    private int counter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_grade_point_average, container, false);

        mSpinner1 = view.findViewById(R.id.grades_spinner_1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R
                .array.grade_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner1.setAdapter(adapter1);

        mSpinner2 = view.findViewById(R.id.grades_spinner_2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R
                .array.grade_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner2.setAdapter(adapter2);

        mSpinner3 = view.findViewById(R.id.grades_spinner_3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(), R
                .array.grade_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner3.setAdapter(adapter3);

        mSpinner4 = view.findViewById(R.id.grades_spinner_4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getContext(), R
                .array.grade_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner4.setAdapter(adapter4);

        mButton = view.findViewById(R.id.button);
        masterLinearLayout = view.findViewById(R.id.linear_layout_master);
        counter = masterLinearLayout.getChildCount();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (masterLinearLayout.getChildCount() >= 10)
                {
                    Snackbar mySnackbar = Snackbar.make(view, "10 Classes is MAX", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                }
                else
                {
                    counter = counter + 1;
                    createView(masterLinearLayout, counter);
                    Log.d("Main Activity", "onClick: " + masterLinearLayout.getChildCount());
                }

            }
        });

        return view;
    }

    private void createView(LinearLayout bossLayout, int counter)
    {
        //Create linear layout first
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        //Create Course EditText
        int id = 0;
        EditText courseEditText = new EditText(getContext());
        courseEditText.setWidth(260);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            id = View.generateViewId();
            courseEditText.setId(id);
            Log.d("Main Activity", "createView: " + id);
        }
        courseEditText.setHint("Course " + counter);

        //Create Spinner and populate it
        Spinner spinner = new Spinner(getContext());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.grade_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPadding(4, 0, 4, 0);

        //Create Credit EditText
        String hint = "Credit " + counter;
        EditText creditEditText = new EditText(getContext());
        creditEditText.setHint(hint);
        creditEditText.setWidth(420);
        //creditEditText.setId(Integer.valueOf(hint));

        // Adding to the Child Layout
        linearLayout.addView(courseEditText);
        linearLayout.addView(spinner);
        linearLayout.addView(creditEditText);

        bossLayout.addView(linearLayout);
    }
}
