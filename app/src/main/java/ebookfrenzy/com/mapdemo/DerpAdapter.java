package ebookfrenzy.com.mapdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Mark on 5/4/2017.
 */

public class DerpAdapter extends RecyclerView.Adapter<DerpAdapter.DerpHolder> {
    private List<ebookfrenzy.com.mapdemo.List> listData;
    private LayoutInflater inflater;

    public DerpAdapter(List<ebookfrenzy.com.mapdemo.List> listData, Context c) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(c);
    }
    class DerpHolder extends RecyclerView.ViewHolder  {

        private View container;
        private TextView title;
        private TextView weather;
        private TextView Wind;
        private TextView sad;
        private TextView humad;
        private TextView hd;
        private TextView ws;
        private TextView wd;

        public DerpHolder(View itemView) {
            super(itemView);


            container = itemView.findViewById(R.id.m);
            title = (TextView) itemView.findViewById(R.id.textView1);
            weather = (TextView) itemView.findViewById(R.id.textView2);
            title = (TextView) itemView.findViewById(R.id.textView1);
            weather = (TextView) itemView.findViewById(R.id.textView2);
            Wind = (TextView) itemView.findViewById(R.id.textView7);
            sad = (TextView) itemView.findViewById(R.id.textView4);
            hd = (TextView) itemView.findViewById(R.id.textView5);
            humad = (TextView) itemView.findViewById(R.id.textView);
            humad.setText("Humidity : ");
            ws = (TextView) itemView.findViewById(R.id.textView3);
            wd = (TextView) itemView.findViewById(R.id.textView6);
            ws.setText("Wind Speed : ");
            wd.setText("Wind Degree : ");
        }
 /*           public void bindPhoto(Photo photo) {
                mPhoto = photo;
                Picasso.with(mItemImage.getContext()).load(photo.getUrl()).into(mItemImage);
                mItemDate.setText(photo.getHumanDate());
                mItemDescription.setText(photo.getExplanation());
            }*/


    }
        @Override
        public DerpAdapter.DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.items, parent, false);


            return new DerpHolder(view);
        }

        @Override
        public void onBindViewHolder(final DerpAdapter.DerpHolder holder, int position) {
            final ebookfrenzy.com.mapdemo.List item = listData.get(position);
            double d = item.getMain().getTemp();
            if (d >= 40) {
                holder.container.setBackgroundColor(Color.parseColor("#ff8000"));
            } else if (d >= 38 && d < 40) {
                holder.container.setBackgroundColor(Color.parseColor("#ffcc00"));
            } else if (d >= 35 && d < 38) {
                holder.container.setBackgroundColor(Color.parseColor("#ffad33"));
            } else if (d >= 30 && d < 35) {
                holder.container.setBackgroundColor(Color.parseColor("#ffdb4d"));
            } else if (d >= 25 && d < 30) {
                holder.container.setBackgroundColor(Color.parseColor("#ffe066"));
            } else if (d >= 20 && d < 25) {
                holder.container.setBackgroundColor(Color.parseColor("#ffeb99"));
            }
            holder.title.setText(item.getName() + " ");
            holder.title.setTextSize(20);
            holder.weather.setTextSize(18);
            holder.weather.setText(String.valueOf(item.getMain().getTemp() + " °C"));
            holder.hd.setText(String.valueOf(item.getMain().getHumidity()));
            holder.sad.setText(String.valueOf(item.getWind().getSpeed()));
            holder.Wind.setText(String.valueOf(item.getWind().getDeg()));

        holder.container.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Bundle bundle = new Bundle();
                String tit = item.getName();
               // Log.e(TAG, (String) holder.title.getText() );
                bundle.putString("key",tit);
                Intent in=new Intent(holder.container.getContext(),MapDemoActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtras(bundle);
                holder.container.getContext().startActivity(in);
            }
        });

    }
            /*public void onClick(View view) {

                MainActivity m = new MainActivity();
                m.clicked();
            }
        });
    }*/



    @Override
    public int getItemCount() {
        return listData.size();
    }
}
