package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Especificacao;

public class EspecificacaoDAO {

    private Connection connection;

    private void conecta() throws SQLException {
        if (this.connection != null) {
            return;
        }

        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/aula_lp", "postgres", "claudio123");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Boolean insert(Especificacao especificacao) {
        try {
            this.conecta();
            String SQLInsert = "INSERT INTO especificacao VALUES (?, ?)";
            PreparedStatement p = this.connection.prepareStatement(SQLInsert);
            p.setInt(1, especificacao.getId());
            p.setString(2, especificacao.getNome());
            p.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public Boolean update(Especificacao e) {
        return null;
    }

    public Boolean delete(Especificacao e) {
        return null;
    }

    public Especificacao findById(int id) {
        return null;
    }

    public List<Especificacao> findAll(Especificacao e) {
        List<Especificacao> result = new ArrayList<>();
        try {
            this.conecta();
            String SQL = "SELECT * FROM especificacao";
            PreparedStatement p = this.connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Especificacao especificacao = new Especificacao();
                especificacao.setNome(rs.getString("nome"));
                especificacao.setId(rs.getInt("id"));
                result.add(especificacao);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao executar a SQL " + ex.getMessage());
        }
        return result;
    }

}
