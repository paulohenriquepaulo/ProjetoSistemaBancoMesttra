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
	

	
/*	public void removerClientePf(int i) {
		this.clientesPf[i] = null;
	}*/

    public static void buscarClientePf() {
        ClientePf c = (ClientePf) MenuService.buscarCliente();
        try {
            System.out.println("Nome: " + c.getNome());
            System.out.println("CPF: " + c.getCpf());
            System.out.println("Idade: " + c.getIdade());
            System.out.println("Agencia: " + c.getAgencia());
            System.out.println("Cheque especial: " + c.getLimiteChequeEspecial());
            System.out.println("Numero da conta: " + c.getNumeroConta());
            System.out.println("Saldo: " + c.getSaldo());
            System.out.println("Telefone: " + c.getTelefone());
            System.out.println("Tipo conta: " + c.getTipoConta());
        } catch (NullPointerException e) {
            System.out.println("Cliente não encontrado");
        }

    }

    public void buscarClientePj(ClientePj p) {

        System.out.println("Agencia: " + p.getAgencia());
        System.out.println("Cnpj: " + p.getCnpj());
        System.out.println("Cheque especial: " + p.getLimiteChequeEspecial());
        System.out.println("Nome fantasia: " + p.getNomeFantasia());
        System.out.println("Numero da conta: " + p.getNumeroConta());
        System.out.println("Razão social: " + p.getRazaoSocial());
        System.out.println("Saldo: " + p.getSaldo());
        System.out.println("Telefone: " + p.getTelefone());
        System.out.print("Sócios: ");
        for (int c = 0; c < p.getNomeSocios().length; c++) {
            System.out.print(p.getNomeSocios()[c] + " | ");
        }

    }

	/*public void aumentarLimitePj(int i, Double val) {
		Double chequeEspecial = this.clientesPj[i].getLimiteChequeEspecial();
		chequeEspecial += val;
		this.clientesPj[i].setLimiteChequeEspecial(chequeEspecial);
	}
	
	public void aumentarLimitePf(int i, Double val) {
		Double chequeEspecial = this.clientesPf[i].getLimiteChequeEspecial();
		chequeEspecial += val;
		this.clientesPf[i].setLimiteChequeEspecial(chequeEspecial);
	}
	
	public void diminuirLimitePj(int i, Double val) {
		Double chequeEspecial = this.clientesPj[i].getLimiteChequeEspecial();
		chequeEspecial -= val;
		this.clientesPj[i].setLimiteChequeEspecial(chequeEspecial);
	}
	
	public void diminuirLimitePf(int i, Double val) {
		Double chequeEspecial = this.clientesPf[i].getLimiteChequeEspecial();
		chequeEspecial -= val;
		this.clientesPf[i].setLimiteChequeEspecial(chequeEspecial);
	}*/

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
	
/*	public Cliente recuperarCliente(String numeroConta) {
        for (int i = 0; i < 50; i++) {
            if (clientesPj[i] != null && clientesPj[i].getNumeroConta().equals(numeroConta)) {
                return (ClientePj) clientesPj[i];
            }
            if (clientesPf[i] != null && clientesPf[i].getNumeroConta().equals(numeroConta)) {
                return (ClientePf) clientesPf[i];
            }

        }
        return null;
    }*/

    private static void alterarSaldoPj(Integer id, Float saldo) {
        String update = "update clientepj set saldo = " + saldo + " where id = " + id + ";";
        Conexao.executarDML(update);
    }

    private static void alterarSaldoPf(Integer id, Float saldo) {
        String update = "update clientepf set saldo = " + saldo + " where id = " + id + ";";
        Conexao.executarDML(update);
    }
	
	/*public void addSaldo(String numeroConta, Double val) {
		Cliente cliente = recuperarCliente(numeroConta);
		cliente.setSaldo(cliente.getSaldo() + val);
		
		if (cliente.getTipoConta().equals(TipoConta.PF)) {
			int i = Helpers.varrerPorNumeroConta(this.clientesPf, numeroConta);
			this.clientesPf[i] = cliente;
			
		}
		else if (cliente.getTipoConta().equals(TipoConta.PJ)) {
			int i = Helpers.varrerPorNumeroConta(this.clientesPj, numeroConta);
			this.clientesPj[i] = cliente;
		}
	}*/

	/*public void editar(String numeroConta, int altTelefone) {
		
		Cliente c = recuperarCliente(numeroConta);
		c.setTelefone(altTelefone);
		
		if (c.getTipoConta().equals(TipoConta.PF)) {
			int i = Helpers.varrerPorNumeroConta(this.clientesPf, numeroConta);
			this.clientesPf[i] = c;
		}
		else if (c.getTipoConta().equals(TipoConta.PJ)) {
			int i = Helpers.varrerPorNumeroConta(this.clientesPj, numeroConta);
			this.clientesPj[i] = c;
		}
		
	}*/

}
