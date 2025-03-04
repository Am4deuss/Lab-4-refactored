import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel implements Subscriber{

    BufferedImage volvoImage;
    BufferedImage saab95Image;
    BufferedImage scaniaImage;

    HashMap<Car, Point> carAndPoint = new HashMap<>();

    BufferedImage volvoWorkshopImage;

    // is provided by Model
    private ArrayList<Car> allCars = new ArrayList<>();
    private ArrayList<Garage> allGarage = new ArrayList<>();

    @Override
    public void notify(Model model){
        this.allCars = model.getAllCars();
        this.allGarage = model.getAllGarage();
        SwingUtilities.invokeLater(this::repaint);
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
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Car car : allCars){
            if(!car.getLoaded()) {
                String model = car.getCarModel();
                BufferedImage img = null;
                if (model == "Volvo240") {
                    img = volvoImage;
                } else if (model == "Saab95") {
                    img = saab95Image;
                } else if (model == "Scania") {
                    img = scaniaImage;
                } else {
                    System.out.println("img does not exist");
                }
                g.drawImage(img, (int) car.getX(), (int) car.getY(), null);
            }
        }
        for (Garage garage : allGarage){
            g.drawImage(volvoWorkshopImage, (int) garage.getX(), (int) garage.getY(), null);
        }
    }
}
