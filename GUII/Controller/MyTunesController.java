package com.example.demo6;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.MyTunes.BE.PlayList;
import java.MyTunes.BE.Song;
import java.MyTunes.DBC.DB.PlayListDA;
import java.MyTunes.DBC.DB.AuthorDA;
import java.MyTunes.DBC.DB.SongDA;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MyTunesController implements Initializable{
    @FXML
    private TableView<PlayList> playlistTable;
    @FXML
    private TableView<Song> songsTable;
    @FXML
    private TableView<Song> songTableView;

    @FXML
    private Pane pane;
    @FXML
    private Label songLabel;
    @FXML
    private Button playButton, pauseButton,resetButton, nextButton;
    @FXML
    private ComboBox<String> volumeSlider;
    @FXML
    private ProgressBar songProgressBar;

    private File directory;
    private File[] files;
    private ArrayList<File> songs;

    private TextField searchField;
    private int songNumber;
    private int[] volume = {10,20,30,40,50,60,70,80,90,100};
    private Timer timer;
    private TimerTask task;
    private boolean running;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private Button addSong;
    @FXML
    private Button editSong;
    @FXML
    private Button editPlaylist;

    @FXML
    private TableColumn<PlayList, String> playlistName;
    @FXML
    private TableColumn<PlayList, String> playListAmount;
    @FXML
    private TableColumn<Song, String> songTitle;
    @FXML
    private TableColumn<Song, String> songArtist;
    @FXML
    private TableColumn<Song, String> songGenre;
    @FXML
    private TableColumn<Song, String> songDuration;


    private TableView.TableViewSelectionModel<Song> viewingSelect;
    private TableView.TableViewSelectionModel<Song> viewingPlaying;

    public MyTunesController(Pane pane, Label songLabel, Button playButton, Button pauseButton, Button resetButton, Button nextButton, ComboBox<String> volumeSlider, ProgressBar songProgressBar, TextField searchField) {
        this.pane = pane;
        this.songLabel = songLabel;
        this.playButton = playButton;
        this.pauseButton = pauseButton;
        this.resetButton = resetButton;
        this.nextButton = nextButton;
        this.volumeSlider = volumeSlider;
        this.songProgressBar = songProgressBar;
        this.searchField = searchField;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewingSelect = songsTable.getSelectionModel();
        viewingPlaying = songsTable.getSelectionModel();
        initTables();
        songs = new ArrayList<File>();
        directory = new File("music");
        files = directory.listFiles();
        if(files != null){
            for (File file : files){
                File File = null;
                songs.add(File);
            }
        }
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName());
        addListenersToTable();
        initTables();
    }

    public void playMusic(){
        startTimer();
        mediaPlayer.play();

    }

    public void pauseMusic(){
        cancelTimer();
        mediaPlayer.pause();
    }

    public void restartMusic() {
        mediaPlayer.seek(Duration.seconds(0.0));
        songProgressBar.setProgress(0);
    }

    public void nextMusic(){
        if(songNumber < songs.size() - 1){
            songNumber++;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            playMusic();

        }
        else {
            songNumber = 0;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            playMusic();

        }

    }
    public void previousMusic(){
        if(songNumber > 0){
            songNumber--;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            playMusic();

        }
        else {
            songNumber = songs.size() - 1;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            playMusic();

        }

    }
    public void volumeMusic(ActionEvent event){
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(Double.parseDouble(volumeSlider.getValue()));
            }
        });

    }
    public void startTimer(){
        timer = new Timer();
        task = new TimerTask() {
            public void run(){
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current/end);

                if (current/end == 1){
                    cancelTimer();
                }

            }
        };
        timer.scheduleAtFixedRate(task,1000,1000);
    }
    public void cancelTimer(){
        running = false;
        timer.cancel();
    }

    private void addListenersToTable(){
        songsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            @Override
            public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
            viewingSelect = songsTable.getSelectionModel();
            }
        });
    }
    private void initTables(){
    songArtist.setCellFactory(Song.getAuthor());
    songTitle.setCellFactory(Song.getName());
    songGenre.setCellFactory(Song.getCategory());
    songDuration.setCellFactory(Song.getIntDuration());
    SongModel.savedSongsLoad();
    songsTable.setItems(SongModel.getSongs());

    playlistName.setCellFactory(PlayList.getName());
    playListAmount.setCellFactory(PlayList.getSizeListString());
    SongModel.savedPlaylistLoad();
    playlistTable.setItems(SongModel.getPlaylists());




    }


}


