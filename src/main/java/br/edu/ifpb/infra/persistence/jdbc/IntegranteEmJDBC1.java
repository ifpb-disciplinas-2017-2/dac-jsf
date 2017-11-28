package br.edu.ifpb.infra.persistence.jdbc;

import br.edu.ifpb.domain.model.album.Album;
import br.edu.ifpb.domain.model.album.Albuns;
import br.edu.ifpb.domain.model.banda.CPF;
import br.edu.ifpb.domain.model.banda.Integrante;
import br.edu.ifpb.infra.persistence.jdbc.Conexao;
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
public class IntegranteEmJDBC1 implements IFIntegrante {

    private Conexao conexao;

    public IntegranteEmJDBC1() {
        conexao = new Conexao();
    }

    //   private static final List<Album> albuns = new CopyOnWriteArrayList<>();
    @Override
    public boolean salvar(Integrante integrante) {
        boolean re = false;
        String sql = "INSERT INTO integrante (nome,cpf) VALUES(?,?)";
        PreparedStatement statement = null;
        try {
            statement = conexao.init().prepareStatement(sql);

            statement.setString(1, integrante.getNome());
            statement.setString(2, integrante.getCpf().formatted());

            re = statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }

    @Override
    public List<Integrante> listarTodos() {
        try {
            String consulta = "SELECT * FROM integrante";

            PreparedStatement statement = conexao.init().prepareStatement(consulta);
            return criarIntegrante(statement);

        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.emptyList();

    }

    @Override
    public void excluir(Integrante integrantearaExcluir) {
        try {
            String sql = "DELETE FROM Album WHERE id=?";
            PreparedStatement statement = conexao.init().prepareStatement(sql);
            statement.setInt(1, integrantearaExcluir.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Integrante localizarPor(String nome) {
        StringBuffer consulta = new StringBuffer("SELECT * FROM integrante where nome ");
        consulta.append(nome);

        PreparedStatement statement = null;
        try {
            statement = conexao.init().prepareStatement(consulta.toString());
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return criarIntegrante(statement).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    private List<Integrante> criarIntegrante(PreparedStatement statement) throws SQLException {
        List<Integrante> integers = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integrante integrante = new Integrante(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    new CPF(resultSet.getString("dataDeLancamento")));
            integers.add(integrante);

        }

        return integers;
    }
}
