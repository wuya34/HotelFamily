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
import android.widget.EditText;

import com.example.amyas.hotelfamily.model.DeskOrder;
import com.example.amyas.hotelfamily.model.DeskTable;
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
    public static final String ORDER_UUID = "AddOrderFragment.uuid_order";
    public static final String TABLE_UUID = "AddOrderFragment.uuid_table";
    private DeskOrder mDeskOrder;
    private DeskTable mDeskTable;
    private Button mSelectDesk;
    private Button mSelectNumber;
    private EditText mEditText;

    public static AddOrderFragment newInstance(UUID uuidTable, UUID uuidOrder) {
        AddOrderFragment fragment = new AddOrderFragment();
        Bundle args = new Bundle();
        args.putSerializable(ORDER_UUID, uuidOrder);
        args.putSerializable(TABLE_UUID, uuidTable);
        fragment.setArguments(args);
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
            UUID uuidOrder = (UUID) bundle.getSerializable(ORDER_UUID);
            UUID uuidTable = (UUID) bundle.getSerializable(TABLE_UUID);
            //            ToastUtil.showToast(getActivity(), "received uuid: "+uuid);
            mDeskOrder = OrderLab.get(getActivity()).getOrder(uuidOrder);
            mDeskTable = OrderLab.get(getActivity()).getTable(uuidTable);
        }

        mEditText = view.findViewById(R.id.phone_edit);
        mSelectDesk = view.findViewById(R.id.select_desk);
        mSelectNumber = view.findViewById(R.id.select_number);
        mSelectNumber.setOnClickListener(this);
        mSelectDesk.setOnClickListener(this);

        String tmp;
        if ((tmp=mDeskTable.getDeskNumber())!=null){
            mSelectDesk.setText(tmp);
        }
        if ((tmp=mDeskTable.getNumberOfDiners())!=null){
            mSelectNumber.setText(tmp);
        }
        if ((tmp=mDeskTable.getPhoneNumber())!=null){
            mEditText.setText(tmp);
        }
        // TODO: 2017/11/21 添加isAvailable控件
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
        updateData();
    }

    private void updateData(){
        mDeskTable.setAvailable(false);
        mDeskTable.setDeskNumber(mSelectDesk.getText().toString());
        mDeskTable.setPhoneNumber(mEditText.getText().toString());
        mDeskTable.setNumberOfDiners(mSelectNumber.getText().toString());

        mDeskOrder.setDeskNumber(mSelectDesk.getText().toString());
        OrderLab.get(getActivity()).updateOrder(mDeskOrder);
        OrderLab.get(getActivity()).updateTable(mDeskTable);
    }
}
