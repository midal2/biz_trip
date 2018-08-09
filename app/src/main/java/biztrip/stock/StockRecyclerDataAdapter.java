package biztrip.stock;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biztrip.stock.biz.HttpBizService;
import biztrip.stock.biz.StockInfo;
import lombok.Data;

import static biztrip.util.Common.Step;

@Data
public class StockRecyclerDataAdapter extends RecyclerView.Adapter<StockViewHolder> {
    private ArrayList<StockInfo> stockInfos;

    public StockRecyclerDataAdapter(){
        stockInfos = new ArrayList();
    }

    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Step("Context를 가져온다");
        Context context = parent.getContext();

        Step("LayoutInflater를 구성한다");
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        Step("view를 생성");
        View view = layoutInflater.inflate(R.layout.fragment_stock, parent, false);

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
