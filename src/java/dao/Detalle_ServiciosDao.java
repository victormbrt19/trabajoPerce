package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conexion;

public class Detalle_ServiciosDao {

    Conexion con;

    PreparedStatement st;
    String query;
    ResultSet res;

    public ResultSet CargarDatos() {

        try {

            con = new Conexion();
            query = "SELECT  Nro_Documento,Nombres,Horas_registradas_en_jira,Proyecto,Horas_laboradas_sin_extras_y_sin_novedades FROM tbljira ORDER BY Proyecto";
            st = con.getConn().prepareStatement(query);

            res = st.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(Detalle_ServiciosDao.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return res;
    }
}
