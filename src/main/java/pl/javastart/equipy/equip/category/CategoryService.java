package pl.javastart.equipy.equip.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<String> findAll(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .map(CatagoryDto::toString)
                .collect(Collectors.toList());
    }
}
