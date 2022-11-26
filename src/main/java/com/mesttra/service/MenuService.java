package com.mesttra.service;


import com.mesttra.model.Cliente;
import com.mesttra.model.ClientePf;
import com.mesttra.model.ClientePj;
import com.mesttra.model.TipoConta;
import com.mesttra.repository.ClienteRepository;
import com.mesttra.repository.Conexao;

import java.util.Random;
import java.util.Scanner;

public class MenuService {

    private static Scanner entrada = new Scanner(System.in);

    private static ClienteRepository clienteRepository = new ClienteRepository();

    public static void menuGerente() {
        System.out.println("====================================================");
        System.out.println("=============== BANCO 1000Devs =====================");
        System.out.println("====================================================");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Excluir Cliente");
        System.out.println("3 - Buscar Cliente");
        System.out.println("4 - Sair");
    }

    public static void menuPrincipal() {
        boolean repetir = true;
        while (repetir) {
            System.out.println("====================================================");
            System.out.println("=============== BANCO 1000Devs =====================");
            System.out.println("====================================================");
            System.out.println("1 - Funcionario");
            System.out.println("2 - Cliente");
            System.out.println("3 - Sair");
            int opcao = Integer.parseInt(entrada.nextLine());
            switch (opcao) {
                case 1:
                    painelFuncionario();
                    break;
                case 2:
                    menuCliente();
                    break;
                case 3:
                    repetir = false;
                    break;
            }
        }
    }

    public static void menuCliente() {

        System.out.println("====================================================");
        System.out.println("=============== BANCO 1000Devs =====================");
        System.out.println("====================================================");
        System.out.println("1 - Primerio acesso");
        System.out.println("2 - Fazer login");
        System.out.println("3 - Sair");
        int opcao = Integer.parseInt(entrada.nextLine());
        if (opcao == 1) {
            primeiroAcesso();
            painelCliente(login());
        } else if (opcao == 2) {
            Cliente cliente = login();
            if (cliente != null) {
                painelCliente(cliente);
            } else {
                System.out.println("Conta Bloqueada");
            }
        } else {
            menuPrincipal();
        }
    }

    public static void painelFuncionario() {
        boolean repetir = true;
        while (repetir) {
            System.out.println("====================================================");
            System.out.println("=============== BANCO 1000Devs =====================");
            System.out.println("====================================================");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Excluir Cliente");
            System.out.println("3 - Buscar Cliente");
            System.out.println("4 - Sair");
            switch (Integer.parseInt(entrada.nextLine())) {
                case 1:
                    System.out.println("Qual tipo de cliente deseja Cadastrar ? " +
                            "\n1 - PJ" +
                            "\n2 - PF");
                    int tipoCliente = Integer.parseInt(entrada.nextLine());
                    if (tipoCliente == 2) {
                        ClientePf pf = (ClientePf) MenuService.dadosCliente(tipoCliente);
                        clienteRepository.save(pf);
                        MenuService.dadosCliente(pf);
                    } else {
                        ClientePj pj = (ClientePj) MenuService.dadosCliente(tipoCliente);
                        MenuService.dadosCliente(pj);
                        clienteRepository.save(pj);

                    }
                    break;
                case 2:
                    MenuService.deletarCliente();
                    break;
                case 3:
                    GerenteService.buscarCliente();
                    break;
                case 4:
                    repetir = false;
                    break;

            }
        }
    }

    public static void painelCliente(Cliente cliente) {
        boolean repetir = true;
        while (repetir) {
            System.out.println("====================================================");
            System.out.println("=============== BANCO 1000Devs =====================");
            System.out.println("====================================================");
            System.out.println("1 - Saldo");
            System.out.println("2 - Transferir");
            System.out.println("3 - Depositar");
            System.out.println("4 - Dados da Conta");
            System.out.println("5 - Sair");
            int opcao = Integer.parseInt(entrada.nextLine());
            switch (opcao) {
                case 1:
                    System.out.println("Saldo " + cliente.getSaldo());
                    break;
                case 2:
                    System.out.println("Informe o valor de transferencia: ");
                    float tranferencia = Float.parseFloat(entrada.nextLine());
                    System.out.println("Informe o número da conta de destino: ");
                    String numeroContaDestino = entrada.nextLine();
                    Cliente clienteDestino = null;
                    while (true) {
                        clienteDestino = clienteRepository.getByCliente(numeroContaDestino);
                        if (clienteDestino == null) {
                            clienteDestino = clienteRepository.getByCliente(numeroContaDestino);
                        }
                        if (clienteDestino != null) {
                            break;
                        }
                        System.out.println("A conta para transferencia está incorreta\n");
                        System.out.println("Informe o número da conta de destino: ");
                        numeroContaDestino = entrada.nextLine();
                    }

                    GerenteService.transferir(cliente, clienteDestino, tranferencia);

                    System.out.println("Saldo " + cliente.getSaldo());
                    break;
                case 3:
                    System.out.print("Informe o valor de deposito: ");
                    float valorDeposito = Float.parseFloat(entrada.nextLine());
                    GerenteService.depositar(cliente, valorDeposito);
                    break;
                case 4:
                    dadosPessoais(cliente);
                    break;
                case 5:
                    repetir = false;
                    break;
            }
        }
    }

