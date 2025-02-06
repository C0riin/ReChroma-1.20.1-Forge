package net.coriin.rechroma.auxiliary.graphs;

import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.Vector;

public class GraphUnit {

    ArrayList<GraphUnit> connected;
    Vector3i Pos;

    public GraphUnit(ArrayList<GraphUnit> conn){
        this.connected = conn;
    }

}
