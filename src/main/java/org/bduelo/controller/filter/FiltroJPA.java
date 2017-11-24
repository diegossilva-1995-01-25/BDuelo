package org.bduelo.controller.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Caio
 * @version 1.0
 * 
 * Esse filtro ir� manusear o EntityManager toda vez que � feito um request para o Faces Servlet
 */
@WebFilter(servletNames = { "Faces Servlet" })
public class FiltroJPA implements Filter {
    
    private EntityManagerFactory factory;
    private final String PERSISTENCE_UNIT = "bduelo";
    
    public FiltroJPA() {}    

    /**
     *
     * @param request O request sendo processado
     * @param response A response sendo criada
     * @param chain Filtro a ser processado
     *
     * @exception IOException Caso erro de E/S ocorra
     * @exception ServletException Caso ocorra um erro de servlet
     * 
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
                                                                throws IOException, ServletException {
        
        /* Criando Entity Manager */
        EntityManager manager = this.factory.createEntityManager();
        
        /* Adicionando-o ao request */
        request.setAttribute("entityManager", manager);
        manager.getTransaction().begin();
        
        /* Iniciando o faces servlet */
        chain.doFilter(request, response);
        
        try {
        	
        	/* Tenta dar commit */
            manager.getTransaction().commit();
        }
        catch (Exception e) {
            
        	/* Em caso de erro execute rollback */
            e.printStackTrace();
            manager.getTransaction().rollback();	
        }
        finally {
            
        	/* Por fim fecha a conex�o */
            manager.close();
        }
    }

    /**
     * Metodo de destrui��o do filtro. Quando o filtro � destruido o EntityManagerFactory � fechado.
     * 
     */
    public void destroy() {
        this.factory.close();
    }

    /**
     * Metodo de inicializa��o
     * 
     * Cria uma instancia do EntityManagerFactory
     */
    public void init(FilterConfig filterConfig) {        

        this.factory = Persistence.createEntityManagerFactory(this.PERSISTENCE_UNIT);
    }  
}
