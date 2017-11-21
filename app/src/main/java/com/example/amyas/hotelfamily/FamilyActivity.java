package com.example.amyas.hotelfamily;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.amyas.hotelfamily.model.DeskOrder;
import com.example.amyas.hotelfamily.model.DeskTable;
import com.example.amyas.hotelfamily.model.OrderLab;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.Random;
import java.util.UUID;

import static com.example.amyas.hotelfamily.AddOrderFragment.UPDATE_ORDER_CODE;

public class FamilyActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener,
        FeelingLuckyFragment.OnFragmentInteractionListener, BookFragment.OnOrderSelectedListener,
HotFragment.OnFragmentInteractionListener, TakeOutFragment.OnFragmentInteractionListener{
    private FeelingLuckyFragment mFeelingLuckyFragment;
    private BookFragment mBookFragment;
    private HotFragment mHotFragment;
    private TakeOutFragment mTakeOutFragment;
    private int navigator_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar navigationBar = findViewById(R.id.fragment_navigator);
        initNavigationBar(navigationBar);
        initFragment();
        initCircleMenu();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    private void SwitchFragment(android.support.v4.app.Fragment from, Fragment to) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (!to.isAdded()){
            transaction.add(R.id.fragment_container,to);
            transaction.hide(from).show(to);
        }else {
            transaction.hide(from).show(to);
        }
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public void onTabSelected(int position) {
        //        Toast.makeText(this, "位置--->"+position, Toast.LENGTH_SHORT).show();
        switch (position) {
            case 0:
                if (mBookFragment==null){
                    mBookFragment = BookFragment.newInstance(null, null);
                }
                if (navigator_position==0){
//                    SwitchFragment(mBookFragment,mBookFragment);
                }else if (navigator_position==1){
                    SwitchFragment(mTakeOutFragment,mBookFragment);
                }else if (navigator_position==2){
                    SwitchFragment(mHotFragment,mBookFragment);
                }else {
                    SwitchFragment(mFeelingLuckyFragment,mBookFragment);
                }
                navigator_position = 0;
                break;
            case 1:
                if (mTakeOutFragment==null){
                    mTakeOutFragment = new TakeOutFragment();
                }
                if (navigator_position==0){
                    SwitchFragment(mBookFragment,mTakeOutFragment);
                }else if (navigator_position==1){
                    //                    SwitchFragment(mTakeOutFragment,mTakeOutFragment);
                }else if (navigator_position==2){
                    SwitchFragment(mHotFragment,mTakeOutFragment);
                }else {
                    SwitchFragment(mFeelingLuckyFragment, mTakeOutFragment);
                }
                navigator_position = 1;
                break;
            case 2:
                if (mHotFragment==null){
                    mHotFragment = new HotFragment();
                }
                if (navigator_position==0){
                    SwitchFragment(mBookFragment,mHotFragment);
                }else if (navigator_position==1){
                    SwitchFragment(mTakeOutFragment,mHotFragment);
                }else if (navigator_position==2){
                    //                    SwitchFragment(mHotFragment,mFeelingLuckyFragment);
                }else {
                    SwitchFragment(mFeelingLuckyFragment, mHotFragment);
                }
                navigator_position = 2;
                break;
            case 3:
                if (mFeelingLuckyFragment ==null){
                    mFeelingLuckyFragment = new FeelingLuckyFragment();
                }
                if (navigator_position==0){
                    SwitchFragment(mBookFragment, mFeelingLuckyFragment);
                }else if (navigator_position==1){
                    SwitchFragment(mTakeOutFragment, mFeelingLuckyFragment);
                }else if (navigator_position==2){
                    SwitchFragment(mHotFragment, mFeelingLuckyFragment);
                }else {}
                navigator_position = 3;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private void initFragment(){
        mBookFragment = new BookFragment();
        SwitchFragment(mBookFragment, mBookFragment);
    }

    @Override
    public void OnOrderSelected(DeskTable deskTable) {
        String relativeString = deskTable.getRelativeString();
        DeskOrder order = OrderLab.get(FamilyActivity.this).orderQueryByRelativeString(relativeString);
        Intent intent = AddOrderActivity.newIntent(this, deskTable.getUUID(),order.getUUID());
        startActivityForResult(intent, UPDATE_ORDER_CODE);
    }
    private void initCircleMenu(){
        ImageView mIdea = new ImageView(this);
        mIdea.setImageResource(R.drawable.ic_action_idea);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(mIdea).build();
        ((FloatingActionButton.LayoutParams)actionButton.getLayoutParams()).setMargins(
                0,0,20,120
        );
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView mAddOrder = new ImageView(this);
        mAddOrder.setImageResource(R.drawable.ic_action_add_order);
        SubActionButton button1 = itemBuilder.setContentView(mAddOrder).build();
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1).attachTo(actionButton).build();

        mAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeskTable deskTable = new DeskTable();
                DeskOrder deskOrder = new DeskOrder();
                String relativeString = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
                deskTable.setRelativeString(relativeString);
                deskOrder.setRelativeString(relativeString);
                OrderLab.get(FamilyActivity.this).addOrder(deskOrder);
                OrderLab.get(FamilyActivity.this).addTable(deskTable);
                Intent intent = AddOrderActivity.newIntent(FamilyActivity.this,
                        deskTable.getUUID(),
                        deskOrder.getUUID());
                startActivityForResult(intent, AddOrderFragment.ADD_ORDER_CODE);
            }
        });
    }
    private void initNavigationBar(BottomNavigationBar navigationBar){
        navigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor(R.color.red_new_year)
                .setInActiveColor(R.color.light_red_new_year);
        navigationBar.setTabSelectedListener(this);
        navigationBar.addItem(new BottomNavigationItem(R.drawable.ic_action_book, "预订"))
                .addItem(new BottomNavigationItem(R.drawable.ic_action_take_out, "外卖"))
                .addItem(new BottomNavigationItem(R.drawable.ic_action_hot, "热卖"))
                .addItem(new BottomNavigationItem(R.drawable.ic_action_about_us, "我很幸运"))
                .setFirstSelectedPosition(0)
                .initialise();
    }
}
