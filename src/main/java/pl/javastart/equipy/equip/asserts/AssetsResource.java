package pl.javastart.equipy.equip.asserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
