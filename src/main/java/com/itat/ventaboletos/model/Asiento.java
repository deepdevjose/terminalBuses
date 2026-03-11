package com.itat.ventaboletos.model;

public class Asiento {
    private int numero;
    private boolean vendido;
    private String vendidoPorTerminal;  // identificador del terminal que compró, null si libre

    public Asiento(int numero) {
        this.numero = numero;
        this.vendido = false;
        this.vendidoPorTerminal = null;
    }

    // Getters y setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public String getVendidoPorTerminal() {
        return vendidoPorTerminal;
    }

    public void setVendidoPorTerminal(String vendidoPorTerminal) {
        this.vendidoPorTerminal = vendidoPorTerminal;
    }
}
