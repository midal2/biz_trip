package biztrip.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * phr phl아님 phr임 스텔링 주의 바람!!
     * @param view
     */
    public void onClickPhlBtn(View view) {
        Intent intent = new Intent(this, ConstrainActivity.class);
        startActivity(intent);

    }

    public void onClickArkBtn(View view){
        Intent intent = new Intent(this, ConstraintActivity2.class);
        startActivity(intent);
    }
}
