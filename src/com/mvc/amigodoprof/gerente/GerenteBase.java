package com.mvc.amigodoprof.gerente;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GerenteBase {

	private static String usuario;
	
	private static char[] senha;
	
	
	private static EntityManagerFactory fabrica_gerente;
	private static EntityManager gerente;
	
	
	public static void setarUserSenha(String usuario, char[] senha) {
		GerenteBase.usuario = usuario;
		GerenteBase.senha = senha;
	}
	
	public static EntityManager getGerente() {

		
		fabrica_gerente = 
				Persistence.createEntityManagerFactory("AmigoDoProf", sobrescreverSenhaNoXML(usuario,senha));
		
		gerente = fabrica_gerente.createEntityManager();
		
		
		return gerente;
	}
	
	private static Map sobrescreverSenhaNoXML(String user, char[] senha) {
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		System.out.println(user);
		
		map.put("hibernate.connection.username", user);
		map.put("hibernate.connection.password", String.valueOf(senha));
		
		return map;
		
	}
	

}

