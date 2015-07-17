package de.ikt.prakt.model.blocks;

import de.ikt.prakt.model.Block;
import de.ikt.prakt.model.BlockParameter;
import de.ikt.prakt.model.BlockParameter.Type;

public class FB_Input extends Block {

	public FB_Input(Block b) {
		super(b);
		parameters.add(new BlockParameter("OUT", 10, 5, Type.DS101));
		parameters.add(new BlockParameter("PV_SCALE", 11, 8, Type.Float));
		parameters.add(new BlockParameter("OUT_SCALE", 12, 11, Type.Other));
		parameters.add(new BlockParameter("LIN_TYPE", 13, 1, Type.Unsinged8));
		parameters.add(new BlockParameter("CHANNEL", 14, 2, Type.Unsigned16));
		parameters.add(new BlockParameter("PV_FTYPE", 15, 4, Type.Float));
	}
}
