/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.games;

import net.nevagames.hub.Hub;
import net.nevagames.hub.games.types.*;

public class GamesTypeManager {

    private DimensionsGame dimensionsGame;
    private QuakeGame quakeGame;
    private UppervoidGame uppervoidGame;
    private BackEndGame backEndGame;
    private TNTZoneGame tntZoneGame;
    private Hub hub;

    /**
     * The main class of this plugin.
     * @param hub - The hub.
     */

    public GamesTypeManager(Hub hub) {
        this.hub = hub;
        this.dimensionsGame = new DimensionsGame(hub);
        this.quakeGame = new QuakeGame (hub);
        this.uppervoidGame = new UppervoidGame (hub);
        this.backEndGame = new BackEndGame (hub);
        this.tntZoneGame = new TNTZoneGame (hub);
    }

    /**
     * Get the DimensionGame class.
     * @return dimensionsGame.
     */

    public DimensionsGame getDimensionsGame() {
        return dimensionsGame;
    }

    /**
     * Get the QuakeGame class.
     * @return quakeGame.
     */

    public QuakeGame getQuakeGame() {
        return quakeGame;
    }

    /**
     * Get the UppervoidGame class.
     * @return uppervoidGame.
     */

    public UppervoidGame getUppervoidGame() {
        return uppervoidGame;
    }

    /**
     * Get the BackEndGame class.
     * @return backEndGame.
     */

    public BackEndGame getBackEndGame() {
        return backEndGame;
    }

    /**
     * Get the TntZoneGame class.
     * @return tntZoneGame.
     */

    public TNTZoneGame getTntZoneGame() {
        return tntZoneGame;
    }

    /**
     * Get the main class of this plugin.
     * @return hub.
     */

    public Hub getHub() {
        return hub;
    }
}
