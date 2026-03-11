package com.itat.ventaboletos.controller;

import com.itat.ventaboletos.model.Asiento;
import com.itat.ventaboletos.service.AsientoService;
import com.itat.ventaboletos.service.AsientoService.ResultadoCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AsientoApiController {

    @Autowired
    private AsientoService asientoService;

    @GetMapping("/asientos")
    public List<Asiento> obtenerAsientos() {
        return asientoService.obtenerTodos();
    }

    @PostMapping("/comprar-aleatorio")
    public ResultadoCompra comprarAleatorio(@RequestParam String terminalId) {
        return asientoService.comprarAleatorio(terminalId);
    }

    @PostMapping("/comprar-especifico")
    public ResultadoCompra comprarEspecifico(@RequestParam String terminalId,
                                             @RequestParam int numeroAsiento) {
        return asientoService.comprarEspecifico(numeroAsiento, terminalId);
    }
}
