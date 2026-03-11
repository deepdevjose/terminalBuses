package com.itat.ventaboletos.service;



import com.itat.ventaboletos.model.Asiento;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class AsientoService {
    private final Asiento[] asientos;
    private final int totalAsientos = 40;
    private int vendidos = 0;

    public AsientoService() {
        asientos = new Asiento[totalAsientos];
        for (int i = 0; i < totalAsientos; i++) {
            asientos[i] = new Asiento(i + 1);
        }
    }

    // Obtener copia de la lista de asientos
    public synchronized List<Asiento> obtenerTodos() {
        List<Asiento> copia = new ArrayList<>();
        for (Asiento a : asientos) {
            // Crear una copia simple para no exponer los originales
            Asiento copiaAsiento = new Asiento(a.getNumero());
            copiaAsiento.setVendido(a.isVendido());
            copiaAsiento.setVendidoPorTerminal(a.getVendidoPorTerminal());
            copia.add(copiaAsiento);
        }
        return copia;
    }

    // Vender asiento específico
    public synchronized ResultadoCompra comprarEspecifico(int numero, String terminalId) {
        if (numero < 1 || numero > totalAsientos) {
            return new ResultadoCompra(false, "INVALIDO", null);
        }
        int idx = numero - 1;
        Asiento asiento = asientos[idx];
        if (asiento.isVendido()) {
            return new ResultadoCompra(false, "OCUPADO", null);
        }
        asiento.setVendido(true);
        asiento.setVendidoPorTerminal(terminalId);
        vendidos++;
        return new ResultadoCompra(true, "OK", numero);
    }

    // Vender siguiente asiento disponible
    public synchronized ResultadoCompra comprarAleatorio(String terminalId) {
        if (vendidos >= totalAsientos) {
            return new ResultadoCompra(false, "NO_QUEDAN", null);
        }
        for (int i = 0; i < totalAsientos; i++) {
            if (!asientos[i].isVendido()) {
                asientos[i].setVendido(true);
                asientos[i].setVendidoPorTerminal(terminalId);
                vendidos++;
                return new ResultadoCompra(true, "OK", i + 1);
            }
        }
        return new ResultadoCompra(false, "NO_QUEDAN", null);
    }

    // Clase interna para encapsular resultado
    public static class ResultadoCompra {
        private final boolean exitoso;
        private final String mensaje;
        private final Integer asiento;

        public ResultadoCompra(boolean exitoso, String mensaje, Integer asiento) {
            this.exitoso = exitoso;
            this.mensaje = mensaje;
            this.asiento = asiento;
        }

        public boolean isExitoso() { return exitoso; }
        public String getMensaje() { return mensaje; }
        public Integer getAsiento() { return asiento; }
    }
}
