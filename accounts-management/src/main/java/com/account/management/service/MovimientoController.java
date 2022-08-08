package com.account.management.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.management.entity.Movimiento;
import com.account.management.repository.MovimientoRepository;
import com.accpunt.management.util.Response;

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin("*")
@RequestMapping(path="/movement")
@RestController
public class MovimientoController {
	
	@Autowired
    private MovimientoRepository movimientoRepository;
	
	@GetMapping(path="/queryMovement" , produces = "application/json") 
    public ResponseEntity<List<Movimiento>> queryMovement() {
		
		List<Movimiento> listMovimiento = movimientoRepository.findAll();
		
		if (listMovimiento.isEmpty()) {
			log.error("La consulta no retorno informacion");
            return ResponseEntity.noContent().build();
        }
	
	return ResponseEntity.ok(listMovimiento);
	
	}
	
	@GetMapping(path="/queryReport" , produces = "application/json") 
    public ResponseEntity<List<Object>> queryReport(@RequestParam(value = "user", required = true, defaultValue = "null") String user,
    												 @RequestParam(value = "startDate", required = true, defaultValue = "null") String startDate,
    												 @RequestParam(value = "endDate", required = true, defaultValue = "null") String endDate) throws ParseException {
		
	
		log.info(startDate);
		log.info(endDate);
		
		List<Object> report = movimientoRepository.getReport(user, startDate, endDate);
		
		if (report.isEmpty()) {
			log.error("La consulta no retorno informacion");
            return ResponseEntity.noContent().build();
        }
	
	return ResponseEntity.ok(report);
	
	}
	
	@PostMapping(path="/registerMovement" , produces = "application/json")
    public ResponseEntity<Response> registerMovement(@RequestBody Movimiento movimiento) {
       
	   String tipoMovimiento, mensaje = "";
	   Integer idAccount, saldo = 0;
	   Response response = new Response();	
		
	   log.info("/registerMovement: -->[ movimiento.getIdMovement = " + movimiento.getIdMovement() + "]");
	   log.info("/registerMovement: -->[ movimiento.getMovementType = " + movimiento.getMovementType() + "]");
       log.info("/registerMovement: -->[ movimiento.getBalance = " + movimiento.getBalance() + "]");
       log.info("/registerMovement: -->[ movimiento.getDate = " + movimiento.getDate() + "]");
       log.info("/registerMovement: -->[ movimiento.getValue = " + movimiento.getValue() + "]");  		
       
       idAccount = movimiento.getCuenta().iterator().next().getIdAccount();
   
       if(movimiento.getValue()==0) {
    	   
    	   response.setCode("-1");
	       response.setMessage("Transacciones con valor 0 no son permitidas");
	       response.setReasson("Transacciones con valor 0 no son permitidas");
	       response.setStatus("200");
	       
	       return ResponseEntity.ok(response);
	       
       }
       
       saldo = movimientoRepository.getBalance(idAccount);
       
       log.debug("idAccount: " + idAccount);
       log.debug("Transaccion de valor : " + movimiento.getValue());
       log.debug("SaldoDisponible: " + saldo);
       
       if(movimiento.getValue()>0) {
    	   
    	   tipoMovimiento = "Credito";
    	   
       }else{
    	   
    	   tipoMovimiento = "Debito";
    	   
    	   if(saldo<=0) {

    		   response.setCode("-2");
    	       response.setMessage("Por favor verifique su saldo disponible");
    	       response.setReasson("Por favor verifique su saldo disponible");
    	       response.setStatus("200");
    	       
    	       return ResponseEntity.ok(response);
    	       
    	   }
    	   
       }
       
       saldo = saldo + movimiento.getValue();
       
       log.debug("SaldoActual: " + saldo);
       
       movimiento.setMovementType(tipoMovimiento);
       movimiento.setBalance(saldo);
       movimientoRepository.save(movimiento);
       
       response.setCode("0");
       response.setMessage("Transaccion realizada de froma exitosa");
       response.setReasson("Transaccion realizada de froma exitosa");
       response.setStatus("200");
       
       return ResponseEntity.ok(response);
       
    }
	

	@PutMapping(path="/updateMovement/{id}" , produces = "application/json")
	public ResponseEntity<Movimiento> updateMovement(@RequestBody Movimiento movimiento, @PathVariable("id") Integer id) {
	   
		log.info("/updateMovement: -->[ movimiento.getIdMovement = " + movimiento.getIdMovement() + "]");
		log.info("/updateMovement: -->[ movimiento.getMovementType = " + movimiento.getMovementType() + "]");
	    log.info("/updateMovement: -->[ movimiento.getBalance = " + movimiento.getBalance() + "]");
	    log.info("/updateMovement: -->[ movimiento.getDate = " + movimiento.getDate() + "]");
	    log.info("/updateMovement: -->[ movimiento.getValue = " + movimiento.getValue() + "]");  		
	   	   
	   Optional<Movimiento> movement = movimientoRepository.findById(id);
	   
	   movement.get().setMovementType(movimiento.getMovementType());
	   movement.get().setBalance(movimiento.getBalance());
	   movement.get().setDate(movimiento.getDate());
	   movement.get().setValue(movimiento.getValue());
		
	   return ResponseEntity.ok(movimientoRepository.save(movement.get()));
	   
	}
	
	@DeleteMapping(path="/deleteMovement" , produces = "application/json")
	public String deleteMovement(@RequestParam(value = "id", required = true, defaultValue = "null") Integer id) {
	   
	   log.info("/deleteMovement: -->[ movimiento.getIdMovement = " + id + "]");
	   
	   movimientoRepository.deleteById(id);
		
	   return "{message:\"Registro eliminado de forma exitosa\"}";
	   
	}

}
