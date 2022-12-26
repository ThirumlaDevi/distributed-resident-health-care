package ucd.healthcare.yuepharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucd.healthcare.yuepharmacy.model.YuePharmaMedicine;

@Repository
public interface YuePharmacyRepository extends JpaRepository<YuePharmaMedicine, Integer> {
    Optional<YuePharmaMedicine> findByName(String name);

    // @Query(value = "select * from medicine where `name` in (?1)", nativeQuery = true)
    // List<Medicine> findByNames(List<String> names);
}
