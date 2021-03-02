package org.example.SpringDemo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Rap implements Music {
    private List<String> songs = List.of("Мой Калашников", "Морген", "Морген2");
    @Override
    public List<String> getName() {
        return songs;
    }

}
