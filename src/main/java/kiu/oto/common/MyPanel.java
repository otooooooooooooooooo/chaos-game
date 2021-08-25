package kiu.oto.common;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static kiu.oto.common.CommonMethodsAndSettings.*;

public abstract class MyPanel extends JPanel implements KeyListener {
    private MyFrame frame;
    private final MyLabel label = new MyLabel();

    //iterable point which makes image
    protected FloatPoint current;


    public MyPanel() {
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setBounds(0, 0, getWidth(), getHeight());
        setLayout(null);
        setFocusable(true);
        addKeyListener(this);
        add(label);
        updateImage();
        setModifier();
    }

    protected BufferedImage getImage() {
        return label.getImage();
    }

    /*sets the modifier which will be used to iterate current point
    modifier should be set by means of static method FloatPoint.setPointModifier(modifier)
     */
    protected abstract void setModifier();

    protected void updateImage() {
        revalidate();
        repaint();
    }
    //takes arguments in respect to displayed image. mainly used to draw with mouse
    protected void setAndDrawCurrentUnscaled(int x, int y) {
        setAndDrawCurrent(round(x * IMAGE_PANEL_WIDTH_RATIO), round(y * IMAGE_PANEL_HEIGHT_RATIO));
    }

    protected void setAndDrawCurrent(double x, double y) {
        current = new FloatPoint(x, y);
        paintCurrent();
        updateImage();
    }

    protected void paint(FloatPoint p) {
        paint(getUsablePoint(p), p.getColor());
    }

    protected abstract Point getUsablePoint(FloatPoint p );

    protected Point getUsablePointFromCurrent() {
        return getUsablePoint(current);
    }

    //iterates current 'amount' times and updates image
    protected void paintNextPoint(int amount) {
        if(current == null) {
            minimize();
            System.out.println("Current point is not initialized.");
            return;
        }
        if(amount <= 0)
            return;
        for(int i = 0; i < amount; i++) {
            doNextIteration();
            paintCurrent();
        }
        updateImage();
    }
    //paints iterable point
    protected void paintCurrent() {
        paint(getUsablePointFromCurrent(), current.getColor());
    }
    //does one iteration and modifies ''current''
    protected void doNextIteration() {
        current.modify();
    }

    protected void paint(Point p, int color) {
        paint(p.x, p.y, color);
    }

    //paints point but doesn't update image
    protected void paint(int x, int y, int color) {
        label.paint(x, y, color);
    }

    public void setFrame(MyFrame frame) {
        this.frame = frame;
        updateImage();
    }
    public void minimize(){
        frame.minimize();
    }

    protected void export() {
        String directoryPath = getChosenExportDirectory();
        String projectName = getProjectNameInput();
        try {
            ImageIO.write(getImage(), "png", Files.createFile(Paths.get("" + directoryPath + "/" + projectName + ".png")).toFile());
            System.out.println("Project saved successfully");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    protected  void doRelevantAction(KeyEvent e) {//TODO switch
        if(e.getKeyCode() == PAINT_NEXT)
            paintNextPoint(1);
        else if (e.getKeyCode() == PAINT_BIG_STEP)
            paintNextPoint(AMOUNT_PER_BIG_STEP);
        else if (e.getKeyCode() == PAINT_FULL_IMAGE)
            paintNextPoint(AMOUNT_TO_DRAW_FULL_IMAGE);
        else if (e.getKeyCode() == EXPORT_IMAGE)
            export();
        else if (e.getKeyCode() == MINIMIZE_WINDOW)
            minimize();
        else if (e.getKeyCode() == RESTART_PROGRAM) {
            restartProgram();
        }
    }

    protected String getProjectNameInput() {
        minimize();
        System.out.println("Input project name:");
        return inputString();
    }



    private void restartProgram() {
        frame.dispose();
        Run.startProgram();
    }

    protected abstract void doRelevantAction(MouseEvent e);

    //key shortcuts
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        doRelevantAction(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

class MyLabel extends JLabel {
    private final MyImage image = new MyImage();
    public MyLabel() {
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setBounds(0, 0, getWidth(), getHeight());
        setIcon(new ImageIcon(image));
    }
    public BufferedImage getImage() { return image.getHighResolutionImage();}
    public void paint(int x, int y, int color) {
        image.setRGB(x, y, color);
    }
}

//this is instance of BufferedImage and also saves image data with higher resolution
class MyImage extends BufferedImage {
    private final int[][] highResolutionImageData = new int[EXPORTED_IMAGE_WIDTH][EXPORTED_IMAGE_HEIGHT];
    public MyImage() {
        super(PANEL_WIDTH, PANEL_HEIGHT, TYPE_INT_RGB);
        setBackground();
    }

    public BufferedImage getHighResolutionImage() {
        BufferedImage img = new BufferedImage(EXPORTED_IMAGE_WIDTH, EXPORTED_IMAGE_HEIGHT, TYPE_INT_RGB);
        for(int i = 0; i < EXPORTED_IMAGE_WIDTH; i++)
            for(int j = 0; j < EXPORTED_IMAGE_HEIGHT; j++)
                img.setRGB(i, j, highResolutionImageData[i][j]);
        return img;
    }

    /* only high resolution coordinates should be passed to this method. this method
    will save it in img data, will map it and set on displayed image as well.
     */
    @Override
    public void setRGB(int x, int y, int color) {
        try {
            highResolutionImageData[x][y] = color;
            super.setRGB((int) (x / IMAGE_PANEL_WIDTH_RATIO), (int) (y / IMAGE_PANEL_HEIGHT_RATIO), color);
        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }

    private void setBackground() {
        for(int i = 0; i < EXPORTED_IMAGE_WIDTH; i++)
            for(int j = 0; j < EXPORTED_IMAGE_HEIGHT; j++)
                setRGB(i, j, BACKGROUND_COLOR);
    }

}
