package cn.qcyrj.dailyrecordpro.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import cn.qcyrj.dailyrecordpro.R;
import cn.qcyrj.dailyrecordpro.data.Diary;

public class DiaryViewHolder extends RecyclerView.ViewHolder {


    private ImageView ivDiaryImage;
    private TextView tvDiaryTitle;
    private TextView tvDiaryContent;
    private TextView tvDiaryTime;
    private int viewType;

    public DiaryViewHolder(@NonNull View itemView, int viewType) {
        super(itemView);
        this.viewType = viewType;

        ivDiaryImage = itemView.findViewById(R.id.iv_diary_image);
        tvDiaryContent = itemView.findViewById(R.id.tv_diary_content);
        tvDiaryTime = itemView.findViewById(R.id.tv_diary_time);

        // 只有列表样式有标题
        if (viewType == 0) {
            tvDiaryTitle = itemView.findViewById(R.id.tv_diary_title);
        }
    }

    public void bind(Diary diary) {
        // 处理图片显示
//        if (diary.hasImage()) {
//            ivDiaryImage.setVisibility(View.VISIBLE);
        // 使用Glide加载图片
        // Glide.with(itemView.getContext())
        //        .load(new File(diary.getImagePath()))
        //        .placeholder(R.drawable.placeholder)
        //        .error(R.drawable.error)
        //        .into(ivDiaryImage);
//        } else {
//            ivDiaryImage.setVisibility(View.GONE);
//        }

        // 设置内容
        if (viewType == 0) {
            // 列表样式：显示标题和内容
            tvDiaryTitle.setText(diary.getTitle());
            tvDiaryContent.setText(diary.getContent());
        } else {
            // 块状样式：只显示内容，内容多少显示多少
            String displayText = diary.getTitle() + "\n" + diary.getContent();
            tvDiaryContent.setText(displayText);
        }
        // 如果是网格视图，需要设置防止item变化
        if (viewType == 1) {
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(false);
            }
        }
        // 设置时间
        tvDiaryTime.setText(diary.getCreateTime());
        tvDiaryTime.setVisibility(View.VISIBLE);
    }
}
