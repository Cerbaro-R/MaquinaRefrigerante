import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MaquinaRefrigerante maquina = new MaquinaRefrigerante();
        Compra compra = new Compra();

        int opcao = -1;

        do {
            try {
                System.out.println("\n=== MÁQUINA DE REFRIGERANTES ===");
                System.out.println("Crédito atual: R$ " + String.format("%.2f", compra.getCredito()));
                System.out.println("1 - Inserir crédito");
                System.out.println("2 - Escolher refrigerante");
                System.out.println("3 - Ver estoque");
                System.out.println("4 - Ver saldo");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");

                opcao = sc.nextInt();

            } catch (Exception e) {
                System.out.println("Entrada inválida. Digite apenas números.");
                sc.nextLine(); // limpa buffer
                continue;      // volta ao menu
            }

            switch (opcao) {

                
                //  INSERIR CRÉDITO
                
                case 1:
                    int opcInserir = -1;

                    do {
                        System.out.println("\n=== INSERIR CRÉDITO ===");
                        System.out.println("Crédito atual: R$ " + String.format("%.2f", compra.getCredito()));
                        System.out.println("Digite o valor da moeda (0,10 | 0,25 | 0,50 | 1,00)");
                        System.out.println("Ou digite 0 para voltar ao menu.");
                        System.out.print("Valor: ");

                        try {
                            String entrada = sc.next().replace(",", ".");
                            double valor = Double.parseDouble(entrada);

                            if (valor == 0) {
                                System.out.println("Retornando ao menu...");
                                break;
                            }

                            compra.inserirCredito(valor);

                        } catch (Exception e) {
                            System.out.println("Entrada inválida.");
                            sc.nextLine();
                        }

                        System.out.print("Deseja inserir mais crédito? (1 = sim / 0 = voltar): ");
                        try {
                            opcInserir = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Entrada inválida.");
                            sc.nextLine();
                            opcInserir = 0;
                        }

                    } while (opcInserir != 0);
                    break;

                
                // ESCOLHER REFRIGERANTE
                
                case 2:
                    System.out.println("\n=== Escolher refrigerante ===");
                    maquina.verEstoque();

                    int escolha = -1;

                    while (true) {
                        try {
                            System.out.print("Digite o número da opção desejada: ");
                            escolha = sc.nextInt();

                            if (escolha < 0 || escolha >= maquina.getQuantidadeTipos()) {
                                System.out.println("Opção inválida. Tente novamente.");
                            } else {
                                break;
                            }

                        } catch (Exception e) {
                            System.out.println("Entrada inválida. Digite apenas números.");
                            sc.nextLine(); // limpa buffer
                        }
                    }

                    maquina.realizarCompra(escolha, compra);
                    break;

                
                // VER ESTOQUE
                
                case 3:
                    System.out.println("\n=== ESTOQUE ===");
                    maquina.verEstoque();
                    break;

                
                // VER SALDO
                
                case 4:
                    System.out.println("\n=== SALDO ATUAL ===");
                    System.out.println("Saldo disponível: R$ " + String.format("%.2f", compra.getCredito()));
                    break;

                
                // SAIR
                
                case 0:
                    System.out.println("Encerrando...");
                    break;

                
                // OPÇÃO INVÁLIDA
                
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
            }

        } while (opcao != 0);

        sc.close();
    }
}
