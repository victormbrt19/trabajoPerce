/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ReadFilelListaProyectos;
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
public class UploadFileListaDeProyectos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListaDeProyectos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListaDeProyectos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected void doPost (HttpServletRequest request , HttpServletResponse response) throws ServletException ,IOException{
    
    try {
    subirFichero(request , response);
    }catch (FileUploadException ex){
    Logger.getLogger(UploadFileCostos.class.getName()).log(Level.SEVERE, null , ex);
       
    }
    
    }
    public String getServletInfo(){
    return "short description";
    }


    private void subirFichero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException{
      boolean isMultipart = ServletFileUpload.isMultipartContent(request);
      if (!isMultipart){
      return;
      }
      FileItemFactory factory = new DiskFileItemFactory();
      String UploadFolder = getServletContext().getRealPath("")+File.separator;
      
      ServletFileUpload upload = new ServletFileUpload(factory);
      List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
      
      Iterator iter = items.iterator();
      String fileName = "",filepath;
      FileItem item = null;
      File uploadedFile = null;
      
   while(iter.hasNext()){
    item = (FileItem) iter.next();
    if (!item.isFormField()){
        fileName = new File(item.getName()).getName();
        filepath = UploadFolder + File.separator + fileName;
        uploadedFile = new File (filepath);
        try {
        item.write(uploadedFile);
        item.delete();
        }catch (Exception e){
        System.out.println (e.getMessage());
        }
    }
      }
   ReadFilelListaProyectos.LeerArchivoProyectos(new FileInputStream (uploadedFile));
      
    }
    

}
