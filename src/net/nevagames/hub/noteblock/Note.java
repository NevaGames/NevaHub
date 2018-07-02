/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.noteblock;

public class Note {

    private byte instrument;
    private byte key;

    public Note(byte instrument, byte key) {
        this.instrument = instrument;
        this.key = key;
    }

    public byte getInstrument() {
        return instrument;
    }

    public void setInstrument(byte instrument) {
        this.instrument = instrument;
    }

    public byte getKey() {
        return key;
    }

    public void setKey(byte key) {
        this.key = key;
    }
}
