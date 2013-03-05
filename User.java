public class User {

    private String username;
    private String password;
    private String email;
    private int level;
    private int points;
    private int qcorrect;
    private int qincorrect;
    private int totalquestions;
    
    public User(){
        level = 1;
        points = 0;
        qcorrect = 0;
        qincorrect = 0;
        totalquestions = 0;
    }
    
    public User(String u, String p, String e){
        username = u;
        password = p;
        email = e;
        User();
    }
    
    public saveInfo(){
        
    }
    
    
}
