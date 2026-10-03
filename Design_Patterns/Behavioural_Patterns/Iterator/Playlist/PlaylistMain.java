package Design_Patterns.Behavioural_Patterns.Iterator.Playlist;

public class PlaylistMain {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        playlist.addSongs("Song-1");
        playlist.addSongs("Song-2");
        playlist.addSongs("Fav-Song-1");
        playlist.addSongs("Song-3");
        playlist.addSongs("Fav-Song-2");
        playlist.addSongs("Song-4");
        playlist.addSongs("Fav-Song-3");

        // Simple Playlist Iterator
        System.out.println("Simple Playlist Iterator");
        PlaylistIterator simplePlaylistIterator = playlist.iterator("simple");
        while(simplePlaylistIterator.hasNext()) {
            System.out.println("Playing song: " + simplePlaylistIterator.next());
        }

        // Shuffled Playlist Iterator
        System.out.println("\nShuffled Playlist Iterator");
        PlaylistIterator shuffledPlaylistIterator = playlist.iterator("shuffled");
        while(shuffledPlaylistIterator.hasNext()) {
            System.out.println("Playing song: " + shuffledPlaylistIterator.next());
        }

        // Favourite Playlist Iterator
        System.out.println("\nFavourite Playlist Iterator");
        PlaylistIterator favouritePlaylistIterator = playlist.iterator("favourite");
        while(favouritePlaylistIterator.hasNext()) {
            System.out.println("Playing song: " + favouritePlaylistIterator.next());
        }
    }
}
