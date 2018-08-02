package biztrip.stock;

import java.util.List;

public class StockPresenter implements Stock.Present {

    private Stock.Model stockModel;

    public StockPresenter(){
        init();
    }

    private void init() {
        stockModel = new StockModel();
    }

    @Override
    public void getStockList(Stock.ViewAll view) {
        view.showAllStockData(stockModel.getAllInfo());
    }

    @Override
    public void getRecentlyData(List<StockInfo> oldData, Stock.ViewRecently view) {
        view.showRecentlyData(stockModel.getAllInfo());
    }
}
