package lahacks.labs.healthplusplus;

public class User implements Profile {
    private String name;
    private int age;
    private String gender;
    private double height;
    private double weight;

    public User() {
        name = "";
        age = 0;
        gender = "";
        height = weight = 0.0;
    }

    public User(String n, int a, String g, double h, double w) {
        name = n;
        age = a;
        gender = g;
        height = h;
        weight = w;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String n) {
        name = n;
    }

    public void setAge(int a) {
        age = a;
    }

    public void setGender(String g) {
        gender = g;
    }

    public void setHeight(double h) {
        height  = h;
    }

    public void setWeight(double w) {
        weight = w;
    }
}