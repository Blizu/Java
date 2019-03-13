
public class S16710_p01 {

	private int rokPoczatek;
	private int miesiacPoczatek;
	private int dzienPoczatek;
	private int godzinaPoczatek;
	private int minutaPoczatek;
	private int sekundaPoczatek;
	private int milisekundaPoczatek;
	private int rokKoniec;
	private int miesiacKoniec;
	private int dzienKoniec;
	private int godzinaKoniec;
	private int minutaKoniec;
	private int sekundaKoniec;
	private int milisekundaKoniec;

	public S16710_p01(int rokPoczatek, int miesiacPoczatek, int dzienPoczatek, int godzinaPoczatek, int minutaPoczatek,
			int sekundaPoczatek, int milisekundaPoczatek, int rokKoniec, int miesiacKoniec, int dzienKoniec,
			int godzinaKoniec, int minutaKoniec, int sekundaKoniec, int milisekundaKoniec) {
		this.rokPoczatek = rokPoczatek;
		this.miesiacPoczatek = miesiacPoczatek;
		this.dzienPoczatek = dzienPoczatek;
		this.godzinaPoczatek = godzinaPoczatek;
		this.minutaPoczatek = minutaPoczatek;
		this.sekundaPoczatek = sekundaPoczatek;
		this.milisekundaPoczatek = milisekundaPoczatek;
		this.rokKoniec = rokKoniec;
		this.miesiacKoniec = miesiacKoniec;
		this.dzienKoniec = dzienKoniec;
		this.godzinaKoniec = godzinaKoniec;
		this.minutaKoniec = minutaKoniec;
		this.sekundaKoniec = sekundaKoniec;
		this.milisekundaKoniec = milisekundaKoniec;

		rokPoczatek = ustawPrzedzial(rokPoczatek, 1518, Integer.MAX_VALUE, "błędny rok daty początkowej");
		miesiacPoczatek = ustawPrzedzial(miesiacPoczatek, 1, 12, "błędny miesiąc daty początkowej");
		dzienPoczatek = ustawPrzedzial(dzienPoczatek, 1, dlugoscMiesiaca(miesiacPoczatek, rokPoczatek),
				"błędny dzień daty początkowej");
		godzinaPoczatek = ustawPrzedzial(godzinaPoczatek, 0, 60, "błędna godzina daty początkowej");
		minutaPoczatek = ustawPrzedzial(minutaPoczatek, 0, 60, "błędna minuta początowej daty początkowej");
		sekundaPoczatek = ustawPrzedzial(sekundaPoczatek, 0, 60, "błędna sekunda daty początowej");
		milisekundaPoczatek = ustawPrzedzial(milisekundaPoczatek, 0, 1000, "błędna milisekunda daty początkowej");

		rokKoniec = ustawPrzedzial(rokKoniec, 1518, 2137, "błędny rok daty końcowej");
		miesiacKoniec = ustawPrzedzial(miesiacKoniec, 1, 12, "błędny miesiąc daty końcowej");
		dzienKoniec = ustawPrzedzial(dzienKoniec, 1, dlugoscMiesiaca(miesiacKoniec, rokKoniec),
				"błędny dzień daty końcowej");
		godzinaKoniec = ustawPrzedzial(godzinaKoniec, 0, 60, "błędna godzina daty końcowej");
		minutaKoniec = ustawPrzedzial(godzinaKoniec, 0, 60, "błędna minuta daty końcowej");
		sekundaKoniec = ustawPrzedzial(sekundaKoniec, 0, 60, "błędna sekunda daty końcowej");
		milisekundaKoniec = ustawPrzedzial(milisekundaKoniec, 0, 1000, "błędna milisekunda daty końcowej");

		if (sprawdzDane() != true)
			System.out.println("Podałeś złe dane");
	}

	public int ustawPrzedzial(int wartosc, int min, int max, String komunikat) {
		if (wartosc >= min && wartosc <= max)
			return wartosc;
		else {
			System.out.println(komunikat);
			return -1;
		}
	}

	// Funkcja domyślna, ustawia dla it wartość 0
	public boolean porownajTabInt(int tab_poczatek[], int tab_koniec[]) {
		return porownaTabjInt(tab_poczatek, tab_koniec, 0);
	}

	// Funkcja porównuje wprowadzone dane, jeżeli rok początkowy jest mniejszy bądź
	// równy od roku końcowego to zwraca fałsz. W przeciwnym wypadku zwraca prawdę.
	public boolean porownaTabjInt(int tab_poczatek[], int tab_koniec[], int it) {
		if (tab_poczatek[it] > tab_koniec[it])
			return false;
		else if (tab_poczatek[it] < tab_koniec[it])
			return true;
		else if (tab_poczatek.length > it + 1) {
			return porownaTabjInt(tab_poczatek, tab_koniec, ++it); // tutaj zastosowana jest rekurencja
		} else
			return false;
	}

