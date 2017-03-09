package models;

import dao.TablaCostosDao;
import entidades.TablaCostos;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFileCostos {

    private static TablaCostosDao objTablaCostosDao;

    public static void leerArchivoExcel(FileInputStream inputStream) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);

        BorrarDatos();
        // Recorremos las filas, desde la 1, pra omitir el encabezado
        for (int i = 1; i <= firstSheet.getLastRowNum(); i++) {
            Row fila = firstSheet.getRow(i);

            TablaCostos c = new TablaCostos();

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
                        
                        break;
                    case 1:
                        if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                            c.setNombres(celda.getStringCellValue());
                        }
                        break;
                    case 2:
                        if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                            c.setTipoDeSueldo(celda.getStringCellValue());
                        }
                        break;
                    case 3:
                        break;

                    case 4:
                        break;

                    case 5:
                        break;
                    case 6:
                        if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            c.setIncapacidades(celda.getNumericCellValue());
                        }
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    case 13:
                        break;
                    case 14:
                        break;
                    case 15:
                        break;
                    case 16:
                        break;
                    case 17:
                        break;
                    case 18:
                        break;
                    case 19:
                        break;
                    case 20:
                        break;
                    case 21:
                        break;
                    case 22:
                        break;
                    case 23:
                        break;
                    case 24:
                        break;
                    case 25:
                        break;
                    case 26:
                        break;
                    case 27:
                        if (celda.getCellType() == Cell.CELL_TYPE_FORMULA) {
                            c.setTotal(celda.getNumericCellValue());
                        }
                        break;

                }
            }

            
            if (j > 0) {
                GuardarTablaCostos(c);

            }
        }

    }

    private static void GuardarTablaCostos(TablaCostos c) {
        if (objTablaCostosDao == null) {
            objTablaCostosDao = new TablaCostosDao(true);
        }
        objTablaCostosDao.GuardarTablaCostos(c);

    }

    private static void BorrarDatos() {
        TablaCostosDao dao = new TablaCostosDao();
        dao.borrarDatos();
    }

}
