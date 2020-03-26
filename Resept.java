package oblig4;

/**
 * Abstrakt klasse for Resept-objekter.
 * @author ronni
 *
 */
public abstract class Resept {
	protected Pasient IDPasient;
	
	protected static int IDResept = -1; 
	protected int objektID;
	
	protected Legemiddel reseptMiddel;
	protected Lege reseptLege;
	protected int reseptReit;
	protected String farge;
	
	/**
	 * Eneste konstruktør for Resept.
	 * @param legemiddel et Legemiddel-objekt.
	 * @param utskrivendeLege et Lege-objekt.
	 * @param pasientID en pasients ID som integer.
	 */
	public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
		reseptMiddel = legemiddel;
		reseptLege = utskrivendeLege;
		IDPasient = pasient;
		
		IDResept++;
		objektID = IDResept;
	}
	
	/**
	 * Henter reseptens unike ID.
	 * @return returnerer resept ID-en som integer.
	 */
	public int hentID() {
		return IDResept;
	}
	
	/**
	 * Henter legemiddelet som konstruktøren referer til.
	 * @return et Legemiddel-objekt "reseptMiddel".
	 */
	public Legemiddel hentLegemiddel() {
		return reseptMiddel;
	}
	
	/**
	 * Henter legen som konstruktøren referer til.
	 * @return et Lege-objekt "reseptLege".
	 */
	public Lege hentLege() {
		return reseptLege;
	}
	
	/**
	 * Henter pasientens ID.
	 * @return pasientens ID som en integer.
	 */
	public Pasient hentPasientID() {
		return IDPasient;
	}
	
	/**
	 * Henter gjenstående reit på resepten.
	 * @return gjenstående reit som en integer.
	 */
	public int hentReit() {
		return reseptReit;
	}
	
	/**
	 * Bruker "en" reit ved kall. Referer til "reseptReit".
	 * @return true hvis den blir brukt, false hvis oppbrukt.
	 */
	public boolean bruk() {
		if(reseptReit == 0) { return false; }
		else { 
			reseptReit--;
			return true; 
		}
	}
	
	/**
	 * Abstrakt metode som defineres i subklassene.
	 * @return fargen til resepten som String.
	 */
	abstract public String farge();
	
	/**
	 * Abstrakt metode som defineres i subklassene.
	 * @return prisen til resepten som double.
	 */
	abstract public double prisAaBetale();
	
	@Override
	public String toString() {
		String alt = reseptMiddel.hentID() + "," + reseptLege.hentNavn() + "," + IDPasient.hentID();
		return alt;
	}

}
