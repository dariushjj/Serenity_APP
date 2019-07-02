package com.serenity.view.alarmclock;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serenityapp.R;


import java.util.ArrayList;
import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder>{
    private List<Time> mTimeList;
    private Context context;
    private int position;
    private List<Boolean> isClicks;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onClick(View view, int position);
    }
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
        Button button;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
            little_sun= (TextView) view.findViewById(R.id.little_sun);
            little_mon= (TextView) view.findViewById(R.id.little_mon);
            little_tue= (TextView) view.findViewById(R.id.little_tue);
            little_wed= (TextView) view.findViewById(R.id.little_wed);
            little_thur= (TextView) view.findViewById(R.id.little_thur);
            little_fri= (TextView) view.findViewById(R.id.little_fri);
            little_sat= (TextView) view.findViewById(R.id.little_sat);

            button=(Button) view.findViewById(R.id.button);
        }
    }
    public TimeAdapter(List<Time> timeList, Context context) {
        mTimeList = timeList;
        this.context=context;
        this.position = -1;
        isClicks = new ArrayList<>();
        for(int i = 0; i< mTimeList.size(); i++){
            isClicks.add(true);
        }

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                time_item, parent, false);
        final TimeAdapter adapter;
        final ViewHolder holder = new ViewHolder(view);


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = holder.getAdapterPosition();
                final Time time = mTimeList.get(position);
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

                    Log.d("asdasd", time.getName());
                    mTimeList.remove(time.getName());
                    //remove from the database
                    mTimeList.remove(position);//集合移除该条
                    notifyItemRemoved(position);//通知移除该条
                        notifyItemRangeChanged(position, mTimeList.size()-position);

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
                Time fruit = mTimeList.get(position);
                Toast.makeText(v.getContext(), "you clicked image " + fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
*/
        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Time time = mTimeList.get(position);
        holder.fruitName.setText(time.getName());

        if(onItemClickListener != null) {
            if (isClicks.get(position)) {
                ((ViewHolder) holder).fruitName.setTextColor(Color.parseColor("#fffafa"));
                if (time.getState1() == false) {
                    ((ViewHolder) holder).little_sun.setTextColor(Color.parseColor("#BFD6D7D7"));
                } else {
                    ((ViewHolder) holder).little_sun.setTextColor(Color.parseColor("#fffafa"));
                }
                if (time.getState2() == false) {
                    ((ViewHolder) holder).little_mon.setTextColor(Color.parseColor("#BFD6D7D7"));
                } else {
                    ((ViewHolder) holder).little_mon.setTextColor(Color.parseColor("#fffafa"));
                }
                if (time.getState3() == false) {
                    ((ViewHolder) holder).little_tue.setTextColor(Color.parseColor("#BFD6D7D7"));
                } else {
                    ((ViewHolder) holder).little_tue.setTextColor(Color.parseColor("#fffafa"));
                }
                if (time.getState4() == false) {
                    ((ViewHolder) holder).little_wed.setTextColor(Color.parseColor("#BFD6D7D7"));
                } else {
                    ((ViewHolder) holder).little_wed.setTextColor(Color.parseColor("#fffafa"));
                }
                if (time.getState5() == false) {
                    ((ViewHolder) holder).little_thur.setTextColor(Color.parseColor("#BFD6D7D7"));
                } else {
                    ((ViewHolder) holder).little_thur.setTextColor(Color.parseColor("#fffafa"));
                }
                if (time.getState6() == false) {
                    ((ViewHolder) holder).little_fri.setTextColor(Color.parseColor("#BFD6D7D7"));
                } else {
                    ((ViewHolder) holder).little_fri.setTextColor(Color.parseColor("#fffafa"));
                }
                if (time.getState7() == false) {
                    ((ViewHolder) holder).little_sat.setTextColor(Color.parseColor("#BFD6D7D7"));
                } else {
                    ((ViewHolder) holder).little_sat.setTextColor(Color.parseColor("#fffafa"));
                }

            } else {
                ((ViewHolder) holder).fruitName.setTextColor(Color.parseColor("#BFD6D7D7"));
                ((ViewHolder) holder).little_sun.setTextColor(Color.parseColor("#BFD6D7D7"));
                ((ViewHolder) holder).little_mon.setTextColor(Color.parseColor("#BFD6D7D7"));
                ((ViewHolder) holder).little_tue.setTextColor(Color.parseColor("#BFD6D7D7"));
                ((ViewHolder) holder).little_wed.setTextColor(Color.parseColor("#BFD6D7D7"));
                ((ViewHolder) holder).little_thur.setTextColor(Color.parseColor("#BFD6D7D7"));
                ((ViewHolder) holder).little_fri.setTextColor(Color.parseColor("#BFD6D7D7"));
                ((ViewHolder) holder).little_sat.setTextColor(Color.parseColor("#BFD6D7D7"));
            }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition(); // 1
                        boolean flag = isClicks.get(position);
                        isClicks.set(position,!flag);
                        notifyDataSetChanged();
                        onItemClickListener.onClick(view, holder.getLayoutPosition());
                    }
                });

        }
    }
    @Override
    public int getItemCount() {
        return mTimeList.size();
    }
    public int getPosition(){
        return this.position;
    }
    public void setPosition(int position){
        this.position = position;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
// TODO: 19-7-2 here needs some databases operation : write setting to databases 

}
