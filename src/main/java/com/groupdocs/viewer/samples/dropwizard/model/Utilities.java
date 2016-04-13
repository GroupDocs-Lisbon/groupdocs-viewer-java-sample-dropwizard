package com.groupdocs.viewer.samples.dropwizard.model;

import com.groupdocs.viewer.converter.options.HtmlOptions;
import com.groupdocs.viewer.converter.options.ImageOptions;
import com.groupdocs.viewer.domain.Watermark;
import com.groupdocs.viewer.domain.WatermarkPosition;
import com.groupdocs.viewer.domain.options.ReorderPageOptions;
import com.groupdocs.viewer.domain.options.RotatePageOptions;
import com.groupdocs.viewer.handler.ViewerHandler;
import com.groupdocs.viewer.licensing.License;
import com.groupdocs.viewer.samples.dropwizard.config.DropwizardConfig;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author Aleksey Permyakov (12.04.2016).
 */
public class Utilities {

    private static List<String> EXT_ARR = Arrays.asList("doc", "docx", "xls", "xlsx", "pdf", "ppt", "pptx", "html", "xml", "bmp", "jpg", "gif");


    public static boolean checkExtenstion(String ext) {
        return EXT_ARR.contains(ext);
    }

    public static String getUploadPath(DropwizardConfig dropwizardConfig) {
        return dropwizardConfig.getStoragePath() + "/";
    }

    public static class PageTransformations {
        /// <summary>
        /// Rotate a Page before rendering
        /// </summary>
        /// <param name="options"></param>
        /// <param name="angle"></param>

        public static void rotatePages(ViewerHandler handler, String guid, int pageNumber, int angle) throws Exception {
            //ExStart:rotationAngle
            // Set the property of handler's rotate Page
            handler.rotatePage(new RotatePageOptions(guid, pageNumber, angle));
            //ExEnd:rotationAngle
        }

        /// <summary>
        /// Reorder a page before rendering
        /// </summary>
        /// <param name="viewerHandler">Base class of handlers</param>
        /// <param name="guid">File name</param>
        /// <param name="currentPageNumber">Existing number of page</param>
        /// <param name="newPageNumber">New number of page</param>
        public static void reorderPage(ViewerHandler viewerHandler, String guid, int currentPageNumber, int newPageNumber) throws Exception {
            //ExStart:reorderPage
            //Initialize the ReorderPageOptions object by passing guid as document name, current Page Number, new page number
            ReorderPageOptions options = new ReorderPageOptions(guid, currentPageNumber, newPageNumber);
            // call ViewerHandler's Reorder page function by passing initialized ReorderPageOptions object.
            viewerHandler.reorderPage(options);
            //ExEnd:reorderPage
        }

        /// <summary>
        /// add a watermark text to all rendered images.
        /// </summary>
        /// <param name="options">HtmlOptions by reference</param>
        /// <param name="text">Watermark text</param>
        /// <param name="color">System.Drawing.Color</param>
        /// <param name="position"></param>
        /// <param name="width"></param>
        public static void addWatermark(ImageOptions options, String text, Color color, WatermarkPosition position, float width) {
            //ExStart:AddWatermark
            //Initialize watermark object by passing the text to display.
            Watermark watermark = new Watermark(text);
            //Apply the watermark color by assigning System.Drawing.Color.
            watermark.setColor(color);
            //Set the watermark's position by assigning an enum WatermarkPosition's value.
            watermark.setPosition(position);
            //set an integer value as watermark width
            watermark.setWidth(width);
            //Assign intialized and populated watermark object to ImageOptions or HtmlOptions objects
            options.setWatermark(watermark);
            //ExEnd:AddWatermark
        }

        /// <summary>
        /// add a watermark text to all rendered Html pages.
        /// </summary>
        /// <param name="options">HtmlOptions by reference</param>
        /// <param name="text">Watermark text</param>
        /// <param name="color">System.Drawing.Color</param>
        /// <param name="position"></param>
        /// <param name="width"></param>
        public static void addWatermark(HtmlOptions options, String text, Color color, WatermarkPosition position, float width) {

            Watermark watermark = new Watermark(text);
            watermark.setColor(color);
            watermark.setPosition(position);
            watermark.setWidth(width);
            options.setWatermark(watermark);
        }

    }


    /// <summary>
    /// Set product's license
    /// </summary>
    public static void applyLicense(String licensePath) {
        License lic = new License();
        lic.setLicense(licensePath);
    }

    /// <summary>
    /// Save file in html form
    /// </summary>
    /// <param name="filename">Save as provided string</param>
    /// <param name="content">Html contents in String form</param>
    public static void saveAsHtml(String filename, String content) {
        try {
            //ExStart:SaveAsHTML
            // set an html file name with absolute path
            // String fname = Path.Combine(Path.GetFullPath(OutputHtmlPath), Path.GetFileNameWithoutExtension(filename) + ".html");

            // create a file at the disk
            // System.IO.File.WriteAllText(fname, content);
            //ExEnd:SaveAsHTML
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /// <summary>
    /// Save the rendered images at disk
    /// </summary>
    /// <param name="imageName">Save as provided string</param>
    /// <param name="imageContent">stream of image contents</param>
    public static void saveAsImage(String path, String imageName, InputStream imageContent) {
        try {
            //ExStart:SaveAsImage
            // extract the image from stream
            BufferedImage img = ImageIO.read(imageContent);
            //save the image in the form of jpeg
            ImageIO.write(img, "jpg", Utilities.makeImagePath(path, imageName));
            //ExEnd:SaveAsImage
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static File makeImagePath(String path, String imageName) {
        final File directory = new File(path + "\\images\\");
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("Can't create directory for images! " + directory.getAbsolutePath());
        }
        return new File(directory.getAbsolutePath() + File.separator + FilenameUtils.getBaseName(imageName) + ".jpg");
    }

    /// <summary>
    /// Save file in any format
    /// </summary>
    /// <param name="filename">Save as provided string</param>
    /// <param name="content">Stream as content of a file</param>
    public static void saveFile(String filename, InputStream content) {
        try {
            //ExStart:SaveAnyFile
            //Create file stream
            //FileStream fileStream = File.Create(Path.Combine(Path.GetFullPath(OutputPath), filename), (int)content.Length);

            // Initialize the bytes array with the stream length and then fill it with data
            //byte[] bytesInStream = new byte[content.Length];
            //content.Read(bytesInStream, 0, bytesInStream.Length);

            // Use write method to write to the file specified above
            //fileStream.Write(bytesInStream, 0, bytesInStream.Length);
            //ExEnd:SaveAnyFile
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
