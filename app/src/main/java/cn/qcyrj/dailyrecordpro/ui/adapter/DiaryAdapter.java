package cn.qcyrj.dailyrecordpro.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.qcyrj.dailyrecordpro.R;
import cn.qcyrj.dailyrecordpro.data.Diary;
import lombok.Setter;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryViewHolder> {

    private List<Diary> diaryList;
    private int displayStyle; // 0: 列表样式, 1: 块状样式
    @Setter
    private OnItemClickListener onItemClickListener;

    public DiaryAdapter(int displayStyle) {
        this.diaryList = new ArrayList<>();
        this.displayStyle = displayStyle;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDisplayStyle(int displayStyle) {
        this.displayStyle = displayStyle;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDiaryList(List<Diary> diaryList) {
        this.diaryList = diaryList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return displayStyle;
    }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (viewType == 0) {
            // 列表
            itemView = inflater.inflate(R.layout.item_diary_list, parent, false);
        } else {
            // 块状样式
            itemView = inflater.inflate(R.layout.item_diary_grid, parent, false);
        }
        return new DiaryViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {
        Diary diary = diaryList.get(position);
        holder.bind(diary);
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(diary, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return diaryList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Diary diary, int position);
    }
}
