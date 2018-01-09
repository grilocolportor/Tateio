package org.avs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.avs.bean.Cidade;
import org.avs.bean.Estados;
import org.avs.bean.Mensagens;
import org.avs.bean.User;
import org.avs.utils.Constantes;

public class UserRepository {

	public static User addUser(User user) {

		String sql = "INSERT INTO USUARIO (NOME, EMAIL, TELEFONE, SENHA, USUARIO_STATUS, UF, CIDADE) VALUES ('" + user.getNome() + "', '"
				+ user.getEmail() + "', '" + user.getTelefone() + "', '" + user.getSenha() + "', " + user.getUsuarioStatus() + ", "
						+ user.getUf().getId() + ", " + user.getCidade().getId() + " )";
		
		try {
			Statement st = (Statement) DataBase.getConexaoMySQL().createStatement();
			st.executeUpdate(sql);
			DataBase.FecharConexao();

			// Chamar metodo para carregar e salvar a photo de perfil
			// retorna o caminho

			// retorna o usuario cadastrado
			User u = new User();
			List<User> lu = getUserByParam(user);
			if (lu.size() > 0) {
				u = lu.get(0);
			}
			return u;
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			Mensagens m = new Mensagens();
			m.setCod(e.getErrorCode());
			m.setMensagem("Error" + " : " + e.getMessage());
			User us = new User();
			us.setMensages(m);
			// u.setMensages(new Mensagens(Constantes.CODIGO_ERROR_DESCONHECIDO,
			// Constantes.MESSAGE_ERROR_DESCONHECIDO));
			return us;
		}

	}

	public static List<User> getUserByParam(User user) {

		List<User> lstUser = new ArrayList<User>();

		String sql = " SELECT * FROM USUARIO " + mountSql(user);

		try {
			Statement st = (Statement) DataBase.getConexaoMySQL().createStatement();
			ResultSet rs = st.executeQuery(sql);
			DataBase.FecharConexao();

			while (rs.next()) {
				User u = new User();
				u.set_id(rs.getInt("_ID"));
				u.setEmail(rs.getString("EMAIL"));
				u.setNome(rs.getString("NOME"));
				u.setSenha(rs.getString("SENHA"));
				u.setTelefone(rs.getString("TELEFONE"));
				//fazer a consulta a cidade e estados
				List<Cidade> lstCidade = new ArrayList<>();
				lstCidade = getCidadeByParam(u.getCidade());
				u.setCidade(lstCidade.get(0));
				
				Mensagens m = new Mensagens();
				m.setCod(Constantes.COD_OK);
				m.setMensagem(Constantes.MESSAGE_OK);
				u.setMensages(m);
			
				lstUser.add(u);
			}

		} catch (SQLException e) {
			User u = new User();
			Mensagens m = new Mensagens();
			m.setCod(e.getErrorCode());
			m.setMensagem("Error!" + " -- " + e.getMessage());
			u.setMensages(m);;
			lstUser.add(u);
		}

		return lstUser;

	}

	public static List<Estados> getEstadoByParam(Estados estado){
		
		List<Estados> lstEstados = new ArrayList<>();
		
		String sql = " WHERE 1=1 ";
		
		if(estado.getId()!=0){
			sql =sql + " AND  ID  = " + estado.getId(); 
		}
		if(estado.getNome()!=null && !estado.getNome().isEmpty()){
			sql = sql + "  AND NOME = '" + estado.getNome() + "'";
		}
		if(estado.getSigla()!=null && !estado.getSigla().isEmpty()){
			sql = sql + " AND SIGLA = '" + estado.getSigla() + "'";
		}
		
		sql = " SELECT * FROM ESTADOS " + sql;
		
		Estados e =null;
		
		try{
			
			Statement st = (Statement) DataBase.getConexaoMySQL().createStatement();
			ResultSet rs = st.executeQuery(sql);
			DataBase.FecharConexao();
	
			while(rs.next()){
				e = new Estados();
				e.setId(rs.getInt("id"));
				e.setNome(rs.getString("nome"));
				e.setSigla(rs.getString("sigla"));
				Mensagens m = new Mensagens();
				m.setCod(Constantes.COD_OK);
				m.setMensagem(Constantes.MESSAGE_OK);
				e.setMensagens(m);
				//chamar metodo que lista todas as cidades
				//e.setCidades(cidades);
				List<Cidade> lstCidade = new ArrayList<>();
				Cidade c = new Cidade();
				c.setEstados(e);
				lstCidade = getCidadeByParam(c);
				e.setCidades(lstCidade);
				
				lstEstados.add(e);
			}
			
			
		}catch(SQLException ex){
			e = new Estados();
			Mensagens m = new Mensagens();
			m.setCod(ex.getErrorCode());
			m.setMensagem(ex.getMessage());
			e.setMensagens(m);
			//chamar metodo que lista todas as cidades
			//e.setCidades(cidades);
			lstEstados.add(e);
		}
		
		return lstEstados;
		
	}
	
