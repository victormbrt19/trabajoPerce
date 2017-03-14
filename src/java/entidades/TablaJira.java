package entidades;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class TablaJira {

    private String nro_Documento;
    private String fecha;
    private int posicion;
    private String proyecto_jira;
    private String tipo;
    private String clave;
    private String titulo;
    private String nombres;
    private Double Horas_registradas_en_jira;
    private String proyecto;
    private Double Horas_laboradas_sin_extras;
    private Double Horas_incapacidades;
    private Double Horas_vacaciones;
    private Double Horas_licencias;
    private String Horas_extras_Proyecto;
    private Double Can_extras_Por_Ticket;
    private Double Horas_laboradas_sin_extras_sin_novedades;
    private Double Tiempo_dedicado;
    private Double Horas_dedicadas;
    private Double Costos;
    private String Especialidad;
    private String Productos_Actividad_Interna;
    private String Facturable;
    private String Tipo_Sueldo;
    private String Estado;
    private String Horas_Extras_Jira;
    private Double Total_Horas_Extras_Jira;
    private Double Cobro_Horas_Extras;
    private String Modalidad_Cobro;
    private Double Costos_Para_La_Empresa;
    private int Mes_Cobro;

    public Double getCobro_Horas_Extras() {
        if (Cobro_Horas_Extras == null) {
            Cobro_Horas_Extras = 0.0;
        }
        return Cobro_Horas_Extras;
    }

    public void setCobro_Horas_Extras(Double Cobro_Horas_Extras) {
        this.Cobro_Horas_Extras = Cobro_Horas_Extras;
    }

    public String getModalidad_Cobro() {
        return Modalidad_Cobro;
    }

    public void setModalidad_Cobro(String Modalidad_Cobro) {
        this.Modalidad_Cobro = Modalidad_Cobro;
    }

    public Double getCostos_Para_La_Empresa() {
        if (Costos_Para_La_Empresa == null) {
            Costos_Para_La_Empresa = 0.0;
        }
        return Costos_Para_La_Empresa;
    }

    public void setCostos_Para_La_Empresa(Double Costos_Para_La_Empresa) {
        this.Costos_Para_La_Empresa = Costos_Para_La_Empresa;
    }

    public int getMes_Cobro() {
        return Mes_Cobro;
    }

    public void setMes_Cobro(int Mes_Cobro) {
       this.Mes_Cobro = Mes_Cobro;
    }

    public String getHoras_Extras_Jira() {

        return Horas_Extras_Jira;
    }

    public void setHoras_Extras_Jira(String Horas_Extras_Jira) {
        this.Horas_Extras_Jira = Horas_Extras_Jira;
    }

    public Double getTotal_Horas_Extras_Jira() {
        if (Total_Horas_Extras_Jira == null) {
            Total_Horas_Extras_Jira = 0.0;
        }
        return Total_Horas_Extras_Jira;
    }

    public void setTotal_Horas_Extras_Jira(Double Total_Horas_Extras_Jira) {
        this.Total_Horas_Extras_Jira = Total_Horas_Extras_Jira;
    }

    public String getEstado() {

        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getTipo_Sueldo() {
        return Tipo_Sueldo;
    }

    public void setTipo_Sueldo(String Tipo_Sueldo) {
        this.Tipo_Sueldo = Tipo_Sueldo;
    }

    public String getFacturable() {
        return Facturable;
    }

    public void setFacturable(String Facturable) {
        this.Facturable = Facturable;
    }

    public String getProductos_Actividad_Interna() {
        return Productos_Actividad_Interna;
    }

    public void setProductos_Actividad_Interna(String Productos_Actividad_Interna) {
        this.Productos_Actividad_Interna = Productos_Actividad_Interna;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }

    public Double getCostos() {
        if (Costos == null) {
            Costos = 0.0;
        }
        return Costos;
    }

    public void setCostos(Double Costos) {
        this.Costos = Costos;
    }

    public Double getHoras_dedicadas() {
        return Horas_dedicadas;
    }

    public void setHoras_dedicadas(Double Horas_dedicadas) {
        this.Horas_dedicadas = Horas_dedicadas;
    }

    public Double getTiempo_dedicado() {
        return Tiempo_dedicado;
    }

    public void setTiempo_dedicado(Double Tiempo_dedicado) {
        this.Tiempo_dedicado = Tiempo_dedicado;
    }

    public String getNro_Documento() {
        return nro_Documento;
    }

    public void setNro_Documento(String nro_Documento) {
        this.nro_Documento = nro_Documento;
    }

    public TablaJira(String fecha) {
        this.fecha = fecha;

    }

    public TablaJira() {

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getProyecto_jira() {
        return proyecto_jira;
    }

    public void setProyecto_jira(String proyecto_jira) {
        this.proyecto_jira = proyecto_jira;
    }

    public String getTipo() {

        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClave() {
        if ("".equals(clave)) {
            clave = "No hay registro";
        }
        return clave;
    }

    public void setClave(String clave) {
        if ("".equals(clave)) {
            clave = "No hay registro";
        }
        this.clave = clave;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Double getHoras_registradas_en_jira() {
        if (Horas_registradas_en_jira == null) {
            Horas_registradas_en_jira = 0.0;
        }
        return Horas_registradas_en_jira;
    }

    public void setHoras_registradas_en_jira(Double Horas_registradas_en_jira) {
        this.Horas_registradas_en_jira = Horas_registradas_en_jira;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public Double getHoras_laboradas_sin_extras() {
        if (Horas_laboradas_sin_extras == null) {
            Horas_laboradas_sin_extras = 0.0;
        }
        return Horas_laboradas_sin_extras;
    }

    public void setHoras_laboradas_sin_extras(double Horas_laboradas_sin_extras) {
        this.Horas_laboradas_sin_extras = Horas_laboradas_sin_extras;
    }

    public Double getHoras_incapacidades() {
        if (Horas_incapacidades == null) {
            Horas_incapacidades = 0.0;
        }
        return Horas_incapacidades;
    }

    public void setHoras_incapacidades(Double Horas_incapacidades) {
        this.Horas_incapacidades = Horas_incapacidades;
    }

    public Double getHoras_vacaciones() {
        if (Horas_vacaciones == null) {
            Horas_vacaciones = 0.0;
        }
        return Horas_vacaciones;
    }

    public void setHoras_vacaciones(Double Horas_vacaciones) {
        this.Horas_vacaciones = Horas_vacaciones;
    }

    public Double getHoras_lincencias() {
        if (Horas_licencias == null) {
            Horas_licencias = 0.0;
        }
        return Horas_licencias;
    }

    public void setHoras_licencias(Double Horas_licencias) {
        this.Horas_licencias = Horas_licencias;
    }

    public String getHoras_extras_Proyecto() {
        return Horas_extras_Proyecto;
    }

    public void setHoras_extras_Proyecto(String Horas_extras_Proyecto) {
        this.Horas_extras_Proyecto = Horas_extras_Proyecto;
    }

    public Double getCan_extras_Por_Ticket() {
        if (Can_extras_Por_Ticket == null) {
            Can_extras_Por_Ticket = 0.0;
        }
        return Can_extras_Por_Ticket;
    }

    public void setCan_extras_Por_Ticket(Double Can_extras_Por_Ticket) {
        this.Can_extras_Por_Ticket = Can_extras_Por_Ticket;
    }

    public Double getHoras_laboradas_sin_extras_sin_novedades() {
        if (Horas_laboradas_sin_extras_sin_novedades == null) {
            Horas_laboradas_sin_extras_sin_novedades = 0.0;
        }
        return Horas_laboradas_sin_extras_sin_novedades;
    }

    public void setHoras_laboradas_sin_extras_sin_novedades(Double Horas_laboradas_sin_extras_sin_novedades) {
        this.Horas_laboradas_sin_extras_sin_novedades = Horas_laboradas_sin_extras_sin_novedades;
    }

    public static Comparator<TablaJira> comparet = (TablaJira tb1, TablaJira tb2) -> {
        String n1;
        String n2;
        //instanciando el collator para que tenga en encuenta al momento
        // del ordenamiento alfabetico las tildes
        Collator collato = Collator.getInstance(new Locale("es", "ES"));

        if (tb1 == null || tb2 == null) {

            return 1;
        } else {
            //aca se esta asiendo la comparacion de los nombres para ordenarlo
            n1 = tb1.getNombres();
            n2 = tb2.getNombres();
            int com = collato.compare(n1, n2);

            return com;
        }
    };

}
