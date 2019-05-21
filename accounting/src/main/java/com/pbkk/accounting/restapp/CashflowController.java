package com.pbkk.accounting.restapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbkk.accounting.aop.TokenRequired;
import com.pbkk.accounting.model.Cashflow;
import com.pbkk.accounting.model.Transaksi;
import com.pbkk.accounting.model.Cashflow;
import com.pbkk.accounting.repository.CashflowRepository;
import com.pbkk.accounting.service.CashflowService;
import com.pbkk.accounting.service.SendAPI;

@RestController
@RequestMapping("/cashflow")

public class CashflowController {
	@Autowired
	CashflowRepository cashflowRepository;
	@Autowired
	CashflowService cashflowService;
	@Autowired
	SendAPI sendAPI;
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	@TokenRequired
	public ArrayList<Map<String, Object>> getCashflows(){
		return cashflowService.getCashflows((ArrayList<Cashflow>)cashflowRepository.findAll());
	}
	
	@ResponseBody
	@RequestMapping("/customer")
	@TokenRequired
	public ArrayList<Map<String, Object>> getCustomerCashflows(){
		ArrayList<Cashflow> source=cashflowRepository.findBySource("customer");
		ArrayList<Cashflow> destination=cashflowRepository.findByDestination("customer");
		source.addAll(destination);
		return cashflowService.getCashflows(source);
	}
	
	@ResponseBody
	@RequestMapping("/customer/{id}")
	@TokenRequired
	public ArrayList<Map<String, Object>> getCustomerCashflowsbyId(
			@PathVariable(value = "id") Long id, 
			@RequestParam(required = false) String dateStart,
			@RequestParam(required = false) String dateEnd, 
			@RequestParam(required = false) String partner, 
			@RequestParam(required = false) Long partnerId) throws ParseException{
		ArrayList<Cashflow> source=cashflowRepository.findBySourceAndSourceId("customer",id);
		ArrayList<Cashflow> destination=cashflowRepository.findByDestinationAndDestinationId("customer",id);
		source.addAll(destination);
		if(dateStart!=null&&dateEnd!=null) {
			SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
			Date start = inFormat.parse(dateStart);
			Date end = inFormat.parse(dateEnd);
			source=cashflowService.filterByDate(source, start, end);
		}
		if(partner!=null&&partnerId!=null) {
			if(partner=="restaurant") {
				source=cashflowService.filterByRestaurantId(source, partnerId);
			}
			if(partner=="customer") {
				source=cashflowService.filterByCustomerId(source, partnerId);
			}
			if(partner=="driver") {
				source=cashflowService.filterByDriverId(source, partnerId);
			}
		}
		return cashflowService.getCashflows(source);
	}
	
	@ResponseBody
	@RequestMapping("/restaurant")
	@TokenRequired
	public ArrayList<Map<String, Object>> getRestaurantCashflows(){
		ArrayList<Cashflow> source=cashflowRepository.findBySource("restaurant");
		ArrayList<Cashflow> destination=cashflowRepository.findByDestination("restaurant");
		source.addAll(destination);
		return cashflowService.getCashflows(source);
	}
	
	@ResponseBody
	@RequestMapping("/restaurant/{id}")
	@TokenRequired
	public ArrayList<Map<String, Object>> getRestaurantCashflowsbyId(
			@PathVariable(value = "id") Long id,
			@RequestParam(required = false) String dateStart,
			@RequestParam(required = false) String dateEnd, 
			@RequestParam(required = false) String partner, 
			@RequestParam(required = false) Long partnerId) throws ParseException{
		ArrayList<Cashflow> source=cashflowRepository.findBySourceAndSourceId("restaurant",id);
		ArrayList<Cashflow> destination=cashflowRepository.findByDestinationAndDestinationId("restaurant",id);
		source.addAll(destination);
		if(dateStart!=null&&dateEnd!=null) {
			SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
			Date start = inFormat.parse(dateStart);
			Date end = inFormat.parse(dateEnd);
			source=cashflowService.filterByDate(source, start, end);
		}
		if(partner!=null&&partnerId!=null) {
			if(partner=="restaurant") {
				source=cashflowService.filterByRestaurantId(source, partnerId);
			}
			if(partner=="customer") {
				source=cashflowService.filterByCustomerId(source, partnerId);
			}
			if(partner=="driver") {
				source=cashflowService.filterByDriverId(source, partnerId);
			}
		}
		return cashflowService.getCashflows(source);
	}
	
	@ResponseBody
	@RequestMapping("/driver")
	@TokenRequired
	public ArrayList<Map<String, Object>> getDriverCashflows(){
		ArrayList<Cashflow> source=cashflowRepository.findBySource("driver");
		ArrayList<Cashflow> destination=cashflowRepository.findByDestination("driver");
		source.addAll(destination);
		return cashflowService.getCashflows(source);
	}
	
	@ResponseBody
	@RequestMapping("/driver/{id}")
	@TokenRequired
	public ArrayList<Map<String, Object>> getDriverCashflowsbyId(
			@PathVariable(value = "id") Long id,
			@RequestParam(required = false) String dateStart,
			@RequestParam(required = false) String dateEnd, 
			@RequestParam(required = false) String partner, 
			@RequestParam(required = false) Long partnerId) throws ParseException{
		ArrayList<Cashflow> source=cashflowRepository.findBySourceAndSourceId("driver",id);
		ArrayList<Cashflow> destination=cashflowRepository.findByDestinationAndDestinationId("driver",id);
		source.addAll(destination);
		if(dateStart!=null&&dateEnd!=null) {
			SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
			Date start = inFormat.parse(dateStart);
			Date end = inFormat.parse(dateEnd);
			source=cashflowService.filterByDate(source, start, end);
		}
		if(partner!=null&&partnerId!=null) {
			if(partner=="restaurant") {
				source=cashflowService.filterByRestaurantId(source, partnerId);
			}
			if(partner=="customer") {
				source=cashflowService.filterByCustomerId(source, partnerId);
			}
			if(partner=="driver") {
				source=cashflowService.filterByDriverId(source, partnerId);
			}
		}
		return cashflowService.getCashflows(source);
	}
	
	@ResponseBody
	@RequestMapping("/{id}")
	@TokenRequired
	public Map<String,Object> getCashflowById(@PathVariable(value = "id") Long id){
		Optional<Cashflow> optionalCashflow = cashflowRepository.findById(id);
		return cashflowService.getCashflow(optionalCashflow.get());
	}
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@TokenRequired
	public Cashflow createCashflow(@RequestParam(value="id") Long id, @RequestParam(value="source") String source,
			@RequestParam(value="sourceId") Long source_id, @RequestParam(value="destination") String destination,
			@RequestParam(value="destinationId") Long destination_id, @RequestParam(value="jumlahUang") Long jumlah_uang){
		Cashflow cashflow=new Cashflow();
		cashflow.setId(id);
		cashflow.setSource(source);
		cashflow.setSourceId(source_id);
		cashflow.setDestination(destination);
		cashflow.setDestinationId(destination_id);
		cashflow.setJumlahUang(jumlah_uang);
		return cashflowRepository.save(cashflow);
	}
	
}
