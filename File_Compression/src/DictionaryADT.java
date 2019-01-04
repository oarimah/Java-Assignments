public interface DictionaryADT {

    int insert (DictEntry pair) throws DictionaryException;

    void remove (String key) throws DictionaryException;

    DictEntry find (String key);

    int numElements();

}
