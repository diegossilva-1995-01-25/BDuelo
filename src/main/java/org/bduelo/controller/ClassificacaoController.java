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

import org.bduelo.model.Classificacao;
import org.bduelo.persistence.ClassificacaoDao;
import org.bduelo.persistence.DaoGenerica;

/**
 * @author Caio
 *
 */
@ViewScoped
@Named(value="classificacaoController")
public class ClassificacaoController implements Serializable, ControllerGenerico<Classificacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5130909604648069327L;

	
	/*
	 * 
	 */
	@Inject private Classificacao classificacaoAtual;
	private DaoGenerica<Classificacao, Integer> classificacaoDao;
	
	@Produces
	@Named("classificacaoList")
	private List<Classificacao> listaClassificacao;
	
	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#init()
	 */
	@Override
	@PostConstruct
	public void init() {
		
		classificacaoDao = new ClassificacaoDao();
		listaClassificacao = classificacaoDao.selectListaLimitada();
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#adicionar()
	 */
	@Override
	public void adicionar() {
		
		classificacaoDao.insert( classificacaoAtual );
		classificacaoAtual = new Classificacao();
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#editar(java.lang.Object)
	 */
	@Override
	public void editar(Classificacao classificacao) {
		
		classificacaoAtual = classificacao;
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#atualizar()
	 */
	@Override
	public void atualizar() {
		
		classificacaoDao.update( classificacaoAtual );		
		listaClassificacao = classificacaoDao.selectListaLimitada();
		
		classificacaoAtual = new Classificacao();
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#deletar(java.lang.Object)
	 */
	@Override
	public void deletar(Classificacao classificacao) {
		
		classificacaoDao.delete( classificacao.getIdClassificacao() );
		listaClassificacao.remove( classificacao );
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#procurar()
	 */
	@Override
	public void procurar() {
		
		listaClassificacao = classificacaoDao.selectMultiplos(classificacaoAtual);
		classificacaoAtual = new Classificacao();
	}


	/*
	 * 
	 */
	public Classificacao getClassificacaoAtual() {
		return classificacaoAtual;
	}
	public void setClassificacaoAtual(Classificacao classificacaoAtual) {
		this.classificacaoAtual = classificacaoAtual;
	}


	public List<Classificacao> getListaClassificacao() {
		return listaClassificacao;
	}
	public void setListaClassificacao(List<Classificacao> listaClassificacao) {
		this.listaClassificacao = listaClassificacao;
	}

}
