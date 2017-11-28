/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.infra.persistence.jdbc;

import br.edu.ifpb.domain.model.banda.Integrante;
import java.util.List;

/**
 *
 * @author jose
 */
public interface IFIntegrante {

    void excluir(Integrante integrantearaExcluir);

    List<Integrante> listarTodos();

    Integrante localizarPor(String descricao);

    //   private static final List<Album> albuns = new CopyOnWriteArrayList<>();
    boolean salvar(Integrante integrante);
    
}
