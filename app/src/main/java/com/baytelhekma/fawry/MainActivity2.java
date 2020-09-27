package com.baytelhekma.fawry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emeint.android.fawryplugin.Plugininterfacing.FawrySdk;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        FawrySdk.resetFawrySDK();
//        FawrySdk.init(FawrySdk.Styles.STYLE2);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
//                startActivity(intent);
//                ItemListDialogFragment.newInstance("","","","").show(getSupportFragmentManager(), "dialog");
                ItemListDialogFragment2.newInstance(30).show(getSupportFragmentManager(), "dialog");
            }
        });
    }
}