package biztrip.stock;

import java.util.List;

/**
 * 주식정보를 관리한다
 */
public interface MonoitorStockInfo {
    /**
     * 최신 주식정보를 가져온다
     * @return
     */
    List<StockInfo> getRecentlyStockInfo();
}
