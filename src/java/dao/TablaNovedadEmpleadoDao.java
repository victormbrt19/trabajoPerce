/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.TablaNombres;
import entidades.TablaNovedadEmpleado;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conexion;

/**
 *
 * @author pcc
 */
public class TablaNovedadEmpleadoDao {

    PreparedStatement st;
    ResultSet res;
    Conexion con = null;
    List<TablaNombres> empleado = new ArrayList<>();

    public TablaNovedadEmpleadoDao(boolean cargarEmpleados) {
        if (cargarEmpleados) {
            this.empleado = cargarEmpleados();
        }
    }

    public TablaNovedadEmpleadoDao() {
    }

    private List<TablaNombres> cargarEmpleados() {
        try {
            con = new Conexion();

            String query1 = "SELECT * FROM tblempleado";
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
            Logger.getLogger(TablaNovedadEmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
    }

    public void BorrarDatos() {

        try {
            con = new Conexion();
            String query = "TRUNCATE tblnovedad_empleado ";
            st = con.getConn().prepareStatement(query);
            st.executeUpdate();

            con.commit();

        } catch (SQLException ex) {
            Logger.getLogger(TablaNovedadEmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int guardarArchivoNoveddades(TablaNovedadEmpleado No) {
        int bandera = 0;
        try {
            con = new Conexion();

            for (int i = 0; i < this.empleado.size(); i++) {

                String NombreN = this.empleado.get(i).getNombre_Novedades();
                String NombreD = No.getNombres();
                if (NombreN == null ? NombreD == null : NombreN.equals(NombreD)) {

                    String ide = this.empleado.get(i).getNro_Documento();
                    No.setNro_Documento(ide);

                    break;
                }

            }

            String query = "INSERT INTO tblnovedad_empleado (fecha,Cod_Novedad,nro_Documento,Nombres,Tipo_de_Novedad,fecha_inicio,fecha_fin,dias,horas) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ?)";
            st = con.getConn().prepareStatement(query);

            st.setString(1, No.getFecha());
            st.setInt(2, No.getCod_Novedad());
            st.setString(3, No.getNro_Documento());
            st.setString(4, No.getNombres());
            st.setString(5, No.getTipo_Novedad());
            st.setDate(6, (Date) No.getFecha_inicio());
            st.setDate(7, (Date) No.getFecha_fin());
            st.setInt(8, No.getDias());
            st.setDouble(9, No.getHoras());

            bandera = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return bandera;
    }

}
