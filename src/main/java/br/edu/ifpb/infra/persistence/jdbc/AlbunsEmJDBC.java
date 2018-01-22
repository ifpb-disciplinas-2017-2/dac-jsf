package br.edu.ifpb.infra.persistence.jdbc;

import br.edu.ifpb.domain.model.album.Album;
import br.edu.ifpb.domain.model.album.Albuns;
import br.edu.ifpb.domain.model.banda.Banda;
import br.edu.ifpb.domain.model.banda.Bandas;
import br.edu.ifpb.domain.model.banda.Integrante;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 10:27:39
 */
public class AlbunsEmJDBC implements Albuns {

    private Conexao conexao;
    private Bandas bandas;

    public AlbunsEmJDBC() {
        conexao = new Conexao();
    }

    //   private static final List<Album> albuns = new CopyOnWriteArrayList<>();
    @Override
    public boolean salvar(Album album) {
        boolean resultado = false;
        String sql = "INSERT INTO Album (descricao,dataDeLancamento,id_banda) VALUES(?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = conexao.init().prepareStatement(sql);

            statement.setString(1, album.getDescricao());
            statement.setDate(2, Date.valueOf(album.getDataDeLancamento()));
            statement.setInt(3, album.getBanda().getId());
            if(statement.executeUpdate()>0)
                resultado = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public List<Album> listarTodos() {
        try {
            String consulta = "SELECT * FROM Album";

            PreparedStatement statement = conexao.init().prepareStatement(consulta);
            return criarAlbum(statement);

        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.emptyList();

    }

    @Override
    public void excluir(Album albumParaExcluir) {
        try {
            String sql = "DELETE FROM Album WHERE id=?";
            PreparedStatement statement = conexao.init().prepareStatement(sql);
            statement.setInt(1, albumParaExcluir.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Album localizarPor(String descricao) {
         StringBuffer consulta = new StringBuffer("SELECT * FROM Album WHERE descricao=?");
         
         
         
         System.err.println(consulta.toString());

            PreparedStatement statement = null;
        try {
            statement = conexao.init().prepareStatement(consulta.toString());
            statement.setString(1, descricao);
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return criarAlbum(statement).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       
    }

    private List<Album> criarAlbum(PreparedStatement statement) throws SQLException {
        List<Album> albuns = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Album album = new Album(
                    resultSet.getInt("id"),
                    resultSet.getString("descricao"),
                    resultSet.getDate("DATADELANCAMENTO").toLocalDate()
            );
            this.bandas = new BandasEmJDBC();
            System.err.println("id banda "+resultSet.getInt("id_banda"));
            Banda banda = bandas.localizarPor(resultSet.getInt("id_banda"));
            album.setBanda(banda);
            albuns.add(album);
        }

        return albuns;
    }
}
