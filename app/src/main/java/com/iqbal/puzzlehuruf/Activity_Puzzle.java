package com.iqbal.puzzlehuruf;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iqbal.puzzlehuruf.Adapter.PuzzleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Activity_Puzzle extends AppCompatActivity {

    String[] puzzleArray = { "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","" };
    ArrayList<String> puzzleArrayList,puzzleArrayListAsli;
    RecyclerView rv_puzzle;
    PuzzleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        getSupportActionBar().setTitle("Puzzle Game");

        rv_puzzle = findViewById(R.id.rv_puzzle);
        acakPuzzle();


    }

    private void acakPuzzle(){
        puzzleArrayList = new ArrayList<>(Arrays.asList(puzzleArray));
        puzzleArrayListAsli = new ArrayList<>(Arrays.asList(puzzleArray));
        Collections.shuffle(puzzleArrayList);
        rv_puzzle.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new PuzzleAdapter(puzzleArrayList,puzzleArrayListAsli,getApplication());
        mAdapter.notifyDataSetChanged();
        rv_puzzle.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback =
                new ItemMoveCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rv_puzzle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.ulangi){
            Intent i = new Intent(getApplicationContext(), Activity_Puzzle.class);
            finish();
            overridePendingTransition(0, 0);
            startActivity(i);
            overridePendingTransition(0, 0);
        }else if(item.getItemId()==R.id.keluar){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Anda Ingin Keluar Dari Aplikasi?");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
//                        finish();
                    finish();
                }
            });
            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }

        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            mAdapter.clear();
            acakPuzzle();
        }
    }
}
