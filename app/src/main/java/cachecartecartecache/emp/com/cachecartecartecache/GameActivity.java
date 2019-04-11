package cachecartecartecache.emp.com.cachecartecartecache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    List<Card> cards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        cards.add(new Card(null, "123"));
        cards.add(new Card(null, "456"));
        cards.add(new Card(null, "789"));
        cards.add(new Card(null, "123"));

        if(cards.get(0).equals(cards.get(3))){
            Toast.makeText(this, "Equal", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Not Equal", Toast.LENGTH_LONG).show();
        }
        displayCards();
    }

    private void displayCards(){
        for (int i=0; i<cards.size(); i++) {
            Toast.makeText(this, cards.get(i).getCardId(), Toast.LENGTH_LONG).show();
        }
    }

}
