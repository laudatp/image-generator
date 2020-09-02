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
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import image.controller.Features;

public class ViewImpl extends JFrame implements View {
    /**
     * System generated serial id.
     */
    private static final long serialVersionUID = -7293519515508019533L;

    /** Signal that file chooser should open file. */
    private static final String OPEN = "OPEN";
    /** Signal that chooser should save file. */
    private static final String SAVE = "SAVE";

    /** String for the height label.. */
    private static String heightString = "Height (200 - 2000): ";
    /** String for the width label. */
    private static String widthString = "Width (300 - 3000): ";

    /** Image height. */
    private int imageHeight;
    /** Image width. */
    private int imageWidth;

    /** Label to identify the height field. */
    private JLabel horizontalRainbowStripesHeightLabel;
    /** Label to identify the width field. */
    private JLabel horizontalRainbowStripesWidthLabel;

    /** Height field. */
    private JTextField horizontalRainbowStripesHeightField;
    /** Width field. */
    private JTextField horizontalRainbowStripesWidthField;
    /** Panel. */
    private JLayeredPane layeredContentPane;
    /** Panel border. */
    private Border border;
    /** Menu bar. */
    private JMenuBar appMenuBar;
    /** Menu. */
    private JMenu fileMenu;
    /** New file submenu. */
    private JMenu newSubmenu;
    /** Rainbow stripes submenu. */
    private JMenu rainbowStripes;
    /** New horizontal rainbow stripes image file menu item. */
    private JMenuItem horizontalRainbowStripesMenuItem;
    /** New vertical rainbow stripes image file menu item. */
    private JMenuItem verticalRainbowStripesMenuItem;
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
    /**
     * Button toolbar for image filtering buttons (blur and sharpen) and for image transformation
     * buttons (grayscale and sepia).
     */
    private JToolBar filterAndTransformToolbar;

    /** Panel containing height and width labels. */
    private JPanel horizontalRainbowStripesDimsPane;

    /** Panel containing height and width labels. */
    private JPanel verticalRainbowStripesDimsPane;

    /** Panel containing labels. */
    private JPanel horizontalRainbowStripesLabelPane;

    /** Panel containing fields. */
    private JPanel horizontalRainbowStripesFieldPane;

    /** Horizontal rainbow stripes image submit button. */
    private JButton horizontalRainbowStripesSubmitButton;

    /** Vertical rainbow stripes image submit button. */
    private JButton verticalRainbowStripesSubmitButton;

    /** Horizontal rainbow stripes image submit button panel. */
    private JPanel horizontalRainbowStripesSubmitPane;

    /** Vertical rainbow stripes image submit button panel. */
    private JPanel verticalRainbowStripesSubmitPane;

    /** Vertical rainbow stripes image height label. */
    private JLabel verticalRainbowStripesHeightLabel;

    /** Vertical rainbow stripes image width label. */
    private JLabel verticalRainbowStripesWidthLabel;

    /** Vertical rainbow stripes image height field. */
    private JTextField verticalRainbowStripesHeightField;

    /** Vertical rainbow stripes image width field. */
    private JTextField verticalRainbowStripesWidthField;

    /** Vertical rainbow stripes image height label. */
    private JPanel verticalRainbowStripesLabelPane;

    /** Vertical rainbow stripes image field pane. */
    private JPanel verticalRainbowStripesFieldPane;

    /** Flag used to tell button which feature to execute. */
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

        // Create the horizontal rainbow stripes image height and width labels.
        horizontalRainbowStripesHeightLabel = new JLabel(heightString);
        horizontalRainbowStripesWidthLabel = new JLabel(widthString);

        // Create the horizontal rainbow stripes height and width text fields and set them up
        horizontalRainbowStripesHeightField = new JTextField(10);
        horizontalRainbowStripesHeightField.setActionCommand("Height Field");

        horizontalRainbowStripesWidthField = new JTextField(10);
        horizontalRainbowStripesWidthField.setActionCommand("Width Field");

        // Tell accessibility tools about horizontal rainbow stripes label/textfield pairs
        horizontalRainbowStripesHeightLabel.setLabelFor(horizontalRainbowStripesHeightField);
        horizontalRainbowStripesWidthLabel.setLabelFor(horizontalRainbowStripesWidthField);

        // Lay out the horizontal rainbow stripes labels in a panel
        horizontalRainbowStripesLabelPane = new JPanel(new GridLayout(0, 1));

        horizontalRainbowStripesLabelPane.add(horizontalRainbowStripesHeightLabel);
        horizontalRainbowStripesLabelPane.add(horizontalRainbowStripesHeightField);

        // Lay out the horizontal rainbow stripes text fields in a panel
        horizontalRainbowStripesFieldPane = new JPanel(new GridLayout(0, 1));
        horizontalRainbowStripesFieldPane.add(horizontalRainbowStripesWidthLabel);
        horizontalRainbowStripesFieldPane.add(horizontalRainbowStripesWidthField);

