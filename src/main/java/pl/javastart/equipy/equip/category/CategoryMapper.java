package pl.javastart.equipy.equip.category;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryMapper {

    private CategoryRepository categoryRepository;

    public CategoryMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category toEntity(CatagoryDto catagoryDto){
        Optional<Category> byName = categoryRepository.findByName(catagoryDto.getName());
        return byName.orElse(null);
    }

    public CatagoryDto toDto(Category category){
        CatagoryDto catagoryDto = new CatagoryDto();
        catagoryDto.setName(category.getName());
        return catagoryDto;
    }
}
