/**
 * 
 */
package image.controller;

import java.io.IOException;
import java.util.Scanner;

import image.model.Image;

/**
 * @author Peter
 *
 */
public class ImageControllerImpl implements ImageController {

    /** The input source. */
    private final Readable in;

    public ImageControllerImpl(Readable in) {
	this.in = in;
    }

    @Override
    public void go(Image image) throws IOException {
	String filename = "";
	String filenameExtension = ".jpg";
	String line = "";
	String imageCommand = "";
	int imageHeight = 0;
	int imageWidth = 0;
	int cellWidth = 0;
	try (Scanner scannedIn = new Scanner(this.in)) {
	    while (scannedIn.hasNextLine()) {
		// read a line
		line = scannedIn.nextLine().toLowerCase();
		try (Scanner tokens = new Scanner(line)) {
		    while (tokens.hasNext()) {
			// parse the line into words
			imageCommand = tokens.next();
			switch (imageCommand) {
			case "load":
			    filename = tokens.next() + filenameExtension;
			    image.load(filename);
			    break;
			case "save":
			    filename = tokens.next() + filenameExtension;
			    image.save(filename);
			    break;
			case "drawhorizontalrainbowstripes":
			    imageHeight = tokens.nextInt();
			    imageWidth = tokens.nextInt();
			    image.drawHorizontalRainbowStripes(imageHeight, imageWidth);
			    break;
			case "drawverticalrainbowstripes":
			    if (tokens.hasNextInt()) {
				imageHeight = tokens.nextInt();
			    }
			    if (tokens.hasNextInt()) {
				imageWidth = tokens.nextInt();
			    }
			    image.drawVerticalRainbowStripes(imageHeight, imageWidth);
			    break;
			case "drawcheckerboard":
			    if (tokens.hasNextInt()) {
				cellWidth = tokens.nextInt();
			    }
			    image.drawCheckerBoard(cellWidth);
			    break;
			case "drawfranceflag":
			    if (tokens.hasNextInt()) {
				imageWidth = tokens.nextInt();
			    }
			    image.drawFranceFlag(imageWidth);
			    break;
			case "drawswitzerlandflag":
			    if (tokens.hasNextInt()) {
				imageWidth = tokens.nextInt();
			    }
			    image.drawSwitzerlandFlag(imageWidth);
			    break;
			case "drawgreeceflag":
			    if (tokens.hasNextInt()) {
				imageWidth = tokens.nextInt();
			    }
			    image.drawGreeceFlag(imageWidth);
			    break;
			case "blur":
			    image.blur();
			    break;
			case "sharpen":
			    image.sharpen();
			    break;
			case "grayscale":
			    image.grayscale();
			    break;
			case "sepia":
			    image.sepia();
			    break;
			case "quit":
			    System.out.println("Quitting program");
			    System.exit(0);
			    break;
			default:
			    throw new IllegalArgumentException("An illegal command string was provided");
			}
		    }
		}
	    }
	}
    }

}
