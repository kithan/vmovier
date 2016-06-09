package com.example.hpb.kunlun.home.latest.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.data.RxBus;
import com.example.hpb.kunlun.home.latest.adapter.LatestAdapter;
import com.example.hpb.kunlun.home.latest.model.Banner;
import com.example.hpb.kunlun.home.latest.model.PostSection;
import com.example.hpb.kunlun.home.latest.model.PostTab;
import com.example.hpb.kunlun.home.latest.presenter.ILatestPresenter;
import com.example.hpb.kunlun.home.latest.presenter.LatestPresenterImpl;
import com.example.hpb.kunlun.home.model.Cate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hpb on 16/6/8.
 */
public class LatestFragment extends Fragment
    implements ILatestView, BaseQuickAdapter.RequestLoadMoreListener {

  ILatestPresenter homePresenter;
  @BindView(R.id.list_post) RecyclerView listPost;
  LatestAdapter latestAdapter;
  List<PostSection> sections = new ArrayList<>();

  public static LatestFragment newInstance() {
    LatestFragment fragment = new LatestFragment();
    return fragment;
  }

  ILatestPresenter latestPresenter;

  String today;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_latest, null);
    ButterKnife.bind(this, view);
    today = dateFormat.format(new Date());
    latestAdapter = new LatestAdapter(getContext(), sections);
    latestAdapter.setOnLoadMoreListener(this);
    latestAdapter.openLoadMore(true);
    latestAdapter.openLoadAnimation();
    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    listPost.setLayoutManager(linearLayoutManager);
    listPost.setAdapter(latestAdapter);
    listPost.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
          int pos = linearLayoutManager.findFirstVisibleItemPosition();
          PostSection section = (PostSection) latestAdapter.getItem(pos);
          if (!section.isHeader) {
            String curr = dateFormat.format(new Date(section.t.getPublish_time() * 1000));
            if (curr.equals(today)) {
              if (!tabTitle.equals("最新")) {
                tabTitle = "最新";
                if (RxBus.getInstance().hasObservers()) {
                  RxBus.getInstance().send(new UpdateTabTitleEvent(tabTitle));
                }
              }
            } else if (!tabTitle.equals(curr)) {
              tabTitle = curr;
              if (RxBus.getInstance().hasObservers()) {
                RxBus.getInstance().send(new UpdateTabTitleEvent(curr));
              }
            }
          }
        }
      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      }
    });
    latestPresenter = new LatestPresenterImpl(this);
    latestPresenter.loadTabPost(page);
    return view;
  }

  String tabTitle = "最新";
  int page = 1;
  SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd");
  String day = "";

  @Override public void onGetTabPost(List<PostTab> tabs) {
    for (PostTab tab : tabs) {
      String curr = dateFormat.format(new Date(tab.getPublish_time() * 1000));
      if (!TextUtils.isEmpty(day) && !day.equals(curr)) {
        sections.add(new PostSection(true, "- " + curr + " -"));
      }
      sections.add(new PostSection(tab));
      day = curr;
    }
    latestAdapter.notifyDataChangedAfterLoadMore(sections, true);
  }

  @Override public void onGetBanner(List<Banner> banners) {

  }

  @Override public void onLoadMoreRequested() {
    page++;
    latestPresenter.loadTabPost(page);
  }

  public class UpdateTabTitleEvent {
    private String title;

    public UpdateTabTitleEvent(String title) {
      this.title = title;
    }

    public String getTitle() {
      return title;
    }
  }
}
