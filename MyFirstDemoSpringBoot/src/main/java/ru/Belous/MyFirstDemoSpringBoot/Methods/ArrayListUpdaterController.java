package ru.Belous.MyFirstDemoSpringBoot.Methods;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
public class ArrayListUpdaterController {
   public static ArrayList<String> arrayList = null;

    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam("arg") String arg) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(arg);
        return showArrayList();
    }

    @GetMapping("/show-array")
    public String showArrayList() {
        if (arrayList == null) {
            return "Список пуст";
        } else {
            return "Список: " + arrayList.toString();
        }
    }
}