        // Layout out the horizontal rainbow stripes submit button in horizontal rainbow stripes panel
        horizontalRainbowStripesSubmitPane = new JPanel(new FlowLayout());
        horizontalRainbowStripesSubmitButton = new JButton("Submit");
        horizontalRainbowStripesSubmitButton.setActionCommand("Submit Horizontal Rainbow Stripes Image Dimensions");
        horizontalRainbowStripesSubmitPane.add(horizontalRainbowStripesSubmitButton);

        // Lay out the horizontal rainbow stripes labels and text field panels in a single horizontal
        // rainbow stripes panel.
        horizontalRainbowStripesDimsPane = new JPanel(new FlowLayout());
        horizontalRainbowStripesDimsPane.add(horizontalRainbowStripesLabelPane);
        horizontalRainbowStripesDimsPane.add(horizontalRainbowStripesFieldPane);
        horizontalRainbowStripesDimsPane.add(horizontalRainbowStripesSubmitPane);

        // Create the vertical rainbow stripes image height and width labels.
        verticalRainbowStripesHeightLabel = new JLabel(heightString);
        verticalRainbowStripesWidthLabel = new JLabel(widthString);

        // Create the vertical rainbow stripes height and width text fields and set them up
        verticalRainbowStripesHeightField = new JTextField(10);
        verticalRainbowStripesHeightField.setActionCommand("Height Field");

        verticalRainbowStripesWidthField = new JTextField(10);
        verticalRainbowStripesWidthField.setActionCommand("Width Field");

        // Tell accessibility tools about vertical rainbow stripes label/textfield pairs
        verticalRainbowStripesHeightLabel.setLabelFor(verticalRainbowStripesHeightField);
        verticalRainbowStripesWidthLabel.setLabelFor(verticalRainbowStripesWidthField);

        // Lay out the vertical rainbow stripes labels in a panel
        verticalRainbowStripesLabelPane = new JPanel(new GridLayout(0, 1));
        verticalRainbowStripesLabelPane.add(verticalRainbowStripesHeightLabel);
        verticalRainbowStripesLabelPane.add(verticalRainbowStripesHeightField);

        // Lay out the vertical rainbow stripes text fields in a panel
        verticalRainbowStripesFieldPane = new JPanel(new GridLayout(0, 1));
        verticalRainbowStripesFieldPane.add(verticalRainbowStripesWidthLabel);
        verticalRainbowStripesFieldPane.add(verticalRainbowStripesWidthField);

        // Layout out the vertical rainbow stripes submit button in vertical rainbow stripes panel
        verticalRainbowStripesSubmitPane = new JPanel(new FlowLayout());
        verticalRainbowStripesSubmitButton = new JButton("Submit");
        verticalRainbowStripesSubmitButton.setActionCommand("Submit Vertical Rainbow Stripes Image Dimensions");
        verticalRainbowStripesSubmitPane.add(verticalRainbowStripesSubmitButton);

        // Lay out the vertical rainbow stripes labels and text field panels in a single vertical rainbow
        // stripes panel.
        verticalRainbowStripesDimsPane = new JPanel(new FlowLayout());
        verticalRainbowStripesDimsPane.add(verticalRainbowStripesLabelPane);
        verticalRainbowStripesDimsPane.add(verticalRainbowStripesFieldPane);
        verticalRainbowStripesDimsPane.add(verticalRainbowStripesSubmitPane);

        // application menu bar
        appMenuBar = new JMenuBar();

        // File menu
        fileMenu = new JMenu("Image");

        // create and add New file menu item to File menu
        newSubmenu = new JMenu("New");
        fileMenu.add(newSubmenu);

        // create and add rainbow stripes submenu new submenu
        rainbowStripes = new JMenu("Rainbow Stripes");
        newSubmenu.add(rainbowStripes);

        // create and add horizontal rainbow stripes menu item to rainbow stripes submenu
        horizontalRainbowStripesMenuItem = new JMenuItem("Horizontal");
        horizontalRainbowStripesMenuItem.setActionCommand("Horizontal Rainbow Stripes Menu Item");
        rainbowStripes.add(horizontalRainbowStripesMenuItem);

        // create and add vertical rainbow stripes menu item to rainbow stripes submenu
        verticalRainbowStripesMenuItem = new JMenuItem("Vertical");
        verticalRainbowStripesMenuItem.setActionCommand("Vertical Rainbow Stripes Menu Item");
        rainbowStripes.add(verticalRainbowStripesMenuItem);

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

        // Create content panel that will overlay the JFrame content pane to allow the ability to take
        // advantage of JComponent features
        layeredContentPane = new JLayeredPane();
        layeredContentPane.setLayout(new BorderLayout());
        layeredContentPane.setBorder(BorderFactory.createEmptyBorder());

        // create the label which will display the image
        display = new JLabel();
        display.setHorizontalAlignment(JLabel.CENTER);
        display.setVerticalAlignment(JLabel.CENTER);
        // add the label to the center panel of the layeredContentPane border layout
        layeredContentPane.add(display, BorderLayout.CENTER);

        // make the content panel the content pane
        this.setContentPane(layeredContentPane);

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

