package Repo;

import models.Partij;
import utils.StateManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("ALL")
public class PartijObjectRepository implements Repository<Partij> {

    private File objectenBestand = new File("out/partijen.dat");
    private ArrayList<Partij> partijen;

    public PartijObjectRepository() {
        boolean ingeladenMetResultaat = load();
        System.out.println("partij load: " + load());
        if (!ingeladenMetResultaat) {
            StateManager.laatLabelZien("Er zijn nog geen partijen om in te laden");
            partijen = new ArrayList<>();
        }
    }

    @Override
    public ArrayList<Partij> getAll() {
        return partijen;
    }

    @Override
    public void add(Partij partij) {
        partijen.add(partij);

    }

    @Override
    public void update(Partij partij) {
        //niet nodig
    }

    @Override
    public void remove(Partij partij) {
        partijen.remove(partij);

    }


    @Override
    public boolean load() {
        boolean waarde = true;
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(objectenBestand));
            partijen = (ArrayList<Partij>) input.readObject();
            // partijen = new ArrayList<> (Arrays.asList((Partij[])input.readObject()));
        } catch (Exception e) {
            waarde = false;
            System.out.println(e.toString());
        }
        return waarde;

    }


    @Override
    public boolean save() {
        boolean waarde = true;
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(objectenBestand));
            output.writeObject(partijen);
        } catch (IOException e) {
            waarde = false;
            e.printStackTrace();
        }
        return waarde;
    }

}
