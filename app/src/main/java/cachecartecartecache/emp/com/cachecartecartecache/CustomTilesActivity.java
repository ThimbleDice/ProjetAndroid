package cachecartecartecache.emp.com.cachecartecartecache;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CustomTilesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tiles);
        setListener();
    }

    private void setListener(){
        findViewById(R.id.btn_back_to_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToMenu();
            }
        });
    }

    private void sendToMenu(){
        Intent intentSendMenu = new Intent(this, MainActivity.class);
        startActivity(intentSendMenu);
    }
}
