package app.gwt.sample.server;


import app.gwt.sample.shared.Log;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

public class FileReaderImpl implements FileReader {
    Repository repository = Repository.getInstance();

    @Override
    public void readFile(List<FileItem> items) {
        String content = "";
        String actualLine = "";
        int counter = 0;
        Iterator iter = items.iterator();
        try {
            while (iter.hasNext()) {
                repository.deleteContent();
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    BufferedInputStream buff = new BufferedInputStream(item.getInputStream());
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(buff));
                    while ((actualLine = br.readLine()) != null) {
                        Log newLog = new Log();
                        newLog.setId(counter++);
                        newLog.setContent(actualLine);
                        repository.addElement(newLog);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
