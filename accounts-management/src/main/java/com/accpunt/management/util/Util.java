package com.accpunt.management.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.account.management.entity.Reporte;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Util {
	
	public Date obtenerFechaActual() throws ParseException {
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        Date fecha = calendar.getTime();
        String fechaString = formatoFecha.format(fecha);
        
        Date fechaActual = formatoFecha.parse(fechaString);
        
        log.info("Fecha Actual --> " + fechaActual);
        
		return fechaActual;
	}
	
    public Date formatearFecha(String fechaProceso) throws ParseException {
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        
        Date fecha = formatoFecha.parse(fechaProceso);
        
        log.info("Fecha Actual --> " + fecha);
        
		return fecha;
	}
    
   public Date formatearFechaBase(String fecha) throws ParseException {
	
	    //SimpleDateFormat formatoFechaC = new SimpleDateFormat("dd/MM/yyyy");
	    SimpleDateFormat formatoFechaC = new SimpleDateFormat("yyyy-MM-dd");
	    
	    String fechaString = formatoFechaC.format(fecha);
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        
        Date fechaBase = formatoFecha.parse(fechaString);
        
        log.info("Fecha Base --> " + fechaBase);
        
		return fechaBase;
	}

}
