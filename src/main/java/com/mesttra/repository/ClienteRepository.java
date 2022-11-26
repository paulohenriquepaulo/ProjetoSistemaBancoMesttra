package com.mesttra.repository;

import com.mesttra.model.Cliente;
import com.mesttra.model.ClientePf;
import com.mesttra.model.ClientePj;
import com.mesttra.model.TipoConta;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRepository {
   

    public Cliente save(ClientePf clientepf) {
        String inserir = "INSERT INTO `clientepf` " + " VALUES (null, '" + clientepf.getNome() + "', '"
                + clientepf.getCpf() + "', " + "'" + clientepf.getIdade() + "', '" + clientepf.getNumeroConta()
                + "', '" + clientepf.getAgencia() + "','" + clientepf.getTelefone() + "', '" + clientepf.getSaldo()
                + "', '" + clientepf.getLimiteChequeEspecial() + "','" + clientepf.getTipoConta().toString() + " null');";
        System.out.println(inserir);
        Conexao.executarDML(inserir);
        return clientepf;
    }
    public Cliente save(ClientePj clientepj) {
        String inserir = "INSERT INTO `clientepj` " + " VALUES (null, '" + clientepj.getCnpj() + "', '"
                + clientepj.getRazaoSocial() + "', " + "'" + clientepj.getNomeFantasia() + "', '" + clientepj.getNumeroConta()
                + "', '" + clientepj.getAgencia() + "','" + clientepj.getTelefone() + "', '" + clientepj.getSaldo()
                + "', '" + clientepj.getLimiteChequeEspecial() + "','" + clientepj.getTipoConta().toString() + " null');";
        System.out.println(inserir);
        Conexao.executarDML(inserir);
        return clientepj;
    }
    public Cliente getByClientePf(String numeroConta) {
        String consulta = "select * from clientepf c where c.numeroConta = '"+ numeroConta + "';";
        ResultSet resultSet =  Conexao.executarConsulta(consulta);
        try {

            if (resultSet.next()) {
                String tipoConta = resultSet.getString("tipoConta");
                if (tipoConta.equals("PF")) {
                    ClientePf clientePf = new ClientePf();
                    clientePf.setSenha(resultSet.getString("senha"));
                    clientePf.setId(resultSet.getInt("id"));
                    clientePf.setTipoConta(TipoConta.PF);
                    clientePf.setNome(resultSet.getString("nome"));
                    clientePf.setIdade(resultSet.getInt("idade"));
                    clientePf.setTelefone(resultSet.getString("telefone"));
                    clientePf.setAgencia(resultSet.getString("agencia"));
                    clientePf.setCpf(resultSet.getString("cpf"));
                    float saldo = resultSet.getFloat("saldo");
                    clientePf.setSaldo(saldo);
                    clientePf.setNumeroConta(resultSet.getString("numeroConta"));
                  //  clientePf.setLimiteChequeEspecial(resultSet.getFloat("limiteChequeEspecial"));
                    return clientePf;
                } else {

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Cliente getByClientePj(String numeroConta) {
        String consulta = "select * from clientepj c where c.numeroConta = '"+ numeroConta + "';";
        ResultSet resultSet =  Conexao.executarConsulta(consulta);
        try {

            if (resultSet.next()) {
                String tipoConta = resultSet.getString("tipoConta");
                if (tipoConta.equals("PJ")) {
                    ClientePj clientePj = new ClientePj();
                    clientePj.setSenha(resultSet.getString("senha"));
                    clientePj.setId(resultSet.getInt("id"));
                    clientePj.setTipoConta(TipoConta.PJ);
                    clientePj.setTelefone(resultSet.getString("telefone"));
                    clientePj.setAgencia(resultSet.getString("agencia"));
                    float saldo = resultSet.getFloat("saldo");
                    clientePj.setSaldo(saldo);
                    clientePj.setNumeroConta(resultSet.getString("numeroConta"));
                    //  clientePf.setLimiteChequeEspecial(resultSet.getFloat("limiteChequeEspecial"));
                    return clientePj;
                } else {

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public  void deletarCliente(Integer id) {
        String delete = "DELETE FROM clientepf WHERE id ='" + id + "'";
        if (Conexao.executarDML(delete)) {
            System.out.println("Cliente deletado com sucesso.");
        }
    }
}
