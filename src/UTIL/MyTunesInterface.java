package UTIL;


import BE.Author;
import BE.CategorySong;
import BE.PlayList;
import BE.Song;
import UTIL.exceptions.*;

import java.util.List;

public interface MyTunesInterface {
        public Author getAuthor(int id) throws AuthorExc;

        public List<Author> getALlAuthors() throws AuthorExc;

        public Author createAuthor(String name) throws AuthorExc;

        public void updateAuthor(Author author) throws AuthorExc;

        public void deleteAuthor(Author author) throws AuthorExc;

        public CategorySong getCategorySong(int id) throws CategoryExc;

        public List<CategorySong> getALlCategorySong() throws CategoryExc;

        public CategorySong createCategorySong(CategorySong category) throws CategoryExc;

        public void updateCategorySong(CategorySong category) throws CategoryExc;

        public void deleteCategorySong(CategorySong category) throws CategoryExc;

        public PlayList getPlayList(int id) throws PlayListExc;

        public List<PlayList> getALlPlayLists() throws PlayListExc;

        public PlayList createPlayList(PlayList playList) throws PlayListExc;

        public void updatePlayList(PlayList playList) throws PlayListExc;

        public void deletePlayList(PlayList playList) throws PlayListExc;

        public Song getSong(int id) throws SongExc;

        public List<Song> getALlSongs() throws SongExc;

        public Song createSong(Song song) throws SongExc;

        public void updateSong(Song song) throws SongExc;

        public void deleteSong(Song song) throws SongExc;

        public void playStopSong() throws MusicPlayerExc;

        public void setCurrentSong(Song song) throws MusicPlayerExc;
        public Song getCurrentSong();

        public void setVolume(int soundVolume);

        public void updatePlayListName(PlayList playList) throws Exception;

}
