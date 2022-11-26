package com.mesttra.service;


import com.mesttra.model.Cliente;
import com.mesttra.model.ClientePf;
import com.mesttra.model.ClientePj;
import com.mesttra.model.TipoConta;
import com.mesttra.repository.ClienteRepository;
import com.mesttra.repository.Conexao;

public class GerenteService {


    private static ClienteRepository repository = new ClienteRepository();

    public GerenteService() {

    }

    public void cadastraClientePf(ClientePf c) {
        repository.save(c);
    }

    public void cadastraClientePj(ClientePj c) {
        repository.save(c);
    }


    public static void buscarCliente() {

        try {
            Cliente cliente = MenuService.buscarCliente();
            if (cliente.getTipoConta().equals(TipoConta.PF)) {
                ClientePf pf = (ClientePf) cliente;
                System.out.println("====================================================");
                System.out.println("===================== CLIENTE ======================");
                System.out.println("====================================================");
                System.out.println("Nome: " + pf.getNome());
                System.out.println("CPF: " + pf.getCpf());
                System.out.println("Idade: " + pf.getIdade());
                System.out.println("Agencia: " + pf.getAgencia());
                System.out.println("Cheque especial: " + pf.getLimiteChequeEspecial());
                System.out.println("Numero da conta: " + pf.getNumeroConta());
                System.out.println("Saldo: " + pf.getSaldo());
                System.out.println("Telefone: " + pf.getTelefone());
                System.out.println("Tipo conta: " + pf.getTipoConta());
            } else {
                ClientePj pj = (ClientePj) cliente;
                System.out.println("====================================================");
                System.out.println("===================== CLIENTE ======================");
                System.out.println("====================================================");
                System.out.println("Agencia: " + pj.getAgencia());
                System.out.println("Cnpj: " + pj.getCnpj());
                System.out.println("Cheque especial: " + pj.getLimiteChequeEspecial());
                System.out.println("Nome fantasia: " + pj.getNomeFantasia());
                System.out.println("Numero da conta: " + pj.getNumeroConta());
                System.out.println("Razão social: " + pj.getRazaoSocial());
                System.out.println("Saldo: " + pj.getSaldo());
                System.out.println("Telefone: " + pj.getTelefone());
            }

        } catch (NullPointerException e) {
            System.out.println("Cliente não encontrado");
        }
    }


    public static void transferir(Cliente origin, Cliente destino, Float valTransf) {


        if (origin.getSaldo() >= valTransf) {
            origin.setSaldo(origin.getSaldo() - valTransf);
            destino.setSaldo(destino.getSaldo() + valTransf);

            if (origin.getTipoConta().equals(TipoConta.PF)) {
                alterarSaldoPf(origin.getId(), origin.getSaldo());
            } else {
                alterarSaldoPj(origin.getId(), origin.getSaldo());
            }

            if (destino.getTipoConta().equals(TipoConta.PF)) {
                alterarSaldoPf(destino.getId(), destino.getSaldo());
            } else {
                alterarSaldoPj(destino.getId(), destino.getSaldo());
            }

            System.out.println("Valor transferido com sucesso!");
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }


    private static void alterarSaldoPj(Integer id, Float saldo) {
        String update = "update clientepj set saldo = " + saldo + " where id = " + id + ";";
        Conexao.executarDML(update);
    }

    private static void alterarSaldoPf(Integer id, Float saldo) {
        String update = "update clientepf set saldo = " + saldo + " where id = " + id + ";";
        Conexao.executarDML(update);
    }

    public static void depositar(Cliente cliente, float valor) {
        String query = null;
        if (valor > 0) {
            cliente.setSaldo((cliente.getSaldo() + valor));
            if (cliente.getTipoConta().equals(TipoConta.PJ)) {
                query = "update clientepj set saldo = " + cliente.getSaldo() + " where id  = " + cliente.getId() + ";";
            } else {
                query = "update clientepf set saldo = " + cliente.getSaldo() + " where id  = " + cliente.getId() + ";";
            }
            Conexao.executarDML(query);
            System.out.println("Deposito Efetuado com sucesso");
        } else {
            System.out.println("Valor de deposito invalido");
        }

    }


}
