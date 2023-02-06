// vazni importi 
package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.ArrayList;
public class DAO {
	private DataSource ds;



	// DEFINICIJA KONEKCIONIH STRINGOVA
	private static String SELECTSTUDENTI = "SELECT * FROM studenti";
	private static String INSERTSTUDENT = "INSERT into studenti(ime, prezime, prosek) VALUES (?,?,?)";
	private static String SELECTSTUDENTWHERE = "SELECT * FROM studenti WHERE prosek>?";
	private static String DELETESTUDENT = "DELETE FROM studenti WHERE id=?";


	// DEFINICIJA KONSTRUKTORA ZA PODESAVNJE KONEKCIJE � UVEK ISTO
	public DAO(){
		try {
			InitialContext cxt = new InitialContext();
			if ( cxt == null ) { 
			} 
			ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
			if ( ds == null ) { 
			} 		
		} catch (NamingException e) {
		}
	}
	
	public void deleteStudentByID(int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		Student pom = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETESTUDENT);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1,id);
			
			pstm.executeUpdate();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 

			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				rs.close();
			}

			if (pstm != null) {
				pstm.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Student> selectStudentiProsekVeciOd(double prosek){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Student> lista = new ArrayList<Student>();
		Student pom = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTSTUDENTWHERE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setDouble(1, prosek);
			pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom = new Student();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				pom.setId(rs.getInt("id"));
				pom.setIme(rs.getString("ime"));
				pom.setPrezime(rs.getString("prezime"));
				pom.setProsek(rs.getDouble("prosek"));

				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lista.add(pom);
			}
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				rs.close();
			}

			if (pstm != null) {
				pstm.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lista; 
	}

	// DEFINICIJA METODE 
	public ArrayList<Student> selectStudenti(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Student> lista = new ArrayList<Student>();
		Student pom = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTSTUDENTI);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			//pstm.setString(1, ime);
			pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom = new Student();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				pom.setId(rs.getInt("id"));
				pom.setIme(rs.getString("ime"));
				pom.setPrezime(rs.getString("prezime"));
				pom.setProsek(rs.getDouble("prosek"));

				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lista.add(pom);
			}
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				rs.close();
			}

			if (pstm != null) {
				pstm.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lista; 
	}
	// DEFINICIJA OSTALIH METODA ... 
	public void insertStudent(Student s){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		Student pom = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTSTUDENT);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setString(1, s.getIme());
			pstm.setString(2, s.getPrezime());
			pstm.setDouble(3, s.getProsek());
			pstm.executeUpdate();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 

			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				rs.close();
			}

			if (pstm != null) {
				pstm.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		
	}

}

