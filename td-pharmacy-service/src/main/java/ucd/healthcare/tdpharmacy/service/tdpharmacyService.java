package ucd.healthcare.tdpharmacy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import service.core.AvailableStock;
import service.core.StockResponse;
import ucd.healthcare.tdpharmacy.model.TDPharmaMedicine;
import ucd.healthcare.tdpharmacy.model.TDPharmaOrder;
import ucd.healthcare.tdpharmacy.repository.tdpharmacyOrderRepository;
import ucd.healthcare.tdpharmacy.repository.tdpharmacyRepository;

@Service
public class tdpharmacyService {
    private static final Logger logger = LoggerFactory.getLogger(tdpharmacyService.class);

    @Autowired
    private Environment env;

    @Autowired
    tdpharmacyRepository repository;

    @Autowired
    tdpharmacyOrderRepository orderRepository;

    /*
     * If existing medicine is added to the pharmacy, then update the new price and stock of the medicine.
     * The exisiting medicine is checked by name, if it is a new medicine then add it to the pharmacy.
     * return updated @TDPharmaMedicine.
     */
    public TDPharmaMedicine addMedicine(TDPharmaMedicine entity) {
        Optional<TDPharmaMedicine> medicine = repository.findByName(entity.getName());
        if (medicine.isPresent()) {
            TDPharmaMedicine newEntity = medicine.get();
            Integer oldQuantity = newEntity.getQuantity();
            newEntity.setQuantity(oldQuantity + entity.getQuantity());
            newEntity.setPrice(entity.getPrice());
            repository.save(newEntity);
            logger.info("Updated exisitng medicine stock");
            return newEntity;
        } else {
            // one improvement to do here is lowercase all medicine names and then save medicine
            repository.save(entity);
            logger.info("Added new medicine " + entity.getName());
            return entity;
        }
    }

    /*
     * Loop through medicine list and call addMedicine method which will add add stock properly if same medicine is added. 
     * The looping is not doing this parallely as parallelStream as adding to the database is not happening as expected when done parallely.
     */
    public List<TDPharmaMedicine> addMedicines(List<TDPharmaMedicine> medicines) {
        medicines.stream().forEach((medicine) -> {addMedicine(medicine);});
        return medicines;
    }

    /*
     * Get all medicines list from pharmacy.
     */
    public List<TDPharmaMedicine> getAllMedicines() {
        List<TDPharmaMedicine> medicines = repository.findAll();
        return medicines.size() > 0 ? medicines : new ArrayList<TDPharmaMedicine>();
    }

    /*
     * When order is done reduce the stock to maintain consistency and real stocl information
     * If the user doesn't want to accept this order, then read the stock
     */
    public StockResponse addOrder(List<TDPharmaMedicine> medicines) {
        TDPharmaOrder orderEntity = new TDPharmaOrder();
        StockResponse stockResponse = updateMedicineAvailability(medicines);
        // try{
            // String healthWorkerJSON = objectMapper.writeValueAsString(stockResponse);
            logger.info(stockResponse.toString());
            // System.out.println(healthWorkerJSON);
            // update table
            // store order details of medicine objects in a blob column
            
            // orderEntity.setOrder(stockResponse.toString());
            // orderEntity.setId(0L);
            orderEntity.setOrder("Arun Karthick");
            orderEntity = orderRepository.save(orderEntity);
            // logger.info("Added new order " + orderEntity.toString());
            // orderEntity = orderRepository.saveOrder(orderEntity.getOrder());
            // orderRepository.saveOrder("Devi");
            logger.info("order id : " + orderEntity.getId());
        // } catch(JsonProcessingException jsonProcessingException) {
        //     logger.info("Exception occured");
        // }


        // TDStockResponse stockResponse = new TDStockResponse();
        // stockResponse.setMedicines(medicines);
        // try {
        //     FileOutputStream file = new FileOutputStream("newfile.txt");
        //     ObjectOutputStream oos = new ObjectOutputStream(file);
        //     logger.info("object written in " + stockResponse);
        //     oos.writeObject(stockResponse);
        //     oos.close();
        //     file.close();
        //     logger.info("coming in for new order" + oos.toString());
        //     // orderEntity.setOrder(oos.toString());
        // } catch (SecurityException exception) {
        //     logger.error("error while adding object information to order entity" + exception.getMessage());
        // } catch(IOException ioException) {
        //     logger.error("error while adding object information to order entity" + ioException.getMessage());
        // }
        
        return stockResponse;
    }

