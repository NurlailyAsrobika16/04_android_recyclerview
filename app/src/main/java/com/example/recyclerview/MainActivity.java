package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList mWordList = new LinkedList<String>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private ArrayList<CatData> CatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab;
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                mWordList.addLast("+ Word " + wordListSize);
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }

        });
        setMotorList();
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CatListAdapter CatListAdapter = new CatListAdapter(MainActivity.this, CatList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(CatListAdapter);
        CatListAdapter.setOnItemClickListener(onItemClickListener);
    }
    private void setMotorList() {
        CatList = new ArrayList<>();
        CatData data;
        data = new CatData(getString(R.string.jenis1), getString(R.string.judul1), R.drawable.gambar1, getString(R.string.ga));
        CatList.add(data);
        data = new CatData(getString(R.string.jenis2), getString(R.string.judul2 ), R.drawable.gambar2, getString(R.string.ga2));
        CatList.add(data);
        data = new CatData(getString(R.string.jenis3), getString(R.string.judul3 ), R.drawable.gambar3, getString(R.string.ga3));
        CatList.add(data);
        data = new CatData(getString(R.string.jenis4), getString(R.string.judul4 ), R.drawable.gambar4, getString(R.string.ga4));
        CatList.add(data);
    }
    public void openDetailActivity(int imageId, String details){
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("image", imageId);
        intent.putExtra("details", details);
        startActivity(intent);
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            CatData thisMotor = CatList.get(position);
            openDetailActivity(thisMotor.getImage(), thisMotor.getDetails());
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}