package com.example.libraryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libraryapp.helpers.DatabaseHelper;

public class UpdateActivity extends AppCompatActivity {
    EditText edtTitle, edtAuthor, edtPages;
    Button btnUpdate, btnDelete;
    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtTitle = findViewById(R.id.edt_update_title);
        edtAuthor = findViewById(R.id.edt_update_author);
        edtPages = findViewById(R.id.edt_update_pages);
        btnUpdate = findViewById(R.id.btn_update_book);
        btnDelete = findViewById(R.id.btn_delete_book);

        getIntentData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtTitle.getText().toString().trim()) && TextUtils.isEmpty(edtAuthor.getText().toString().trim()) && TextUtils.isEmpty(edtPages.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "All fields must be filled!", Toast.LENGTH_LONG).show();
                    getIntentData();
                }
                else if (TextUtils.isEmpty(edtTitle.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "Title is required!", Toast.LENGTH_LONG).show();
                    getIntentData();
                }
                else if (TextUtils.isEmpty(edtAuthor.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "Author is required!", Toast.LENGTH_LONG).show();
                    getIntentData();
                }
                else if (TextUtils.isEmpty(edtPages.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "Pages is required!", Toast.LENGTH_LONG).show();
                    getIntentData();
                }
                else {
                    DatabaseHelper helper = new DatabaseHelper(UpdateActivity.this);
                    title = edtTitle.getText().toString().trim();
                    author = edtAuthor.getText().toString().trim();
                    pages = edtPages.getText().toString().trim();
                    helper.updateData(id, title, author, pages);

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteDialog();
            }
        });
    }

    public void getIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            edtTitle.setText(title);
            edtAuthor.setText(author);
            edtPages.setText(pages);
        }
        else {
            Toast.makeText(this, "No data.", Toast.LENGTH_LONG).show();
        }
    }

    public void confirmDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + "?");
        builder.setMessage("Are you sure want to delete " + title + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper helper = new DatabaseHelper(UpdateActivity.this);
                helper.deleteSingleData(id);
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}