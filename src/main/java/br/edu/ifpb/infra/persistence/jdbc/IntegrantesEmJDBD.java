
package br.edu.ifpb.infra.persistence.jdbc;

import br.edu.ifpb.domain.model.banda.CPF;
import br.edu.ifpb.domain.model.banda.Integrante;
import br.edu.ifpb.domain.model.banda.Integrantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IntegrantesEmJDBD implements Integrantes{
    
    private Connection connection;

    public IntegrantesEmJDBD() {
        connection = Conexao.getConnection();
    }

    @Override
    public void excluir(Integrante integranteParaExcluir) {
        
         try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from integrante where id=?");

            preparedStatement.setInt(1, integranteParaExcluir.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public List<Integrante> listarTodos() {
        
         List<Integrante> lista = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from integrante");
            while (rs.next()) {
                Integrante integrante = new Integrante();
                integrante.setId(rs.getInt("id"));
                integrante.setNome(rs.getString("nome"));
                
                CPF cpf = new CPF(rs.getString("cpf"));
                
                integrante.setCpf(cpf);
                integrante.setBanda(rs.getInt("banda"));
                

                lista.add(integrante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean salvar(Integrante integrante) {
         try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into integrante(nome, cpf, banda) values (?, ?, ?)");

            preparedStatement.setString(1, integrante.getNome());
            preparedStatement.setString(2, integrante.getCpf().value());
            preparedStatement.setInt(3, integrante.getBanda());

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Integrante localizarPor(int id) {
        
         Integrante integrante = new Integrante();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from integrante where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
 
                integrante.setId(rs.getInt("id"));
                integrante.setNome(rs.getString("nome"));
                
                CPF cpf = new CPF(rs.getString("cpf"));
                
                integrante.setCpf(cpf);
                integrante.setBanda(rs.getInt("banda"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return integrante;
    }
    
}
