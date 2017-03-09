package entidades;

import java.sql.Date;

public class Calendario {

    private String fecha;

    public Calendario(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
