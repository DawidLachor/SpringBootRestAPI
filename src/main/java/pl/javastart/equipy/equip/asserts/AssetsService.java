package pl.javastart.equipy.equip.asserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
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

    public AssetsDto findById(Long id){
        Optional<Assets> byId = assetsRepository.findById(id);
        if (byId.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono urządzenia o takim id");
        return assetsMapper.toDto(byId.get());
    }

    public AssetsDto save(AssetsDto assetsDto) {
        Assets assets = assetsMapper.toEntity(assetsDto);
        if (checkSerialNumberToSave(assets.getSerialNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Wyposażenie z takim numerem seryjnym już istnieje");
        }
        return saveAndReturn(assets);
    }

    public AssetsDto edit(AssetsDto assetsDto) {
        Assets assets = assetsMapper.toEntity(assetsDto);
        if (checkSerialNumberToEdit(assets)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Wyposażenie z takim numerem seryjnym już istnieje");
        }
        return saveAndReturn(assets);
    }



    private AssetsDto saveAndReturn(Assets assets){
        Assets save = assetsRepository.save(assets);
        return assetsMapper.toDto(save);
    }

    private boolean checkSerialNumberToSave(String serialNumber){
        Optional<Assets> bySerialNumber = assetsRepository.findBySerialNumberIgnoreCase(serialNumber);
        return bySerialNumber.isPresent();
    }

    private boolean checkSerialNumberToEdit(Assets assets){
        Optional<Assets> bySerialNumber = assetsRepository.findBySerialNumberIgnoreCase(assets.getSerialNumber());
        Optional<Assets> byId = assetsRepository.findById(assets.getId());
        if (bySerialNumber.isEmpty() || byId.isEmpty())
            return false;
        else {
            return !byId.get().getSerialNumber().equalsIgnoreCase(bySerialNumber.get().getSerialNumber());
        }
    }

    public List<AssetsAssignmentDto> findAssignments(Long assetId) {
       return assetsRepository.findById(assetId)
                .map(Assets::getAssignments)
                .orElseThrow(AssetsNotFoundException::new)
                .stream()
                .map(AssetsAssignmentMapping::toDto)
                .collect(Collectors.toList());
    }
}
