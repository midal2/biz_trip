package biztrip.stock;

import java.util.List;

public class Stock {

    public interface Present{
        void getRecentlyData(List<StockInfo> info);
    }

    public interface View{
        void showRecentlyData(List<StockInfo> stockInfos);
    }

}
