package com.example.amyas.hotelfamily;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class FamilyActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener,
        AboutUsFragment.OnFragmentInteractionListener, BookFragment.OnFragmentInteractionListener,
HotFragment.OnFragmentInteractionListener, TakeOutFragment.OnFragmentInteractionListener{
    private AboutUsFragment mAboutUsFragment;
    private BookFragment mBookFragment;
    private HotFragment mHotFragment;
    private TakeOutFragment mTakeOutFragment;
    private int navigator_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar navigationBar = findViewById(R.id.fragment_navigator);
        navigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor(R.color.red_new_year)
                .setInActiveColor(R.color.light_red_new_year);
        navigationBar.setTabSelectedListener(this);
        navigationBar.addItem(new BottomNavigationItem(R.drawable.ic_action_book, "预订"))
                .addItem(new BottomNavigationItem(R.drawable.ic_action_take_out, "外卖"))
                .addItem(new BottomNavigationItem(R.drawable.ic_action_hot, "热卖"))
                .addItem(new BottomNavigationItem(R.drawable.ic_action_about_us, "家"))
                .setFirstSelectedPosition(0)
                .initialise();
        init();
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
                    mBookFragment = new BookFragment();
                }
                if (navigator_position==0){
//                    SwitchFragment(mBookFragment,mBookFragment);
                }else if (navigator_position==1){
                    SwitchFragment(mTakeOutFragment,mBookFragment);
                }else if (navigator_position==2){
                    SwitchFragment(mHotFragment,mBookFragment);
                }else {
                    SwitchFragment(mBookFragment, mAboutUsFragment);
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
                    SwitchFragment(mAboutUsFragment, mTakeOutFragment);
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
                    //                    SwitchFragment(mHotFragment,mAboutUsFragment);
                }else {
                    SwitchFragment(mAboutUsFragment, mHotFragment);
                }
                navigator_position = 2;
                break;
            case 3:
                if (mAboutUsFragment==null){
                    mAboutUsFragment = new AboutUsFragment();
                }
                if (navigator_position==0){
                    SwitchFragment(mBookFragment,mAboutUsFragment);
                }else if (navigator_position==1){
                    SwitchFragment(mTakeOutFragment,mAboutUsFragment);
                }else if (navigator_position==2){
                    SwitchFragment(mHotFragment,mAboutUsFragment);
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

    private void init(){
        mBookFragment = new BookFragment();
        SwitchFragment(mBookFragment, mBookFragment);
    }

}
