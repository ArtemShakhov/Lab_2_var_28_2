package com.company;

import com.company.State;

import javax.xml.bind.JAXB;
import java.io.File;

public class StateManager {
    private static final File file = new File("state.xml");

    private StateManager() {
    }

    public static State getState() {
        return JAXB.unmarshal(file, State.class);
    }

    public static void setState(State state) {
        JAXB.marshal(state, file);
    }

}
