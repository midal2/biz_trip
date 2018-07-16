package biztrip.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import biztrip.stock.RecyclerStockDataAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerStockDataAdapter recyclerDataAdapter = new RecyclerStockDataAdapter();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(recyclerDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));



        /*if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_layout, new MainFragment())
                    .commit();
        }*/
    }
}
