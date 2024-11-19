package com.example.madapp;

import android.content.Intent;
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
    private static final String ARG_USER_EMAIL = "user_email"; // New constant for user email
    private String barcodeData;
    private String userEmail; // New variable for user email
    private TextView title;

    public static BottomDialog newInstance(String barcodeData, String userEmail) { // Updated method signature
        BottomDialog bottomDialog = new BottomDialog();
        Bundle args = new Bundle();
        args.putString(ARG_BARCODE_DATA, barcodeData);
        args.putString(ARG_USER_EMAIL, userEmail); // Store user email in arguments
        bottomDialog.setArguments(args);
        return bottomDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_layout, container, false);
        title = view.findViewById(R.id.textView);
        if (getArguments() != null) {
            barcodeData = getArguments().getString(ARG_BARCODE_DATA);
            userEmail = getArguments().getString(ARG_USER_EMAIL);
            title.setText(barcodeData);
        }

        title.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), ratingpage.class);
            intent.putExtra("groupID", barcodeData);
            intent.putExtra("username", userEmail);
            startActivity(intent);
        });

        return view;
    }
}
