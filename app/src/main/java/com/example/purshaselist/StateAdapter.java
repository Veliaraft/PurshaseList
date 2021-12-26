package com.example.purshaselist;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final ArrayList<State> states;

    public StateAdapter(Context context, ArrayList<State> listShape) {
        this.states = listShape;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.ViewHolder holder, int position) {
        State state = states.get(position);
        holder.PRODUCTView.setText(state.getPRODUCT());
        holder.DATEView.setText(state.getDATE());
        holder.AMOUNTView.setText(state.getAMOUNT());
        holder.COSTView.setText(state.getCOST());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView PRODUCTView;
        final TextView DATEView;
        final TextView AMOUNTView;
        final TextView COSTView;

        ViewHolder(View view){
            super(view);
            PRODUCTView = view.findViewById(R.id.PRODUCT);
            DATEView = view.findViewById(R.id.DATE);
            AMOUNTView = view.findViewById(R.id.AMOUNT);
            COSTView = view.findViewById(R.id.COST);
        }
    }
}
