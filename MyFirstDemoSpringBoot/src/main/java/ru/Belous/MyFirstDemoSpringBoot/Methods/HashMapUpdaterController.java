package ru.Belous.MyFirstDemoSpringBoot.Methods;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;

@RestController
public class HashMapUpdaterController {
    public static HashMap<Integer, String> hashMap = null;
    private static int counter = 0;

    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam("arg") String arg) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put(counter++, arg);
        return showHashMap();
    }

    @GetMapping("/show-map")
    public String showHashMap() {
        if (hashMap == null) {
            return "HashMap пуста";
        } else {
            return "элементы HashMap: " + hashMap.toString();
        }
    }
}
