package ucd.healthcare.yuepharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ucd.healthcare.yuepharmacy.model.YuePharmaMedicine;
import ucd.healthcare.yuepharmacy.service.YuePharmacyService;

/*
 * Pharmacy which has medium availability of medicines + lower cost + late delivery service.
 *  - medicines delivered 1 day from the time of order.
 *  - 5% of bill on delivery charge.
 *  - medium stock on medicines.
 *  - add address and available stocks to the string comment in the response
 */

@RestController
@RequestMapping(value = "v1/pharmacy")
public class YuePharmacyController {
    @Autowired
    private YuePharmacyService service;

    // endpoint POST v1/pharmacy/medicines add list of medicines
    // @RequestMapping(method = RequestMethod.POST, value = "/m")
    // public ResponseEntity<YuePharmaMedicine> addMedicine(@RequestBody List<YuePharmaMedicine> medicines) {
    //     service.print();
    //     // return new ResponseEntity<>(service.addMedicine(obj), HttpStatus.CREATED);
    // }

    // endpoint POST v1/pharmacy/medicine
    @RequestMapping(method = RequestMethod.POST, path = "/medicine")
    public ResponseEntity<YuePharmaMedicine> addMedicine(@RequestBody YuePharmaMedicine obj) {
        service.print();
        return new ResponseEntity<>(service.addMedicine(obj), HttpStatus.CREATED);
    }

    // endpoint GET v1/pharmacy/medicines
    @RequestMapping(method = RequestMethod.GET, path = "/medicines")
    public ResponseEntity<List<YuePharmaMedicine>> getAllMedicines() {
        service.print();
        return new ResponseEntity<>(service.getAllMedicines(), HttpStatus.OK);
    }

    // POST order
    @RequestMapping(method = RequestMethod.POST, path = "/order")
    public ResponseEntity<List<YuePharmaMedicine>> order(@RequestBody List<YuePharmaMedicine> medicines) {
        service.print();
        // update table
        // store oder details of medicine objects in a blob column
        // send endpoint to get order information in the header, this information can be stored in a 
        return new ResponseEntity<>(service.getAllMedicines(), HttpStatus.CREATED);
    }

    // get order details
    @RequestMapping(method = RequestMethod.GET, path = "/order/{id}")
    public ResponseEntity<List<YuePharmaMedicine>> orderDetails(@PathVariable Long id) {
        service.print();
        // send endpoint to get order information in the header, this information can be stored in a 
        return new ResponseEntity<>(service.getAllMedicines(), HttpStatus.OK);
    }

    // cancel order
    @RequestMapping(method = RequestMethod.DELETE, path = "/order/{id}")
    public ResponseEntity cancelOrder(@PathVariable Long id) {
        service.print();
        // store oder details of medicine objects in a blob column
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // StockResponse - true
    // StockResponse(List<AvailableStock> medicines, true, long time_of_availability, float total_price, String comment)

    // @RequestMapping(method = RequestMethod.POST, value = "/m/names")
    // public ResponseEntity<List<Medicine>> getAllMedicinesByNames(@RequestBody List<String> names) {
    //     service.print();
    //     return new ResponseEntity<>(service.getMedicinesByNames(names), HttpStatus.OK);
    // }

    // @RequestMapping(method = RequestMethod.GET, value = "/m/name/{name}")
    // public ResponseEntity<YuePharmaMedicine> getAllMedicinesByName(@PathVariable("name") String name) {
    //     service.print();
    //     return new ResponseEntity<>(service.getMedicinesByName(name), HttpStatus.OK);
    // }

    // @RequestMapping(method = RequestMethod.GET, value = "/printsth")
    // public void printSomething() {
    //     service.print();
    // }
}
