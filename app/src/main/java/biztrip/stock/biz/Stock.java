package biztrip.stock.biz;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    public interface Model{
        ArrayList<StockInfo> getAllInfo();
    }

    public interface Present{
        void getStockList(Stock.ViewAll view);
        void getRecentlyData(Stock.ViewRecently view);
    }

    public interface ViewAll{
        /**
         * 전체 목록을 표시한다
         * @param stockInfos
         */
        void showAllStockData(List<StockInfo> stockInfos);
    }

    public interface ViewRecently{
        /**
         * 최신목록을 표시한다
         * @param newData
         */
        void showRecentlyData(List<StockInfo> newData);
    }

}
