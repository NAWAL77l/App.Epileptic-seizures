package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seizuremonitoring.R;

public class RecyclerViewAdapterNotification extends RecyclerView.Adapter<RecyclerViewAdapterNotification.ViewHolder> {

    private Context context;
    private String  notificationList [];

    public RecyclerViewAdapterNotification(Context context, String[] notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterNotification.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater1 = LayoutInflater.from(parent.getContext());
        View view1 = layoutInflater1.inflate(R.layout.custom_noti_card,parent,false);
        ViewHolder viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterNotification.ViewHolder holder, int position) {
        holder.notiText.setText(notificationList[position]);

    }

    @Override
    public int getItemCount() {
        return notificationList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notiText;
        CardView notificationCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notiText =itemView.findViewById(R.id.notiText);
            notificationCard =itemView.findViewById(R.id.notificationCard);
        }
    }
}
