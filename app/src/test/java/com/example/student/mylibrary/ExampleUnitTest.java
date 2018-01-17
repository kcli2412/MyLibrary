package com.example.student.mylibrary;

import com.example.student.mylibrary.data.Book;
import com.example.student.mylibrary.data.BookDAO;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_addBook() throws Exception {
        BookDAO dao = new BookDAO();
        dao.add(new Book(1, "Android Book"));
        assertEquals(1, dao.getList().size());
    }
}