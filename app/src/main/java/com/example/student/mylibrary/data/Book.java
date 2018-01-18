package com.example.student.mylibrary.data;

/**
 * Created by Student on 2018/1/17.
 */

public class Book {
    public int id;
    public String name;// 書名
    public String isbn;// ISBN
    public String author;// 作者
    public String publication_date;// 出版日期
    public String press;// 出版社
    public int pricing;// 定價
    public String category;// 類別
    public String introduction;// 簡介

    public int bookshelf;// 書架

    public Book(int id, String name, String isbn, String author,String publication_date,
                String press, int pricing, String category, String introduction, int bookshelf)
    {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.author = author;
        this.publication_date = publication_date;
        this.press = press;
        this.pricing = pricing;
        this.category = category;
        this.introduction = introduction;
        this.bookshelf = bookshelf;
    }
}
