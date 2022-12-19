package DBC.Interface;

import BE.Song;

import java.util.List;

public interface SongAccess {

        Song getSong(int idSong) throws Exception;
        List<Song> getALlSongs() throws Exception;
        Song createSong(Song song) throws Exception;
        void updateSong(Song song) throws Exception;
        void deleteSong(Song song) throws Exception;

    }
