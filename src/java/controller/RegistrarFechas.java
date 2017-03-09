package controller;

import dao.CalendarioDao;
import dao.TablaJiraDao;
import dao.TablaNovedadEmpleadoDao;
import entidades.Calendario;
import entidades.TablaJira;
import entidades.TablaNovedadEmpleado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pcc
 */
public class RegistrarFechas extends HttpServlet {

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

        String Fecha = request.getParameter("Fecha");

        TablaJira ji = new TablaJira(Fecha);
        TablaJiraDao d = new TablaJiraDao();

        TablaNovedadEmpleado no = new TablaNovedadEmpleado(Fecha);
        TablaNovedadEmpleadoDao da = new TablaNovedadEmpleadoDao();

        
        Calendario c = new Calendario(Fecha);
        CalendarioDao dao = new CalendarioDao();

        int f = da.guardarArchivoNoveddades(no);

        boolean s = d.insertarTablaJira(ji);

        int sw = dao.IngresarFecha(c);

        HttpSession session = request.getSession(true);
        session.setAttribute("FechaJira", Fecha);

        String mensaje = (sw > 0 ? "Datos guardados" : "Error, no se guardaron los datos");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrarFechas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrarFechas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
