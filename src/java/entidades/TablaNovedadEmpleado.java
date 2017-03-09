/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author pcc
 */
public class TablaNovedadEmpleado {

    private String fecha;
    private int Cod_novedad;
    private String Nro_Documento;
    private String Nombres;
    private String Tipo_Novedad;
    private Date Fecha_inicio;
    private Date Fecha_fin;
    private int dias;
    private Double horas;

    public TablaNovedadEmpleado(String fecha) {

    }

    public TablaNovedadEmpleado() {

    }

    public String getFecha() {

        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCod_Novedad() {
        return Cod_novedad;
    }

    public void setCod_Novedad(int Cod_novedad) {
        this.Cod_novedad = Cod_novedad;
    }

    public String getNro_Documento() {
        return Nro_Documento;
    }

    public void setNro_Documento(String Nro_Documento) {
        this.Nro_Documento = Nro_Documento;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getTipo_Novedad() {
        return Tipo_Novedad;
    }

    public void setTipo_Novedad(String Tipo_Novedad) {
        this.Tipo_Novedad = Tipo_Novedad;
    }

    public Date getFecha_inicio() {
        return Fecha_inicio;
    }

    public void setFecha_inicio(Date Fecha_inicio) {
        this.Fecha_inicio = Fecha_inicio;
    }

    public Date getFecha_fin() {
        return Fecha_fin;
    }

    public void setFecha_fin(Date Fecha_fin) {
        this.Fecha_fin = Fecha_fin;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public static Comparator<TablaNovedadEmpleado> comparet = new Comparator<TablaNovedadEmpleado>() {
        @Override
        public int compare(TablaNovedadEmpleado tb1, TablaNovedadEmpleado tb2) {
            String n1;
            String n2;

            Collator collato = Collator.getInstance(new Locale("es", "Es"));

            if (tb1 == null || tb2 == null) {
                return 1;
            } else {

                n1 = tb1.getNombres();
                n2 = tb2.getNombres();
                int com = collato.compare(n1, n2);
                return com;
            }
        }
    };

}
