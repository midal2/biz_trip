package biztrip.stock;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import biztrip.stock.biz.Stock;
import biztrip.stock.biz.StockInfo;
import biztrip.stock.biz.StockPresenter;

public class StockFragment extends Fragment {

    private StockRecyclerDataAdapter recyclerStockDataAdapter;
    private StockList_AsyncTask stockListAsyncTask;
    private RecyclerView recyclerView;

    private static int cnt = 0;
    private int stockFragmentcnt = 0;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StockFragment() {
        this.stockFragmentcnt = ++cnt;
    }

    public static StockFragment newInstance() {
        Log.d("StockFragment", "newInstance");
        StockFragment fragment = new StockFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(), "onCreate/" + stockFragmentcnt);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(getClass().getSimpleName(), "onCreateView/" + stockFragmentcnt);
        View view = inflater.inflate(R.layout.fragment_stock_list , container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(getClass().getSimpleName(), "onViewCreated/" + stockFragmentcnt + "/" + Thread.currentThread().getId());

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerStockDataAdapter = new StockRecyclerDataAdapter();
        recyclerView.setAdapter(recyclerStockDataAdapter);

        stockListAsyncTask = new StockList_AsyncTask(stockFragmentcnt);
        stockListAsyncTask.execute();
    }

    class StockList_AsyncTask extends AsyncTask<Void, StockRecyclerDataAdapter, DataGather> implements Stock.ViewAll {
        private Stock.Present present;
        private int stockListcnt = 0;

        public StockList_AsyncTask(int cnt) {
            super();
            this.stockListcnt = cnt;
            Log.d("StockList_AsyncTask", "StockList_AsyncTask/" + stockListcnt + "/" + Thread.currentThread().getId());
            this.present = new StockPresenter();
        }

        @Override
        protected void onProgressUpdate(StockRecyclerDataAdapter... values) {
            Log.d(this.getClass().getSimpleName(), "onProgressUpdate/" + stockListcnt+ "/" + Thread.currentThread().getId());
            recyclerView.setAdapter(values[0]);
        }

        @Override
        protected DataGather doInBackground(Void ... params) {
            Log.d(this.getClass().getSimpleName(), "doInBackground/" + stockListcnt + "/" + Thread.currentThread().getId());
            present.getStockList(this);
            return new DataGather(stockListcnt);
        }

        @Override
        protected void onPostExecute(DataGather dataGather) {
            super.onPostExecute(dataGather);
            Log.d(this.getClass().getSimpleName(), "onPostExecute/" + stockListcnt+ "/" + Thread.currentThread().getId());
            dataGather.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, present);
        }

        @Override
        public void showAllStockData(List<StockInfo> stockInfos) {
            Log.d(this.getClass().getSimpleName(), "showAllStockData/" + stockListcnt + "/" + Thread.currentThread().getId());
            recyclerStockDataAdapter.setStockInfos((ArrayList<StockInfo>)stockInfos);
            publishProgress(recyclerStockDataAdapter);
        }
    }

    class DataGather extends AsyncTask<Stock.Present, Integer, Void> implements Stock.ViewRecently {
        private int id;

        public DataGather(int cnt) {
            super();
            id = cnt;
            Log.d(getClass().getSimpleName(), "DataGather/" + cnt + "/" + Thread.currentThread().getId());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(getClass().getSimpleName(), "onProgressUpdate/" + cnt+ "/" + Thread.currentThread().getId());
            super.onProgressUpdate(values);
            for(Integer selectedIdx : values){
                recyclerStockDataAdapter.notifyItemChanged(selectedIdx);
            }
        }

        @Override
        protected Void doInBackground(Stock.Present... presents) {
            while(!isCancelled()){
                Log.d(this.getClass().getSimpleName(),"doInBackground/" + id + "/" + Thread.currentThread().getId());
                presents[0].getRecentlyData(this);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
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

        @Nullable
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
}


