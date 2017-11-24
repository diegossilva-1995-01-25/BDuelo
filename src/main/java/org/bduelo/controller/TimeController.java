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

import org.bduelo.model.Time;
import org.bduelo.persistence.DaoGenerica;
import org.bduelo.persistence.TimeDao;
import org.bduelo.util.TarefasComuns;

/**
 * @author Caio
 *
 */
@ViewScoped
@Named(value="timeController")
public class TimeController implements Serializable, ControllerGenerico<Time> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 811430188192040981L;

	
	/*
	 * 
	 */
	@Inject private Time timeAtual;
	private DaoGenerica<Time, Integer> timeDao;
	
	@Produces
	@Named("timeList")
	private List<Time> listaTime;
	
	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#init()
	 */
	@Override
      @PostConstruct
	public void init() {
		
		timeDao = new TimeDao();
		listaTime = timeDao.selectListaLimitada();
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#adicionar()
	 */
	@Override
	public void adicionar() {
          
		timeDao.insert( timeAtual );
            TarefasComuns.mensagem( timeAtual.getNome() + " adicionado com sucesso!");
		timeAtual = new Time();
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#editar(java.lang.Object)
	 */
	@Override
	public void editar(Time time) {
		
		timeAtual = time;
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#atualizar()
	 */
	@Override
	public void atualizar() {
		
		timeDao.update( timeAtual );
		listaTime = timeDao.selectListaLimitada();
		
		timeAtual = new Time();
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#deletar(java.lang.Object)
	 */
	@Override
	public void deletar(Time time) {
		
		timeDao.delete( time.getIdTime() );
		listaTime.remove( time );
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#procurar()
	 */
	@Override
	public void procurar() {
		
		listaTime = timeDao.selectMultiplos( timeAtual );
		timeAtual = new Time();
	}


	/*
	 * 
	 */
	public Time getTimeAtual() {
		return timeAtual;
	}
	public void setTimeAtual(Time timeAtual) {
		this.timeAtual = timeAtual;
	}


	public List<Time> getListaTime() {
		return listaTime;
	}
	public void setListaTime(List<Time> listaTime) {
		this.listaTime = listaTime;
	}

}
