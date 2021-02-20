package pl.javastart.equipy.equip.asserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetsService {

    private AssetsRepository assetsRepository;
    private AssetsMapper assetsMapper;

    @Autowired
    public AssetsService(AssetsRepository assetsRepository, AssetsMapper assetsMapper) {
        this.assetsRepository = assetsRepository;
        this.assetsMapper = assetsMapper;
    }

    public AssetsService() {
    }

    public List<AssetsDto> findAll(){
        return assetsRepository.findAll().stream()
                .map(assetsMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AssetsDto> findByNameOrSerial(String text){
        return assetsRepository.findByNameOrSerialNumber(text.toLowerCase())
                .stream()
                .map(assetsMapper::toDto)
                .collect(Collectors.toList());
    }
}
