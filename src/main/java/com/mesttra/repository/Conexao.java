package com.mesttra.repository;

import com.mesttra.model.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Conexao {

    private static Connection connection = null;
    private static java.sql.Statement statement = null;
    private static ResultSet resultSet = null;


    public static void conectar() {
        // Criando a variável para se conectar ao nosso servidor e DataBase
        String servidor = "jdbc:mysql://localhost/sistemabancario";

        // Criando a variável do ususario do banco de dados
        String usuario = "root";

        // Criando a variável senha do banco de dados
        String senha = "qwe123";

        // Criando a variável de conexão
        String driver = "com.mysql.cj.jdbc.Driver";

        // Criando o processo de analise da nossa conexão com o banco de dados
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(servidor, usuario, senha);
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static ResultSet executarConsulta(String consulta) {
        if (!estaConectado()) {
            conectar();
        }
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.println("Não conseguiu executar a consulta\n" + consulta);
            // Caso ocorra algum erro desconecta do banco de dados.

        }

        return resultSet;
    }

    public static boolean executarDML(String dml) {
        if (!estaConectado()) {
            conectar();
        }
        boolean ok = false;
        try {
            statement = connection.createStatement();
            statement.execute(dml);
            ok = true;
        } catch (SQLException ex) {
            System.out.println("Nao conseguiu executar o DML\n" + dml);
        }

        return ok;
    }

    public static boolean estaConectado() {
        if (connection != null) {
            return true;
        }
        return false;
    }
}
