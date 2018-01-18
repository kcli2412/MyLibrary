package com.example.student.mylibrary;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.student.mylibrary.data.Book;

import static com.example.student.mylibrary.MainActivity.dao;

public class EditActivity extends AppCompatActivity {
    EditText edit_name, edit_isbn, edit_author, edit_publication_date, edit_press;
    EditText edit_pricing, edit_category, edit_introduction;

    int id;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edit_name = findViewById(R.id.edit_name_edit);
        edit_isbn = findViewById(R.id.edit_isbn_edit);
        edit_author = findViewById(R.id.edit_author_edit);
        edit_publication_date = findViewById(R.id.edit_publication_date_edit);
        edit_press = findViewById(R.id.edit_press_edit);
        edit_pricing = findViewById(R.id.edit_pricing_edit);
        edit_category = findViewById(R.id.edit_category_edit);
        edit_introduction = findViewById(R.id.edit_introduction_edit);

        id = getIntent().getIntExtra("id", 0);
        book = dao.getBook(id);
        edit_name.setText(book.name);
        edit_isbn.setText(book.isbn);
        edit_author.setText(book.author);
        edit_publication_date.setText(book.publication_date);
        edit_press.setText(book.press);
        edit_pricing.setText(String.valueOf(book.pricing));
        edit_category.setText(book.category);
        edit_introduction.setText(book.introduction);
    }

    public  void clickBack(View v)
    {
        finish();
    }

    public void clickUpdate(View v)
    {
        int pricing = 0;
        if (!edit_pricing.getText().toString().isEmpty())
            pricing = Integer.valueOf(pricing);
        Book b = new Book(
                id,
                edit_name.getText().toString(),
                edit_isbn.getText().toString(),
                edit_author.getText().toString(),
                edit_publication_date.getText().toString(),
                edit_press.getText().toString(),
                pricing,
                edit_category.getText().toString(),
                edit_introduction.getText().toString(),
                1);
        if (dao.updateBook(b)) {
            finish();
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
            builder.setTitle("更新失敗");
            builder.setMessage("資料錯誤");
            builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }
    }

    public void clickDelete(View v)
    {

    }
}
