package Design_Patterns.Behavioural_Patterns.Iterator.Playlist;

// import java.util.NoSuchElementException;

public class FavouritePlaylistIterator implements PlaylistIterator {

    Playlist playlist;
    int index;

    public FavouritePlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        while(index < playlist.getSongs().size()) {
            if(playlist.getSongs().get(index).contains("Fav")) return true;
            index++;
        }
        return false;
    }

    @Override
    public String next() {
        // if(!hasNext()) throw new NoSuchElementException();

        return playlist.getSongs().get(index++);
    }
    
}
