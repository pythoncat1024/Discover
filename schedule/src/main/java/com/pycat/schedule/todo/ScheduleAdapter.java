package com.pycat.schedule.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.pycat.schedule.R;
import com.python.cat.commonlib.net.domain.ScheduleBean;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.VH> {

    private List<ScheduleBean> mScheduleList;

    public void setScheduleList(List<ScheduleBean> data) {
        this.mScheduleList = data;
    }

    @NonNull
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_schedule_layout, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (mScheduleList == null || mScheduleList.size() <= position) {
            return;
        }
        ScheduleBean bean = mScheduleList.get(position);

        holder.itemDate.setText(bean.dateStr);
        holder.itemTitle.setText(bean.title);
        holder.itemContent.setText(bean.content);
        holder.itemDelete.setOnClickListener(v -> {
            LogUtils.e("delete...");
            LogUtils.v("delete...");
        });

        holder.itemTitle.setOnClickListener(v -> {
            LogUtils.e("title - show detail...");
            LogUtils.v("title - show detail...");
        });
    }

    @Override
    public int getItemCount() {
        return mScheduleList == null ? 0 : mScheduleList.size();
    }

    static class VH extends RecyclerView.ViewHolder {

        private CheckBox itemCheck;
        private TextView itemDate;
        private TextView itemTitle;
        private TextView itemContent;
        private ImageView itemDelete;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.itemCheck = itemView.findViewById(R.id.item_schedule_check);
            this.itemDate = itemView.findViewById(R.id.item_schedule_date);
            this.itemTitle = itemView.findViewById(R.id.item_schedule_title);
            this.itemContent = itemView.findViewById(R.id.item_schedule_content);
            this.itemDelete = itemView.findViewById(R.id.item_schedule_delete);
        }
    }
}
