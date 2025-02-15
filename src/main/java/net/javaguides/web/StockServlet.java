package net.javaguides.ManagStock.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.ManagStock.dao.ProduitDAO;
import net.javaguides.ManagStock.model.Produit;

/**
 * Servlet implementation class produitServlets
 */
@WebServlet("/")
public class stockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProduitDAO produitDAO;
	public void init() {
		produitDAO = new ProduitDAO();
	}
	private ProduitDAO produitDao = new ProduitDAO();
	 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stockServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			switch(action) {
			case "/new" :
				showNewForm(request,response);
				break;
			case "/insert" :
				insertProduit(request,response);
				break;
			case "/list" : 
				listProduit(request,response);
				break;
			default : 
				listProduit(request,response);
				break;
			}
		}catch(SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listProduit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<> ListProduit = produitDAO.selectAllProduits();
		request.setAttribute("listProduit", ListProduit);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("Ajout.jsp");
	     dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("Affiche.jsp");
        dispatcher.forward(request, response);
}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Produit existingProduit = produitDAO.selectProduit(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Affiche.jsp");
		request.setAttribute("produit", existingProduit);
	    dispatcher.forward(request, response);
	}
		
	

	private void insertProduit(HttpServletRequest request, HttpServletResponse response) throws  SQLException, IOException{
		
		// TODO Auto-generated method stub
		String nom = request.getParameter("nom");
		String descreption = request.getParameter("descreption");
		String categorie = request.getParameter("categorie") ;
		float prix = Float.parseFloat(request.getParameter("prix"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		Produit newProduit= new Produit(nom,descreption,quantite,prix,categorie);
		produitDao.insertProduit(newProduit);
		response.sendRedirect("./");
	}
}





