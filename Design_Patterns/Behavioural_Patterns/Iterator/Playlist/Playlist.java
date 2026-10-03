package Design_Patterns.Behavioural_Patterns.Iterator.Playlist;

import java.util.ArrayList;

public class Playlist {
    private ArrayList<String> songs;

    public Playlist() {
        this.songs = new ArrayList<>();
    }

    public void addSongs(String song) {
        songs.add(song);
    }

    public PlaylistIterator iterator(String type) {
        switch(type) {
            case "simple":
                return new SimplePlaylistIterator(this);
            case "shuffled":
                return new ShuffledPlaylistIterator(this);
            case "favourite":
                return new FavouritePlaylistIterator(this);
            default:
                return null;
        }
    }

    public ArrayList<String> getSongs() {
        return this.songs;
    }
}
