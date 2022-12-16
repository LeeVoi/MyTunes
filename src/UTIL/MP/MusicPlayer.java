package UTIL.MP;

import BE.Song;
import UTIL.exceptions.MusicPlayerExc;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
public class MusicPlayer {
    private final static float MAX_VOLUME = 200;
    private static MusicPlayer single_instance = null;
    private MediaPlayer player = null;
    private Song currentSong;
    private float volume;

    public static MusicPlayer getInstance()
    {
        if (single_instance == null)
            single_instance = new MusicPlayer();

        return single_instance;
    }

    public MusicPlayer()

    {
        volume = MAX_VOLUME/2;
    }

    public MediaPlayer getPlayer()
    {
        return player;
    }
    public void setCurrentSong(Song song) throws MusicPlayerExc
    {
        if (player != null && player.getStatus() == MediaPlayer.Status.PLAYING) {
            player.stop();
        }
        try {
            this.currentSong = song;
            final Media media = new Media(toValidPath(currentSong.getStringSongFile()));
            this.player = new MediaPlayer(media);
        }
        catch (MediaException e)
        {
            throw new MusicPlayerExc("Cannot find the song file" + "or the file is corrupted!", e);
        }
    }
    public void playStopSong()
    {
        if (currentSong == null)
        {
        return;
        }
        if (player != null && player.getStatus() == MediaPlayer.Status.PLAYING)
        {
            player.pause();
            return;
        }
        if (player != null)
        {
            player.play();
        }
    }
    private String toValidPath(String path)
    {

        return path.replace("\\","/").replace(" ", "%20");
    }
    public void setVolume(int soundVolume)
    {
        volume = (float) (1 - (Math.log(MAX_VOLUME - soundVolume) / Math.log(MAX_VOLUME)));
        player.setVolume(volume);
    }
    public Song getCurrentSong()
    {
        return currentSong;
    }
}