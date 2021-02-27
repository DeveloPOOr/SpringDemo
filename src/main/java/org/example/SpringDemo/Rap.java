package org.example.SpringDemo;

import org.springframework.stereotype.Component;

@Component
public class Rap implements Music {
    @Override
    public String getName() {
        return "Candy Shop";
    }

}
