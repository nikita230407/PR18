package com.example.PR18-pr-23.103-fn;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etEmail, etNum1, etNum2;
    DBHelper dbHelper;

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);

        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnRead).setOnClickListener(this);
        findViewById(R.id.btnClear).setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String num1 = etNum1.getText().toString();
        String num2 = etNum2.getText().toString();

        if (v.getId() == R.id.btnAdd) {

            cv.put("name", name);
            cv.put("email", email);
            cv.put("num1", num1);
            cv.put("num2", num2);

            long id = db.insert("mytable", null, cv);
            Log.d(LOG_TAG, "Добавлено ID = " + id);

        } else if (v.getId() == R.id.btnRead) {

            startActivity(new Intent(this, SecondActivity.class));

        } else if (v.getId() == R.id.btnClear) {

            int count = db.delete("mytable", null, null);
            Log.d(LOG_TAG, "Удалено строк = " + count);
        }
    }
}
