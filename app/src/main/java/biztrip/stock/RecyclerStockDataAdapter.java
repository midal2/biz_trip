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
            stockInfo.setUpdateDt(new Date());

            stockInfos.add(stockInfo);
        }
        return  stockInfos;
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

    public StockViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.stock_title_txt);
        txtUpdateDt = itemView.findViewById(R.id.update_dt_txt);
        txtNowPrice = itemView.findViewById(R.id.now_price_txt);
    }
}
