package com.groupdocs.viewer.samples.dropwizard.model;

import com.groupdocs.viewer.converter.options.HtmlOptions;
import com.groupdocs.viewer.converter.options.ImageOptions;
import com.groupdocs.viewer.domain.FileDescription;
import com.groupdocs.viewer.domain.Transformation;
import com.groupdocs.viewer.domain.WatermarkPosition;
import com.groupdocs.viewer.domain.containers.FileContainer;
import com.groupdocs.viewer.domain.containers.FileTreeContainer;
import com.groupdocs.viewer.domain.html.PageHtml;
import com.groupdocs.viewer.domain.image.PageImage;
import com.groupdocs.viewer.domain.options.FileTreeOptions;
import com.groupdocs.viewer.domain.options.PdfFileOptions;
import com.groupdocs.viewer.handler.ViewerHandler;
import com.groupdocs.viewer.handler.ViewerHtmlHandler;
import com.groupdocs.viewer.handler.ViewerImageHandler;
import com.groupdocs.viewer.samples.dropwizard.model.business.HtmlInfo;
import com.groupdocs.viewer.samples.dropwizard.model.business.ImageInfo;
import org.apache.commons.io.FilenameUtils;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Aleksey Permyakov (12.04.2016).
 */
public class ViewGenerator {

    private static com.groupdocs.viewer.config.ViewerConfig config;

    public static void initGenerator(com.groupdocs.viewer.config.ViewerConfig viewerConfig) {
        ViewGenerator.config = viewerConfig;
    }

    /// <summary>
    /// Render simple document in html representation
    /// </summary>
    /// <param name="documentName">File name</param>
    /// <param name="DocumentPassword">Optional</param>
    public static List<HtmlInfo> renderDocumentAsHtml(String documentName, String DocumentPassword) throws Exception {

        // Create html handler
        ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

        //Instantiate the HtmlOptions object
        HtmlOptions options = new HtmlOptions();

        //to get html representations of pages with embedded resources
        options.setResourcesEmbedded(true);

        // Set password if document is password protected.
        if (DocumentPassword != null && !DocumentPassword.isEmpty()) {
            options.setPassword(DocumentPassword);
        }

        //Get document pages in html form
        List<PageHtml> pages = htmlHandler.getPages(documentName, options);
        List<HtmlInfo> contents = new ArrayList<HtmlInfo>();

        for (PageHtml page : pages) {
            HtmlInfo htmlInfo = new HtmlInfo();
            htmlInfo.setHtmlContent(page.getHtmlContent());
            htmlInfo.setPageNmber(page.getPageNumber());
            contents.add(htmlInfo);
        }

        return contents;
        //ExEnd:RenderAsHtml
    }

    /// <summary>
    /// Render document in html representation with watermark
    /// </summary>
    /// <param name="DocumentName">file/document name</param>
    /// <param name="WatermarkText">watermark text</param>
    /// <param name="WatermarkColor"> System.Drawing.Color</param>
    /// <param name="position">Watermark Position is optional parameter. Default value is WatermarkPosition.Diagonal</param>
    /// <param name="WatermarkWidth"> width of watermark as integer. it is optional Parameter default value is 100</param>
    /// <param name="DocumentPassword">Password Parameter is optional</param>
    public static List<HtmlInfo> renderDocumentAsHtml(String DocumentName, String WatermarkText, Color WatermarkColor, int WatermarkWidth, String DocumentPassword) throws Exception {
        // Create html handler
        ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

        // Guid implies that unique document name
        String guid = DocumentName;

        //Instantiate the HtmlOptions object
        HtmlOptions options = new HtmlOptions();

        options.setResourcesEmbedded(false);
        // Set password if document is password protected.
        if (DocumentPassword != null && !DocumentPassword.isEmpty()) {
            options.setPassword(DocumentPassword);
        }

        // Call AddWatermark and pass the reference of HtmlOptions object as 1st parameter
        Utilities.PageTransformations.addWatermark(options, WatermarkText, WatermarkColor, WatermarkPosition.Diagonal, WatermarkWidth);

        //Get document pages in html form
        List<PageHtml> pages = htmlHandler.getPages(guid, options);

        List<HtmlInfo> contents = new ArrayList<HtmlInfo>();

        for (PageHtml page : pages) {
            HtmlInfo htmlInfo = new HtmlInfo();
            htmlInfo.setHtmlContent(page.getHtmlContent());
            htmlInfo.setPageNmber(page.getPageNumber());
            contents.add(htmlInfo);
        }

        return contents;
        //ExEnd:RenderAsHtmlWithWaterMark
    }

