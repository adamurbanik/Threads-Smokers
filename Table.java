import java.util.ArrayList;


/* Klasa ktora prezentuje glowne funkcjonalnosci stolu i posiada pola prywatne 
 * ArrayList<Integer> _elementsList jako lista elementow liczb calkowitych
 * TableBox _tableBox jako ze stol ma swoj wlasny monitor
 * String _name jako nazwa stolu
 * */
public class Table {

	private ArrayList<Integer> _elementsList;
	private TableBox _tableBox;
	private String _name;
	
	/* Konstruktor ktory inicjuje obiekt Table
	 * Nowa lista elementow liczb calkowitych jest zainicjowana 
	 * Nowy monitor jest uruchomiony
	 * Stol zostaje nazwany
	 * Informacja zostaje przekazana do loga
	 * */
	public Table() {
		_elementsList = new ArrayList<Integer>();
		_tableBox = new TableBox();
		_name = "SMOKING TABLE";
		log("created");
	}
	
	/* Metoda getter do zwrocenia nazwy stolu */
	public String getName() {
		return _name;
	}
	
	/* Metoda ktora pozwala umiescic na stole nowy element
	 * Element wartosci liczby calkowitej jest przekazany jako argument w funkcji
	 * Element jest dodany do listy
	 * Referencja do istniejacego monitora na stole jest zwrocona jako rezultat
	 * */
	public TableBox connect(int element) {
		
		// check if this element already exists and if not add it to list
		if (!_elementsList.contains(element)) {
			_elementsList.add(element);
		}
		else {
			return null;
		}
		return _tableBox;
	}
	
	/* Metoda ktora ma za zadanie napisac log z wartoscia parametru podanym jako argument */
	private void log(String s) {
		System.out.println();
		System.out.println("Table " + _name + ": " + s);
	}
	
	
}
