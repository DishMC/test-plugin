package net.ouja.dish.events;

import net.ouja.api.Entity;
import net.ouja.api.Player;
import net.ouja.api.event.EventHandler;
import net.ouja.api.event.EventListener;
import net.ouja.api.event.entity.EntityDeathEvent;
import net.ouja.api.event.player.PlayerDeathEvent;
import net.ouja.api.network.chat.Component;

public class DeathEvent implements EventListener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(Component.literal("Died of Testing!"));
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getDamageSource().getEntity();
        if (entity instanceof Player player) {
            // this is called only when a player killed an entity
            System.out.println(String.format("%s killed %s", player.getName(), event.getEntity().getType().getName()));
        }
    }
}
