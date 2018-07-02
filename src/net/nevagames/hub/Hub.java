package net.nevagames.hub;

import net.nevagames.api.APIPlugin;
import net.nevagames.api.gui.AbstractGui;
import net.nevagames.hub.commands.admin.CommandAnguille;
import net.nevagames.hub.commands.admin.CommandEvacuate;
import net.nevagames.hub.commands.moderating.CommandSlow;
import net.nevagames.hub.commands.moderating.ShutupCommand;
import net.nevagames.hub.commands.players.FlyCommand;
import net.nevagames.hub.common.manager.EventBus;
import net.nevagames.hub.common.players.ChatManager;
import net.nevagames.hub.common.players.PlayerManagers;
import net.nevagames.hub.common.task.AdvertisingTask;
import net.nevagames.hub.common.task.RebootTask;
import net.nevagames.hub.common.task.TaskManagers;
import net.nevagames.hub.games.GamesTypeManager;
import net.nevagames.hub.gui.InventoryListeners;
import net.nevagames.hub.gui.cosmetics.GuiCometicsCategory;
import net.nevagames.hub.gui.cosmetics.GuiParticles;
import net.nevagames.hub.gui.main.GuiMain;
import net.nevagames.hub.gui.main.GuiSwitchHub;
import net.nevagames.hub.gui.profile.GuiClickMe;
import net.nevagames.hub.gui.profile.GuiClickMeSettings;
import net.nevagames.hub.gui.profile.GuiProfil;
import net.nevagames.hub.gui.profile.GuiStatistics;
import net.nevagames.hub.gui.profile.settings.GuiSettings;
import net.nevagames.hub.gui.profile.settings.GuiSettingsTwo;
import net.nevagames.hub.gui.shop.GuiShop;
import net.nevagames.hub.hydroangeas.queues.HydroangeasQueues;
import net.nevagames.hub.hydroangeas.queues.QueueListeners;
import net.nevagames.hub.hydroangeas.sign.SignUpdate;
import net.nevagames.hub.listeners.DeveloppeurListeners;
import net.nevagames.hub.listeners.PlayerConnection;
import net.nevagames.hub.listeners.PlayerListeners;
import net.nevagames.hub.listeners.protection.EntityEditionListener;
import net.nevagames.hub.listeners.protection.InventoryEditionListener;
import net.nevagames.hub.listeners.protection.PlayerProtectionListener;
import net.nevagames.hub.listeners.protection.WorldEditionListener;
import net.nevagames.hub.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Hub extends JavaPlugin {

    private static Hub instance;
    private World world;

    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;
    private ScoreboardManager scoreboardManager;

    private GamesTypeManager gamesTypeManager;
    private PlayerManagers playerManagers;

    private TaskManagers taskManagers;
    private ChatManager chatManager;
    private EventBus eventBus;

    public Map<Player, Integer> parcours = new HashMap<> ();

    private HashMap<String, HydroangeasQueues> queues = new HashMap<> ();

    private FlyCommand flyCommand = new FlyCommand (this);
    private CommandSlow commandSlow = new CommandSlow (this);
    private ShutupCommand shutupCommand = new ShutupCommand (this);
    private CommandAnguille commandAnguille = new CommandAnguille (this);
    private CommandEvacuate commandEvacuate = new CommandEvacuate(this);

    public Map<Class<? extends AbstractGui>, AbstractGui> registeredMenus = new HashMap<> ();

    @Override
    public void onEnable() {
        instance = this;

        this.scheduledExecutorService = Executors.newScheduledThreadPool(16);
        this.executorMonoThread = Executors.newScheduledThreadPool(1);

        this.setupWorld ();
        this.registersCommands ();
        this.registersListener ();
        //this.setupParkours();

        //this.effectManager = new EffectManager (this);
        this.eventBus = new EventBus ();
        this.playerManagers = new PlayerManagers (this);
        this.chatManager = new ChatManager (this);
        this.taskManagers = new TaskManagers (this);
        this.scoreboardManager = new ScoreboardManager ();
        this.gamesTypeManager = new GamesTypeManager(this);
        RebootTask rebootTask = new RebootTask ();
        rebootTask.runTaskTimer (this, 20, 20);
        APIPlugin.log ("Scheduled automatic reboot enable.");

        this.addMenu (new GuiMain (this));
        this.addMenu (new GuiProfil ());
        this.addMenu (new GuiShop ());
        this.addMenu (new GuiCometicsCategory ());
        this.addMenu (new GuiParticles ());
        this.addMenu (new GuiSwitchHub ());
        this.addMenu (new GuiStatistics ());
        this.addMenu (new GuiSettingsTwo ());
        this.addMenu (new GuiSettings ());
        this.addMenu (new GuiClickMeSettings ());

        //taskManagers.getCirclesTask().addCircleAt(new Location(Bukkit.getWorlds().get(0), 40.428, 101, -47.497));
        //taskManagers.getCirclesTask().addCircleAt(new Location(Bukkit.getWorlds().get(0), -113.677, 160, 47.560));

        super.onEnable ( );
    }

    @Override
    public void onDisable() {
        this.scoreboardManager.onDisable ();
        super.onDisable ( );
    }

    private void setupWorld(){
        this.world = this.getServer ().getWorlds ().get (0);
        this.world.setGameRuleValue("randomTickSpeed", "0");
        this.world.setGameRuleValue("doDaylightCycle", "false");
        this.world.setTime(this.getConfig().getLong("time", 6000L));
        new AdvertisingTask (this).run ();
    }

    private void registersListener(){

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents (new EntityEditionListener (this), this);
        pm.registerEvents (new InventoryEditionListener (this), this);
        pm.registerEvents (new PlayerProtectionListener (this), this);
        pm.registerEvents (new WorldEditionListener (this), this);
        pm.registerEvents (new DeveloppeurListeners (this), this);
        pm.registerEvents (new GuiClickMe (), this);
        pm.registerEvents (new PlayerConnection (this), this);
        pm.registerEvents (new PlayerListeners (this), this);
        pm.registerEvents (new InventoryListeners (), this);
        pm.registerEvents (new SignUpdate (), this);
        pm.registerEvents(new QueueListeners (), this);
    }

    private void registersCommands(){
        this.getCommand ("fly").setExecutor (flyCommand);
        this.getCommand ("slow").setExecutor (commandSlow);
        this.getCommand ("anguille").setExecutor (commandAnguille);
        this.getCommand ("shutup").setExecutor (shutupCommand);
        this.getCommand ("evacuate").setExecutor (commandEvacuate);
    }

    public World getWorld() {
        return world;
    }
    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }
    public ScheduledExecutorService getExecutorMonoThread() {
        return executorMonoThread;
    }
    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }
    public EventBus getEventBus() {
        return eventBus;
    }
    public TaskManagers getTaskManager() {
        return taskManagers;
    }
    public ChatManager getChatManager() {
        return chatManager;
    }
    public PlayerManagers getPlayerManagers() {
        return playerManagers;
    }
    public GamesTypeManager getGamesTypeManager() {
        return gamesTypeManager;
    }
    public HashMap<String, HydroangeasQueues> getQueues() {
        return queues;
    }
    public static Hub getHubInstance() {
        return instance;
    }
    private void addMenu(AbstractGui m){
        this.registeredMenus.put(m.getClass(), m);
    }

    public void open(Player player, Class<? extends AbstractGui> gClass){

        if(!this.registeredMenus.containsKey(gClass)) return;

        AbstractGui menu = this.registeredMenus.get(gClass);
        Inventory inv = Bukkit.createInventory(null, menu.getSize(), menu.name());
        menu.contents(player, inv);
        player.openInventory(inv);

    }
}
