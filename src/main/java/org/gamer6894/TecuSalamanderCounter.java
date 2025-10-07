package org.gamer6894;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Item;
import net.runelite.api.ItemID;
import net.runelite.api.ItemContainer;
import net.runelite.api.InventoryID;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;



@Slf4j
@PluginDescriptor(
	name = "Tecu Salamander Counter"
)
public class TecuSalamanderCounter extends Plugin
{
    private int caughtAmount;
    private int yourDropProgress;
    private int yourXpProgress;
    private int matureCaught;
    public boolean runcheck = false;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private TecuSalamanderOverlay overlay;

    @Inject
    private Client client;

    @Inject
    private ConfigManager configManager;

	@Override
	protected void startUp(){
		overlayManager.add(overlay);
	}

    @Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}

    private static final int TECU_ID = 12769;
    private static final int TECU_item_ID = 28834;
    private static final int TECU_immature_ID = 28831;

    @Subscribe
    public void onGameObjectDespawned(GameObjectDespawned event)
    {
        if (event.getGameObject().getId() == 50717){
            runcheck = true;
            //client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Collected salamander", null);

        }
    }
    @Subscribe
    public void onItemContainerChanged(ItemContainerChanged event) {
        if (event.getContainerId() == InventoryID.INVENTORY.getId()) {
            checkInventoryForTecu(event.getItemContainer());
            //client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "inventory changed", null);
                }
    }
   private void checkInventoryForTecu(ItemContainer inventory){
        for (Item item : inventory.getItems()) {
            if (runcheck == true) {
                if (item.getId() == 28831) {
                    caughtAmount++;
                    // The overlay will display this count
                    yourDropProgress++;
                    yourXpProgress = caughtAmount * 344;
                    client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "It was just a baby", null);
                    runcheck = false;
                    savingCounters();
                } else if (item.getId() == 28834) {
                    caughtAmount++;
                    // The overlay will display this count
                    yourDropProgress++;
                    yourXpProgress = caughtAmount * 344;
                    matureCaught++;
                    client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "You caught the real deal", null);
                    runcheck = false;
                    savingCounters();
                }
            }
        }
        }
        private void savingCounters(){
        configManager.setConfiguration("myplugin", "caughtCount",  caughtAmount);
        configManager.setConfiguration("myplugin", "xpGained",  yourXpProgress);
        configManager.setConfiguration("myplugin", "matureProgress",  yourDropProgress);
        configManager.setConfiguration("myplugin", "matureSuccess",  matureCaught);
    }

    public int getSuccessfulCatches(){
        return caughtAmount;
        }
    public int caughtVsDropRate()
        {
            return yourDropProgress;
        }

    public int xpProgress()
        {
            return yourXpProgress;
        }
    public int matureCaughtCount()
    {
        return matureCaught;
    }

	@Provides
	ExampleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExampleConfig.class);
	}
}
