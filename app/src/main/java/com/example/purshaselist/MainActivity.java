package com.example.purshaselist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText product, col,type, cost,category;
    SQLiteHelper helper;
    SQLiteDatabase database;
    Cursor cursor;

    ArrayList<State> listShape = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product = findViewById(R.id.product);
        col = findViewById(R.id.amount);
        type = findViewById(R.id.type);
        cost = findViewById(R.id.cost);
        category = findViewById(R.id.category);

        helper = new SQLiteHelper(this);
        try {
            database = helper.getWritableDatabase();
        }
        catch (SQLiteException ex){
            database = helper.getReadableDatabase();
        }
        showList();
        setRiew();
    }

    private void setRiew() {
        RecyclerView recyclerView = findViewById(R.id.list);
        StateAdapter adapter = new StateAdapter(this, listShape);
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("Range")
    private void setInitialData(Cursor cursor) {
        int curCount = cursor.getCount(), i=0;
        cursor.moveToFirst();
        while (i<curCount) {
            String PRODUCT = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_PRODUCT));
            String DATE = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_DATE));
            String COST = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_COST));
            String AMOUNT = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_AMOUNT));
            listShape.add(new State(PRODUCT, DATE, AMOUNT, COST));
            i++;
            cursor.moveToNext();
        }
        cursor.moveToLast();
        String PRODUCT = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_PRODUCT));
        String DATE = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_DATE));
        String COST = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_COST));
        String AMOUNT = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_AMOUNT));
        listShape.add(new State(PRODUCT, DATE, AMOUNT, COST));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addNote(View view){
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_COST, Double.parseDouble(cost.getText().toString()));
        values.put(SQLiteHelper.COLUMN_AMOUNT,Double.parseDouble( col.getText().toString()));
        values.put(SQLiteHelper.COLUMN_TYPE, type.getText().toString());
        values.put(SQLiteHelper.COLUMN_DATE, LocalDate.now().toString());
        values.put(SQLiteHelper.COLUMN_PRODUCT, product.getText().toString());
        values.put(SQLiteHelper.COLUMN_CATEGORY, category.getText().toString());
        database.insert(SQLiteHelper.DATABASE_TABLE,null, values);
    }

    public void showList(){
        cursor  = database.rawQuery("SELECT * FROM " + SQLiteHelper.DATABASE_TABLE, null);
        if(cursor.getCount()>0){
            setInitialData(cursor);
        }
        cursor.close();
    }

    @Override
    protected void onStop() {
        super.onStop();
        database.close();
    }
}