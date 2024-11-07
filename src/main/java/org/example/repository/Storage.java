package org.example.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage<T> implements Repository<T> {
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(T entity) {
        List<T> entities = this.load();
        if(entities == null) {
            entities = new ArrayList<>();
        }

        entities.add(entity);
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            System.out.println("There is no such file: "+e.toString());
        } catch (IOException e) {
            System.out.println("I/O error: "+e.toString());
        }
    }
    @Override
    public void saveAll(List<T> entities) {
        if(entities == null) {
            entities = new ArrayList<>();
        }

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            System.out.println("There is no such file: "+e.toString());
        } catch (IOException e) {
            System.out.println("I/O error: "+e.toString());
        }
    }

    @Override
    public List<T> load() {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<T>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("There is no such file: "+e.toString());
        } catch (IOException e) {
            System.out.println("I/O error: "+e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: "+e.toString());
        }
        return new ArrayList<T>();
    }
}