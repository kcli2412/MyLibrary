package com.example.student.mylibrary.data;

import android.content.Context;

/**
 * Created by Student on 2018/1/18.
 */

public class BookDAOFactory {
    public static BookDAO getDAOInstance(Context context)
    {
        return new BookDAO(context);
    }
}
