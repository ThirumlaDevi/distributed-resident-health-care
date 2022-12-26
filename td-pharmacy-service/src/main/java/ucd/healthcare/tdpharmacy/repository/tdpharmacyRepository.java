package ucd.healthcare.tdpharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucd.healthcare.tdpharmacy.model.TDPharmaMedicine;

@Repository
public interface tdpharmacyRepository extends JpaRepository<TDPharmaMedicine, Integer> {
    Optional<TDPharmaMedicine> findByName(String name);

    // @Query(value = "select * from medicine where `name` in (?1)", nativeQuery = true)
    // List<Medicine> findByNames(List<String> names);
}
