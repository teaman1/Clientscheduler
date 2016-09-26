package com.bignerdranch.android.clientscheduler;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by teaman1 on 9/15/2016.
 */
public class customerhelp extends Fragment {
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private customerinfo mCustomerinfo;
    private RecyclerView mclientlistRecyclerView;
    private customerlistAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_client_list,container,false);

        mclientlistRecyclerView = (RecyclerView)view
                .findViewById(R.id.clientlist);
        mclientlistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.options, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_new_crime:
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.setTargetFragment(customerhelp.this, REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
                return true;
            case R.id.menu_item_new_customer:
                customerinfo customer = new customerinfo();
                Crimelab.get(getActivity()).addcustomer(customer);
                Intent intent = addnewcustomer
                        .newIntent(getActivity(), customer.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        Crimelab crimelab = Crimelab.get(getActivity());
        List<customerinfo>customerinfos=crimelab.getCrimes();
        if (mAdapter== null) {
            mAdapter = new customerlistAdapter(customerinfos);
            mclientlistRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setClientlists(customerinfos);
            mAdapter.notifyDataSetChanged();
        }
    }
    private class clientlistholder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        private customerinfo mCustomerinfo;
        private TextView mTitleTextView;
        private ImageView mImageView;
        public clientlistholder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView)itemView.findViewById(R.id.textview);
            mImageView = (ImageView)itemView.findViewById(R.id.imageview);
        }
        public void bindcustomerinfo(customerinfo Customerinfo){
            mCustomerinfo = Customerinfo;
            mTitleTextView.setText(mCustomerinfo.getTitle());
        }

        @Override
        public void onClick(View v) {
            Intent intent = addnewcustomer.newIntent(getActivity(),mCustomerinfo.getId());
            startActivity(intent);
        }
    }

    private class customerlistAdapter extends RecyclerView.Adapter<clientlistholder>{


        private List<customerinfo>mClientlists;
        public customerlistAdapter(List<customerinfo>clientlists){
            mClientlists = clientlists;
        }

        @Override
        public clientlistholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.clientlist, parent, false);
            return new clientlistholder(view);
        }

        @Override
        public void onBindViewHolder(clientlistholder holder, int position) {
            customerinfo customer = mClientlists.get(position);
            holder.bindcustomerinfo(customer);

        }

        @Override
        public int getItemCount() {
            return mClientlists.size();
        }
        public void setClientlists(List<customerinfo>customerinfos){
            mClientlists = customerinfos;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;


        }
        if(requestCode==REQUEST_DATE){
            Intent i = new Intent(getActivity(), login.class);
            startActivity(i);
        }
    }

}


