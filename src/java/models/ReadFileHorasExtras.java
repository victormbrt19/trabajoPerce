package models;

import dao.HorasExtrasDao;
import dao.TablaJiraDao;
import entidades.HorasExtras;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFileHorasExtras {

    private static HorasExtrasDao objHorasExtrasDao;

    public static void LeerHorasExtras(FileInputStream inputStream) throws IOException {
        BorrarDatos();
        String stra = "";
        String Nombre_anterior = "";
        String Ticket_anterior = "";
        Double Hora_an = 0.0;
        Double suma = 0.0;
        Double total = 0.0;
        Double TotalValor = 0.0;
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        Sheet firstsheet = wb.getSheetAt(0);
        List<HorasExtras> lst = new ArrayList<>();

        Row fila;
        HorasExtras Ho = null;
        for (int i = 1; i <= firstsheet.getLastRowNum(); i++) {
            fila = firstsheet.getRow(i);
            Ho = new HorasExtras();
            for (int j = 0; j < fila.getLastCellNum(); j++) {
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
                                Ho.setNombreEmpleado(celda.getStringCellValue());
                            }
                            break;
                        case 1:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                Ho.setModalida_deCobro(celda.getStringCellValue());
                            }
                            break;
                        case 2:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                Ho.setPersona_cuentaCobro(celda.getStringCellValue());
                            }
                            break;
                        case 3:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                Ho.setTikect(celda.getStringCellValue());
                            }
                            break;
                        case 4:
                            if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                stra = String.valueOf(celda.getNumericCellValue());
                            } else {
                                stra = celda.getStringCellValue();
                            }
                            Ho.setHoras(Double.parseDouble(stra.replaceAll(",", ".")));
                            break;
                        case 5:
                            if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                stra = String.valueOf(celda.getNumericCellValue());
                            } else {
                                stra = celda.getStringCellValue();
                            }
                            Ho.setValor(Double.parseDouble(stra.replaceAll(",", ".")));
                            break;
                        case 6:
                            if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                Ho.setMes((int) celda.getNumericCellValue());
                            }
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
            if (Ho.getNombreEmpleado() != null) {
                lst.add(Ho);
            }

        }
        Collections.sort(lst, HorasExtras.comparet);

        for (int x = 0; x < lst.size(); x++) {
            String Nombre = lst.get(x).getNombreEmpleado();
            String Ticket = lst.get(x).getTicket();
            Double Horas = lst.get(x).getHoras();
            Double Valor = lst.get(x).getValor();

            if (x == 0) {
                Nombre_anterior = Nombre;
                Ticket_anterior = Ticket;
                TotalValor = Valor;
                suma = Horas;
            } else if (Nombre.equals(Nombre_anterior)) {

                if (Ticket.equals(Ticket_anterior)) {
                    suma = suma + Horas;
                    TotalValor = TotalValor + Valor;

                    for (int z = 0; z < lst.size(); z++) {
                        if (lst.get(z).getNombreEmpleado() == null ? Nombre_anterior == null : lst.get(z).getNombreEmpleado().equals(Nombre_anterior) & lst.get(z).getTicket() == null
                                ? Ticket_anterior == null : lst.get(z).getTicket().equals(Ticket_anterior)) {
                            lst.get(z).setHoras_Extras_Por_Ticket(suma);
                            lst.get(z).setTotalValorTicket(TotalValor);
                        }
                    }

                } else {
                    lst.get(x - 1).setHoras_Extras_Por_Ticket(suma);
                    lst.get(x - 1).setTotalValorTicket(TotalValor);
                    Nombre_anterior = Nombre;
                    Ticket_anterior = Ticket;
                    TotalValor = 0.0;
                    suma = 0.0;
                    suma = Horas;
                    TotalValor = Valor;
                }

            } else {
                lst.get(x - 1).setHoras_Extras_Por_Ticket(suma);
                lst.get(x - 1).setTotalValorTicket(TotalValor);
                Nombre_anterior = Nombre;
                Ticket_anterior = Ticket;
                TotalValor = 0.0;
                suma = 0.0;
                suma = Horas;
                TotalValor = Valor;
            }
            if (x + 1 == lst.size()) {
                lst.get(x).setHoras_Extras_Por_Ticket(suma);
                lst.get(x).setTotalValorTicket(TotalValor);
            }
        }

        lst.forEach(ReadFileHorasExtras::guardarExtras);
    }

    private static void BorrarDatos() {
        HorasExtrasDao dao = new HorasExtrasDao();
        dao.BorrarDatos();
    }

    private static void guardarExtras(HorasExtras Ho) {
        if (objHorasExtrasDao == null) {
            objHorasExtrasDao = new HorasExtrasDao(true);
        }
        objHorasExtrasDao.insertarHorasExtras(Ho);

    }

}
