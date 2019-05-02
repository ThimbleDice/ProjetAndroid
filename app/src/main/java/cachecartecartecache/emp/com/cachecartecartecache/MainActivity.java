package cachecartecartecache.emp.com.cachecartecartecache;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListener();
    }

    private void setListener(){
        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToNewGame();
            }
        });
        findViewById(R.id.btn_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToCustom();
            }
        });
    }

    private void sendToNewGame(){
        Intent sendToNewGame = new Intent(this, GameActivity.class);
        startActivity(sendToNewGame);
    }

    private void sendToCustom(){
        Intent sendToCustomTiles = new Intent(this, CustomTilesActivity.class);
        startActivity(sendToCustomTiles);
    }
}
