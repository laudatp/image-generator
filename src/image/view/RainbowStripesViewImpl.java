package image.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RainbowStripesViewImpl extends JFrame {
    /** System generated serial id. */
    private static final long serialVersionUID = -7293519515508019533L;
    /** String for height label. */
    private static final String HEIGHT_STRING = "Height (pixels)";
    /** String for width label. */
    private static final String WIDTH_STRING = "Width (pixels)";
    /** New file submenu. */
    private JMenu newSubmenu;
    /** Rainbow stripes submenu. */
    private JMenu rainbowStripes;
    /** New image file menu item. */
    private JMenuItem horizontalRainbowStripesMenuItem;
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
    /** Vertical rainbow stripes menu item. */
    private JMenuItem verticalRainbowStripesMenuItem;

    /** Enable multiple uses for same button by tracking user-selected feature. */

    public RainbowStripesViewImpl() {
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
    }

}
