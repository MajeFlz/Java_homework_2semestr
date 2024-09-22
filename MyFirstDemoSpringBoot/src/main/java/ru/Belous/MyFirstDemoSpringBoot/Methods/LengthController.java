package ru.Belous.MyFirstDemoSpringBoot.Methods;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LengthController {
    @GetMapping("/show-all-length")
    public String showAllLength() {
        int arrayListLength = ArrayListUpdaterController.arrayList == null ? 0 : ArrayListUpdaterController.arrayList.size();
        int hashMapLength = HashMapUpdaterController.hashMap == null ? 0 : HashMapUpdaterController.hashMap.size();
        int allLength = arrayListLength + hashMapLength;
        return "Элементов в ArrayList: " + arrayListLength + "<br>Элементов в HashMap: " + hashMapLength + "<br>Всего элементов: " + allLength;
    }
}
