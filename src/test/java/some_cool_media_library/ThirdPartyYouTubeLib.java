package some_cool_media_library;


import java.util.HashMap;

//Remote service-ის ინტერფეისი
public interface ThirdPartyYouTubeLib {
    HashMap<String, Video> popularVideos();

    Video getVideo(String videoId);
}
