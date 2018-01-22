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
    IFIntegrante IntegranteEmJDBC1;

    public BandasEmJDBC() {
        this.conexao = new Conexao().init();
        this.IntegranteEmJDBC1 = new IntegranteEmJDBC1();
    }

    @Override
    public Banda localizarPor(int id) {
         StringBuffer consulta = new StringBuffer();
        consulta.append("SELECT B.ID, B.NOMEFANTASIA NOMEBANDA ,IT.ID ID_INTEG ,IT.NOME NOME_INTEG, IT.CPF CPF_INTEG ");
        consulta.append("FROM BANDA B, INTEGRANTE IT, INTEGRANTE_BANDA IB ");
        consulta.append("WHERE B.ID = IB.ID_BANDA AND IT.ID = IB.ID_INTEGRANTE AND ");
        consulta.append(" B.ID=?");
        try {

            PreparedStatement Statement = conexao.prepareStatement(consulta.toString());
            Statement.setInt(1, id);

            ResultSet rs = Statement.executeQuery();

//            while (rs.next()) {
//                banda.setId(rs.getInt("ID"));
//                banda.setNomeFantasia(rs.getString("nomeFantasia"));
//                break;
//            }
            return criarBanda(rs).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Conexao.fecharConexao(conexao);
            } catch (SQLException ex) {
                Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new Banda();

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
        } finally {
            try {
                Conexao.fecharConexao(conexao);
            } catch (SQLException ex) {
                Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Banda> listarTodos() {
        List<Banda> retorno = null;
        try {
            StringBuffer consulta = new StringBuffer();
            consulta.append("SELECT B.ID, B.NOMEFANTASIA NOMEBANDA ,IT.ID ID_INTEG ,IT.NOME NOME_INTEG, IT.CPF CPF_INTEG ");
            consulta.append("FROM BANDA B, INTEGRANTE IT, INTEGRANTE_BANDA IB ");
            consulta.append("WHERE B.ID = IB.ID_BANDA AND IT.ID = IB.ID_INTEGRANTE");

            PreparedStatement statement = conexao.prepareStatement(consulta.toString());
            ResultSet resultSet = statement.executeQuery();
            retorno = criarBanda(resultSet);

        } catch (SQLException ex) {
            Logger.getLogger(AlbunsEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Conexao.fecharConexao(conexao);
            } catch (SQLException ex) {
                Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retorno;
    }

    @Override
    public boolean salvar(Banda banda) {
        boolean resultado = false;
        String sql = "INSERT INTO BANDA(nomeFantasia) VALUES(?)";
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            sql = "INSERT INTO integrante_banda(id_banda,id_integrante) VALUES(?,?)";
            PreparedStatement stmt2 = conexao.prepareStatement(sql);
            statement.setString(1, banda.getNomeFantasia());

            if (statement.executeUpdate() > 0) {
                insertIntegrantes(banda.getId(), banda.getIntegrantes(), stmt2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {

                Conexao.fecharConexao(conexao);
            } catch (SQLException ex) {
                Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return true;
    }

    @Override
    public Banda localizarPor(String nome) {
        Banda banda = new Banda();
        StringBuffer consulta = new StringBuffer();
        consulta.append("SELECT B.ID, B.NOMEFANTASIA NOMEBANDA ,IT.ID ID_INTEG ,IT.NOME NOME_INTEG, IT.CPF CPF_INTEG ");
        consulta.append("FROM BANDA B, INTEGRANTE IT, INTEGRANTE_BANDA IB ");
        consulta.append("WHERE B.ID = IB.ID_BANDA AND IT.ID = IB.ID_INTEGRANTE AND ");
        consulta.append(" B.NOMEFANTASIA=?");

        try {
            List<Banda> bandas = new ArrayList<>();
            PreparedStatement Statement = conexao.prepareStatement(consulta.toString());
            Statement.setString(1, nome);

            ResultSet rs = Statement.executeQuery();

//            while (rs.next()) {
//                banda.setId(rs.getInt("ID"));
//                banda.setNomeFantasia(rs.getString("nomeFantasia"));
//                break;
//            }
            banda = criarBanda(rs).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Conexao.fecharConexao(conexao);
            } catch (SQLException ex) {
                Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return banda;
    }

    @Override
    public List<Integrante> listarOsIntegrantes(int id_banda) {
        try {
            StringBuffer consulta = new StringBuffer();
            consulta.append("SELECT IT.ID ID_INTEG ,IT.NOME NOME_INTEG, IT.CPF CPF_INTEG ");
            consulta.append("FROM BANDA B, INTEGRANTE IT, INTEGRANTE_BANDA IB ");
            consulta.append("WHERE ").
                    append(id_banda).append("=IB.ID_BANDA AND IT.ID = IB.ID_INTEGRANTE");

            PreparedStatement statement = conexao.prepareStatement(consulta.toString());
            ResultSet rs = statement.executeQuery();

            List<Integrante> integrantes = new ArrayList<>();
            while (rs.next()) {
                String cpf = rs.getString("cpf_integ");
                Integrante integ = new Integrante(rs.getInt("id_integ"),
                        rs.getString("nome_integ"),
                        new CPF(cpf));

                integrantes.add(integ);
            }
            if (!integrantes.isEmpty()) {
                return integrantes;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Integrante localizarIntegrantePor(String nome) {
        return IntegranteEmJDBC1.localizarPor(nome);
    }

    private void insertIntegrantes(int id, List<Integrante> integrantes, PreparedStatement stmt2) throws SQLException {

        int i = 0;
        while (!integrantes.isEmpty()) {
            stmt2.setInt(1, id);
            stmt2.setInt(2, integrantes.get(i).getId());
            stmt2.executeUpdate();
            i++;
        }
        stmt2.close();
    }

    private List<Banda> criarBanda(ResultSet rs) throws SQLException {
        List<Banda> bandas = new ArrayList<>();

        while (rs.next()) {
            Banda banda = new Banda(rs.getInt("ID"), rs.getString("nomebanda"));
            List<Integrante> integrantes = new ArrayList<>();
            String cpf = rs.getString("cpf_integ");
            Integrante integ = new Integrante(rs.getInt("id_integ"),
                    rs.getString("nome_integ"),
                    new CPF(cpf));

            integrantes.add(integ);
            banda.setIntegrantes(integrantes);
            bandas.add(banda);
        }
        if (!bandas.isEmpty()) {
            return bandas;
        }
        return Collections.EMPTY_LIST;
    }

}
