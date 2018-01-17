package com.example.student.mylibrary.data;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/17.
 */

public class BookDAO {
    ArrayList<Book> mylist = new ArrayList<>();

    public boolean add(Book book)
    {
        mylist.add(book);
        return true;
    }

    public ArrayList<Book> getList()
    {
        return mylist;
    }

    public Book getBook(int id)
    {
        for (Book b:mylist)
        {
            if (b.id == id)
            {
                return b;
            }
        }
        return null;
    }

    public boolean updateName(Book book)
    {
        for (Book b:mylist)
        {
            if (b.id == book.id)
            {
                b.name = book.name;
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id)
    {
        for (Book b:mylist)
        {
            if (b.id == id)
            {
                mylist.remove(b);
                return true;
            }
        }
        return false;
    }
}
