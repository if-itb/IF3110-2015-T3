package com.wbd.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token{
	public String access_token;
	public int lifetime;
        public String errMessage = "";
}