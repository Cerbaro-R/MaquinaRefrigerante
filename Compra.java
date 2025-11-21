public class Compra {

    private double credito;

    public Compra() {
        this.credito = 0; // usuário inicia com 10 créditos
    }

    public void inserirCredito(double valor) {
        if (valor == 0.10 || valor == 0.25 || valor == 0.50 || valor == 1.00) {
            credito += valor;
        } else {
            System.out.println("Moeda inválida.");
        }
    }

    public double getCredito() {
        return credito;
    }

    public void debitar(double valor) {
        credito -= valor;
    }

    public double calcularTroco() {
        return credito;
    }
}
