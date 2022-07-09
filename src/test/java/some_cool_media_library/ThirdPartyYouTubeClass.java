package some_cool_media_library;

import java.util.HashMap;


//remote service-ის მეთოდების დაკონკრეტებული იმპლემნტაცია, ამ კლასის მეთოდებს შეუძლიათ ინფორმაციის მოთხოვნა youtube-დან, რექვესთის სიჩქარე
//დამოკიდებულია კლიენტისა და youtube-ის ინტერნეტ კავშირზე. აპლიკაცია შენელდება თუ ბევრი რექუესთი არის გაგზავნილი ერთდროულად
//თუნდაც ერთი და იგივე ინფომრაციის მოთხოვნისას.
public class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib {

    //popularVideos(), მეთოდის ოვერრაიდი. უკავშირდება სერვერს და გვიბრუნებს რანდომვიდეოებს.
    @Override
    public HashMap<String, Video> popularVideos() {
        connectToServer("http://www.youtube.com");
        return getRandomVideos();
    }

    //getVideo() მეთოდის ოვერრაიდი, უკავშირდება სერვერს და გვიბრუნებს ვიდეოს(გადაეცემა პარამეტრად ვიდეოს აიდი).
    @Override
    public Video getVideo(String videoId) {
        connectToServer("http://www.youtube.com/" + videoId);
        return getSomeVideo(videoId);
    }

    // -----------------------------------------------------------------------
    // Fake methods to simulate network activity. They as slow as a real life.

    private int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    //ეს მეთოდი ნეთვორქთან კომუნიკაციის შეყოვნების დროს ითვალისწინებს, რათა exception აირიდოს თავიდან.
    private void experienceNetworkLatency() {
        int randomLatency = random(5, 10);
        for (int i = 0; i < randomLatency; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    //ამ მეთოდს გამოაქვს შეტყობინება მეთოდთან დაკავშირების დაწყებისას, ელოდება experienceNetworkLatency()-ში გათვალისიწნებული
    //დროით ნეთვორქს და გამოაქვს შეტყობინება რომ დაკავშირება მოხდა.
    private void connectToServer(String server) {
        System.out.print("Connecting to " + server + "... ");
        experienceNetworkLatency();
        System.out.print("Connected!" + "\n");
    }

    //getRandomVideos მეთოდი უკავშირდება youtube-ს, ითვალისწინებს lag-ს, HashMap-ში ამატებს ვიდეოებს აიდის და თაითლ პარამეტრების
    //მიხედვით და გვიბრუნებს HashMap-ს.
    private HashMap<String, Video> getRandomVideos() {
        System.out.print("Downloading populars... ");

        experienceNetworkLatency();
        HashMap<String, Video> hmap = new HashMap<String, Video>();
        hmap.put("catzzzzzzzzz", new Video("sadgahasgdas", "Catzzzz.avi"));
        hmap.put("mkafksangasj", new Video("mkafksangasj", "Dog play with ball.mp4"));
        hmap.put("dancesvideoo", new Video("asdfas3ffasd", "Dancing video.mpq"));
        hmap.put("dlsdk5jfslaf", new Video("dlsdk5jfslaf", "Barcelona vs RealM.mov"));
        hmap.put("3sdfgsd1j333", new Video("3sdfgsd1j333", "Programing lesson#1.avi"));

        System.out.print("Done!" + "\n");
        return hmap;
    }

    //getSomeVideo მეთოდით, კლიენტი უკავშირდება youtube-ს, ითვალისწინებს lag-ს, იწერს რანდომ ვიდეოს აიდი პარამეტრისა
    //და თაითლის მიხედვით. აბრუნებს რანდომ ვიდეოს.
    private Video getSomeVideo(String videoId) {
        System.out.print("Downloading video... ");

        experienceNetworkLatency();
        Video video = new Video(videoId, "Some video title");

        System.out.print("Done!" + "\n");
        return video;
    }

}