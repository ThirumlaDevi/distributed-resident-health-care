package ucd.healthcare.tdpharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ucd.healthcare.tdpharmacy.model.TDPharmaOrder;

@Repository
public interface tdpharmacyOrderRepository extends JpaRepository<TDPharmaOrder, Long> {
    Optional<TDPharmaOrder> findById(long id);

    // save()

    // @Modifying
    // @Transactional
    // @Query(value = "insert into pharma1order VALUES (?, ?)", nativeQuery = true)
    // TDPharmaOrder save(int id, String order);
    
    // default TDPharmaOrder save(TDPharmaOrder order) {
    //     return save(0, order.getOrder());
    // }

    // @Transactional
    // @Query(value = "insert into pharma1order VALUES (0, ?))", nativeQuery = true)
    // TDPharmaOrder saveOrder(String order);
}
