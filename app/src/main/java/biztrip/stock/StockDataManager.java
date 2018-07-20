package biztrip.stock;

import java.util.List;
import java.util.Map;

public interface StockDataManager<E> {
    List<E> getData(Map param);
}
