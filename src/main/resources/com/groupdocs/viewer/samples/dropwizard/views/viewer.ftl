<#-- @ftlvariable name="" type="com.groupdocs.viewer.samples.dropwizard.views.ViewerView" -->
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>GroupDocs.Viewer for Java Dropwizard Sample</title>

    <link href="${contextPath}assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${contextPath}assets/css/StyleSheet1.css" rel="stylesheet" />
    <link href="${contextPath}assets/css/bootstrap-theme.min.css" rel="stylesheet" />

    <script src="${contextPath}assets/js/jquery.min.js"></script>
    <script src="${contextPath}assets/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script src="${contextPath}assets/js/bootstrap.min.js"></script>
    <script src="${contextPath}assets/js/viewer.js"></script>
    <style type="text/css">
        body {
            padding-top: 60px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="page-header">
        <h1>GroupDocs.Viewer for Java <small>View any document anytime</small></h1>
    </div>

    <div class="row">
        <div class="col-md-4">
            <!-- Main hero unit for a primary marketing message or call to action -->

            <div class=" well">
                <div class="alert alert-success">
                    <b>Control Panel</b>
                </div>


                <form>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <b>Process Document</b>
                        </div>
                        <div class="panel-body ">
                            <b>Document Representation: </b>
                            <label class="radio-inline">
                                <input type="radio" name="RenderOptions" id="inlineRadio1" value="html" checked>
                                Html
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="RenderOptions" id="inlineRadio2" value="image">
                                Image
                            </label>
                            <br />
                            <label for="inputFile"></label>
                            <input type="file" id="inputFile" style="width: 80%">
                            <br />
                            <button id="btnRender" type="button" class="btn btn-primary btn-sm">Load Document</button><button id="btnPDF" type="button" class="btn btn-primary btn-sm" style="margin-left:5px">Download PDF</button>
                        </div>
                    </div>



                    <div id="grpTransform" class="form-group ">

                        <label class="checkbox-inline">
                            <input type="hidden" id="hfguid" value="" />
                            <input type="checkbox" id="chkWatermark" value="option1">
                            Watermark
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="chkReorder" value="option2">
                            Reorder/Rotate
                        </label>


                    </div>


                    <div id="watermarkDiv" class="form-group optional">
                        <div class="input-group">
                            <input type="text" id="txtWatermark" class="form-control" placeholder="Watermark Text...">
                                <span class="input-group-btn">
                                    <button id="btnWatermark" class="btn btn-default" type="button">Apply!</button>
                                </span>
                        </div>
                    </div>
                    <div id="reorderDiv" class="optional">
                        <div class="alert alert-info" role="alert"><b>Re-Order Pages:</b>Drag the <b>Document Pages</b> thumbnails and change the positions to apply re-order</div>

                        <div class="panel panel-default ">
                            <div class="panel-heading">
                                <b>Document Pages</b>

                            </div>
                            <div class="panel-body ">

                                <ul id="reorderable">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div id="rotateDiv" class="optional">
                        <div class="alert alert-info" role="alert">
                            <b>Rotate Page: </b>Drag the <b>Document Pages</b> thumbnails and drop to the below <b>Rotation Pane</b>.
                            <br />
                            Clik on the dragged thumbnail to rotate the page.
                        </div>
                        <div id="rotateDiv" class="panel panel-default ">
                            <div class="panel-heading">
                                <b>Rotation Pane</b>
                            </div>
                            <div id="rotation_container" class="panel-body ">
                            </div>
                        </div>
                    </div>


                </form>

            </div>
        </div>
        <div class="col-md-8">
            <!-- Example row of columns -->
            <div class="row">
                <div class="span5">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Viewer</h3>
                        </div>
                        <div class="panel-body viewer">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
