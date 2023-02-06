package model;

public class Student {
	
	private int id;
	private String ime, prezime;
	private double prosek;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String ime, String prezime, double prosek) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.prosek = prosek;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public double getProsek() {
		return prosek;
	}

	public void setProsek(double prosek) {
		this.prosek = prosek;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", prosek=" + prosek + "]\n";
	}

	

}
