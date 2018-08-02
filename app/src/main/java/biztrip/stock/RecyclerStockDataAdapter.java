package biztrip.stock;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;

import static biztrip.util.Common.Step;

@Data
public class RecyclerStockDataAdapter extends RecyclerView.Adapter<StockViewHolder> {
    private ArrayList<StockInfo> stockInfos;


    public RecyclerStockDataAdapter(){
        stockInfos = createDummyData();
    }

    /**
     * 임시데이터
     * @return
     */
    private ArrayList<StockInfo> createDummyData() {
        ArrayList<StockInfo> stockInfos = new ArrayList();

        for(int i=0; i<20; i++){
            StockInfo stockInfo = new StockInfo();
            stockInfo.setNowPrice(i * 10000);
            stockInfo.setStockCd("0000" + i);
            stockInfo.setStockNm("Stock" + i);
            stockInfo.setUpdateDt("");

            stockInfos.add(stockInfo);
        }
        return  stockInfos;
    }

    private ArrayList<StockInfo> createRealData() {
        ArrayList<StockInfo> stockInfos = new ArrayList();

        HttpBizService httpBizService = new HttpBizService();
        Map<String, Object> resultMap = httpBizService.send("http://13.209.159.207:9000/stock/getAllInfo", "");

        if ("0000".equals(resultMap.get("RESPONSE_CODE"))) {
            List<Map<String, Object>> mapList = (List<Map<String, Object>>)resultMap.get("RESPONSE_DATA");
            for (Map<String, Object> selectedMap : mapList) {
                stockInfos.add(createStockInfo(selectedMap));
            }
        }

        return  stockInfos;
    }

    private StockInfo createStockInfo(Map<String, Object> selectedMap) {
        StockInfo stockInfo = new StockInfo();

        stockInfo.setStockCd(selectedMap.get("StockCd").toString());
        stockInfo.setStockNm(selectedMap.get("JongName").toString());
        stockInfo.setNowPrice(Long.parseLong(selectedMap.get("CurJuka").toString()));
        stockInfo.setUpdateDt(selectedMap.get("UpdateDt").toString());

        return stockInfo;
    }

    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Step("Context를 가져온다");
        Context context = parent.getContext();

        Step("LayoutInflater를 구성한다");
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        Step("view를 생성");
        View view = layoutInflater.inflate(R.layout.stock_item_fragment, parent, false);

        StockViewHolder viewHolder = new StockViewHolder(view) ;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        StockInfo stockInfo =  stockInfos.get(position);

        holder.txtNowPrice.setText(Long.toString(stockInfo.getNowPrice()));
        holder.txtTitle.setText(stockInfo.getStockNm() + "\n(" + stockInfo.getStockCd() + ")");
        holder.txtUpdateDt.setText(stockInfo.getUpdateDt().toString());
        holder.txtDebi.setText(stockInfo.getDebi() + "(" + stockInfo.getDungrak() + ")");

    }

    @Override
    public int getItemCount() {
        return stockInfos.size();
    }
}

/**
 * 증권각항목
 */
class StockViewHolder extends RecyclerView.ViewHolder{
    public TextView txtTitle;
    public TextView txtUpdateDt;
    public TextView txtNowPrice;
    public TextView txtDebi;

    public StockViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.stock_title_txt);
        txtUpdateDt = itemView.findViewById(R.id.update_dt_txt);
        txtNowPrice = itemView.findViewById(R.id.now_price_txt);
        txtDebi = itemView.findViewById(R.id.debi_txt);
    }
}
