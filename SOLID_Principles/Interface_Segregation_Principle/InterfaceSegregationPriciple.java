package SOLID_Principles.Interface_Segregation_Principle;

interface mediaPlayer {
    void playViedeo();
    void pauseVideo();
    void playAudio();
    void pauseAudio();
    void adjustSound();
    void adjustBrightness();
}

// If we create a class and implenent mediaPlayer then that class would need to implement all the methods
// If we create a mp3 class and implement mediaPlayer then we would have to define video play and pause and brightness functions too but for mp3 player it is not a general usecase

// After implying LSP

interface audioPlayer {
    void playAudio();
    void pauseAudio();
    void adjustSound();
}

interface videoPlayer {
    void playVideo();
    void pauseVideo();
    void adjustBrightness();
}

// MP3Player only implements audioPlayer interface, hence it would not need to implement video related functions
class MP3Player implements audioPlayer {
    @Override
    public void playAudio() {}

    @Override
    public void pauseAudio() {}

    @Override
    public void adjustSound() {}
}

class AviVideoPlayer implements videoPlayer {
    @Override
    public void playVideo() {}

    @Override
    public void pauseVideo() {}

    @Override
    public void adjustBrightness() {}
}

// MP3Player implements both audioPlayer and videoPlayer interfaces, hence it would need to implement both audio and video related functions
class MultiMediaPlayer implements videoPlayer, audioPlayer {
    @Override
    public void playAudio() {}

    @Override
    public void pauseAudio() {}

    @Override
    public void adjustSound() {}

    @Override
    public void playVideo() {}

    @Override
    public void pauseVideo() {}

    @Override
    public void adjustBrightness() {}
}

public class InterfaceSegregationPriciple {
    public static void main(String[] args) {
        
    }   
}
