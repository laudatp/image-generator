package image.view;

import java.awt.BorderLayout;
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
import javax.swing.JToolBar;
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
    private JMenuItem horizontal;
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

        // create and add horizontal menu item to rainbow stripes submenu
        horizontal = new JMenuItem("Horizontal");
        horizontal.setActionCommand("Horizontal Menu Item");
        rainbowStripes.add(horizontal);

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

        // create and add the blur button to the left hand borde
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

        horizontal.addActionListener(l -> f.drawHorizontalRainbowStripes(imageHeight, imageWidth););
        blurButton.addActionListener(l -> f.blur());
        sharpenButton.addActionListener(l -> f.sharpen());
        grayscaleButton.addActionListener(l -> f.grayscale());
        sepiaButton.addActionListener(l -> f.sepia());

        openFileMenuItem.addActionListener(l -> {
            try {
                f.load(chooseFile(OPEN));
                makeButtonsVisible(true);
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
