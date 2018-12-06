package com.blaq.hylton.studentprogress;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
    private Button mAddClassButton;
    private Button mDeleteClassButton;

    private FloatingActionButton fab;
    private LinearLayout masterLinearLayout;
    private TextView mGPATextView;

    private int counter;
    private int creditsID;
    private int spinnerID;
    private View mView;

    private ArrayList<EditText> mEditTextList;
    private ArrayList<Spinner> mSpinnerList;

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_grade_point_average, container, false);

        mAddClassButton = mView.findViewById(R.id.button);
        mDeleteClassButton = mView.findViewById(R.id.delete_button);

        fab = mView.findViewById(R.id.floating_action_button);
        mGPATextView = mView.findViewById(R.id.grade_point_average_textview);
        mGPATextView.setVisibility(View.GONE);
        masterLinearLayout = mView.findViewById(R.id.linear_layout_master);
        masterLinearLayout.setAlpha(0.8f);
        masterLinearLayout.setBackgroundColor(Color.parseColor("#ced9dd"));

        creditsID = 0;
        spinnerID = 0;
        counter = masterLinearLayout.getChildCount();

        mEditTextList = new ArrayList<>();
        mSpinnerList = new ArrayList<>();

        mAddClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (masterLinearLayout.getChildCount() >= 10)
                {
                    Snackbar snackbar = Snackbar.make(view, "10 Classes is MAX", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    //mAddClassButton.setEnabled(false);
                    mDeleteClassButton.setEnabled(true);
                }
                else
                {
                    counter = counter + 1;
                    mDeleteClassButton.setEnabled(true);
                    mAddClassButton.setEnabled(true);
                    createView(masterLinearLayout, counter, mEditTextList, mSpinnerList);
                }
            }
        });

        // Delete button deletes a view created
        mDeleteClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (masterLinearLayout.getChildCount() <= 0)
                {
                    Snackbar snackbar = Snackbar.make(view, "There are 0 Classes", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    mDeleteClassButton.setEnabled(false);
                }
                else
                {
                    // Reduce the counter by 1 and remove view
                    counter = counter - 1;
                    mAddClassButton.setEnabled(true);
                    removeView(masterLinearLayout, counter);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGPATextView.setText(String.valueOf(calculateGPA()));
                mGPATextView.setVisibility(View.VISIBLE);
                hideKeyboardFrom(getContext(), mView);
                calculateGPA();
            }
        });

        return mView;
    }

    // This deletes the views the were created programmatically on click
    private void removeView(LinearLayout masterLinearLayout, int counter)
    {
        masterLinearLayout.removeViewAt(counter);
    }

    // This creates an EditTextView, a Spinner and a TextView upon request
    // It also assigns to the UNIQUE IDs
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
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(4, 4, 4, 4);

        //Create Course EditText
        TextView courseTextView = new TextView(getContext());
        courseTextView.setWidth(260);
        courseTextView.setTextSize(20f);
        courseTextView.setTextColor(Color.BLUE);
        courseTextView.setAllCaps(true);
        courseTextView.setInputType(InputType.TYPE_CLASS_TEXT);
        courseTextView.setHint("Course " + counter);

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
        linearLayout.addView(courseTextView);
        linearLayout.addView(spinner);
        linearLayout.addView(creditEditText);

        //Sets new ID to newly created EditText field
        mEditTextList.add(counter - 1, creditEditText);
        mSpinnerList.add(counter - 1, spinner);

        bossLayout.addView(linearLayout);
    }

    // Returns the GPA to the TextView which converts it to String
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
            if (TextUtils.isEmpty(mEditTextList.get(i).getText()))
            {
                Snackbar snackbar = Snackbar.make(mView, "Please fill all fields", Snackbar.LENGTH_LONG);
                snackbar.show();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    mEditTextList.get(i).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                }

                mEditTextList.get(i).setHint("Please Fill Field");
                mEditTextList.get(i).setHintTextColor(Color.RED);

                return 0;
            }

            else
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    mEditTextList.get(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#80d6ff")));
                }

                mEditTextList.get(i).setHint("Please Fill Field");

                a = Integer.valueOf(mEditTextList.get(i).getText().toString());
                b = gradeValueFromSpinner(mSpinnerList.get(i).getSelectedItem().toString());

                gradePoints = gradePoints + (a * b);
                totalCredits = totalCredits + Integer.valueOf(mEditTextList.get(i).getText().toString());   //FOR EACH CLASS = sum credits up
            }
        }

        gpa = gradePoints / totalCredits;

        return Double.valueOf(numberFormat.format(gpa));
    }

    // This returns the value of the Spinner
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

    // I got this bit of code from https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
    // It hides the soft keyboard upon request
    public void hideKeyboardFrom(Context context, View view)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
