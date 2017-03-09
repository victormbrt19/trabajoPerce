package models;

import dao.EspecialidadDao;
import entidades.Especialidad;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFileEspecialidades {

    private static EspecialidadDao objEspecialidadDao;

 

    public static void ArchivodeEspecialidades(FileInputStream inputStream) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);

     Especialidad esp= null;
        // Recorremos las filas, desde la 1, pra omitir el encabezado
        for (int i = 1; i <= firstSheet.getLastRowNum(); i++) {
            Row fila = firstSheet.getRow(i);

             esp= new Especialidad();

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
                            esp.setNombreEmpleado(celda.getStringCellValue());
                        }
                        break;
                    case 1:
                        if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                            esp.setEspecialidad(celda.getStringCellValue());
                        }
                        break;
                    case 2:
                        if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                            esp.setModalidad(celda.getStringCellValue());
                        }
                        break;
                   

                }
            }

            
            if (j > 0) {
                GuardarEspecialidad(esp);

            }
        }

    }

    private static void GuardarEspecialidad(Especialidad esp) {
        if (objEspecialidadDao== null) {
            objEspecialidadDao = new EspecialidadDao(true);
        }
        objEspecialidadDao.GuardarEspecialidad(esp);

    }

   

}
