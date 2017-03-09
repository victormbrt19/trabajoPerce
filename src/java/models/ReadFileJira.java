package models;

import controller.RegistrarFechas;
import dao.TablaJiraDao;
import entidades.TablaJira;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFileJira {

    private static TablaJiraDao objTablaJiraDao;

    public static void leerArchivoExcel2(FileInputStream inputStream, String fecha) throws IOException {

        BorrarDatos();

        //creacion del libro de excel 
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);

        int con = 1;
        Double SumaHorasExtras = 0.0;
        String nombre_ant = "";
        String Employee_name_A = "";
        Double TotalHoraLa = 0.0;
        Double suma = 0.0;
        Double sumaextras = 0.0;
        Double hora;
        String Employee_name = "";
        String stra;
        String est;
        String nombre_anterior = "";
        String ticket_anterior = "";
        double Horas_anterior = 0.0;
        Double sumaExtrasTicket = 0.0;
        int horasla = 198;
        String project_anterior = "";

        Row fila;
        List<TablaJira> lst = new ArrayList<>();
        TablaJira ji = null;

        //for para obetener las fila comenzando desde la primera para no tener encuenta el encabezado
        for (int i = 1; i <= firstSheet.getLastRowNum(); i++) {

            fila = firstSheet.getRow(i);

            ji = new TablaJira();
            ji.setFecha(fecha);
            int j = 0;
            //for para empezar a recorrer las celdas
            for (j = 0; j < fila.getLastCellNum(); j++) {
                Cell celda = fila.getCell(j);

                if (j == 0) {

                    if (celda == null || celda.getCellType() == Cell.CELL_TYPE_BLANK) {
                        break;
                    }

                }
                try {

                    switch (j) {

                        case 0:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                ji.setProyecto_jira(celda.getStringCellValue());

                            }

                            break;

                        case 1:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                ji.setTipo(celda.getStringCellValue());
                            }

                            break;

                        case 2:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                ji.setClave(celda.getStringCellValue());
                            }

                            break;

                        case 3:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                ji.setTitulo(celda.getStringCellValue());
                            }
                            break;
                        case 4:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                ji.setEstado(celda.getStringCellValue());
                            }

                            break;
                        case 5:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                ji.setHoras_Extras_Jira(celda.getStringCellValue());
                            }
                            break;

                        case 6:
                            try {
                                if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    est = String.valueOf(celda.getNumericCellValue());
                                } else {
                                    est = celda.getStringCellValue();
                                }
                                ji.setTotal_Horas_Extras_Jira(Double.parseDouble(est.replaceAll(",", ".")));
                            } catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 7:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                ji.setNombres(celda.getStringCellValue());

                            }

                            break;
                        case 8:

                            try {
                                if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    stra = String.valueOf(celda.getNumericCellValue());
                                } else {
                                    stra = celda.getStringCellValue();
                                }

                                ji.setHoras_registradas_en_jira(Double.parseDouble(stra.replaceAll(",", ".")));

                            } catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                            }

                            break;

                        case 9:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                ji.setProyecto(celda.getStringCellValue());
                            }
                            break;
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            //Se guardan los datos por cada fila
            //guardarTablaNovedadEmpleado(ne);
            //guardarTablaNovedades(n);
            if (ji.getNombres() != null) {
                lst.add(ji);
            }
        }

        Collections.sort(lst, TablaJira.comparet);

        //ASIGNACION DE LOS IDE REINICIABLES POR REGISTRO DIFERENTE
        for (int i = 0; i < lst.size(); i++) {

            Employee_name = lst.get(i).getNombres();

            if (Employee_name == null ? nombre_ant != null : !Employee_name.equals(nombre_ant)) {
                con = 1;

                lst.get(i).setPosicion(con);

                nombre_ant = Employee_name;
            } else {
                con++;
                lst.get(i).setPosicion(con);

            }

        }

        //FOR PARA SUMAR LAS HORAS REGISTRADAS EN JIRA POR EMPLEADO
        //PARA CALCULAR EL TOTAL DE HORAS LABORADAS SIN EXTRAS Y SI LAS HORAS REGISTRADAS
        //EN JIRA POR EMPLEADO SON MENORES A 198 COLOCARLE HORAS LABORADAS 198
        for (int j = 0; j < lst.size(); j++) {

            Employee_name = lst.get(j).getNombres();
            String pro = lst.get(j).getProyecto();
            hora = (double) lst.get(j).getHoras_registradas_en_jira();
            Double ex = (double) lst.get(j).getHoras_registradas_en_jira();

            String proyectName = pro.toLowerCase();

            Pattern pat = Pattern.compile(".*horas-extras.*");
            Matcher mat = pat.matcher(proyectName);

            if (mat.matches()) {

                lst.get(j).setHoras_extras_Proyecto("Horas extras");
                SumaHorasExtras = SumaHorasExtras + ex;
                lst.get(j).setCan_extras(ex);

            }

            if (j == 0) {
                suma = hora;
                TotalHoraLa = suma - SumaHorasExtras;
                Employee_name_A = Employee_name;
            } else if (Employee_name == null ? Employee_name_A == null : Employee_name.equals(Employee_name_A)) {

                suma = (Double) (suma + hora);

                TotalHoraLa = suma - SumaHorasExtras;

                for (int m = 0; m < lst.size(); m++) {

                    if (lst.get(m).getNombres() == null ? Employee_name_A == null : lst.get(m).getNombres().equals(Employee_name_A)) {
                        if (TotalHoraLa < horasla) {

                            lst.get(m).setHoras_laboradas_sin_extras(horasla);

                        } else {
                            lst.get(m).setHoras_laboradas_sin_extras(TotalHoraLa);

                        }

                    }

                }

            } else {
                if (TotalHoraLa < horasla) {

                    lst.get(j - 1).setHoras_laboradas_sin_extras(horasla);
                    suma = hora;
                    Employee_name_A = Employee_name;
                    SumaHorasExtras = 0.0;
                    TotalHoraLa = 0.0;
                } else {
                    lst.get(j - 1).setHoras_laboradas_sin_extras(TotalHoraLa);
                    suma = hora;
                    Employee_name_A = Employee_name;
                    SumaHorasExtras = 0.0;
                    TotalHoraLa = 0.0;
                }
            }

            if (j + 1 == lst.size()) {
                if (TotalHoraLa < horasla) {

                    lst.get(j).setHoras_laboradas_sin_extras(horasla);
                    suma = hora;
                    Employee_name_A = Employee_name;
                    SumaHorasExtras = 0.0;
                    TotalHoraLa = 0.0;
                } else {
                    lst.get(j).setHoras_laboradas_sin_extras(TotalHoraLa);
                    suma = hora;
                    Employee_name_A = Employee_name;
                    SumaHorasExtras = 0.0;
                    TotalHoraLa = 0.0;
                }

            }
        }

        for (int j = 0; j < lst.size(); j++) {
            String projecto = lst.get(j).getProyecto();
            String ticket = lst.get(j).getClave();
            String Nombre = lst.get(j).getNombres();
            Double horas = lst.get(j).getHoras_registradas_en_jira();

            String pro = projecto.toLowerCase();
            Pattern pat = Pattern.compile(".*horas-extras.*");
            Matcher mat = pat.matcher(pro);

            if (mat.matches()) {
                if (j == 0) {
                    if (ticket == null) {
                        nombre_anterior = Nombre;
                        project_anterior = pro;
                        lst.get(j).setCan_extras(horas);
                    } else {
                        project_anterior = pro;
                        sumaextras = horas;
                        nombre_anterior = Nombre;
                        ticket_anterior = ticket;

                    }
                } else if (Nombre.equals(nombre_anterior)) {
                    if (ticket == null) {
                        nombre_anterior = Nombre;
                        project_anterior = pro;
                        // sumaextras = horas;
                        lst.get(j).setCan_extras(horas);
                    } else if (ticket_anterior != null & ticket.equals(ticket_anterior)) {
                        sumaextras = sumaextras + horas;

                        for (int i = 0; i < lst.size(); i++) {
                            if (lst.get(i).getNombres() == null ? nombre_anterior == null : lst.get(i).getNombres().equals(nombre_anterior)) {
                                if (lst.get(i).getClave() == null) {
                                } else if (lst.get(i).getClave().equals(ticket_anterior)) {
                                    String projec_Name = lst.get(i).getProyecto();
                                    String project = projec_Name.toLowerCase();
                                    Pattern projecname = Pattern.compile(".*horas-extras.*");
                                    Matcher mate = projecname.matcher(project);

                                    if (mate.matches()) {
                                        lst.get(i).setCan_extras(sumaextras);
                                    }
                                }
                            }
                        }

                    } else {
                        nombre_anterior = Nombre;
                        ticket_anterior = ticket;
                        project_anterior = pro;
                        lst.get(j).setCan_extras(horas);
                        sumaextras = 0.0;
                        sumaextras = horas;
                    }

                } else if (j == 0) {
                    nombre_anterior = Nombre;
                    ticket_anterior = ticket;
                    project_anterior = pro;

                    sumaextras = horas;
                } else {
                    nombre_anterior = Nombre;
                    ticket_anterior = ticket;
                    project_anterior = pro;
                    sumaextras = 0.0;
                    sumaextras = horas;
                }
            }
        }

        lst.forEach(ReadFileJira::guardarTblJira);

    }
    RegistrarFechas fe = new RegistrarFechas();

    private static void guardarTblJira(TablaJira ji) {
        if (objTablaJiraDao == null) {
            objTablaJiraDao = new TablaJiraDao(true);
        }

        objTablaJiraDao.insertarTablaJira(ji);

    }

    private static void BorrarDatos() {

        TablaJiraDao dao = new TablaJiraDao();
        dao.borrarDatos();
    }

}
