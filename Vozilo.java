package vjezbeVII;

import java.util.ArrayList;

class Vozilo {
	private String proizvodjac;
	private int godinaProizvodnje;
	private double kubikaza;
	private String boja;

	public Vozilo(String proizvodjac, int godinaProizvodnje, double kubikaza, String boja) {
		this.proizvodjac = proizvodjac;
		this.godinaProizvodnje = godinaProizvodnje;
		this.kubikaza = kubikaza;
		this.boja = boja;
	}

	public double izracunajCijenuRegistracije() {
		double cijena = 100;

		if (godinaProizvodnje < 2010)
			cijena += 30;
		if (kubikaza > 2000)
			cijena += 50;

		return cijena;
	}

	public void prikaziPodatke() {
		System.out.println("Proizvođač: " + proizvodjac);
		System.out.println("Godina: " + godinaProizvodnje);
		System.out.println("Kubikaža: " + kubikaza);
		System.out.println("Boja: " + boja);
	}

	class Automobil extends Vozilo {
		private int brojVrata;
		private String tipMotora;

		public Automobil(String proizvodjac, int godinaProizvodnje, double kubikaza, String boja, int brojVrata,
				String tipMotora) {
			super(proizvodjac, godinaProizvodnje, kubikaza, boja);
			this.brojVrata = brojVrata;
			this.tipMotora = tipMotora;
		}

		@Override
		public double izracunajCijenuRegistracije() {
			double cijena = super.izracunajCijenuRegistracije();
			if (tipMotora.equalsIgnoreCase("dizel"))
				cijena += 20;
			return cijena;
		}

		@Override
		public void prikaziPodatke() {
			super.prikaziPodatke();
			System.out.println("Broj vrata: " + brojVrata);
			System.out.println("Tip motora: " + tipMotora);
			System.out.println("Cijena registracije: " + izracunajCijenuRegistracije() + " EUR\n");
		}
	}

	class Kamion extends Vozilo {
		private double kapacitetTereta;
		private boolean prikolica;

		public Kamion(String proizvodjac, int godinaProizvodnje, double kubikaza, String boja, double kapacitetTereta,
				boolean prikolica) {
			super(proizvodjac, godinaProizvodnje, kubikaza, boja);
			this.kapacitetTereta = kapacitetTereta;
			this.prikolica = prikolica;
		}

		@Override
		public double izracunajCijenuRegistracije() {
			double cijena = super.izracunajCijenuRegistracije();
			if (prikolica)
				cijena += 50;
			return cijena;
		}

		@Override
		public void prikaziPodatke() {
			super.prikaziPodatke();
			System.out.println("Kapacitet tereta: " + kapacitetTereta + "t");
			System.out.println("Prikolica: " + (prikolica ? "Da" : "Ne"));
			System.out.println("Cijena registracije: " + izracunajCijenuRegistracije() + " EUR\n");
		}
	}

	class Kombi extends Vozilo {
		private int kapacitetPutnika;

		public Kombi(String proizvodjac, int godinaProizvodnje, double kubikaza, String boja, int kapacitetPutnika) {
			super(proizvodjac, godinaProizvodnje, kubikaza, boja);
			this.kapacitetPutnika = kapacitetPutnika;
		}

		@Override
		public double izracunajCijenuRegistracije() {
			double cijena = super.izracunajCijenuRegistracije();
			if (kapacitetPutnika > 8)
				cijena += 30;
			return cijena;
		}

		@Override
		public void prikaziPodatke() {
			super.prikaziPodatke();
			System.out.println("Kapacitet putnika: " + kapacitetPutnika);
			System.out.println("Cijena registracije: " + izracunajCijenuRegistracije() + " EUR\n");
		}
	}

	public class Main {
        public static void main(String[] args) {
            ArrayList<Vozilo> vozila = new ArrayList<>();

            vozila.add(new Automobil("Audi", 2015, 2.0, "Crna", 4, "dizel"));
            vozila.add(new Kamion("Volvo", 2005, 5.0, "Bijela", 10, true));
            vozila.add(new Kombi("Mercedes", 2012, 2.5, "Siva", 9));

            for (Vozilo v : vozila) {
                v.prikaziPodatke();
            }
        }
    }
