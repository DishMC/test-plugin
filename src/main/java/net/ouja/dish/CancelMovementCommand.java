package net.ouja.dish;

import net.ouja.api.Player;
import net.ouja.api.commands.Command;
import net.ouja.api.commands.CommandListener;
import net.ouja.api.network.chat.Component;

public class CancelMovementCommand implements Command {
    @Override
    @CommandListener(name = "cancel_movement")
    public boolean command(Player player) {
        TestPlugin.cancelMovement = !TestPlugin.cancelMovement;
        TestPlugin.server.broadcast(Component.literal("Movement has been " + (TestPlugin.cancelMovement ? "disabled" : "enabled")));
        return true;
    }
}
