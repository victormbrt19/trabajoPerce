package models;

import dao.TablaEmpleadoDao;
import entidades.TablaEmpleado;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFileNombres {

    public static void LeerArchivoNombre(FileInputStream inputStream) throws IOException {
        BorrarDatos();
        //creacion del libor de excel
        XSSFWorkbook libro = new XSSFWorkbook(inputStream);
        Sheet firstSheet = libro.getSheetAt(0);

        Row fila;
        List<TablaEmpleado> lst = new ArrayList<>();
        TablaEmpleado em = null;

        for (int i = 1; i <= firstSheet.getLastRowNum(); i++) {
            fila = firstSheet.getRow(i);
            em = new TablaEmpleado();

            for (int j = 0; j <= fila.getLastCellNum(); j++) {
                Cell celda = fila.getCell(j);

                if (j == 0) {

                    if (celda == null || celda.getCellType() == Cell.CELL_TYPE_BLANK) {
                        break;
                    }

                }
                try {
                    switch (j) {

                        case 0:
                            if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                em.setNro_Documento((int) celda.getNumericCellValue());
                            }
                            break;

                        case 1:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                em.setNombre_Jira(celda.getStringCellValue());
                            }
                            break;

                        case 2:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                em.setNombre_Novedades(celda.getStringCellValue());
                            }
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (em.getNombre_Jira() != null) {
                lst.add(em);

                // guardarTablaEmpleado(em);
                //nom.add(em);
            }
        }
        Collections.sort(lst, TablaEmpleado.comparet);

        lst.forEach(ReadFileNombres::guardarTablaEmpleado);
    }

    private static void guardarTablaEmpleado(TablaEmpleado em) {
        TablaEmpleadoDao dao = new TablaEmpleadoDao();
        dao.GuardarDatosEmpleado(em);
    }

    private static void BorrarDatos() {
        TablaEmpleadoDao dao = new TablaEmpleadoDao();
        dao.borrarDatos();
    }
}
