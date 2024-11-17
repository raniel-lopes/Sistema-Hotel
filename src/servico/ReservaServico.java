package servico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import modelo.Hospede;
import modelo.Quarto;
import modelo.Reserva;

public class ReservaServico {
    public void fazerReserva(int numeroQuarto, String nomeHospede, String dataInicioStr, String dataFimStr, 
                             SistemaReserva sistemaReserva) {

        // Busca o quarto pelo número
        Quarto quartoEscolhido = sistemaReserva.getQuartos().stream()
                                                .filter(q -> q.getNumero() == numeroQuarto)
                                                .findFirst()
                                                .orElse(null);

        if (quartoEscolhido != null) {
            // Verifica se o quarto está disponível
            if (!quartoEscolhido.isDisponivel()) {
                System.out.println("Quarto já está reservado.");
                return; // Não prossegue se o quarto não estiver disponível
            }

            // Busca o hóspede pelo nome
            Hospede hospede = sistemaReserva.getHospedes().stream()
                                            .filter(h -> h.getNome().equals(nomeHospede))
                                            .findFirst()
                                            .orElse(null);

            if (hospede != null) {
                try {
                    // Converte as datas
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataInicio = sdf.parse(dataInicioStr);
                    Date dataFim = sdf.parse(dataFimStr);

                    // Verifica se a data de saída é posterior à data de entrada
                    if (dataFim.before(dataInicio)) {
                        System.out.println("Erro: A data de saída não pode ser anterior à data de entrada.");
                        return; // Não prossegue se a data de saída for inválida
                    }

                    // Cria uma nova reserva
                    Reserva reserva = new Reserva(hospede, quartoEscolhido, dataInicio, dataFim);
                    sistemaReserva.getReservas().add(reserva);
                    quartoEscolhido.setDisponivel(false); // Define o quarto como indisponível
                    System.out.println("Reserva realizada com sucesso!");

                } catch (ParseException e) {
                    System.out.println("Erro ao converter as datas. Use o formato dd/MM/yyyy.");
                }
            } else {
                System.out.println("Hóspede não encontrado!");
            }
        } else {
            System.out.println("Quarto inexistente!");
        }
    }
}
