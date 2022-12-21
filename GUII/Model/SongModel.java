package com.example.demo6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class SongModel {
    private static ObservableList<Song> songs;
    private final ObservableList<Song> songsSaved;
    private final ObservableList<Song> playlistCurrent;
    private static ObservableList<PlayList> playLists;

    public SongModel(ObservableList<Song> songs, ObservableList<Song> songsSaved, ObservableList<Song> playlistCurrent, ObservableList<PlayList> playLists) {
        this.songs = songs;
        this.songsSaved = songsSaved;
        this.playlistCurrent = playlistCurrent;
        this.playLists = playLists;
    }
    private SongModel(){
        songsSaved = FXCollections.observableArrayList();
        playlistCurrent = FXCollections.observableArrayList();
        songs = FXCollections.observableArrayList();
        playLists = FXCollections.observableArrayList();
    }
    public static void savedSongsLoad() {
        ArrayList<Song> filesfromSong = IDALMyTunesInterface.getaLlSongs();
        if (!filesfromSong.isEmpty()){
            songs.addAll(filesfromSong);
        }
    }

    public static void savedPlaylistLoad(){
        ArrayList<PlayList> filesfromPlayList = IDALMyTunesInterface.getaLlPlaylist();
        if (!filesfromPlayList.isEmpty()){
            playLists.addAll(filesfromPlayList);
        }
    }
    public static ObservableList<Song> getSongs() {
        return songs;
    }

    public static ObservableList<PlayList> getPlaylists() {
        return playLists;
    }


    public void songSearch(String stringSearch){
        ArrayList<Song> searchedSongs = new ArrayList<>();
        songsSaved.addAll(songs);
        songs.clear();
        songs.addAll(searchedSongs);
    }
    public ObservableList<Song> getPlaylistCurrent(){
        return playlistCurrent;
    }

    private void songsSave(){
        ArrayList<Song> SavedSongs = new ArrayList<>(songs);
        SongDA.createSong(SavedSongs);
    }


    private void songAdd(Song song){
        songs.add(song);
        songsSave();
    }
}
