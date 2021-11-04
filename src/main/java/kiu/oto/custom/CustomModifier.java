package kiu.oto.custom;

import kiu.oto.common.AbstractModifier;
import kiu.oto.common.FloatPoint;
import kiu.oto.common.Vertex;
import kiu.oto.common.inputs.MultiChoiceHandler;
import kiu.oto.common.inputs.MultiChoiceInputPanel;
import kiu.oto.common.inputs.PopupDialogFrame;
import kiu.oto.common.inputs.enums.TemplateMode;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static kiu.oto.common.CommonMethodsAndSettings.*;
import static kiu.oto.common.inputs.enums.TemplateMode.IMPORT;

@AllArgsConstructor
public class CustomModifier extends AbstractModifier {

    private static final ArrayList<Vertex> vertices = new ArrayList<>();

    private final CustomPanel customPanel;

    @Override
    public void prepareSetup() {
        vertices.clear();
        //determines whether vertices are drawn manually or imported with template
        TemplateMode templateMode = getUserChoice();
        if(templateMode == IMPORT)
            importTemplate();
    }
    //TODO crashes when user presses 'cancel' and not choosing file
    private void importTemplate() {
        JFileChooser ch = new JFileChooser(); //TODO updated from final
        ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ch.showOpenDialog(new Container());
        File project = ch.getSelectedFile();
        File template = new File(project.getPath() + "/template");
        buildFromTemplate(template);
    }

    //TODO json format template import/export
    private void buildFromTemplate(File template) {
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
                boolean clockwise = CustomSettingsAndMethods.parseBoolean(parameters[4]);
                int color = Integer.parseInt(parameters[5]);
                int quantity = Integer.parseInt(parameters[6]);

                addVertex(new Vertex(x, y, compressionRatio, rotationDegree, clockwise, color, quantity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private TemplateMode getUserChoice() {
        return new PopupDialogFrame<>(new MultiChoiceInputPanel<>("Choose mode",
                MultiChoiceHandler.templateModeHandler()
        )).getInput();
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
        int color = inputColor("Choose vertex color");
        int quantity = inputQuantity();

        Vertex createdVertex = new Vertex(x, y, compressionRatio, rotationDegree, clockwise, color, quantity);

        addVertex(createdVertex);

        customPanel.drawVertex(createdVertex);

        System.out.println("Vertex created.");

    }

    private double inputCompressionRatio() {
        double input;
        do {
            input = inputDouble("Input compression ratio (nonzero)", 2.0);
        } while (input == 0);
        return input;
    }

    private double inputRotationDegree() {
        return inputDouble("Input rotation degree", 0.0);
    }

    //returns true if clockwise, returns false if counterclockwise
    private boolean inputRotationDirection() {
        return new PopupDialogFrame<>
                (new MultiChoiceInputPanel<>
                        ("Choose rotation direction",
                                MultiChoiceHandler.rotationDirectionHandler())).getInput()
                .isClockwise();
    }

    private int inputQuantity() {
        return inputInteger("Input vertex quantity", 1);
    }
}
