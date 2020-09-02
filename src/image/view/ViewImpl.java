package image.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

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

public class ViewImpl extends JFrame implements View {
    /** System generated serial id. */
    private static final long serialVersionUID = -7293519515508019533L;
    /** Signal that file chooser should open file. */
    private static final String OPEN = "OPEN";
    /** Signal that chooser should save file. */
    private static final String SAVE = "SAVE";
    /** String for height label. */
    private static final String HEIGHT_STRING = "Height";
    /** String for width label. */
    private static final String WIDTH_STRING = "Width";
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
    /** Quit file menu item. */
    private JMenuItem quitFileMenuItem;
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
    private JLabel rainbowStripesHeightLabel;
    /** Rainbow stripes image width label for text field. */
    private JLabel rainbowStripesWidthLabel;
    /** Rainbow stripes image height text field. */
    private JTextField rainbowStripesHeightField;
    /** Rainbow stripes image height text field. */
    private JTextField rainbowStripesWidthField;
    /** Pane containing rainbow stripes labels. */
    private JPanel rainbowStripesLabelPane;
    /** Pane containing rainbow stripes fields. */
    private JPanel rainbowStripesFieldPane;
    /** Pane containing rainbow stripes submit button. */
    private JPanel rainbowStripesSubmitPane;
    /** Rainbow stripes submit button. */
    private JButton rainbowStripesSubmitButton;
    /** Pane containing labels, fields, and submit button for rainbow stripes image definition. */
    private JPanel rainbowStripesDimsPane;
    /** Image height. */
    private int imageHeight;
    /** Image width. */
    private int imageWidth;
    /** Vertical rainbow stripes menu item. */
    private JMenuItem verticalRainbowStripesMenuItem;
    /** Enable multiple uses for same button by tracking user-selected feature. */
    private String featureFlag;

