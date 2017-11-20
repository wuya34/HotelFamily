package com.example.amyas.hotelfamily;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.amyas.hotelfamily.db.OrderDbSchema;
import com.example.amyas.hotelfamily.model.Order;
import com.example.amyas.hotelfamily.model.OrderLab;
import com.example.amyas.hotelfamily.util.PickerFragment;

import java.util.UUID;

/**
 * author: Amyas
 * date: 2017/11/18
 */

public class AddOrderFragment extends Fragment implements View.OnClickListener {
    public static final int UPDATE_ORDER_CODE = 1;
    public static final int ADD_ORDER_CODE = 2;
    public static final int DESK_PICK = 3;
    public static final int NUMBER_PICK = 4;
    public static final int REQUEST_CODE = 5;
    public static final int RESULT_OK = 6;
    public static final String DIALOG_PICKER = "DialogPicker";
    public static final String ORDER_UUID = "AddOrderFragment.uuid";
    private Order mOrder;
    private Button mSelectDesk;
    private Button mSelectNumber;

    public static AddOrderFragment newInstance(UUID uuid) {
        AddOrderFragment fragment = new AddOrderFragment();
        if (uuid != null) {
            Bundle args = new Bundle();
            args.putSerializable(ORDER_UUID, uuid);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_order, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            UUID uuid = (UUID) bundle.getSerializable(ORDER_UUID);
            //            ToastUtil.showToast(getActivity(), "received uuid: "+uuid);
            mOrder = OrderLab.get(getActivity()).getOrder(uuid);
        }

        mSelectDesk = view.findViewById(R.id.select_desk);
        mSelectNumber = view.findViewById(R.id.select_number);
        mSelectNumber.setOnClickListener(this);
        mSelectDesk.setOnClickListener(this);
        // TODO: 2017/11/18 增加菜单选项
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_number:
                prepareLoopView("用餐人数：", NUMBER_PICK);
                break;
            case R.id.select_desk:
                prepareLoopView("可选桌号", DESK_PICK);
                break;
        }
    }

    private void prepareLoopView(String title, int type) {
        PickerFragment dialog = PickerFragment.newInstance(title, type);
        FragmentManager manager = getFragmentManager();
        dialog.setTargetFragment(AddOrderFragment.this, REQUEST_CODE);
        dialog.show(manager, DIALOG_PICKER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE) {
            int type = (int) data.getSerializableExtra(PickerFragment.EXTRA_TYPE);
            String text = (String) data.getSerializableExtra(PickerFragment.EXTRA_DATA);
            if (type==NUMBER_PICK){
                mSelectNumber.setText(text);
            }
            if (type==DESK_PICK){
                mSelectDesk.setText(text);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        OrderLab.get(getActivity()).updateOrder(mOrder);
    }
}
