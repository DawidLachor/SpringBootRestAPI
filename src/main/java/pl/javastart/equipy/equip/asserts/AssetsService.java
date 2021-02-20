package pl.javastart.equipy.equip.asserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
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

    public AssetsDto save(AssetsDto assetsDto) {
        Assets assets = assetsMapper.toEntity(assetsDto);
        if (checkSerialNumber(assets.getSerialNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Wyposażenie z takim numerem seryjnym już istnieje");
        }
        Assets save = assetsRepository.save(assets);
        return assetsMapper.toDto(save);
    }

    private boolean checkSerialNumber(String serialNumber){
        Optional<Assets> bySerialNumber = assetsRepository.findBySerialNumber(serialNumber);
        return bySerialNumber.isPresent();
    }
}
