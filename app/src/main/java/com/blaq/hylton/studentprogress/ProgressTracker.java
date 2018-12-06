package com.blaq.hylton.studentprogress;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ProgressTracker extends Fragment
{
    private Button mAddAssignButton;
    private Button mAddTestButon;
    private Button mAddQuizButon;
    private Button mAddOtherButon;

    private Button mDeleteClassButton;
    private Button mDeleteTestButon;
    private Button mDeleteQuizButon;
    private Button mDeleteOtherButon;

    private FloatingActionButton floatingActionButtonProgress;
    private ScrollView scrollView;

    private LinearLayout masterLinearLayout;
    private LinearLayout masterLinearLayout2;
    private LinearLayout masterLinearLayout3;
    private LinearLayout masterLinearLayout4;
    private TextView mProgressGradeTextView;

    private int counter;
    private int counter2;
    private int counter3;
    private int counter4;

    private int persentageID;
    private int assignmentID;


    private ArrayList<EditText> mEditTextList;
    private ArrayList<EditText> mSpinnerList;

    private ArrayList<EditText> mEditTextList2;
    private ArrayList<EditText> mSpinnerList2;

    private ArrayList<EditText> mEditTextList3;
    private ArrayList<EditText> mSpinnerList3;

    private ArrayList<EditText> mEditTextList4;
    private ArrayList<EditText> mSpinnerList4;

    // main Create View Which create the whole app page
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_progress_tracker, container, false);

        scrollView = view.findViewById(R.id.scrollview);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        mAddAssignButton = view.findViewById(R.id.addHomework);
        mAddTestButon = view.findViewById(R.id.addTest);
        mAddQuizButon = view.findViewById(R.id.addQuiz);
        mAddOtherButon = view.findViewById(R.id.addOther);

        mDeleteClassButton = view.findViewById(R.id.delete_button);
        mDeleteTestButon = view.findViewById(R.id.delete_test2);
        mDeleteQuizButon = view.findViewById(R.id.delete_quiz3);
        mDeleteOtherButon = view.findViewById(R.id.delete_other4);

        mProgressGradeTextView = view.findViewById(R.id.progress_tracker_textView);
        mProgressGradeTextView.setVisibility(View.GONE);
        floatingActionButtonProgress = view.findViewById(R.id.floating_action_button_progress);

        masterLinearLayout = view.findViewById(R.id.linear_layout_master);
        masterLinearLayout2 = view.findViewById(R.id.linear_layout_master2);
        masterLinearLayout3 = view.findViewById(R.id.linear_layout_master3);
        masterLinearLayout4 = view.findViewById(R.id.linear_layout_master4);

        persentageID = 0;
        assignmentID = 0;
        counter = masterLinearLayout.getChildCount();
        counter2 = masterLinearLayout2.getChildCount();
        counter3 = masterLinearLayout3.getChildCount();
        counter4 = masterLinearLayout4.getChildCount();

        mEditTextList = new ArrayList<>();
        mSpinnerList = new ArrayList<>();

        mEditTextList2 = new ArrayList<>();
        mSpinnerList2 = new ArrayList<>();

        mEditTextList3 = new ArrayList<>();
        mSpinnerList3 = new ArrayList<>();

        mEditTextList4 = new ArrayList<>();
        mSpinnerList4 = new ArrayList<>();

        // first linearLayout when addHomework Button is clicked
        mAddAssignButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (masterLinearLayout.getChildCount() >= 10)
                {
                    Snackbar mySnackbar = Snackbar.make(view, "10 Classes is MAX", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                    mAddAssignButton.setEnabled(false);
                    mDeleteClassButton.setEnabled(true);

                }
                else
                {
                    counter = counter + 1;
                    mDeleteClassButton.setEnabled(true);
                    mAddAssignButton.setEnabled(true);
                    createView(masterLinearLayout, counter, mEditTextList, mSpinnerList);
                }
            }
        });

        // Second linearLayout when AddTest Button is clicked
        mAddTestButon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (masterLinearLayout2.getChildCount() >= 10)
                {
                    Snackbar mySnackbar = Snackbar.make(view, "10 Classes is MAX", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                    mAddTestButon.setEnabled(false);
                    mDeleteTestButon.setEnabled(true);
                }
                else
                {
                    counter2 = counter2 + 1;
                    mAddTestButon.setEnabled(true);
                    mDeleteTestButon.setEnabled(true);
                    TestView(masterLinearLayout2, counter2, mEditTextList2, mSpinnerList2);
                }
            }
        });

        // Third linearLayout when addQuiz Button is clicked
        mAddQuizButon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (masterLinearLayout3.getChildCount() >= 10)
                {
                    Snackbar mySnackbar = Snackbar.make(view, "10 Classes is MAX", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                    mAddQuizButon.setEnabled(false);
                    mDeleteQuizButon.setEnabled(true);

                }
                else
                {
                    counter3 = counter3 + 1;
                    mAddQuizButon.setEnabled(true);
                    mDeleteQuizButon.setEnabled(true);
                    QuizView(masterLinearLayout3, counter3, mEditTextList3, mSpinnerList3);
                }
            }
        });

        // Fourth linearLayout when addOther Button is clicked
        mAddOtherButon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (masterLinearLayout4.getChildCount() >= 10)
                {
                    Snackbar mySnackbar = Snackbar.make(view, "10 Classes is MAX", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                    mAddOtherButon.setEnabled(false);
                    mDeleteOtherButon.setEnabled(true);
                }
                else
                {
                    counter4 = counter4 + 1;
                    mAddOtherButon.setEnabled(true);
                    mAddOtherButon.setEnabled(true);
                    OtherView(masterLinearLayout4, counter4, mEditTextList4, mSpinnerList4);
                }
            }
        });

        // HW Delete button deletes a HW view created
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
                    mAddAssignButton.setEnabled(true);
                    removeView(masterLinearLayout, counter);
                }
            }
        });

        // TestDelete button deletes a Test view created
        mDeleteTestButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (masterLinearLayout2.getChildCount() <= 0)
                {
                    Snackbar snackbar = Snackbar.make(view, "There are 0 Classes", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    mDeleteTestButon.setEnabled(false);
                }
                else
                {
                    // Reduce the counter by 1 and remove view
                    counter2 = counter2 - 1;
                    mDeleteTestButon.setEnabled(true);
                    removeView2(masterLinearLayout2, counter2);
                }
            }
        });

        // QUizDelete button deletes a Quiz view created
        mDeleteQuizButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (masterLinearLayout3.getChildCount() <= 0)
                {
                    Snackbar snackbar = Snackbar.make(view, "There are 0 Classes", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    mDeleteQuizButon.setEnabled(false);
                }
                else
                {
                    // Reduce the counter by 1 and remove view
                    counter3 = counter3 - 1;
                    mDeleteQuizButon.setEnabled(true);
                    removeView3(masterLinearLayout3, counter3);
                }
            }
        });

        // otherDelete button deletes a Other view created
        mDeleteOtherButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (masterLinearLayout4.getChildCount() <= 0)
                {
                    Snackbar snackbar = Snackbar.make(view, "There are 0 Classes", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    mDeleteOtherButon.setEnabled(false);
                }
                else
                {
                    // Reduce the counter by 1 and remove view
                    counter4 = counter4 - 1;
                    mDeleteOtherButon.setEnabled(true);
                    removeView4(masterLinearLayout4, counter4);
                }
            }
        });

        // Calculate button which calculates everything
        floatingActionButtonProgress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mProgressGradeTextView.setText(String.valueOf(calculateGradeAverage()));
                mProgressGradeTextView.setVisibility(View.VISIBLE);
                calculateGradeAverage();
            }
        });

        return view;
    }

    // This deletes the linearLayout4 the were created programmatically on click
    private void removeView(LinearLayout masterLinearLayout, int counter)
    {
        masterLinearLayout.removeViewAt(counter);
    }

    // This deletes the linearLayout2 the were created programmatically on click
    private void removeView2(LinearLayout masterLinearLayout2, int counter2)
    {
        masterLinearLayout2.removeViewAt(counter2);
    }
    // This deletes the LinearLayout3 the were created programmatically on click
    private void removeView3(LinearLayout masterLinearLayout3, int counter3)
    {
        masterLinearLayout3.removeViewAt(counter3);
    }

    // This deletes the LinearLayout4 the were created programmatically on click
    private void removeView4(LinearLayout masterLinearLayout4, int counter4)
    {
        masterLinearLayout4.removeViewAt(counter4);
    }

    // creating a homeWork LinearLayout and all the editText that goes in it
    private void createView(LinearLayout bossLayout, int counter, ArrayList<EditText> mEditTextList, ArrayList<EditText> mSpinnerList)
    {
        // This creates the ID for all the views created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            persentageID = View.generateViewId();
            assignmentID = View.generateViewId() * 10;
        }

        //Create linear layout first
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(4, 4, 4, 4);

        //Create assignment EditText
        EditText assignmentEditText = new EditText(getContext());
        assignmentEditText.setWidth(310);
        assignmentEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        assignmentEditText.setHint("HomeWork " + counter);

        //Creating the hW EditText
        String hint = "HW Score ";
        EditText assignmentGradeEditText = new EditText(getContext());
        assignmentGradeEditText.setHint(hint);
        assignmentGradeEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        assignmentGradeEditText.setWidth(310);
        assignmentGradeEditText.setId(assignmentID);

        //Creating the HW persantage EditText
        String hintPercent = "percentage";
        EditText creditEditText = new EditText(getContext());
        creditEditText.setHint(hintPercent);
        creditEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        creditEditText.setWidth(310);
        creditEditText.setId(persentageID);

        //Percentage symbol text
        TextView percantagetext = new TextView(getContext());
        percantagetext.setText("%");


        // Adding to the Child Layout
        linearLayout.addView(assignmentEditText);
        linearLayout.addView(assignmentGradeEditText);
        linearLayout.addView(creditEditText);
        linearLayout.addView((percantagetext));



        //Sets new ID to newly created EditText field
        mSpinnerList.add(counter - 1, assignmentGradeEditText);
        mEditTextList.add(counter - 1, creditEditText);

        bossLayout.addView(linearLayout);
    }

    // creating a Test LinearLayout and all the editText that goes in it
    private void TestView(LinearLayout bossLayout2, int counter2, ArrayList<EditText> mEditTextList2, ArrayList<EditText> mSpinnerList2)
    {
        // This creates the ID for all the views created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            persentageID = View.generateViewId();
            assignmentID = View.generateViewId() * 10;
        }

        //Create linear layout first
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(4, 4, 4, 4);

        //Create assignment EditText
        EditText assignmentEditText = new EditText(getContext());
        assignmentEditText.setWidth(300);
        assignmentEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        assignmentEditText.setHint("Test " + counter2);

        //Creating the score EditText
        String hint = "Test Score " + counter2;
        EditText assignmentGradeEditText = new EditText(getContext());
        assignmentGradeEditText.setHint(hint);
        assignmentGradeEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        assignmentGradeEditText.setWidth(300);
        assignmentGradeEditText.setId(assignmentID);

        //Creating the percentage EditText
        String hintPercent = "percentage";
        EditText creditEditText = new EditText(getContext());
        creditEditText.setHint(hintPercent);
        creditEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        creditEditText.setWidth(300);
        creditEditText.setId(persentageID);

        // add % symbol
        TextView percantagetext = new TextView(getContext());
        percantagetext.setText("%");

        // Adding to the Child Layout
        linearLayout.addView(assignmentEditText);
        linearLayout.addView(assignmentGradeEditText);
        linearLayout.addView(creditEditText);
        linearLayout.addView(percantagetext);


        //Sets new ID to newly created EditText field
        mSpinnerList2.add(counter2 - 1, assignmentGradeEditText);
        mEditTextList2.add(counter2 - 1, creditEditText);

        bossLayout2.addView(linearLayout);
    }

    // creating a QUiz LinearLayout and all the editText that goes in it
    private void QuizView(LinearLayout bossLayout3, int counter3, ArrayList<EditText> mEditTextList3, ArrayList<EditText> mSpinnerList3)
    {
        // This creates the ID for all the views created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            persentageID = View.generateViewId();
            assignmentID = View.generateViewId() * 10;
        }

        //Create linear layout first
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(4, 4, 4, 4);

        //Create assignment EditText
        EditText assignmentEditText = new EditText(getContext());
        assignmentEditText.setWidth(300);
        assignmentEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        assignmentEditText.setHint("Quiz " + counter3);

        //Creating the score EditText
        String hint = "Quiz Score " + counter3;
        EditText assignmentGradeEditText = new EditText(getContext());
        assignmentGradeEditText.setHint(hint);
        assignmentGradeEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        assignmentGradeEditText.setWidth(300);
        assignmentGradeEditText.setId(assignmentID);

        //Creating the persantage EditText
        String hintPercent = "percentage";
        EditText creditEditText = new EditText(getContext());
        creditEditText.setHint(hintPercent);
        creditEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        creditEditText.setWidth(300);
        creditEditText.setId(persentageID);

        // creating % symbol
        TextView percantagetext = new TextView(getContext());
        percantagetext.setText("%");

        // Adding to the Child Layout
        linearLayout.addView(assignmentEditText);
        linearLayout.addView(assignmentGradeEditText);
        linearLayout.addView(creditEditText);
        linearLayout.addView(percantagetext);

        //Sets new ID to newly created EditText field
        mSpinnerList3.add(counter3 - 1, assignmentGradeEditText);
        mEditTextList3.add(counter3 - 1, creditEditText);

        bossLayout3.addView(linearLayout);
    }

    // creating a Other LinearLayout and all the editText that goes in it
    private void OtherView(LinearLayout bossLayout4, int counter4, ArrayList<EditText> mEditTextList4, ArrayList<EditText> mSpinnerList4)
    {
        // This creates the ID for all the views created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            persentageID = View.generateViewId();
            assignmentID = View.generateViewId() * 10;
        }

        //Create linear layout first
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(4, 4, 4, 4);

        //Create assignment EditText
        EditText assignmentEditText = new EditText(getContext());
        assignmentEditText.setWidth(260);
        assignmentEditText.setTextSize(20f);
        assignmentEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        assignmentEditText.setHint("Other " + counter4);

        //Creating the Credit EditText
        String hint = "Other Score " + counter4;
        EditText assignmentGradeEditText = new EditText(getContext());
        assignmentGradeEditText.setHint(hint);
        assignmentGradeEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        assignmentGradeEditText.setWidth(300);
        assignmentGradeEditText.setId(assignmentID);

        //Creating the Credit EditText
        String hintPercent = "percentage";
        EditText creditEditText = new EditText(getContext());
        creditEditText.setHint(hintPercent);
        creditEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        creditEditText.setWidth(420);
        creditEditText.setId(persentageID);

        // creating % symbol
        TextView percantagetext = new TextView(getContext());
        percantagetext.setText("%");

        // Adding to the Child Layout
        linearLayout.addView(assignmentEditText);
        linearLayout.addView(assignmentGradeEditText);
        linearLayout.addView(creditEditText);
        linearLayout.addView(percantagetext);

        //Sets new ID to newly created EditText field
        mSpinnerList4.add(counter4 - 1, assignmentGradeEditText);
        mEditTextList4.add(counter4 - 1, creditEditText);

        bossLayout4.addView(linearLayout);
    }

    // Calculating all the data gathered from the users
    public double calculateGradeAverage()
    {
        NumberFormat numberFormat = (new DecimalFormat("#0.00"));
        double totalGrade = 0;
        double totalGradeHomA = 0;
        double totalGradeHomB = 0;

        double testGrade2a = 0;
        double testGrade2b = 0;

        double testGrade3a = 0;
        double testGrade3b = 0;

        double testGrade4a = 0;
        double testGrade4b = 0;

        double masterGradeA = 0;
        double masterGradeB = 0;
        double gpa;

        double a;
        double b;

        // loop through Homework layout to get the data
        for (int i = 0; i < masterLinearLayout.getChildCount(); i++)
        {
            if (TextUtils.isEmpty(mSpinnerList.get(i).getText()) || TextUtils.isEmpty(mEditTextList.get(i).getText()))
            {
                mSpinnerList.get(i).setHint("Fill Field");
                mSpinnerList.get(i).setHintTextColor(Color.RED);

                mEditTextList.get(i).setHint("Fill Field");
                mEditTextList.get(i).setHintTextColor(Color.RED);
            }

            else
            {

                a = Double.valueOf(mSpinnerList.get(i).getText().toString());
                b = Double.valueOf(mEditTextList.get(i).getText().toString());

                totalGradeHomA = totalGradeHomA + ((a / 100) * b);
                totalGradeHomB = totalGradeHomB + b;


            }
        }
        // loop through Test layout to get the data
        for (int i = 0; i < masterLinearLayout2.getChildCount(); i++)
        {
            if (TextUtils.isEmpty(mSpinnerList2.get(i).getText()) || TextUtils.isEmpty(mEditTextList2.get(i).getText()))
            {
                mSpinnerList2.get(i).setHint("Fill Field");
                mSpinnerList2.get(i).setHintTextColor(Color.RED);

                mEditTextList2.get(i).setHint("Fill Field");
                mEditTextList2.get(i).setHintTextColor(Color.RED);
            }

            else {
                a = Double.valueOf(mSpinnerList2.get(i).getText().toString());
                b = Double.valueOf(mEditTextList2.get(i).getText().toString());

                testGrade2a = testGrade2a + ((a / 100) * b);
                testGrade2b = testGrade2b + b;

            }
        }

        // loop through quiz layout to get the data
        for (int i = 0; i < masterLinearLayout3.getChildCount(); i++)
        {
            if (TextUtils.isEmpty(mSpinnerList3.get(i).getText()) || TextUtils.isEmpty(mEditTextList3.get(i).getText()))
            {
                mSpinnerList3.get(i).setHint("Fill Field");
                mSpinnerList3.get(i).setHintTextColor(Color.RED);

                mEditTextList3.get(i).setHint("Fill Field");
                mEditTextList3.get(i).setHintTextColor(Color.RED);
            }

            else {
                a = Double.valueOf(mSpinnerList3.get(i).getText().toString());
                b = Double.valueOf(mEditTextList3.get(i).getText().toString());

                testGrade3a = testGrade3a + ((a / 100) * b);
                testGrade3b = testGrade3b + b;
            }
        }

        // loop through other layout to get the data
        for (int i = 0; i < masterLinearLayout4.getChildCount(); i++)
        {
            if (TextUtils.isEmpty(mSpinnerList4.get(i).getText()) || TextUtils.isEmpty(mEditTextList4.get(i).getText()))
            {
                mSpinnerList4.get(i).setHint("Fill Field");
                mSpinnerList4.get(i).setHintTextColor(Color.RED);

                mEditTextList4.get(i).setHint("Fill Field");
                mEditTextList4.get(i).setHintTextColor(Color.RED);
            }

            else
            {
                a = Double.valueOf(mSpinnerList4.get(i).getText().toString());
                b = Double.valueOf(mEditTextList4.get(i).getText().toString());

                testGrade4a = testGrade4a + ((a / 100) * b);
                testGrade4b = testGrade4b + b;
            }
        }

        masterGradeA = totalGradeHomA + testGrade2a + testGrade3a + testGrade4a;
        masterGradeB = totalGradeHomB + testGrade2b + testGrade3b + testGrade4b;

        if(masterGradeB > 100)
        {
            Snackbar mySnackbar = Snackbar.make(getView(), "Total percentage cant be more than 100%", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        }

        gpa = ((masterGradeA / masterGradeB) * 100);

        return Double.valueOf(numberFormat.format(gpa));
    }

}