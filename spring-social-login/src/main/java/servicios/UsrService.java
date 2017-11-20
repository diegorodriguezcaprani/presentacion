package servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "usrService")
@ApplicationScoped
public class UsrService {
	private final static String[] lastnames;
    
    private final static String[] names;
     
    static {
        lastnames = new String[10];
        lastnames[0] = "Black";
        lastnames[1] = "White";
        lastnames[2] = "Green";
        lastnames[3] = "Red";
        lastnames[4] = "Blue";
        lastnames[5] = "Orange";
        lastnames[6] = "Silver";
        lastnames[7] = "Yellow";
        lastnames[8] = "Brown";
        lastnames[9] = "Maroon";
         
        names = new String[10];
        names[0] = "BMW";
        names[1] = "Mercedes";
        names[2] = "Volvo";
        names[3] = "Audi";
        names[4] = "Renault";
        names[5] = "Fiat";
        names[6] = "Volkswagen";
        names[7] = "Honda";
        names[8] = "Jaguar";
        names[9] = "Ford";
    }
     
    public List<Usuario> createUsrs(int size) {
        List<Usuario> list = new ArrayList<Usuario>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new Usuario(getRandomId(), getRandomName(), getRandomYear(), getRandomLastname(), getRandomYear(), getRandomState()));
        }
         
        return list;
    }
     
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }
     
    private String getRandomLastname() {
        return lastnames[(int) (Math.random() * 10)];
    }
     
    private String getRandomName() {
        return names[(int) (Math.random() * 10)];
    }
     
    public int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }
     
    public boolean getRandomState() {
        return (Math.random() > 0.5) ? true: false;
    }
 
    public List<String> getLastnames() {
        return Arrays.asList(lastnames);
    }
     
    public List<String> getNames() {
        return Arrays.asList(names);
    }
}
