package net.ouja.dish.events;

import net.ouja.api.event.EventHandler;
import net.ouja.api.event.EventListener;
import net.ouja.api.event.player.PlayerDeathEvent;
import net.ouja.api.network.chat.Component;

public class DeathEvent implements EventListener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        System.out.println(event.getDeathMessage().getString());
        System.out.println(event.getPlayer().lastDeathLocation());
        event.setDeathMessage(Component.literal("Died of Testing!"));
    }
}