	public static List<Cidade> getCidadeByParam(Cidade cidade){
		
		List<Cidade> lstCidade = new ArrayList<>();
		
		String sql = " WHERE 1=1 ";
		
		if(cidade.getId()!=0){
			sql =sql + " AND  ID  = " + cidade.getId(); 
		}
		if(cidade.getNome()!=null && !cidade.getNome().isEmpty()){
			sql = sql + "  AND NOME = '" + cidade.getNome() + "'";
		}
		if(cidade.getEstados().getId()!=0){
			sql = sql + " AND ESTADOS_ID  = " + cidade.getEstados().getId();
		}
		
		sql = " SELECT * FROM CIDADES " + sql;
		
		Cidade c =null;
		
		try{
			
			Statement st = (Statement) DataBase.getConexaoMySQL().createStatement();
			ResultSet rs = st.executeQuery(sql);
			DataBase.FecharConexao();
	
			while(rs.next()){
				c = new Cidade();
				c.setId(rs.getInt("ID"));
				c.setNome(rs.getString("NOME"));
				Mensagens m = new Mensagens();
				m.setCod(Constantes.COD_OK);
				m.setMensagem(Constantes.MESSAGE_OK);
				c.setMensagens(m);
				//get estado
				List<Estados> le = new ArrayList<>();
				le = getEstadoByParam(c.getEstados());
				c.setEstados(le.get(0));
				
				lstCidade.add(c);
			}
			
			
		}catch(SQLException ex){
			c = new Cidade();
			Mensagens m = new Mensagens();
			m.setCod(ex.getErrorCode());
			m.setMensagem(ex.getMessage());
			c.setMensagens(m);
			//chamar metodo que lista todas as cidades
			//e.setCidades(cidades);
			lstCidade.add(c);
		}
		
		return lstCidade;
		
	}
//	public static User updateUser(User user) {
//
//		String sql = " UPDATE  USER SET  COUNTRY_COD = '" + user.getCountry_cod() + "', EMAIL = '" + user.getEmail()
//				+ "', IMAGE_PATH = '" + user.getImage_path() + "', " + " NAME = '" + user.getName() + "', PHONE = '"
//				+ user.getPhone() + "' WHERE _ID = " + user.getId() ;
//
//		try {
//			Statement st = (Statement) DataBase.getConexaoMySQL().createStatement();
//			st.executeUpdate(sql);
//			DataBase.FecharConexao();
//
//			// retorna o usuario cadastrado
//			User u = new User();
//			List<User> lu = getUserByParam(user);
//			if (lu.size() > 0) {
//				u = lu.get(0);
//			}
//
//			return u;
//
//		} catch (SQLException e) {
//			User u = new User();
//			Mensagens m = new Mensagens();
//			m.setCod(e.getErrorCode());
//			m.setMensagem("Error!" + " -- " + e.getMessage());
//			u.setMensagem(m);
//			return u;
//		}
//
//	}

	private static String mountSql(User u) {

		String sql = " WHERE 1=1 ";

		if (u.get_id() != 0) {
			sql = sql + " AND _ID = " + u.get_id();
		}
		if (u.getEmail() != null && !u.getEmail().isEmpty()) {
			sql = sql + " AND EMAIL = '" + u.getEmail() + "'";
		}
		if (u.getTelefone() != null && !u.getTelefone().isEmpty()) {
			sql = sql + " AND TELEFONE = '" + u.getTelefone() + "'";
		}
		if (u.getNome() != null && !u.getNome().isEmpty()) {
			sql = sql + " AND NOME =  '" + u.getNome() + "'";
		}
		if (u.getSenha() != null && !u.getSenha().isEmpty()) {
			sql = sql + " AND SENHA =  '" + u.getSenha() + "'";
		}
		if (u.getUsuarioStatus() != 0 ) {
			sql = sql + " AND USUARIO_STATUS =  " + u.getUsuarioStatus() ;
		}
		if(u.getCidade().getId()!=0){
			sql = sql + " AND CIDADE =  " + u.getCidade().getId();
		}
		if(u.getUf().getId()!=0){
			sql = sql + " AND UF = " + u.getUf().getId();
		}
		return sql;
	}

}