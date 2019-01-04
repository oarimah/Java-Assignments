/**
 * DictEntry is java class that contains the information about the Dictionary entries.
 *
 * @author Ositadinma Arimah
 * Mail: oarimah@uwo.ca
 */

public class DictEntry
{
    private String key;
    private int code;

    public DictEntry (String key, int code)
    {
        this.key=key;
        this.code=code;
    }

    public String getKey(){return key;}

    public int getCode(){return code;}
}