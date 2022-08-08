package com.account.management.service;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

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

import com.account.management.entity.Cliente;
import com.account.management.repository.ClienteRepository;

@Log4j2
@CrossOrigin("*")
@RequestMapping(path="/customer")
@RestController
public class ClienteController {
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	@GetMapping(path="/queryCustomer" , produces = "application/json") 
    public ResponseEntity<List<Cliente>> queryCustomer() {
		
		List<Cliente> listCliente = clienteRepository.findAll();
		
		if (listCliente.isEmpty()) {
			log.error("La consulta no retorno informacion");
            return ResponseEntity.noContent().build();
        }
	
	return ResponseEntity.ok(listCliente);
	
	}
	
	@PostMapping(path="/registerCustomer" , produces = "application/json")
    public ResponseEntity<Cliente> registerCustomer(@RequestBody Cliente cliente) { 
	
       log.info("/registerCustomer: -->[ cliente.getClientId = " + cliente.getClientId() + "]");
       log.info("/registerCustomer: -->[ cliente.getPassword = " + cliente.getPassword() + "]");
       log.info("/registerCustomer: -->[ cliente.getStatus = " + cliente.getStatus() + "]");
       
       cliente.setClientId(cliente.getName().substring(1,4));
       cliente.setPassword(cliente.getName().substring(1,4));
		
       return ResponseEntity.ok(clienteRepository.save(cliente));
       
    }
	

	@PutMapping(path="/updateCustomer/{id}" , produces = "application/json")
	public ResponseEntity<Cliente> updateCustomer(@RequestBody Cliente cliente, @PathVariable("id") Integer id) {
	   
	   log.info("/updateCustomer: -->[ cliente.getClientId = " + cliente.getClientId() + "]");
	   log.info("/updateCustomer: -->[ cliente.getPassword = " + cliente.getPassword() + "]");
	   log.info("/updateCustomer: -->[ cliente.getStatus = " + cliente.getStatus() + "]");  	
	   	   
	   Optional<Cliente> customer = clienteRepository.findById(id);
	   
	   customer.get().setClientId(cliente.getClientId());
	   customer.get().setPassword(cliente.getPassword());
	   customer.get().setStatus(cliente.getStatus());
		
	   return ResponseEntity.ok(clienteRepository.save(customer.get()));
	   
	}
	
	@PatchMapping(path="/inactivateCustomer" , produces = "application/json")
	public ResponseEntity<Cliente> inactivateCustomer(@RequestParam(value = "id", required = true, defaultValue = "null") Integer id,
													  @RequestParam(value = "status", required = true, defaultValue = "null") String status) {
	   
	   log.info("/inactivateCustomer: -->[ cliente.getClientId = " + id + "]");
	   
	   Optional<Cliente> customer = clienteRepository.findById(id);
	   
	   customer.get().setClientId(customer.get().getClientId());
	   customer.get().setPassword(customer.get().getPassword());
	   customer.get().setStatus(status);
		 
	   return ResponseEntity.ok(clienteRepository.save(customer.get()));
	   
	}
	
	@DeleteMapping(path="/deleteCustomer" , produces = "application/json")
	public String deleteCustomer(@RequestParam(value = "id", required = true, defaultValue = "null") Integer id) {
	   
	   log.info("/deleteCustomer: -->[ cliente.getClientId = " + id + "]");
	   
	   clienteRepository.deleteById(id);
		
	   return "{message:\"Registro eliminado de forma exitosa\"}";
	   
	}

}
