package com.example.libraryapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.R;
import com.example.libraryapp.UpdateActivity;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList book_id, book_title, book_author, book_pages;

    public Adapter(Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_pages) {
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, final int position) {
        holder.tv_id.setText(String.valueOf(book_id.get(position)));
        holder.tv_title.setText(String.valueOf(book_title.get(position)));
        holder.tv_author.setText(String.valueOf(book_author.get(position)));
        holder.tv_pages.setText(String.valueOf(book_pages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateActivity.class);
                i.putExtra("id", String.valueOf(book_id.get(position)));
                i.putExtra("title", String.valueOf(book_title.get(position)));
                i.putExtra("author", String.valueOf(book_author.get(position)));
                i.putExtra("pages", String.valueOf(book_pages.get(position)));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id, tv_title, tv_author, tv_pages;
        LinearLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_book_id);
            tv_title = itemView.findViewById(R.id.tv_book_title);
            tv_author = itemView.findViewById(R.id.tv_book_author);
            tv_pages = itemView.findViewById(R.id.tv_book_pages);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
