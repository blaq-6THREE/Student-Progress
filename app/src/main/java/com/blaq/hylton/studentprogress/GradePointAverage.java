package com.blaq.hylton.studentprogress;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import java.util.ArrayList;

public class GradePointAverage extends Fragment
{
    private Spinner mSpinner1;

    private EditText mEditText1;

    private Button mButton;
    private FloatingActionButton fab;
    private LinearLayout masterLinearLayout;

    private int counter;
    private int creditIndex;
    private int creditsID;

    private ArrayList<Integer> idList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_grade_point_average, container, false);

        mSpinner1 = view.findViewById(R.id.grades_spinner_1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.grade_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner1.setAdapter(adapter1);

        mButton = view.findViewById(R.id.button);
        fab = view.findViewById(R.id.floating_action_button);
        masterLinearLayout = view.findViewById(R.id.linear_layout_master);

        counter = masterLinearLayout.getChildCount();
        creditsID = 0;
        creditIndex = 0;

        idList = new ArrayList<>();
        idList.add(creditIndex, R.id.credit_1);

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
                    createView(masterLinearLayout, counter, idList);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < masterLinearLayout.getChildCount(); i++)
                {
                    Log.d("FAB", "onClick: id = " + idList.get(i) + " for pos " + i);
                }
            }
        });

        return view;
    }

    private void createView(LinearLayout bossLayout, int counter, ArrayList<Integer> idList)
    {
        creditIndex = creditIndex + 1;

        //Create linear layout first
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(4, 4, 4, 4);

        //Create Course EditText
        EditText courseEditText = new EditText(getContext());
        courseEditText.setWidth(260);
        courseEditText.setHint("Course " + counter);

        //Create Spinner and populate it
        Spinner spinner = new Spinner(getContext());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.grade_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPadding(4, 0, 4, 0);

        //Create Credit EditText
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            creditsID = View.generateViewId();
        }

        String hint = "Credit " + counter;
        EditText creditEditText = new EditText(getContext());
        creditEditText.setHint(hint);
        creditEditText.setWidth(420);
        creditEditText.setId(creditsID);
        Log.d("creditEditText", "createView: " + creditsID);

        // Adding to the Child Layout
        linearLayout.addView(courseEditText);
        linearLayout.addView(spinner);
        linearLayout.addView(creditEditText);

        //Sets new ID to newly created EditText field
        idList.add(creditIndex, creditsID);

        bossLayout.addView(linearLayout);
    }

    public double calculateGPA()
    {

        return 0;
    }

    public void getAllID()
    {

    }
}
