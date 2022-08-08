package com.account.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.account.management.entity.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{
	
	@Query(nativeQuery = true,value = " select b.balance from cuenta_movimiento a, movimiento b where a.id_account = ?1 and a.id_movement = b.id_movement order by b.id_movement Desc LIMIT 1")
    Integer getBalance(Integer idAccount);
	
	//@Query(nativeQuery = false,value = " select a.balance from Movimiento a JOIN a.cuenta b where b.idAccount = ?1 order by a.idMovement Desc")

	@Query(nativeQuery = true,
		   value = " select DATE_FORMAT(d.date, '%d/%m/%Y'),e.name, b.account_number, b.account_type, b.initial_balance, b.status, d.value, d.balance "
		   		   + " from cliente a, "
		   		   + "      cuenta b, "
		   		   + "      cuenta_movimiento c, "
		   		   + "      movimiento d, "
		   		   + "      persona e "
		   		   + " where a.client_id = ?1 "
		   		   + "  and DATE(d.date) Between STR_TO_DATE(?2,'%d/%m/%Y') and STR_TO_DATE(?3,'%d/%m/%Y') "
		   		   + "  and a.id = e.id "
		   		   + "  and e.id = b.id_cliente "
		   		   + "  and a.id = b.id_cliente "
		   		   + "  and b.id_account = c.id_account "
		   		   + "  and c.id_movement = d.id_movement "
		   		   + " group by b.account_number,b.account_type, b.initial_balance, b.status, d.value, d.balance "
		   		   + " order by d.date,b.account_number Asc")
    List<Object> getReport(String user, String startDate, String endDate);
	
}
