package com.serenity.view.playlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.android.serenityapp.R;

public class SearchActivity extends AppCompatActivity {

    private Button backBtn;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        backBtn = findViewById(R.id.search_back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //when hitting the search button, trigger that method
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            //when query text changed, trigger that method
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
}
