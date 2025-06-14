package com.riego;

import java.util.List;

public class RegistradorDeObservadores {
	private List<Evaluador> evaluadores;
	
	public RegistradorDeObservadores (List<Evaluador> evaluadores) {
		this.evaluadores = evaluadores;
	}
	
	public void agregarObservador(Observer o) {
       for (Evaluador e : this.evaluadores) {
        	e.agregarObservador(o);
        }
	}
}
