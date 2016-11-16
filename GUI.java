/* Klasa ta ma za zadanie uruchomić cala aplikacje i zainicjować wszelkie procesy. 
 Uruchamia obiekt stolu do ktorego usiada palacze i agent plus
 poszczegolne postacie palaczy i agenta w osobnych watkach. */
public class GUI {

	public static void main(String[] args) {
		
		Table table = new Table();
		
		new Smoker("Pawel", 1, table).start();;
		new Smoker("Przemek", 2, table).start();
		new Smoker("Ania", 3, table).start();

		new Agent(table).start();
		
		
	}

}
