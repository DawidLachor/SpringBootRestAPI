package pl.javastart.equipy.equip.asserts;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.equipy.equip.asserts.Assets;

public interface AssetsRepository extends JpaRepository<Assets, Long> {
}
