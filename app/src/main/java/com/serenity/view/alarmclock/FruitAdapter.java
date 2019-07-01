package com.serenity.view.alarmclock;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serenityapp.R;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> implements WiperSwitch.OnChangedListener{
    private List<Fruit> mFruitList;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        //ImageView fruitImage;
        TextView fruitName;
        TextView little_sun;
        TextView little_mon;
        TextView little_tue;
        TextView little_wed;
        TextView little_thur;
        TextView little_fri;
        TextView little_sat;
        WiperSwitch wiperSwitch1;
        Button button;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            //fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
            little_sun= (TextView) view.findViewById(R.id.little_sun);
            little_mon= (TextView) view.findViewById(R.id.little_mon);
            little_tue= (TextView) view.findViewById(R.id.little_tue);
            little_wed= (TextView) view.findViewById(R.id.little_wed);
            little_thur= (TextView) view.findViewById(R.id.little_thur);
            little_fri= (TextView) view.findViewById(R.id.little_fri);
            little_sat= (TextView) view.findViewById(R.id.little_sat);
            wiperSwitch1=(WiperSwitch)view.findViewById(R.id.wiperSwitch1);
            wiperSwitch1.setChecked(true);				//设置监听
            //wiperSwitch1.setOnChangedListener(this);
            button=(Button) view.findViewById(R.id.button);
        }
    }
    public FruitAdapter(List<Fruit> fruitList,Context context) {
        mFruitList = fruitList;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                fruit_item, parent, false);
        final FruitAdapter adapter;
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = holder.getAdapterPosition();
                final Fruit fruit = mFruitList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("want to delete the alarm?");//设置标题
                builder .setCancelable(false);//设置选项
                builder    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                 Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                            }
                        });
                builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    Log.d("asdasd",fruit.getName());
                    mFruitList.remove(fruit.getName());
                    //remove from the database
                    mFruitList.remove(position);//集合移除该条
                    notifyItemRemoved(position);//通知移除该条
                        notifyItemRangeChanged(position,mFruitList.size()-position);

                    }
                });
                builder.show();

            }

        });

        /*
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked image " + fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
*/
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.wiperSwitch1.setOnChangedListener(this);
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());

        if(fruit.getFlag()==true) {
            ((ViewHolder) holder).fruitName.setTextColor(Color.parseColor("#fffafa"));
            if (fruit.getState1() == false) {
                ((ViewHolder) holder).little_sun.setTextColor(Color.parseColor("#BFD6D7D7"));
            } else {
                ((ViewHolder) holder).little_sun.setTextColor(Color.parseColor("#fffafa"));
            }
            if (fruit.getState2() == false) {
                ((ViewHolder) holder).little_mon.setTextColor(Color.parseColor("#BFD6D7D7"));
            } else {
                ((ViewHolder) holder).little_mon.setTextColor(Color.parseColor("#fffafa"));
            }
            if (fruit.getState3() == false) {
                ((ViewHolder) holder).little_tue.setTextColor(Color.parseColor("#BFD6D7D7"));
            } else {
                ((ViewHolder) holder).little_tue.setTextColor(Color.parseColor("#fffafa"));
            }
            if (fruit.getState4() == false) {
                ((ViewHolder) holder).little_wed.setTextColor(Color.parseColor("#BFD6D7D7"));
            } else {
                ((ViewHolder) holder).little_wed.setTextColor(Color.parseColor("#fffafa"));
            }
            if (fruit.getState5() == false) {
                ((ViewHolder) holder).little_thur.setTextColor(Color.parseColor("#BFD6D7D7"));
            } else {
                ((ViewHolder) holder).little_thur.setTextColor(Color.parseColor("#fffafa"));
            }
            if (fruit.getState6() == false) {
                ((ViewHolder) holder).little_fri.setTextColor(Color.parseColor("#BFD6D7D7"));
            } else {
                ((ViewHolder) holder).little_fri.setTextColor(Color.parseColor("#fffafa"));
            }
            if (fruit.getState7() == false) {
                ((ViewHolder) holder).little_sat.setTextColor(Color.parseColor("#BFD6D7D7"));
            } else {
                ((ViewHolder) holder).little_sat.setTextColor(Color.parseColor("#fffafa"));
            }
        }
        else{
            ((ViewHolder) holder).fruitName.setTextColor(Color.parseColor("#BFD6D7D7"));
            ((ViewHolder) holder).little_sun.setTextColor(Color.parseColor("#BFD6D7D7"));
            ((ViewHolder) holder).little_mon.setTextColor(Color.parseColor("#BFD6D7D7"));
            ((ViewHolder) holder).little_tue.setTextColor(Color.parseColor("#BFD6D7D7"));
            ((ViewHolder) holder).little_wed.setTextColor(Color.parseColor("#BFD6D7D7"));
            ((ViewHolder) holder).little_thur.setTextColor(Color.parseColor("#BFD6D7D7"));
            ((ViewHolder) holder).little_fri.setTextColor(Color.parseColor("#BFD6D7D7"));
            ((ViewHolder) holder).little_sat.setTextColor(Color.parseColor("#BFD6D7D7"));
        }
    }
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
    public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {

        Log.e("log", "" + checkState);
    }


}
