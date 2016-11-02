package control;

import model.*;
import model.Character;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SessionData {

    private String adjectiveFile = "data/adjectives.txt";
    private String prepositionFile = "data/prepositions.txt";
    private String nounFile = "data/nouns.txt";
    private String femaleNameFile = "data/female_all.txt";
    private String maleNameFile = "data/male_all.txt";
    private String lastNameFile = "data/lastnames_all.txt";
    private String goodsFile = "data/goods_pathfinder.txt";

    private ArrayList<Character> characters;
    private ArrayList<Tavern> taverns;
    private ArrayList<Noun> nouns;
    private ArrayList<Preposition> prepositions;
    private ArrayList<Adjective> adjectives;
    private ArrayList<Good> goods;
    private HashMap<String, Good> goodsNameToGoods;
    
    private ArrayList<String> femaleNames;
    private ArrayList<String> maleNames;
    private ArrayList<String> lastNames;

    public Random randomer;

    public SessionData(){
        this.characters = new ArrayList<Character>();
        this.taverns = new ArrayList<Tavern>();
        this.goodsNameToGoods = new HashMap<>();
        this.nouns = new ArrayList<>();
        this.prepositions = new ArrayList<>();
        this.adjectives = new ArrayList<>();

        randomer = new Random();

        //read files
        ArrayList<String> nounStrings = readFile(nounFile);
        ArrayList<String> prepositionStrings = readFile(prepositionFile);
        ArrayList<String> adjectiveStrings = readFile(adjectiveFile);
        femaleNames = readFile(femaleNameFile);
        maleNames = readFile(maleNameFile);
        lastNames = readFile(lastNameFile);
        ArrayList<String> goodsStrings = readFile(goodsFile);

        this.adjectives = stringArrayToAdjectivesArray(adjectiveStrings);
        this.nouns = stringArrayToNounArray(nounStrings);
        this.prepositions = stringArrayToPrepositionArray(prepositionStrings);
        this.goods = stringArrayToGoodsArray(goodsStrings);
        this.goodsNameToGoods = goodsListToDictionary(goods);

        System.out.println("Application is ready!");

    }

    private HashMap<String, Good> goodsListToDictionary(ArrayList<Good> goods) {
        HashMap<String, Good> goodsDict = new HashMap<>();
        for (Good g :
                goods) {
            goodsDict.put(g.getName(), g);
        }
        return goodsDict;
    }

    private ArrayList<Good> stringArrayToGoodsArray(ArrayList<String> goodsStrings) {
        ArrayList<Good> goodsList = new ArrayList<>();

        for (String s :
                goodsStrings) {
            String[] split = s.split(";");
            Good newGood = new Good(split[1], split[0], split[3], split[2]);
            goodsList.add(newGood);
        }

        return goodsList;
    }

    private ArrayList<Preposition> stringArrayToPrepositionArray(ArrayList<String> prepositionStrings) {
        ArrayList<Preposition> preps = new ArrayList<>();

        for (String s :
                prepositionStrings) {
            Preposition newPrep = new Preposition(s);
            preps.add(newPrep);
        }
        return preps;
    }

    private ArrayList<Noun> stringArrayToNounArray(ArrayList<String> nounStrings) {
        ArrayList<Noun> nouns = new ArrayList<>();

        for (String s :
                nounStrings) {
            Noun newNoun = new Noun(s);
            nouns.add(newNoun);
        }
        return nouns;
    }

    private ArrayList<Adjective> stringArrayToAdjectivesArray(ArrayList<String> adjectiveStrings) {
        ArrayList<Adjective> adjectives = new ArrayList<>();

        for (String s :
                adjectiveStrings) {
            Adjective newAdj = new Adjective(s);
            adjectives.add(newAdj);
        }
        return adjectives;
    }

    public static ArrayList<String> readFile(String filePath) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String readLine = null;

            // While the BufferedReader readLine is not null
            while ((readLine = br.readLine()) != null) {
                if(!readLine.startsWith("//")){
                    list.add(readLine);
                }
            }
            // Close the BufferedReader
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Noun> getNouns() {
        return nouns;
    }

    public ArrayList<Preposition> getPrepositions() {
        return prepositions;
    }

    public ArrayList<Adjective> getAdjectives() {
        return adjectives;
    }

    public void addTavernToList(Tavern t) {
        this.taverns.add(t);
    }

    public String getRandomMaleName() {
        String first = maleNames.get(randomer.nextInt(maleNames.size()));
        String last = lastNames.get(randomer.nextInt(lastNames.size()));
        return first + " " + last + " (m)";
    }

    public String getRandomFemaleName() {
        String first = femaleNames.get(randomer.nextInt(femaleNames.size()));
        String last = lastNames.get(randomer.nextInt(lastNames.size()));
        return first + " " + last + " (w)";
    }

    public String getRandomName() {
        double d = randomer.nextDouble();
        if(d < 0.5){
            return getRandomFemaleName();
        }
        else{
            return getRandomMaleName();
        }
    }

    public Tavern getRandomTavern() {
        Noun n = nouns.get(randomer.nextInt(nouns.size()));
        Preposition p = prepositions.get(randomer.nextInt(prepositions.size()));
        Adjective a = adjectives.get(randomer.nextInt(adjectives.size()));

        String noun = n.toString();
        String ad = a.toString();
        String prep = "";

        if(n.getGenus() == 0){
            prep = p.getM();
        }

        if(n.getGenus() == 1){
            prep = p.getF();
        }

        if(n.getGenus() == 2){
            prep = p.getN();
        }

        Tavern t = new Tavern(prep + " " + ad + " " + noun);
        addTavernToList(t);
        return t;
    }

    public ArrayList<Good> getGoodsList() {
        return goods;
    }

    public HashMap<String, Good> getGoodsDict() {
        return goodsNameToGoods;
    }
}
