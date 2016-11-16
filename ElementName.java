/* Klasa ktora prezentuje wlasciwosci tekstowe odpowiednio dla wartosci numerycznych elementow
 * Sa trzy mozliwosci dla wartosci numerycznych dla papieru, tytoniu i zapalek*/
public class ElementName {

	public static final int INGREDIENT_TYPE_PAPER = 1;
	public static final int INGREDIENT_TYPE_TOBACCO = 2;
	public static final int INGREDIENT_TYPE_MATCHES = 3;
	
	private String _elementName;
	
	
	/* Metoda ktora zwraca wartosc tekstowa dla podanej wartosci numerycznej jako parametr*/
	public String getElementName(int element) {
		
		switch(element) {
			case INGREDIENT_TYPE_PAPER:
				_elementName = "paper";
				break;

			case INGREDIENT_TYPE_TOBACCO:
				_elementName = "tobacco";
				break;
	
			case INGREDIENT_TYPE_MATCHES:
				_elementName = "matches";
			break;
		}
		
		return _elementName;
	}
		
	
	
}
