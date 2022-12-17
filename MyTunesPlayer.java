package com.example.demo3;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.example.demo3.Playlist;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

class MyTunesController implements Initializable {

    @FXML
    private TableView<Playlist> tablePlaylists;
    @FXML
    private TableView<Song> tableSongs;
    @FXML
    private Label lblIsPlaying;
    @FXML
    private TextField txtSearch;
    @FXML
    private Slider sliderVolume;
    @FXML
    private TableColumn<Playlist, String> clmPlaylistName;
    @FXML
    private TableColumn<Playlist, String> clmPlaylistSongsAmount;
    @FXML
    private TableColumn<Playlist, String> clmPlaylistTotalDuration;
    @FXML
    private TableColumn<Song, String> clmSongTitle;
    @FXML
    private TableColumn<Song, String> clmSongArtist;
    @FXML
    private TableColumn<Song, String> clmSongDuration;
    @FXML
    private TableView<Song> tableCurrentPlaylist;
    @FXML
    private TableColumn<Song, String> clmCurrentPlaylistTrack;
    @FXML
    private TableColumn<Song, String> clmCurrentPlaylistTitle;
    @FXML
    private TableColumn<Song, String> clmSongGenre;
    @FXML
    private ImageView btnPlay;
    @FXML
    private ImageView speaker;
    @FXML
    private Label lblTotalSongs;
    @FXML
    private Label lblTotalDuration;
    @FXML
    private ProgressBar sliderMusic;

    private final Image play = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/mytunes/assets/icons/playy.png")));
    private final Image pause = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/mytunes/assets/icons/paused.png")));
    private final Image normal = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/mytunes/assets/icons/speakers.png")));

    private final Image mute = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/mytunes/assets/icons/mute.png")));

    private Song selectedSong;

    private Stage primStage;

    private double lastVolume;

    private SongModel songModel;
    private MathManager mathManager;

    private static final String IDLE_TEXT = "Enjoy your music!";
    private static final String IS_PLAYING = " is playing";
    private static final String IS_PAUSED = " is paused";

    private TableView.TableViewSelectionModel<Song> selectedView;
    private TableView.TableViewSelectionModel<Song> playingView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        songModel = SongModel.getInstance();
        songModel.setMyTunesController(this);


        btnPlay.setImage(play);

        speaker.setImage(normal);

        initializeTables();


        lblIsPlaying.setText(IDLE_TEXT);

        selectedView = tableSongs.getSelectionModel();
        playingView = tableSongs.getSelectionModel();


