/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.GenerarFechas;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conexion;

/**
 *
 * @author pcc
 */
public class GenerarFechasDao {

    PreparedStatement sta;
    Conexion con;
    String query;

    public int GuardarFechas(GenerarFechas m) {
        int bandera = 0;

        try {
            con = new Conexion();

            query = "INSERT INTO generarmeses (Año_Meses) values (?)";
            sta = con.getConn().prepareStatement(query);

            sta.setString(1, m.getYear_Meses());
            bandera = sta.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(GenerarFechasDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return bandera;
    }

    
    
}
