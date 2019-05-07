package com.pycat.schedule.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.pycat.schedule.R;
import com.python.cat.commonlib.net.domain.ScheduleListBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @see com.python.cat.commonlib.net.domain.ScheduleListBean
 * @see ScheduleSmallAdapter
 */
public class ScheduleBigAdapter extends RecyclerView.Adapter<ScheduleBigAdapter.BigVH> {

    private Context mContext;
    private List<ScheduleListBean> mScheduleList;


    public void setScheduleList(List<ScheduleListBean> beanList) {
        this.mScheduleList = beanList;
    }

    public ScheduleBigAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public BigVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_big_schedule, parent, false);
        return new BigVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BigVH holder, int position) {
        if (mScheduleList == null || mScheduleList.size() <= position) {
            return;
        }
        ScheduleListBean bean = mScheduleList.get(position);
        holder.tvDate.setText(date2FormatString(bean.date));
        holder.rvList.setLayoutManager(new LinearLayoutManager(mContext));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        holder.rvList.addItemDecoration(itemDecoration);
        ScheduleSmallAdapter smallAdapter = new ScheduleSmallAdapter(mContext);
        smallAdapter.setSchedules(bean.todoList);
        holder.rvList.setAdapter(smallAdapter);
        holder.tvDate.setOnClickListener(v -> {
            if (View.VISIBLE == holder.rvList.getVisibility()) {
                holder.rvList.setVisibility(View.GONE);
            } else {
                holder.rvList.setVisibility(View.VISIBLE);
                holder.rvList.smoothScrollToPosition(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mScheduleList == null ? 0 : mScheduleList.size();
    }

    static class BigVH extends RecyclerView.ViewHolder {

        private TextView tvDate;
        private RecyclerView rvList;

        public BigVH(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.item_big_date);
            rvList = itemView.findViewById(R.id.item_big_info);
        }
    }

    private String date2FormatString(long date) {
        Date dt = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formatStr = dateFormat.format(dt);
        return formatStr;
    }
}
