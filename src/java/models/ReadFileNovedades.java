package models;

import controller.RegistrarFechas;
import dao.TablaNovedadEmpleadoDao;
import entidades.TablaNovedadEmpleado;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import java.util.Date;

public class ReadFileNovedades {

    private static TablaNovedadEmpleadoDao TablaNovedadEmpleadoDao;

    public static void leerarhivoNovedades(FileInputStream inputStream, String fecha) throws IOException {

        BorrarDatos();

        //crreacion del libro nuevo
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        //creacion de la hoja del libro
        Sheet firstsheet = workbook.getSheetAt(0);
        List<TablaNovedadEmpleado> lst = new ArrayList<>();
        String tip = "";
        Double suma = 0.0;
        Double sumav = 0.0;
        Double sumai = 0.0;
        Double sumal = 0.0;
        Double sumap = 0.0;
        String ida = "";
        String nombrean = "";
        Double totalv = 0.0;
        Double totali = 0.0;
        Double totall = 0.0;
        Double totalp = 0.0;
        Double total = 0.0;
        String novedad = "";

        int con = 0;
        Row fila;
        TablaNovedadEmpleado No = null;

        for (int i = 1; i <= firstsheet.getLastRowNum(); i++) {
            fila = firstsheet.getRow(i);
            No = new TablaNovedadEmpleado();
            No.setFecha(fecha);

            int j = 0;

            for (j = 0; j < fila.getLastCellNum(); j++) {
                Cell celda = fila.getCell(j);
                if (j == 0) {
                    if (celda == null || celda.getCellType() == Cell.CELL_TYPE_BLANK) {
                        break;
                    }
                }

                switch (j) {

                    case 0:
                        if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                            No.setNombres(celda.getStringCellValue());
                        }
                        break;

                    case 1:
                        if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                            No.setTipo_Novedad(celda.getStringCellValue());
                        }
                        break;
                    case 2:

                        No.setFecha_inicio(new java.sql.Date(((Date) celda.getDateCellValue()).getTime()));

                        break;

                    case 3:

                        No.setFecha_fin(new java.sql.Date(((Date) celda.getDateCellValue()).getTime()));

                        break;

                    case 4:
                        if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            No.setDias((int) celda.getNumericCellValue());
                        }
                        break;
                    case 5:
                        if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            No.setHoras((Double) celda.getNumericCellValue());
                        }
                        break;
                }

            }
            if (No.getNombres() != null) {
                lst.add(No);

            }
        }
        Collections.sort(lst, TablaNovedadEmpleado.comparet);

        for (int i = 0; i < lst.size(); i++) {
            String nombre = lst.get(i).getNombres();

            if (nombre == null ? nombrean != null : !nombre.equals(nombrean)) {
                con = 1;
                lst.get(i).setCod_Novedad(con);
                nombrean = nombre;
            } else {
                con++;
                lst.get(i).setCod_Novedad(con);
            }

        }

        for (int n = 0; n < lst.size(); n++) {

            String nombre = lst.get(n).getNombres();
            String tipo = lst.get(n).getTipo_Novedad();
            Double va = lst.get(n).getHoras();
            if (n == 0) {

                if ("Vacaciones".equals(tipo)) {

                    sumav = va;
                    totalv = sumav;
                    nombrean = nombre;
                    tip = tipo;

                }
                if ("Incapacidad".equals(tipo)) {
                    sumai = va;
                    totali = sumai;
                    nombrean = nombre;
                    tip = tipo;

                }
                if ("Licencia no Rem.".equals(tipo)) {
                    sumal = va;
                    totall = sumal;
                    nombrean = nombre;
                    tip = tipo;
                }
                if ("Permiso".equals(tipo)) {
                    sumap = va;
                    totalp = sumap;
                    nombrean = nombre;
                    tip = tipo;
                }
            } else if (nombre == null ? nombrean == null : nombre.equals(nombrean) & tipo.equals(tip)) {
                if ("Vacaciones".equals(tipo)) {
                    if (tipo.equals(tip)) {

                        total = sumav + va;
                        totalv = total;
                        sumav = va;
                    }

                }
                if ("Incapacidad".equals(tipo)) {
                    if (tipo.equals(tip)) {

                        total = sumai + va;
                        totali = total;
                        sumai = va;
                    }

                }

                if ("Licencia no Rem.".equals(tipo)) {
                    if (tipo.equals(tip)) {

                        total = sumal + va;
                        totall = va;
                        sumal = va;
                    }

                }
                if ("Permiso".equals(tipo)) {
                    if (tipo.equals(tip)) {

                        total = sumap + va;
                        totalp = va;
                        sumap = va;

                    }

                }

                for (int m = 0; m < lst.size(); m++) {
                    if (lst.get(m).getNombres() == null ? nombrean == null : lst.get(m).getNombres().equals(nombrean)) {
                        novedad = lst.get(m).getTipo_Novedad();
                        if (novedad.equals(tipo)) {

                            if ("Vacaciones".equals(tipo)) {
                                lst.get(m).setHoras(totalv);
                            }

                            if ("Incapacidad".equals(tipo)) {
                                lst.get(m).setHoras(totali);
                            }
                            if ("Licencia no Rem.".equals(tipo)) {
                                lst.get(m).setHoras(totall);
                            }
                            if ("Permiso".equals(tipo)) {
                                lst.get(m).setHoras(totalp);
                            }
                        }
                    }
                }

            } else {
                switch (tip) {
                    case "Vacaciones":
                        lst.get(n - 1).setHoras(totalv);
                        sumav = 0.0;
                        totalv = 0.0;
                        total = 0.0;
                        if ("Incapacidad".equals(tipo)) {
                            sumai = va;
                            totali = sumai;

                        }
                        if ("Vacaciones".equals(tipo)) {
                            sumav = va;
                            totalv = sumav;
                        }

                        if ("Licencia no Rem.".equals(tipo)) {
                            sumal = va;
                            totall = sumal;

                        }
                        if ("Permiso".equals(tipo)) {
                            sumap = va;
                            totalp = sumap;
                        }

                        nombrean = nombre;

                        tip = tipo;
                        break;
                    case "Incapacidad":
                        lst.get(n - 1).setHoras(totali);
                        sumai = 0.0;
                        totali = 0.0;
                        total = 0.0;

                        if ("Incapacidad".equals(tipo)) {
                            sumai = va;
                            totali = sumai;
                        }
                        if ("Vacaciones".equals(tipo)) {
                            sumav = va;
                            totalv = sumav;
                        }
                        if ("Licencia no Rem.".equals(tipo)) {
                            sumal = va;
                            totall = sumal;
                        }
                        if ("Permiso".equals(tipo)) {
                            sumap = va;
                            totalp = sumap;
                        }

                        nombrean = nombre;

                        tip = tipo;
                        break;

                    case "Licencia no Rem.":
                        lst.get(n - 1).setHoras(totall);
                        sumal = 0.0;
                        total = 0.0;
                        totall = 0.0;
                        if ("Incapacidad".equals(tipo)) {
                            sumai = va;
                            totali = sumai;
                        }
                        if ("Vacaciones".equals(tipo)) {
                            sumav = va;
                            totalv = sumav;
                        }
                        if ("Licencia no Rem.".equals(tipo)) {
                            sumal = va;
                            totall = sumal;
                        }
                        if ("Permiso".equals(tipo)) {
                            sumap = va;
                            totalp = sumap;
                        }

                        nombrean = nombre;

                        tip = tipo;
                        break;
                    case "Permiso":
                        lst.get(n - 1).setHoras(totalp);
                        sumap = 0.0;
                        totalp = 0.0;
                        total = 0.0;
                        if ("Incapacidad".equals(tipo)) {
                            sumai = va;
                            totali = sumai;
                        }
                        if ("Vacaciones".equals(tipo)) {
                            sumav = va;
                            totalv = sumav;

                        }
                        if ("Licencia no Rem.".equals(tipo)) {
                            sumal = va;
                            totall = sumal;

                        }
                        nombrean = nombre;
                        tip = tipo;
                }

            }

        }

        lst.forEach(ReadFileNovedades::guardarArchivoNovedades);
    }

    RegistrarFechas fe = new RegistrarFechas();

    private static void guardarArchivoNovedades(TablaNovedadEmpleado No) {
        if (TablaNovedadEmpleadoDao == null) {
            TablaNovedadEmpleadoDao = new TablaNovedadEmpleadoDao(true);
        }
        TablaNovedadEmpleadoDao.guardarArchivoNoveddades(No);

    }

    private static void BorrarDatos() {
        TablaNovedadEmpleadoDao dao = new TablaNovedadEmpleadoDao();
        dao.BorrarDatos();
    }

}
