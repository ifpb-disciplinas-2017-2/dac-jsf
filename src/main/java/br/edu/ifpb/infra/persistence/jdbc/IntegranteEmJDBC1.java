package br.edu.ifpb.infra.persistence.jdbc;

import br.edu.ifpb.domain.model.album.Album;
import br.edu.ifpb.domain.model.album.Albuns;
import br.edu.ifpb.domain.model.banda.CPF;
import br.edu.ifpb.domain.model.banda.Integrante;
import br.edu.ifpb.infra.persistence.jdbc.Conexao;
import java.sql.Connection;
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

    private Connection conexao;

    public IntegranteEmJDBC1() {
        conexao = new Conexao().init();
    }

    //   private static final List<Album> albuns = new CopyOnWriteArrayList<>();
    @Override
    public boolean salvar(Integrante integrante) {
        boolean resultado = false;
        String sql = "INSERT INTO integrante (nome,cpf) VALUES(?,?)";
        PreparedStatement statement = null;
        try {
            statement = conexao.prepareStatement(sql);

            statement.setString(1, integrante.getNome());
            statement.setString(2, integrante.getCpf().formatted());

            if(statement.executeUpdate()>0);
            resultado= true;
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        finally{
            try {
                Conexao.fecharConexao(conexao);
            } catch (SQLException ex) {
                Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public List<Integrante> listarTodos() {
         List<Integrante> integrantes = null;
        try {
           
            String consulta = "SELECT * FROM integrante";

            PreparedStatement statement = conexao.prepareStatement(consulta);
             integrantes =new ArrayList<>(criarIntegrante(statement));

        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         finally{
            try {
                Conexao.fecharConexao(conexao);
            } catch (SQLException ex) {
                Logger.getLogger(IntegranteEmJDBC1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         return !integrantes.isEmpty() ? integrantes : Collections.EMPTY_LIST;

    }

    @Override
    public void excluir(Integrante integrantearaExcluir) {
        try {
            String sql = "DELETE FROM integrante WHERE id=?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, integrantearaExcluir.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            try {
                Conexao.fecharConexao(conexao);
            } catch (SQLException ex) {
                Logger.getLogger(IntegranteEmJDBC1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Integrante localizarPor(String nome) {
        StringBuffer consulta = new StringBuffer("SELECT * FROM integrante where nome=?");
        Integrante Integrante = new Integrante();


        PreparedStatement statement = null;
        try {
            statement = conexao.prepareStatement(consulta.toString());
            statement.setString(1, nome);
            Integrante= criarIntegrante(statement).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            try {
                Conexao.fecharConexao(conexao);
            } catch (SQLException ex) {
                Logger.getLogger(IntegranteEmJDBC1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return Integrante;
    }

    private List<Integrante> criarIntegrante(PreparedStatement statement) throws SQLException {
        List<Integrante> integers = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integrante integrante = new Integrante(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    new CPF(resultSet.getString("CPF")));
            System.err.println("eee "+integrante.getNome());
            integers.add(integrante);

        }
if(!integers.isEmpty())
        return integers;
return Collections.EMPTY_LIST;
    }
}
