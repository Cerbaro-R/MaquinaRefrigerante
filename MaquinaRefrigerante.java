import java.util.Scanner;

public class MaquinaRefrigerante {

    private Refrigerante[] refrigerantes;
    private double saldoMaquina;

    public MaquinaRefrigerante() {

        refrigerantes = new Refrigerante[5];

        refrigerantes[0] = new Refrigerante("0 Coca-Cola", 4.00);
        refrigerantes[1] = new Refrigerante("1 Guaraná",   3.20);
        refrigerantes[2] = new Refrigerante("2 Fanta",     3.75);
        refrigerantes[3] = new Refrigerante("3 Sprite",    4.75);
        refrigerantes[4] = new Refrigerante("4 Pepsi",     3.50);

        saldoMaquina = 10.0;
    }

    public double getSaldoMaquina() {
        return saldoMaquina;
    }

    public void verEstoque() {
        System.out.println("\n=== ESTOQUE ===");
        for (Refrigerante r : refrigerantes) {
            System.out.printf("%s - R$ %.2f | Qtd: %d\n",
                r.getMarca(), r.getValor(), r.getQuantidade());
        }
    }

    public boolean refrigeranteDisponivel(int index) {
        return refrigerantes[index].getQuantidade() > 0;
    }

    public Refrigerante getRefrigerante(int index) {
        return refrigerantes[index];
    }

    public boolean realizarCompra(int index, Compra compra) {

        Refrigerante refri = refrigerantes[index];

        if (refri.getQuantidade() <= 0) {
            System.out.println("Refrigerante indisponível.");
            return false;
        }

        if (compra.getCredito() < refri.getValor()) {
            System.out.println("Crédito insuficiente.");
            return false;
        }

        // Efetua a compra
        refri.reduzirEstoque();
        saldoMaquina += refri.getValor();
        compra.debitar(refri.getValor());

        System.out.println("\nVocê comprou: " + refri.getMarca());

        // Se ainda tiver crédito, perguntar se quer continuar comprando
        if (compra.getCredito() > 0) {

            Scanner sc = new Scanner(System.in);

            System.out.println("\nCrédito restante: R$ " + String.format("%.2f", compra.getCredito()));
            System.out.print("Deseja continuar comprando? (1 = sim / 0 = não): ");

            int opcao = -1;

            try {
                opcao = sc.nextInt();
            } catch (Exception e) {
                opcao = 0; // Se digitou errado, devolve troco
            }

            // ENCERRAR COMPRA E DEVOLVER TROCO
            if (opcao == 0) {

                double troco = compra.calcularTroco();

                if (troco > 0) {
                    System.out.println("Devolvendo troco: R$ " + String.format("%.2f", troco));
                    compra.debitar(troco); // zera o crédito
                }

                System.out.println("Compra encerrada.");
            }
        }

        return true;
    }

    public int getQuantidadeTipos() {
        return refrigerantes.length;
    }
}
