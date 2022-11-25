package com.mesttra.repository;

import com.mesttra.model.Cliente;
import com.mesttra.model.ClientePf;
import com.mesttra.model.TipoConta;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientePJRepository {
   

    public Cliente save(ClientePf clientepf) {
        String inserir = "INSERT INTO `clientepf` " + " VALUES (null, '" + clientepf.getNome() + "', '"
                + clientepf.getCpf() + "', " + "'" + clientepf.getIdade() + "', '" + clientepf.getNumeroConta()
                + "', '" + clientepf.getAgencia() + "','" + clientepf.getTelefone() + "', '" + clientepf.getSaldo()
                + "', '" + clientepf.getLimiteChequeEspecial() + "','" + clientepf.getTipoConta().toString() + "');";
        System.out.println(inserir);
        Conexao.executarDML(inserir);
        return clientepf;
    }

    public Cliente getByCliente(String numeroConta) {
        String consulta = "select * from clientepf c where c.numeroConta = '"+ numeroConta + "';";
        ResultSet resultSet =  Conexao.executarConsulta(consulta);
        try {

            if (resultSet.next()) {
                String tipoConta = resultSet.getString("tipoConta");
                if (tipoConta.equals("PF")) {
                    ClientePf clientePf = new ClientePf();
                    clientePf.setTipoConta(TipoConta.PF);
                    clientePf.setNome(resultSet.getString("nome"));
                    clientePf.setIdade(resultSet.getInt("idade"));
                    clientePf.setTelefone(resultSet.getString("telefone"));
                    clientePf.setAgencia(resultSet.getString("agencia"));
                    clientePf.setCpf(resultSet.getString("cpf"));
                  //  clientePf.setLimiteChequeEspecial(resultSet.getFloat("limiteChequeEspecial"));
                    return clientePf;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
}
