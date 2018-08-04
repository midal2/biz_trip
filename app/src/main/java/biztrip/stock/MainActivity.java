package biztrip.stock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import biztrip.stock.biz.Stock;
import biztrip.stock.biz.StockPresenter;

public class MainActivity extends AppCompatActivity {
    private Stock.Present stockPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stockPresent = new StockPresenter();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.stockContainer, StockFragment.newInstance(stockPresent))
                    .commit();
        }
    }
}

