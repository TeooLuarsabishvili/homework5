package some_cool_media_library;

//Video file-ის კლასი , რომელიც შეიცავს ვიდეოს აიდის,დეითას და სახელს.
public class Video {
    public String id;
    public String title;
    public String data;

    //აიდი და დასახელება შეგვიძლია მივანიჭოთ ვიდეოს.
    Video(String id, String title) {
        this.id = id;
        this.title = title;
        this.data = "Random video.";
    }
}