        // create filterAndTransformToolbar and add the buttons to the filterAndTransformToolbar
        filterAndTransformToolbar = new JToolBar(JToolBar.HORIZONTAL);
        filterAndTransformToolbar.add(blurButton);
        filterAndTransformToolbar.add(sharpenButton);
        filterAndTransformToolbar.add(grayscaleButton);
        filterAndTransformToolbar.add(sepiaButton);

        // Lay out the filter and transform buttons toolbar
        layeredContentPane.add(filterAndTransformToolbar, BorderLayout.PAGE_END);
        filterAndTransformToolbar.setVisible(false);

        // Lay out the labels-and-text panel in the top row of the content panel
        layeredContentPane.add(verticalRainbowStripesDimsPane);
        layeredContentPane.add(horizontalRainbowStripesDimsPane);
        horizontalRainbowStripesDimsPane.setVisible(false);
        verticalRainbowStripesDimsPane.setVisible(false);

        this.validate();
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
            this.clearInputStrings();
            verticalRainbowStripesDimsPane.setVisible(false);
            horizontalRainbowStripesDimsPane.setVisible(true);
            this.validate();
            setFeatureFlag("horizontalRainbowStripes");
        });

        verticalRainbowStripesMenuItem.addActionListener(l -> {
            this.clearInputStrings();
            horizontalRainbowStripesDimsPane.setVisible(false);
            verticalRainbowStripesDimsPane.setVisible(true);
            this.validate();
            setFeatureFlag("verticalRainbowStripes");
        });

        horizontalRainbowStripesSubmitButton.addActionListener(l -> {
            this.setImageHeight(Integer.parseInt(horizontalRainbowStripesHeightField.getText()));
            this.setImageWidth(Integer.parseInt(horizontalRainbowStripesWidthField.getText()));
            if (this.getFeatureFlag().equals("horizontalRainbowStripes")) {
                f.drawHorizontalRainbowStripes(this.getImageHeight(), this.getImageWidth());
            }
            this.validate();
        });

        verticalRainbowStripesSubmitButton.addActionListener(l -> {
            this.setImageHeight(Integer.parseInt(verticalRainbowStripesHeightField.getText()));
            this.setImageWidth(Integer.parseInt(verticalRainbowStripesWidthField.getText()));
            if (this.getFeatureFlag().equals("verticalRainbowStripes")) {
                f.drawVerticalRainbowStripes(this.getImageHeight(), this.getImageWidth());
            }
            this.validate();
        });

        blurButton.addActionListener(l -> f.blur());
        sharpenButton.addActionListener(l -> f.sharpen());
        grayscaleButton.addActionListener(l -> f.grayscale());
        sepiaButton.addActionListener(l -> f.sepia());

        openFileMenuItem.addActionListener(l -> {
            try {
                f.load(chooseFile(OPEN));
                setFilterAndTransformButtonsVisibility(true);
            } catch (HeadlessException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        saveFileMenuItem.addActionListener(l -> {
            try {
                f.save(chooseFile(SAVE));
            } catch (HeadlessException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        // exit program is tied to the exit button
        quitFileMenuItem.addActionListener(l -> f.exitProgram());

    }

    private Object getFeatureFlag() {
        return this.featureFlag;
    }

    private void setFeatureFlag(String featureFlag) {
        this.featureFlag = featureFlag;

    }

    private void clearInputStrings() {
        horizontalRainbowStripesHeightField.setText("");
        horizontalRainbowStripesWidthField.setText("");
        verticalRainbowStripesHeightField.setText("");
        verticalRainbowStripesWidthField.setText("");
        this.validate();
    }

    /**
     * Set filter and transform buttons toolbar visibility.
     * 
     * @param b set visible if true, otherwise invisible
     */
    private void setFilterAndTransformButtonsVisibility(boolean b) {
        filterAndTransformToolbar.setVisible(b);
        blurButton.setVisible(b);
        sharpenButton.setVisible(b);
        grayscaleButton.setVisible(b);
        sepiaButton.setVisible(b);
        this.validate();
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
            this.validate();
            display.setVisible(true);
        } else {
            System.err.println("Couldn't find file: " + path);
        }
    }

    @Override
    public void updateDisplay(Image image) {
        ImageIcon imageIcon = new ImageIcon(image);
        display.setIcon(imageIcon);
        this.validate();
        display.repaint();
    }

    public void resetFocus() {
        setFocusable(true);
        requestFocus();
    }

    private void setVisibility(JComponent component, boolean v) {
        component.setVisible(v);
        component.validate();
        this.validate();
    }

    /**
     * Return image height.
     * 
     * @return imageHeight
     */
    @Override
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * Set image height.
     * 
     * @param height
     * 
     */
    @Override
    public void setImageHeight(int height) {
        this.imageHeight = height;
    }

    /**
     * Return image width.
     * 
     * @return the width
     */
    @Override
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * Set image width.
     * 
     * @param width width
     */
    public void setImageWidth(int width) {
        this.imageWidth = width;
    }

}
