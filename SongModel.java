package com.example.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.ArrayList;


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
        mytunController = HelloController.getInstance();

    }
    public void removeSongsFromCurrentPlaylist(ArrayList<Song> songsToDelete) {
    }

    public void setMyTunesController(MyTunesController myTunesController) {
    }

    public void loadSavedSongs() {
    }

    private void savePlaylists(){
        ArrayList<Playlist> playlistsSave = new ArrayList<>(playlists);
        musicDao.writePlaylists(playlistsSave);
    }

    public ObservableList<Song> getSongs() {
        return tunes;
    }

    public void stopPlaying() {
    }

    public void playSelectedSong(Song selectedSong) {
    }

    public void searchSong(String search) {
    }

    public void pausePlaying() {
    }

    public Stage getCurrentSongPlaying() {
        return null;
    }

    public void setPlaylistID(int playlistId) {
    }

    public void updateCurrentPlaylist(ArrayList<Song> list) {
    }

    public void shuffleCurrentPlaylist() {
    }

    public void addSongToPlaylist(Song songToAdd) {
    }

    public void replaySong() {
    }

    public void deleteSongs(ArrayList<Song> songsToDelete) {
    }

    public void clearSearch() {
    }

    public void loadSavedPlaylists() {
    }

    public ObservableList<Playlist> getPlaylists() {
    }

    public ObservableList<Song> getCurrentPlaylist() {
    }

    public ArrayList<Song> getCurrentPlaylistAsArrayList() {
    }

    public void deletePlaylist(ArrayList<Playlist> playlistsToDelete) {
    }

    public void switchVolume(double value) {
        mytunesplayer.setVolume(value);
    }
}
