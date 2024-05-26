package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seizuremonitoring.R;

import java.util.ArrayList;

import Model.Caregiver;

public class RecyclerViewAdapterCaregiverList extends RecyclerView.Adapter<RecyclerViewAdapterCaregiverList.ViewHolder> {

    private Context context;
    private ArrayList<Caregiver> caregiverList;

    public RecyclerViewAdapterCaregiverList(Context context, ArrayList<Caregiver> caregiverList) {
        this.context = context;
        this.caregiverList = caregiverList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater1 = LayoutInflater.from(parent.getContext());
        View view1 = layoutInflater1.inflate(R.layout.custom_caregiver_card,parent,false);
       ViewHolder viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cUsername.setText(caregiverList.get(position).getUsername());
        holder.cFullname.setText(caregiverList.get(position).getFullname());
        holder.cphone.setText(caregiverList.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return caregiverList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cUsername,cFullname,cphone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cUsername =itemView.findViewById(R.id.cUsername);
            cFullname=itemView.findViewById(R.id.cFullname);
            cphone=itemView.findViewById(R.id.cphone);
        }
    }
}
