package MANAGSTOCK.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MANAGSTOCK.model.Produit;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class ProduitDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/GestionStock";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String SELECT_PRODUIT_BY_ID = "select id,nom,description,quantite,prix,categorie from stock where id =?";
	private static final String SELECT_ALL_PRODUITS = "select * from stock";
	private static final String INSERT_PRODUITS_SQL = "INSERT INTO stock"
			+ "  (nom, description, quantite, prix, categorie) VALUES " + " (?, ?, ?, ?, ?);";
	private static final String UPDATE_PRODUITS_SQL =  "update stock set nom = ?,description= ?, quantite =?, prix =?, categorie =? where id = ?;";
	private static final String DELETE_PRODUITS_SQL = "delete from stock where id = ?;";

	public ProduitDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertProduit(Produit produit) throws SQLException {
		System.out.println(INSERT_PRODUITS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUITS_SQL)) {
			preparedStatement.setString(1, produit.getNom());
			preparedStatement.setString(2, produit.getDescription());
			preparedStatement.setInt(3, produit.getQuantite());
			preparedStatement.setDouble(4, produit.getPrix());
			preparedStatement.setString(5, produit.getCategorie());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Produit selectProduit(int id) {
		Produit produit = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUIT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nom = rs.getString("nom");
				String description = rs.getString("description");
				int quantite = rs.getInt("quantite");
				double prix = rs.getDouble("prix");
				String categorie = rs.getString("categorie");
				produit = new Produit(id, nom, description, quantite, prix, categorie);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return produit;
	}

	public List<Produit> selectAllProduits() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Produit> produits = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUITS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String description = rs.getString("description");
				int quantite = rs.getInt("quantite");
				float prix = rs.getFloat("prix");
				String categorie = rs.getString("categorie");
				produits.add(new Produit(id, nom, description, quantite, prix, categorie));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return produits;
	}

	public boolean deleteProduit(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PRODUITS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateProduit(Produit produit) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUITS_SQL);) {
			statement.setString(1, produit.getNom());
			statement.setString(2, produit.getDescription());
			statement.setInt(3, produit.getId());
			statement.setInt(4, produit.getQuantite());
			statement.setFloat(5, (float) produit.getPrix());
			statement.setString(6, produit.getCategorie());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}