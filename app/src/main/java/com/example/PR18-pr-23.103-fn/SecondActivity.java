package com.example.PR18-pr-23.103-fn;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SecondActivity extends AppCompatActivity {

    TextView tvData;
    Button btnBack;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvData = findViewById(R.id.tvData);
        btnBack = findViewById(R.id.btnBack);

        // используем DBHelper из MainActivity
        dbHelper = new DBHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);

        StringBuilder data = new StringBuilder();

        if (c.moveToFirst()) {
            do {
                data.append("ID: ").append(c.getInt(0)).append("\n");
                data.append("Name: ").append(c.getString(1)).append("\n");
                data.append("Email: ").append(c.getString(2)).append("\n");
                data.append("Num1: ").append(c.getString(3)).append("\n");
                data.append("Num2: ").append(c.getString(4)).append("\n\n");
            } while (c.moveToNext());
        } else {
            data.append("Нет данных");
        }

        tvData.setText(data.toString());

        c.close();
        dbHelper.close();

        // кнопка назад
        btnBack.setOnClickListener(v -> finish());
    }
}
