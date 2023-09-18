package com.example.travel.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.travel.MainActivity;
import com.example.travel.R;
import com.example.travel.adapter.RecyclerAdapter;

import java.util.ArrayList;


public class JourneyFragment extends Fragment {

    View view;
    MainActivity mainActivity;
    RecyclerView recyclerView;
    TextView textView;
    Button button;

    ArrayList<Uri> uri = new ArrayList<>();
    RecyclerAdapter adapter;

    private static final int Read_Permission = 101;


    public JourneyFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_journey, container, false);
        mainActivity = (MainActivity) getActivity();
        textView = view.findViewById(R.id.totalPhotos);
        recyclerView = view.findViewById(R.id.gallery);
        button = view.findViewById(R.id.pick);

        adapter = new RecyclerAdapter(uri);
        recyclerView.setLayoutManager(new GridLayoutManager(mainActivity, 4));

        recyclerView.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Read_Permission);

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select picture"), 1);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            if(data.getClipData()!= null){
                int x = data.getClipData().getItemCount();

                for (int i = 0; i < x; i++){
                    uri.add(data.getClipData().getItemAt(i).getUri());

                }

                adapter.notifyDataSetChanged();
                textView.setText("Photos (" +uri.size()+ ")");
            } else if(data.getData() != null){
                String imgURL = data.getData().getPath();
                uri.add(Uri.parse(imgURL));
            }
        }
    }
}