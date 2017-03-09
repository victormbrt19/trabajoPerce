package dao;

import entidades.TablaEmpleado;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conexion;

public class TablaEmpleadoDao {

    Conexion con = null;
    String query;
    Statement sta;
    PreparedStatement st;

    public void borrarDatos() {

        try {
            con = new Conexion();
            query = "TRUNCATE tblempleado";

            st = con.getConn().prepareStatement(query);
            st.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TablaEmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int GuardarDatosEmpleado(TablaEmpleado em) {
        int bandera = 0;

        try {
            con = new Conexion();
            query = "INSERT INTO tblempleado (nro_Documento , Nombre_Jira , Nombre_Contabilidad) values (? , ? , ?)";
            st = con.getConn().prepareStatement(query);

            st.setInt(1, em.getNro_Documento());
            st.setString(2, em.getNombre_Jira());
            st.setString(3, em.getNombre_Novedades());

            bandera = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TablaEmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return bandera;
    }
}
