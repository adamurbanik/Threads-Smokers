/* Klasa zostala zaimplementowana jako monitor ktora kontroluje dostep do danych, 
 * w tym przydpadku dostep do elementow umieszczonych na stole. 
 * Dane moga byc tylko obserwowane lub modyfikowane przez procedury dostepu klasy monitor.
 * Tylko jedna pojedyncza procedura dostepu moze byc aktywna przez caly czas.
 * Klasa Monitor wspiera warunek synchronizacji i blokuje wszystkie watki do momentu az okreslony warunek jest spelniony.
 * Monitor zaklada zamek (lock) na zainteresowane obiekty i zabezpiecza fragmenty kodu przed egzekucja przez inne watki.
  Stad tez metody putElements() i getAllElements() zostaly zaimplementowane z zasada synchronizacji zamykajac dostep 
  do danych umieszczonych na stole.
 * */
public class TableBox {

	private int[] _elements;
	private boolean _elemLock;
	private ElementName _en;
	
	/* Konstruktor inicuje obiekt typu TableBox bez zamykania dostepu do czegokolowiek
	 * */
	public TableBox() {
		_elemLock = false;
		_en = new ElementName();
		
	}
	
	/* Metoda ktora ma za zadanie polozcy nowe elementy do monitora tylko wtedy kiedy warunek jest spelniony
	 * Tutaj brak elementow na stole i brak zamka.
	 * Jesli warunek nie jest spelniony, zainteresowany watek musi czekac.
	 * Jesli warunek zostal spelniony petla zostaje przerwana i elementy zostaja przekazane do monitora
	 * Informacja o nowych elementach jest przekazana do loga.
	 * Metoda powiadamia wszytskie inne zainteresowane watki ze cos sie zmnienilo ktore moga zaczac dzialac
	 * Obecny watek zasypia spowrotem jako ze elementy sa obecne w monitorze
	 * */
	synchronized public void putElements(int[] elements) throws InterruptedException {
		while (_elements!= null || _elemLock) {
			wait();
		}
		
		_elemLock = true;
		_elements = elements;
		_elemLock = false;
		log(_en.getElementName(elements[0]) + " and  " + _en.getElementName(elements[1]));
		
		notifyAll();
	}
	
	private boolean checkElements(int element) throws InterruptedException {
		
		if (_elements == null) { 
			return false;
		}
		
		return _elements[0] + _elements[1] + element == 6;
	}
	
	/* Metoda ktora ma za zadanie zabrac elementy z monitora tylko wtedy kiedy warunek jest spelniony 
	 * Tutaj monitor musi posiadac elementy, brak zamka i warunek allowed spelniony co do zgodnosci elementow.
	 * W tym przypadku tylko wlasciwy watek moze sobie wziac elementy, czyli to on posiada brakujacy element
	 * Jesli warunek zostal spelniony elementy w monitorze sa przekazane watkowi i w monitorze przestaja istniec.
	 * Metoda powiadamia wszytskie inne zainteresowane watki ze cos sie zmnienilo ktore moga zaczac dzialac.
	 * Obecny watek zasypia jako ze nie ma juz zadnych elementow w monitorze.
	 * */
	synchronized public int[] getAllElements(int element) throws InterruptedException {
		boolean allowed = false;
		while (_elements == null || _elemLock || !allowed) {			
			
			if(checkElements(element)) {
				break;
			}
			else {
				allowed = false;
			}
			
			wait();
			
		}
		
		
		_elemLock = true;
		int[] elements = _elements;
		_elements = null;
		_elemLock = false; 
		notifyAll();
		return elements;
	}
	
	
	/* Metoda ktora ma za zadanie napisac log z wartoscia parametru podanym jako argument */
	public void log(String s) {
		System.out.println("On the table we have: " + s);

	}
}
