package com.example.hpb.kunlun.home.channel.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hpb.kunlun.BaseFragment;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.home.channel.presenter.ChannelPresenter;
import com.example.hpb.kunlun.home.model.Cate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0000- on 2016/6/9.
 */
public class ChannelFragment extends BaseFragment<IChannelView,ChannelPresenter> implements IChannelView {


  @BindView(R.id.rv_channel) RecyclerView recyclerView;

  ChannelAdapter channelAdapter;

  @Override
  public int getContentView() {
    return R.layout.frament_channel;
  }

  @Override
  public ChannelPresenter initPresenter() {
    return new ChannelPresenter();
  }

  @Override
  public void initViews() {
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    recyclerView.setFadingEdgeLength(0);
    recyclerView.offsetChildrenVertical(0);
    channelAdapter = new ChannelAdapter(getActivity(), new ArrayList<Cate>());
    channelAdapter.setOnRecyclerViewItemClickListener(
            new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
              @Override public void onItemClick(View view, int i) {
                Toast.makeText(getContext(), channelAdapter.getItem(i).getCatename()+" clicked",
                        Toast.LENGTH_SHORT).show();
              }
            });
    recyclerView.setAdapter(channelAdapter);
    presenter.getAllChannel();
  }


  @Override public void onLoadChannels(List<Cate> cates) {
    channelAdapter.notifyDataChangedAfterLoadMore(cates, false);
  }
}
