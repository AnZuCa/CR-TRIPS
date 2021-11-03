/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hp
 */
public class ConvertidorFechaSQL {

    public ConvertidorFechaSQL() {
    }
    public java.sql.Date Convertidor(Date fecha)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        String formattedDate = simpleDateFormat.format(fecha);

        java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);
        
        return date1;
    
    
    }
    public java.sql.Timestamp ConvertidorFechaHora(Date fecha)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String formattedDate = simpleDateFormat.format(fecha);
 
        java.sql.Timestamp date1 = java.sql.Timestamp.valueOf(formattedDate);
        
        return date1;
    
    
    }
}
