package br.com.agenda.aplicacao;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {

        ContatoDAO contatoDao = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("Gabriel Belfort");
        contato.setId(27);
        contato.setDataCadastro(new Date());

        contatoDao.save(contato);

        //visualização dos registros do banco de dados TODOS



    }
}
