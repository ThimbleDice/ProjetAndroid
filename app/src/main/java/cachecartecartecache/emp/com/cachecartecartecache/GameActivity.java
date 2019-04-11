package cachecartecartecache.emp.com.cachecartecartecache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
        downloadImage("https://www.ssbwiki.com/images/1/11/SSBUSmashBall.png");
    }

    private void displayCards(){
        ImageView imageView = findViewById(R.id.image_card0);
        imageView.setImageBitmap(cards.get(0).getPicture());
        for (int i=0; i<cards.size(); i++) {
            //Toast.makeText(this, cards.get(i).getCardId(), Toast.LENGTH_LONG).show();
        }
    }

    private void downloadImage(String imageUrl){
        ImageDownloader imageDownloader = new ImageDownloader();
        try{
            imageDownloader.execute(imageUrl);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap imageBitmap = BitmapFactory.decodeStream(inputStream);
                return imageBitmap;
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Bitmap bitmap){
            final ImageView card0 = findViewById(R.id.image_card0);
            card0.setImageBitmap(bitmap);
        }
    }

}
