package image.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import image.controller.Features;

/**
 * Implements the View.
 * 
 * @author  Peter Laudat
 * @version 09/20/2020
 *
 */
public class ViewImpl extends JFrame implements View {
    /** System generated serial id. */
    private static final long serialVersionUID = -7293519515508019533L;
    /** One dimensional images. */
    private static final Set<String> ONE_D_IMAGES = new HashSet<>();
    /** Two dimensional images. */
    private static final Set<String> TWO_D_IMAGES = new HashSet<>();
    /** Signal that file chooser should open file. */
    private static final String OPEN = "OPEN";
    /** Signal that chooser should save file. */
    private static final String SAVE = "SAVE";
    /** String for height label. */
    private static final String HEIGHT_STRING = "Image Height (pixels)";
    /** String for width label. */
    private static final String WIDTH_STRING = "Image Width (pixels)";

    static {
        ONE_D_IMAGES.add("checkerboard");
        ONE_D_IMAGES.add("franceFlag");
        ONE_D_IMAGES.add("greeceFlag");
        ONE_D_IMAGES.add("switzerlandFlag");
        TWO_D_IMAGES.add("horizontalRainbowStripes");
        TWO_D_IMAGES.add("verticalRainbowStripes");
    }
    /** Panel. */
    private JPanel contentPanel;
    /** Menu bar. */
    private JMenuBar appMenuBar;
    /** Menu. */
    private JMenu fileMenu;
    /** New file submenu. */
    private JMenu newSubmenu;
    /** Rainbow stripes submenu. */
    private JMenu rainbowStripes;
    /** New image file menu item. */
    private JMenuItem horizontalRainbowStripesMenuItem;
    /** Open File file menu item. */
    private JMenuItem openFileMenuItem;
    /** Save File file menu item. */
    private JMenuItem saveFileMenuItem;
    /** Exit program file menu item. */
    private JMenuItem exitProgramFileMenuItem;
    /** Image display. */
    private JLabel display;
    /** Blur button. */
    private JButton blurButton;
    /** Blur button. */
    private JButton sharpenButton;
    /** Blur button. */
    private JButton grayscaleButton;
    /** Blur button. */
    private JButton sepiaButton;
    /** Button group. */
    private ButtonGroup buttonGroup;
    /** Button toolbar. */
    private JToolBar toolbar;
    /** Rainbow stripes image height label for text field. */
    private JLabel imageHeightLabel;
    /** Rainbow stripes image width label for text field. */
    private JLabel imageWidthLabel;
    /** Rainbow stripes image height text field. */
    private JTextField imageHeightField;
    /** Rainbow stripes image height text field. */
    private JTextField imageWidthField;
    /** Pane containing image height label and text field. */
    private JPanel imageHeightDimensionPane;
    /** Pane containing image width label and text field. */
    private JPanel imageWidthDimensionPane;
    /** Pane containing image dimensions submit button. */
    private JPanel imageDimensionsSubmitPane;
    /** Image dimensions submit button. */
    private JButton imageDimensionsSubmitButton;
    /** Pane containing labels, fields, and submit button for image dimensions submission. */
    private JPanel imageDimensionsPane;
    /** Vertical rainbow stripes menu item. */
    private JMenuItem verticalRainbowStripesMenuItem;
    /** Enable multiple uses for same button by tracking user-selected feature. */
    private String imageType;
    /** Flags submenu. */
    private JMenu flags;
    /** French flag menu item. */
    private JMenuItem franceFlagMenuItem;
    /** Swiss flag menu item. */
    private JMenuItem switzerlandFlagMenuItem;
    /** Greek flag menu item. */
    private JMenuItem greeceFlagMenuItem;
    /** Checkerboard submenu. */
    private JMenu checkerboard;
    /** Checkerboard menu item. */
    private JMenuItem checkerboardMenuItem;
    /** Run batch file of Image Generator commands. */
    private JMenuItem runBatchFileMenuItem;

