package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ReadFileHorasExtras;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author pcc
 */
public class UploadFileHorasExtras extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            subirFichero(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(UploadFileCostos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "short description";
    }

    public void subirFichero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            return;
        }
        FileItemFactory factory = new DiskFileItemFactory();
        String uploadFolder = getServletContext().getRealPath("") + File.separator;

        ServletFileUpload upload = new ServletFileUpload(factory);
        List items = upload.parseRequest(new ServletRequestContext(request));
        Iterator iter = items.iterator();
        FileItem item = null;
        String fileName = "", filepath;
        File uploadedFile = null;

        while (iter.hasNext()) {
            item = (FileItem) iter.next();
            if (!item.isFormField()) {
                fileName = new File(item.getName()).getName();
                filepath = uploadFolder + File.separator + fileName;
                uploadedFile = new File(filepath);
                try {
                    item.write(uploadedFile);
                    item.delete();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        }
ReadFileHorasExtras.LeerHorasExtras (new FileInputStream(uploadedFile));
    }

}

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
