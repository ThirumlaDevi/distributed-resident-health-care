package ucd.healthcare.yanpharmacy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import ucd.healthcare.yanpharmacy.model.YanPharmaMedicine;
import ucd.healthcare.yanpharmacy.repository.YanPharmacyRepository;

@Service
public class YanPharmacyService {
    private static final Logger logger = LoggerFactory.getLogger(YanPharmacyService.class);

    @Autowired
    private Environment env;

    @Autowired
    YanPharmacyRepository repository;

    /*
     * If existing medicine is added to the pharmacy, then update the new price and stock of the medicine.
     * The exisiting medicine is checked by name, if it is a new medicine then add it to the pharmacy.
     * return updated @YanPharmaMedicine.
     */
    public YanPharmaMedicine addMedicine(YanPharmaMedicine entity) {
        Optional<YanPharmaMedicine> medicine = repository.findByName(entity.getName());
        if (medicine.isPresent()) {
            YanPharmaMedicine newEntity = medicine.get();
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
    public List<YanPharmaMedicine> addMedicines(List<YanPharmaMedicine> medicines) {
        medicines.stream().forEach((medicine) -> {addMedicine(medicine);});
        return medicines;
    }

    /*
     * Get all medicines list from pharmacy.
     */
    public List<YanPharmaMedicine> getAllMedicines() {
        List<YanPharmaMedicine> medicines = repository.findAll();
        return medicines.size() > 0 ? medicines : new ArrayList<YanPharmaMedicine>();
    }

    public YanPharmaMedicine addOrder(YanPharmaMedicine entity) {
        return entity;
        // Optional<YanPharmaMedicine> medicine = repository.findByName(entity.getName());
        // if (medicine.isPresent()) {
        //     YanPharmaMedicine newEntity = medicine.get();
        //     newEntity.setName(entity.getName());
        //     newEntity.setPrice(entity.getPrice());
        //     repository.save(newEntity);
        //     return newEntity;
        // } else {
        //     repository.save(entity);
        //     return entity;
        // }
    }

    public List<YanPharmaMedicine> getOrderDetails() {
        List<YanPharmaMedicine> medicines = repository.findAll();
        return medicines.size() > 0 ? medicines : new ArrayList<YanPharmaMedicine>();
    }

    public List<YanPharmaMedicine> deleteOrder() {
        // delete order
        // add medicines to Pharma
        List<YanPharmaMedicine> medicines = repository.findAll();
        return medicines.size() > 0 ? medicines : new ArrayList<YanPharmaMedicine>();
    }

    // public List<Medicine> getMedicinesByNames(List<String> names) {
    //     List<Medicine> medicines = repository.findByNames(names);
    //     return medicines.size() > 0 ? medicines : new ArrayList<Medicine>();
    // }

    public YanPharmaMedicine getMedicinesByName(String name) {
        Optional<YanPharmaMedicine> medicine = repository.findByName(name);
        if (medicine.isPresent()) {
            return medicine.get();
        } else {
            return null;
        }
    }

    public void deleteMedicine(String name) {
        Optional<YanPharmaMedicine> opt = repository.findByName(name);
        if (opt.isPresent()) {
            repository.delete(opt.get());
        }
    }

    public void print() {
        System.out.println("You are in TD pharmacy container now");
        System.out.println(env.getProperty("CONTAINER_NUMBER"));
    }
}
