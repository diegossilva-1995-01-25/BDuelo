/**
 * 
 */
package org.bduelo.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bduelo.model.Torneio;
import org.bduelo.persistence.DaoGenerica;
import org.bduelo.persistence.TorneioDao;

/**
 * @author Caio
 *
 */
@ViewScoped
@Named(value="torneioController")
public class TorneioController implements Serializable, ControllerGenerico<Torneio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3585922980113566721L;

	
	/*
	 * 
	 */
	@Inject private Torneio torneioAtual;
	private DaoGenerica<Torneio, Integer> torneioDao;
	
	@Produces
	@Named("torneioList")
	private List<Torneio> listaTorneio;
	
	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#init()
	 */
	@Override
      @PostConstruct
	public void init() {
		
		torneioDao = new TorneioDao();
		listaTorneio = torneioDao.selectListaLimitada();
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#adicionar()
	 */
	@Override
	public void adicionar() {
		
		torneioDao.insert( torneioAtual );
		torneioAtual = new Torneio();
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#editar(java.lang.Object)
	 */
	@Override
	public void editar(Torneio torneio) {
		
		torneioAtual = torneio;
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#atualizar()
	 */
	@Override
	public void atualizar() {
		
		torneioDao.update( torneioAtual );
		listaTorneio = torneioDao.selectListaLimitada();
		
		torneioAtual = new Torneio();
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#deletar(java.lang.Object)
	 */
	@Override
	public void deletar(Torneio torneio) {
		
		torneioDao.delete( torneio.getIdTorneio() );
		listaTorneio.remove( torneio );
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#procurar()
	 */
	@Override
	public void procurar() {
		
		listaTorneio = torneioDao.selectMultiplos( torneioAtual );
		torneioAtual = new Torneio();
	}


	/*
	 * 
	 */
	public Torneio getTorneioAtual() {
		return torneioAtual;
	}
	public void setTorneioAtual(Torneio torneioAtual) {
		this.torneioAtual = torneioAtual;
	}


	public List<Torneio> getListaTorneio() {
		return listaTorneio;
	}
	public void setListaTorneio(List<Torneio> listaTorneio) {
		this.listaTorneio = listaTorneio;
	}

}
