package controller;

import dao.Detalle_ServiciosDao;
import entidades.TablaDetalle_Servicios;
import entidades.TablaJira;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class GenerarInformeDetalles extends HttpServlet {

    public GenerarInformeDetalles() {
        super();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method. 5
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String EmployeeName = "";
        Double PorcentajeDedicacion = 0.0;
        Double HoursServices = 0.0;
        String proyectName = "";
        Double total = 0.0;
        String NombreAnterior = "";
        String proan = "";
        Double HoraAnterior = 0.0;
        String idean = "";
        Double horat = 0.0;
        Double suma = 0.0;

        TablaDetalle_Servicios det;
        List<TablaDetalle_Servicios> lst = new ArrayList<>();

        Detalle_ServiciosDao dao = new Detalle_ServiciosDao();
        dao.CargarDatos();
        ResultSet res = dao.CargarDatos();
        List<TablaJira> Jira = new ArrayList<>();

        try {
            while (res.next()) {
                TablaJira j = new TablaJira();
                j.setNro_Documento(res.getString("Nro_Documento"));
                j.setNombres(res.getString("Nombres"));
                j.setHoras_registradas_en_jira(res.getDouble("Horas_registradas_en_jira"));
                j.setProyecto(res.getString("proyecto"));
                j.setHoras_laboradas_sin_extras_sin_novedades(res.getDouble("Horas_laboradas_sin_extras_y_sin_novedades"));
                Jira.add(j);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(GenerarInformeDetalles.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int j = 0; j < Jira.size(); j++) {

            String ide = Jira.get(j).getNro_Documento();
            String name = Jira.get(j).getNombres();
            Double h = Jira.get(j).getHoras_registradas_en_jira();
            String proyec = Jira.get(j).getProyecto();
            Double time = Jira.get(j).getHoras_laboradas_sin_extras_sin_novedades();

            if (j == 0) {
                idean = ide;
                NombreAnterior = name;
                HoraAnterior = h;
                proan = proyec;
                horat = time;

            } else if (ide.equals(idean) & proyec.equals(proan)) {

                suma = suma + h;
                idean = ide;
                proan = proyec;
                NombreAnterior = name;

                horat = time;
            } else {
                det = new TablaDetalle_Servicios();
                if (suma == 0) {
                    total = HoraAnterior / horat;

                    det.setPorcentaje_dedicacion(total);
                    det.setHoras_laboradas_mes(horat);
                    det.setNombre_empleado(NombreAnterior);
                    det.setNro_Documento(idean);
                    det.setHoras_dedicadas_Servicio(HoraAnterior);
                    det.setNombre_proyecto(proan);
                    lst.add(det);

                    NombreAnterior = name;
                    horat = time;
                    idean = ide;
                    proan = proyec;
                    HoraAnterior = 0.0;
                    suma = h;

                } else {

                    total = suma / horat;

                    det.setPorcentaje_dedicacion(total);
                    det.setHoras_laboradas_mes(horat);
                    det.setNombre_empleado(NombreAnterior);
                    det.setNro_Documento(idean);
                    det.setHoras_dedicadas_Servicio(suma);
                    det.setNombre_proyecto(proan);
                    lst.add(det);

                    NombreAnterior = name;
                    horat = time;
                    idean = ide;
                    proan = proyec;

                    suma = h;

                }
                if (j + 1 == Jira.size()) {
                    if (ide.equals(idean) & proyec.equals(proan)) {
                        det = new TablaDetalle_Servicios();
                        total = suma / horat;
                        det.setNombre_proyecto(proan);
                        det.setPorcentaje_dedicacion(total);
                        det.setHoras_laboradas_mes(horat);
                        det.setNombre_empleado(NombreAnterior);
                        det.setNro_Documento(idean);
                        det.setHoras_dedicadas_Servicio(suma);

                        lst.add(det);
                    }
                }
            }
        }
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        CellStyle style = wb.createCellStyle();

        Font font = wb.createFont();
        font.setFontName("Arial");

        style.setFillForegroundColor((short) 30);
        style.setBottomBorderColor((short) 8);

        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        style.setWrapText(true);

        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        XSSFRow row = sheet.createRow(1);
        XSSFCell cell;

        row.createCell(1).setCellValue("Nombre Empleado");
        row.getCell(1).setCellStyle(style);

        row.createCell(2).setCellValue("Proyecto");
        row.getCell(2).setCellStyle(style);

        row.createCell(3).setCellValue("Horas Dedicadas al servicios");
        row.getCell(3).setCellStyle(style);

        row.createCell(4).setCellValue("Horas laboradas");
        row.getCell(4).setCellStyle(style);

        row.createCell(5).setCellValue("Porcentaje de dedicacion");
        row.getCell(5).setCellStyle(style);

        CellStyle stylePercent = wb.createCellStyle();
        stylePercent.setDataFormat(wb.createDataFormat().getFormat("0.00%"));

        int i = 2;
        for (int n = 0; n < lst.size(); n++) {
            EmployeeName = lst.get(n).getNombre_empleado();

            proyectName = lst.get(n).getNombre_proyecto();
            HoursServices = lst.get(n).getHoras_dedicadas_Servicio();
            PorcentajeDedicacion = lst.get(n).getPorcentaje_dedicacion();
            horat = lst.get(n).getHoras_laboradas_mes();
            row = sheet.createRow(i++);

            cell = row.createCell(1);
            cell.setCellValue(EmployeeName);
            cell = row.createCell(2);
            cell.setCellValue(proyectName);
            cell = row.createCell(3);
            cell.setCellValue(HoursServices);
            cell = row.createCell(4);
            cell.setCellValue(horat);
            cell = row.createCell(5);
            cell.setCellValue(PorcentajeDedicacion);
            cell.setCellStyle(stylePercent);
            sheet.autoSizeColumn(n, true);

        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        wb.write(out);
        byte[] outArray = out.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0");
        response.setHeader("Content-Disposition", "attachment; filename=ensayo.xlsx");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        outStream.close();
    }
}
