package com.baytelhekma.fawry;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.emeint.android.fawryplugin.Plugininterfacing.FawrySdk;
import com.emeint.android.fawryplugin.Plugininterfacing.PayableItem;
import com.emeint.android.fawryplugin.exceptions.FawryException;
import com.emeint.android.fawryplugin.interfaces.FawrySdkCallback;
import com.emeint.android.fawryplugin.models.BillItem;
import com.emeint.android.fawryplugin.views.cutomviews.FawryButton;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment2.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ItemListDialogFragment2 extends BottomSheetDialogFragment  {
    String merchantRefNum;
    public static final int PAYMENT_PLUGIN_REQUEST = 201;
    public static String merchantCODE="q4M2SYnoFiG6h4pxxCxOaw==";
    String url="https://www.atfawry.com";
    PayableItem payableItem;
    List<PayableItem> items;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String ARG_ITEM_COUNT = "item_count";


    public static ItemListDialogFragment2 newInstance(int itemCount) {
        final ItemListDialogFragment2 fragment = new ItemListDialogFragment2();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FawrySdk.init(FawrySdk.Styles.STYLE1);
        View view=inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog2, container, false);
        items = new ArrayList<>();

        payableItem=new BillItem("name","dec","1","200") ;
        items.add(payableItem);
        merchantRefNum = randomAlphaNumeric(16);


        try {
            Log.e("FawryPluginERROR_OBJECT",payableItem.getFawryItemDescription()+" "+payableItem.getFawryItemPrice()+" "+payableItem.getFawryItemQuantity()+" "+payableItem
                    .getFawryItemSKU());
            FawrySdk.initialize(requireActivity(),url ,
                    new FawrySdkCallback() {
                        @Override  public void paymentOperationSuccess(String trxID, Object customParams)
                        {

                        }
                        @Override  public void paymentOperationFailure(String errorMessage, Object customParams)
                        {
                            Log.e("ahmederror",customParams+"");
                        }
                    }, merchantCODE, merchantRefNum, items,
                    FawrySdk.Language.EN ,
                    PAYMENT_PLUGIN_REQUEST , null, new UUID(1, 2));

        } catch (FawryException e) {
            e.printStackTrace();
        }

        FawrySdk.startPaymentActivity(requireContext());
        return view;
    }


    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}