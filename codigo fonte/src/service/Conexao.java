package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    public static ConexaoBanco conexao;

    public static void main(String[] args) {
        conexao = new ConexaoBanco();

        opcoes();

    }

    public static void opcoes() {
        while (true) {
            System.out.println("Digite a opção: \n1 - insert\n2 - delete\n3 - select\n4 - fechar");
            int opcao = new Scanner(System.in).nextInt();
            switch (opcao) {
                case 1:
                    insert();
                    break;

                case 2:
                    delete();
                    break;

                case 3:
                    select();
                    break;

                case 4:
                    System.exit(0);
                    break;
            }

        }
    }

    public static void insert() {
        System.out.println("Digite o nome da marca: ");
        String nome = new Scanner(System.in).nextLine();
        conexao.insert(nome);
    }

    public static void select() {
        System.out.println("Digite o filtro: ");
        String filtro = new Scanner(System.in).nextLine();
        conexao.select(filtro);
    }

    public static void delete() {
        conexao.select("");
        System.out.println("Digite o código da marca: ");
        int id = new Scanner(System.in).nextInt();
        conexao.delete(id);
    }

    public static class ConexaoBanco {

        private Connection connection;

        public ConexaoBanco() {
            try {
                Class.forName("org.postgresql.Driver");
                this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/aula_lp", "postgres", "claudio123");
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        public void insert(String dado) {
            try {
                String sqlinsert = "INSERT INTO MARCA (id, nome) VALUES (nextval('public.sequencia_marca'), ?)";
                PreparedStatement p = connection.prepareStatement(sqlinsert);
                p.setString(1, dado);
                p.execute();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        public void delete(int id) {
            try {
                String sqldelete = "DELETE FROM MARCA WHERE id = ?";
                PreparedStatement p = connection.prepareStatement(sqldelete);
                p.setInt(1, id);
                p.execute();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        public void select(String filtro) {
            try {
                String sqlselect = "SELECT * FROM MARCA";
                if (!filtro.isEmpty()) {
                    sqlselect += " nome LIKE '%?%'";
                }
                PreparedStatement p = connection.prepareStatement(sqlselect);
                if (!filtro.isEmpty()) {
                    p.setString(0, filtro);
                }
                ResultSet result = p.executeQuery();

                while (result.next()) {
                    System.out.println(result.getInt("id") + "\t" + result.getString("nome"));
                }

                result.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
