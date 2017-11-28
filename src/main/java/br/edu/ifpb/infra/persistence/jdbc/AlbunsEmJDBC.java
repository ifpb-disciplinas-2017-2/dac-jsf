package br.edu.ifpb.infra.persistence.jdbc;

import br.edu.ifpb.domain.model.album.Album;
import br.edu.ifpb.domain.model.album.Albuns;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public AlbunsEmJDBC() {
        conexao = new Conexao();
    }

    //   private static final List<Album> albuns = new CopyOnWriteArrayList<>();
    @Override
    public boolean salvar(Album album) {
        boolean re = false;
        String sql = "INSERT INTO Album (descricao,dataDeLancamento,id_banda) VALUES(?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = conexao.init().prepareStatement(sql);

            statement.setString(1, album.getDescricao());
            statement.setDate(2, Date.valueOf(album.getDataDeLancamento()));
            statement.setInt(3, album.getBanda().getId());
            re = statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }

    @Override
    public List<Album> listarTodos() {
        try {
            String consulta = "SELECT * FROM Album";

            PreparedStatement statement = conexao.init().prepareStatement(consulta);
            return criarPessoa(statement);

        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.emptyList();

    }

    @Override
    public void excluir(Album albumParaExcluir) {

    }

    @Override
    public Album localizarPor(String descricao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Album> criarPessoa(PreparedStatement statement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
