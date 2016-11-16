import java.util.Random;

/* Klasa Agent reprezentuje glowne wlasciwosci agenta i posiada pola prywatne: 
 * boolean connected informuje czy Agent jest polaczony ze stolem
 * int _element ktory przechowuje wartosc numeryczna posiadanego elementu, tutaj Agent zawsze bedzie mial wartosc 0 
 * Table _table jako referencja obiektu stolu do ktorego agent jest polaczony
 * TableBox tableBox jako monitor ktory zarzadza danymi na stole
 * ElementName en jako wartosc tekstowa posiadanego elementu. na tyme etapie nieuzyta tutaj
 * Klasa Agent jest subklasa klasy Thread aby Agent mogl dzialac jako osobny watek, stad tez implementacja metody run()
 * ktora zawiera kod ktory jest wykonywany przez watek
 * */
public class Agent extends Thread {
	private boolean _connected;
	private int _element = 0;
	private Table _table;
	private TableBox _tableBox;
	ElementName en;
	
	
	/* Konstruktor ktory inicjuje obiekt typu Agent z parametrem obiektu stolu do ktorego Agent ma byc polaczony.
	 * Agent zostaje polaczony ze stolem i informacja o statusie polaczenia jest dana w logu */
	public Agent(Table table) {
		_connected = false;
		_table = table;	
		
		_connected = false;
		
		en = new ElementName();
		
		if(connect(table)) {
			log("Connected to " + table.getName());
			_connected = true;
		}
		else {
			log("Connection to " + table + " failed");
		}
	
	}
	
	/* Kod ktory jest wykonywany w watku dla Agenta.
	 * Informacja w logu o polaczeniu przez Agenta. Istniejacy obiekt Agenta 
	 * ma za zadanie polozyc na stol dwa brakujace elementy. 
	 * Raz polozone na stol Agent czeka na kolejna runde. */
	public void run() {
		if(_connected) {
			log("entered the table");
			
			while(_connected) {
				try {
					log("Who wants a smoke?");
					_tableBox.putElements(findTwoElements());
					this.sleep(5000); 
				}
				catch(Exception e) {
					log(e.getMessage());
					break;
				}
			}
		}
	}
	
	/* Metoda ktora ma za zadanie napisac log z wartoscia parametru podanym jako argument  */
	public void log(String s) {
		System.out.println();
		System.out.println("Agent: " + s);
		
	}
	
	/* Metoda ktora ma za zadanie polaczyc istniejacy obiekt Agenta z istniejaca referencja do obiektu stolu 
	 * przeslanym jako parametr. Laczy nie tylko ze stolem ale tez z monitorem tableBox. 
	 * Raz polaczony agent zachowuje informacje do obiektow stolu i monitora.
	 * */
	private boolean connect (Table table) {
		_connected = false;
		_table = table;
		_tableBox = table.connect(_element);
		
		
		return _tableBox != null ? true : false;
		
	}
	
	/* Metoda ktora ma za zadanie wygenerowac dwa elementy liczby calkowitej typu Random w zakresie od 1 do 3 
	 * Jako rezultat jest zwrocony array zlozony z dwoch zawsze roznych elementow.*/
	private int[] findTwoElements() {
		
		int[] randomNum = new int[2];
		do {
			randomNum[0] = new Random().nextInt((3 - 1) + 1) + 1;
			randomNum[1] = new Random().nextInt((3 - 1) + 1) + 1;
		} while (randomNum[0]==randomNum[1]);
		
		
		return randomNum;
	}
	
	
	
}
