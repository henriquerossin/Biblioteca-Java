package src.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import src.model.livro;

public class DALbiblioteca {

	// um método para consultar o BD
	public static ArrayList<livro> getLivros(Connection connection) {

		// criar uma lista auxiliar de livros
		ArrayList<livro> livros = new ArrayList<livro>();

		// todos comandos de BD tem que estar dentro de um try catch
		try {
			// 3 - definir qual é o SQL que vai ser executado
			String sql = "SELECT * FROM livros";

			// 4 - abrir a conexão com o BD
			Statement statement = connection.createStatement();

			// 5 - executar o SQL
			ResultSet resultado = statement.executeQuery(sql);

			// 6 - acessar os resultados e armazená-los em uma lista de objetos
			while (resultado.next()) {
				int id = resultado.getInt("id"); // 1
				String titulo = resultado.getString("titulo"); // código limpo
				String autor = resultado.getString("autor"); // robert c martin
				String editora = resultado.getString("editora"); // alta books
				int anoPublicacao = resultado.getInt("anoPublicacao"); // 2009
				// criar um novo objeto de livro com as informações que vieram do BD
				livro livro = new livro(id, titulo, autor, editora, anoPublicacao);
				// adiciona o novo objeto de livro em uma lista de livros
				livros.add(livro);
			}

		} catch (SQLException e) {
			// System.out.println(e);
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return livros;

	}// fim do método getLivros

	// um método para inserir dados no BD
	public static void inserirLivro(Connection connection) {

		// primeiro, vamos ler o teclado para o usuário informar
		// quais dados serão inseridos no BD
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Digite o ID do livro (número inteiro): ");
			// String id = scanner.nextLine(); //isso retorna uma string
			int id = scanner.nextInt(); // assim, já retorna um int
			// quando usamos o next int, o scanner consome apenas o valor
			// inteiro, mas o buffer do teclado sempre coloca um \n no fim
			// de cada instrução que é lida do teclado
			// se a gente ser ler direto, ela vai ficar com o \n
			// que tava sobrando no buffer
			scanner.nextLine(); // para poder consumir o \n e limpar o buffer
			System.out.println("Digite o título do livro: ");
			String titulo = scanner.nextLine(); // que é usado pra ler string
			System.out.println("Digite o autor do livro: ");
			String autor = scanner.nextLine();
			System.out.println("Digite a editora do livro: ");
			String editora = scanner.nextLine();
			System.out.println("Digite o ano de publicacao do livro: ");
			int anoPublicacao = scanner.nextInt();
			
			// depois, é necessário fazer a 
			// definição da query sql de inserção de dados
			String sql = " INSERT INTO livros " +
						 " (id, titulo, autor, editora, anoPublicacao) " +
						 " VALUES (?, ?, ?, ?, ?)";
			
			// após, fazer a inicialização do objeto que permite criar
			// queries com parâmetros
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//por fim, fazer o preenchimento dos parâmetros
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, titulo);
			preparedStatement.setString(3, autor);
			preparedStatement.setString(4, editora);
			preparedStatement.setInt(5, anoPublicacao);
			
			//agora, só falta executar a query sql
			int linhasAfetadas = preparedStatement.executeUpdate();
			
			if (linhasAfetadas > 0) {
				System.out.println("\nInserção efetuada com sucesso!\n");
			} else { 
				System.out.println("\nNenhuma linha do BD foi afetada!");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL: " + e.getMessage());
		} catch (Exception e1) {
			System.out.println("Erro genérico: " + e1.getMessage());
		}
	}

	// um método para editar dados do BD
	public static void editarDados(Connection connection) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Informe o ID do livro que deseja atualizar");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Informe o novo titulo do livro: ");
		String titulo = scanner.nextLine();
		String sql = "UPDATE livros SET titulo = ? WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, titulo);
			preparedStatement.setInt(2, id);
			int rowsAffection = preparedStatement.executeUpdate();
			if (rowsAffection > 0) {
				System.out.println("Registo atualizado com sucesso!");
			} else {
				System.out.println("Nenhum registro foi atualizado. Verifique o ID informado");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar dados: " + e.getMessage());
		}
		
	}

	// um método para deletar dados do BD
	public static void deletarDados(Connection connection) {
		Scanner scanner = new Scanner (System.in);
		System.out.println("Informe o ID do livro que deseja deletar do banco de dados: ");
		int id = scanner.nextInt();
		String sql = "DELETE FROM livros where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Registro deletado com sucesso!");
			} else {
				System.out.println("Nenhum registro encontrado.");
			}
		} catch (SQLException e){
			System.out.println("Erro ao deletar registro: " + e.getMessage());
		}
	}

}
