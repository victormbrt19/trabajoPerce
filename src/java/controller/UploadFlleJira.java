package controller;

import dao.TablaJiraDao;
import entidades.TablaJira;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ReadFileJira;
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
public class UploadFlleJira extends HttpServlet {

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
        List ErrorMsg = new ArrayList();
        String fecha = request.getParameter("Fecha");
        if (fecha == null) {
            ErrorMsg.add("Por favor ingrese una fecha");
        }

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

        String fecha = "";
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            return;
        }
        FileItemFactory factory = new DiskFileItemFactory();
        String uploadFolder = getServletContext().getRealPath("") + File.separator;

        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));

        String fileName = "", filepath;
        File uploadedFile = null;

        for (FileItem item : items) {
            if (item.isFormField()) {
                String fieldname = item.getFieldName();
                if (fieldname.equals("Fecha")) {
                    fecha = item.getString();
                }
            } else if (!item.isFormField()) {
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

        //  HttpSession session = request.getSession();
        ReadFileJira.leerArchivoExcel2(new FileInputStream(uploadedFile), fecha);

        TablaJira ji = new TablaJira();
        TablaJiraDao dao = new TablaJiraDao();
        if (dao.insertarTablaJira(ji) == true) {
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
