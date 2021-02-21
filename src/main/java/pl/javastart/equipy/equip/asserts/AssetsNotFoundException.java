package pl.javastart.equipy.equip.asserts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Nie znaleziono urządzenia o tym id")
public class AssetsNotFoundException extends RuntimeException{
}
