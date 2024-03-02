package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.ICosmetiqueDao;
import dao.CosmetiqueDaoImpl;
import metier.entities.Cosmetique;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
	ICosmetiqueDao metier;

	@Override
	public void init() throws ServletException {
		metier = new CosmetiqueDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/index.do")) {
			request.getRequestDispatcher("cosmetiques.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			CosmetiqueModele model = new CosmetiqueModele();
			model.setMotCle(motCle);
			List<Cosmetique> cosms = metier.cosmetiquesParMC(motCle);
			model.setCosmetiques(cosms);
			request.setAttribute("model", model);
			request.getRequestDispatcher("cosmetiques.jsp").forward(request, response);
		} else if (path.equals("/saisie.do")) {
			request.getRequestDispatcher("saisieCosmetique.jsp").forward(request, response);
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Cosmetique c = metier.save(new Cosmetique(nom, prix));
			request.setAttribute("cosmetique",c );
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		} else if (path.equals("/supprimer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteCosmetique(id);
			response.sendRedirect("chercher.do?motCle=");
		} else if (path.equals("/editer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Cosmetique c = metier.getCosmetique(id);
			request.setAttribute("cosmetique", c);
			request.getRequestDispatcher("editerCosmetique.jsp").forward(request, response);
		} else if (path.equals("/update.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Cosmetique c = new Cosmetique();
			c.setIdCosmetique(id);
			c.setNomCosmetique(nom);
			c.setPrix(prix);
			metier.updateCosmetique(c);
			request.setAttribute("cosmetique", c);
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		} else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}