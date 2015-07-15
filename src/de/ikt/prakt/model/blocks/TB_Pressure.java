package de.ikt.prakt.model.blocks;

import java.util.LinkedList;

import de.ikt.prakt.model.BlockParameter;
import de.ikt.prakt.model.BlockParameter.Type;

public class TB_Pressure extends LinkedList<BlockParameter> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TB_Pressure() {
		add(new BlockParameter("SENSOR_VALUE", 8, 4, Type.Float));
		add(new BlockParameter("SENSOR_HI_LIM", 9, 4, Type.Float));
		add(new BlockParameter("SENSOR_LO_LIM", 10, 4, Type.Float));
		add(new BlockParameter("CAL_POINT_HI", 11, 4, Type.Float));
		add(new BlockParameter("CAL_POINT_LO", 12, 4, Type.Float));
		add(new BlockParameter("CAL_MIN_SPAN", 13, 4, Type.Float));
		add(new BlockParameter("SENSOR_UNIT", 14, 2, Type.Unsigned16));
	}

}
