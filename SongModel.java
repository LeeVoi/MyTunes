package com.example.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


public class SongModel {

    private static SongModel instance;
    private final ObservableList<Object> songsSaved;
    private final DaoMusic daoMusic = DaoMusic.getInstance();
    //missing connection to data access layer//
    private final FileManager fileManager;
    //missing connection to bll//
    private final PlayerMusic;
    //missing connection to musicplayer in bll//
    private final MathManager;
    //missing connection to math manager in bll//
    private final ObservableList<Song> tunes;
    private final ObservableList<Song> searchedSongs;
    private final ObservableList<Song> songsSaved;
    private final mytunController  mytunController;

    public static SongModel getInstance() {
        if (instance == null){
            instance = new SongModel(tunes);
        }
        return instance;
    }
    private SongModel(ObservableList<Song> tunes){
        this.tunes = tunes;
        mathManager = MathManager.getInstance();
        searchedSongs = FXCollections.observableArrayList();
        fileManager = new FileManager();
        playermusic.setSongModel(this);
        songsSaved = FXCollections.observableArrayList();
        mytunController = getInstance().mytunController;

    }
    public void removeSongsFromCurrentPlaylist(ArrayList<Song> songsToDelete) {
        for (Song song : songsToDelete) {
            currentPlaylist.remove(song);
    }

    public void setMyTunesController(MyTunesController myTunesController) {
            this.mtController = mtController;
    }

    public void loadSavedSongs() {
            if (musicDao.isSongsThere()) {
                ArrayList<Song> songsFromFile = musicDao.getSongsFromFile();
                if (!songsFromFile.isEmpty()) {
                    songs.clear();
                    songs.addAll(songsFromFile);
                }
            } else {
                System.out.println("Song File Not Found");
            }
    }

    private void savePlaylists(){
        ArrayList<Playlist> playlistsSave = new ArrayList<>(playlists);
        musicDao.writePlaylists(playlistsSave);
    }

    public ObservableList<Song> getSongs() {
        return tunes;
    }

    public void stopPlaying() {
            if )playerMusic.isPlaying()){
                playerMusic.stopPlaying();
            }
    }

    public void playSelectedSong(Song selectedSong) {
            if (playerMusic.isPlaying))

    }

    public void searchSong(String searchString) {
            ArrayList<Song> searchedSongs = new ArrayList<>();
            songsSaved.addAll(tunes);
            tunes.clear();
    }

    public void pausePlaying() {
            playerMusic.stopPlaying();
    }

    public Stage getCurrentSongPlaying() {
        return musicPlayer.getCurrentSong();
    }

    public void setPlaylistID(int playlistId) {
            this.playlistID = playlistID;
        }
    }

    public void updateCurrentPlaylist(ArrayList<Song> list) {
        currentPlaylist.clear();
        for (Song song : playlist) {
            currentPlaylist.add(tunes);
        }
    }

    public void shuffleCurrentPlaylist() {
        Collections.shuffle(Playlist);
    }

    public void addSongToPlaylist(Song songToAdd) {
        Playlist.add(tunes);
    }

    public void replaySong() {
        if(playerMusic.isPlaying()){
            playerMusic.replaySong();
        }
    }

    public void deleteSongs(ArrayList<Song> songsToDelete) {{
        tunes.remove(tunes);
    }
    saveSongs();}

    public void clearSearch() {
        if (songsSaved.isEmpty()){
            tunes.clear();
        }
    }

    public void loadSavedPlaylists() {
        if (songsfromfile.isEmpty()){
            tunes.clear();
            tunes.addAll(fileSongs);
        }
    }

    public ObservableList<Playlist> getPlaylists() {
        return Playlist;
    }

    public ObservableList<Song> getCurrentPlaylist() {
        return Playlist;
    }

    public ArrayList<Song> getCurrentPlaylistAsArrayList() {
        ArrayList<Song> playlist = new ArrayList<>();
        for (Song song : currentPlaylist) {
            playlist.add(song);
        }
        return playlist;
    }

    public void deletePlaylist(ArrayList<Playlist> playlistsToDelete) {
        {
            playlists.remove(playlist);
        }
        savePlaylists();
    }

    public void switchVolume(double value) {
        mytunesplayer.setVolume(value);
    }

    public void stopPlaying() {
        if (playerMusic.isPlaying()) {
            playerMusic.stopPlaying();
        }

    }

    public Stage getCurrentSongPlaying() {
        return playerMusic.getCurrentSong();
    }
}
