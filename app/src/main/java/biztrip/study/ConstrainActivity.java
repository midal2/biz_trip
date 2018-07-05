package biztrip.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ConstrainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constrain);

    }

    public void onClickConPhrBtn(View view) {
        Toast.makeText(this, "toast", Toast.LENGTH_LONG).show();

    }

    public void onClickConPhrBtn2(View view) {
        Toast.makeText(this, "snackbar", Toast.LENGTH_LONG).show();
    }
}
