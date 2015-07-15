package de.ikt.prakt.model.blocks;

import java.util.LinkedList;

import de.ikt.prakt.model.BlockParameter;
import de.ikt.prakt.model.BlockParameter.Type;

public class TB_Temperature extends LinkedList<BlockParameter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TB_Temperature() {
		add(new BlockParameter("PRIMARY_VALUE", 8, 5, Type.DS101));
		add(new BlockParameter("PRIMARY_VALUE_UNIT", 9, 2, Type.Unsigned16));
		add(new BlockParameter("SECONDARY_VALUE_1", 10, 5, Type.DS101));
		add(new BlockParameter("SECONDARY_VALUE_2", 11, 5, Type.DS101, true));
		add(new BlockParameter("SENSOR_MEAS_TYPE", 12, 1, Type.Unsinged8));
		add(new BlockParameter("INPUT_RANGE", 13, 1, Type.Unsinged8));
	}
	
}
