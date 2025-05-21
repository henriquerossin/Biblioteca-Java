package src.model;

public class livro {
	
	
	
	//os atributos da tabela livros
	private String titulo, autor, editora;
	private int id, anoPublicacao;
	
	//apenas o nosso construtor
	public livro (	int id, 
					String titulo,
					String autor,
					String editora,
					int anoPublicacao	) {
		
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.anoPublicacao = anoPublicacao;
	}

	//aqui apenas os get e set
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return this.editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getAnoPublicacao() {
		return this.anoPublicacao;
	}

	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

}
