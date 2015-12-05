package org.tusiri.ws.token_validity;

public class TokenValidity {
	private int isValid;
	private int idUser;
	
	public int getIsValid(){
		return isValid;	
	}
	
	public void setIsValid(int isValid){
		this.isValid = isValid;
	}
	
	public int getIdUser(){
		return idUser;
	}
	
	public void setIdUser(int idUser){
		this.idUser = idUser;
	}
}
