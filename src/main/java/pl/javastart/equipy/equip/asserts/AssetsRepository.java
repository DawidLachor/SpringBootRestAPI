package pl.javastart.equipy.equip.asserts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.javastart.equipy.equip.asserts.Assets;

import java.util.List;
import java.util.Optional;

public interface AssetsRepository extends JpaRepository<Assets, Long> {
    @Query("select a from Assets a where lower(a.name) like %?1% or lower(a.serialNumber) like %?1%")
    List<Assets> findByNameOrSerialNumber(String text);
    Optional<Assets> findBySerialNumberIgnoreCase(String serialNumber);
}
