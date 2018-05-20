package youngjung.test.Model;

public class Category {
    String name;
    int number;
    Integer[] imageId;

    public Category(String name, int number, Integer[] imageId) {
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

    public Integer[] getImageId() {
        return imageId;
    }

    public void setImageId(Integer[] imageId) {
        this.imageId = imageId;
    }
}
