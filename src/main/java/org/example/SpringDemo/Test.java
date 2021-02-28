package org.example.SpringDemo;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        Computer player = context.getBean("computer", Computer.class);
        player.play();
        context.close();
    }
}
