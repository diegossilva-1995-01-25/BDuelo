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
import org.bduelo.model.Time;
import org.bduelo.model.Torneio;
import org.bduelo.persistence.ClassificacaoDao;
import org.bduelo.persistence.DaoGenerica;
import org.bduelo.util.TarefasComuns;

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
      private Classificacao[] listaAtual;
      private int[] listaColocacao;
	private DaoGenerica<Classificacao, Integer> classificacaoDao;
	
	@Produces
	@Named("classificacaoList")
	private List<Classificacao> listaClassificacao;
	
      @Inject private List<Time> listaTimes;
      @Inject private List<Torneio> listaTorneio;
	
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
		
            for(int i = 0; i < listaAtual.length; i++) {
                classificacaoDao.insert( listaAtual[i] );
            }
            
            TarefasComuns.mensagem( "Resultado do torneio "
                   + classificacaoAtual.getTorneio().getData()
                   + classificacaoAtual.getTorneio().getJogo()
                   + " adicionado com sucesso!");
            
		classificacaoAtual = new Classificacao();
            
            listaAtual = new Classificacao[0];
            listaColocacao = new int[0];
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#editar(java.lang.Object)
	 */
	@Override
	public void editar(Classificacao classificacao) {
			
            int indice = 0;
            
            for (Classificacao c : listaClassificacao)               
                if (c.getTorneio().equals( classificacao.getTorneio() )) indice++;
            
            
            listaColocacao = new int[indice];
            
            for (int j = 0; j < indice; j++)       
                listaColocacao[j] = j+1;  
            
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
            listaColocacao = new int[0];
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
		
		if (classificacaoAtual.getPosicao() > 0) {
                
                listaClassificacao = classificacaoDao.selectMultiplos(classificacaoAtual);
            } 
            else {
                listaClassificacao = classificacaoDao.selectListaLimitada();
            }
		classificacaoAtual = new Classificacao();
	}

      
      /* (non-Javadoc)
	 * @see org.bduelo.controller.ControllerGenerico#procurar()
	 */
	public void setNumeroDeTimes(int indice) {
		
            System.out.println( indice );
            System.out.println( classificacaoAtual.getTorneio() == null );
          
		listaAtual = new Classificacao[indice];
            listaColocacao = new int[indice];
            
            for (int i = 0; i < listaAtual.length; i++)  {
                
                listaColocacao[i] = i+1;
                listaAtual[i] = new Classificacao();
                listaAtual[i].setTorneio( classificacaoAtual.getTorneio() );
            }
            
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
      
      public List<Time> getListaTimes() {
            return listaTimes;
      }
      public void setListaTimes(List<Time> listaTimes) {
            this.listaTimes = listaTimes;
      }

    
      public List<Torneio> getListaTorneio() {
            return listaTorneio;
      }
      public void setListaTorneio(List<Torneio> listaTorneio) {
            this.listaTorneio = listaTorneio;
      }

      
      public int[] getListaColocacao() {
            return listaColocacao;
      }
      public void setListaColocacao(int[] listaColocacao) {
            this.listaColocacao = listaColocacao;
      }

      
      public Classificacao[] getListaAtual() {
            return listaAtual;
      }
      public void setListaAtual(Classificacao[] listaAtual) {
            this.listaAtual = listaAtual;
      }
      
}
