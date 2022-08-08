package com.account.management.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.management.entity.Cuenta;
import com.account.management.entity.Movimiento;
import com.account.management.repository.CuentaRepository;
import com.account.management.repository.MovimientoRepository;
import com.accpunt.management.util.Response;
import com.accpunt.management.util.Util;

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin("*")
@RequestMapping(path="/account")
@RestController
public class CuentaController {
	
	@Autowired
    private CuentaRepository cuentaRepository;
	
	@Autowired
    private MovimientoRepository movimientoRepository;
	
	@GetMapping(path="/queryAccount" , produces = "application/json") 
    public ResponseEntity<List<Cuenta>> queryAccount() {
		
		List<Cuenta> listCuenta = cuentaRepository.findAll();
		
		if (listCuenta.isEmpty()) {
			log.error("La consulta no retorno informacion");
            return ResponseEntity.noContent().build();
        }
	
	return ResponseEntity.ok(listCuenta);
	
	}
	
	@PostMapping(path="/registerAccount" , produces = "application/json")
    public ResponseEntity<Response> registerAccount(@RequestBody Cuenta cuenta) throws ParseException {
       
	   log.info("/registerAccount: -->[ cuenta.getIdAccount = " + cuenta.getIdAccount() + "]");
	   log.info("/registerAccount: -->[ cuenta.getInitialBalance = " + cuenta.getInitialBalance() + "]");
       log.info("/registerAccount: -->[ cuenta.getAccountNumber = " + cuenta.getAccountNumber() + "]");
       log.info("/registerAccount: -->[ cuenta.getAccountType = " + cuenta.getAccountType() + "]");
       log.info("/registerAccount: -->[ cuenta.getStatus = " + cuenta.getStatus() + "]");  		
    	
       Cuenta account = cuentaRepository.save(cuenta);
       List<Cuenta> cuentaN = new ArrayList<Cuenta>();
       cuentaN.add(account);
       Set<Cuenta> setCuenta = new HashSet<Cuenta>(cuentaN); 
       
       Movimiento movement = new Movimiento();
       Util util = new Util();
       
       movement.setDate(util.obtenerFechaActual());
       movement.setMovementType("Credito");
       movement.setBalance(cuenta.getInitialBalance());
       movement.setValue(cuenta.getInitialBalance());
       movement.setCuenta(setCuenta);
       
       movimientoRepository.save(movement);
       
       Response response = new Response();
       
       response.setCode("0");
       response.setMessage("Cuenta registrada de forma exitosa");
       response.setReasson("Cuenta registrada de forma exitosa");
       response.setStatus("200");
       
       return ResponseEntity.ok(response);
       
    }
	

	@PutMapping(path="/updateAccount/{id}" , produces = "application/json")
	public ResponseEntity<Cuenta> updateAccount(@RequestBody Cuenta cuenta, @PathVariable("id") Integer id) {
	   
		log.info("/updateAccount: -->[ cuenta.getIdAccount = " + cuenta.getIdAccount() + "]");
		log.info("/updateAccount: -->[ cuenta.getInitialBalance = " + cuenta.getInitialBalance() + "]");
	    log.info("/updateAccount: -->[ cuenta.getAccountNumber = " + cuenta.getAccountNumber() + "]");
	    log.info("/updateAccount: -->[ cuenta.getAccountType = " + cuenta.getAccountType() + "]");
	    log.info("/updateAccount: -->[ cuenta.getStatus = " + cuenta.getStatus() + "]");  		
	   	   
	   Optional<Cuenta> account = cuentaRepository.findById(id);
	   
	   account.get().setInitialBalance(cuenta.getInitialBalance());
	   account.get().setAccountNumber(cuenta.getAccountNumber());
	   account.get().setAccountType(cuenta.getAccountType());
	   account.get().setStatus(cuenta.getStatus());
		
	   return ResponseEntity.ok(cuentaRepository.save(account.get()));
	   
	}
	
	@PatchMapping(path="/inactivateAccount" , produces = "application/json")
	public ResponseEntity<Cuenta> inactivateAccount(@RequestParam(value = "id", required = true, defaultValue = "null") Integer id,
													@RequestParam(value = "status", required = true, defaultValue = "null") String status) {
	   
	   log.info("/inactivateAccount: -->[ cuenta.getIdAccount = " + id + "]");
	   
	   Optional<Cuenta> account = cuentaRepository.findById(id);
	   
	   account.get().setInitialBalance(account.get().getInitialBalance());
	   account.get().setAccountNumber(account.get().getAccountNumber());
	   account.get().setAccountType(account.get().getAccountType());
	   account.get().setStatus(status);
		 
	   return ResponseEntity.ok(cuentaRepository.save(account.get()));
	   
	}
	
	@DeleteMapping(path="/deleteAccount" , produces = "application/json")
	public String deleteAccount(@RequestParam(value = "id", required = true, defaultValue = "null") Integer id) {
	   
	   log.info("/deleteAccount: -->[ cuenta.getIdAccount = " + id + "]");
	   
	   cuentaRepository.deleteById(id);
		
	   return "{message:\"Registro eliminado de forma exitosa\"}";
	   
	}

}
