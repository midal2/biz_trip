package biztrip.stock;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static biztrip.util.Common.null2void;

public class StockModel implements Stock.Model {

    private HttpBizService httpBizService = new HttpBizService();

    @Override
    public ArrayList<StockInfo> getAllInfo() {
        ArrayList<StockInfo> stockInfos = new ArrayList();

        try{
            Map<String, Object> resultMap = httpBizService.send("http://13.209.159.207:9000/stock/getAllInfo", "");

            //비정상응답 종료
            if (resultMap == null){
                return stockInfos;
            }

            //Http 응답 오류 비정상처리
            if (!"200".equals(resultMap.get("RESPONSE_CODE"))) {
                return stockInfos;
            }

            List<Map<String, Object>> mapList = (List<Map<String, Object>>)resultMap.get("RESPONSE_DATA");
            for (Map<String, Object> selectedMap : mapList) {
                stockInfos.add(createStockInfo(selectedMap));
            }

        }catch (Exception e){
            Log.e(getClass().getSimpleName(), e.getMessage());
        }

        return stockInfos;
    }


    private StockInfo createStockInfo(Map<String, Object> selectedMap) {
        StockInfo stockInfo = new StockInfo();

        stockInfo.setStockCd(null2void(selectedMap.get("stockCd")));
        stockInfo.setStockNm(null2void(selectedMap.get("jongName")));
        stockInfo.setNowPrice(Long.parseLong(null2void(selectedMap.get("curJuka"))));
        stockInfo.setDebi(Long.parseLong(null2void(selectedMap.get("debi"))));
        stockInfo.setDungrak(null2void(selectedMap.get("dungRak")));
        stockInfo.setUpdateDt(null2void(selectedMap.get("updateDt")));

        return stockInfo;
    }
}
