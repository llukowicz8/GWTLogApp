package app.gwt.sample.server;


import java.util.ArrayList;
import java.util.List;

public class Repository {

    private List<String> elements = new ArrayList<>();

    private static Repository repo;
    private Repository(){}

    public static Repository getInstance(){
        if (repo == null){
            synchronized(Repository.class){
                if (repo == null){
                    repo = new Repository();//instance will be created at request time
                }
            }
        }
        return repo;
    }

    public void addElement(String newElement){
        elements.add(newElement);
    }

    public void deleteContent(){
        elements.clear();
    }
    public List<String>getElements(){
        return  elements;
    }

}


