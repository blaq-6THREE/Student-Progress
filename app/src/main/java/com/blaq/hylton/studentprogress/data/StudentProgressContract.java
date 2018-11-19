package com.blaq.hylton.studentprogress.data;

import android.provider.BaseColumns;

public class StudentProgressContract
{
    public StudentProgressContract() {}

    public static final class GradeEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "gpa";
    }
}
