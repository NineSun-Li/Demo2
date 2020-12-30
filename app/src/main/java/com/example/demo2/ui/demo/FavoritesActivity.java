package com.example.demo2.ui.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demo2.R;
import com.example.demo2.adapter.FavoritesAdapter;
import com.example.demo2.base.BaseActivity;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.sql.Favorites;
import com.example.demo2.sql.Realms;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class FavoritesActivity extends BaseActivity {


    @BindView(R.id.recycler_favorites)
    SwipeMenuRecyclerView recyclerFavorites;

    private List<Favorites> list;
    private FavoritesAdapter favoritesAdapter;
    private RealmResults<Favorites> all;


    @Override
    protected int getLayout() {
        return R.layout.activity_favorites;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        recyclerFavorites.setLayoutManager(new LinearLayoutManager(this));
        // 设置菜单创建器
        recyclerFavorites.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听
        recyclerFavorites.setSwipeMenuItemClickListener(menuItemClickListener);


        favoritesAdapter = new FavoritesAdapter(this, list);
        recyclerFavorites.setAdapter(favoritesAdapter);
        recyclerFavorites.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        all = Realms.getRealm(FavoritesActivity.this).where(Favorites.class).findAll();
        list.clear();
        list.addAll(all);
        favoritesAdapter.notifyDataSetChanged();



    }

    //创建侧滑菜单
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            SwipeMenuItem swipeMenuItem = new SwipeMenuItem(FavoritesActivity.this)
                    .setImage(R.mipmap.b8)
                    .setWidth(144)//设置
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);//高（MATCH_PARENT意为Item多高侧滑菜单多高 （推荐使用）;
            swipeRightMenu.addMenuItem(swipeMenuItem);
        }
    };

    //创建侧滑菜单的点击事件
    private SwipeMenuItemClickListener menuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            //在menuBridge中我们可以得到侧滑的这一项item的position (menuBridge.getAdapterPosition())
            int adapterPosition = menuBridge.getAdapterPosition();
            list.remove(adapterPosition);
            //删除数据库
            Realms.getRealm(FavoritesActivity.this).executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    all.get(adapterPosition).deleteFromRealm();
                }
            });
            favoritesAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
