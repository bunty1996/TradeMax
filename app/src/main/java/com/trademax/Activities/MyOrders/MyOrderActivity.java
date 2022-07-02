package com.trademax.Activities.MyOrders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayoutMediator;
import com.trademax.Activities.MyOrders.Presenter.Orderpresenter;
import com.trademax.Activities.MyOrders.view.Orderview;
import com.trademax.Adapters.MyOrderTabAdapter;
import com.trademax.Fragments.AllOrders.AllOrderFragment;
import com.trademax.Fragments.DeliveredOrders.DeliveredOrderFragment;
import com.trademax.Fragments.OrderedOrders.OrderedOrderFragment;
import com.trademax.Fragments.ShippingOrders.ShippingOrderFragment;
import com.trademax.R;
import com.trademax.Utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity implements View.OnClickListener, Orderview {

    private Activity activity;
    //    private RecyclerView recycler_orderStatus;
//    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView img_back;
    private MyOrderTabAdapter myOrderTabAdapter;
    private Orderpresenter orderpresenter;


    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        activity = this;
        init();
        listiners();

//        addTabs(viewPager);
//        tabLayout.setupWithViewPager(viewPager);
//        setupTabIcons();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        Utils.changeTabFragment(activity, new AllOrderFragment());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new AllOrderFragment();
                        break;
                    case 1:
                        fragment = new OrderedOrderFragment();
                        break;
                    case 2:
                        fragment = new ShippingOrderFragment();
                        break;
                    case 3:
                        fragment = new DeliveredOrderFragment();
                        break;
                }

                Utils.changeTabFragment(activity, fragment);
//                FragmentManager fm = activity.getFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.frameLayout, fragment);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                ft.commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }

    private void init() {
        img_back = (ImageView) findViewById(R.id.img_back);
    }

    private void listiners() {
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == img_back) {
            finish();
        }
    }

//    private void setupTabIcons() {
//        tabLayout.getTabAt(0);
//        tabLayout.getTabAt(1);
//        tabLayout.getTabAt(2);
//        tabLayout.getTabAt(3);
//    }
//    private void addTabs(ViewPager viewPager) {
//        MyOrderTabAdapter adapter = new MyOrderTabAdapter(getSupportFragmentManager());
//        adapter.addFrag(new AllOrderFragment(), "All");
//        adapter.addFrag(new OrderedOrderFragment(), "Ordered");
//        adapter.addFrag(new ShippingOrderFragment(), "Shipped");
//        adapter.addFrag(new DeliveredOrderFragment(), "Delivered");
//        viewPager.setAdapter(adapter);
//    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE); // optional
        }
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, activity.getFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }

    @Override
    public void showMessage(Activity activity, String message) {
        Utils.showMessage(activity, message);
    }

    @Override
    public void setAdapter() {

//        myOrderTabAdapter = new MyOrderTabAdapter(activity, getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(myOrderTabAdapter);

    }
}