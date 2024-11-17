package servico;

import java.util.ArrayList;
import java.util.List;

import modelo.Quarto;

public class QuartoServico {
    private List<Quarto> quartos;

    public QuartoServico() {
        this.quartos = new ArrayList<>();
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public void adicionarQuarto(Quarto quarto) {
        quartos.add(quarto);
        System.out.println("Quarto adicionado: " + quarto.getNumero());
    }

    public void listarQuartos() {
        // Exibe informações dos quartos, incluindo se estão disponíveis ou não.
        for (Quarto quarto : quartos) {
            quarto.exibirInfo();
            System.out.println("Disponível: " + (quarto.isDisponivel() ? "Sim" : "Não"));
        }
    }

    public Quarto buscarQuarto(int numero) {
        // Retorna o quarto com o número especificado ou null se não encontrado.
        return quartos.stream()
                .filter(q -> q.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    public boolean isQuartoDisponivel(int numero) {
        // Verifica se o quarto existe e está disponível.
        Quarto quarto = buscarQuarto(numero);
        if (quarto != null) {
            return quarto.isDisponivel();
        }
        return false; // Se o quarto não existir, retorna false.
    }
}
