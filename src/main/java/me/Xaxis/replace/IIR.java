package me.Xaxis.replace;

import me.Xaxis.replace.File.BannedItems;
import me.Xaxis.replace.File.LogFile;
import me.Xaxis.replace.Listener.onInventoryClick;
import me.Xaxis.replace.Listener.onInventoryOpen;
import me.Xaxis.replace.Manager.BannedItemManager;
import me.Xaxis.replace.commands.ReplaceCommand;
import me.Xaxis.replace.commands.SetPanicChestCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class IIR extends JavaPlugin {

    BannedItems itemsFile;
    LogFile logFile;
    BannedItemManager bannedItems;

    @Override
    public void onEnable() {

        logFile = new LogFile(this);
        itemsFile = new BannedItems(this);
        bannedItems = new BannedItemManager(itemsFile);
        itemsFile.run(this, "ItemData");
        bannedItems.loadItems();
        logFile.create();

        getCommand("replaceItem").setExecutor(new ReplaceCommand(this, itemsFile));
        getCommand("setPanicChestLocation").setExecutor(new SetPanicChestCommand(this));
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new onInventoryClick(this, bannedItems), this);
        getServer().getPluginManager().registerEvents(new onInventoryOpen(this, bannedItems, logFile), this);


    }
}
