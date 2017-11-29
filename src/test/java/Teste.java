
import br.edu.ifpb.domain.model.album.Album;
import br.edu.ifpb.domain.model.album.Albuns;
import br.edu.ifpb.domain.model.banda.Banda;
import br.edu.ifpb.domain.model.banda.CPF;
import br.edu.ifpb.domain.model.banda.Integrante;
import br.edu.ifpb.infra.persistence.jdbc.AlbunsEmJDBC;
import br.edu.ifpb.infra.persistence.jdbc.BandasEmJDBC;
import br.edu.ifpb.infra.persistence.jdbc.IFIntegrante;
import br.edu.ifpb.infra.persistence.jdbc.IntegranteEmJDBC1;
import br.edu.ifpb.infra.persistence.memory.AlbunsEmMemoria;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jose
 */
public class Teste {

    public static void main(String[] args) {
//         Banda banda = new Banda();
//         banda.setNomeFantasia("NX0");
//         banda.setId(1);
//         Album album = new Album(0, "t descrição", LocalDate.of(1982, Month.MARCH, 10));
//                 album.setBanda(banda);
//       //  boolean r = dao.salvar(album);
//       album.setId(4);
        // dao.excluir(album);
//         IFIntegrante dao = new IntegranteEmJDBC1();
//         Integrante integrante = new Integrante(1, "jose", new CPF("12345678909"));
//         integrante.setId(1);
//         // boolean r = dao.salvar(integrante);
//         // System.err.println("result "+dao.localizarPor("jose"));
//       //  boolean e = dao.salvar(integrante);
//         System.err.println("rrree" +dao.localizarPor("maria"));
        Banda banda = new Banda();
        banda.setId(1);
        banda.setNomeFantasia("teste");
        List<Integrante> integrantes = new ArrayList<>();
        integrantes.add(new Integrante(3, "jose", new CPF("12121212121")));
        integrantes.add(new Integrante(1, "maria", new CPF("12345678911")));
        banda.setIntegrantes(integrantes);

        BandasEmJDBC dao = new BandasEmJDBC();

        // boolean e = dao.salvar(banda);
        System.err.println("erttttt " + dao.listarOsIntegrantes(1));

    }
}
