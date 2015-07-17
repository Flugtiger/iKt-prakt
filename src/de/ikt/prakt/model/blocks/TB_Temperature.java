package de.ikt.prakt.model.blocks;

import de.ikt.prakt.model.Block;
import de.ikt.prakt.model.BlockParameter;
import de.ikt.prakt.model.BlockParameter.Type;

public class TB_Temperature extends Block {

	public TB_Temperature(Block b) {
		super(b);
		parameters.add(new BlockParameter("PRIMARY_VALUE", 8, 5, Type.DS101));
		parameters.add(new BlockParameter("PRIMARY_VALUE_UNIT", 9, 2, Type.Unsigned16));
		parameters.add(new BlockParameter("SECONDARY_VALUE_1", 10, 5, Type.DS101));
		parameters.add(new BlockParameter("SECONDARY_VALUE_2", 11, 5, Type.DS101, true));
		parameters.add(new BlockParameter("SENSOR_MEAS_TYPE", 12, 1, Type.Unsinged8));
		parameters.add(new BlockParameter("INPUT_RANGE", 13, 1, Type.Unsinged8));
	}
	
}
