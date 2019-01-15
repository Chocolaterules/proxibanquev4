package fr.formation.proxi4.presentation.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Classe permettant de filtrer les requetes HTTP entrantes
 * 
 * @author Adminl
 *
 */
public class ForceEncodingFilter implements Filter {

	private static final String CHARSET = "UTF-8";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * Methode permettant de forcer l'encodage de toutes les requetes entrantes au
	 * format UTF-8.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(ForceEncodingFilter.CHARSET);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
