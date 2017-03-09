package models;

import dao.ListaProyectosDao;
import entidades.TablaListaDeProyectos;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class ReadFilelListaProyectos {

    private static ListaProyectosDao objListaProyectosDao;

    public static void LeerArchivoProyectos(FileInputStream InpuStream) throws IOException {

        XSSFWorkbook wb = new XSSFWorkbook(InpuStream);
        Sheet firstSheet = wb.getSheetAt(0);
        TablaListaDeProyectos li;
        int j = 0;

        for (int i = 1; i <=firstSheet.getLastRowNum(); i++) {
            Row fila = firstSheet.getRow(i);
            li = new TablaListaDeProyectos();

            for (j = 0; j < fila.getLastCellNum(); j++) {
                Cell celda = fila.getCell(j);
                if (j == 0) {
                    if (celda.getCellType() == Cell.CELL_TYPE_BLANK) {
                        break;
                    }

                }
                switch(j) {
                    case 0:
                        if (celda.getCellType() == Cell.CELL_TYPE_STRING){
                        li.setProyectosyConceptos(celda.getStringCellValue());
                        }
                        break;
                    case 1:
                        if (celda.getCellType() == Cell.CELL_TYPE_STRING){
                        li.setProductos(celda.getStringCellValue());
                        }
                        break;
                    case 2:
                        if (celda.getCellType() == Cell.CELL_TYPE_STRING){
                        li.setFacturable(celda.getStringCellValue());
                        }
                        break;
                    case 3:
                        if (celda.getCellType() == Cell.CELL_TYPE_STRING){
                        li.setEspecialidad(celda.getStringCellValue());
                        }
                        break;
                
                }
            }
            if (j > 0){
            GuardarDatosProyecto (li);
            }
        }

    }

    private static void GuardarDatosProyecto(TablaListaDeProyectos li) {
        if (objListaProyectosDao == null){
        objListaProyectosDao = new ListaProyectosDao(true);
        }
        objListaProyectosDao.GuardarDatosProyecto(li);
    }

}
