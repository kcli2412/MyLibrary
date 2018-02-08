package com.example.student.mylibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.student.mylibrary.data.Book;
import com.example.student.mylibrary.data.BookDAO;
import com.example.student.mylibrary.data.BookDAOFactory;
import com.example.student.mylibrary.data.ScanData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static BookDAO dao;
    public static ScanData scan;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = BookDAOFactory.getDAOInstance(MainActivity.this);
        lv = findViewById(R.id.listView);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Intent it = new Intent(MainActivity.this, EditActivity.class);
//                it.putExtra("id", dao.getList().get(position).id);
//                startActivity(it);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*ArrayList<String> data = new ArrayList<>();
        for (Book b:dao.getList())
        {
            data.add(b.name);
        }
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);*/

        if (scan != null && scan.isScan)
        {
            Intent it = new Intent(MainActivity.this, AddActivity.class);
            startActivity(it);
            return;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_add:
                //Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                //startActivity(intent);
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_edit:
                Intent it = new Intent(MainActivity.this, AddActivity.class);
                startActivity(it);
                break;
            case R.id.menu_options:
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        String str_url = "http://isbn.ncl.edu.tw/NCL_ISBNNet/main_DisplayResults.php?PHPSESSID=n9aq8223aaj11u744k50pvemr1&Pact=DisplayAll";
                        URL url = null;
                        try {
                            url = new URL(str_url);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.connect();
                            InputStream inputStream = conn.getInputStream();
                            InputStreamReader isr = new InputStreamReader(inputStream);
                            BufferedReader br = new BufferedReader(isr);
                            StringBuilder sb = new StringBuilder();
                            String str;
                            while((str = br.readLine()) != null)
                            {
                                sb.append(str);
                            }
                            String str1 = sb.toString();
                            Log.d("NET", str1);
                            br.close();
                            isr.close();
                            inputStream.close();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
