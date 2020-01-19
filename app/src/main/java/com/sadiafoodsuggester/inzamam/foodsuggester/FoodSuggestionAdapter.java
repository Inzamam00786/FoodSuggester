package com.sadiafoodsuggester.inzamam.foodsuggester;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

public class FoodSuggestionAdapter extends RecyclerView.Adapter<FoodSuggestionAdapter.FoodViewAdapter> {
    public Context context;
    public List<ValuesList> valuesListList;

    public FoodSuggestionAdapter(Context context, List<ValuesList> valuesListList) {
        this.context = context;
        this.valuesListList = valuesListList;
    }

    @Override
    public FoodViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.foodsuggestionlist, parent, false);
        FoodViewAdapter foodViewAdapter=new FoodViewAdapter(view);
        return foodViewAdapter;
    }

    @Override
    public void onBindViewHolder(FoodViewAdapter holder, int position) {
        ValuesList valuesList=valuesListList.get(position);

        holder.tvfoodtitle.setText(valuesList.getFoodtitle());
        holder.tvcarbohydrates.setText(String.valueOf(valuesList.getCarbohydrates()));
        holder.tvproteins.setText(String.valueOf(valuesList.getProteins()));
        holder.tvfats.setText(String.valueOf(valuesList.getFats()));

        Picasso.with(context).load(valuesList.getImage()).into(holder.imageView);

        holder.labelcarbo.setText(valuesList.getLabelcarbohydrates());
        holder.labelpro.setText(valuesList.getLabelprotiens());
        holder.labelfat.setText(valuesList.getLabelfats());
    }

    @Override
    public int getItemCount() {
        return valuesListList.size();
    }

    class FoodViewAdapter extends ViewHolder{
        TextView tvfoodtitle, tvcarbohydrates, tvproteins, tvfats, labelcarbo, labelpro, labelfat;
        ImageView imageView;
         FoodViewAdapter(View itemView) {
            super(itemView);
             imageView=(ImageView) itemView.findViewById(R.id.imageView);
             tvfoodtitle=(TextView) itemView.findViewById(R.id.textViewTitle);
             tvcarbohydrates=(TextView) itemView.findViewById(R.id.textviewcarbohydrates);
             tvproteins=(TextView) itemView.findViewById(R.id.textviewproteins);
             tvfats=(TextView) itemView.findViewById(R.id.textviewfats);

             labelcarbo=(TextView)itemView.findViewById(R.id.labelcarbohydrates);
             labelpro=(TextView)itemView.findViewById(R.id.labelproteins);
             labelfat=(TextView)itemView.findViewById(R.id.labelfats);
        }
    }
}
