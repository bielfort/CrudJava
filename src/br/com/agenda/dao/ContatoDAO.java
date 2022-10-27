package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    public Contato[] getContatos;

    /*
     * CRUD
     * C: CREATE
     * R: READ
     * U: UPDATE
     * D: DELETE
     */

    public void save(Contato contato) throws Exception {

        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // criar uma conexão com banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            // criamos uma preparedStatement, para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            // adicionar os valores que são esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getId());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //executar a query
            pstm.execute();

            System.out.println("Contato salvo com sucesso!");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //Fechar as conexões
            try {
                if(pstm!=null) {
                    pstm.close();
                }

                if(conn!=null) {
                    conn.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contato> getContatos(){

        String sql = "SELECT * FROM contatos";


        Connection conn = null;
        PreparedStatement pstm = null;
        // classe que vai recuperar os dados do banco. ****SELECT*****
        ResultSet rset = null;

        List<Contato> contatos = null;
        try {
            contatos = new ArrayList<Contato>();
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Contato contato = new Contato();

                //Recuperar o id
                contato.setId(rset.getInt("id"));
                //Recuperar o nome
                contato.setNome(rset.getString("nome"));
                //Recuperar idade
                contato.setId(rset.getInt("idade"));
                //Recuperar a data de cadastro
                contato.setDataCadastro(rset.getDate("datacadastro"));

                contatos.add(contato);


            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return contatos;

    }

}


