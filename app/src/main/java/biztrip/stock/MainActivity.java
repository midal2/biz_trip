package biztrip.stock;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerStockDataAdapter recyclerStockDataAdapter;
    private Stock.Present stockPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stockPresent = new StockPresenter();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerStockDataAdapter = new RecyclerStockDataAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(recyclerStockDataAdapter);

        StockList_AsyncTask stockListAsyncTask = new StockList_AsyncTask(stockPresent, recyclerView, recyclerStockDataAdapter);
        stockListAsyncTask.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();

        DataGather dataGather = new DataGather(stockPresent, recyclerStockDataAdapter);
        dataGather.execute(this, null);
    }

}

class StockList_AsyncTask extends AsyncTask<AppCompatActivity, Integer, Void> implements Stock.ViewAll {
    private Stock.Present stockPresent;
    private RecyclerStockDataAdapter recyclerStockDataAdapter;
    private RecyclerView recyclerView;

    public StockList_AsyncTask(Stock.Present present, RecyclerView view, RecyclerStockDataAdapter adapter) {
        super();

        this.stockPresent = present;
        this.recyclerView = view;
        this.recyclerStockDataAdapter = adapter;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        recyclerView.setAdapter(recyclerStockDataAdapter);
    }

    @Override
    protected Void doInBackground(AppCompatActivity... appCompatActivities) {
        stockPresent.getStockList(this);
        return null;
    }

    @Override
    public void showAllStockData(List<StockInfo> stockInfos) {
        recyclerStockDataAdapter.setStockInfos((ArrayList<StockInfo>)stockInfos);
        publishProgress(1);
    }
}


class DataGather extends AsyncTask<AppCompatActivity, Integer, Void> implements Stock.ViewRecently{
    private RecyclerStockDataAdapter recyclerStockDataAdapter;
    private Stock.Present stockPresent;

    public DataGather(Stock.Present present, RecyclerStockDataAdapter recyclerStockDataAdapter) {
        this.stockPresent = present;
        this.recyclerStockDataAdapter = recyclerStockDataAdapter;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("MIDAL", "onProgressUpdate");
        for(Integer selectedIdx : values){
            recyclerStockDataAdapter.notifyItemChanged(selectedIdx);
        }
    }

    @Override
    protected Void doInBackground(AppCompatActivity... appCompatActivities) {
        Log.d("MIDAL", "doInBackground");

        while (true) {
            try {
                stockPresent.getRecentlyData(recyclerStockDataAdapter.getStockInfos(), this);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(getClass().getSimpleName(), e.getMessage()) ;
            }
        }
    }

    @Override
    public void showRecentlyData(List<StockInfo> newData) {
        if (newData == null || newData.size() == 0){
            return;
        }

        for(StockInfo newInfo : newData){
            StockInfo oldInfo = getOldInfo(newInfo.getStockCd());

            if (oldInfo == null){
                recyclerStockDataAdapter.getStockInfos().add(newInfo);
                publishProgress(recyclerStockDataAdapter.getStockInfos().indexOf(newInfo));
                continue;
            }

            if (oldInfo.getUpdateDt().equals(newInfo.getUpdateDt())){
                continue;
            }

            oldInfo.setStockNm(newInfo.getStockNm());
            oldInfo.setDungrak(newInfo.getDungrak());
            oldInfo.setDebi(newInfo.getDebi());
            oldInfo.setUpdateDt(newInfo.getUpdateDt());
            oldInfo.setNowPrice(newInfo.getNowPrice());
            publishProgress(recyclerStockDataAdapter.getStockInfos().indexOf(oldInfo));
        }
    }

    private StockInfo getOldInfo(String stockCd) {
        ArrayList<StockInfo> stockInfos = recyclerStockDataAdapter.getStockInfos();

        for(StockInfo selectedInfo : stockInfos){
            if (stockCd.equals(selectedInfo.getStockCd())){
                return selectedInfo;
            }
        }

        return null;
    }
}
