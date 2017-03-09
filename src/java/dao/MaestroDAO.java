/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conexion;
import models.MaestroEmpleado;

/**
 *
 *
 * @author victormanuel
 */
public class MaestroDAO {

    public int insertarMaestro(MaestroEmpleado e) {

        int bandera = 0;
        Conexion con = null;

        try {
            con = new Conexion();

            String query = "INSERT INTO maestroempleado (identificacion, nombres, tipo) VALUES (?,?,?)";
            PreparedStatement st = con.getConn().prepareStatement(query);

            // Empieza desde 1, cada número coresponde a la posición de cada signo de interrogación (?)
            st.setInt(1, e.getIdentificacion());
            st.setString(2, e.getNombres());
            st.setString(3, e.getTipo());

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
