package oblig4;

/**
 * Sub-klasse av Legemiddel for narkotiske legemidler.
 * @author ronni
 *
 */
public class Narkotisk extends Legemiddel{
	protected double styrke;
	
	/**
	 * Eneste konstrukt�r for Narkotisk.
	 * @param navn String-navn p� legemiddelet.
	 * @param pris prisen p� legemiddelet som double.
	 * @param mg antall mg legemiddelet innehar som double.
	 * @param narkStyrke hvilken grad av narkotisk styrke legemiddelet har, som integer.
	 */
	public Narkotisk(String navn, double pris, double mg, double narkStyrke) {
		super(navn, pris, mg);
		styrke = narkStyrke;
	}
	
	/**
	 * Henter graden av narkotisk styrke p� legemiddelet.
	 * @return styrken som en integer.
	 */
	public double hentNarkotiskStyrke() {
		return styrke;
	}
	
	@Override
	public String toString() {
		String tekst = navn + "," + "narkotisk" + "," + pris + "," + total + "," + styrke;
		return tekst;
	}
	
}