    public static List<HtmlInfo> rotateDocumentAsHtml(String DocumentName, int pageNumber, int RotationAngle, String DocumentPassword) throws Exception {
        // Create image handler
        ViewerHandler handler = new ViewerHtmlHandler(config);

        // Guid implies that unique document name
        String guid = DocumentName;

        //Initialize ImageOptions Object and setting Rotate Transformation
        HtmlOptions options = new HtmlOptions();
        options.setTransformations(Transformation.Rotate);

        // Set password if document is password protected.
        if (DocumentPassword != null && !DocumentPassword.isEmpty())
            options.setPassword(DocumentPassword);

        //Call RotatePages to apply rotate transformation to a page
        Utilities.PageTransformations.rotatePages(handler, guid, pageNumber, RotationAngle);

        //down cast the handler(ViewerHandler) to viewerHtmlHandler
        ViewerHtmlHandler htmlHandler = (ViewerHtmlHandler) handler;

        //Get document pages in image form
        List<PageHtml> pages = htmlHandler.getPages(guid, options);

        List<HtmlInfo> contents = new ArrayList<HtmlInfo>();

        for (PageHtml page : pages) {
            HtmlInfo htmlInfo = new HtmlInfo();
            htmlInfo.setHtmlContent(page.getHtmlContent());
            htmlInfo.setPageNmber(page.getPageNumber());
            contents.add(htmlInfo);
        }

        return contents;
        //ExEnd:RenderAsImageWithRotationTransformation
    }

    /// <summary>
    ///  document in html representation and reorder a page
    /// </summary>
    /// <param name="DocumentName">file/document name</param>
    /// <param name="CurrentPageNumber">Page existing order number</param>
    /// <param name="NewPageNumber">Page new order number</param>
    /// <param name="DocumentPassword">Password Parameter is optional</param>
    public static List<HtmlInfo> renderDocumentAsHtml(String DocumentName, int CurrentPageNumber, int NewPageNumber, String DocumentPassword) throws Exception {
        // Cast ViewerHtmlHandler class object to its base class(ViewerHandler).
        ViewerHandler handler = new ViewerHtmlHandler(config);

        // Guid implies that unique document name
        String guid = DocumentName;

        //Instantiate the HtmlOptions object with setting of Reorder Transformation
        HtmlOptions options = new HtmlOptions();
        options.setTransformations(Transformation.Reorder);

        //to get html representations of pages with embedded resources
        options.setResourcesEmbedded(true);

        // Set password if document is password protected.
        if (DocumentPassword != null && !DocumentPassword.isEmpty()) {
            options.setPassword(DocumentPassword);
        }

        //Call ReorderPage and pass the reference of ViewerHandler's class  parameter by reference.
        Utilities.PageTransformations.reorderPage(handler, guid, CurrentPageNumber, NewPageNumber);

        //down cast the handler(ViewerHandler) to viewerHtmlHandler
        ViewerHtmlHandler htmlHandler = (ViewerHtmlHandler) handler;

        //Get document pages in html form
        List<PageHtml> pages = htmlHandler.getPages(guid, options);

        List<HtmlInfo> contents = new ArrayList<HtmlInfo>();

        for (PageHtml page : pages) {
            HtmlInfo htmlInfo = new HtmlInfo();
            htmlInfo.setHtmlContent(page.getHtmlContent());
            htmlInfo.setPageNmber(page.getPageNumber());
            contents.add(htmlInfo);
        }
        return contents;
        //ExEnd:RenderAsHtmlAndReorderPage
    }

