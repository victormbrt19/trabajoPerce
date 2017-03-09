package entidades;

public class TablaDetalle_Servicios {

    private String Nro_Documento;
    private String Nombre_empleado;
    private String Nombre_proyecto;
    private Double Horas_dedicadas_Servicio;
    private Double Horas_laboradas_mes;
    private Double Porcentaje_dedicacion;

    public Double getHoras_dedicadas_Servicio() {
        return Horas_dedicadas_Servicio;
    }

    public void setHoras_dedicadas_Servicio(Double Horas_dedicadas_Servicio) {
        this.Horas_dedicadas_Servicio = Horas_dedicadas_Servicio;
    }

    public Double getHoras_laboradas_mes() {
        return Horas_laboradas_mes;
    }

    public void setHoras_laboradas_mes(Double Horas_laboradas_mes) {
        this.Horas_laboradas_mes = Horas_laboradas_mes;
    }

    public Double getPorcentaje_dedicacion() {
        return Porcentaje_dedicacion;
    }

    public void setPorcentaje_dedicacion(Double Porcentaje_dedicacion) {
        this.Porcentaje_dedicacion = Porcentaje_dedicacion;
    }

    public String getNro_Documento() {
        return Nro_Documento;
    }

    public void setNro_Documento(String Nro_Documento) {
        this.Nro_Documento = Nro_Documento;
    }

    public String getNombre_empleado() {
        return Nombre_empleado;
    }

    public void setNombre_empleado(String Nombre_empleado) {
        this.Nombre_empleado = Nombre_empleado;
    }

    public String getNombre_proyecto() {
        return Nombre_proyecto;
    }

    public void setNombre_proyecto(String Nombre_proyecto) {
        this.Nombre_proyecto = Nombre_proyecto;
    }

}
