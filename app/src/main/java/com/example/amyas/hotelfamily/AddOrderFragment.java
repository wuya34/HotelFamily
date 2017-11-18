package com.example.amyas.hotelfamily;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amyas.hotelfamily.model.Order;
import com.example.amyas.hotelfamily.model.OrderLab;
import com.example.amyas.hotelfamily.util.ToastUtil;

import java.util.UUID;

/**
 * author: Amyas
 * date: 2017/11/18
 */

public class AddOrderFragment extends Fragment {
    public static final int UPDATE_ORDER_CODE = 1;
    public static final int ADD_ORDER_CODE = 2;
    private Order mOrder;
    public static final String ORDER_UUID = "AddOrderFragment.uuid";

    public static AddOrderFragment newInstance(UUID uuid){
        AddOrderFragment fragment = new AddOrderFragment();
        if (uuid!=null){
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
        View view = inflater.inflate(R.layout.add_order,container, false);
        Bundle bundle = this.getArguments();
        if (bundle!=null){
            UUID uuid = (UUID) bundle.getSerializable(ORDER_UUID);
//            ToastUtil.showToast(getActivity(), "received uuid: "+uuid);
            mOrder = OrderLab.get(getActivity()).getOrder(uuid);
        }

        // TODO: 2017/11/18 增加菜单选项
        return view;
    }
}
