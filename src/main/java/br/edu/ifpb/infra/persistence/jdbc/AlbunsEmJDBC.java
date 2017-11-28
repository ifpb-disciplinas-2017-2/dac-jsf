package br.edu.ifpb.infra.persistence.jdbc;

import br.edu.ifpb.domain.model.album.Album;
import br.edu.ifpb.domain.model.album.Albuns;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 10:27:39
 */
public class AlbunsEmJDBC implements Albuns {

     private Connection connection;

    public AlbunsEmJDBC() {
        connection = Conexao.getConnection();
    }

    @Override
    public boolean salvar(Album album) {

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into album(descricao, dataDeLancamento) values (?, ?)");

            LocalDate localDate = album.getDataDeLancamento();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateInStr = localDate.format(formatter);

            preparedStatement.setString(1, album.getDescricao());
            preparedStatement.setString(2, dateInStr);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Album> listarTodos() {

        List<Album> lista = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from album");
            while (rs.next()) {
                Album album = new Album();
                album.setId(rs.getInt("id"));
                album.setDescricao(rs.getString("descricao"));

                String data = rs.getString("dataDeLancamento");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(data, formatter);

                album.setDataDeLancamento(date);

                lista.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void excluir(Album albumParaExcluir) {

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from album where id=?");

            preparedStatement.setInt(1, albumParaExcluir.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Album localizarPor(int id) {
         Album album = new Album();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from album where descricao=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
 
                album.setId(rs.getInt("id"));
                album.setDescricao(rs.getString("descricao"));
                album.setBanda(rs.getInt("banda"));
                
                String data = rs.getString("dataDeLancamento");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(data, formatter);
                
                album.setDataDeLancamento(date);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return album;
    }
}
