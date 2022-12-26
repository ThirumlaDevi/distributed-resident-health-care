package ucd.healthcare.yuepharmacy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import ucd.healthcare.yuepharmacy.model.YuePharmaMedicine;
import ucd.healthcare.yuepharmacy.repository.YuePharmacyRepository;

@Service
public class YuePharmacyService {
    private static final Logger logger = LoggerFactory.getLogger(YuePharmacyService.class);

    @Autowired
    private Environment env;

    @Autowired
    YuePharmacyRepository repository;

    /*
     * If existing medicine is added to the pharmacy, then update the new price and stock of the medicine.
     * The exisiting medicine is checked by name, if it is a new medicine then add it to the pharmacy.
     * return updated @YuePharmaMedicine.
     */
    public YuePharmaMedicine addMedicine(YuePharmaMedicine entity) {
        Optional<YuePharmaMedicine> medicine = repository.findByName(entity.getName());
        if (medicine.isPresent()) {
            YuePharmaMedicine newEntity = medicine.get();
            Integer oldQuantity = newEntity.getQuantity();
            newEntity.setQuantity(oldQuantity + entity.getQuantity());
            newEntity.setPrice(entity.getPrice());
            repository.save(newEntity);
            logger.info("Updated exisitng medicine stock");
            return newEntity;
        } else {
            repository.save(entity);
            logger.info("Added new medicine " + entity.getName());
            return entity;
        }
    }

    /*
     * Loop through medicine list and call addMedicine method which will add add stock properly if same medicine is added. 
     * The looping is not doing this parallely as parallelStream as adding to the database is not happening as expected when done parallely.
     */
    public List<YuePharmaMedicine> addMedicines(List<YuePharmaMedicine> medicines) {
        medicines.stream().forEach((medicine) -> {addMedicine(medicine);});
        return medicines;
    }

    /*
     * Get all medicines list from pharmacy.
     */
    public List<YuePharmaMedicine> getAllMedicines() {
        List<YuePharmaMedicine> medicines = repository.findAll();
        return medicines.size() > 0 ? medicines : new ArrayList<YuePharmaMedicine>();
    }

    public YuePharmaMedicine addOrder(YuePharmaMedicine entity) {
        return entity;
        // Optional<YuePharmaMedicine> medicine = repository.findByName(entity.getName());
        // if (medicine.isPresent()) {
        //     YuePharmaMedicine newEntity = medicine.get();
        //     newEntity.setName(entity.getName());
        //     newEntity.setPrice(entity.getPrice());
        //     repository.save(newEntity);
        //     return newEntity;
        // } else {
        //     repository.save(entity);
        //     return entity;
        // }
    }

    public List<YuePharmaMedicine> getOrderDetails() {
        List<YuePharmaMedicine> medicines = repository.findAll();
        return medicines.size() > 0 ? medicines : new ArrayList<YuePharmaMedicine>();
    }

    public List<YuePharmaMedicine> deleteOrder() {
        // delete order
        // add medicines to Pharma
        List<YuePharmaMedicine> medicines = repository.findAll();
        return medicines.size() > 0 ? medicines : new ArrayList<YuePharmaMedicine>();
    }

    // public List<Medicine> getMedicinesByNames(List<String> names) {
    //     List<Medicine> medicines = repository.findByNames(names);
    //     return medicines.size() > 0 ? medicines : new ArrayList<Medicine>();
    // }

    public YuePharmaMedicine getMedicinesByName(String name) {
        Optional<YuePharmaMedicine> medicine = repository.findByName(name);
        if (medicine.isPresent()) {
            return medicine.get();
        } else {
            return null;
        }
    }

    public void deleteMedicine(String name) {
        Optional<YuePharmaMedicine> opt = repository.findByName(name);
        if (opt.isPresent()) {
            repository.delete(opt.get());
        }
    }

    public void print() {
        System.out.println("I'm coming");
        System.out.println(env.getProperty("CONTAINER_NUMBER"));
    }
}
