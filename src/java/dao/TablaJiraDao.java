package dao;

import entidades.Especialidad;
import entidades.HorasExtras;
import entidades.TablaListaDeProyectos;
import entidades.TablaCostos;
import entidades.TablaJira;
import entidades.TablaNombres;
import entidades.TablaNovedadEmpleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.Conexion;

public class TablaJiraDao {

    Conexion con;
    PreparedStatement st;
    String query;
    Statement sta;
    ResultSet res;

    Double total2 = 0.0;
    Double sumaVacaciones = 0.0;
    Double sumaIncapacidades = 0.0;
    Double sumaLincencias = 0.0;
    Double total1 = 0.0;
    Double sumaTotal = 0.0;
    String IdentificacionNove = "";
    String tipo = "";
    Double hi = 0.0;
    Double HorasTotal_SinNada = 0.0;
    Double SumaTotal_Costos = 0.0;
    Double horastotal = 0.0;
    String nr = "";
    String proyecto = "";
    String Tipo_sueldo = "";
    Double valorIncapacidad = 0.0;
    Double costosPersonas = 0.0;
    Double horastotalin = 0.0;
    Double TotalValor_PorIncapacidad = 0.0;
    String nrn = "";
    Double totalcostosc2 = 0.0;
    String Tipo_Novedad = "";
    Double HorasResgistradasJira = 0.0;
    String facturablesi = "SI";
    String facturableno = "NO";
    String Actividad = "";
    String bug = "Defectos de fabrica";
    String TipoSueldoExtras = "Horas_Extras_y_Recargos";
    String TipoSueldoVin = "Vinculado";

    List<TablaNovedadEmpleado> novedades = new ArrayList<>();
    List<TablaNombres> empleado = new ArrayList<>();
    List<TablaCostos> Costos = new ArrayList<>();
    List<HorasExtras> Extras = new ArrayList<>();
    List<Especialidad> especialidad = new ArrayList<>();
    List<TablaListaDeProyectos> proyectos = new ArrayList<>();

