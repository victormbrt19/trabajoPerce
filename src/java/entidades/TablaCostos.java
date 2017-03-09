package entidades;

public class TablaCostos {

    private String nro_Documento;
    private String Nombres;
    private String TipoDeSueldo;
    private Double Incapacidades;
    private Double Total;
    private Double Total_Costo_sin_incapacidades;

    public Double getTotal_Costo_sin_incapacidades() {
        if (Total_Costo_sin_incapacidades == null) {
            Total_Costo_sin_incapacidades = 0.0;
        }
        return Total_Costo_sin_incapacidades;
    }

    public void setTotal_Costo_sin_incapacidades(Double Total_Costo_sin_incapacidades) {

        this.Total_Costo_sin_incapacidades = Total_Costo_sin_incapacidades;
    }

    public String getNro_Documento() {
        return nro_Documento;
    }

    public void setNro_Documento(String nro_Documento) {
        this.nro_Documento = nro_Documento;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getTipoDeSueldo() {
        return TipoDeSueldo;
    }

    public void setTipoDeSueldo(String TipoDeSueldo) {
        this.TipoDeSueldo = TipoDeSueldo;
    }

    public Double getIncapacidades() {
        if (Incapacidades == null) {
            Incapacidades = 0.0;
        }
        return Incapacidades;
    }

    public void setIncapacidades(Double Incapacidades) {
        this.Incapacidades = Incapacidades;
    }

    public Double getTotal() {
        if (Total == null) {
            Total = 0.0;
        }
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

}
