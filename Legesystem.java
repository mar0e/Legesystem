package oblig4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Legesystem {
	
	Lenkeliste<Resept> reseptListe = new Lenkeliste<Resept>();
	
	Lenkeliste<Pasient> pasientListe = new Lenkeliste<Pasient>();
	
	Lenkeliste<Legemiddel> lmListe = new Lenkeliste<Legemiddel>();
	
	SortertLenkeliste<Lege> legeListe = new SortertLenkeliste<Lege>();
	
	protected int totalNarkotisk;
	protected int totalVanedannende;
	
	public void lesFraFil(String filnavn) throws UlovligUtskrift{

		
		File f = new File(filnavn);
		Scanner fscan = null;
		try {
			fscan = new Scanner(f);
		}
		catch (FileNotFoundException e) {
			System.out.println("Fant ikke filen!");
			//return null;
			return;
		}
		
		boolean ferdigMedAlt = false;
		String[] linjer;
		
		while (ferdigMedAlt == false && fscan.hasNextLine()) { // STAMP OF APPROVAL
			boolean ferdigMedPasienter = false;
			boolean ferdigMedLegemidler = false;
			boolean ferdigMedLeger = false;
			boolean ferdigMedResepter = false;
			
			fscan.nextLine();
			String temp = null;
			//System.out.println("Test 1: Første while.");
			
			while (ferdigMedPasienter == false) { // STAMP OF APPROVAL
				//System.out.println("Test 2: Andre while.");
				temp = fscan.nextLine();
				while (!(temp.substring(0, 1).equals("#"))) {
					//System.out.println("Test 3: Tredje while, og at temp-variabel fungerer.");
					// yada yada
					linjer = temp.split(",");
					Pasient nyPas = new Pasient(linjer[0], linjer[1]);
					pasientListe.leggTil(nyPas);
					temp = fscan.nextLine();
				}
				if (temp.substring(0, 1).equals("#")) { ferdigMedPasienter = true; /*System.out.println("Test 4: if-sjekk");*/ }
				//System.out.println("Test 5: Innlegging og slutt på while løkke. Good.");
				//skrivUt();
			}
			
			//System.out.println();
			//System.out.println("Ferdig fram til Legemidler.");
			//System.out.println();

			while (ferdigMedLegemidler == false) { // STAMP OF APPROVAL
				//System.out.println("Test 6: Start på Legemidler.");
				temp = fscan.nextLine();
				while(!(temp.substring(0, 1).equals("#"))) {
					//System.out.println("Test 7: Rytme på nextLine.");
					linjer = temp.split(",");
					
					//for (String s : linjer) { System.out.println("Linjer: " + s); }
					
					String type = linjer[1].toLowerCase();
					
					if (type.equals("narkotisk")) {
						double pris = Double.parseDouble(linjer[2]);
						double mg = Double.parseDouble(linjer[3]);
						double styrke = Double.parseDouble(linjer[4]);
						Narkotisk nyNark = new Narkotisk(linjer[0], pris, mg, styrke);
						lmListe.leggTil(nyNark);
					}
					
					else if (type.equals("vanedannende")) {
						double pris = Double.parseDouble(linjer[2]);
						double mg = Double.parseDouble(linjer[3]);
						double styrke = Double.parseDouble(linjer[4]);
						Vanedannede nyVane = new Vanedannede(linjer[0], pris, mg, styrke);
						lmListe.leggTil(nyVane);
					}

					else if (type.equals("vanlig")) {
						double pris = Double.parseDouble(linjer[2]);
						double mg = Double.parseDouble(linjer[3]);
						Vanlig nyVanlig = new Vanlig(linjer[0], pris, mg);
						lmListe.leggTil(nyVanlig);
					}
					temp = fscan.nextLine();
				}
				//System.out.println("Temp-test: " + temp);
				if (temp.substring(0, 1).equals("#")) { ferdigMedLegemidler = true; }
				//System.out.println("Test 8: Innlegging av Legemidler. Good.");
				//skrivUt();
			}
			//System.out.println();
			//System.out.println("Ferdig fram til Leger.");
			//System.out.println();
			
			while (ferdigMedLeger == false) { // STAMP OF APPROVAL
				//System.out.println("Test 9: Leger.");
				temp = fscan.nextLine();
				while(!(temp.substring(0, 1).equals("#"))) {
					//System.out.println("Test 10: Rytme på nextLine for Leger");
					linjer = temp.split(",");
					// yada yada
					int ID = Integer.parseInt(linjer[1]);
					
					if (ID > 0) {
						Spesialist nySpes = new Spesialist(linjer[0], ID);
						legeListe.leggTil(nySpes);
					}
					
					else {
						Lege nyLege = new Lege(linjer[0]);
						legeListe.leggTil(nyLege);
					}
					temp = fscan.nextLine();
				}
				if (temp.substring(0, 1).equals("#")) { ferdigMedLeger = true; }
				//System.out.println("Test 11: Innlegging av Leger. Good.");
				//skrivUt();
			}
			
			//System.out.println();
			//System.out.println("Ferdig fram til Resepter.");
			//System.out.println();
			
			while (ferdigMedResepter == false) {
				//System.out.println("Test 12: Resepter.");
				if (fscan.hasNextLine()) { temp = fscan.nextLine(); }
				else { // behandle siste linjen
					ferdigMedResepter = true;
				}
				while(temp != null && fscan.hasNextLine()) {
					//System.out.println("Test 13: Rytme på nextLine for Resepter");
					linjer = temp.split(",");
					// yada yada
					int legemiddelID = Integer.parseInt(linjer[0]);
					int pasientID = Integer.parseInt(linjer[2]);
					String type = linjer[3].toLowerCase();
					String navn = linjer[1].toLowerCase();
					//System.out.println("Typen til resepten er: " + type);
					
					if (legemiddelID > lmListe.stoerrelse()) { // sjekker om ID-en er innafor
						//System.out.println("Test 14: Sjekket ID. Out-of-bounds.");
						temp = fscan.nextLine();
					}
					
					else {
						//System.out.println("Test 15: ID er OK.");
						
						Legemiddel resLm = null;
						Lege resLege = null;
						Pasient resPas = null;
						
						for (Legemiddel lm : lmListe) {
							if (lm.hentID() == legemiddelID) { /*System.out.println("Test 16: LM (x)");*/ resLm = lm; }
						}
						
						for (Lege l : legeListe) {
							if (l.hentNavn().toLowerCase().equals(navn)) { 
								//System.out.println("Test 17: Lege funnet."); 
								resLege = l;
							}
						}
						
						if (pasientID > pasientListe.stoerrelse()) {
							//System.out.println("Test 18: Sjekket Pasient. Out-of-bounds.");
						}
						
						else if (resLm != null && resLege != null){ // sjekker at LM og lege ble funnet
							for (Pasient p : pasientListe) {
								//System.out.println("pasientListeID vs pasientID: " + p.hentID() + " vs " + pasientID);
								if (p.hentID() == pasientID) {
									resPas = p;
									//System.out.println("Test 19: Funnet pasient.");
									if (type.equals("p")) {
										try {
											PrevensjonResept prevRes = resLege.skrivPrevensjonResept(resLm, resPas);
											reseptListe.leggTil(prevRes);
											if (resLm instanceof Narkotisk && resLege instanceof Spesialist) { 
												totalNarkotisk++; 
											}
											if (resLm instanceof Vanedannede) { 
												totalVanedannende++; 
											}
											resPas.leggInn(prevRes);
										} catch (UlovligUtskrift u) {}
									}
									
									else {
										int resReit = Integer.parseInt(linjer[4]);
										// alle resepter unntatt P-resepter har reit
										
										if (type.equals("blaa")) { 
											BlaaResept blaaRes = null;
											try { 
												blaaRes = resLege.skrivBlaaResept(resLm, resReit, resPas); 
												reseptListe.leggTil(blaaRes);
												if (resLm instanceof Narkotisk && resLege instanceof Spesialist) { 
													totalNarkotisk++; 
												}
												if (resLm instanceof Vanedannede) { 
													totalVanedannende++; 
												}
												resPas.leggInn(blaaRes);
											} catch (UlovligUtskrift u) {}
										}
										
										if (type.equals("militaer")) {
											try {
												MilitaerResept miliRes = resLege.skrivMilitaerResept(resLm, resReit, resPas);
												reseptListe.leggTil(miliRes);
												if (resLm instanceof Narkotisk && resLege instanceof Spesialist) { 
													totalNarkotisk++; 
												}
												if (resLm instanceof Vanedannede) { 
													totalVanedannende++; 
												}
												resPas.leggInn(miliRes);
											} catch (UlovligUtskrift u) {}
										}
										
										if (type.equals("hvit")) {
											try {
												HvitResept hvitRes = resLege.skrivHvitResept(resLm, resReit, resPas);
												reseptListe.leggTil(hvitRes);
												if (resLm instanceof Narkotisk && resLege instanceof Spesialist) { 
													totalNarkotisk++; 
												}
												if (resLm instanceof Vanedannede) { 
													totalVanedannende++; 
												}
												resPas.leggInn(hvitRes);
											} catch (UlovligUtskrift u) {}
										}
									}
								}
							}
						}
					}
					if (fscan.hasNextLine()) { temp = fscan.nextLine(); }
					if (!fscan.hasNextLine()) { /*System.out.println("HALLO?!");*/
						//System.out.println("Test 20: Rytme på nextLine for Resepter");
						linjer = temp.split(",");
						// yada yada
						legemiddelID = Integer.parseInt(linjer[0]);
						pasientID = Integer.parseInt(linjer[2]);
						type = linjer[3].toLowerCase();
						navn = linjer[1].toLowerCase();
						//System.out.println("Typen til resepten er: " + type);
						
						if (legemiddelID > lmListe.stoerrelse()) { // sjekker om ID-en er innafor
							//System.out.println("Test 21: Sjekket ID. Out-of-bounds.");
							temp = fscan.nextLine();
						}
						
						else {
							//System.out.println("Test 22: ID er OK.");
							
							Legemiddel resLm = null;
							Lege resLege = null;
							Pasient resPas = null;
							
							for (Legemiddel lm : lmListe) {
								if (lm.hentID() == legemiddelID) { /*System.out.println("Test 16: LM funnet.");*/ resLm = lm;}
							}
							
							for (Lege l : legeListe) {
								if (l.hentNavn().toLowerCase().equals(navn)) { 
									//System.out.println("Test 23: Lege funnet."); 
									resLege = l;
								}
							}
							
							if (pasientID > pasientListe.stoerrelse()) {
								//System.out.println("Test 24: Sjekket Pasient. Out-of-bounds.");
							}
							
							else if (resLm != null && resLege != null){ // sjekker at LM og lege ble funnet
								for (Pasient p : pasientListe) {
									//System.out.println("pasientListeID vs pasientID: " + p.hentID() + " vs " + pasientID);
									if (p.hentID() == pasientID) {
										resPas = p;
										//System.out.println("Test 25: Funnet pasient.");
										if (type.equals("p")) {
											try {
												PrevensjonResept prevRes = resLege.skrivPrevensjonResept(resLm, resPas);
												reseptListe.leggTil(prevRes);
												if (resLm instanceof Narkotisk && resLege instanceof Spesialist) { 
													totalNarkotisk++; 
												}
												if (resLm instanceof Vanedannede) { 
													totalVanedannende++; 
												}
												resPas.leggInn(prevRes);
											} catch (UlovligUtskrift u) {}
										}
										
										else {
											int resReit = Integer.parseInt(linjer[4]);
											// alle resepter unntatt P-resepter har reit
											
											if (type.equals("blaa")) { 
												try {
													BlaaResept blaaRes = resLege.skrivBlaaResept(resLm, resReit, resPas);
													reseptListe.leggTil(blaaRes);
													if (resLm instanceof Narkotisk && resLege instanceof Spesialist) { 
														totalNarkotisk++; 
													}
													if (resLm instanceof Vanedannede) { 
														totalVanedannende++; 
													}
													resPas.leggInn(blaaRes);
												} catch (UlovligUtskrift u) {}
											}
											
											if (type.equals("militaer")) {
												try {
													MilitaerResept miliRes = resLege.skrivMilitaerResept(resLm, resReit, resPas);
													reseptListe.leggTil(miliRes);
													if (resLm instanceof Narkotisk && resLege instanceof Spesialist) { 
														totalNarkotisk++; 
													}
													if (resLm instanceof Vanedannede) { 
														totalVanedannende++; 
													}
													resPas.leggInn(miliRes);
												} catch (UlovligUtskrift u) {}
											}
											
											if (type.equals("hvit")) {
												try {
													HvitResept hvitRes = resLege.skrivHvitResept(resLm, resReit, resPas);
													reseptListe.leggTil(hvitRes);
													if (resLm instanceof Narkotisk && resLege instanceof Spesialist) { 
														totalNarkotisk++; 
													}
													if (resLm instanceof Vanedannede) { 
														totalVanedannende++; 
													}
													resPas.leggInn(hvitRes);
												} catch (UlovligUtskrift u) {}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
		}
		//System.out.println();
		//System.out.println("Test 26: Ultimat test.");
		//System.out.println();
		//skrivUt();
	}

	public void kommando() throws UlovligUtskrift{
		boolean quit = false;
		Scanner iscan = new Scanner(System.in);
		
		while (quit == false) {
			// do stuff
			System.out.println("Velkommen til Legesystem!");
			System.out.println("Du kan når som helst trykke 'q' for å avslutte.");
			System.out.print("Gå videre? Trykk enter: ");
			String inp = iscan.nextLine();
			if (inp.equals("q")) { quit = true; }
			if (quit == true) { break; }
			else {
				System.out.println("Hva vil du gjøre?");
				System.out.println("1. Skrive ut fullstendig oversikt");
				System.out.println("2. Opprette og legge til nye elementer");
				System.out.println("3. Bruke en pasient sin resept");
				System.out.println("4. Skrive ut statistikk");
				System.out.println("5. Skrive ut all data til fil");
				inp = iscan.nextLine();
				if (inp.equals("1")) {
					skrivUt();
				}
				
				else if (inp.equals("2")) {
					System.out.println("Hva vil du gjøre?");
					System.out.println("1. Lage pasient");
					System.out.println("2. Lage legemiddel");
					System.out.println("3. Lage lege");
					System.out.println("4. Lage resept");
					inp = iscan.nextLine();
					if (inp.equals("1")) {
						leggTilPasient();
					}
					
					else if (inp.equals("2")) {
						leggTilLegemiddel();
					}
					
					else if (inp.equals("3")) {
						leggTilLege();
					}
					
					else if (inp.equals("4")) {
						leggTilResept();
					}
					
					else if (inp.equals("q")) { quit = true; }
				}
				
				else if (inp.equals("3")) {
					brukResept();
				}
				
				else if (inp.equals("4")) {
					skrivUtStatistikk();
				}
				
				else if (inp.equals("5")) {
					skrivTilFil();
				}
				
				else if (inp.equals("q")) { quit = true; }
			}
		}
	}
	
	public void skrivUt() {
		System.out.println("Pasienter: ");
		for (Pasient p : pasientListe) { System.out.println(p); System.out.println(); }
		System.out.println();
		
		System.out.println("Legemidler: ");
		for (Legemiddel lm : lmListe) { System.out.println(lm); System.out.println(); }
		System.out.println();
		
		System.out.println("Leger: ");
		for (Lege l : legeListe) { System.out.println(l); System.out.println(); }
		System.out.println();
		
		System.out.println("Resepter: ");
		for (Resept r : reseptListe) { System.out.println(r); System.out.println(); }
		System.out.println();
	}
	
	public void skrivTilFil() {
		Scanner wscan = new Scanner(System.in);
		System.out.print("Skriv inn et filnavn: ");
		String filNavn = wscan.nextLine() + ".txt";
		try {
			File nyFil = new File(filNavn);
			
			if (nyFil.createNewFile()) {
				System.out.println("Fil lagd.");
				// Skriv til fil
				//
				//
				FileWriter nySkriver = new FileWriter(filNavn);
				nySkriver.write("# Pasienter (navn, fnr)" + "\n");
				for (Pasient p : pasientListe) {
					nySkriver.write(p.toString() + "\n");
				}
				nySkriver.write("# Legemidler (navn,type,pris,virkestoff,[styrke])" + "\n");
				for (Legemiddel lm : lmListe) {
					nySkriver.write(lm.toString() + "\n");
				}
				nySkriver.write("# Leger (navn,kontrollid / 0 hvis vanlig lege)" + "\n");
				for (Lege l : legeListe) {
					nySkriver.write(l.toString() + "\n");
				}
				nySkriver.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])" + "\n");
				for (Resept r : reseptListe) {
					nySkriver.write(r.toString() + "\n");
				}
				nySkriver.close();
				System.out.println();
			}
			
			else {
				System.out.println("Fil eksisterer.");
				System.out.println();
			}
		} catch (IOException e) {
			System.out.println("ERROR.");
			e.printStackTrace();
			System.out.println();
		}
	}
	
	public void leggTilPasient() {

		String navn = null;
		String fodselsnummer = null;
		Scanner sc = new Scanner(System.in);
		System.out.print("Skriv inn et navn: ");
		navn = sc.nextLine();
		System.out.print("Skriv inn et fødselsnummer: ");
		fodselsnummer = sc.nextLine();
	    Pasient nyPasient = new Pasient(navn, fodselsnummer);
	    pasientListe.leggTil(nyPasient);
	    System.out.println();
	  }
	
	public void leggTilLegemiddel() {
		
		String type = null;
		String navn = null;
		double pris = 0;
		double virkestoff = 0;
		double styrke = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Hva slags legemiddel vil du opprette? (narkotisk/vanedannende/vanlig): ");
		type = sc.nextLine();
		System.out.println();
		System.out.print("Gi et navn til legemiddelet ditt: ");
		navn = sc.nextLine();
		System.out.println();
		System.out.print("Sett en pris på legemiddelet: ");
		try { pris = sc.nextDouble(); }
		catch (InputMismatchException a) { 
			System.out.println("Vennligst skriv inn et tall!");
			System.out.println();
			leggTilLegemiddel();
			return;
		}
		System.out.println();
		System.out.print("Hvor mye milligram veier stoffet?: ");
		try { virkestoff = sc.nextDouble(); }
		catch (InputMismatchException b) { 
			System.out.println("Vennligst skriv inn et tall!");
			System.out.println();
			leggTilLegemiddel();
			return;
		}
		System.out.println();
		System.out.print("Hvor sterkt er stoffet?: ");
		try { styrke = sc.nextDouble(); }
		catch (InputMismatchException c) { 
			System.out.println("Vennligst skriv inn et tall!");
			System.out.println();
			leggTilLegemiddel();
			return;
		}

	    Legemiddel nyLm;
	    if (type.toLowerCase().equals("narkotisk")) {

	      nyLm = new Narkotisk(navn, pris, virkestoff, styrke);
	      lmListe.leggTil(nyLm);

	    } else if (type.toLowerCase().equals("vanedannende")) {

	      nyLm = new Vanedannede(navn, pris, virkestoff, styrke);
	      lmListe.leggTil(nyLm);

	    } else if (type.toLowerCase().equals("vanlig")) {

	      nyLm = new Vanlig(navn, pris, virkestoff);
	      lmListe.leggTil(nyLm);

	    }
	    System.out.println();
	  }
	
	public void leggTilLege() {
	    Lege nyLege;
	    String navn = null;
	    int kontrollID = 0;
	    Scanner sc = new Scanner(System.in);
	    System.out.print("Skriv et navn til legen din: ");
	    navn = sc.nextLine();
	    System.out.println();
	    System.out.print("Er legen spesialist? Skriv '0' hvis nei, skriv kontroll ID-en hvis ja: ");
	    try { kontrollID = sc.nextInt(); }
	    catch (InputMismatchException a) { 
			System.out.println("Vennligst skriv inn et tall!");
			System.out.println();
			leggTilLege();
			return;
		}

	    if (kontrollID == 0) {

	      nyLege = new Lege(navn);
	      legeListe.leggTil(nyLege);

	    } else {

	      nyLege = new Spesialist(navn, kontrollID);
	      legeListe.leggTil(nyLege);

	    }
	    System.out.println();
	  }
	
	public boolean leggTilResept() throws UlovligUtskrift {
	    //Denne metoden kaster UlovligUtskrift, dette maa tas haand om av metoden som kaller paa den.
	    //Hvis den ikke kan legge til noen resept, saa returnerer den false
	    //Hvis den klarer legge til resept, saa returnerer den true
	    Resept nyResept = null;
	    Scanner sc = new Scanner(System.in);
	    String legenavn = null;
	    String type = null;
	    int lmNummer = 0;
	    int pasientId = 0;
	    int reit = 0;
	    
	    if (lmListe.stoerrelse() == 0) { 
	    	System.out.println("Du kan ikke opprette resepter uten et legemiddel!");
	    	System.out.println();
	    	return false;
	    }
	    
	    if (legeListe.stoerrelse() == 0) {
	    	System.out.println("Du kan ikke opprette resepter uten en lege!");
	    	System.out.println();
	    	return false;
	    }

	    if (pasientListe.stoerrelse() == 0) {
	    	System.out.println("Du kan ikke opprette resepter uten en pasient!");
	    	System.out.println();
	    	return false;
	    }
	    
	    System.out.println("Velkommen til ReseptSkaper!");
	    for (Legemiddel lm : lmListe) { System.out.println(lm.hentID() + ". " + lm); }
	    System.out.print("Skriv inn ID-en til legemiddelet: ");
	    try { lmNummer = sc.nextInt(); }
	    catch (InputMismatchException a) { 
			System.out.println("Vennligst skriv inn et tall!");
			System.out.println();
			leggTilResept();
			return false;
		}
	    sc.nextLine();
	    System.out.println();
	    
	    for (Lege l : legeListe) { System.out.println(l.hentNavn()); }
	    System.out.print("Skriv inn hele navnet på legen: ");
	    legenavn = sc.nextLine();
	    System.out.println();
	    
	    for (Pasient p : pasientListe) { System.out.println(p.hentID() + ". " + p.hentNavn()); }
	    System.out.print("Skriv inn ID-en til pasienten: ");
	    try { pasientId = sc.nextInt(); }
	    catch (InputMismatchException a) { 
			System.out.println("Vennligst skriv inn et tall!");
			System.out.println();
			leggTilResept();
			return false;
		}
	    sc.nextLine();
	    System.out.println();
	    
	    System.out.print("Hva slags type er legemiddelet? (hvit/militaer/p/blaa)");
	    type = sc.nextLine();
	    System.out.println();
	    
	    System.out.print("Hvis du har en reit, vennligst oppgi den (hvis ikke, skriv '0'): ");
	    try { reit = sc.nextInt(); }
	    catch (InputMismatchException a) { 
			System.out.println("Vennligst skriv inn et tall!");
			System.out.println();
			leggTilResept();
			return false;
		}
	    sc.nextLine();
	    System.out.println();

	    for (Lege l : legeListe) {

	      if (l.hentNavn().toLowerCase().equals(legenavn.toLowerCase())) {

	        for (Pasient p : pasientListe) {

	          if (p.hentID() == pasientId) {

	            for (Legemiddel lm : lmListe) {

	              if (lm.hentID() == lmNummer) {

	                if (type.toLowerCase().equals("hvit")) {
	                	try {
	                		nyResept = l.skrivHvitResept(lm, reit, p);
	                		p.leggInn(nyResept);
	    	                reseptListe.leggTil(nyResept);
	    	                if (lm instanceof Narkotisk && l instanceof Spesialist) { totalNarkotisk++; }
	    	                if (lm instanceof Vanedannede) { totalVanedannende++; }
	                	} catch (UlovligUtskrift u) { System.out.println("Legen har ikke tillatelse!"); }

	                } else if (type.toLowerCase().equals("militaer")) {
	                	try {
	                		nyResept = l.skrivMilitaerResept(lm, reit, p);
	                		p.leggInn(nyResept);
	    	                reseptListe.leggTil(nyResept);
	    	                if (lm instanceof Narkotisk && l instanceof Spesialist) { totalNarkotisk++; }
	    	                if (lm instanceof Vanedannede) { totalVanedannende++; }
	                	} catch (UlovligUtskrift u) { System.out.println("Legen har ikke tillatelse!"); }
	                } else if (type.toLowerCase().equals("p")) {
	                	try {
	                		nyResept = l.skrivPrevensjonResept(lm, p);
	                		p.leggInn(nyResept);
	    	                reseptListe.leggTil(nyResept);
	    	                if (lm instanceof Narkotisk && l instanceof Spesialist) { totalNarkotisk++; }
	    	                if (lm instanceof Vanedannede) { totalVanedannende++; }
	                	} catch (UlovligUtskrift u) { System.out.println("Legen har ikke tillatelse!"); }
	                } else if (type.toLowerCase().equals("blaa")) {
	                	try {
	                		nyResept = l.skrivBlaaResept(lm, reit, p);
	                		p.leggInn(nyResept);
	    	                reseptListe.leggTil(nyResept);
	    	                if (lm instanceof Narkotisk && l instanceof Spesialist) { totalNarkotisk++; }
	    	                if (lm instanceof Vanedannede) { totalVanedannende++; }
	                	} catch (UlovligUtskrift u) { System.out.println("Legen har ikke tillatelse!"); }
	                } else {return false;}
	                
	                return true;

	              }
	            }

	            return false;

	          }
	        }

	        return false;

	      }
	    }

	    return false;

	  }
	
	public int brukResept() {
		int pasientId = 0;
		int reseptTall = 0;
		int teller = 0;
		Pasient pas = null;
		Scanner sc = new Scanner(System.in);
		
		for (Pasient p : pasientListe) { System.out.println(p.hentID() + ". " + p.hentNavn()); }
		System.out.print("Skriv pasientens ID: ");
		pasientId = sc.nextInt();
		System.out.println();
		
		for (Pasient p : pasientListe) {
			if (p.hentID() == pasientId) { pas = p; }
		}
		System.out.println("Pasient: " + pas);
		for (Resept r : pas.hentResepter()) { 
			System.out.println(teller + ". " + r + " | Gjenstående reit: " + r.hentReit());
			teller++;
		}
		if (pas.antRes() == 0) { System.out.println("Pasient har ingen resepter!"); return 0; }
		
		System.out.print("Velg resept: ");
		reseptTall = sc.nextInt();
		System.out.println();
	    Resept r = pasientListe.hent(pasientId).hentResepter().hent(reseptTall);
	    System.out.println(r);
	    if (r.bruk()) { 
	    	System.out.println("Resept ble brukt!"); 
	    	System.out.println("Gjenstående reit: " + r.hentReit());
	    	System.out.println();
	    }
	    else { System.out.println("Reiten er tom, resepten ble ikke brukt!"); }
	    return r.hentReit();

	  }
	
	public void skrivUtStatistikk() {
		int sjekker = 0;
		System.out.println("Antall resepter med narkotisk legemiddel: " + totalNarkotisk);
		System.out.println("Antall resepter med vanedannende legemiddel: " + totalVanedannende);

		System.out.println("Ant. leger med minst en utskrevet narkotisk resept (navn, antall resepter): ");
		for (Lege l : legeListe) {
			if (l.hentAntallNarkotisk() > 0) {
				System.out.println(l.hentNavn() + ": " + l.hentAntallNarkotisk());
				sjekker++;
			}
		}
		if (sjekker == 0) { System.out.println("Ingen narkotiske resepter er utskrevet!"); }
		System.out.println();
		
		System.out.println("Ant. pasienter med minst en gyldig resept paa narkotisk legemiddel: ");
		for (Pasient p : pasientListe) {
			if (p.hentAntallNarkotisk() > 0) {
				System.out.println(p.hentNavn() + ": " + p.hentAntallNarkotisk());
			}
		}
		System.out.println();
	}
}
