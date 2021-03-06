package kiu.oto.custom;

import kiu.oto.common.FloatPoint;
import kiu.oto.common.CommonPanel;
import kiu.oto.common.Vertex;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static kiu.oto.common.CommonMethodsAndSettings.*;

public class CustomPanel extends CommonPanel implements MouseListener {

    public CustomPanel() {
        addMouseListener(this);
    }

    @Override
    protected void setModifier() {
        CustomModifier customModifier = new CustomModifier(this);
        FloatPoint.setPointModifier(customModifier);
        customModifier.getVertices().forEach(this::paint);
    }

    @Override
    protected void getInputParameters() {

    }

    @Override
    protected Point getUsablePoint(FloatPoint p) {
        return new Point(round(p.getX()), round(p.getY()));
    }


    /*
    template:
    RESOLUTION_X, RESOLUTION_Y
    Vertex.tostring
    vertex.tostring
     */

    @Override
    protected void export() {
        String directoryPath = getChosenExportDirectory();
        String projectName = getProjectNameInput();
        StringBuilder data = new StringBuilder();
        data.append(EXPORTED_IMAGE_WIDTH).append("|").append(EXPORTED_IMAGE_HEIGHT).append('\n');
        Set<Vertex> vertices = new HashSet<>(((CustomModifier) FloatPoint.getModifier()).getVertices());
        vertices.forEach(x -> data.append(x.toString()));

        try {
            Path directory = Files.createDirectory(Paths.get(directoryPath + "/" + projectName + ".template"));
            Path template = Files.createFile(Paths.get(directory.toString() + "/template"));
            File painting = Files.createFile(Paths.get(directory.toString() + "/image.png")).toFile();
            Files.writeString(template, data);
            ImageIO.write(getImage(), "png", painting);
            minimize();
            output("Project saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawVertex(Vertex vertex) {
        paint(vertex);
    }

    @Override
    protected void doRelevantAction(MouseEvent e) {
        if(e.getButton() == CustomSettingsAndMethods.CREATE_VERTEX) {
            ((CustomModifier) FloatPoint.getModifier()).createVertex(e);
        }
        else if (e.getButton() == CustomSettingsAndMethods.PAINT_CURRENT)
            setAndDrawCurrentUnscaled(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        doRelevantAction(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
