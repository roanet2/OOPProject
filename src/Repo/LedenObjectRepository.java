package Repo;

import models.Lid;
import utils.StateManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("ALL")
public class LedenObjectRepository implements Repository<Lid> {

    private File objectenBestand = new File("out/leden.dat");
    private ArrayList<Lid> leden;

    public LedenObjectRepository() {
        boolean ingeladenMetResultaat = load();
        System.out.println("Leden load: " + load());
        if (!ingeladenMetResultaat) {
            StateManager.laatLabelZien("Er zijn nog geen leden om in te laden");
            leden = new ArrayList<>();
        }
    }


    @Override
    public ArrayList<Lid> getAll() {
        return leden;
    }

    @Override
    public void add(Lid lid) {
        leden.add(lid);

    }

    @Override
    public void update(Lid lid) {

    }

    @Override
    public void remove(Lid lid) {
        leden.remove(lid);

    }


    @Override
    public boolean load() {
        boolean waarde = true;

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(objectenBestand));
            leden = (ArrayList<Lid>) input.readObject();
            //leden = new ArrayList<> (Arrays.asList((Lid[])input.readObject()));

        } catch (IOException e) {
            waarde = false;
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            waarde = false;
        }

        return waarde;

    }

    @Override
    public boolean save() {
        boolean waarde = true;

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(objectenBestand));
            output.writeObject(leden);
        } catch (IOException e) {
            waarde = false;
            e.printStackTrace();

        }
        return waarde;

    }
}
