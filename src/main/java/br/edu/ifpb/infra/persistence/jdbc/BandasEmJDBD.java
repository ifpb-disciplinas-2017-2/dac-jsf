package br.edu.ifpb.infra.persistence.jdbc;

import br.edu.ifpb.domain.model.banda.Banda;
import br.edu.ifpb.domain.model.banda.Bandas;
import br.edu.ifpb.domain.model.banda.CPF;
import br.edu.ifpb.domain.model.banda.Integrante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 10:27:39
 */
public class BandasEmJDBD implements Bandas {

    private Connection connection;

    public BandasEmJDBD() {
        connection = Conexao.getConnection();
    }

    @Override
    public boolean salvar(Banda banda) {

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into banda(descricao) values (?)");

            preparedStatement.setString(1, banda.getNomeFantasia());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Banda> listarTodas() {

        List<Banda> lista = new ArrayList<>();
        List<Integrante> listaIntegrante = new ArrayList<>();

        IntegrantesEmJDBD integrante = new IntegrantesEmJDBD();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from banda");
            while (rs.next()) {
                Banda banda = new Banda();
                banda.setId(rs.getInt("id"));
                banda.setNomeFantasia(rs.getString("descricao"));

                for (Integrante i : integrante.listarTodos()) {
                    if (i.getBanda() == rs.getInt("id")) {
                        listaIntegrante.add(i);
                    }
                }

                banda.setIntegrantes(listaIntegrante);

                lista.add(banda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void excluir(Banda bandaParaExcluir) {

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from banda where id=?");

            preparedStatement.setInt(1, bandaParaExcluir.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Banda localizarPor(int id) {
        List<Integrante> listaIntegrante = new ArrayList<>();
        Banda banda = new Banda();

        IntegrantesEmJDBD integrante = new IntegrantesEmJDBD();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from banda where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                for (Integrante i : integrante.listarTodos()) {
                    if (i.getBanda() == rs.getInt("id")) {
                        listaIntegrante.add(i);
                    }
                }

                banda.setId(id);
                banda.setNomeFantasia(rs.getString("descricao"));
                banda.setIntegrantes(listaIntegrante);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return banda;
    }

    @Override
    public List<Integrante> listarOsIntegrantes() {
        
        
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
    public Integrante localizarIntegrantePor(int id) {
        
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
