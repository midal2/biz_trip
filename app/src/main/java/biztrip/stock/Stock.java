package biztrip.stock;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    interface Model{
        ArrayList<StockInfo> getAllInfo();
    }

    interface Present{
        void getStockList(Stock.ViewAll view);
        void getRecentlyData(List<StockInfo> oldData, Stock.ViewRecently view);
    }

    interface ViewAll{
        /**
         * 전체 목록을 표시한다
         * @param stockInfos
         */
        void showAllStockData(List<StockInfo> stockInfos);
    }

    interface ViewRecently{
        /**
         * 최신목록을 표시한다
         * @param newData
         */
        void showRecentlyData(List<StockInfo> newData);
    }

}
