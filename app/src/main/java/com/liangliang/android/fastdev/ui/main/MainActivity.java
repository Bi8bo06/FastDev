package com.liangliang.android.fastdev.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.liangliang.android.component.base.activity.BaseActivity;
import com.liangliang.android.component.widget.adapter.decoration.SectionItemDecoration;
import com.liangliang.android.component.widget.adapter.decoration.StickyItemDecoration;
import com.liangliang.android.component.widget.base.TitleBarLayout;
import com.liangliang.android.component.widget.index.IndexBar;
import com.liangliang.android.component.widget.recycler.BaseRecyclerViewAdapter;
import com.liangliang.android.core.utils.ArrayUtils;
import com.liangliang.android.core.utils.DensityUtils;
import com.liangliang.android.fastdev.App;
import com.liangliang.android.fastdev.R;
import com.liangliang.android.fastdev.bean.MainBean;
import com.liangliang.android.fastdev.ui.dialog.DialogTestActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {
    /**
     * 启动
     *
     * @param context 上下文
     */
    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    /**
     * 索引标题
     */
    private static final String[] INDEX_TITLE = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    private static final List<MainBean> MAIN_DATA_LIST = Arrays.asList(
            new MainBean("弹框测试", "T", DialogTestActivity.class)
    );

    /**
     * 标题名称
     */
    public static final String EXTRA_TITLE_NAME = "extra_title_name";

    /**
     * 列表
     */
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    /**
     * 索引栏
     */
    @BindView(R.id.index_bar)
    IndexBar mIndexBar;
    /**
     * 提示控件
     */
    @BindView(R.id.hint)
    TextView mHintTv;

    private MainAdapter mAdapter;
    private List<MainBean> mList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_layout;
    }

    @Override
    protected void findViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initTitleBar(getTitleBarLayout());
        initRecyclerView();
        mIndexBar.setHintTextView(mHintTv);
    }

    private void initTitleBar(TitleBarLayout titleBarLayout) {
        titleBarLayout.setTitleName(R.string.main_title);
        TextView refreshBtn = new TextView(getContext());
        refreshBtn.setText(R.string.main_change_mood);
        refreshBtn.setPadding(DensityUtils.dp2px(getContext(), 15), 0, DensityUtils.dp2px(getContext(), 15), 0);
        refreshBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.notifyDataSetChanged();
            }
        });
        titleBarLayout.addExpandView(refreshBtn);
        titleBarLayout.needBackButton(false);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mAdapter = new MainAdapter(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(getItemDecoration());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    private RecyclerView.ItemDecoration getItemDecoration() {
        return StickyItemDecoration.<String>create(getContext())
                .setOnSectionCallback(new SectionItemDecoration.OnSectionCallback<String>() {
                    @Override
                    public String getSourceItem(int position) {
                        return mList.get(position).getSortStr();
                    }
                })
                .setSectionTextSize(16)
                .setSectionHeight(30)
                .setSectionTextTypeface(Typeface.DEFAULT_BOLD)
                .setSectionTextColorRes(R.color.color_00a0e9)
                .setSectionTextPaddingLeftDp(8)
                .setSectionBgColorRes(R.color.color_f0f0f0);
    }

    @Override
    protected boolean onPressBack() {
        App.get().exit();
        return true;
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<MainBean>() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, MainBean item, int position) {
                Intent intent = new Intent(getContext(), item.getCls());
                intent.putExtra(EXTRA_TITLE_NAME, item.getTitleName());
                startActivity(intent);
            }
        });

        mIndexBar.setOnIndexListener(new IndexBar.OnIndexListener() {
            @Override
            public void onStart(int position, String indexText) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                layoutManager.scrollToPositionWithOffset(ArrayUtils.getPositionByIndex(mList, ArrayUtils.arrayToList(INDEX_TITLE), indexText), 0);
            }

            @Override
            public void onEnd() {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mList = ArrayUtils.groupList(MAIN_DATA_LIST, ArrayUtils.arrayToList(INDEX_TITLE));
        mIndexBar.setIndexList(ArrayUtils.arrayToList(INDEX_TITLE));
        mAdapter.setData(mList);
        showStatusCompleted();
    }
}