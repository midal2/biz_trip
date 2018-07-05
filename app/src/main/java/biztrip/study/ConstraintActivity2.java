package biztrip.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ConstraintActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint2);
    }

    public void onClickConArkBtn1(View view) {
        Toast.makeText(this, "toast", Toast.LENGTH_LONG).show();
    }

    public void onClickConArkBtn2(View view){
        Toast.makeText(this, "snack bar", Toast.LENGTH_LONG).show();
    }
}
