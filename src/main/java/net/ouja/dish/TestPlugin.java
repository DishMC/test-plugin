package net.ouja.dish;

import net.ouja.api.Server;
import net.ouja.api.entity.EntityTypes;
import net.ouja.api.entity.monster.*;
import net.ouja.api.entity.neutral.Goat;
import net.ouja.api.entity.neutral.Panda;
import net.ouja.api.entity.neutral.Wolf;
import net.ouja.api.entity.passive.*;
import net.ouja.api.event.EventHandler;
import net.ouja.api.event.EventListener;
import net.ouja.api.event.player.PlayerAttackEvent;
import net.ouja.api.event.player.PlayerChatEvent;
import net.ouja.api.event.player.PlayerJoinEvent;
import net.ouja.api.event.player.PlayerLoginEvent;
import net.ouja.api.event.player.PlayerMoveEvent;
import net.ouja.api.event.player.PlayerQuitEvent;
import net.ouja.api.event.vehicle.VehicleMoveEvent;
import net.ouja.api.event.world.level.chunk.StructureGenerateEvent;
import net.ouja.api.network.chat.Component;
import net.ouja.api.plugin.JavaPlugin;
import net.ouja.api.server.players.BanEntry;
import net.ouja.api.world.level.chunk.StructureTypes;
import net.ouja.dish.events.BreakBlockEvent;

public class TestPlugin extends JavaPlugin implements EventListener {
    public static Server server;
    public static boolean cancelMovement = false;

    @Override
    public void onEnable() {
//        getLogger().info("[TestPlugin] Running on dish version: " + getServer().getDishVersion());
        getServer().registerEvent(this, this.getClass());
        getServer().registerEvent(new BreakBlockEvent(), BreakBlockEvent.class);
        getServer().registerCommand(new TestCommand());
        getServer().registerCommand(new CancelMovementCommand());
        server = getServer();
    }

    @Override
    public void onDisable() {
//        getLogger().info("[TestPlugin] Disabling plugin");
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        boolean isBanned = getServer().isPlayerBanned(event.getProfile());
        BanEntry banEntry = getServer().getBanEntry(event.getProfile());
        if (isBanned) {
            System.out.printf("%s was banned by %s for the reason '%s'%n", event.getProfile().getPlayerName(), banEntry.getSource(), banEntry.getReason());
            System.out.println("The ban expires at " + banEntry.getExpires());
        }
        if (event.getErrorMessage() != null) {
            System.out.println("Player errorMessage: " + event.getErrorMessage().getString());
            event.setErrorMessage(Component.literal("This is a test component for disconnecting the player.").setColor("#9c0000").setUnderlined(true));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        System.out.println("A player joined!");
        event.setJoinMessage(Component.literal(String.format("%s joined!", event.getPlayer().getName())).setColor("#7b68aa"));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        System.out.println("A player left!");
        event.setQuitMessage(Component.literal(String.format("%s quit", event.getPlayer().getName())).setColor("#7b68aa"));
    }

    @EventHandler
    public void onMessage(PlayerChatEvent event) {
        System.out.println(event.getPlayer().getName() + " " + event.getMessage().getString());
        event.setCancel(event.getMessage().getString().equals("Cancel me!"));
    }

    @EventHandler
    public void onAttack(PlayerAttackEvent event) {
        if (event.getEntity().getType() == EntityTypes.CAT) {
            Cat cat = (Cat)event.getEntity();
            cat.hiss();
            cat.setLaying(!cat.isLaying());
            event.setCancel(true);
        }
        if (event.getEntity().getType() == EntityTypes.CAMEL) {
            Camel camel = (Camel)event.getEntity();
            if (camel.isSitting()) {
                camel.standUp();
            } else {
                camel.sitDown();
            }
        }
        if (event.getEntity().getType() == EntityTypes.HORSE) {
            Horse horse = (Horse)event.getEntity();
            System.out.println(horse.getMarkings());
            System.out.println(horse.getVariant());
        }
        if (event.getEntity().getType() == EntityTypes.PIG) {
            Pig pig = (Pig)event.getEntity();
            System.out.println(pig.canSaddle());
            System.out.println(pig.isSaddled());
            pig.equipSaddle();
        }
        if (event.getEntity().getType() == EntityTypes.SQUID || event.getEntity().getType() == EntityTypes.GLOW_SQUID) {
            Squid squid = (Squid)event.getEntity();
            squid.ink();
        }
        if (event.getEntity().getType() == EntityTypes.TROPICAL_FISH) {
            TropicalFish fish = (TropicalFish)event.getEntity();
            System.out.println(fish.getVariant().getName());
            System.out.println(fish.getVariant().getSize());
            System.out.println(fish.getVariant().getPackedId());
        }
        if (event.getEntity().getType() == EntityTypes.GOAT) {
            Goat goat = (Goat)event.getEntity();
            System.out.println(goat.hasLeftHorn());
            System.out.println(goat.hasRightHorn());
            goat.addHorns();
            goat.removeHorns();
        }
        if (event.getEntity().getType() == EntityTypes.PANDA) {
            Panda panda = (Panda)event.getEntity();
            System.out.println(panda.isSitting());
            System.out.println(panda.isOnBack());
            panda.sneeze(true);
        }
        if (event.getEntity().getType() == EntityTypes.WOLF) {
            Wolf wolf = (Wolf)event.getEntity();
            System.out.println(wolf.isWet());
            System.out.println(wolf.getTailAngle());
            System.out.println(wolf.isInterested());
        }
        if (event.getEntity().getType() == EntityTypes.CREEPER) {
            Creeper creeper = (Creeper)event.getEntity();
            creeper.ignite();
        }
        if (event.getEntity().getType() == EntityTypes.GHAST) {
            Ghast ghast = (Ghast)event.getEntity();
            ghast.setCharged(true);
        }
        if (event.getEntity().getType() == EntityTypes.HOGLIN) {
            Hoglin hoglin = (Hoglin)event.getEntity();
            System.out.println(hoglin.isConverting());
        }
        if (event.getEntity().getType() == EntityTypes.RAVAGER) {
            Ravager ravager = (Ravager)event.getEntity();
        }
        if (event.getEntity().getType() == EntityTypes.SHULKER) {
            Shulker shulker = (Shulker)event.getEntity();
            System.out.println(shulker.isClosed());
        }
        if (event.getEntity().getType() == EntityTypes.VEX) {
            Vex vex = (Vex)event.getEntity();
            if (vex.getOwner() != null) {
                System.out.println(vex.getOwner());
            }
        }
        if (event.getEntity().getType() == EntityTypes.ZOMBIE) {
            Zombie zombie = (Zombie)event.getEntity();
            System.out.println(zombie.canBreakDoors());
        }
        if (event.getEntity().getType() == EntityTypes.ZOMBIE_VILLAGER) {
            ZombieVillager zombie = (ZombieVillager)event.getEntity();
            System.out.println(zombie.canBreakDoors());
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        event.setCancel(cancelMovement);
    }

    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {
        event.setCancel(cancelMovement);
    }

    @EventHandler
    public void onStructureGenerated(StructureGenerateEvent event) {
        if (event.getStructure().getType() == StructureTypes.MINESHAFT) {
            System.out.printf("%s was generated%n", event.getStructure().getType().getName());
            System.out.printf("%s is located at x: %s y:%s%n", event.getStructure().getType().getName(), event.getChunkPos().getBlockX(), event.getChunkPos().getBlockZ());
            event.setCancel(true);
        }
    }
}