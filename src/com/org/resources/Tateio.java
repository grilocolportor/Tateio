package com.org.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.avs.bean.Cidade;
import org.avs.bean.User;
import org.avs.control.UserControl;

import com.google.gson.Gson;


@Path("/ws")
public class Tateio {

	@Path("/start")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response start(){
		
		
		//criar um objeto qualquer
		User user = new User();
		user.set_id(1);
		Cidade cidade = new Cidade();
		cidade.setNome("Olinda");
		user.setCidade(cidade);
		user.setEmail("teste@teste.com");
		user.setNome("TESTE ");
		user.setSenha("1234567");
		user.setTelefone("81123456789");
		
		
		//tranfosrmar o objeto em JSON
		Gson gson = new Gson();
		String json = gson.toJson(user);
		
		///retornar o objeto
		return Response.status(200).entity(json)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
		
	@POST
	@Path("/adduser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public User addUser(User user){
		return UserControl.addUser(user);
	}
	
}
