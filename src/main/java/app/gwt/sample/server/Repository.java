package app.gwt.sample.server;


import app.gwt.sample.shared.Log;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private List<Log> elements = new ArrayList<>();

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

    public void addElement(Log newElement){
        elements.add(newElement);
    }

    public void deleteContent(){
        elements.clear();
    }
    public List<Log>getElements(){
        return  elements;
    }

}


