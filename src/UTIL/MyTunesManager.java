package UTIL;

import BE.Author;
import BE.CategorySong;
import BE.PlayList;
import BE.Song;
import UTIL.MP.MusicPlayer;
import UTIL.exceptions.*;
import DBC.DALMyTunesInterface;
import DBC.IDALMyTunesInterface;

import java.util.List;

public class MyTunesManager  implements MyTunesInterface{
        MusicPlayer musicPlayer;

        IDALMyTunesInterface idalMyTunesInterface;

        public MyTunesManager() throws ManagerExc
        {
            musicPlayer = MusicPlayer.getInstance();
            try
            {
                idalMyTunesInterface = new DALMyTunesInterface();
            }
            catch (Exception e)
            {
                throw new ManagerExc(" Initializing MyTunesManager class failed !", e);
            }
        }

        public Author getAuthor(int id) throws AuthorExc
        {
            try {
                return idalMyTunesInterface.getAuthor(id);
            } catch (Exception e) {
                throw new AuthorExc("Couldn't get the author !", e);
            }
        }
        public List<Author> getALlAuthors() throws AuthorExc
        {
            try {
                return idalMyTunesInterface.getAllAuthors();
            } catch (Exception e) {
                throw new AuthorExc("Couldn't not get all authors !", e);
            }
        }

        public Author createAuthor(String name) throws AuthorExc
        {
            try {
                return idalMyTunesInterface.createAuthor(name);
            } catch (Exception e) {
                throw new AuthorExc("Couldn't not create author !", e);
            }
        }

        public void updateAuthor(Author author) throws AuthorExc
        {
            try {
                idalMyTunesInterface.updateAuthor(author);
            } catch (Exception e) {
                throw new AuthorExc("Couldn't update author !", e);
            }
        }
        public void deleteAuthor(Author author) throws AuthorExc
        {
            try {
                idalMyTunesInterface.updateAuthor(author);
            } catch (Exception e) {
                throw new AuthorExc("Couldn't delete author !", e);
            }
        }

        public CategorySong getCategorySong(int id) throws CategoryExc
        {
            try {
                return idalMyTunesInterface.getCategorySong(id);
            } catch (Exception e) {
                throw new CategoryExc("Couldn't get the song category !", e);
            }
        }

        public List<CategorySong> getALlCategorySong() throws CategoryExc
        {
            try {
                return idalMyTunesInterface.getALlCategorySong();
            } catch (Exception e) {
                throw new CategoryExc("Couldn't get all song categories !", e);
            }
        }

        public CategorySong createCategorySong(CategorySong category) throws CategoryExc
        {
            try {
                return idalMyTunesInterface.createCategorySong(category);
            } catch (Exception e) {
                throw new CategoryExc("Couldn't creat song category !", e);
            }
        }

        public void updateCategorySong(CategorySong category) throws CategoryExc
        {
            try {
                idalMyTunesInterface.updateCategorySong(category);
            } catch (Exception e) {
                throw new CategoryExc("Couldn't update category !", e);
            }
        }

        public void deleteCategorySong(CategorySong category) throws CategoryExc
        {
            try {
                idalMyTunesInterface.deleteCategorySong(category);
            } catch (Exception e) {
                throw new CategoryExc("Couldn't creat category !", e);
            }
        }

        public PlayList getPlayList(int id) throws PlayListExc
        {
            try {
                return idalMyTunesInterface.getPlayList(id);
            } catch (Exception e) {
                throw new PlayListExc("Couldn't play the playlist !", e);
            }
        }

        public List<PlayList> getALlPlayLists() throws PlayListExc
        {
            try {
                return idalMyTunesInterface.getALlPlayLists();
            } catch (Exception e) {
                throw new PlayListExc("Couldn't get all playlists !", e);
            }
        }

        public PlayList createPlayList(PlayList playList) throws PlayListExc
        {
            try {
                return idalMyTunesInterface.createPlayList(playList);
            } catch (Exception e) {
                throw new PlayListExc("Couldn't create the playlist !", e);
            }
        }

        public void updatePlayList(PlayList playList) throws PlayListExc
        {
            try {
                idalMyTunesInterface.updatePlayList(playList);
            } catch (Exception e) {
                throw new PlayListExc("Couldn't update the playlist !", e);
            }
        }

        public void deletePlayList(PlayList playList) throws PlayListExc
        {
            try {
                idalMyTunesInterface.deletePlayList(playList);
            } catch (Exception e) {
                throw new PlayListExc("Couldn't delete playlist!", e);
            }
        }

        public Song getSong(int id) throws SongExc
        {
            try {
                return idalMyTunesInterface.getSong(id);
            } catch (Exception e) {
                throw new SongExc("Unable to get song!", e);
            }
        }

        public List<Song> getALlSongs() throws SongExc
        {
            try {
                return idalMyTunesInterface.getALlSongs();
            } catch (Exception e) {
                throw new SongExc("Unable to get song!", e);
            }
        }

        public Song createSong(Song song) throws SongExc
        {
            try {
                return idalMyTunesInterface.createSong(song);
            } catch (Exception e) {
                throw new SongExc("Unable to create song!", e);
            }
        }

        public void updateSong(Song song) throws SongExc
        {
            try {
                idalMyTunesInterface.updateSong(song);
            } catch (Exception e) {
                throw new SongExc("Unable to update song!", e);
            }
        }

        public void deleteSong(Song song) throws SongExc
        {
            try {
                idalMyTunesInterface.deleteSong(song);
            } catch (Exception e) {
                throw new SongExc("Unable to delete song!", e);
            }
        }

        public void playStopSong() throws MusicPlayerExc {
            musicPlayer.playStopSong();
        }

        public void setCurrentSong(Song song) throws MusicPlayerExc
        {
            musicPlayer.setCurrentSong(song);
        }

        public Song getCurrentSong()
        {
            return musicPlayer.getCurrentSong();
        }

        @Override
        public void setVolume(int soundVolume)
        {
            musicPlayer.setVolume(soundVolume);
        }

        @Override
        public void updatePlayListName(PlayList playList) throws Exception
        {
            idalMyTunesInterface.updatePlayListName(playList);
        }
    }

