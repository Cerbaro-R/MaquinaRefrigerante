public class Refrigerante {

    private String marca;
    private double valor;
    private int quantidade;

    public Refrigerante(String marca, double valor) {
        this.marca = marca;
        this.valor = valor;
        this.quantidade = 20; // capacidade inicial
    }

    public String getMarca() {
        return marca;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void reduzirEstoque() {
        if (quantidade > 0) {
            quantidade--;
        }
    }
}
