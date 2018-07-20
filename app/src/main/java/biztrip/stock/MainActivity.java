package biztrip.stock;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerStockDataAdapter recyclerStockDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerStockDataAdapter = new RecyclerStockDataAdapter();
        recyclerView.setAdapter(recyclerStockDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        DataGather dataGather = new DataGather(recyclerStockDataAdapter);
        dataGather.execute(this, null);
    }


}

class DataGather extends AsyncTask<AppCompatActivity, Integer, Void> implements Stock.Present, Stock.View{
    private RecyclerStockDataAdapter recyclerStockDataAdapter;

    public DataGather(){
        super();
        this.recyclerStockDataAdapter = new RecyclerStockDataAdapter();
    }

    public DataGather(RecyclerStockDataAdapter recyclerStockDataAdapter) {
        this.recyclerStockDataAdapter = recyclerStockDataAdapter;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("MIDAL", "onProgressUpdate");

        showRecentlyData(recyclerStockDataAdapter.getStockInfos());
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(AppCompatActivity... appCompatActivities) {
        int cnt = 0;
        Log.d("MIDAL", "doInBackground");

        while (true) {
            try {
                Log.d("MIDAL-INNER111", "doInBackground");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d("MIDAL-INNER222", "doInBackground");
            int pos = cnt % recyclerStockDataAdapter.getItemCount();
            publishProgress(pos);
            ++cnt;

        }
    }

    @Override
    public void getRecentlyData(List<StockInfo> info) {
        showRecentlyData(info);
    }

    @Override
    public void showRecentlyData(List<StockInfo> stockInfos) {
        for(int i=0; i<stockInfos.size(); i++){
            StockInfo stockInfo = recyclerStockDataAdapter.getStockInfos().get(i);
            stockInfo.setUpdateDt(new Date());
            recyclerStockDataAdapter.notifyItemChanged(i);
        }


    }
}
