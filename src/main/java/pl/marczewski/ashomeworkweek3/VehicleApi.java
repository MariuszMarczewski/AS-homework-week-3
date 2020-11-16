package pl.marczewski.ashomeworkweek3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
public class VehicleApi {

    private VehicleService vehicleService;

    @Autowired
    public VehicleApi(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicles() {
        return new ResponseEntity<>(vehicleService.getVehicleList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable long id) {
        Optional<Vehicle> first = vehicleService.getVehicleList().stream()
                .filter(vehicle -> vehicle.getId() == id).findFirst();
        if (first.isPresent()) {
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Vehicle>> getVehicleByColor(@PathVariable String color) {
        List<Vehicle> foundList = vehicleService.getVehicleList().stream()
                .filter(vehicle -> vehicle.getColor().equals(color)).collect(Collectors.toList());
        if (foundList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundList, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity addVehicle(@RequestBody Vehicle vehicle) {
        boolean add = vehicleService.getVehicleList().add(vehicle);
        if (add) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<Vehicle> modVehicle(@RequestBody Vehicle newVehicle) {
        Optional<Vehicle> first = vehicleService.getVehicleList().stream()
                .filter(vehicle -> vehicle.getId() == newVehicle.getId()).findFirst();
        if (first.isPresent()) {
            vehicleService.remove(first.get());
            vehicleService.add(newVehicle);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeVehicle(@PathVariable long id) {
        Optional<Vehicle> first = vehicleService.getVehicleList().stream()
                .filter(vehicle -> vehicle.getId() == id).findFirst();
        if (first.isPresent()) {
            vehicleService.remove(first.get());
            return new ResponseEntity(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/updatemark/{id}/{mark}")
    public ResponseEntity updateMark(@PathVariable long id, @PathVariable String mark){
        Optional<Vehicle> first = vehicleService.getVehicleList().stream()
                .filter(vehicle -> vehicle.getId() == id).findFirst();
        if (first.isPresent()) {
            first.get().setMark(mark);
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/updatemodel/{id}/{model}")
    public ResponseEntity updateModel(@PathVariable long id, @PathVariable String model){
        Optional<Vehicle> first = vehicleService.getVehicleList().stream()
                .filter(vehicle -> vehicle.getId() == id).findFirst();
        if (first.isPresent()) {
            first.get().setModel(model);
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/updatecolor/{id}/{color}")
    public ResponseEntity updateColor(@PathVariable long id, @PathVariable String color){
        Optional<Vehicle> first = vehicleService.getVehicleList().stream()
                .filter(vehicle -> vehicle.getId() == id).findFirst();
        if (first.isPresent()) {
            first.get().setColor(color);
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
