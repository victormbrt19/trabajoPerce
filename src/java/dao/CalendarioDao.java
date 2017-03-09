package dao;

import entidades.Calendario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conexion;

public class CalendarioDao {

    public int IngresarFecha(Calendario c) {
        int bandera = 0;
        String query;
        Conexion con = null;

        try {
            con = new Conexion();
            query = "INSERT INTO año_mes (fecha) values (?)";
            PreparedStatement st = con.getConn().prepareStatement(query);

            st.setString(1, c.getFecha());

            bandera = st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }

        }
        return bandera;
    }
}
