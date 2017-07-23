package app.gwt.sample.server;


import org.apache.commons.fileupload.FileItem;

import java.util.List;

public interface FileReader {

    void readFile(List<FileItem> items);

}
