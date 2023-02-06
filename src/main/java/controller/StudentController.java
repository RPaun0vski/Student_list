package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.Student;

// moze da se zove i npt Studenti tj. moze biti mapiran pod drugacijim nazivom nego klasa
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public StudentController() {
		super();
	}
	//ime=Moma&prezime=Momorivoc&prosek=9.5&sifra=123

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action=request.getParameter("action");

		// u slucaju izmene u action-u, vraca nas na Indeks.jsp. Ovime stitimo action
		switch (action) {
		case "Unesi":
			unosStudenta(request, response);
			break;
		case "Prikazi":			
			prikazStudenata(request, response);
			break;
		case "Prikazi filtrirano":			
			prikaziBoljeOdUnetogProseka(request, response);
			break;
		case "DELETE":			
			deleteStudentById(request, response);
			break;
		default:
			response.sendRedirect("Index.jsp");
			break;
		}

	}

	private void deleteStudentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		String id = request.getParameter("id");

		try {
			int i = Integer.parseInt(id);
			//prvo pozivamo metodu za brisanje...
			dao.deleteStudentByID(i);
			request.setAttribute("msg", "Uspesno obrisan student pod rednim brojem: " + id);
			//... a onda metodu za izlistavanje
			request.setAttribute("studenti", dao.selectStudenti());
			request.getRequestDispatcher("Studenti.jsp").forward(request, response);
			//Student.jsp jos uvek ne postoji. Sada se pravi nova view stranica
			//Student.jsp koja je prilagodjena da ispisuje jednog studenta
		}
		catch (Exception e)  {
			request.setAttribute("msg", "id mora biti broj");
			request.getRequestDispatcher("Index.jsp").forward(request, response);
			
		}

		
	}
	private void prikaziBoljeOdUnetogProseka(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		String prosek = request.getParameter("prosek");

		try {
			double p = Double.parseDouble(prosek);
			request.setAttribute("msg", "Uspesan prikaz filtrirane liste sa prosekom boljim od: " + p);
			request.setAttribute("studenti", dao.selectStudentiProsekVeciOd(p));
			request.getRequestDispatcher("Studenti.jsp").forward(request, response);
			//Student.jsp jos uvek ne postoji. Sada se pravi nova view stranica
			//Student.jsp koja je prilagodjena da ispisuje jednog studenta
		}
		catch (Exception e)  {
			request.setAttribute("msg", "Prosek mora biti broj");
			request.getRequestDispatcher("Index.jsp").forward(request, response);
			
		}

	}
	
	private void prikazStudenata (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		DAO dao = new DAO();

		try {
			request.setAttribute("msg", "Uspesan prikaz liste svih studenata ");
			request.setAttribute("studenti", dao.selectStudenti());
			request.getRequestDispatcher("Studenti.jsp").forward(request, response);
			//Student.jsp jos uvek ne postoji. Sada se pravi nova view stranica
			//Student.jsp koja je prilagodjena da ispisuje jednog studenta
		}
		catch (Exception e)  {
			request.setAttribute("msg", "Neuspesno izlistavanje svih studenata");
			request.getRequestDispatcher("Index.jsp").forward(request, response);
			// TODO: handle exception
		}
	}



	// ovakve metode se obicno stave u zasebnu klasu.Obicno se nazivaju StudentServis
	private void unosStudenta (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		String prosek = request.getParameter("prosek");
		String sifra = request.getParameter("sifra");

		if (ime!=null && ime.length()>0 &&
				prezime!=null && prezime.length()>0 &&
				prosek!=null && prosek.length()>0 &&
				sifra!=null && sifra.length()>0) {
			try {
				double p=Double.parseDouble(prosek);

				Student student = new Student(0, ime, prezime, p); 

				DAO dao = new DAO();
				dao.insertStudent(student);


				request.setAttribute("msg", "Uspesan unos");
				request.setAttribute("student", student);
				request.getRequestDispatcher("Student.jsp").forward(request, response);
				//Student.jsp jos uvek ne postoji. Sada se pravi nova view stranica
				//Student.jsp koja je prilagodjena da ispisuje jednog studenta


			} catch (Exception e)  {
				request.setAttribute("message", "Prosek mora biti broj");
				request.getRequestDispatcher("Index.jsp").forward(request, response);
				// TODO: handle exception
			}
		}
		else {
			System.out.println("Morate popuniti sva polja");
			request.setAttribute("message", "Morate popuniti sva polja");
			request.getRequestDispatcher("Index.jsp").forward(request, response);
			//moramo reci gde saljemo ovaj atribut prosledjujuci request i response
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);

		String action=request.getParameter("action");

		switch (action) {
		case "Akcija 1":
			unosStudenta(request, response);
			break;
		case "Akcija 2":			
			break;
		default:
			response.sendRedirect("Index.jsp");
			break;
		}
	}

}
// kontroleri se prave prema broju akcija/entiteta (tipa, studenti, profesori) tj. mozemo imati vise kontrolera

