package youngjung.test.Model;

public class Category {
    String name;
    int number;
    int imageId;

    public Category(String name, int number, int imageId) {
        this.name = name;
        this.number = number;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
