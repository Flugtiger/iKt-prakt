package de.ikt.prakt.model.blocks;

import java.util.LinkedList;

import de.ikt.prakt.model.BlockParameter;
import de.ikt.prakt.model.BlockParameter.Type;

public class FB_Input extends LinkedList<BlockParameter> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FB_Input() {
		add(new BlockParameter("OUT", 10, 5, Type.DS101));
		add(new BlockParameter("PV_SCALE", 11, 8, Type.Float));
		add(new BlockParameter("OUT_SCALE", 12, 11, Type.Other));
		add(new BlockParameter("LIN_TYPE", 13, 1, Type.Unsinged8));
		add(new BlockParameter("CHANNEL", 14, 2, Type.Unsigned16));
		add(new BlockParameter("PV_FTYPE", 15, 4, Type.Float));
	}
}
