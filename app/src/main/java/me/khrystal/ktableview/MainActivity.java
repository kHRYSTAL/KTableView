package me.khrystal.ktableview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.khrystal.ktabble.TableScrollView;

public class MainActivity extends AppCompatActivity {

    private TableScrollView mTableScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTableScrollView = (TableScrollView)findViewById(R.id.table_scrollview);
        List<String> columnNames = new ArrayList<>();
        List<String> rowNames = new ArrayList<>();
        List<List<String>> rowsData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            columnNames.add("Col"+i);
            rowNames.add("Row"+i);
        }

        for (int i = 0; i <= rowNames.size(); i++) {
            List<String> rows = new ArrayList<>();
            for (int j = 0; j <= columnNames.size(); j++) {
                if (j==0) rows.add("Row"+i);
                else rows.add("Data"+j);
            }
            rowsData.add(rows);
        }

        mTableScrollView.setColsNames(columnNames,200,150);
        mTableScrollView.setTableData(columnNames.size(),200,150,rowsData);
        mTableScrollView.setLeftTopText("表格",200,150);
        mTableScrollView.setOnRowsSwapListener(new TableScrollView.OnRowsSwapListener() {
            @Override
            public void onRowsSwap(int fromPosition, int toPosition) {
                Toast.makeText(MainActivity.this,"swap from "+ fromPosition+" to " + toPosition,Toast.LENGTH_SHORT).show();
            }
        });


    }
}
