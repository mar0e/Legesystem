package oblig4;

public class Pasient {
	protected Stabel<Resept> pasientResepter = new Stabel<Resept>();
	String pasientNavn;
	String pasientFoedsel;
	protected int pasientID;
	protected static int teller = -1;
	protected int resAntall;
	protected int antallNarkotisk;
	
	public Pasient(String navn, String foedselsnr) {
		pasientNavn = navn;
		pasientFoedsel = foedselsnr;
		teller++;
		pasientID = teller;
	}
	
	public int hentID() {
		return pasientID;
	}
	
	public String hentNavn() {
		return pasientNavn;
	}
	
	public int hentAntallNarkotisk() {
		return antallNarkotisk;
	}
	
	public void leggInn(Resept resept) {
		pasientResepter.leggPaa(resept);
		resAntall++;
		if (resept.hentLegemiddel() instanceof Narkotisk && resept.hentLege() instanceof Spesialist) {
			antallNarkotisk++;
		}
	}
	
	public int antRes() {
		return resAntall;
	}
	
	public void taUt() {
		pasientResepter.taAv();
	}
	
	public Stabel<Resept> hentResepter(){
		return pasientResepter;
	}
	
	public String toString() {
		String alt = pasientNavn + "," + pasientFoedsel;
		return alt;
	}
}
