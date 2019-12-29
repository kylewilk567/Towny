package com.palmergames.bukkit.towny.object;

import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class TownBlockOwner extends TownyObject implements Econable{
	protected transient List<TownBlock> townBlocks = new ArrayList<>();
	protected TownyPermission permissions = new TownyPermission();
	
	protected TownBlockOwner(UUID identifier) {
		super(identifier);
	}
	
	public void setTownblocks(List<TownBlock> townblocks) {

		this.townBlocks = townblocks;
	}

	public List<TownBlock> getTownBlocks() {

		return townBlocks;
	}

	public boolean hasTownBlock(TownBlock townBlock) {

		return townBlocks.contains(townBlock);
	}

	public void addTownBlock(TownBlock townBlock) throws AlreadyRegisteredException {

		if (hasTownBlock(townBlock))
			throw new AlreadyRegisteredException();
		else
			townBlocks.add(townBlock);
	}

	public void removeTownBlock(TownBlock townBlock) throws NotRegisteredException {

		if (!hasTownBlock(townBlock))
			throw new NotRegisteredException();
		else
			townBlocks.remove(townBlock);
	}

	public void setPermissions(String line) {

		//permissions.reset(); not needed, already done in permissions.load()
		permissions.load(line);
	}

	public TownyPermission getPermissions() {

		return permissions;
	}
	
	@Override
	public abstract World getBukkitWorld();
	
	@Override
	public abstract String getEconomyName();
}