    /// <summary>
    /// Render a document in html representation whom located at web/remote location.
    /// </summary>
    /// <param name="DocumentURL">URL of the document</param>
    /// <param name="DocumentPassword">Password Parameter is optional</param>
    public static void renderDocumentAsHtml(URI DocumentURL, String DocumentPassword) throws Exception {
        //ExStart:RenderRemoteDocAsHtml
        //Get Configurations


        // Create html handler
        ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

        //Instantiate the HtmlOptions object
        HtmlOptions options = new HtmlOptions();

        if (DocumentPassword != null && !DocumentPassword.isEmpty()) {
            options.setPassword(DocumentPassword);
        }

        //Get document pages in html form
        List<PageHtml> pages = htmlHandler.getPages(DocumentURL, options);

        for (PageHtml page : pages) {
            //Save each page at disk
            Utilities.saveAsHtml(page.getPageNumber() + "_" + FilenameUtils.getName(DocumentURL.getPath()), page.getHtmlContent());
        }
        //ExEnd:RenderRemoteDocAsHtml
    }

    /// <summary>
    /// Render simple document in image representation
    /// </summary>
    /// <param name="documentName">File name</param>
    /// <param name="DocumentPassword">Optional</param>
    public static List<ImageInfo> renderDocumentAsImages(String documentName, String DocumentPassword) {
        //ExStart:RenderAsImage
        //Get Configurations


        // Create image handler
        ViewerImageHandler imageHandler = new ViewerImageHandler(config);

        // Guid implies that unique document name
        String guid = documentName;

        //Initialize ImageOptions Object
        ImageOptions options = new ImageOptions();

        // Set password if document is password protected.
        if (DocumentPassword != null && !DocumentPassword.isEmpty())
            options.setPassword(DocumentPassword);

        //Get document pages in image form
        List<PageImage> Images = imageHandler.getPages(guid, options);

        List<ImageInfo> contents = new ArrayList<ImageInfo>();

        for (PageImage image : Images) {
            String imgname = image.getPageNumber() + "_" + FilenameUtils.getName(documentName);
            imgname = imgname.replace("\\s+", "_");

            Utilities.saveAsImage(documentName, imgname, image.getStream());

            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setImageUrl("/Uploads/images/" + imgname + ".jpg?" + UUID.randomUUID().toString());
            imageInfo.setPageNmber(image.getPageNumber());
            imageInfo.setHtmlContent("<div class='image_page'><img src='" + imageInfo.getImageUrl() + "' /></div>");
            contents.add(imageInfo);
        }

        return contents;
        //ExEnd:RenderAsImage

    }

    /// <summary>
    /// Render document in image representation with watermark
    /// </summary>
    /// <param name="DocumentName">file/document name</param>
    /// <param name="WatermarkText">watermark text</param>
    /// <param name="WatermarkColor"> System.Drawing.Color</param>
    /// <param name="position">Watermark Position is optional parameter. Default value is WatermarkPosition.Diagonal</param>
    /// <param name="WatermarkWidth"> width of watermark as integer. it is optional Parameter default value is 100</param>
    /// <param name="DocumentPassword">Password Parameter is optional</param>
    public static List<ImageInfo> renderDocumentAsImages(String DocumentName, String WatermarkText, Color WatermarkColor, int WatermarkWidth, String DocumentPassword) {
        //ExStart:RenderAsImageWithWaterMark
        //Get Configurations


        // Create image handler
        ViewerImageHandler imageHandler = new ViewerImageHandler(config);

        // Guid implies that unique document name
        String guid = DocumentName;

        //Initialize ImageOptions Object
        ImageOptions options = new ImageOptions();

        // Set password if document is password protected.
        if (DocumentPassword != null && !DocumentPassword.isEmpty())
            options.setPassword(DocumentPassword);

        // Call AddWatermark and pass the reference of ImageOptions object as 1st parameter
        Utilities.PageTransformations.addWatermark(options, WatermarkText, WatermarkColor, WatermarkPosition.Diagonal, WatermarkWidth);

        //Get document pages in image form
        List<PageImage> Images = imageHandler.getPages(guid, options);

        List<ImageInfo> contents = new ArrayList<ImageInfo>();

        for (PageImage image : Images) {
            String imgname = image.getPageNumber() + "_" + FilenameUtils.getName(DocumentName);
            imgname = imgname.replace("\\s+", "_");

            Utilities.saveAsImage(new File(DocumentName).getParentFile().getAbsolutePath(), imgname, image.getStream());

            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setImageUrl("/Uploads/images/" + imgname + ".jpg?" + UUID.randomUUID().toString());
            imageInfo.setPageNmber(image.getPageNumber());
            imageInfo.setHtmlContent("<div class='image_page'><img src='" + imageInfo.getImageUrl() + "' /></div>");
            contents.add(imageInfo);
        }

        return contents;
        //ExEnd:RenderAsImageWithWaterMark
    }

