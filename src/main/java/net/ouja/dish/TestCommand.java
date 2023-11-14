package net.ouja.dish;

import net.ouja.api.Player;
import net.ouja.api.commands.Command;
import net.ouja.api.commands.CommandListener;
import net.ouja.api.network.chat.Component;

public class TestCommand implements Command {

    @Override
    @CommandListener(name = "test")
    public boolean command(Player player) {
        if (player.isConsole()) {
            System.out.println("Can't run this command as console");
            return false;
        }

        System.out.println(player.getLevel().getName());
        System.out.println(player.getLevel().getSeed());
        System.out.println(player.getLevel().getPlayers().size());
        System.out.println(String.format("%s (%s) ran the test command", player.getName(), player.getUUID()));

        Component component = Component.literal("Yo! This is a test message from the test plugin.").setColor("#e3256b").setUnderlined(true).setBold(true);
        Component strickthroughMessage = Component.literal("This is a message with a line through it!").setStrikeThrough(true).setItalic(true);
        System.out.println("Sending player message: " + component.getString());
        player.sendMessage(component);
        player.sendMessage(strickthroughMessage);

        TestPlugin.server.broadcast(Component.literal("Testing broadcast").setItalic(true));
        return true;
    }

    @CommandListener(name = "errorCommand")
    public boolean errorCommand(Player player) throws Exception {
        throw new Exception("This is a test exception");
    }
}
