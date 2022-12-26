package ucd.healthcare.yanpharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucd.healthcare.yanpharmacy.model.YanPharmaMedicine;

@Repository
public interface YanPharmacyRepository extends JpaRepository<YanPharmaMedicine, Integer> {
    Optional<YanPharmaMedicine> findByName(String name);

    // @Query(value = "select * from medicine where `name` in (?1)", nativeQuery = true)
    // List<Medicine> findByNames(List<String> names);
}
