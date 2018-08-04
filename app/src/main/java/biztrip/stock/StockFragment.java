package biztrip.stock;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import biztrip.stock.biz.Stock;
import biztrip.stock.biz.StockInfo;

public class StockFragment extends Fragment {

    // TODO: Customize parameters
    private Stock.Present stockPresent;
    private StockRecyclerDataAdapter recyclerStockDataAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StockFragment() {

    }

    public static StockFragment newInstance(Stock.Present stockPresent) {
        StockFragment fragment = new StockFragment();
        fragment.setStockPresent(stockPresent);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_list , container, false);

        recyclerStockDataAdapter = new StockRecyclerDataAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(recyclerStockDataAdapter);

        StockList_AsyncTask stockListAsyncTask = new StockList_AsyncTask(recyclerView, recyclerStockDataAdapter);
        stockListAsyncTask.execute(stockPresent);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(this.getClass().getSimpleName(),"OnStart");

        DataGather dataGather = new DataGather(recyclerStockDataAdapter);
        dataGather.execute(stockPresent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setStockPresent(Stock.Present stockPresent) {
        this.stockPresent = stockPresent;
    }
}

class StockList_AsyncTask extends AsyncTask<Stock.Present, StockRecyclerDataAdapter, Void> implements Stock.ViewAll {
    private StockRecyclerDataAdapter recyclerStockDataAdapter;
    private RecyclerView recyclerView;

    public StockList_AsyncTask(RecyclerView view, StockRecyclerDataAdapter adapter) {
        super();

        this.recyclerView = view;
        this.recyclerStockDataAdapter = adapter;
    }

    @Override
    protected void onProgressUpdate(StockRecyclerDataAdapter... values) {
        recyclerView.setAdapter(values[0]);
    }

    @Override
    protected Void doInBackground(Stock.Present... params) {
        params[0].getStockList(this);
        return null;
    }

    @Override
    public void showAllStockData(List<StockInfo> stockInfos) {
        recyclerStockDataAdapter.setStockInfos((ArrayList<StockInfo>)stockInfos);
        publishProgress(recyclerStockDataAdapter);
    }
}


class DataGather extends AsyncTask<Stock.Present, Integer, Void> implements Stock.ViewRecently {
    private StockRecyclerDataAdapter recyclerStockDataAdapter;

    public DataGather(StockRecyclerDataAdapter recyclerStockDataAdapter) {
        this.recyclerStockDataAdapter = recyclerStockDataAdapter;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        for(Integer selectedIdx : values){
            recyclerStockDataAdapter.notifyItemChanged(selectedIdx);
        }
    }

    @Override
    protected Void doInBackground(Stock.Present... presents) {
        while (true) {
            try {
                Log.d(this.getClass().getSimpleName(),"doInBackground");
                presents[0].getRecentlyData(this);
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
