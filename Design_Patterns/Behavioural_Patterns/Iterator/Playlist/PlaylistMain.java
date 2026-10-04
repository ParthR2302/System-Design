package Design_Patterns.Behavioural_Patterns.Iterator.Playlist;

import java.util.*;

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
        PlaylistIterator favouritePlaylistIterator = new FavouritePlaylistIterator(playlist);
        while(favouritePlaylistIterator.hasNext()) {
            System.out.println("Playing song: " + favouritePlaylistIterator.next());
        }

        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Banana");

        Iterator<String> iterator = fruits.iterator();

        // In Java's Iterator from utils, when fetching the iterator, initially the iterator sits before the first element
        // The hasNext() method check if there exists any next object
        // the next() method returns the next object of where the iterator is currently at, and then moves the iterator to its next object
        System.out.println("\nFruits List Traversal using Iterator from Java Util class");
        while(iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
