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
import com.example.seizuremonitoring.ReportDetailsActivity;

public class RecyclerViewAdapterReportList extends RecyclerView.Adapter<RecyclerViewAdapterReportList.ViewHolder> {

    private Context context;
    private String  reportList [];

    public RecyclerViewAdapterReportList(Context context, String[] reportList) {
        this.context = context;
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater1 = LayoutInflater.from(parent.getContext());
        View view1 = layoutInflater1.inflate(R.layout.custom_report_card,parent,false);
      ViewHolder viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pName.setText(reportList[position]);
        holder.reportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReportDetailsActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reportList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pName;
        CardView reportCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pName =itemView.findViewById(R.id.pName);
            reportCard=itemView.findViewById(R.id.reportCard);

        }
    }
}
