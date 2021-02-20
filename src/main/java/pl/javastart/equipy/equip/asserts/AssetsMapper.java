package pl.javastart.equipy.equip.asserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.equip.category.Category;
import pl.javastart.equipy.equip.category.CategoryRepository;

import java.util.Optional;

@Service
public class AssetsMapper {
    private CategoryRepository categoryRepository;

    @Autowired
    public AssetsMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Assets toEntity(AssetsDto assetsDto){
        Assets assets = new Assets();
        assets.setDescription(assetsDto.getDescription());
        assets.setId(assetsDto.getId());
        assets.setName(assetsDto.getName());
        assets.setSerialNumber(assetsDto.getSerialNumber());

        Optional<Category> byName = categoryRepository.findByName(assetsDto.getCategory());
        assets.setCategory(byName.orElse(null));
        return assets;
    }

    public AssetsDto toDto(Assets assets){
        AssetsDto assetsDto = new AssetsDto();
        assetsDto.setDescription(assets.getDescription());
        assetsDto.setSerialNumber(assets.getSerialNumber());
        assetsDto.setId(assets.getId());
        assetsDto.setName(assets.getName());
        if (assets.getCategory() != null)
            assetsDto.setCategory(assets.getCategory().getName());
        return assetsDto;
    }
}
