package app.gwt.sample.server;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileUploadServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "E:\\uploaded\\";
    Repository repository = Repository.getInstance();

    @Override
         protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                 throws ServletException, IOException {
                 super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

                     FileItemFactory factory = new DiskFileItemFactory();
                     ServletFileUpload upload = new ServletFileUpload(factory);
                     List items = null;
                     String content=null;
                     String thisLine;
                     StringBuffer contentFile = new StringBuffer();
                     try {
                         items = upload.parseRequest(req);
                     } catch (FileUploadException e) {
                         e.printStackTrace();
                     }

                     Iterator iter = items.iterator();
                     while (iter.hasNext())
                     {
                         FileItem item = (FileItem) iter.next();

                         if(!item.isFormField())
                         {
                             BufferedInputStream buff=new BufferedInputStream(item.getInputStream());
                             BufferedReader br = new BufferedReader(
                                     new InputStreamReader(buff));
                             while ((thisLine = br.readLine()) != null) { // while loop begins here
                                 System.out.println(thisLine);
                                 repository.addElement(thisLine);
                             }


                         }

                     }


                    //System.out.println("liczba elementow "+repository.getElements().size());
                     //resp.getOutputStream().write(contentFile.toString().getBytes());
                 }

                 }
