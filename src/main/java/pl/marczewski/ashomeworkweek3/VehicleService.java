package pl.marczewski.ashomeworkweek3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private List<Vehicle> vehicleList;

    @Autowired
    public VehicleService() {
        this.vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(1L, "Volvo", "S40", "silver"));
        vehicleList.add(new Vehicle(2L, "Fiat", "Bravo", "pink"));
        vehicleList.add(new Vehicle(3L, "Daewoo", "Tico", "pink"));
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public boolean remove(Vehicle vehicle){
        return vehicleList.remove(vehicle);
    }

    public boolean add(Vehicle vehicle){
        return vehicleList.add(vehicle);
    }
}
