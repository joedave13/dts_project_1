package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libraryapp.helpers.DatabaseHelper;

public class AddActivity extends AppCompatActivity {
    EditText edtTitle, edtAuthor, edtPages;
    Button btnAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtTitle = findViewById(R.id.edt_add_title);
        edtAuthor = findViewById(R.id.edt_add_author);
        edtPages = findViewById(R.id.edt_add_pages);
        btnAddBook = findViewById(R.id.btn_add_book);

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtTitle.getText().toString().trim()) && TextUtils.isEmpty(edtAuthor.getText().toString().trim()) && TextUtils.isEmpty(edtPages.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "All field must be filled!", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(edtTitle.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "Title is required!", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(edtAuthor.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "Author is required!", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(edtPages.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "Pages is required!", Toast.LENGTH_LONG).show();
                }
                else {
                    String title = edtTitle.getText().toString().trim();
                    String author = edtAuthor.getText().toString().trim();
                    int pages = Integer.parseInt(edtPages.getText().toString().trim());
                    DatabaseHelper helper = new DatabaseHelper(AddActivity.this);

                    helper.addBook(title, author, pages);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}