package Design_Patterns.Behavioural_Patterns.Iterator.Playlist;

import java.util.ArrayList;
import java.util.Collections;
// import java.util.NoSuchElementException;

public class ShuffledPlaylistIterator implements PlaylistIterator {

    private Playlist playlist;
    private ArrayList<String> ShuffledPlaylist;
    private int index;

    public ShuffledPlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
        this.ShuffledPlaylist = new ArrayList<>(this.playlist.getSongs());
        Collections.shuffle(ShuffledPlaylist);
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < ShuffledPlaylist.size();
    }

    @Override
    public String next() {
        // if(!hasNext()) throw new NoSuchElementException();
        return ShuffledPlaylist.get(index++);
    }
}
