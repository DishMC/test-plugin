package net.ouja.dish.events;

import net.ouja.api.event.EventHandler;
import net.ouja.api.event.EventListener;
import net.ouja.api.event.world.level.LootGenerateEvent;
import net.ouja.api.world.item.ItemStack;

public class LootGeneratedEvent implements EventListener {
    @EventHandler
    public void onLootGenerated(LootGenerateEvent event) {
        for (ItemStack item : event.getItems()) {
            System.out.println(item.getDisplayName().getString());
        }
    }
}
