# Sistema de Reservas em Java

Este projeto implementa um sistema de reservas de quartos para um hotel, permitindo que hóspedes se cadastrem, visualizem os quartos disponíveis e realizem reservas. Administradores podem gerenciar os quartos e controlar as reservas.

## 📌 Funcionalidades
- **Login como Administrador ou Hóspede**
- **Cadastro de Hóspedes**
- **Listagem de Quartos Disponíveis**
- **Realização de Reservas**
- **Adição de Novos Quartos (somente para administradores)**

## 🚀 Como Executar
1. Clone este repositório para sua máquina:
   ```sh
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   ```
2. Abra o projeto em sua IDE favorita (Eclipse, IntelliJ, VS Code, etc.).
3. Compile e execute a classe `Main.java`.

## 🛠 Tecnologias Utilizadas
- **Java**
- **Collections Framework (List, ArrayList)**
- **Scanner para entrada de dados**
- **Estruturas de controle de fluxo (switch-case, loops, if-else)**

## 📌 Estrutura do Código
- `Main.java`: Classe principal que gerencia a interação com o usuário.
- `Hospede.java`: Representa um hóspede do sistema.
- `Quarto.java`: Representa um quarto do hotel.
- `Reserva.java`: Representa uma reserva feita por um hóspede.
- `QuartoServico.java`: Gerencia os quartos disponíveis.
- `ReservaServico.java`: Gerencia o processo de reserva.
- `SistemaReserva.java`: Responsável por integrar os serviços e manter o funcionamento do sistema.

## 📢 Melhorias Futuras
- Implementação de persistência de dados (banco de dados ou arquivos).
- Adicionar interface gráfica (GUI) com JavaFX ou Swing.
- Melhor tratamento de erros e validação de entrada.

## 📄 Licença
Este projeto está sob a licença MIT. Sinta-se à vontade para utilizá-lo e melhorá-lo! 😊

