package com.example.student.mylibrary.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 2018/1/17.
 */

public class BookDAO {
    Context context;
    ArrayList<Book> mylist = new ArrayList<>();
    private int BooksKeyNumber = 0;

    public BookDAO(Context context)
    {
        this.context = context;
    }

    public void setBooksKeyNumber(int id)
    {
        BooksKeyNumber = id;
    }

    public int getBooksKeyNumber()
    {
        for (int i = 0; i < mylist.size(); i++)
        {

            if (getBook(i + 1) == null)
            {
                return i + 1;
            }
        }
        return mylist.size() + 1;
    }

    private void saveFile()
    {
        File f = new File(context.getFilesDir(), "mydata.txt");
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mylist);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load()
    {
        File f = new File(context.getFilesDir(), "mydata.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Gson gson = new Gson();
            mylist = gson.fromJson(str, new TypeToken<ArrayList<Book>>(){}.getType());
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean add(Book book)
    {
        mylist.add(book);
        saveFile();
        return true;
    }

    public ArrayList<Book> getList()
    {
        load();
        return mylist;
    }

    public Book getBook(int id)
    {
        load();
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
        load();
        for (Book b:mylist)
        {
            if (b.id == book.id)
            {
                b.name = book.name;
                saveFile();
                return true;
            }
        }
        return false;
    }

    public boolean updateBook(Book book)
    {
        load();
        for (Book b:mylist)
        {
            if (b.id == book.id)
            {
                b.name = book.name;
                b.isbn = book.isbn;
                b.author = book.author;
                b.publication_date = book.publication_date;
                b.press = book.press;
                b.press = book.press;
                b.category = book.category;
                b.introduction = book.introduction;
                saveFile();
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id)
    {
        load();
        for (Book b:mylist)
        {
            if (b.id == id)
            {
                mylist.remove(b);
                saveFile();
                return true;
            }
        }
        return false;
    }
}
