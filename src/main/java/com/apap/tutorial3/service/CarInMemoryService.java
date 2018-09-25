package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import com.apap.tutorial3.model.CarModel;
import org.springframework.stereotype.Service;

/**
 * CarInMemoryService
 */
@Service
public class CarInMemoryService implements CarService{
	private List<CarModel> archiveCar;

	public CarInMemoryService() {
		archiveCar = new ArrayList<>();
	}

	@Override
	public void addChar(CarModel car) {
		archiveCar.add(car);
	}

	@Override
	public List<CarModel> getCarList() {
		return archiveCar;
	}

	@Override
	public CarModel getCarDetail(String id) {
		CarModel carObj = null;
		for(int i=0; i<archiveCar.size();i++) {
			if(archiveCar.get(i).getId().equals(id)) {
				carObj = archiveCar.get(i);
				return carObj;
			}
		}
		return carObj;
	}
	
}