        addChangeListenersToTables();
    }


    private void addChangeListenersToTables() {
        //Adds a listener to tableSongs.
        tableSongs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            private ObservableValue<? extends Song> observableValue;
            private Song song;
            private Song t1;


            public void changed(ObservableValue<? extends Song> observableValue, Song song, Song t1) {

                this.observableValue = observableValue;
                this.song = song;
                this.t1 = t1;
            }


        });

        tableCurrentPlaylist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            @Override
            public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
                if (newValue != null) {
                    tableSongs.getSelectionModel().clearSelection();
                    selectedView = tableCurrentPlaylist.getSelectionModel();
                }
            }
        });
    }


    private void initializeTables() {

        clmSongTitle.setCellValueFactory(i -> i.getValue().getTitle());
        clmSongArtist.setCellValueFactory(i -> i.getValue().getArtist());
        clmSongGenre.setCellValueFactory(i -> i.getValue().getGenre());
        clmSongDuration.setCellValueFactory(i -> i.getValue().getDuration());
        songModel.loadSavedSongs();
        tableSongs.setItems(songModel.getSongs());
        updateTotals();


        clmCurrentPlaylistTrack.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tableCurrentPlaylist.getItems().indexOf(column.getValue())).asString());
        clmCurrentPlaylistTitle.setCellValueFactory(i -> i.getValue().getTitle());
        tableCurrentPlaylist.setItems(songModel.getCurrentPlaylist());


        clmPlaylistName.setCellValueFactory(i -> i.getValue().getName());
        clmPlaylistSongsAmount.setCellValueFactory(i -> i.getValue().getSongs());
        clmPlaylistTotalDuration.setCellValueFactory(i -> i.getValue().getDuration());
        songModel.loadSavedPlaylists();
        tablePlaylists.setItems(songModel.getPlaylists());
    }


    @FXML
    private void SongTableButton(MouseEvent event) throws IOException {

        ImageView selectedImage = (ImageView) event.getSource();


        primStage = (Stage) txtSearch.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/NewEditSongView.fxml"));
        Parent root = loader.load();

        Stage editStage = new Stage();
        editStage.setScene(new Scene(root));


        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.initOwner(primStage);


        if (selectedImage.getId().equals("add")) {
            editStage.show();

        } else if (selectedImage.getId().equals("editSong")) {
            Song songToEdit = tableSongs.getSelectionModel().getSelectedItem();
            if (songToEdit != null) {
                assignSongValuesToModal(loader, songToEdit);
                editStage.show();
            }
        }
    }

    private void assignSongValuesToModal(FXMLLoader loader, Song songToEdit) {
    }


    @FXML
    private void pressingThePlayButton() {
        selectedSong = selectedView.getSelectedItem();

        if (btnPlay.getImage() == play) {
            if (selectedSong != null) {
                playSong(selectedSong);
            }

        } else {
            songModel.pausePlaying();
            btnPlay.setImage(play);
            lblIsPlaying.setText(songModel.getCurrentSongPlaying().getTitle() + IS_PAUSED);
        }

    }



    private void DoubleClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            songModel.stopPlaying();

            selectedSong = selectedView.getSelectedItem();
            playSong(selectedSong);
        }
    }


    private void playSong(Song selectedSong) {
        songModel.playSelectedSong(selectedSong);
        playingView = selectedView;
        btnPlay.setImage(pause);
        lblIsPlaying.setText(selectedSong.getTitle() + IS_PLAYING);
    }


    @FXML
    private void StopButton() {
        songModel.stopPlaying();
        btnPlay.setImage(play);
        lblIsPlaying.setText(IDLE_TEXT);
    }


    @FXML
    private void Search() {
        String search = txtSearch.getText();
        if (!search.equals("")) {
            songModel.searchSong(search);
        }
    }


    @FXML
    private void ClearSearch() {
        songModel.clearSearch();
        txtSearch.setText("");
    }

    public void playNextSong() {
        Song nextSong = selectNextSong(true); //sends true value to skip forward
        playSong(nextSong);
    }


    private Song selectNextSong(boolean goForward) {
        songModel.stopPlaying();
        Stage currentSong = songModel.getCurrentSongPlaying();
        playingView.select();
        if (goForward) {
            playingView.selectNext();
        } else {
            playingView.selectPrevious();
        }
        return selectedView.getSelectedItem();
    }


    @FXML
    private void SkipForwardButton() {
        Song nextSong = selectNextSong(true);
        playSong(nextSong);
    }


    @FXML
    private void SkipBackwardButton() {
        Song nextSong = selectNextSong(false); //sends false value to go to previous
        playSong(nextSong);
    }


    @FXML
    private void actionSongDeleteButton(MouseEvent event) {
        try {
            ObservableList<Song> songsSelected = tableSongs.getSelectionModel().getSelectedItems();
            ArrayList<Song> songsToDelete = new ArrayList<>(songsSelected);

            Alert alert = songRemoveDialog(songsToDelete.get(0));

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                songModel.deleteSongs(songsToDelete);
                updateTotals();
            }
        } catch (NullPointerException npe) {
            System.out.println("Wrong delete button");
        }

    }

    private Alert songRemoveDialog(Song songToRemove) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Making Sure");
        alert.setContentText("Hit OK to Remove");
        alert.setHeaderText("Are you certain you want to remove the song");
        return alert;
    }

    private void updateTotals() {
        lblTotalSongs.setText(songModel.getSongs().size()+"");

    }


    @FXML
    private void actionReplay() {
        songModel.replaySong();
    }


    @FXML
    private void actionSongToPlaylist(MouseEvent event) {
        Song songToAdd = tableSongs.getSelectionModel().getSelectedItem();
        songModel.addSongToPlaylist(songToAdd);
    }


    @FXML
    private void actionRemoveSongFromPlaylistButton(MouseEvent event) {
        try {
            ObservableList<Song> selectedSongs = tableCurrentPlaylist.getSelectionModel().getSelectedItems();
            ArrayList<Song> songsToDelete = new ArrayList<>(selectedSongs);

            Alert alert = songRemoveDialog(songsToDelete.get(0));

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                songModel.removeSongsFromCurrentPlaylist(songsToDelete);
            }
        } catch (NullPointerException npe) {
            System.out.println("Wrong delete button");
        }
    }


    private Alert songRemovalPopup(Song songToRemove) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Making Sure");
        alert.setHeaderText("Are you certain you wanna do this?: " + "\n\n" + songToRemove.getTitle());
        alert.setContentText("Press Okay To Remove");
        return alert;
    }


    private Alert playlistRemoveDialog(Playlist playlistToRemove) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Making Sure");
        alert.setHeaderText("Are you certain you want to remove the playlist: " + "\n\n" + playlistToRemove.getName());
        alert.setContentText("Press Okay To Remove");
        return alert;
    }


    @FXML
    private void actionPlaylistTableButton(MouseEvent event) throws IOException {

        ImageView selectedImage = (ImageView) event.getSource();


        primStage = (Stage) txtSearch.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/NewEditPlaylistView.fxml"));
        Parent root = loader.load();

        Stage editStage = new Stage();
        editStage.setScene(new Scene(root));


        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.initOwner(primStage);


        if (selectedImage.getId().equals("createPlaylist")) {
            editStage.show();

        } else if (selectedImage.getId().equals("editPlaylist")) {
            Playlist editPlaylist = tablePlaylists.getSelectionModel().getSelectedItem();
            if (editPlaylist != null) {
                assignPlaylistValuesToModal(loader, editPlaylist);
                editStage.show();
            }
        }
    }

    private void assignPlaylistValuesToModal(FXMLLoader loader, Playlist editPlaylist) {
        NewEditPlaylistController playlistController = loader.getController();
        playlistController.setCurrentPlaylist(editPlaylist);
    }

    @FXML
    private void actionRemovePlaylist(MouseEvent event) {
        ObservableList<Playlist> selectedPlaylists = tablePlaylists.getSelectionModel().getSelectedItems();
        ArrayList<Playlist> playlistsToDelete = new ArrayList<>(selectedPlaylists);

        Alert alert = playlistRemoveDialog(selectedPlaylists.get(0));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            songModel.deletePlaylist(playlistsToDelete);
        }
    }


    @FXML
    private void actionMoveSongUpButton(MouseEvent event) {
        int selectedIndex = tableCurrentPlaylist.getSelectionModel().getSelectedIndex();
        ArrayList<Song> currentPlaylist = songModel.getCurrentPlaylistAsArrayList();
        if (selectedIndex - 1 >= 0) {
            Collections.swap(currentPlaylist, selectedIndex, selectedIndex - 1);
            songModel.updateCurrentPlaylist(currentPlaylist);
            tableCurrentPlaylist.getSelectionModel().select(selectedIndex - 1);
        }
    }


    @FXML
    private void actionMoveSongDownButton(MouseEvent event) {
        int selectedIndex = tableCurrentPlaylist.getSelectionModel().getSelectedIndex();
        ArrayList<Song> currentPlaylist = songModel.getCurrentPlaylistAsArrayList();
        if (selectedIndex + 1 < currentPlaylist.size() && selectedView == tableCurrentPlaylist.getSelectionModel()) {
            Collections.swap(currentPlaylist, selectedIndex, selectedIndex + 1);
            songModel.updateCurrentPlaylist(currentPlaylist);
            tableCurrentPlaylist.getSelectionModel().select(selectedIndex + 1);
        }
    }


    @FXML
    private void actionMusicVolume() {
        sliderVolume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (sliderVolume.isValueChanging()) {
                    songModel.switchVolume(sliderVolume.getValue() / 100.0);
                } else if (sliderVolume.isPressed()) {
                    songModel.switchVolume(sliderVolume.getValue() / 100.0);
                }
            }

        });
    }


    @FXML
    private void actionMute(MouseEvent event) {
        if (speaker.getImage().equals(normal)) {
            lastVolume = sliderVolume.getValue();
            songModel.switchVolume(0);
            sliderVolume.setValue(0);
            speaker.setImage(mute);
        } else {
            songModel.switchVolume(lastVolume);
            sliderVolume.setValue(lastVolume);
            speaker.setImage(normal);
        }
    }


    private void actionShuffle(MouseEvent event) {
        songModel.shuffleCurrentPlaylist();
    }


    @FXML
    private void selectPlaylist(MouseEvent event) throws NullPointerException {
        try {
            int playlistId = tablePlaylists.getSelectionModel().getSelectedItem().getId();
            songModel.setPlaylistID(playlistId);
            ArrayList<Song> list = tablePlaylists.getSelectionModel().getSelectedItem().getSongsInPlaylist();
            songModel.updateCurrentPlaylist(list);
        } catch (Exception e) {
            System.out.println("Selection error " + e);
        }
    }


    private void MultiSelect(KeyEvent event) {
        if (event.isControlDown() | event.isShiftDown()) {
            selectedView.setSelectionMode(SelectionMode.MULTIPLE);
            tablePlaylists.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } else {
            selectedView.setSelectionMode(SelectionMode.SINGLE);
            tablePlaylists.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        }
    }


}