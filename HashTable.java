public class HashTable {
	private LinkedList[] table;

	public HashTable(int size) {
		table = new LinkedList[size];
		for (int i = 0; i < table.length; i++) {
			table[i] = new LinkedList();
		}
	}

	public int hashFunction(String key) {
		int hv = key.hashCode();

		hv = Math.abs(hv); // in case int became too "big" and resulting a negative
		return hv % table.length; // forces the value to be a valid index
	}

	public void insert(String key) {
		int hv = hashFunction(key);
		table[hv].addToEnd(key);
	}

	public boolean retrieve(String keyToLookFor) {
		int hv = hashFunction(keyToLookFor);
		return table[hv].search(keyToLookFor);
	}

}
