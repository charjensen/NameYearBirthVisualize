import java.util.Map.Entry;

public interface Map<K, V>
{
	/**
	 * Add the key, value pair into the map if the key is not in the map. Replace the old value with the new one if the key 
	 * is in the map
	 * @param key search by key
	 * @param value value associated with key
	 * @return the old value we replaced or null if key was not in map
	 */
	public V put(K key, V value);
	
	/**
	 * Returns the value associated with the key if in map, null otherwise
	 * @param key search by key
	 * @return value associated by key or null
	 */
	public V get(K key);
	
	/**
	 * Remove entry associated with key and return removed value or null if key is not in map
	 * @param key search by key
	 * @return removed value
	 */
	public V remove(K key);
	
	public boolean isEmpty();
	public int size();
	
	public Iterable<K> keySet();
	public Iterable<V> values();
	public Iterable<Entry<K, V>> entrySet();
}