    /// <summary>
    /// Render the document in image form and set the rotation angle to rotate the page while display.
    /// </summary>
    /// <param name="DocumentName"></param>
    /// <param name="RotationAngle">rotation angle in digits</param>
    /// <param name="DocumentPassword"></param>
    public static List<ImageInfo> rotateDocumentAsImages(String DocumentName, int pageNumber, int RotationAngle, String DocumentPassword) throws Exception {
        //ExStart:RenderAsImageWithRotationTransformation
        //Get Configurations


        // Create image handler
        ViewerHandler handler = new ViewerImageHandler(config);

        // Guid implies that unique document name
        String guid = DocumentName;

        //Initialize ImageOptions Object and setting Rotate Transformation
        ImageOptions options = new ImageOptions();
        options.setTransformations(Transformation.Rotate);

        // Set password if document is password protected.
        if (DocumentPassword != null && !DocumentPassword.isEmpty())
            options.setPassword(DocumentPassword);

        //Call RotatePages to apply rotate transformation to a page
        Utilities.PageTransformations.rotatePages(handler, guid, pageNumber, RotationAngle);

        //down cast the handler(ViewerHandler) to viewerHtmlHandler
        ViewerImageHandler imageHandler = (ViewerImageHandler) handler;

        //Get document pages in image form
        List<PageImage> Images = imageHandler.getPages(guid, options);

        List<ImageInfo> contents = new ArrayList<ImageInfo>();

        for (PageImage image : Images) {
            String imgname = image.getPageNumber() + "_" + FilenameUtils.getBaseName(DocumentName);
            imgname = imgname.replace("\\s+", "_");

            Utilities.saveAsImage(new File(DocumentName).getParentFile().getAbsolutePath(), imgname, image.getStream());

            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setImageUrl("/Uploads/images/" + imgname + ".jpg?" + UUID.randomUUID().toString());
            imageInfo.setPageNmber(image.getPageNumber());
            imageInfo.setHtmlContent("<div class='image_page'><img src='" + imageInfo.getImageUrl() + "' /></div>");
            contents.add(imageInfo);
        }

        return contents;
        //ExEnd:RenderAsImageWithRotationTransformation
    }

    /// <summary>
    ///  document in image representation and reorder a page
    /// </summary>
    /// <param name="DocumentName">file/document name</param>
    /// <param name="CurrentPageNumber">Page existing order number</param>
    /// <param name="NewPageNumber">Page new order number</param>
    /// <param name="DocumentPassword">Password Parameter is optional</param>
    public static List<ImageInfo> renderDocumentAsImages(String DocumentName, int CurrentPageNumber, int NewPageNumber, String DocumentPassword) throws Exception {
        //ExStart:RenderAsImageAndReorderPage
        //Get Configurations


        // Cast ViewerHtmlHandler class object to its base class(ViewerHandler).
        ViewerHandler handler = new ViewerImageHandler(config);

        // Guid implies that unique document name
        String guid = DocumentName;

        //Initialize ImageOptions Object and setting Reorder Transformation
        ImageOptions options = new ImageOptions();
        options.setTransformations(Transformation.Reorder);

        // Set password if document is password protected.
        if (DocumentPassword != null && !DocumentPassword.isEmpty()) {
            options.setPassword(DocumentPassword);
        }

        //Call ReorderPage and pass the reference of ViewerHandler's class  parameter by reference.
        Utilities.PageTransformations.reorderPage(handler, guid, CurrentPageNumber, NewPageNumber);

        //down cast the handler(ViewerHandler) to viewerHtmlHandler
        ViewerImageHandler imageHandler = (ViewerImageHandler) handler;

        //Get document pages in image form
        List<PageImage> Images = imageHandler.getPages(guid, options);

        List<ImageInfo> contents = new ArrayList<ImageInfo>();

        for (PageImage image : Images) {
            String imgname = image.getPageNumber() + "_" + FilenameUtils.getBaseName(DocumentName);
            imgname = imgname.replace("\\s+", "_");

            Utilities.saveAsImage(new File(DocumentName).getParentFile().getAbsolutePath(), imgname, image.getStream());

            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setImageUrl("/Uploads/images/" + imgname + ".jpg?" + UUID.randomUUID().toString());
            imageInfo.setPageNmber(image.getPageNumber());
            imageInfo.setHtmlContent("<div class='image_page'><img src='" + imageInfo.getImageUrl() + "' /></div>");
            contents.add(imageInfo);
        }

        return contents;
        //ExEnd:RenderAsImageAndReorderPage
    }

