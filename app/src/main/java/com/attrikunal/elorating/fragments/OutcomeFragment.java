package com.attrikunal.elorating.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attrikunal.elorating.R;
import com.google.android.material.chip.ChipGroup;


public class OutcomeFragment extends Fragment {

    private static ChipGroup outcomeGroup;
    public OutcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_outcome, container, false);
        outcomeGroup = view.findViewById(R.id.chipgroup);
        return view;
    }

    public static int getSelectedChip() {
        return outcomeGroup.getCheckedChipId();
    }
}