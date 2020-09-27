package com.baytelhekma.fawry;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.emeint.android.fawryplugin.Plugininterfacing.FawrySdk;
import com.emeint.android.fawryplugin.Plugininterfacing.PayableItem;
import com.emeint.android.fawryplugin.exceptions.FawryException;
import com.emeint.android.fawryplugin.interfaces.FawrySdkCallback;
import com.emeint.android.fawryplugin.views.cutomviews.FawryButton;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements PayableItem {

    public static String merchantCODE="1tSa6uxz2nSq2JY00BsPSw==";
    String merchantRefNum;
    public static final int PAYMENT_PLUGIN_REQUEST = 201;
    String url="https://atfawry.fawrystaging.com";
    PayableItem payableItem;
    List <PayableItem> items;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FawrySdk.init(FawrySdk.Styles.STYLE1);
        FawryButton checkout = new FawryButton(this);

        items = new ArrayList<>();
        payableItem=this;

        items.add(payableItem);
        merchantRefNum = randomAlphaNumeric(16);


        try {
            Log.e("FawryPluginERROR_OBJECT",payableItem.getFawryItemDescription()+" "+payableItem.getFawryItemPrice()+" "+payableItem.getFawryItemQuantity()+" "+payableItem
                    .getFawryItemSKU());
            FawrySdk.initialize(this,url ,
                    new FawrySdkCallback() {
                        @Override  public void paymentOperationSuccess(String trxID, Object customParams)
                        {
                            Log.e("ahmedSuccess",customParams+"");

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





//        try {
//            Log.e("FawryPluginERROR_OBJECT",payableItem.getFawryItemDescription()+" "+payableItem.getFawryItemPrice()+" "+payableItem.getFawryItemQuantity()+" "+payableItem
//                    .getFawryItemSKU());
//            FawrySdk.initializeCardTokenizer(this, url,
//                    new FawrySdkCallback() {
//                        @Override
//                        public void paymentOperationSuccess(String s, Object o) {
//                            Log.e("ahmed2",o+"");
//                        }
//
//                        @Override
//                        public void paymentOperationFailure(String s, Object o) {
//                            Log.e("ahmed",o+"");
//                        }
//                    }, merchantCODE,
//                    "123456789123456798", "01156616441",
//                    "ahmed_mohamed299@hotmail.com", FawrySdk.Language.EN,PAYMENT_PLUGIN_REQUEST ,
//                    null, new UUID(1, 2));
//        } catch (FawryException e) {
//            e.printStackTrace();
//
//        }



//                d425d389d5404e52bbe9e9bbb4a5a395

    }


    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("ahmed",data.getIntExtra("FawryPluginAppClass.REQUEST_RESULT",0)+"");
        Log.e("ERROR_MESSAGE_KEY",data.getStringExtra("FawryPluginAppClass.ERROR_MESSAGE_KEY")+"");
//        FawryPaymentActivity.getFawryIntent(MainActivity.this,merchantCODE,items, "FawrySdk.Language.EN");
//        FawrySdk.startPaymentActivity(MainActivity.this);

    }

    @Override
    public String getFawryItemDescription() {
        return "bla bla bla ";
    }

    @Override
    public String getFawryItemSKU() {
        return "bayt el hekma";
    }

    @Override
    public String getFawryItemPrice() {
        return "299";
    }

    @Override
    public String getFawryItemQuantity() {
        return "1";
    }

    @Override
    public String getFawryItemVariantCode() {
        return null;
    }

    @Override
    public String[] getFawryItemReservationCodes() {
        return new String[0];
    }

    @Override
    public String getFawryItemHeight() {
        return null;
    }

    @Override
    public String getFawryItemWidth() {
        return null;
    }

    @Override
    public String getFawryItemLength() {
        return null;
    }

    @Override
    public String getFawryItemWeight() {
        return null;
    }

    @Override
    public String getFawryItemEarningRuleID() {
        return null;
    }

    @Override
    public String getFawryItemOriginalPrice() {
        return null;
    }
}