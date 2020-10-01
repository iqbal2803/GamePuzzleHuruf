package com.iqbal.puzzlehuruf.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.iqbal.puzzlehuruf.Activity_Puzzle;
import com.iqbal.puzzlehuruf.ItemMoveCallback;
import com.iqbal.puzzlehuruf.R;

import java.util.ArrayList;
import java.util.Collections;

public class PuzzleAdapter extends RecyclerView.Adapter<PuzzleAdapter.MyViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {
    private ArrayList<String> data;
    private ArrayList<String> dataAsli;
    private Context mContexl;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private Button btn_puzzle;
        View rowView;

        public MyViewHolder(View itemView) {
            super(itemView);

            rowView = itemView;
            btn_puzzle = itemView.findViewById(R.id.btn_huruf);
        }
    }

    public PuzzleAdapter(ArrayList<String> data,ArrayList<String> dataAsli, Context mContexl) {
        this.data = data;
        this.dataAsli = dataAsli;
        this.mContexl = mContexl;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_puzzle, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.btn_puzzle.setText(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public ArrayList<String> getAllData() {
        return data;
    }

    public void clear() {
        data.clear ();
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
      //int cekPosisiPuzzleKosong = data.indexOf("");
      int atas=0,kiri=0,kanan=0,bawah=0;
      atas=fromPosition-4;
      if(fromPosition%4!=0) {
          kiri = fromPosition - 1;
      }
      if(fromPosition%4!=3) {
          kanan = fromPosition + 1;
      }
      bawah=fromPosition+4;

//        if() misal posisi index 0 ,     1 4
//        if() misal posisi index 1 ,   0 2 5
//        if() misal posisi index 2 ,   1 3 6
//        if() misal posisi index 3 ,   2   7
//        if() misal posisi index 4 , 0   5 8
//        if() misal posisi index 5 , 1 4 6 9
//        if() misal posisi index 6 , 2 5 7 10
//        if() misal posisi index 7 , 3 6   11
//        if() misal posisi index 8 , 4   9 12
//        if() misal posisi index 9 , 5 8 10 13
//        if() misal posisi index 10 , 6 9 11 14
//        if() misal posisi index 11 , 7 10   15
//        if() misal posisi index 12 , 8   13
//        if() misal posisi index 13 , 9 12 14
//        if() misal posisi index 14 , 10 13 15
//        if() misal posisi index 15 , 11 14
//        if() misal posisi 6 , 2 5 7 10
        // ATAS , KIRI , KANAN , BAWAH
        Log.d("CEKPESAN",fromPosition+" "+toPosition+"|"+(fromPosition-4)+"|"+(fromPosition-1)+"|"+(fromPosition+1)+"|"+(fromPosition+4)+"|");
        //if(data.get(fromPosition).equals("") && (fromPosition-4>=0) || fromPosition-1>=0 || fromPosition+1<=total || fromPosition+4<=total ) {
            if (fromPosition < toPosition) {

                if( (data.get(fromPosition).equals("") || data.get(toPosition).equals("") ) && (toPosition==kanan || toPosition==bawah )) {
                    Collections.swap(data, fromPosition, toPosition);
                    notifyItemMoved(fromPosition, toPosition);
                    notifyItemMoved(toPosition - 1, fromPosition);
                }
            } else {

                if( (data.get(fromPosition).equals("") || data.get(toPosition).equals("") ) && (toPosition==atas || toPosition==kiri ) ) {
                    Collections.swap(data, fromPosition, toPosition);
                    notifyItemMoved(fromPosition, toPosition);
                    notifyItemMoved(toPosition + 1, fromPosition);
                }
            }
        //}
        //log cek index puzzle kosong
        //Log.d("PESAN", String.valueOf(data.indexOf("")));
//        notifyItemMoved(fromPosition, toPosition);

//        Item temp = mItems.get(oldPos);
//        mItems.set(oldPos, mItems.get(newPos));
//        mItems.set(newPos, temp);
//        mAdapter.notifyItemChanged(oldPos);
//        mAdapter.notifyItemChanged(newPos);
//        oldPos[0]=fromPosition;
//        newPos[0]=toPosition;

//        String temp = data.get(fromPosition);
//        data.set(fromPosition, data.get(toPosition));
//        data.set(toPosition, temp);
//        notifyItemChanged(fromPosition);
//        notifyItemChanged(toPosition);

    }

    @Override
    public void onRowSelected(MyViewHolder myViewHolder) {
        //myViewHolder.rowView.setBackgroundColor(Color.BLUE);

    }

    @Override
    public void onRowClear(MyViewHolder myViewHolder) {
        //myViewHolder.rowView.setBackgroundColor(Color.BLACK);
//        String temp = data.get(oldPos[0]);
//        data.set(oldPos[0], data.get(newPos[0]));
//        data.set(newPos[0], temp);
//        Collections.swap(data, oldPos[0] , newPos[0]);
//        notifyItemMoved(oldPos[0],newPos[0]);
//        notifyItemMoved(newPos[0]+1,oldPos[0]);
//        notifyItemChanged(newPos[0]);
        if(data.equals(dataAsli)) {
            Toast.makeText(mContexl, "KAMU MENANG", Toast.LENGTH_SHORT).show();
        }
        Log.d("PesanPuzzle", String.valueOf(data));
        Log.d("PesanPuzzleAsli", String.valueOf(dataAsli));

    }
}
