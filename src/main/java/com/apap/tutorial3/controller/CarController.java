package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;
import com.apap.tutorial3.service.CarInMemoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.CarModel;
import com.apap.tutorial3.service.CarService;

@Controller
public class CarController {
	@Autowired
	private CarService mobileService;
	@RequestMapping("/car/add")
	public String add(@RequestParam(value = "id", required = true) String id,
					@RequestParam(value = "brand", required = true) String brand,
					@RequestParam(value = "type", required = true) String type,
					@RequestParam(value = "price", required = true) Long price,
					@RequestParam(value = "amount", required = true) Integer amount) {
			CarModel car = new CarModel (id, brand, type, price, amount);
			mobileService.addChar(car);
			return "add";
	}
	
	
	@RequestMapping ("/car/viewall")
	public String viewall(Model model) {
		List<CarModel> archive = mobileService.getCarList();
		model.addAttribute("listCar", archive);
		return "viewall-car";
	}
	
	@RequestMapping(value = {"/car/view","car/view/{id}"})
	public String viewCar(@PathVariable Optional <String> id, Model model) {
		if(id.isPresent()) {
			CarModel archiveCar = mobileService.getCarDetail(id.get());
			if(archiveCar==null) {
				return "template";
			}
			else {
				model.addAttribute("car", archiveCar);
				return "view-car";
			}			
		}
		return "template";
	}
	
	@RequestMapping ("car/update/{id}/amount/{amount}")
	public String updateAmount(@PathVariable Optional <String> id, @PathVariable Optional <String> amount, Model model) {
		if(id.isPresent() && amount.isPresent()) {
			CarModel archiveCar = mobileService.getCarDetail(id.get());
			if(archiveCar==null) {
				return "template";
			}
			else {
				archiveCar.setAmount(Integer.parseInt(amount.get()));
				model.addAttribute("car", archiveCar);
				return "updated_data";
			}
		}
		return "template";
	}
	
	@RequestMapping ("car/delete/{id}")
	public String deleteCar(@PathVariable Optional <String> id, Model model) {
		if(id.isPresent()) {
			CarModel archiveCar = mobileService.getCarDetail(id.get());
			if(archiveCar==null) {
				return "template";
			}
			else {
				List<CarModel> List = mobileService.getCarList();
				List.remove(archiveCar);
				model.addAttribute("car", archiveCar);
				return "deleted";
			}
		}
		return "template";
		
	}
	
	
	
	
}
