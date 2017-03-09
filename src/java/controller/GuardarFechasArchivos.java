/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GenerarFechasDao;
import entidades.GenerarFechas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pcc
 */
public class GuardarFechasArchivos extends HttpServlet {

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

        String Starting_year = request.getParameter("Starting_year");
        String Mes_Inicio = request.getParameter("Mes_Inicio");
        String Final_year = request.getParameter("Final_year");
        String Mes_Fin = request.getParameter("Mes_Fin");

        int a = Integer.parseInt(Starting_year);
        int mI = Integer.parseInt(Mes_Inicio);
        int af = Integer.parseInt(Final_year);
        int mf = Integer.parseInt(Mes_Fin);

        List resultados = new ArrayList<>();
        List res = new ArrayList<>();
        List<GenerarFechas> lst = new ArrayList<>();

        while (a <= af) {
            res.add(a);
            a++;

        }
        while (mI <= mf) {
            resultados.add(mI);

            mI++;
        }

        System.out.println(resultados);

        for (int i = 0; i < res.size(); i++) {

            for (int j = 0; j < resultados.size(); j++) {
                int dia = (int) res.get(i);
                int an = (int) resultados.get(j);
                GenerarFechas m = new GenerarFechas();
                String cadena = an + "." + dia;
                m.setYear_Meses(cadena);

                lst.add(m);

                GenerarFechasDao dao = new GenerarFechasDao();
                dao.GuardarFechas(m);
            }

        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GuardarFechasArchivos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GuardarFechasArchivos at " + request.getContextPath() + "</h1>");
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
