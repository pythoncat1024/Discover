package com.pycat.schedule.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pycat.schedule.R;
import com.python.cat.commonlib.net.domain.ScheduleBean;

import java.util.List;

/**
 * @see com.python.cat.commonlib.net.domain.ScheduleBean
 * @see ScheduleBigAdapter
 */
public class ScheduleSmallAdapter extends RecyclerView.Adapter<ScheduleSmallAdapter.SmallVH> {

    private Context mContext;
    private List<ScheduleBean> mSchedules;

    public void setSchedules(List<ScheduleBean> list) {
        this.mSchedules = list;
    }

    public ScheduleSmallAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public SmallVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_small_schedule, parent, false);
        return new SmallVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmallVH holder, int position) {
        if (mSchedules == null || mSchedules.size() <= position) {
            return;
        }
        ScheduleBean bean = mSchedules.get(position);
        holder.tvTitle.setText(bean.title);
        holder.tvContent.setText(bean.content);
        holder.tvType.setText(type2formatStr(bean.type));
    }

    @Override
    public int getItemCount() {
        return mSchedules == null ? 0 : mSchedules.size();
    }

    static class SmallVH extends RecyclerView.ViewHolder {

        private TextView tvType;
        private TextView tvTitle;
        private TextView tvContent;

        public SmallVH(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.item_small_schedule_type);
            tvTitle = itemView.findViewById(R.id.item_small_schedule_title);
            tvContent = itemView.findViewById(R.id.item_small_schedule_content);
        }
    }

    private String type2formatStr(int type) {
        String cc;
        switch (type) {
            case 0:
                cc = "T0 ZZ";
                break;
            case 1:
                cc = "T1 ZZ";
                break;
            case 2:
                cc = "T2 ZZ";
                break;
            case 3:
                cc = "T3 ZZ";
                break;
            default:
                cc = "TT ZZ";
        }
        return cc;
    }
}
