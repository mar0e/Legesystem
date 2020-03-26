package oblig4;

/**
 * Sub-klasse til Legemiddel for vanedannede legemidler.
 * @author ronni
 *
 */
public class Vanedannede extends Legemiddel{
	protected double styrke;
	
	/**
	 * Eneste konstruktør for Vanedannede.
	 * @param navn String-navn på legemiddelet.
	 * @param pris prisen på legemiddelet som double.
	 * @param mg antall mg legemiddelet innehar som double.
	 * @param vaneStyrke hvilken grad av vanedannede styrke legemiddelet har, som integer.
	 */
	public Vanedannede(String navn, double pris, double mg, double vaneStyrke) {
		super(navn, pris, mg);
		styrke = vaneStyrke;
	}
	
	/**
	 * Henter graden av vanedannede styrke på legemiddelet.
	 * @return styrken som en integer.
	 */
	public double hentVanedannedeStyrke() {
		return styrke;
	}
	
	@Override
	public String toString() {
		String tekst = navn + "," + "vanedannende" + "," + pris + "," + total + "," + styrke;
		return tekst;
	}
}