package cachecartecartecache.emp.com.cachecartecartecache;

import android.graphics.Bitmap;

public final class Card {
    private Bitmap picture;
    private String cardId;

    public Bitmap getPicture(){
        return picture;
    }
    public String getCardId(){
        return cardId;
    }

    public Card(Bitmap picture, String cardId) {
        this.picture = picture;
        this.cardId = cardId;
    }

    public Card(Card card) {
        this.picture = card.picture;
        this.cardId = card.cardId;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + cardId.hashCode();
        result = 31 * result + picture.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) {
            return false;
        }

        Card card = (Card) obj;
        return this.cardId == card.cardId;
    }
}