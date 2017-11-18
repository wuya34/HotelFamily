package com.example.amyas.hotelfamily;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amyas.hotelfamily.model.Order;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookFragment.OnOrderSelectedListener} interface
 * to handle interaction events.
 * Use the {@link BookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookFragment extends Fragment implements View.OnClickListener{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final int BOOK_CODE = 0;

    private String mParam1;
    private String mParam2;
    private Order mOrder;

    private RecyclerView mRecyclerView;


    private OnOrderSelectedListener mListener;

    public BookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookFragment.
     */
    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_book);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setPrice("100");order.setAvailable(true);
        orders.add(order);

        //OrderLab.get(getActivity()).getOrderList()
        mRecyclerView.setAdapter(new BookAdapter(orders));

        // TODO: 2017/11/17 增加开源项目的按钮，设置增加order的接口
        return view;
    }

    public void onButtonPressed(Order order) {
        if (mListener != null) {
            mListener.OnOrderSelected(order);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOrderSelectedListener) {
            mListener = (OnOrderSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.desk_item:
                if (mListener!=null){
                    mListener.OnOrderSelected(mOrder);
                }

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnOrderSelectedListener {
        void OnOrderSelected(Order order);
    }
    private class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
        private List<Order> mOrders;
        public BookAdapter(List<Order> orders){
            mOrders = orders;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.desk_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            mOrder = mOrders.get(position);
            holder.OnBind(mOrder);
        }

        @Override
        public int getItemCount() {
            return mOrders.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView mTextView;
            private ImageView mImageView;
            public ViewHolder(View itemView) {
                super(itemView);
                mTextView = itemView.findViewById(R.id.deskNumber);
                mImageView = itemView.findViewById(R.id.desk);
                itemView.setOnClickListener(BookFragment.this);
            }
            public void OnBind(Order order){
                mTextView.setText(String.valueOf(order.getDeskNumber()));
                Glide.with(itemView)
                        .load(order.isAvailable()?R.drawable.booked:R.drawable.available)
                        .into(mImageView);
            }
        }
    }

}
