package com.example.android.newtabbedactivity.data;

import android.provider.BaseColumns;


public final class dataContract {
    private dataContract() {
    }

    public static final class skillEntry implements BaseColumns {

        public final static String TABLE_NAME = "skill1";
        public final static String SKILL_ID = BaseColumns._ID;
        public final static String SKILL_NAME = "name";
        public final static String SKILL_LEVEL = "level";

        public static final int LEVEL_0 = 0;
        public static final int LEVEL_1 = 1;
        public static final int LEVEL_2 = 2;


    }


}
