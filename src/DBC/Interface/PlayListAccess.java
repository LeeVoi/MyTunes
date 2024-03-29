package DBC.Interface;

import BE.PlayList;
import java.util.List;

public interface PlayListAccess {
        /**
         * Returns a Playlist with the specified ID.
         * See Also (PlayList Object to know what is returned)
         * */
        PlayList getPlayList(int idPlayList) throws Exception;

        public void updatePlayListName(PlayList playList) throws Exception;

        List<PlayList> getALlPlayLists() throws Exception;

        PlayList createPlayList(PlayList playList) throws Exception;

        void updatePlayList(PlayList playList) throws Exception;

        void deletePlayList(PlayList playList) throws Exception;
    }