    public static Cliente login() {

        while (true) {
            System.out.println("====================================================");
            System.out.println("===================== LOGIN ========================");
            System.out.println("====================================================");
            System.out.print("Informe o número da conta: ");
            String numeroConta = entrada.nextLine();
            Cliente cliente = clienteRepository.getByCliente(numeroConta);
            if (cliente == null) {
                cliente = clienteRepository.getByCliente(numeroConta);
            } else if (cliente == null){
                System.out.println("Número de conta Invalido");
            } else if (cliente != null) {
                if (cliente.getSenha() == null) {
                    System.out.println("Este é seu primeiro acesso\nVocê será redirecionado para cadastrar a sua senha\n");
                    primeiroAcesso();
                     return login();
                } else {
                    int tentativa = 3;
                    for (int i = 0; i < 4; i++) {
                        System.out.print("Informe a senha: ");
                        String senhaInformada = entrada.nextLine();
                        if (senhaInformada.equals(cliente.getSenha())) {
                            return cliente;
                        } else {
                            System.out.println("Senha invalida, você tem mais " + tentativa + " Tentativas");
                            tentativa--;
                            if (tentativa < 0) {
                                return null;
                            }

                        }
                    }
                }
            }

        }

    }

    private static void primeiroAcesso() {
        while (true) {
            System.out.println("====================================================");
            System.out.println("================= CADASTRAR SENHA ==================");
            System.out.println("====================================================");
            System.out.print("Informe o número da conta: ");
            String numeroConta = entrada.nextLine();
            if (validarNumeroConta(numeroConta)) {
                while (true) {
                    System.out.print("Infome uma senha: ");
                    String senha = entrada.nextLine();
                    System.out.print("Infome novamente a senha: ");
                    String senha1 = entrada.nextLine();
                    if (senha.equals(senha1)) {
                        Cliente cliente = clienteRepository.getByCliente(numeroConta);
                        if (cliente == null) {
                            cliente = clienteRepository.getByCliente(numeroConta);
                        }
                        cliente.setSenha(senha);
                        cadastrarSenha(senha, numeroConta);
                        System.out.println("Senha cadastrada com sucesso");
                        break;
                    } else {
                        System.out.println("As senhas não pode ser diferente");
                    }
                }
                break;
            } else {
                System.out.println("Número da conta invalido");
            }

        }
    }

    private static Boolean validarNumeroConta(String numeroConta) {
        if (clienteRepository.getByCliente(numeroConta) != null || clienteRepository.getByCliente(numeroConta) != null) {
            return true;
        }
        return false;
    }

    private static void cadastrarSenha(String senha, String numeroConta) {
        Cliente c = clienteRepository.getByCliente(numeroConta);
        String update = null;
        if (c == null) {
            c = clienteRepository.getByCliente(numeroConta);
        }
        if (c.getTipoConta().equals(TipoConta.PF)) {
            update = "update clientepf set senha = " + senha + " where id = " + c.getId() + ";";
        } else {
            update = "update clientepj set senha = " + senha + " where id = " + c.getId() + ";";
        }
        Conexao.executarDML(update);
    }


    public static void cadastroCliente() {
        System.out.println("Qual tipo de cliente deseja Cadastrar ? " +
                "\n1 - PJ" +
                "\n2 - PF");
    }

    public static Cliente dadosCliente(int tipoCliente) {
        ClientePf clientePf = new ClientePf();
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        String numeroConta = gerarNumeroConta();
        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();
        System.out.print("Limete do cheque Especial: ");
        Float limiteCheque = Float.parseFloat(entrada.nextLine());

        if (tipoCliente == 1) {
            String agencia = "01";
            ClientePj cliente = new ClientePj();
            System.out.print("CNPJ: ");
            String cnpj = entrada.nextLine();
            cliente.setNomeFantasia(nome);
            cliente.setAgencia(agencia);
            cliente.setTelefone(telefone);
            cliente.setNumeroConta(numeroConta);
            cliente.setLimiteChequeEspecial(limiteCheque);
            cliente.setTipoConta(TipoConta.PJ);
            cliente.setSaldo(0.0f);
            cliente.setCnpj(cnpj);
            return cliente;
        } else {

            clientePf.setTipoConta(TipoConta.PF);
            clientePf.setNome(nome);
            clientePf.setNumeroConta(numeroConta);
            clientePf.setAgencia("02");
            clientePf.setTelefone(telefone);
            clientePf.setLimiteChequeEspecial(limiteCheque);
            clientePf.setSaldo(0);
            System.out.print("Idade: ");
            clientePf.setIdade(Integer.parseInt(entrada.nextLine()));
            System.out.print("CPF: ");
            clientePf.setCpf(entrada.nextLine());

        }
        return clientePf;
    }

