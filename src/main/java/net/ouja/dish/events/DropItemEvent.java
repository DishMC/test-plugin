package net.ouja.dish.events;

import net.ouja.api.Player;
import net.ouja.api.event.EventHandler;
import net.ouja.api.event.EventListener;
import net.ouja.api.event.player.PlayDropItemEvent;

public class DropItemEvent implements EventListener {
    @EventHandler
    public void onPlayerDropItem(PlayDropItemEvent event) {
        Player player = event.getPlayer();
        System.out.printf("%s dropped %s%n", player.getName(), event.getItemStack().getDisplayName().getString());
        event.setPickUpDelay(120);
    }
}
