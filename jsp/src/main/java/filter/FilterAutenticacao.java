package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/* Intercepta todas as requisições oriundas do projeto ou mapeamento */
@WebFilter(urlPatterns = {"/principal/*"}) /*  */
public class FilterAutenticacao implements Filter {

    public FilterAutenticacao() {
        
    }

	/* Encerra os processos quando o servidor é parado */
    // Por exemplo, encerrar os processos de conexão com o banco
	public void destroy() {
		
	}
 
	/* Intercepta todas as requisições e dá as respostas no sistema */
	// Tudo que fizer no sistema passa por aqui!
	// Validação de autenticaçao
	// Dar commit e rollback de transações do banco
	// Validar e fazer redirecionamento de páginas
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		
		
		
		chain.doFilter(request, response);
	}

	/* Iniciar os processos ou recursos quando o servidor sobe o projeto */
	// por exemplo iniciar a conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}