package konak_alg2_sem_autoskola;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Method contains logic parts of code
 * @author Martin Koňák
 */
public class ProgramLogic{
    ArrayList<Question> questions = new ArrayList<Question>();
    ArrayList<Question> easyQuestions = new ArrayList<Question>();
    ArrayList<Question> mediumQuestions = new ArrayList<Question>();
    ArrayList<Question> hardQuestions = new ArrayList<Question>();
    ArrayList<Question> healthQuestions = new ArrayList<Question>();
    ArrayList<Result> results = new ArrayList<Result>();
    
    /**
     * Method load questions from text file into ArrayList
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void loadQuestions(String s1, String s2) throws FileNotFoundException, IOException{
        File quests = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + s1);
        Scanner loadQuestions = new Scanner(quests);
        while(loadQuestions.hasNext()){
            String q = loadQuestions.nextLine();
            String a1 = loadQuestions.nextLine();
            String a2 = loadQuestions.nextLine();
            String a3 = loadQuestions.nextLine();
            Question qe = new Question(q, a1, a2, a3);
            switch(s2){
                case "e":
                    easyQuestions.add(qe);
                    break;
                case "m":
                    mediumQuestions.add(qe);
                    break;
                case "h":
                    hardQuestions.add(qe);
                    break;
                case "H":
                    healthQuestions.add(qe);
                    break;
            }
        }
    }
    
    /**
     * Method picks one question at random
     * @param s - selects which type of question will be select
     * @return q - Object type Question
     */
    public Question selectQ(String s){
        Question q = null;
        int rnd;
        switch(s){
            case "e":
                rnd = (int)(Math.random()*(easyQuestions.size()));
                q = easyQuestions.get(rnd);
                easyQuestions.remove(rnd);
                break;
            case "m":
                rnd = (int)(Math.random()*(mediumQuestions.size()));
                q = mediumQuestions.get(rnd);
                mediumQuestions.remove(rnd);
                break;
            case "h":
                rnd = (int)(Math.random()*(hardQuestions.size()));
                q = hardQuestions.get(rnd);
                hardQuestions.remove(rnd);
                break;
            case "H":
                rnd = (int)(Math.random()*(healthQuestions.size()));
                q = healthQuestions.get(rnd);
                healthQuestions.remove(rnd);
                break;
        }
        return q;
    }
    
    /**
     * Method loads results form text file into ArrayList
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void loadResults() throws FileNotFoundException, IOException{
        File user = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "results.txt");
        Scanner loadResult = new Scanner(user);
        while(loadResult.hasNext()){
            String line = loadResult.nextLine();
            String[] r = line.split(" +");
            Result u = new Result(r[1], Integer.parseInt(r[3]), r[5]);
            results.add(u);
        }
    }
    
    /**
     * Mathod transforms ArrayList of Results into String
     * @param results - concrete ArrayList you want to transform
     * @return String line of results
     */
    public String resultsToString(ArrayList<Result> results){
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < results.size(); i++) {
            sb.append(results.get(i) + "\n");
        }        
        return sb.toString();
    }
    
    /**
     * Method saves Result into text file
     * @param s - Result in String
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveResult(String s) throws FileNotFoundException, IOException{
        try{
            FileWriter writer = new FileWriter(System.getProperty("user.dir") + File.separator + "data" + File.separator + "results.txt");
            PrintWriter printResult = new PrintWriter(writer);
            printResult.print(s);
            printResult.close();
        }catch(Exception e){
            throw new IOException("An error occured");
        }
        
    } 
    
    /**
     * Getter to results
     * @return results in ArrayList
     */
    public ArrayList<Result> getResults() {
        return results;
    }
    
    /**
     * This method clears results ArrayList
     */
    public void clearResults(){
        results = new ArrayList<Result>();
    }          
    
    /**
     * Method returns sorted results
     * @return sorted ArrayList of results
     * @throws IOException 
     */
    public ArrayList<Result> getComparedResults() throws IOException {
        loadResults();
        Result[] res = new Result[results.size()];
        for (int i = 0; i < results.size(); i++) {
            res[i] = results.get(i);
        }
        sort(res);
        ArrayList<Result>comparedResults = new ArrayList<Result>();
        for (Result i : res) {
            comparedResults.add(i);
        }
        clearResults();
        return comparedResults;
    }
    
    /**
     * Sorting method (bubble sort concretly)
     * @param array - array of objects
     */
    public void sort(ComparingInterface[] array)
        {for (int i = 0; i < array.length-1; i++) {
            for (int j = 1; j < array.length-i; j++) {
                if (array[j-1].isBigger(array[j])) {
                    ComparingInterface temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
        }
    }
}
