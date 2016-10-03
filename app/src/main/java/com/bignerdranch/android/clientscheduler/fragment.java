package com.bignerdranch.android.clientscheduler;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.UUID;
import java.util.jar.Manifest;

/**
 * Created by teaman1 on 8/30/2016.
 */
public class fragment extends Fragment {
    private static final String ARG_customer_ID = "customer_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int CAMERA_PERMISSION = 1;
    private static final int REQUEST_PHOTO= 2;
    private customerinfo mCustomerinfo;
    private File mPhotoFile;
    private ImageView mImageView;
    private Button mButton;
    private EditText clientlist;
    private EditText customer;

    public static fragment newInstance(UUID customerId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_customer_ID,customerId);
        fragment f = new fragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addnewcustomer, container, false);



        clientlist = (EditText)v.findViewById(R.id.clientlist);
        clientlist.setText(mCustomerinfo.getTitle());
        clientlist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCustomerinfo.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        customer = (EditText)v.findViewById(R.id.customername);
        customer.setText(mCustomerinfo.getClientlist());
        customer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCustomerinfo.setClientlist(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mButton = (Button) v.findViewById(R.id.button1);
        PackageManager packageManager = getActivity().getPackageManager();
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;
        mButton.setEnabled(canTakePhoto);

        if(ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.CAMERA}, CAMERA_PERMISSION);
        }

        if (canTakePhoto){
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        }

        mButton.setOnClickListener(new  View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           try {
                                               startActivityForResult(captureImage, REQUEST_PHOTO);
                                           }
                                           catch (Exception e){
                                               Toast.makeText(
                                                       getActivity(), "Could not take picture", Toast.LENGTH_SHORT
                                               ).show();
                                           }
                                       }
                                   });
        mImageView = (ImageView) v.findViewById(R.id.image);
        try {
            updatePhotoView();
        }
        catch (Exception e ){
            e.printStackTrace();
        }


        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.options, menu);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID customerId = (UUID)getArguments().getSerializable(ARG_customer_ID);
        mCustomerinfo = Crimelab.get(getActivity()).getCrime(customerId);
        mPhotoFile = Crimelab.get(getActivity()).getPhotoFile(mCustomerinfo);


        setHasOptionsMenu(true);


    }


    @Override
    public void onPause() {
        super.onPause();
        Crimelab.get(getActivity())
                .updatecustomer(mCustomerinfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_new_crime:
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.setTargetFragment(fragment.this, REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;


        }
        if(requestCode==REQUEST_DATE) {
            Intent i = new Intent(getActivity(), login.class);
            startActivity(i);
        } else if (requestCode == REQUEST_PHOTO){
            try {
                updatePhotoView();
            }
            catch (Exception e ){
                e.printStackTrace();
            }
        }


    }
    private void updatePhotoView(){
        if (mImageView == null || !mPhotoFile.exists()) {
            mImageView.setImageDrawable(null);
        }else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mImageView.setImageBitmap(bitmap);
        }
    }
}





