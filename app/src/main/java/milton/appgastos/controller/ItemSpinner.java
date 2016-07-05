package milton.appgastos.controller;

/**
 * Created by Milton on 17/06/2016.
 */
public class ItemSpinner {

    private String nomeConta;
    private double valorDisponivel;

    public ItemSpinner() {
    }

    public ItemSpinner(String texto, double valorDisponivel) {
        this.nomeConta = texto;
        this.valorDisponivel = valorDisponivel;
    }

    public String getTexto() {
        return nomeConta;
    }

    public void setTexto(String texto) {
        this.nomeConta = texto;
    }

    public double getValorDisponivel() {
        return valorDisponivel;
    }

    public void setValorDisponivel(double valorDisponivel) {
        this.valorDisponivel = valorDisponivel;
    }
}