    public void borrarDatos() {

        try {

            con = new Conexion();
            query = "TRUNCATE  tbljira";
            st = con.getConn().prepareStatement(query);

            st.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TablaJiraDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public TablaJiraDao(boolean cargarEmpleados) {
        if (cargarEmpleados) {
            this.empleado = cargarEmpleados();
            this.novedades = CargarEmpleados();
            this.Costos = Cargarempleados();
            this.Extras = cargarempleados();
            this.especialidad = cargarEmpleadoS();
            this.proyectos = CargarEmpleadoS();
        }
    }
    //TABLA TBLNOVEDAD_EMPLEADO 

    private List<TablaNovedadEmpleado> CargarEmpleados() {
        try {
            con = new Conexion();
            query = "SELECT  nro_Documento, Tipo_de_novedad , horas from tblnovedad_empleado";
            st = con.getConn().prepareStatement(query);
            res = st.executeQuery();

            while (res.next()) {
                TablaNovedadEmpleado e = new TablaNovedadEmpleado();
                e.setNro_Documento(res.getString("nro_Documento"));
                e.setTipo_Novedad(res.getString("Tipo_de_novedad"));
                e.setHoras(res.getDouble("horas"));
                novedades.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TablaJiraDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return novedades;

    }

    //TABLA TBLEMPLEAD SELECCIONANDO LOS NOMBRES CON SUS RESPECTIVAS
    //CEDULAS PARA ASIGNARLAR A LOS REGISTROS DE LA TABLA JIRA
    private List<TablaNombres> cargarEmpleados() {
        try {
            con = new Conexion();

            String query1 = "SELECT * FROM tblempleado order by Nombre_Jira";
            st = con.getConn().prepareStatement(query1);
            res = st.executeQuery();

            while (res.next()) {
                TablaNombres n = new TablaNombres();

                n.setNro_Documento(res.getString("nro_Documento"));
                n.setNombre_Jira(res.getString("Nombre_Jira"));
                n.setNombre_Novedades(res.getString("Nombre_Contabilidad"));
                empleado.add(n);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        } finally {
            if (con != null) {
                con.close();
            }
        }
        return empleado;
    }

    //SELECCIONANDO LOS DATOS DE LA TABLA LISTA_PROYECTOS
    //PARA AL ASIGNACION DEL PRODUCTO
    private List<TablaListaDeProyectos> CargarEmpleadoS() {
        try {
            con = new Conexion();
            query = "SELECT Proyectos_Conceptos, Producto FROM lista_proyectos order by Proyectos_Conceptos";
            sta = con.getConn().prepareStatement(query);
            res = sta.executeQuery(query);
            while (res.next()) {
                TablaListaDeProyectos li = new TablaListaDeProyectos();

                li.setProyectosyConceptos(res.getString("Proyectos_Conceptos"));
                li.setProductos(res.getString("Producto"));
                proyectos.add(li);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TablaJiraDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return proyectos;
    }

    //SELECCIONANDO LOS DATOS DE LA TABLA COSTOS PARA CALCULAR EL COSTO POR PESONA
    //MIRANDO SI TUVO NOVEDADES
    private List<TablaCostos> Cargarempleados() {

        try {
            con = new Conexion();
            query = "SELECT Nro_Documento, Tipo_Sueldo ,Valor_incapacidades , Total FROM tblcostos where Nro_Documento is not null order by Nro_Documento";
            st = con.getConn().prepareStatement(query);
            res = st.executeQuery();

            while (res.next()) {
                TablaCostos c = new TablaCostos();
                c.setNro_Documento(res.getString("Nro_Documento"));
                c.setTipoDeSueldo(res.getString("Tipo_Sueldo"));
                c.setIncapacidades(res.getDouble("Valor_incapacidades"));
                c.setTotal(res.getDouble("Total"));
                this.Costos.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TablaJiraDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return Costos;
    }

    public TablaJiraDao() {

    }

    //SELECCIONANDO DATOS DE LA TABLA ESPECIALIDAD PARA LA ASIGNACION DE DICHA ESPECIALIDAD
    //AL EMPLEADO CORRESPONDIENTE
    private List<Especialidad> cargarEmpleadoS() {
        try {
            con = new Conexion();
            query = "SELECT Nro_Documento , Especialidad FROM especialidad  where Nro_Documento is not null";
            sta = con.getConn().prepareStatement(query);
            res = sta.executeQuery(query);

            while (res.next()) {
                Especialidad es = new Especialidad();
                es.setNro_Documento(res.getString("Nro_Documento"));
                es.setEspecialidad(res.getString("Especialidad"));
                especialidad.add(es);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaJiraDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return especialidad;

    }

    //SELECCIONANDO LOS DATOS DE LA HORAS EXTRAS PARA DESPUES CALCULAR EL COSTO
    // Y COBRO DE HORAS EXTRAS POR CADA TICKET Y EMPLEADO
    private List<HorasExtras> cargarempleados() {
        try {
            con = new Conexion();

            query = "SELECT Nro_Documento , Ticket , Valor from horas_extras";
            sta = con.getConn().prepareStatement(query);
            res = sta.executeQuery(query);

            while (res.next()) {
                HorasExtras extras = new HorasExtras();
                extras.setNro_Documento(res.getString("Nro_Documento"));
                extras.setTikect(res.getString("Ticket"));
                extras.setValor(res.getDouble("Valor"));
                Extras.add(extras);

            }

        } catch (SQLException ex) {
            Logger.getLogger(TablaJiraDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return Extras;
    }

    //ASIGNACION DE LAS CEDULAS A CADA EMPLEADO EN LA TABLA JIRA
    public boolean insertarTablaJira(TablaJira ji) {
        int bandera = 0;
        boolean sw = true;
        try {
            con = new Conexion();
            int Empleado = this.empleado.size();

            for (int i = 0; i < Empleado; i++) {
                String nombre = this.empleado.get(i).getNombre_Jira();

                String nombret = (ji.getNombres());

                if (nombre == null ? nombret == null : nombre.equals(nombret)) {

                    String iden = this.empleado.get(i).getNro_Documento();
                    ji.setNro_Documento(iden);
                    break;
                }

            }

            int NovedadesE = this.novedades.size();

            //MIRAR EL TIPO DE NOVEDAD DE CADA EMPLEADO SUMA LA CANTIDAD DE HORAS
            // DEL TIPO DE NOVEDAD Y AGINARLAS AL EMPLEADO CORRESPONDIENTE
            for (int j = 0; j < NovedadesE; j++) {

                IdentificacionNove = this.novedades.get(j).getNro_Documento();
                nr = (ji.getNro_Documento());
                tipo = this.novedades.get(j).getTipo_Novedad();
                hi = this.novedades.get(j).getHoras();
                HorasTotal_SinNada = ji.getHoras_laboradas_sin_extras();

                if (IdentificacionNove == null ? nr == null : IdentificacionNove.equals(nr)) {

                    if ("Vacaciones".equals(tipo)) {
                        sumaVacaciones = hi;

                        ji.setHoras_vacaciones(hi);

                    }
                    if ("Incapacidad".equals(tipo)) {
                        sumaIncapacidades = hi;

                        ji.setHoras_incapacidades(hi);

                    }
                    if ("Licencia no Rem.".equals(tipo)) {
                        sumaLincencias = hi;

                        ji.setHoras_licencias(hi);

                    }
                    sumaTotal = sumaVacaciones + sumaIncapacidades + sumaLincencias;
                    total1 = HorasTotal_SinNada - sumaTotal;
                    total2 = total1;

                }

                if (total1 == 0.0) {

                    ji.setHoras_laboradas_sin_extras_sin_novedades(HorasTotal_SinNada);

                } else {
                    ji.setHoras_laboradas_sin_extras_sin_novedades(total2);

                }

                if (nr == null ? IdentificacionNove != null : !nr.equals(IdentificacionNove)) {

                    sumaVacaciones = 0.0;
                    sumaIncapacidades = 0.0;
                    sumaLincencias = 0.0;
                    sumaTotal = 0.0;

                }
                if (j + 1 == this.novedades.size()) {
                    total1 = 0.0;
                }
            }

            //FOR PARA CALCULAR EL COSTOS DE LOS EMPLEADOS MIRANDO EL TIPO DE NOVEDAD
            //PARA HACER EL RESPECTIVO CALCULO
            int Costosp = this.Costos.size();
            int Novedadesp = this.novedades.size();
            fuera:
            for (int x = 0; x < Costosp; x++) {

                costosPersonas = this.Costos.get(x).getTotal();
                Tipo_sueldo = this.Costos.get(x).getTipoDeSueldo();
                valorIncapacidad = this.Costos.get(x).getIncapacidades();
                nr = this.Costos.get(x).getNro_Documento();
                proyecto = ji.getProyecto();
                String Identificacionjira = ji.getNro_Documento();
                horastotalin = ji.getHoras_incapacidades();
                HorasResgistradasJira = ji.getHoras_registradas_en_jira();
                horastotal = ji.getHoras_laboradas_sin_extras_sin_novedades();
                String TipoSueldo = Tipo_sueldo.replaceAll(" ", "");

                if (nr.equals(Identificacionjira)) {

                    String proyectName = proyecto.toLowerCase();

                    Pattern pat = Pattern.compile(".*horas-extras.*");
                    Matcher mat = pat.matcher(proyectName);
                    switch (TipoSueldo) {
                        case "SUELDOS":
                            if (mat.matches()) {
                                ji.setTipo_Sueldo(TipoSueldoExtras);
                            } else {
                                ji.setTipo_Sueldo(TipoSueldoVin);
                            }
                            break;
                        case "SERVICIOS":
                            if (mat.matches()) {
                                ji.setTipo_Sueldo(TipoSueldoExtras);
                            } else {
                                ji.setTipo_Sueldo(Tipo_sueldo);
                            }
                            break;
                        default:
                            if (!mat.matches()) {
                                ji.setTipo_Sueldo(TipoSueldoVin);
                            } else {
                                ji.setTipo_Sueldo(Tipo_sueldo);
                            }
                    }

                    for (int j = 0; j < Novedadesp; j++) {
                        Tipo_Novedad = this.novedades.get(j).getTipo_Novedad();
                        nrn = this.novedades.get(j).getNro_Documento();

                        if (Identificacionjira.equals(nr) && Identificacionjira.equals(nrn)) {
                            if (!mat.matches() && !"Incapacidades".equals(proyecto) && !"Licencia no Rem.".equals(proyecto) && !"Vacaciones".equals(proyecto)) {
                                if (TipoSueldo.equals(TipoSueldo)) {
                                    if ("Incapacidad".equals(Tipo_Novedad)) {

                                        SumaTotal_Costos = HorasResgistradasJira / horastotal;

                                        TotalValor_PorIncapacidad = SumaTotal_Costos * (costosPersonas - valorIncapacidad);

                                        ji.setCostos(TotalValor_PorIncapacidad);
                                        break fuera;

                                    } else {
                                        SumaTotal_Costos = HorasResgistradasJira / horastotal;
                                        TotalValor_PorIncapacidad = SumaTotal_Costos * costosPersonas;

                                        ji.setCostos(TotalValor_PorIncapacidad);
                                        break fuera;
                                    }
                                } else {

                                    ji.setCostos(totalcostosc2);
                                    break fuera;
                                }

                            }
                            if ("Incapacidades".equals(proyecto)) {
                                SumaTotal_Costos = HorasResgistradasJira / horastotalin;
                                TotalValor_PorIncapacidad = SumaTotal_Costos * valorIncapacidad;
                                ji.setCostos(TotalValor_PorIncapacidad);
                                break fuera;
                            } else {
                                ji.setCostos(totalcostosc2);
                                break fuera;
                            }

                        }
                        if (j + 1 == this.novedades.size()) {
                            if (!mat.matches() && !"Incapacidades".equals(proyecto) && !"Licencia no Rem.".equals(proyecto) && !"Vacaciones".equals(proyecto)) {
                                if (TipoSueldo.equals(TipoSueldo)) {
                                    SumaTotal_Costos = HorasResgistradasJira / horastotal;
                                    TotalValor_PorIncapacidad = SumaTotal_Costos * costosPersonas;

                                    ji.setCostos(TotalValor_PorIncapacidad);
                                    break fuera;
                                } else {
                                    ji.setCostos(totalcostosc2);
                                    break fuera;
                                }
                            } else {
                                ji.setCostos(totalcostosc2);
                                break fuera;
                            }
                        } else {

                        }

                    }
                }

            }

            // ASIGNACION DE LAS ESPECIALIDADES DE LOS EMPLEADOS
            for (int z = 0; z < this.especialidad.size(); z++) {
                String Nro_Documento = this.especialidad.get(z).getNro_Documento();
                String Nombre = this.especialidad.get(z).getEspecialidad();
                String Documento = ji.getNro_Documento();

                if (Nro_Documento.equals(Documento)) {
                    ji.setEspecialidad(Nombre);
                    break;
                }

            }

            //ASIGNACION  DEL PRODUCTO Y/O ACTIVIDAD INTERNA Y
            //SI EL PROYECTO ES FACTURABLE O NO 
            for (int i = 0; i < this.proyectos.size(); i++) {

                String Conceptos = this.proyectos.get(i).getProyectosyConceptos();
                String Productos = this.proyectos.get(i).getProductos();
                String proyectojira = ji.getProyecto();
                String tipojira = ji.getTipo();
                String Conceptos2 = Conceptos.replaceAll(" ", "");
                String projira = proyectojira.replaceAll(" ", "");
                String prueba = Conceptos2.replaceAll("-", "");
                String prueba2 = projira.replaceAll("-", "");
                String Conceptosm = prueba.toUpperCase();
                String proyecjira = prueba2.toUpperCase();

                if (Conceptosm.equals(proyecjira)) {
                    ji.setProductos_Actividad_Interna(Productos);

                    Actividad = ji.getProductos_Actividad_Interna();
                    if (Actividad.equals("FÁBRICA") & tipojira == null) {
                        ji.setFacturable(facturablesi);
                        break;
                    }
                    if (Actividad.equals("PROYECTO") || Actividad.equals("OUT. S.REAL") || Actividad.equals("FÁBRICA")) {
                        if (tipojira == null) {
                            ji.setFacturable(facturablesi);
                            break;
                        }
                        if (Actividad.equals("FÁBRICA") & tipojira.equals("Bug")) {
                            ji.setProductos_Actividad_Interna(bug);
                            ji.setFacturable(facturableno);
                        }

                    }
                    if (tipojira == null) {
                        if (Actividad.equals("PROYECTO") || Actividad.equals("OUT. S.REAL") || Actividad.equals("FÁBRICA")) {
                            ji.setFacturable(facturablesi);
                            break;
                        } else {
                            ji.setFacturable(facturableno);
                            break;
                        }
                    }
                    if (Actividad.equals("FÁBRICA") & tipojira.equals("Historia")) {
                        ji.setFacturable(facturablesi);
                        break;
                    }
                    if (Actividad.equals("FÁBRICA") & tipojira.equals("Soporte")) {
                        ji.setFacturable(facturablesi);
                        break;
                    }
                    if (Actividad.equals("PROYECTO") || Actividad.equals("OUT. S.REAL") || Actividad.equals("FÁBRICA") & tipojira.equals("Sub-task")) {
                        ji.setFacturable(facturablesi);
                        break;
                    } else {
                        ji.setFacturable(facturableno);
                        break;
                    }

                }
            }

//            for (int x = 0; x < ExtrasE; x++) {
//                String Identificacion = this.Extras.get(x).getNro_Documento();
//                String Ticke = this.Extras.get(x).getTicket();
//                Double Valor = this.Extras.get(x).getValor();
//                Double TotalTicketValor = this.Extras.get(x).getTotalValorTicket();
//                Double SumaTicket = this.Extras.get(x).getHoras_Extras_Por_Ticket();
//                String HorasExtras = ji.getHoras_extras();
//                Double CantidadExtras = ji.getCan_extras();
//
//                String Nro = ji.getNro_Documento();
//                String TickeJira = ji.getClave();
//                if (HorasExtras.equals("Horas extras") & Identificacion.equals(Nro)) {
//                    if (Ticke.equals(TickeJira)) {
//                        CobroExtra = CantidadExtras / SumaTicket;
//                        TotalCobro = CobroExtra * TotalTicketValor;
//                    } else {
//
//                    }
//                }
//
//            }
            query = "INSERT INTO tbljira ( Fecha , Nro_Documento , Posicion , Proyecto_jira , Tipo , Ticket , Titulo , Estado ,  Horas_Extras_Jira , Total_Horas_Extras_Jira , Nombres , Horas_registradas_en_jira , Proyecto , Especialidad , Tipo_Sueldo , Productos_Actividad_Interna , Facturable , Horas_extras_Proyecto , Can_extras , Horas_laboradas_sin_extras , Horas_incapacidades , Horas_vacaciones , Horas_Licencias , Horas_laboradas_sin_extras_y_sin_novedades , Costos ) values ( "
                    + " ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            st = con.getConn().prepareStatement(query);

            st.setString(1, ji.getFecha());
            st.setString(2, ji.getNro_Documento());
            st.setInt(3, ji.getPosicion());
            st.setString(4, ji.getProyecto_jira());
            st.setString(5, ji.getTipo());
            st.setString(6, ji.getClave());
            st.setString(7, ji.getTitulo());
            st.setString(8, ji.getEstado());
            st.setString(9, ji.getHoras_Extras_Jira());
            st.setDouble(10, ji.getTotal_Horas_Extras_Jira());
            st.setString(11, ji.getNombres());
            st.setDouble(12, ji.getHoras_registradas_en_jira());
            st.setString(13, ji.getProyecto());
            st.setString(14, ji.getEspecialidad());
            st.setString(15, ji.getTipo_Sueldo());
            st.setString(16, ji.getProductos_Actividad_Interna());
            st.setString(17, ji.getFacturable());
            st.setString(18, ji.getHoras_extras_Proyecto());
            st.setDouble(19, ji.getCan_extras());
            st.setDouble(20, ji.getHoras_laboradas_sin_extras());
            st.setDouble(21, ji.getHoras_incapacidades());
            st.setDouble(22, ji.getHoras_vacaciones());
            st.setDouble(23, ji.getHoras_lincencias());
            st.setDouble(24, ji.getHoras_laboradas_sin_extras_sin_novedades());
            st.setDouble(25, ji.getCostos());

            st.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TablaJiraDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return true;
    }

}
