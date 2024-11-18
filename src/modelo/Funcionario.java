package modelo;

public class Funcionario extends Usuario {

    // Construtor
    public Funcionario(String nome, String cpf) {
        super(nome, cpf);
    }

    // Sobrescrita do método exibirInfo
    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Tipo de usuário: Funcionário");
    }
}
