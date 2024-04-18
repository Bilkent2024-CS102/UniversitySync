package location.cafeteria;

public class MenuItem {
    private static int numberOfInstances;

    private int id;
    private String name;
    private double price;
    private Image image;

    /*
    * Getters
    */
    public static int getNumberOfInstances()
    {
        return numberOfInstances;
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public double getPrice()
    {
        return price;
    }
    public Image getImage()
    {
        return image;
    }

    /*
    * Setters
    * TODO validation
    */
    public void setId(int id)
    {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public void setImage(Image image)
    {
        this.image = image;
    }
}
