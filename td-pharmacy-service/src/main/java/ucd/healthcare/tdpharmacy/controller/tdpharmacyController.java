package ucd.healthcare.tdpharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.core.StockResponse;
import ucd.healthcare.tdpharmacy.model.TDPharmaMedicine;
import ucd.healthcare.tdpharmacy.service.tdpharmacyService;

/*
 * Pharmacy which has high medicine availability + no home delivery service.
 *  - medicines available 10mins from the time of order.
 *  - high stock medicine availability.
 *  - add address and available stocks to the string comment in the response
 */

@RestController
@RequestMapping(value = "v1/pharmacy")
public class tdpharmacyController {
    @Autowired
    private tdpharmacyService service;

    // endpoint POST v1/pharmacy/medicines add list of medicines
    @RequestMapping(method = RequestMethod.POST, value = "/medicines")
    public ResponseEntity<List<TDPharmaMedicine>> addMedicines(@RequestBody List<TDPharmaMedicine> medicines) {
        service.print();
        return new ResponseEntity<>(service.addMedicines(medicines), HttpStatus.CREATED);
    }

    // endpoint POST v1/pharmacy/medicine
    @RequestMapping(method = RequestMethod.POST, path = "/medicine")
    public ResponseEntity<TDPharmaMedicine> addMedicine(@RequestBody TDPharmaMedicine medicine) {
        service.print();
        return new ResponseEntity<>(service.addMedicine(medicine), HttpStatus.CREATED);
    }

    // endpoint GET v1/pharmacy/medicines
    @RequestMapping(method = RequestMethod.GET, path = "/medicines")
    public ResponseEntity<List<TDPharmaMedicine>> getAllMedicines() {
        service.print();
        return new ResponseEntity<>(service.getAllMedicines(), HttpStatus.OK);
    }

    // POST order
    @RequestMapping(method = RequestMethod.POST, path = "/order")
    public ResponseEntity<StockResponse> order(@RequestBody List<TDPharmaMedicine> medicines) {
        service.print();
        // send endpoint to get order information in the header, this information can be stored in a 
        // TD_SERVICE_ORDER_ENDPOINT + tdPharmaOrder.getId()
        return new ResponseEntity<>(service.addOrder(medicines), HttpStatus.CREATED);
    }

    // get order details
    // @RequestMapping(method = RequestMethod.GET, path = "/order/{id}")
    // public ResponseEntity<List<TDPharmaMedicine>> orderDetails(@PathVariable Long id) {
    //     service.print();
    //     // send endpoint to get order information in the header, this information can be stored in a 
    //     return new ResponseEntity<>(service.getAllMedicines(), HttpStatus.OK);
    // }

    // cancel/delete order
    @RequestMapping(method = RequestMethod.DELETE, path = "/order")
    public ResponseEntity cancelOrder(@RequestBody StockResponse order) {
        service.print();
        service.deleteOrder(order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // @RequestMapping(method = RequestMethod.POST, value = "/m/names")
    // public ResponseEntity<List<Medicine>> getAllMedicinesByNames(@RequestBody List<String> names) {
    //     service.print();
    //     return new ResponseEntity<>(service.getMedicinesByNames(names), HttpStatus.OK);
    // }

    @RequestMapping(method = RequestMethod.GET, value = "/m/name/{name}")
    public ResponseEntity<TDPharmaMedicine> getAllMedicinesByName(@PathVariable("name") String name) {
        service.print();
        return new ResponseEntity<>(service.getMedicinesByName(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/printsth")
    public void printSomething() {
        service.print();
    }
}
