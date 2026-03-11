package com.itat.ventaboletos.controller;



import com.itat.ventaboletos.service.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TerminalController {

    @Autowired
    private AsientoService asientoService;

    @GetMapping("/terminal/{id}")
    public String mostrarTerminal(@PathVariable("id") String id, Model model) {
        model.addAttribute("terminalId", id);
        model.addAttribute("asientos", asientoService.obtenerTodos());
        return "terminal";  // referencia a terminal.html
    }
}
