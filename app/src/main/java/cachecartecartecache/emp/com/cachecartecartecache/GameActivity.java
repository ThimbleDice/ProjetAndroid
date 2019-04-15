package cachecartecartecache.emp.com.cachecartecartecache;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    List<Card> cards = new ArrayList<>();
    List<Integer> cardsToCompare = new ArrayList<>();
    List<Integer> cardsFound = new ArrayList<>();
    String[] imageUrls = new String[8];
    Boolean wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        wait=false;
        imageUrls[0] = "https://www.ssbwiki.com/images/1/11/SSBUSmashBall.png";
        imageUrls[1] = "https://banner2.kisspng.com/20180723/oor/kisspng-magic-the-gathering-duels-of-the-planeswalker-magic-the-gathering-logo-5b55c4287f14b0.3599000515323474325205.jpg";
        imageUrls[2] = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Firefox_Logo%2C_2017.svg/1200px-Firefox_Logo%2C_2017.svg.png";
        imageUrls[3] = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Google_Chrome_icon_%28September_2014%29.svg/1024px-Google_Chrome_icon_%28September_2014%29.svg.png";
        imageUrls[4] = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Android_robot.svg/872px-Android_robot.svg.png";
        imageUrls[5] = "https://upload.wikimedia.org/wikipedia/fr/d/d1/Spotify_logo_sans_texte.svg.png";
        imageUrls[6] = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/Tux.png/220px-Tux.png";
        imageUrls[7] = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Wikipedia-logo-v2.svg/1200px-Wikipedia-logo-v2.svg.png";
        doubleUrl();
        randomizeUrlArray();
        downloadImages();
        setListener();

        //downloadImage("https://www.ssbwiki.com/images/1/11/SSBUSmashBall.png");
        //displayCards();
    }

    private void doubleUrl(){
        Integer imageUrlsLength = imageUrls.length;
        String[] newImageUrls = new String[2 * imageUrlsLength];
        for (int i=0; i<imageUrlsLength; i++) {
            newImageUrls[i]=imageUrls[i];
            newImageUrls[imageUrlsLength+i]=imageUrls[i];
        }
        imageUrls = newImageUrls;
    }

    private void randomizeUrlArray(){
        Random rand = new Random();
        int max = imageUrls.length;
        List<Integer> ids = new ArrayList<>();
        for (int i=0; i<max; i++) {
            ids.add(i);
        }
        String[] newImageUrls = new String[imageUrls.length];
        for (int i=0; i<max; i++) {
            int n = rand.nextInt(max-i);
            newImageUrls[i] = imageUrls[ids.get(n)];
            ids.remove(n);
        }
        imageUrls = newImageUrls;
    }

    private void setListener(){
        setImageViewListener();
    }

    private void setImageViewListener(){
        for (int i=0; i<16; i++) {
            String imageViewId = "image_card"+i;
            int resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
            final ImageView imageView = findViewById(resID);
            imageView.setTag(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer tag = (Integer) imageView.getTag();
                    if (!wait && !cardsFound.contains(tag)){
                        turnCardFaceUp(imageView);
                        if(cardsToCompare.size() == 1){
                            if(cardsToCompare.get(0) != tag){
                                cardsToCompare.add(tag);
                            }
                        }else{
                            cardsToCompare.add(tag);
                        }
                        if(cardsToCompare.size() == 2){
                            wait=true;
                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            compareCard();
                                            cardsToCompare.clear();
                                            wait=false;
                                        }
                                    },
                                    1000);
                        }
                    }
                }
            });
        }
    }

    private void compareCard(){
        if(cards.get(cardsToCompare.get(0)).equals(cards.get(cardsToCompare.get(1)))){
            cardsFound.add(cardsToCompare.get(0));
            cardsFound.add(cardsToCompare.get(1));
            if(cardsFound.size() == cards.size()){
                win();
            }
        }else{
            turnComparedCardFaceDown();
        }
    }

    private void win(){
        Toast.makeText(this, "WIN", Toast.LENGTH_LONG).show();
        sendToMain();
    }

    private void turnComparedCardFaceDown(){
        String imageViewId = "image_card"+cardsToCompare.get(0);
        int resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
        ImageView imageView = findViewById(resID);
        imageView.setImageResource(R.drawable.moon);

        imageViewId = "image_card"+cardsToCompare.get(1);
        resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
        imageView = findViewById(resID);
        imageView.setImageResource(R.drawable.moon);
    }

    private void turnCardFaceUp(ImageView imageView){
        imageView.setImageBitmap(cards.get((Integer)imageView.getTag()).getPicture());
    }

    private void downloadImages(){
        int max = imageUrls.length;
        for (int i=0; i<max; i++) {
            downloadImage(imageUrls[i], imageUrls[i]);
        }
    }

    private void downloadImage(String imageId ,String imageUrl){
        CardImageDownloader cardImageDownloader = new CardImageDownloader(imageId);
        try{
            cardImageDownloader.execute(imageUrl);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public class CardImageDownloader extends AsyncTask<String, Void, Bitmap> {

        String cardId;

        public CardImageDownloader(String imageId) {
            this.cardId = imageId;
        }

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
            cardImageDownloded(this.cardId, bitmap);
        }
    }

    private void cardImageDownloded(String cardId, Bitmap bitmap){
        cards.add(new Card(bitmap, cardId));
        if(cards.size() == 16){
            displayCards();
        }
    }

    private void displayCards(){
        Integer max = cards.size();
        for (int i=0; i<max; i++) {
            String imageViewId = "image_card"+i;
            int resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
            ImageView imageView = findViewById(resID);
            imageView.setImageResource(R.drawable.moon);
        }
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
    }

    private void sendToMain(){
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
