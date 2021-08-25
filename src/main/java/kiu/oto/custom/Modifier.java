package kiu.oto.custom;

import kiu.oto.common.AbstractModifier;
import kiu.oto.common.FloatPoint;
import kiu.oto.common.Vertex;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static kiu.oto.common.CommonMethodsAndSettings.*;
@AllArgsConstructor
public class Modifier extends AbstractModifier {

    private static final int TEMPLATE_MODE_IMPORT = 1;
    private static final int TEMPLATE_MODE_MANUAL = 2;

    private static final ArrayList<Vertex> vertices = new ArrayList<>();

    private final Panel panel;

    @Override
    public void prepareSetup() {
        vertices.clear();
        //determines whether vertices are drawn manually or imported with template
        int templateMode = getUserChoice();
        if(templateMode == TEMPLATE_MODE_IMPORT)
            importTemplate();
    }
    
    private void importTemplate() {
        JFileChooser ch = new JFileChooser(); //TODO updated from final
        ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ch.showOpenDialog(new Container());
        File project = ch.getSelectedFile();
        File template = new File(project.getPath() + "/template");
        buildFromTemplate(template);
    }

    private void buildFromTemplate(File template) {//TODO save '|' in a variable and refer to it in export method
        List<String>  content;

        try {
            content = Files.readAllLines(template.toPath());

            String[] dimensions = content.remove(0).split("\\|");
            double widthRatio = EXPORTED_IMAGE_WIDTH / (double) Integer.parseInt(dimensions[0]);
            double heightRatio = EXPORTED_IMAGE_HEIGHT / (double) Integer.parseInt(dimensions[1]);

            for(String vertexData : content) {
                String[] parameters = vertexData.split("\\|");
                double x = Double.parseDouble(parameters[0]) * widthRatio;
                double y = Double.parseDouble(parameters[1]) * heightRatio;
                double compressionRatio = Double.parseDouble(parameters[2]);
                double rotationDegree = Double.parseDouble(parameters[3]);
                boolean clockwise = SettingsAndMethods.parseBoolean(parameters[4]);
                int color = Integer.parseInt(parameters[5]);
                int quantity = Integer.parseInt(parameters[6]);

                addVertex(new Vertex(x, y, compressionRatio, rotationDegree, clockwise, color, quantity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int getUserChoice() {
        System.out.println(
                "Input " + TEMPLATE_MODE_IMPORT +
                " to import template. " +
                "Input " + TEMPLATE_MODE_MANUAL +
                " to continue manually:");
        int userChoice;
        do {
            userChoice = inputInteger();
        } while (userChoice != TEMPLATE_MODE_IMPORT && userChoice != TEMPLATE_MODE_MANUAL);
        return userChoice;
    }

    private Vertex getRandomVertex() {
        return vertices.get(RANDOM.nextInt(vertices.size()));
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public void modify(FloatPoint point) {
        if(vertices.size() == 0 || point == null)
            return;
        Vertex randomVertex = getRandomVertex();
        FloatPoint modified = randomVertex.next(point);
        point.setX(modified.getX());
        point.setY(modified.getY());
        point.setColor(randomVertex.getColor());
    }

    private void addVertex(Vertex vertex) {
        for(int i = 0; i < vertex.getQuantity(); i++)
            vertices.add(vertex);
    }

    public void createVertex(MouseEvent e) {
        double x = e.getX() * IMAGE_PANEL_WIDTH_RATIO;
        double y = e.getY() * IMAGE_PANEL_HEIGHT_RATIO;
        double compressionRatio = inputCompressionRatio();
        double rotationDegree = inputRotationDegree();
        boolean clockwise = inputRotationDirection();
        int color = inputColor();
        int quantity = inputQuantity();

        Vertex createdVertex = new Vertex(x, y, compressionRatio, rotationDegree, clockwise, color, quantity);

        addVertex(createdVertex);

        panel.drawVertex(createdVertex);

        System.out.println("Vertex created.");

    }

    private double inputCompressionRatio() {
        System.out.println("Input compression ratio (double):");
        double input;
        do {
            input = inputDouble();
        } while (input == 0);
        return input;
    }

    private double inputRotationDegree() {
        System.out.println("Input rotation degree (double):");
        return inputDouble();
    }

    //returns true if clockwise, returns false if counter clockwise
    private boolean inputRotationDirection() {
        System.out.println("Choose rotation direction (1 for clockwise, 2 for counter clockwise):");
        int choice;
        do {
            choice = inputInteger();
        } while(choice != 1 && choice != 2);

        return choice == 1;
    }

    private int inputQuantity() {
        System.out.println("Input vertex quantity: ");
        return inputInteger();
    }
}
