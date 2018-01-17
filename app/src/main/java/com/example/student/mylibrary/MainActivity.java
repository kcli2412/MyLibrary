package com.example.student.mylibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                //Intent it = new Intent(MainActivity.this, AddActivity.class);
                //startActivity(it);
                //break;
            case R.id.menu_options:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        String str_url = "http://news.pchome.com.tw/rss/new";
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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
