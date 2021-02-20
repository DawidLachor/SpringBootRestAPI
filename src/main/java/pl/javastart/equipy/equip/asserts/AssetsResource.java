package pl.javastart.equipy.equip.asserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetsResource {

    private AssetsService assetsService;

    @Autowired
    public AssetsResource(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    public AssetsResource() {
    }

    @GetMapping
    public List<AssetsDto> findAll(@RequestParam(required = false) String text){
        if (text!=null){
            return assetsService.findByNameOrSerial(text);
        }
        return assetsService.findAll();
    }

    @GetMapping("/{id}")
    public AssetsDto findById(@PathVariable Long id){
        return assetsService.findById(id);
    }

    @PutMapping("/{id}")
    public AssetsDto edit(@RequestBody AssetsDto assetsDto, @PathVariable Long id){
        if (!assetsDto.getId().equals(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu");
        return assetsService.edit(assetsDto);
    }

    @PostMapping
    public AssetsDto save(@RequestBody AssetsDto assetsDto){
        if (assetsDto.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wyposażenie z takim id już istnieje");
        return assetsService.save(assetsDto);
    }
}
