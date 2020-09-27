package com.baytelhekma.fawry;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.baytelhekma.fawry.databinding.FragmentItemListDialogListDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ItemListDialogFragment extends BottomSheetDialogFragment  {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_DEC = "dec";
    private static final String ARG_ITEM_NAME = "name";
    private static final String ARG_ITEM_PRICE = "price";
    private static final String ARG_ITEM_QUANTITY = "quantity";
    FragmentItemListDialogListDialogBinding binding;
    // TODO: Customize parameters
    public static ItemListDialogFragment newInstance(String dec,String name,String price,String quantity) {
        final ItemListDialogFragment fragment = new ItemListDialogFragment();
        final Bundle args = new Bundle();
        args.putString(ARG_ITEM_DEC, dec);
        args.putString(ARG_ITEM_NAME, name);
        args.putString(ARG_ITEM_PRICE, price);
        args.putString(ARG_ITEM_QUANTITY, quantity);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentItemListDialogListDialogBinding.inflate(inflater, container, false);

        getArguments().getString(ARG_ITEM_DEC);
        getArguments().getString(ARG_ITEM_NAME);
        getArguments().getString(ARG_ITEM_PRICE);
        getArguments().getString(ARG_ITEM_QUANTITY);
        binding.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.cash.isChecked()){
                    Toast.makeText(requireActivity(), "Cash", Toast.LENGTH_SHORT).show();
                }
                if (binding.fawry.isChecked()){
                    Toast.makeText(requireActivity(), "Fawry", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });

        binding.cash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                   binding.textView.setVisibility(View.VISIBLE);
                   binding.fawry.setChecked(false);
                   binding.linear2.setBackgroundResource(R.drawable.border);
                   binding.linear.setBackgroundResource(0);
               }
            }
        });

        binding.fawry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    binding.cash.setChecked(false);
                    binding.textView.setVisibility(View.GONE);
                    binding.linear2.setBackgroundResource(0);
                    binding.linear.setBackgroundResource(R.drawable.border);
                }
            }
        });
        return binding.getRoot();
    }




}