public interface DynamicString {
	/**
	 * Antal tecken i strängen
	 * @return antal tecken i strängen
	 */
	public int length();
	
	/**
	 * Returnerar tecknet i angiven position i strängen
	 * @param index positionen för tecknet som ska returneras
	 * @return tecknet i positionen
	 */
	public char charAt(int index);
	
	/**
	 * Returnerar teckensekvensen som ett String-objket
	 * @return Teckensekvensen som ett String-objekt
	 */
	public String toString();
	
	/**
	 * Lägger till ett tecken sist i strängen
	 * @param chr Tecknet som ska läggas till
	 */
	public void append(char chr);
	
	/**
	 * Lägger till tecknen i ett DString-objekt sist i strängen
	 * @param str Strängen som ska läggas till
	 */
	public void append(DString str);
	
	/**
	 * Tar bort tecken ur strängen. Det första tecknet som tas bort är i positionen start och 
	 * teckenen fram till positionen (end-1) tas bort
	 * @param start Positionen där borttagningen startas
	 * @param end Tecken tas bort fram till positionen end. Tecknet i positionen end är kvar i strängen.
	 */
	public void delete(int start, int end);
	
	/**
	 * Tar bort tecknet i angiven position
	 * @param index Positionen för tecknet som ska tas bort
	 */
	public void delete(int index);
	
	/**
	 * Returnerar ett String-objekt som består av tecknen från positionen start till positionen (end-1)
	 * @param start Positionen start ger det första tecknet i retursträngen
	 * @param end Positionen (end-1) ger det sista tecknet i retursträngen
	 * @return Ett String-objekt som innehåller tecknen från positionen start till positionen (end-1)
	 */
	public String substring(int start, int end);
	
	/**
	 * Returnerar ett String-objekt som består av tecknen från positionen start
	 * @param start Positionen start ger det första tecknet i retursträngen
	 * @return ett String-objekt som innehåller tecknen fr.o.m. positionen start
	 */
	public String substring(int start);
	
	/**
	 * Returnerar positionen för första förekomsten av tecknet chr. Om tecknet chr ej finns i strängen 
	 * returneras -1.
	 * @param chr tecknet som metoden söker efter
	 * @return positionen för första förekomsten av tecknet i strängen. Om tecknet ej finns i strängen 
	 * så returneras -1. 
	 */
	public int indexOf(char chr);
}