    /**
     * ViewImpl Constructor.
     * 
     * @param caption
     */
    public ViewImpl(String caption) {
        super(caption);
        // Set container
        this.setLayout(new BorderLayout());
        this.setSize(1000, 1000);
        this.setLocation(700, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create content panel that will overlay the JFrame content pane to allow the ability to take
        // advantage of JComponent features
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder());

        // application menu bar
        appMenuBar = new JMenuBar();

        // File menu
        fileMenu = new JMenu("File");

        // create and add New file menu item to File menu
        newSubmenu = new JMenu("New");
        fileMenu.add(newSubmenu);

        // create and add rainbow stripes submenu new submenu
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

        // Create the horizontal rainbow stripes image height and width labels.
        rainbowStripesHeightLabel = new JLabel(HEIGHT_STRING);
        rainbowStripesWidthLabel = new JLabel(WIDTH_STRING);

        // Create the horizontal and vertical rainbow stripes height and width text fields and set them up
        rainbowStripesHeightField = new JTextField(10);
        rainbowStripesHeightField.setActionCommand("Rainbow Stripes Height Field");

        rainbowStripesWidthField = new JTextField(10);
        rainbowStripesWidthField.setActionCommand("Rainbow Stripes Width Field");

        // Tell accessibility tools about horizontal rainbow stripes label/textfield pairs
        rainbowStripesHeightLabel.setLabelFor(rainbowStripesHeightField);
        rainbowStripesWidthLabel.setLabelFor(rainbowStripesWidthField);

        // Lay out the horizontal rainbow stripes labels in a panel
        rainbowStripesLabelPane = new JPanel(new GridLayout(0, 1));

        rainbowStripesLabelPane.add(rainbowStripesHeightLabel);
        rainbowStripesLabelPane.add(rainbowStripesHeightField);

        // Lay out the horizontal rainbow stripes text fields in a panel
        rainbowStripesFieldPane = new JPanel(new GridLayout(0, 1));
        rainbowStripesFieldPane.add(rainbowStripesWidthLabel);
        rainbowStripesFieldPane.add(rainbowStripesWidthField);

        // Layout out the horizontal rainbow stripes submit button in horizontal rainbow stripes panel
        rainbowStripesSubmitPane = new JPanel(new FlowLayout());
        rainbowStripesSubmitButton = new JButton("Submit");
        rainbowStripesSubmitButton.setActionCommand("Submit Rainbow Stripes Image Dimensions");
        rainbowStripesSubmitPane.add(rainbowStripesSubmitButton);

        // Lay out the horizontal rainbow stripes labels and text field panels in a single horizontal
        // rainbow stripes panel.
        rainbowStripesDimsPane = new JPanel(new FlowLayout());
        rainbowStripesDimsPane.add(rainbowStripesLabelPane);
        rainbowStripesDimsPane.add(rainbowStripesFieldPane);
        rainbowStripesDimsPane.add(rainbowStripesSubmitPane);
        rainbowStripesDimsPane.setVisible(false);

        // Add the complete rainbow stripes dimensions pane to the content panel
        contentPanel.add(rainbowStripesDimsPane, BorderLayout.PAGE_START);

        // create and add Open file file menu item to File menu
        openFileMenuItem = new JMenuItem("Open File...");
        openFileMenuItem.setActionCommand("Open File Menu Item");
        fileMenu.add(openFileMenuItem);

        // create and add Save to file file menu item to File menu
        saveFileMenuItem = new JMenuItem("Save to File...");
        saveFileMenuItem.setActionCommand("Save to File Menu Item");
        fileMenu.add(saveFileMenuItem);

        // create and add Quit file menu item to File menu
        quitFileMenuItem = new JMenuItem("Quit");
        quitFileMenuItem.setActionCommand("Quit Menu Item");
        fileMenu.add(quitFileMenuItem);

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
        blurButton.setVisible(false);
        blurButton.setActionCommand("Blur Button");

        // create and add the sharpen button to the border line start
        sharpenButton = new JButton("Sharpen");
        sharpenButton.setVisible(false);
        sharpenButton.setActionCommand("Sharpen Button");

        // create and add the grayscale button to the border line start
        grayscaleButton = new JButton("Grayscale");
        grayscaleButton.setVisible(false);
        grayscaleButton.setActionCommand("Grayscale Button");

        // create and add the sepia button to the border line start
        sepiaButton = new JButton("Sepia");
        sepiaButton.setVisible(false);
        sepiaButton.setActionCommand("Sepia Button");

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

        horizontalRainbowStripesMenuItem.addActionListener(l -> {
            rainbowStripesDimsPane.setVisible(true);
            setFeatureFlag("horizontalRainbowStripes");
            validate();
        });

        verticalRainbowStripesMenuItem.addActionListener(l -> {
            rainbowStripesDimsPane.setVisible(true);
            setFeatureFlag("verticalRainbowStripes");
            validate();
        });

        rainbowStripesSubmitButton.addActionListener(l -> {
            try {
                int height = Integer.parseInt(rainbowStripesHeightField.getText());
                int width = Integer.parseInt(rainbowStripesWidthField.getText());
                if (getFeatureFlag().equals("horizontalRainbowStripes")) {
                    System.out.println("image height: " + height + " image width: " + width);
                    f.drawHorizontalRainbowStripes(height, width);
                } else if (getFeatureFlag().equals("verticalRainbowStripes")) {
                    f.drawVerticalRainbowStripes(height, width);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });

        blurButton.addActionListener(l -> f.blur());
        sharpenButton.addActionListener(l -> f.sharpen());
        grayscaleButton.addActionListener(l -> f.grayscale());
        sepiaButton.addActionListener(l -> f.sepia());

        openFileMenuItem.addActionListener(l -> {
            try {
                f.load(chooseFile(OPEN));
                makeButtonsVisible(true);
            } catch (HeadlessException | IOException e) {
                e.printStackTrace();
            }
        });

        saveFileMenuItem.addActionListener(l -> {
            try {
                f.save(chooseFile(SAVE));
            } catch (HeadlessException | IOException e) {
                e.printStackTrace();
            }
        });

        // exit program is tied to the exit button
        quitFileMenuItem.addActionListener(l -> f.exitProgram());

    }

    private String getFeatureFlag() {
        return this.featureFlag;
    }

    private void setFeatureFlag(String feature) {
        this.featureFlag = feature;
    }

    private int getImageWidth() {
        return this.imageWidth;
    }

    private int getImageHeight() {
        return this.imageHeight;
    }

    private void setImageWidth(int width) {
        this.imageWidth = width;
    }

    private void setImageHeight(int height) {
        this.imageHeight = height;
    }

    private String chooseFile(String chooserType) {
        // create and add a load file chooser
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
        Path path = Path.of("C:/Users/Peter/mygitworkspace/git-repos/mvcPractice/images/");
        File currentDirectory = path.toFile();
        chooser.setCurrentDirectory(currentDirectory);
        chooser.setFileFilter(filter);

        if (chooserType.equals(SAVE)) {
            int returnVal = chooser.showSaveDialog(saveFileMenuItem);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to save this file: " + chooser.getSelectedFile().getAbsolutePath());
            }
        } else {
            int returnVal = chooser.showOpenDialog(openFileMenuItem);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
            }
        }
        return chooser.getSelectedFile().getAbsolutePath();
    }

    /**
     * Updates the display with an ImageIcon, or null if the path was invalid.
     * 
     * @param path
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