    private static String gerarNumeroConta() {
        Random random = new Random();

        String numeracao = "";
        for (int i = 0; i < 5; i++) {
            numeracao += Integer.toString(random.nextInt(9));
        }
        return numeracao;
    }

    public static void dadosCliente(Cliente cliente) {

        if (cliente.getTipoConta().equals(TipoConta.PF)) {
            ClientePf c = (ClientePf) cliente;
            System.out.println("-----------------------------------------------------");
            System.out.println("----------------- CLIENTE CADASTRADO ----------------");
            System.out.println("-----------------------------------------------------");
            System.out.println("Nome: " + c.getNome());
            System.out.println("Idade: " + c.getIdade());
            System.out.println("Telefone: " + c.getTelefone());
            System.out.println("Número conta: " + c.getNumeroConta());
            System.out.println("Agencia: " + c.getAgencia());
            System.out.println("Limite cheque Especial: " + c.getLimiteChequeEspecial());
            System.out.println("Tipo conta: " + c.getTipoConta().toString());
            System.out.println("Senha: " + c.getSenha());
            System.out.println("Saldo: " + c.getSaldo());
        } else {
            System.out.println("-----------------------------------------------------");
            System.out.println("----------------- CLIENTE CADASTRADO ----------------");
            System.out.println("-----------------------------------------------------");
            ClientePj pj = (ClientePj) cliente;
            System.out.println("Nome fantasia: " + pj.getNomeFantasia());
            System.out.println("Telefone: " + pj.getTelefone());
            System.out.println("Razão social: " + pj.getRazaoSocial());
            System.out.println("Agencia: " + pj.getAgencia());
            System.out.println("Cnpj: " + pj.getCnpj());
            System.out.println("Cheque especial: " + pj.getLimiteChequeEspecial());
            System.out.println("Numero da conta: " + pj.getNumeroConta());
            System.out.println("Saldo: " + pj.getSaldo());
        }


    }

    public static void dadosPessoais(Cliente c) {

        if (c.getTipoConta().equals(TipoConta.PF)) {
            ClientePf pf = (ClientePf) c;
            System.out.println("-----------------------------------------------------");
            System.out.println("------------------ DADOS PESSOAIAS ------------------");
            System.out.println("-----------------------------------------------------");
            System.out.println("Nome: " + pf.getNome());
            System.out.println("Idade: " + pf.getIdade());
            System.out.println("Telefone: " + pf.getTelefone());
            System.out.println("Número conta: " + pf.getNumeroConta());
            System.out.println("Agencia: " + pf.getAgencia());
            System.out.println("Limite cheque Especial: " + pf.getLimiteChequeEspecial());
            System.out.println("Tipo conta: " + pf.getTipoConta().toString());
            System.out.println("Senha: " + pf.getSenha());
            System.out.println("Saldo: " + pf.getSaldo());
        } else {
            ClientePj pj = (ClientePj) c;
            System.out.println("-----------------------------------------------------");
            System.out.println("------------------ DADOS PESSOAIAS ------------------");
            System.out.println("-----------------------------------------------------");
            System.out.println("Nome fantasia: " + pj.getNomeFantasia());
            System.out.println("Telefone: " + pj.getTelefone());
            System.out.println("Razão social: " + pj.getRazaoSocial());
            System.out.println("Agencia: " + pj.getAgencia());
            System.out.println("Cnpj: " + pj.getCnpj());
            System.out.println("Cheque especial: " + pj.getLimiteChequeEspecial());
            System.out.println("Numero da conta: " + pj.getNumeroConta());
            System.out.println("Saldo: " + pj.getSaldo());
        }

    }

    public static Cliente buscarCliente() {
        System.out.print("Número da conta: ");
        String numeroConta = entrada.nextLine();
        return clienteRepository.getByCliente(numeroConta);

    }


    public static void deletarCliente() {
        System.out.print("Digite o número da conta que deseja deletar: ");
        Cliente c = clienteRepository.getByCliente(entrada.nextLine());
        try {
            clienteRepository.deletarCliente(c.getId(), c.getTipoConta());
        } catch (NullPointerException e) {
            System.out.println("Conta não encontrada.");
        }
    }


}
