package oblig4;

/**
 * Sub-klasse av Legemiddel for narkotiske legemidler.
 * @author ronni
 *
 */
public class Narkotisk extends Legemiddel{
	protected double styrke;
	
	/**
	 * Eneste konstruktør for Narkotisk.
	 * @param navn String-navn på legemiddelet.
	 * @param pris prisen på legemiddelet som double.
	 * @param mg antall mg legemiddelet innehar som double.
	 * @param narkStyrke hvilken grad av narkotisk styrke legemiddelet har, som integer.
	 */
	public Narkotisk(String navn, double pris, double mg, double narkStyrke) {
		super(navn, pris, mg);
		styrke = narkStyrke;
	}
	
	/**
	 * Henter graden av narkotisk styrke på legemiddelet.
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
