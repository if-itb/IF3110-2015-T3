package UserModel;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author M. Fauzan Naufan
 */
public class User {

    @XmlElement(name = "UserID", required = true)
    private final int userid;
    @XmlElement(name = "Nama", required = true)
    private String nama;
    @XmlElement(name = "Email", required = true)
    private String email;
    @XmlElement(name = "Password", required = true)
    private String password;

    public User() {
        userid = 0;
    }

    public User(int userid1, String nama1, String email1, String password1) {
        userid = userid1;
        nama = nama1;
        email = email1;
        password = password1;
    }
}
