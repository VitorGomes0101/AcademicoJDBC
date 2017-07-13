package com.ads.bd2.academico.persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ads.bd2.academico.modelo.Curso;

public class DAOJDBCCurso extends DAOJDBC<Curso> {

	@Override
	public void create(Curso object) {
		String sql = "INSERT INTO public.curso(	nome, codigo, coodernador, 'cargaHoraria')	VALUES (?, ?, ?, ?);";
		if (object != null) {
			init();
			try {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, object.getNome());
				statement.setString(3, object.getCoordenador());
				statement.setInt(4, object.getCargaHoraria());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
		}
	}

	@Override
	public void delete(Curso object) {
		String sql = "DELETE FROM curso	WHERE codigo=?;";
		if (object != null){
			init();
			try{
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, object.getCodigo());
				statement.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			close();
		}

	}

	@Override
	public void update(Curso object) {


	}

	public void update(Curso object, boolean cascade) {
		//Implementaremos um update em cascata aqui (o curso + os alunos).
		//chame o update do curso para atualizar o o curso em si.
		
		//se cascade true, caso n�o haja nenhum aluno no curso passado, remover v�nculos dos alunos no banco tamb�m.
		//Caso haja algum aluno, remova os v�nculos dos alunos que o curso j� tinha no banco primeiro, para limpar. 
		//Em seguida, vincule os alunos contidos no curso passado como par�metro neste m�todo.
		//Caso alguns dos alunos presentes no curso passado como par�metro j� exista no banco, considerando que eles
		//podem ter algum atributo atualizado, atualize esses alunos tamb�m (use o dao do aluno nessa situa��o para evitar retrabalho)
	}

	@Override
	public Curso find(Curso object) {
		//colete o atributo que identifica o curso e o recupere do banco.
		//devolva um objeto curso com essas informa��es, mapeando as colunas do
		//ResultSet como atributos desse curso.

		return null;
	}

	public Curso find(Curso object, boolean cascade) {
		//colete o atributo que identifica o curso e o recupere do banco.
		//devolva um objeto curso com essas informa��es, mapeando as colunas do
		//ResultSet como atributos desse curso.

		//se cascade for true, traga os alunos e os adicione ao curso, se tiverem alunos neste curso no banco.
		return null;
	}

}
