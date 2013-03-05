import java.io.*;
import java.util.*;

public class Questions {
    
    private ArrayList<String> Qlist;
    private ArrayList<String> Alist;
    
    public Questions(){
    Qlist = new ArrayList<String>();
    Alist = new ArrayList<String>();
    }
    
    public String randomQuestion(int level) throws FileNotFoundException {
    Random rand = new Random();
    int index = 0;
        if(level < 10){
            storeQuestions("level1.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
        else if(level >= 10 && level < 20){
            storeQuestions("level2.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
        else if(level >= 20 && level < 30){
            storeQuestions("level3.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
        else if(level >= 30 && level < 40){
            storeQuestions("level4.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
        else if(level >= 40 && level < 50){
            storeQuestions("level5.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
        else if(level >= 50 && level < 60){
            storeQuestions("level6.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
        else if(level >= 60 && level < 70){
            storeQuestions("level7.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
        else if(level >= 70 && level < 80){
            storeQuestions("level8.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
        else if(level >= 80 && level <  90){
            storeQuestions("level9.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
        else if(level >= 90 && level < 100){
            storeQuestions("level10.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
        else{
            storeQuestions("level11.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
    }
    public void storeQuestions(String file) throws FileNotFoundException{
        Scanner input = new Scanner(new File(file));
        String text = "";
        while(input.hasNext()){
           text += input.next() + " ";
        }
        //System.out.println(text);
        String question = "";
        String answer = "";
        int temp = 0;
        for(int i=0; i<text.length(); i++){
            if(text.substring(i,i+2).equals("? ")){
                question = text.substring(temp,i+2);
                temp = i+2;
            }
            else if(text.substring(i,i+2).equals(". ")){
                answer = text.substring(temp,i+2);
                temp = i+2;
            }
            if(checkquestion(question,answer)){
                Qlist.add(question);
                Alist.add(answer);
                question = "";
                answer = "";
            }
            if(i+2 >= text.length())
                break;
        }
    }
    public String getAnswer(String q){
        int temp = 0;
        temp = Qlist.indexOf(q);
        return Alist.get(temp);   
    }
    public boolean checkquestion(String question, String answer){
        if(question != "" && answer != "")
            return true;
        return false;
    }
                
}    
        
