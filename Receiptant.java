import java.io.Serializable;
import java.util.Date;

public class Receiptant implements Serializable {

    String name;
    String emailID;
    String Designation;

    Date DOB;
    String nickname;

    public Receiptant(String name, String emailID, String designation) {
        this.name = name;
        this.emailID = emailID;
        Designation = designation;
    }

    public Receiptant(String name, String emailID, String designation, Date DOB) {
        this.name = name;
        this.emailID = emailID;
        Designation = designation;
        this.DOB = DOB;
    }

    public Receiptant(String name, String emailID, Date DOB, String nickname) {
        this.name = name;
        this.emailID = emailID;
        this.DOB = DOB;
        this.nickname = nickname;
    }
}
