
package br.edu.ifpb.infra.persistence.jdbc;

import br.edu.ifpb.domain.model.banda.Banda;
import br.edu.ifpb.domain.model.banda.Bandas;
import br.edu.ifpb.domain.model.banda.Integrante;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michelle Oliveira
 * @mail miolivc@outlook.com
 * @since 28/11/2017
 */

public class BandasEmJDBC implements Bandas {

    private Connection conexao;

    public BandasEmJDBC() {
        this.conexao = new Conexao().init();
    }

    @Override
    public void excluir(Banda bandaParaExcluir) {
        try {
            String sql = String.format("DELETE FROM BANDA WHERE ID = %d",
                    bandaParaExcluir.getId());
            Statement stmt = conexao.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Banda> listarTodos() {
        try {
            List<Banda> bandas = new ArrayList<>();
            ResultSet rs = conexao.createStatement().executeQuery("SELECT * FROM BANDA");
            while(rs.next()) {
                Banda banda = new Banda();
                banda.setId(rs.getInt("ID"));
                banda.setNomeFantasia(rs.getString("nomeFantasia"));
                bandas.add(banda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean salvar(Banda banda) {
        try {
            String sql = String.format("INSERT INTO BANDA(nomeFantasia) VALUES(%s)",
                    banda.getNomeFantasia());
            Statement stmt = conexao.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Banda localizarPor(String descricao) {
        Banda banda = new Banda();
        try {
            List<Banda> bandas = new ArrayList<>();
            ResultSet rs = conexao.createStatement().executeQuery("SELECT * FROM BANDA");
            while(rs.next()) {
                banda.setId(rs.getInt("ID"));
                banda.setNomeFantasia(rs.getString("nomeFantasia"));
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return banda;
    }

    @Override
    public List<Integrante> listarOsIntegrantes() {
    }

    @Override
    public Integrante localizarIntegrantePor(String nome) {
    }
    
    
}
