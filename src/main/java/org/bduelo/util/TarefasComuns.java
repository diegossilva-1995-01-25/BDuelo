package org.bduelo.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Caio
 * 
 * Classe que realiza a execução de tarefas comuns
 */
public class TarefasComuns {
    
	
	/**
	 * Metodo para pegar a instancia do EntityManager criada pelo filtro do JPA
	 * 
	 * @return - Retorna uma instancia do Entity Manager
	 */
    public static EntityManager getInstanciaEntityManager() {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request  = (HttpServletRequest)externalContext.getRequest();
 
        return (EntityManager)request.getAttribute("entityManager");
    }
    
    
    /**
     * 
     * Metodo para exibição de mensagens comuns
     * 
     * @param mensagem - Mensagem a ser exibida
     */
    public static void mensagem(String mensagem){
	
        FacesContext facesContext = FacesContext.getCurrentInstance();
	
        facesContext.addMessage(null, new FacesMessage("Atenção: ",mensagem));
    }
	
    
    /**
     * 
     * Metodo para exibição de mensagens de erro
     * 
     * @param mensagem - Mensagem a ser exibida
     */
    public static void mensagemErro(String mensagem){
	
        FacesContext facesContext = FacesContext.getCurrentInstance();
	
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro:", mensagem));
    }
	 
    /**
     * 
     * Metodo para exibição de mensagens informativas
     * 
     * @param mensagem - Mensagem a ser exibida
     */
    public static void mensagemInformativa(String mensagem){
	
        FacesContext facesContext = FacesContext.getCurrentInstance();
	
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
    }
}