	// Funkcja sprawdza, czy dane wprowadzone przy tworzeniu obiektu są prawidłowe
	private boolean sprawdzDane() {
		int[] tab_poczatek = { rokPoczatek, miesiacPoczatek, dzienPoczatek, godzinaPoczatek, minutaPoczatek,
				sekundaPoczatek, milisekundaPoczatek };
		int[] tab_koniec = { rokKoniec, miesiacKoniec, dzienKoniec, godzinaKoniec, minutaKoniec, sekundaKoniec,
				milisekundaKoniec };
		return porownajTabInt(tab_poczatek, tab_koniec);
	}

	public void zwrocDanePoczatkowej() {

		System.out.print("Data początkowa (YYY-MM-DD-hh:mm:ss:ms): ");
		System.out.println(rokPoczatek + " " + miesiacPoczatek + " " + dzienPoczatek + " " + godzinaPoczatek + " "
				+ minutaPoczatek + " " + sekundaPoczatek + " " + milisekundaPoczatek);
		System.out.println(rokPoczatek + " " + miesiacPoczatek + " " + dzienPoczatek + " " + godzinaPoczatek + " "
				+ minutaPoczatek + " " + sekundaPoczatek + " " + milisekundaPoczatek);
	}

	public void zwrocDaneKoncowej() {
		System.out.println(rokKoniec + " " + miesiacKoniec + " " + dzienKoniec + " " + godzinaKoniec + " "
				+ minutaKoniec + " " + sekundaKoniec + " " + milisekundaKoniec);
		System.out.print("Data końcowa (YYY-MM-DD-hh:mm:ss:ms): ");
		System.out.println(rokKoniec + " " + miesiacKoniec + " " + dzienKoniec + " " + godzinaKoniec + " "
				+ minutaKoniec + " " + sekundaKoniec + " " + milisekundaKoniec);
	}

	// Tworzę tablicę, która przechowuje ilość dni dla konkretnego miesiąca.
	static int[] dlugoscMiesiaca_tab = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	// Sprawdzam, który rok jest przystępny
	public static boolean czyPrzystepny(int rok) {
		if ((rok % 4 == 0 && !(rok % 100 == 0)) || rok % 400 == 0)
			return true;
		return false;
	}

	// Ta funkcja w przypadku roku przystępnego dla lutego zmienia ilość dni na 29
	public static int dlugoscMiesiaca(int miesiac, int rok) {
		if (miesiac == 2 && czyPrzystepny(rok))
			return 29;
		else
			return dlugoscMiesiaca_tab[miesiac - 1];
	}

	// jeżeli ostatni elementy tablicy osiągnie swoje maksium to funkcja ustawia dla
	// tego indeksu wartość początkową
	public void resetElementuDaty(int it, int[] data_aktualna) {
		final int[] wartoscMinimalna = { 1518, 1, 1, 0, 0, 0, 0 };
		data_aktualna[it] = wartoscMinimalna[it];
	}

	/// Jeżeli ostatni indeks w tablicy wartośćMaksymalna osiągnie swoje maksiumum,
	/// to zwiększą wartość poprzedniego indeksu o 1 i tak dalej, aż zwiększy
	/// wszystkie elementy tablicy.
	public void licznik(int[] data_aktualna, int it, int[] wartoscMaksymalna) {
		if (data_aktualna[it] < wartoscMaksymalna[it]) {
			data_aktualna[it]++;
		} else {
			resetElementuDaty(it, data_aktualna);
			licznik(data_aktualna, --it, wartoscMaksymalna);
		}
	}

	/// Zwraca datę w następnej milisekundzie
	public int[] dataNastepna(int[] data_aktualna) {
		int[] wartoscMaksymalna = { Integer.MAX_VALUE, 12, dlugoscMiesiaca(data_aktualna[1], data_aktualna[0]), 23, 59,
				59, 1000 };
		licznik(data_aktualna, 6, wartoscMaksymalna);
		return data_aktualna;
	}

	/// Sprawdza, czy tablica data_1 i data_2 są sobie równe
	public boolean porownajTablice(int[] data_1, int[] data_2) {

		for (int i = 0; i < data_1.length; i++) {
			if (data_1[i] != data_2[i]) {
				return false;
			}
		}
		return true;
	}

	public void licznikCzasu() {
		int[] data_aktualna = { rokPoczatek, miesiacPoczatek, dzienPoczatek, godzinaPoczatek, minutaPoczatek,
				sekundaPoczatek, milisekundaPoczatek };
		int[] data_koniec = { rokKoniec, miesiacKoniec, dzienKoniec, godzinaKoniec, minutaKoniec, sekundaKoniec,
				milisekundaKoniec };
		while (!porownajTablice(dataNastepna(data_aktualna), data_koniec)) {
			for (int i = 0; i < data_aktualna.length; i++)
				System.out.print(data_aktualna[i] + " ");
			System.out.println();
		}

	}

	public static void main(String[] args) {

		S16710_p01 data = new S16710_p01(2018, 3, 29, 23, 50, 0, 0, 2018, 3, 29, 23, 50, 0, 20);

		data.zwrocDanePoczatkowej();

		data.licznikCzasu();
		data.zwrocDaneKoncowej();

	}
}
