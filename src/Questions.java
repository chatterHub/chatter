package src;
import java.io.*;
import java.util.*;

//QUESTIONS CLASS - To build ArrayLists of Questions and Answers
//depending on users level
public class Questions {
    
    //Fields: ArrayList<String> Qlist - List of Questions
    //        ArrayList<String> Alist - List of Answers
    private ArrayList<String> Qlist;
    private ArrayList<String> Alist;
    
    //Constructor: Builds both ArrayLists
    public Questions(){
    Qlist = new ArrayList<String>();
    Alist = new ArrayList<String>();
    }
    //Parameters: int level: Users level
    //Throws: FileNitFoundException (if specific file is not found)
    //Pre-Conditions: A level of a user so ArrayLists can be build
    //Post-Conditions: storeQuestions() is called storing all Q/A's
    //Returns: a random question depending how many Q/A's are in txt file
    public String randomQuestion(int level) throws FileNotFoundException {
    Random rand = new Random();
    int index = 0;
        if(level < 10){
            storeQuestions("docs/level1.txt");
            index = rand.nextInt(Qlist.size());
            return Qlist.get(index);
        }
        else if(level >= 10 && level < 20){
            storeQuestions("docs/level2.txt");
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
    //Parameters: String file: name of txt file being read
    //Throws: FileNotFoundException (if file is not found)
    //Pre-Conditions:a File that exists and can be read
    //Post-Condtions: ArrayList Qlist holds all question for users level
    //                ArrayList Alist holds all answers for users level
    //Essentially: Methods reads in a text file and stores all questions
    //and answers in arraylist, but doesent store one without the other...
    //All questions have the same index as their answer
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
    
    //Parameter: String q: String question
    //Returns: A answer to which have the same indexOf(q)
    public String getAnswer(String q){
        int temp = 0;
        temp = Qlist.indexOf(q);
        return Alist.get(temp);   
    }
    
    //Parameter: String question: questions...
    //           String answer:   answer...
    //Returns: True: if there is both a question, and a answer != ""
    //         False: otherwise
    public boolean checkquestion(String question, String answer){
        if(question != "" && answer != "")
            return true;
        return false;
    }
}    
        
