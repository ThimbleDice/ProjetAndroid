package cachecartecartecache.emp.com.cachecartecartecache;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mBoutonUpload = findViewById(R.id.button_upload);
        mBoutonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Upload in progress", Toast.LENGTH_LONG).show();
                openImagesActivity();
            }
        });
    }

    private void openImagesActivity() {
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }

}
