package com.ads.bd2.academico.persistencia;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ads.bd2.academico.modelo.Aluno;

public class DAOJDBCAluno extends DAOJDBC<Aluno> {

	@Override
	public void create(Aluno object) {
		String sql = "INSERT INTO aluno(matricula, nome, datamatricula) VALUES (?, ?, ?)";
		if (object != null) {
			init();
			try {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, object.getMatricula());
				statement.setString(2, object.getNome());
				statement.setObject(3, object.getDataMatricula());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
		}
	}

	@Override
	public void delete(Aluno object) {
		String sql = "DELETE FROM aluno	WHERE matricula = ?;";
		if (object != null){
			init();
			try{
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, object.getMatricula());
				statement.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			close();
		}
	}

	@Override
	public void update(Aluno object) {
		String sql = "UPDATE aluno SET matricula=?, nome=?, datamatricula=? WHERE matricula = ?;";
		if(object != null){
			init();
			try{
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(4, object.getMatricula());
				statement.setInt(1, object.getMatricula());
				statement.setString(2, object.getNome());
				@SuppressWarnings("deprecation")
				Date data = new Date(object.getDataMatricula().getYear(), object.getDataMatricula().getMonthValue(), object.getDataMatricula().getDayOfMonth());
				statement.setDate(3, data);
				statement.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			close();
		}
		
	}

	@Override
	public Aluno find(Aluno object) {
		//colete o atributo que identifica o aluno e o recupere do banco.
		//devolva um objeto aluno com essas informa��es, mapeando as colunas do
		//ResultSet como atributos desse aluno.
		
		String sql = "SELECT matricula, nome, datamatricula	FROM aluno WHERE matricula = ?";
		if(object != null){
			init();
			try{
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, object.getMatricula());
				ResultSet resultado = statement.executeQuery();
				if(resultado.next()){
					Aluno aluno = new Aluno();
					aluno.setMatricula(resultado.getInt(1));
					aluno.setNome(resultado.getString(2));
					aluno.setDataMatricula((LocalDate) resultado.getObject(3));
					return aluno;
				}
				else{
					throw new Exception("Aluno n�o encontrado!");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			close();
		}
		return null;
		
	}

}