    /**
     * ViewImpl Constructor.
     * 
     * @param caption application JFrame title
     */
    public ViewImpl(String caption) {
        super(caption);
        // Set container
        this.setLayout(new BorderLayout());
        this.setSize(1000, 1000);
        this.setLocation(700, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create content panel that will overlay the JFrame content pane to
        // take advantage of JComponent features
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder());

        // application menu bar
        appMenuBar = new JMenuBar();

        // File menu
        fileMenu = new JMenu("Menu");

        // create and add New file menu item to File menu
        newSubmenu = new JMenu("Generate New Image");
        fileMenu.add(newSubmenu);

        // create and add rainbow stripes submenu
        rainbowStripes = new JMenu("Rainbow Stripes");
        newSubmenu.add(rainbowStripes);

        // create and add horizontalRainbowStripesMenuItem menu item to rainbow stripes submenu
        horizontalRainbowStripesMenuItem = new JMenuItem("Horizontal");
        horizontalRainbowStripesMenuItem.setActionCommand("Horizontal Rainbow Stripes Menu Item");
        rainbowStripes.add(horizontalRainbowStripesMenuItem);

        // create and add verticalRainbowStripesMenuItem menu item to rainbow stripes submenu
        verticalRainbowStripesMenuItem = new JMenuItem("Vertical");
        verticalRainbowStripesMenuItem.setActionCommand("Vertical Rainbow Stripes Menu Item");
        rainbowStripes.add(verticalRainbowStripesMenuItem);

        // Create the image height and width labels.
        imageHeightLabel = new JLabel(HEIGHT_STRING);
        imageWidthLabel = new JLabel(WIDTH_STRING);

        // Create the image height and width text fields and set them up
        imageHeightField = new JTextField(10);
        imageHeightField.setActionCommand("Image Height Field");
        imageWidthField = new JTextField(10);
        imageWidthField.setActionCommand("Image Width Field");

        // Tell accessibility tools about the image label/text field pairs
        imageHeightLabel.setLabelFor(imageHeightField);
        imageWidthLabel.setLabelFor(imageWidthField);

        // Lay out the image height dimensions pane
        imageHeightDimensionPane = new JPanel(new GridLayout(0, 1));
        imageHeightDimensionPane.add(imageHeightLabel);
        imageHeightDimensionPane.add(imageHeightField);

        // Lay out the image width dimensions pane
        imageWidthDimensionPane = new JPanel(new GridLayout(0, 1));
        imageWidthDimensionPane.add(imageWidthLabel);
        imageWidthDimensionPane.add(imageWidthField);

        // Lay out out the image dimensions submit button pane
        imageDimensionsSubmitPane = new JPanel(new FlowLayout());
        imageDimensionsSubmitButton = new JButton("Submit");
        imageDimensionsSubmitButton.setActionCommand("Submit Rainbow Stripes Image Dimensions");
        imageDimensionsSubmitPane.add(imageDimensionsSubmitButton);

        // Lay out the image dimensions pane composed of height, width, and submit panes
        imageDimensionsPane = new JPanel(new FlowLayout());
        imageDimensionsPane.add(imageHeightDimensionPane);
        imageDimensionsPane.add(imageWidthDimensionPane);
        imageDimensionsPane.add(imageDimensionsSubmitPane);
        imageDimensionsPane.setVisible(false);

        // Add the complete image dimensions pane to the content panel
        contentPanel.add(imageDimensionsPane, BorderLayout.PAGE_START);

        // create and add flags submenu
        flags = new JMenu("Flags");
        newSubmenu.add(flags);

        // create and add French flag menu item to flags submenu
        franceFlagMenuItem = new JMenuItem("France");
        franceFlagMenuItem.setActionCommand("France Flag Menu Item");
        flags.add(franceFlagMenuItem);

        // create and add Swiss flag menu item to flags submenu
        switzerlandFlagMenuItem = new JMenuItem("Switzerland");
        switzerlandFlagMenuItem.setActionCommand("Switzerland Flag Menu Item");
        flags.add(switzerlandFlagMenuItem);

        // create and add Greek flag menu item to flags submenu
        greeceFlagMenuItem = new JMenuItem("Greece");
        greeceFlagMenuItem.setActionCommand("Greece Flag Menu Item");
        flags.add(greeceFlagMenuItem);

        // create and add checkerboard submenu
        checkerboard = new JMenu("Checkerboard");
        newSubmenu.add(checkerboard);

        // create and add checkerboard menu item to checkerboard submenu
        checkerboardMenuItem = new JMenuItem("Checkerboard");
        checkerboardMenuItem.setActionCommand("Checkerboard Menu Item");
        checkerboard.add(checkerboardMenuItem);

        // create and add Open Image file file menu item to File menu
        openFileMenuItem = new JMenuItem("Open Image File...");
        openFileMenuItem.setActionCommand("Open Image File Menu Item");
        fileMenu.add(openFileMenuItem);

        // create and add Save Image File menu item to File menu
        saveFileMenuItem = new JMenuItem("Save to Image File...");
        saveFileMenuItem.setActionCommand("Save to Image File Menu Item");
        fileMenu.add(saveFileMenuItem);

        // create and add Run Batch (commands) File file menu item to File menu
        runBatchFileMenuItem = new JMenuItem("Run Batch File");
        runBatchFileMenuItem.setActionCommand("Run Batch File Menu Item");
        fileMenu.add(runBatchFileMenuItem);

        // create and add Exit Program file menu item to File menu
        exitProgramFileMenuItem = new JMenuItem("Exit");
        exitProgramFileMenuItem.setActionCommand("Exit Menu Item");
        fileMenu.add(exitProgramFileMenuItem);

        // add File menu to app menu bar
        appMenuBar.add(fileMenu);
        // set the JFrame menu bar
        this.setJMenuBar(appMenuBar);

        // create the label which will display the image
        display = new JLabel();
        display.setHorizontalAlignment(JLabel.CENTER);
        display.setVerticalAlignment(JLabel.CENTER);
        // add the label to the center panel of the contentPanel border layout
        contentPanel.add(display, BorderLayout.CENTER);

        // make the content panel the content pane
        this.setContentPane(contentPanel);

        // create and add the blur button to the left hand border
        blurButton = new JButton("Blur");
        blurButton.setVisible(true);
        blurButton.setActionCommand("blur");

        // create and add the sharpen button to the border line start
        sharpenButton = new JButton("Sharpen");
        sharpenButton.setVisible(true);
        sharpenButton.setActionCommand("sharpen");

        // create and add the grayscale button to the border line start
        grayscaleButton = new JButton("Grayscale");
        grayscaleButton.setVisible(true);
        grayscaleButton.setActionCommand("grayscale");

        // create and add the sepia button to the border line start
        sepiaButton = new JButton("Sepia");
        sepiaButton.setVisible(true);
        sepiaButton.setActionCommand("sepia");

        // create button group and add the buttons to the group
        buttonGroup = new ButtonGroup();
        buttonGroup.add(blurButton);
        buttonGroup.add(sharpenButton);
        buttonGroup.add(grayscaleButton);
        buttonGroup.add(sepiaButton);
        buttonGroup.clearSelection();

        // create toolbar and add the buttons to the toolbar
        toolbar = new JToolBar(JToolBar.HORIZONTAL);
        toolbar.add(blurButton);
        toolbar.add(sharpenButton);
        toolbar.add(grayscaleButton);
        toolbar.add(sepiaButton);

        contentPanel.add(toolbar, BorderLayout.PAGE_END);

        this.setVisible(true);

    }

