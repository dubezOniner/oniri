package com.flexabyse.app.oniri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    public static DreamsAdapter dreamsAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.dreams_journal);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<DreamItem> list = new ArrayList<>();

        list.add(new DreamItem("Walking on air again", "16 August 2022", R.drawable.img_4));
        list.add(new DreamItem("Walking on rocks", "15 August 2022", R.drawable.img_2));
        list.add(new DreamItem("Walking on air", "13 August 2022", R.drawable.img_3));
        list.add(new DreamItem("Walking on water", "12 August 2022", R.drawable.img_2));
        list.add(new DreamItem("Walking on the Beach", "11 August 2022", R.drawable.img_1));


        // specify an adapter
        dreamsAdapter = new DreamsAdapter(getContext());
        dreamsAdapter.setItems(list);
        mRecyclerView.setAdapter(dreamsAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    private class DreamItem {

        int img_id = 0;
        String date = "";
        String title = "";

        public DreamItem(String title, String date, int img)
        {
            img_id = img;
            this.date = date;
            this.title = title;
        }
    }

    private class DreamsAdapter extends RecyclerView.Adapter<DreamsAdapter.ViewHolder> {

        private List<DreamItem> mData;
        private LayoutInflater mInflater;

        // data is passed into the constructor
        DreamsAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        // inflates the row layout from xml when needed
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.dream_tile_layout, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.bindData(mData.get(position));
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }

        // resets the list with a new set of data
        public void setItems(List<DreamItem> items) {
            mData = items;
        }

        public List<DreamItem> getItems() {
            return this.mData;
        }

        // stores and recycles views as they are scrolled off screen
        class ViewHolder extends RecyclerView.ViewHolder {
            TextView label, date;
            ImageView bg;

            ViewHolder(View itemView) {
                super(itemView);
                label = itemView.findViewById(R.id.title);
                date = itemView.findViewById(R.id.date);
                bg = itemView.findViewById(R.id.bg);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = getAdapterPosition();

                        // check if item still exists
                        if(pos != RecyclerView.NO_POSITION) {

                            DreamItem dream = mData.get(pos);
                            Intent detailIntent = new Intent(getContext(), DetailActivity.class);
                            detailIntent.putExtra("title", dream.title);
                            detailIntent.putExtra("date", dream.date);
                            detailIntent.putExtra("img", dream.img_id);
                            startActivity(detailIntent);
                        }
                    }
                });
            }

            private void bindData(DreamItem item) {

                label.setText(item.title);
                date.setText(item.date);
                bg.setImageResource(item.img_id);
            }
        }
    }
}