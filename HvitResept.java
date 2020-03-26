package oblig4;

/**
 * Sub-klasse av Resept for hvite resepter.
 * @author ronni
 *
 */
public class HvitResept extends Resept{
	
	/**
	 * Eneste konstruktør for HvitResept.
	 * @param legemiddel et Legemiddel-objekt.
	 * @param utskrivendeLege et Lege-objekt.
	 * @param pasientID en pasients ID som integer.
	 */
	public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
		super(legemiddel, utskrivendeLege, pasient);
		farge = "hvit";
		reseptReit = reit;
	}
	
	@Override
	public String farge() {
		// midlertidig return!!!!!
		return farge;
	}
	
	@Override
	public double prisAaBetale() {
		int pris = (int) hentLegemiddel().hentPris();
		return pris;
	}
	
	@Override
	public String toString() {
		String alt = super.toString();
		alt += "," + "hvit" + "," + reseptReit;
		return alt;
	}
	
}
