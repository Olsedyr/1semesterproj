package worldOfZuul;

import java.util.ArrayList;
import java.util.Arrays;

class Objs extends GameObjs {

    Objs(String name, String gameObjDescription, boolean state, boolean pickable){
        super(name,gameObjDescription,state,pickable);
    }
        ///This is just a simple version, needs update
        public static ArrayList<String> objsArrayList = new ArrayList<>(Arrays.asList("Light","Heater","Window","Computer"));

}
