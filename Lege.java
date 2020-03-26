package oblig4;

/**
 * Klasse for leger.
 * @author ronni
 *
 */

public class Lege implements Comparable<Lege>{
	protected String legeNavn;
	protected int antallNarkotisk;
	protected Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();
	
	
	/**
	 * Eneste konstruktør for Lege.
	 * @param navn String-navn på legen.
	 */
	public Lege(String navn) {
		legeNavn = navn;
	}
	
	/**
	 * Hent navnet på legen.
	 * @return navnet på legen som String.
	 */
	public String hentNavn() {
		return legeNavn;
	}
	
	public int hentAntallNarkotisk() {
		return antallNarkotisk;
	}
	
	@Override
	public String toString() {
		String alt = hentNavn() + "," + 0;
		return alt;
	}
	
	public Lenkeliste<Resept> hentListe(){
		return utskrevedeResepter;
	}
	
	public int compareTo(Lege lege) {
		return legeNavn.compareTo(lege.hentNavn());
	}
	
	public HvitResept skrivHvitResept(Legemiddel lm, int reit, Pasient pasient) throws UlovligUtskrift{
		HvitResept hNy = new HvitResept(lm, this, pasient, reit);
		
		if (lm instanceof Narkotisk && !(this instanceof Spesialist)) {
			throw new UlovligUtskrift(this, lm);
		}

		utskrevedeResepter.leggTil(hNy);
		if (lm instanceof Narkotisk && this instanceof Spesialist) { antallNarkotisk++; }
		return hNy;

	}
	
	public MilitaerResept skrivMilitaerResept(Legemiddel lm, int reit, Pasient pasient) throws UlovligUtskrift{
		MilitaerResept mNy = new MilitaerResept(lm, this, pasient, reit);
		
		if (lm instanceof Narkotisk && !(this instanceof Spesialist)) {
			throw new UlovligUtskrift(this, lm);
		}
		
		utskrevedeResepter.leggTil(mNy);
		if (lm instanceof Narkotisk && this instanceof Spesialist) { antallNarkotisk++; }
		return mNy;
		
	}
	
	public PrevensjonResept skrivPrevensjonResept(Legemiddel lm, Pasient pasient) throws UlovligUtskrift{
		PrevensjonResept pNy = new PrevensjonResept(lm, this, pasient);
		
		if (lm instanceof Narkotisk && !(this instanceof Spesialist)) {
			throw new UlovligUtskrift(this, lm);
		}
		
		utskrevedeResepter.leggTil(pNy);
		if (lm instanceof Narkotisk && this instanceof Spesialist) { antallNarkotisk++; }
		return pNy;
		
	}
	
	public BlaaResept skrivBlaaResept(Legemiddel lm, int reit, Pasient pasient) throws UlovligUtskrift{
		BlaaResept bNy = new BlaaResept(lm, this, pasient, reit);
		
		if (lm instanceof Narkotisk && !(this instanceof Spesialist)) {
			throw new UlovligUtskrift(this, lm);
		}
		
		utskrevedeResepter.leggTil(bNy);
		if (lm instanceof Narkotisk && this instanceof Spesialist) { antallNarkotisk++; }
		return bNy;
		
	}
}
