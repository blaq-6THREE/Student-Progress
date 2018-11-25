package com.blaq.hylton.studentprogress;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.InputType;
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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GradePointAverage extends Fragment
{
    private Button mButton;
    private FloatingActionButton fab;
    private LinearLayout masterLinearLayout;
    private TextView mGPATextView;

    private int counter;
    private int creditsID;
    private int spinnerID;

    private ArrayList<EditText> mEditTextList;
    private ArrayList<Spinner> mSpinnerList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_grade_point_average, container, false);

        mButton = view.findViewById(R.id.button);
        fab = view.findViewById(R.id.floating_action_button);
        mGPATextView = view.findViewById(R.id.grade_point_average_textview);
        mGPATextView.setVisibility(View.GONE);
        masterLinearLayout = view.findViewById(R.id.linear_layout_master);

        creditsID = 0;
        spinnerID = 0;
        counter = masterLinearLayout.getChildCount();

        mEditTextList = new ArrayList<>();
        mSpinnerList = new ArrayList<>();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (masterLinearLayout.getChildCount() >= 10)
                {
                    Snackbar mySnackbar = Snackbar.make(view, "10 Classes is MAX", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                    mButton.setEnabled(false);
                }
                else
                {
                    counter = counter + 1;
                    createView(masterLinearLayout, counter, mEditTextList, mSpinnerList);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGPATextView.setText("" + calculateGPA());
                mGPATextView.setVisibility(View.VISIBLE);
                calculateGPA();
            }
        });

        return view;
    }

    private void createView(LinearLayout bossLayout, int counter, ArrayList<EditText> mEditTextList, ArrayList<Spinner> mSpinnerList)
    {
        // This creates the ID for all the views created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            creditsID = View.generateViewId();
            spinnerID = View.generateViewId() * 10;
        }

        //Create linear layout first
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(4, 4, 4, 4);

        //Create Course EditText
        EditText courseEditText = new EditText(getContext());
        courseEditText.setWidth(260);
        courseEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        courseEditText.setHint("Course " + counter);

        //Create Spinner and populate it
        Spinner spinner = new Spinner(getContext());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.grade_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPadding(4, 0, 4, 0);
        spinner.setId(spinnerID);

        //Creating the Credit EditText
        String hint = "Credit " + counter;
        EditText creditEditText = new EditText(getContext());
        creditEditText.setHint(hint);
        creditEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        creditEditText.setWidth(420);
        creditEditText.setId(creditsID);

        // Adding to the Child Layout
        linearLayout.addView(courseEditText);
        linearLayout.addView(spinner);
        linearLayout.addView(creditEditText);

        //Sets new ID to newly created EditText field
        mEditTextList.add(counter - 1, creditEditText);
        mSpinnerList.add(counter - 1, spinner);

        bossLayout.addView(linearLayout);
    }

    public double calculateGPA()
    {
        NumberFormat numberFormat = new DecimalFormat("#0.00");
        double gradePoints = 0;
        int totalCredits = 0;
        double gpa;

        double a;
        double b;

        for (int i = 0; i < masterLinearLayout.getChildCount(); i++)
        {
            a = Integer.valueOf(mEditTextList.get(i).getText().toString());
            b = gradeValueFromSpinner(mSpinnerList.get(i).getSelectedItem().toString());

            //FOR EACH CLASS = credit * grade
            gradePoints = gradePoints + a * b;

            totalCredits = totalCredits + Integer.valueOf(mEditTextList.get(i).getText().toString());   //FOR EACH CLASS = sum credits up
        }

        gpa = gradePoints / totalCredits;

        Log.i("FAB", "onClick: totalCredits " + totalCredits);
        Log.i("FAB", "onClick: gradePoints " + gradePoints);
        Log.i("FAB", "onClick: gpa " + gpa);

        return Double.valueOf(numberFormat.format(gpa));
    }

    public double gradeValueFromSpinner(String letterGrade)
    {
        switch (letterGrade)
        {
            case "A":
                return 4.0;
            case "A-":
                return 3.7;
            case "B+":
                return 3.3;
            case "B":
                return 3.0;
            case "B-":
                return 2.7;
            case "C+":
                return 2.3;
            case "C":
                return 2.0;
            case "C-":
                return 1.7;
            case "D+":
                return 1.3;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return 0.0;
        }
    }
}