    /**
     * Accept the set of callbacks from the controller, and hook up as needed to various things in this
     * view.
     * 
     * @param f the set of feature callbacks as a Features object
     */
    @Override
    public void setFeatures(Features f) {

        // Listen for horizontal rainbow stripes menu item and set menu accordingly
        horizontalRainbowStripesMenuItem.addActionListener(l -> {
            imageHeightLabel.setVisible(true);
            imageHeightField.setVisible(true);
            imageDimensionsPane.setVisible(true);
            setImageType("horizontalRainbowStripes");
            validate();
        });

        // Listen for vertical rainbow stripes menu item and display appropriate dimension capture pane
        verticalRainbowStripesMenuItem.addActionListener(l -> {
            imageHeightLabel.setVisible(true);
            imageHeightField.setVisible(true);
            imageDimensionsPane.setVisible(true);
            setImageType("verticalRainbowStripes");//
            validate();
        });

        // Listen for france flag menu item and display appropriate dimension capture pane
        franceFlagMenuItem.addActionListener(l -> {
            imageHeightLabel.setVisible(false);
            imageHeightField.setVisible(false);
            imageDimensionsPane.setVisible(true);
            setImageType("franceFlag");
            validate();
        });

        // Listen for switzerland flag menu item and display appropriate dimension capture pane
        switzerlandFlagMenuItem.addActionListener(l -> {
            imageHeightLabel.setVisible(false);
            imageHeightField.setVisible(false);
            imageDimensionsPane.setVisible(true);
            setImageType("switzerlandFlag");
            validate();
        });

        // Listen for greece flag menu item and display appropriate dimension capture pane
        greeceFlagMenuItem.addActionListener(l -> {
            imageHeightLabel.setVisible(false);
            imageHeightField.setVisible(false);
            imageDimensionsPane.setVisible(true);
            setImageType("greeceFlag");
            validate();
        });

        // Listen for checkerboard menu item and display appropriate dimension capture pane
        checkerboardMenuItem.addActionListener(l -> {
            imageHeightLabel.setVisible(false);
            imageHeightField.setVisible(false);
            imageDimensionsPane.setVisible(true);
            setImageType("checkerboard");
            validate();
        });

        // Submit user-selected image type and user-entered dimensions to the controller
        imageDimensionsSubmitButton.addActionListener(l -> {
            int width = Integer.parseInt(imageWidthField.getText());
            String imageCommand = this.getImageType();
            if (ONE_D_IMAGES.contains(imageCommand)) {
                f.render(imageCommand, width);
            }
            if (TWO_D_IMAGES.contains(imageCommand)) {
                int height = Integer.parseInt(imageHeightField.getText());
                f.render(imageCommand, height, width);
            }
        });

        // Listen for Run Batch File menu item and set menu accordingly
        runBatchFileMenuItem.addActionListener(l -> {
            try {
                f.runBatchFile(chooseBatchFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Open image file
        openFileMenuItem.addActionListener(l -> {
            try {
                f.load(chooseImageFile(OPEN));
                makeButtonsVisible(true);
            } catch (HeadlessException | IOException e) {
                e.printStackTrace();
            }
        });

        // Save image to file
        saveFileMenuItem.addActionListener(l -> {
            try {
                f.save(chooseImageFile(SAVE));
            } catch (HeadlessException | IOException e) {
                e.printStackTrace();
            }
        });

        // exit program is tied to the exit button
        exitProgramFileMenuItem.addActionListener(l -> f.exitProgram());
        // Blur the current image
        blurButton.addActionListener(l -> f.render(l.getActionCommand()));
        // Sharpen the current image
        sharpenButton.addActionListener(l -> f.render(l.getActionCommand()));
        // Grayscale the current image
        grayscaleButton.addActionListener(l -> f.render(l.getActionCommand()));
        // Sepia the current image
        sepiaButton.addActionListener(l -> f.render(l.getActionCommand()));

    }

    // return the image type
    private String getImageType() {
        return this.imageType;
    }

    // Set the image type
    private void setImageType(String imageType) {
        this.imageType = imageType;
    }

    // Select file to save or open and return absolute pathname
    private String chooseImageFile(String chooserType) {
        JFileChooser chooser = new JFileChooser();
        String chosen = "";
        String fileExtension = ".jpg";
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
        Path path = FileSystems.getDefault().getPath("");
        File currentDirectory = path.toFile();
        chooser.setCurrentDirectory(currentDirectory);
        chooser.setFileFilter(filter);

        if (chooserType.equals(SAVE)) {
            int returnVal = chooser.showSaveDialog(saveFileMenuItem);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                chosen = chooser.getSelectedFile().getAbsolutePath().concat(fileExtension);
            }
        } else {
            int returnVal = chooser.showOpenDialog(openFileMenuItem);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                chosen = chooser.getSelectedFile().getAbsolutePath();
            }
        }
        return chosen;
    }

    // Select file to save or open and return absolute pathname
    private String chooseBatchFile() {
        JFileChooser chooser = new JFileChooser();
        String chosen = "";
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        Path path = FileSystems.getDefault().getPath("");
        File currentDirectory = path.toFile();
        chooser.setCurrentDirectory(currentDirectory);
        int returnVal = chooser.showOpenDialog(runBatchFileMenuItem);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            chosen = chooser.getSelectedFile().getAbsolutePath();
        }
        return chosen;
    }

    /**
     * Updates the display with an ImageIcon, or null if the path was invalid.
     * 
     * @param path image file's path
     */
    @Override
    public void updateDisplay(String path) {
        if (path != null) {
            ImageIcon imageIcon = new ImageIcon(path);
            display.setIcon(imageIcon);
            display.setVisible(true);
        } else {
            System.err.println("Couldn't find file: " + path);
        }
    }

    @Override
    public void updateDisplay(Image image) {
        ImageIcon imageIcon = new ImageIcon(image);
        display.setIcon(imageIcon);
        display.repaint();
    }

    public void resetFocus() {
        setFocusable(true);
        requestFocus();
    }

    private void makeButtonsVisible(boolean v) {
        blurButton.setVisible(v);
        sharpenButton.setVisible(v);
        grayscaleButton.setVisible(v);
        sepiaButton.setVisible(v);
    }

}
