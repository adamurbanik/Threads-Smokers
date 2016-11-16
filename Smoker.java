/* Klasa ta prezentuje  glowne funkcjonalnosci palacza i posiada pola prywatne: 
 int _element ktory przechowuje wartosc numeryczna posiadanego elementu 
 String _myName jako imie palacza 
 boolean _connected jako wartosc boolean czy Palacz jest polaczony ze stolem
 Table _table jako referencja obiektu stolu do ktorego palacz jest polaczony
 TableBox tableBox jako monitor ktory zarzadza danymi na stole
 ElementName en jako wartosc tekstowa posiadanego elementu
 Klasa Smoker jest subklasa klasy Thread aby Palacz mogl dzialac jako osobny watek,
 stad tez implementacja metody run() ktora zawiera kod ktory jest wykonywany przez watek.
 */
public class Smoker extends Thread {
	
	private int _element = 0;
	private String _myName = "";
	private boolean _connected;
	private Table _table;
	private TableBox _tableBox;
	ElementName en;
	
	/* Konstruktor ktory inicjuje obiekt typu Smoker z parametrami typu
	 * nazwa palacza, wartosc numeryczna elementu ktory posiada Palacz,
	 * stol do ktorego Palacz ma byc polaczony.
	 * Palacz jest niezwlocznie polaczony ze stolem
	 * */
	public Smoker(String name, int element, Table table) {
		this._myName = name;
		this._element = element;
		
		en = new ElementName();
		
		_connected = false;
		
		connect(table);
		
	}
	
	/* Metoda ktora zwraca wartosc elementu*/
	public int getElement() {
		return _element;
	}
	
	/* Metoda ktora ustawia wartosc elementu */
	public void setElement(int element) {
		_element = element;
	}
	
	 /* Kod ktory jest wykonywana w watku dla Palacza.
	 * Informacja w logu o polaczeniu przez Palacza. Istniejacy obiekt Palacza
	 * ma za zadanie przetworzyc wszystkie elementy jesli to on jest szczesliwcem ktoremu 
	 * sie uda zebrac wszystkie elementy */
	public void run () {
		if (_connected) {
			log(" entered the table");
			
			while(_connected) {
				try {
					//log ("Waiting for all elements to be on the table");
					processElements(_tableBox.getAllElements(_element));  
					
				}
				catch(Exception e) {
					log(e.getMessage());
					break;
				}
			}
		}
		
	}
	
	/* Metoda ktora ma za zadanie napisac log z wartoscia parametru podanym jako argument */
	private void log(String s) {
		String s_element = en.getElementName(_element);
		System.out.println("Smoker: " + _myName + " with element " + s_element + " " + s);
	}
	
	/* Metoda ktora ma za zadanie polaczyc istniejacy obiekt Palacza z istniejaca referencja do obiektu stolu 
	 * przeslanym jako parametr. Laczy nie tylko ze stolem ale tez z monitorem tableBox. 
	 * Raz polaczony palacz zachowuje informacje do obiektow stolu i monitora.
	 * Loguje informacje o statusie polaczenia.
	 * */
	private boolean connect(Table table) {
		_connected = false;
		_tableBox = table.connect(_element);
		_table = table;
		
		boolean success = _tableBox != null ? true : false;
		if (success) {
			log("Connected to " + table.getName());
			_connected = true;
		}
		else {
			log("Connection to " + table + " failed");
			_connected = false;
		}
		return success;
	}
	
	/* Metoda ktora ma za zadanie powiadomic wszystkich zaintersowanych Palaczy ze ten obiekt Palacza skolekcjonowal
	 * wszystkie elementy. Dodatkowy check tutaj nie jest potrzebny ale istnieje i sprawdza czy wszystkie elementy 
	 * do siebie pasuja jak nalezy. Jesli tak informacja jest zapisana w logu.
	 * */
	private void processElements(int[] tbBoxElements) {
		boolean success = false;
		if(tbBoxElements[0] + tbBoxElements[1] + _element == 6) {
			success = true;
			log("collected all the elements. This player can have a smoke!!!!!!!!!!!!!");
		}
	
		
	}
	
	
	
}
