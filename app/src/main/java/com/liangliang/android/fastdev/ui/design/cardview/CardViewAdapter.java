package com.liangliang.android.fastdev.ui.design.cardview;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.liangliang.android.component.widget.adapter.recycler.BaseRecyclerViewAdapter;
import com.liangliang.android.core.utils.DateUtils;
import com.liangliang.android.fastdev.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * CardView列表适配器
 */
public class CardViewAdapter extends BaseRecyclerViewAdapter<String> {
    public CardViewAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataViewHolder(getLayoutView(parent, R.layout.item_card_view_layout));
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, int position) {
        String str = getItem(position);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        showItem((DataViewHolder) holder, str);
    }

    private void showItem(DataViewHolder holder, String str) {
        holder.titleTv.setText(str);

        int length = new Random().nextInt(50);
        String content = "我是内容";
        for (int i = 0; i < length; i++) {
            content += "我是内容";
        }
        holder.contentTv.setText(content);

        holder.dateTv.setText(DateUtils.getCurrentFormatString(DateUtils.TYPE_9));
    }

    class DataViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title)
        TextView titleTv;

        @BindView(R.id.content)
        TextView contentTv;

        @BindView(R.id.date)
        TextView dateTv;

        private DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
