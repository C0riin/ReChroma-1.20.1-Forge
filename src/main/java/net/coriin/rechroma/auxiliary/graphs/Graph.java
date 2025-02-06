package net.coriin.rechroma.auxiliary.graphs;

import java.util.ArrayList;

public class Graph {

    private ArrayList<GraphUnit> net = new ArrayList<>();

    public void Append(GraphUnit gu){
        net.add(gu);
        for(GraphUnit gu_el : gu.connected)
        {
            if(!net.contains(gu_el))
            {
                Append(gu_el);
            }
        }
    }
    public void Remove(GraphUnit gu){
        net.remove(gu);
        for(GraphUnit gu_el : gu.connected){
            gu_el.connected.remove(gu);
        }
    }

}
