package com.itat.ventaboletos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VentaBoletosApplication {

    public static void main(String[] args) {
        SpringApplication.run(VentaBoletosApplication.class, args);
        System.out.println("\n==============================================");
        System.out.println("Aplicación iniciada correctamente");
        System.out.println("Accede a: http://localhost:8080/terminal/Terminal-1");
        System.out.println("Otros terminales: /terminal/Terminal-2, /terminal/Terminal-3");
        System.out.println("==============================================\n");
    }
}
