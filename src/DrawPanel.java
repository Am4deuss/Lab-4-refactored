import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    BufferedImage volvoImage;
    BufferedImage saab95Image;
    BufferedImage scaniaImage;

    HashMap<Car, Point> carAndPoint = new HashMap<>();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(0,300);

    public void addToCarAndPoint(Car vehicle, Point point) {
        carAndPoint.put(vehicle, point);
    }

    void moveit(Car car, int x, int y){
        Point generalPoint = carAndPoint.get(car);
        generalPoint.x = x;
        generalPoint.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saab95Image = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));

            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Car vehicle : carAndPoint.keySet()){
            String model = vehicle.getCarModel();
            Point general = carAndPoint.get(vehicle);
            BufferedImage img = null;
            if(model == "Volvo240"){
                img = volvoImage;
            } else if (model == "Saab95"){
                img = saab95Image;
            } else if (model == "Scania"){
                img = scaniaImage;
            } else {
                System.out.println("img does not exist");
            }
            g.drawImage(img, general.x, general.y, null);
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
