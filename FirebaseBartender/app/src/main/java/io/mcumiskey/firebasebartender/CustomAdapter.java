package io.mcumiskey.firebasebartender;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    Context context;
    ArrayList<Cocktail> list;

    public CustomAdapter(Context ct, ArrayList<Cocktail> list){
        this.context = ct;
        this.list = list;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_item, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Cocktail cocktail = list.get(position);
        holder.name.setText(cocktail.getCocktailName());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send to the cocktail detail page!
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("name", cocktail.getCocktailName());
                intent.putExtra("ingredients", cocktail.getCocktailIngredients());
                intent.putExtra("steps", cocktail.getCocktailInstructions());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void searchCocktailList(ArrayList<Cocktail> searchList){
        //only show the searchlist
        list = searchList;
        notifyDataSetChanged();
    }
    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView name;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.recCard);
            name = itemView.findViewById(R.id.recName);

        }
    }
}
