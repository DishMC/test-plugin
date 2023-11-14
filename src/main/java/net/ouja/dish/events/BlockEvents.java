package net.ouja.dish.events;

import net.ouja.api.event.EventHandler;
import net.ouja.api.event.EventListener;
import net.ouja.api.event.player.PlayerBreakBlockEvent;
import net.ouja.api.network.chat.Component;
import net.ouja.api.world.level.block.Block;
import net.ouja.api.world.level.block.BlockPos;
import net.ouja.api.world.level.block.ButtonBlock;
import net.ouja.api.world.level.block.Sign;

public class BlockEvents implements EventListener {
    @EventHandler
    public void onPlayerBreakBlockEvent(PlayerBreakBlockEvent event) {
        Block block = event.block();
        BlockPos blockPos = block.getBlockPos();
        String blockPosString = String.format("%s[x: %s, y: %s, z: %s]", blockPos.getLevel().getName(), blockPos.getX(), blockPos.getY(), blockPos.getZ());
        System.out.println(event.getPlayer().getName() + " broke a block!");
        System.out.printf("The block was \"%s\" and it was located at %s%n", block.getType().getBlockName(), blockPosString);
        if (block instanceof Sign sign) {
            System.out.println("This block is a sign!");
            for (Component component : sign.getFrontText().getText(false)) {
                System.out.println(component.getString());
            }
            sign.getFrontText().setMessage(0, Component.literal("Test!"));
        }

        if (block instanceof ButtonBlock button) {
            System.out.printf("The button's signal is %s%n", button.getSignal());
        }
    }
}
