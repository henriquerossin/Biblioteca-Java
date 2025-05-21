
/*
 * 0 - adicionar o JAR do JDBC do SQLite (cfg) 1 - identificar onde que está o
 * arquivo de BD 2 - conectar com o BD 3 - definir qual é o SQL que vai ser
 * executado 4 - abrir a conexão com o BD 5 - executar o SQL 6 - acessar e
 * exibir os resultados
 *
 */

package src;

import java.sql.Statement;
import java.util.ArrayList;

import src.data.DALbiblioteca;
import src.model.livro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class biblioteca2024 {

    public static void main(String[] args) {

        try { // o main tem que estar assim!
            // 1 - identificar onde que está o arquivo de BD
            String jdbcUrl = "jdbc:sqlite:C:/Users/Pichau/Desktop/biblioteca2024/biblioteca2024.db";

            // 2 - conectar com o BD
            Connection connection = DriverManager.getConnection(jdbcUrl);
            // a partir daqui, apenas as chamadas
            // das funções de banco de dados

            // mostrar o estado inicial do BD
            mostrarLivros(connection);

            System.out.println("----------------------------------------");

            // inserir um livro novo
            DALbiblioteca.inserirLivro(connection);

            // mostrar como ficou o BD após a inserção
            mostrarLivros(connection);

            System.out.println("----------------------------------------");

            // deleta um livro
            DALbiblioteca.deletarDados(connection);

            // mostrar como ficou o BD após a deleção
            mostrarLivros(connection);

            System.out.println("----------------------------------------");

            // edita o titulo do livro
            DALbiblioteca.editarDados(connection);

            // mostrar como ficou o BD após a edição
            mostrarLivros(connection);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // método de exibição dos livros
    public static void mostrarLivros(Connection connection) {

        // chamada do método getLivros da classe DAL
        ArrayList<livro> livros = DALbiblioteca.getLivros(connection);

        // criar um for para iterar a lista de livros resultante do método que acessa o
        // BD
        System.out.println("Conteúdo da tabela 'livros': \n\n");
        for (livro livro : livros) { // for each / enhanced for
            System.out.println("Id: " + livro.getId());
            System.out.println("Titulo: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("Ano de publicacao: " + livro.getAnoPublicacao() + "\n");
        }

    }

    public static void deletarLivros(Connection connection) {

        //chamada do método getLivros da classe DAL
        ArrayList<livro> livros = DALbiblioteca.getLivros(connection);

        //criar um for para iterar a lista de livros resultante do método que acessa o BD
        System.out.println("Conteúdo da tabela 'livros': \n\n");
        for (livro livro : livros) { //for each / enhanced for
            System.out.println("Id: " + livro.getId());
            System.out.println("Titulo: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("Ano de publicacao: " + livro.getAnoPublicacao() + "\n");
        }
    }
}