    private StockResponse updateMedicineAvailability(List<TDPharmaMedicine> medicines) {
        StockResponse stockResponse = new StockResponse();
        stockResponse.setCan_home_deliver(false);
        String comment="";
        for(TDPharmaMedicine tdPharmaMedicine : medicines) {
            // boolean canProvideMedicine = true;
            // one improvement to do here is lowercase all medicine names and then check if it's lowercased and saved
            Optional<TDPharmaMedicine> medicine = repository.findByName(tdPharmaMedicine.getName());
            if (medicine.isPresent()) {
                TDPharmaMedicine newEntity = medicine.get();
                Integer oldQuantity = newEntity.getQuantity();
                logger.info("if loop");
                if(oldQuantity - tdPharmaMedicine.getQuantity() >= 0){
                    stockResponse.addMedicines(new AvailableStock(tdPharmaMedicine.getName(), 
                                                tdPharmaMedicine.getQuantity(), true,
                                                newEntity.getPrice() ));
                    
                    stockResponse.setTotal_price(stockResponse.getTotal_price()+newEntity.getPrice());
                    newEntity.setQuantity(oldQuantity - tdPharmaMedicine.getQuantity());
                    repository.save(newEntity);
                } else {
                    logger.info("else loop");
                    stockResponse.addMedicines(new AvailableStock(tdPharmaMedicine.getName(), 0 , 
                        false, newEntity.getPrice() ));
                    comment += (" " + tdPharmaMedicine.getName() + ",");
                }
                logger.info("Updated exisitng medicine stock");
            } else {
                logger.info("else else loop");
                stockResponse.addMedicines(new AvailableStock(tdPharmaMedicine.getName(), 0 , 
                        false, null));
            }
        }
        if(comment !="") {
            comment = "Full stock not available for medicines " + comment;
        }
        comment += "\n Pharmacy Address : TDPharma, Ballay, Dublin 18, D18XT96";
        stockResponse.setComment(comment);
        Date curdate = new Date();
        stockResponse.setTime_of_availability((new Date(curdate.getTime() + 10 * 60000)).toString());
        return stockResponse;
    }

    public List<TDPharmaMedicine> getOrderDetails() {
        List<TDPharmaMedicine> medicines = repository.findAll();
        return medicines.size() > 0 ? medicines : new ArrayList<TDPharmaMedicine>();
    }

    /*
     * When order is deleted, add medicines back to pharma.
     */
    public Boolean deleteOrder(StockResponse order) {
        // delete order
        // add medicines back to pharma
        // List<TDPharmaMedicine> medicines = repository.findAll();
        for(AvailableStock tdPharmaMedicine : order.getMedicines()) {
            Optional<TDPharmaMedicine> medicine = repository.findByName(tdPharmaMedicine.getMedicineName());
            if (medicine.isPresent()) {
                TDPharmaMedicine newEntity = medicine.get();
                Integer oldQuantity = newEntity.getQuantity();
                logger.info("delete order if loop");
                newEntity.setQuantity(oldQuantity + tdPharmaMedicine.getStockAvailable());
                repository.save(newEntity);
            }
        }
        return true;
    }

    // public List<Medicine> getMedicinesByNames(List<String> names) {
    //     List<Medicine> medicines = repository.findByNames(names);
    //     return medicines.size() > 0 ? medicines : new ArrayList<Medicine>();
    // }

    public TDPharmaMedicine getMedicinesByName(String name) {
        Optional<TDPharmaMedicine> medicine = repository.findByName(name);
        if (medicine.isPresent()) {
            return medicine.get();
        } else {
            return null;
        }
    }

    public void deleteMedicine(String name) {
        Optional<TDPharmaMedicine> opt = repository.findByName(name);
        if (opt.isPresent()) {
            repository.delete(opt.get());
        }
    }

    public void print() {
        System.out.println("You are in TD pharmacy container now");
        System.out.println(env.getProperty("CONTAINER_NUMBER"));
    }
}
