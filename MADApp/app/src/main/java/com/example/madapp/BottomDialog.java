package com.example.madapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomDialog extends BottomSheetDialogFragment {

    private static final String ARG_BARCODE_DATA = "barcode_data";
    private String barcodeData;
    private TextView title;  // Initialize title here

    public static BottomDialog newInstance(String barcodeData) {
        BottomDialog bottomDialog = new BottomDialog();
        Bundle args = new Bundle();
        args.putString(ARG_BARCODE_DATA, barcodeData);
        bottomDialog.setArguments(args);
        return bottomDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_layout, container, false);
        title = view.findViewById(R.id.textView);  // Initialize title here

        // Retrieve barcode data from arguments
        if (getArguments() != null) {
            barcodeData = getArguments().getString(ARG_BARCODE_DATA);
            title.setText(barcodeData);
        }

        title.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.example.com")); // Placeholder, adjust as needed
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        });

        return view;
    }
}
