package com.example.amyas.hotelfamily;

import android.content.Context;
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
import com.example.amyas.hotelfamily.model.DeskOrder;
import com.example.amyas.hotelfamily.model.DeskTable;
import com.example.amyas.hotelfamily.model.OrderLab;

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
public class BookFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final int BOOK_CODE = 0;
    private String mParam1;
    private String mParam2;
    private DeskOrder mDeskOrder;
    private DeskTable mDeskTable;
    private BookAdapter mBookAdapter;

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
        List<DeskTable> deskTables = OrderLab.get(getActivity()).getTableList();

        mBookAdapter = new BookAdapter(deskTables);
        mRecyclerView.setAdapter(mBookAdapter);

        // TODO: 2017/11/17 增加开源项目的按钮，设置增加order的接口
        return view;
    }

//    public void onButtonPressed(DeskOrder deskOrder) {
//        if (mListener != null) {
//            mListener.OnOrderSelected(deskOrder);
//        }
//    }

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
        void OnOrderSelected(DeskTable deskTable);
    }
    private class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
        private List<DeskTable> mDeskTables;
        public BookAdapter(List<DeskTable> deskTables){
            mDeskTables = deskTables;
        }
        private void setDeskTables(List<DeskTable> deskTables){
            mDeskTables = deskTables;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.desk_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            mDeskTable = mDeskTables.get(position);
            holder.OnBind(mDeskTable);
        }

        @Override
        public int getItemCount() {
            return mDeskTables.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private DeskTable mDeskTable;
            private TextView mTextView;
            private ImageView mImageView;
            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                mTextView = itemView.findViewById(R.id.deskNumber);
                mImageView = itemView.findViewById(R.id.desk);
            }
            public void OnBind(DeskTable deskTable){
                mDeskTable = deskTable;
                mTextView.setText(String.valueOf(deskTable.getDeskNumber()));
                Glide.with(itemView)
                        .load(mDeskTable.isAvailable()?R.drawable.booked:R.drawable.available)
                        .into(mImageView);
            }

            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.OnOrderSelected(mDeskTable);
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        List<DeskTable> list = OrderLab.get(getActivity()).getTableList();
        if (mBookAdapter==null){
            mBookAdapter = new BookAdapter(list);
            mRecyclerView.setAdapter(mBookAdapter);
        }else {
            mBookAdapter.setDeskTables(list);
            mBookAdapter.notifyDataSetChanged();
        }
    }
}
