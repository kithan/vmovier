package com.example.hpb.kunlun.home.channel.view;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.home.channel.presenter.ChannelPresenterImpl;
import com.example.hpb.kunlun.home.channel.presenter.IchannelPresenter;
import com.example.hpb.kunlun.home.model.Cate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0000- on 2016/6/9.
 */
public class ChannelFragment extends Fragment implements IChannelView {

  private IchannelPresenter channelPresenter;

  @BindView(R.id.rv_channel) RecyclerView recyclerView;

  ChannelAdapter channelAdapter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frament_channel, null);
    ButterKnife.bind(this, view);

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

    channelPresenter = new ChannelPresenterImpl(this);
    channelPresenter.getAllChannel();

    return view;
  }

  @Override public void onLoadChannels(List<Cate> cates) {
    channelAdapter.notifyDataChangedAfterLoadMore(cates, false);
  }
}
