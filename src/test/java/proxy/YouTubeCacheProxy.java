package proxy;

import some_cool_media_library.ThirdPartyYouTubeClass;
import some_cool_media_library.ThirdPartyYouTubeLib;
import some_cool_media_library.Video;

import java.util.HashMap;

//Caching proxy, ზოგავს გამტანუნარიანობას. შეგვიძლია რექუესთ რესფონი დავქეშოთ და გარკვეული დროით შევინახოთ
// პროქსის კლასი იგივე ინტერფეისის იმპლემენტაციაა რომლისაც სერვის კლასი. ეს კლასი  სერვისის ობიექტს მიმართვს
//მხოლოდ მაშინ როდესაც ნამდვილი რექუესთია გასაგზავნი.
public class YouTubeCacheProxy implements ThirdPartyYouTubeLib {
    private ThirdPartyYouTubeLib youtubeService;
    private HashMap<String, Video> cachePopular = new HashMap<String, Video>();
    private HashMap<String, Video> cacheAll = new HashMap<String, Video>();

    //ეს კლასი რემოუთ სერვისის ინტერფეისის ინსტანსს ქმნის.
    public YouTubeCacheProxy() {
        this.youtubeService = new ThirdPartyYouTubeClass();
    }
    //ოვერრაიდს უკეთებს  ThirdPartyYouTubeLib მეთოდს. მხოლოდ იმ შემთხვევაში  ამატებს გადმოსაწერ ვიეოებს თუ HashMap ცარიელია
    // სხვა შემთხვევაში აბრუნებს ქეშებში უკვე არსებულ HashMap-ს და რეალურ რექუესთს არ გზავნის,რითიც ზოგავს დროს.
    @Override
    public HashMap<String, Video> popularVideos() {
        if (cachePopular.isEmpty()) {
            cachePopular = youtubeService.popularVideos();
        } else {
            System.out.println("Retrieved list from cache.");
        }
        return cachePopular;
    }

    //ოვერრაიდს უკეთებს  ThirdPartyYouTubeLib მეთოდს. ამ მეთოდშიც ვიდეოს ძებნის რექუესთი იგზავნება მხოლოდ მაშინ თუ ასეთი
    //ვიდეო არ არის ქეშებში. სხვა შემთხვევაში ქეშებში უკვე არსებული ვიდეო ბრუნდება HashMap-იდან და რეალურ რექუესთს არ გზავნის,რითიც ზოგავს დროს.
    @Override
    public Video getVideo(String videoId) {
        Video video = cacheAll.get(videoId);
        if (video == null) {
            video = youtubeService.getVideo(videoId);
            cacheAll.put(videoId, video);
        } else {
            System.out.println("Retrieved video '" + videoId + "' from cache.");
        }
        return video;
    }

    //ასუფთავებს ვიდეოებს HashMap-ებიდან.
    public void reset() {
        cachePopular.clear();
        cacheAll.clear();
    }
}