    /// <summary>
    /// Render a document in image representation whom located at web/remote location.
    /// </summary>
    /// <param name="DocumentURL">URL of the document</param>
    /// <param name="DocumentPassword">Password Parameter is optional</param>
    public static void renderDocumentAsImages(URI DocumentURL, String DocumentPassword) {
        // Create image handler
        ViewerImageHandler imageHandler = new ViewerImageHandler(config);

        //Initialize ImageOptions Object
        ImageOptions options = new ImageOptions();

        // Set password if document is password protected.
        if (DocumentPassword != null && !DocumentPassword.isEmpty())
            options.setPassword(DocumentPassword);

        //Get document pages in image form
        List<PageImage> Images = imageHandler.getPages(DocumentURL, options);

        for (PageImage image : Images) {
            //Save each image at disk
            // Utilities.SaveAsImage(image.getPageNumber() + "_" + Path.GetFileName(DocumentURL.LocalPath), image.Stream);
        }
        //ExEnd:RenderRemoteDocAsImages
    }

    /// <summary>
    /// Render a document as it is (original form)
    /// </summary>
    /// <param name="DocumentName"></param>
    public static void renderDocumentAsOriginal(String DocumentName) {
        //ExStart:RenderOriginal
        // Create image handler
        ViewerImageHandler imageHandler = new ViewerImageHandler(config);

        // Guid implies that unique document name
        String guid = DocumentName;

        // Get original file
        FileContainer container = imageHandler.getFile(guid);

        //Save each image at disk
        // Utilities.SaveAsImage(DocumentName, container.Stream);
        //ExEnd:RenderOriginal

    }

    /// <summary>
    /// Render a document in PDF Form
    /// </summary>
    /// <param name="DocumentName"></param>
    public static void renderDocumentAsPDF(String DocumentName) {
        //ExStart:RenderAsPdf
        // Create/initialize image handler
        ViewerImageHandler imageHandler = new ViewerImageHandler(config);

        //Initialize PdfFileOptions object
        PdfFileOptions options = new PdfFileOptions();

        // Guid implies that unique document name
        options.setGuid(DocumentName);

        // Call GetPdfFile to get FileContainer type object which contains the stream of pdf file.
        FileContainer container = imageHandler.getPdfFile(options);

        //Change the extension of the file and assign to a String type variable filename
        String filename = FilenameUtils.getBaseName(DocumentName) + ".pdf";

        //Save each image at disk
        Utilities.saveFile(filename, container.getStream());
        //ExEnd:RenderAsPdf

    }

    /// <summary>
    /// Load directory structure as file tree
    /// </summary>
    /// <param name="Path"></param>
    public static void loadFileTree(String Path) {
        //ExStart:LoadFileTree
        // Create/initialize image handler
        ViewerImageHandler imageHandler = new ViewerImageHandler(config);

        // Load file tree list for custom path
        FileTreeOptions options = new FileTreeOptions(Path);

        // Load file tree list for ViewerConfig.StoragePath
        FileTreeContainer container = imageHandler.loadFileTree(options);

        for (FileDescription node : container.getFileTree()) {
            if (node.isDirectory()) {
                System.out.println(String.format("Guid: %s | Name: %s | LastModificationDate: %s",
                        node.getGuid(),
                        node.getName(),
                        node.getLastModificationDate()));
            } else {
                System.out.println(String.format("Guid: %s | Name: %s | Document type: %s | File type: %s | Extension: %s | Size: %s | LastModificationDate: %s",
                        node.getGuid(),
                        node.getName(),
                        node.getDocumentType(),
                        node.getFileType(),
                        node.getExtension(),
                        node.getSize(),
                        node.getLastModificationDate()));
            }
        }
    }
}
