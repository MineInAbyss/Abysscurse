package de.miirpati.abysscurse.events.effects;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;


public class PotionEffect extends AbstractEffect {

    /*private static final List<PotionEffectType> noMerge;

    static {
        noMerge = Arrays.asList(
                PotionEffectType.BLINDNESS,
                PotionEffectType.CONFUSION,
                PotionEffectType.FIRE_RESISTANCE,
                PotionEffectType.GLOWING,
                PotionEffectType.INVISIBILITY,
                PotionEffectType.NIGHT_VISION,
                PotionEffectType.WATER_BREATHING,
                PotionEffectType.POISON);
    }

    private void mergeAddPotionEffect(Player player, PotionEffectType PotionEffect, int newEffectDuration, int newStrength) {
        if (player.getPotionEffect(PotionEffect).getDuration() < newEffectDuration) {
            int oldEffectDuration = player.getPotionEffect(PotionEffect).getDuration();
            int oldEffectStrength = player.getPotionEffect(PotionEffect).getAmplifier();
            PotionEffect firstAddPotionEffect = new PotionEffect(PotionEffect, oldEffectDuration, player.getPotionEffect(PotionEffect).getAmplifier() + newStrength);
            player.removePotionEffect(PotionEffect);
            player.addPotionEffect(firstAddPotionEffect);
            Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(MineInAbyss.class), () -> {
                if (player.getPotionEffect(PotionEffect) != null) {
                    mergeAddPotionEffect(player, PotionEffect, newEffectDuration - oldEffectDuration, newStrength);
                } else {
                    player.addPotionEffect(new PotionEffect(PotionEffect, newEffectDuration - oldEffectDuration, newStrength));
                }
                }, (oldEffectDuration + 1));
        } else if (player.getPotionEffect(PotionEffect).getDuration() > newEffectDuration) {
            int oldEffectDuration = player.getPotionEffect(PotionEffect).getDuration();
            int oldEffectStrength = player.getPotionEffect(PotionEffect).getAmplifier();
            PotionEffect firstAddPotionEffect = new PotionEffect(PotionEffect, newEffectDuration, oldEffectStrength + newStrength);
            player.removePotionEffect(PotionEffect);
            player.addPotionEffect(firstAddPotionEffect);
            Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(MineInAbyss.class), () -> {
                if (player.getPotionEffect(PotionEffect) != null) {
                    mergeAddPotionEffect(player, PotionEffect,  oldEffectDuration - newEffectDuration, oldEffectStrength);
                } else {
                    player.addPotionEffect(new PotionEffect(PotionEffect, oldEffectDuration - newEffectDuration, oldEffectStrength));
                }
            }, (newEffectDuration + 1));
        } else {
            PotionEffect mergedAddPotionEffect =  new PotionEffect(PotionEffect, newEffectDuration, player.getPotionEffect(PotionEffect).getAmplifier() + newStrength);
            player.removePotionEffect(PotionEffect);
            player.addPotionEffect(mergedAddPotionEffect);
        }
    }*///TODO: integrate potion effect merging by power
    //that code is actually from an old version


    LivingEntity living;
    private List<PotionEffectType> effectsApply;

    public PotionEffect(int delay, int power, long time, int iterations, double probability, ArrayList<PotionEffectType> effects) {
        super(delay, power, time, iterations, probability);
        this.effectsApply = effects;
        if (entity instanceof LivingEntity) {       //cast Entity to LivingEntity for the effect to be able to apply
            this.living = (LivingEntity) entity;
        } else {
            Bukkit.getConsoleSender().sendMessage("Warning: PotionEffect Entity is not LivingEntity, ignored.");
        }
    }


    public void effect() {
        for (PotionEffectType e : effectsApply) {
            if (living.getPotionEffect(e) != null) { //prevent disappearance of effects because of
                mergeExtendPotionEffect(e, tRemaining);
            } else {
                living.addPotionEffect(new org.bukkit.potion.PotionEffect(e, (int) tRemaining, power));
            }
        }

    }

    private void mergeExtendPotionEffect(PotionEffectType potionEffect, long newEffectDuration) { //essentially adding the length
        org.bukkit.potion.PotionEffect extendedPotionEffect = new org.bukkit.potion.PotionEffect(potionEffect, (int) newEffectDuration + living.getPotionEffect(potionEffect).getDuration() + 10, power);
        living.removePotionEffect(potionEffect);
        living.addPotionEffect(extendedPotionEffect);
    }
}
