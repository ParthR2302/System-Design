package Design_Patterns.Behavioural_Patterns.Iterator.Playlist;

// import java.util.NoSuchElementException;

public class SimplePlaylistIterator implements PlaylistIterator {

    private Playlist playlist;
    private int index;
    
    public SimplePlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < playlist.getSongs().size();
    }

    @Override
    public String next() {
        // if(!hasNext()) throw new NoSuchElementException();
        return playlist.getSongs().get(index++);
    }
    
}
