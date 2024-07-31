public interface Image {
    void display();
}
public class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromRemoteServer(filename);
    }

    private void loadFromRemoteServer(String filename) {
        // Simulate loading image from remote server
        System.out.println("Loading image from remote server...");
    }

    @Override
    public void display() {
        System.out.println("Displaying image...");
    }
}
public class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}
public class ProxyPattern {
    public static void main(String[] args) {
        Image image = new ProxyImage("image.jpg");
        image.display();
        image.display();
    }
}