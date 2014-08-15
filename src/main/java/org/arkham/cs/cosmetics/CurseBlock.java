package org.arkham.cs.cosmetics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.gui.Category;
import org.arkham.cs.interfaces.Button;
import org.arkham.cs.utils.PlayerMetaDataUtil;
import org.arkham.cs.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CurseBlock extends Button {

    private Rank rank;
    private static HashMap<UUID, CurseBlock> blocks = new HashMap<>();
    private static HashMap<Rank, List<CurseBlock>> blocksByRank = new HashMap<>();

    /**
     * 
     * @param slot
     * @param permission
     * @param item
     */
    public CurseBlock(int slot, String permission, ItemStack item, Rank rank){
        super(slot, Category.CURSE_BLOCKS, permission, item);
        this.rank = rank;
        List<CurseBlock> cbs = blocksByRank.get(rank);
        if(cbs == null){
            cbs = new ArrayList<>();
        }
        cbs.add(this);
        if(rank == Rank.SUPERHERO){
            cbs.addAll(blocksByRank.get(Rank.HERO));
        }
        blocksByRank.put(rank, cbs);
    }

    public CurseBlock(int i, ItemStack item, Rank rank, String permission){
        this(i, permission, item, rank);
    }

    public Rank getRank(){
        return rank;
    }

    @Override
    public void onClick(Player player) {
        PlayerMetaDataUtil.removeFromSwitching(player);
        if(blocks.containsKey(player.getUniqueId()) && blocks.get(player.getUniqueId()).equals(this)){
            // Cancel effect...
            blocks.remove(player.getUniqueId());
            player.sendMessage(CosmeticSuite.PREFIX + "Your have disabled your block trail.");
        }  else {
            blocks.put(player.getUniqueId(), this);
            String item = getPermission().replace("cosmetics.cursedblocks.", "").replace("_", " ");
            player.sendMessage(CosmeticSuite.PREFIX + "Your new block trail is: " + ChatColor.UNDERLINE + item);
        }
        player.closeInventory();
    }

    public static CurseBlock get(Player player){
        return blocks.get(player.getUniqueId());
    }

    public static List<CurseBlock> getByRank(Rank rank){
        return blocksByRank.get(rank);
    }
}
