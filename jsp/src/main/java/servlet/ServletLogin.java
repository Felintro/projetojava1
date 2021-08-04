package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;


/* Controller */
@WebServlet("/ServletLogin") /* Mapeamento da URL do servlet que vem da tela */
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ServletLogin() {
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/* Recebe os dados pela URL em parâmetros */
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/* Recebe os dados enviados por um formulário */
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);

		} else {
			
			RequestDispatcher redirecionador = request.getRequestDispatcher("index.jsp");
			request.setAttribute("teste", "Informe login e senha corretamente!");
			redirecionador.forward(request, response);
			
		}
		
		
	}

}
