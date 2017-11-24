package org.bduelo.controller;


/**
 * 
 * @author Caio
 *
 */
public interface ControllerGenerico <T> {

	void init();
	void adicionar();
    void editar(T object);
    void atualizar();
    void deletar(T object);
    void procurar();
    
}
