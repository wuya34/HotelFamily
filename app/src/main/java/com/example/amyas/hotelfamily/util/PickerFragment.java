package com.example.amyas.hotelfamily.util;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.amyas.hotelfamily.AddOrderFragment;
import com.example.amyas.hotelfamily.R;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Amyas
 * date: 2017/11/20
 */

public class PickerFragment extends DialogFragment {
    public static final String EXTRA_DATA = "pickerFragment.data";
    public static final String EXTRA_TYPE = "pickerFragment.type";
    private List<String> mList;
    private String mTitle;
    private LoopView mLoopView;
    private int mType;
    private int expectIndex;
    private int currentIndex;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_picker,null);
        inflateData();
        FrameLayout frameLayout = view.findViewById(R.id.picker_container);
        mLoopView = new LoopView(getActivity());
        mLoopView.setNotLoop();
        mLoopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                currentIndex = index;
                ToastUtil.showToast(getActivity(), "index is"+index+"选择了"+ mList.get(currentIndex));
            }
        });
        mLoopView.setItems(mList);
        mLoopView.setInitPosition(expectIndex);
        currentIndex = expectIndex;
        mLoopView.setTextSize(30);
        frameLayout.addView(mLoopView);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(mTitle)
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(AddOrderFragment.RESULT_OK);
                    }
                })
                .create();
    }
    public static PickerFragment newInstance(String title, int type){
        PickerFragment fragment = new PickerFragment();
        fragment.mTitle = title;
        fragment.mType = type;
        return fragment;
    }
    private void inflateData(){
        mList = new ArrayList<>();
        if (mType== AddOrderFragment.NUMBER_PICK){

            for (int i = 1; i < 21; i++) {
                mList.add(i+" 人");
            }
            expectIndex = 5;
            return;
        }
        if (mType==AddOrderFragment.DESK_PICK){
            mList.add("6");
            mList.add("8");
            mList.add("66");
            mList.add("88");
            mList.add("666");
            mList.add("888");
            expectIndex = 0;
        }
    }
    private void sendResult(int resultCode){
        if (getTargetFragment()==null){
            return;
        }
        String result = mList.get(currentIndex);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATA, result);
        intent.putExtra(EXTRA_TYPE, mType);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode, intent);
    }
}
