package cachecartecartecache.emp.com.cachecartecartecache;

import android.media.MediaPlayer;

public class Music {
    MediaPlayer mediaPlayer;

    public Music(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void toggle(){
        if(this.mediaPlayer.isPlaying()){
            this.pause();
        }else{
            this.start();
        }
    }

    public void start(){
        this.mediaPlayer.start();
    }

    public void pause(){
        this.mediaPlayer.pause();
    }

    public void stop(){
        this.mediaPlayer.stop();
    }

    public void seekToPercent(int percent){
        this.mediaPlayer.seekTo(percent * this.mediaPlayer.getDuration() / 100);
    }

    public boolean isPlaying(){
        return this.mediaPlayer.isPlaying();
    }

    public int getPercent(){
        return  this.getCurrentPosition() * 100 / this.getDuration();
    }

    public int getCurrentPosition(){
        return this.mediaPlayer.getCurrentPosition();
    }

    public int getDuration(){
        return this.mediaPlayer.getDuration();
    }

    public void changeVolume(int percentVolume){
        int maxVolume = 100;
        float volume = (float) (1 - (Math.log(maxVolume - percentVolume) / Math.log(maxVolume)));
        this.mediaPlayer.setVolume(volume, volume);
    }
}