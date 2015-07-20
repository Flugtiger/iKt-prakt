package de.ikt.prakt.model.blocks;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import de.ikt.prakt.controller.ProfibusInterface;
import de.ikt.prakt.model.Block;
import de.ikt.prakt.model.BlockParameter;
import de.ikt.prakt.model.BlockParameter.Type;

public class TB_Temperature extends Block {
	
	private byte[] unitCache = null;

	public TB_Temperature(Block b) {
		super(b);
		parameters.add(new BlockParameter("PRIMARY_VALUE", 8, 5, Type.DS101));
		parameters.add(new BlockParameter("PRIMARY_VALUE_UNIT", 9, 2, Type.Unsigned16));
		parameters.add(new BlockParameter("SECONDARY_VALUE_1", 10, 5, Type.DS101));
		parameters.add(new BlockParameter("SECONDARY_VALUE_2", 11, 5, Type.DS101, true));
		parameters.add(new BlockParameter("SENSOR_MEAS_TYPE", 12, 1, Type.Unsinged8));
		parameters.add(new BlockParameter("INPUT_RANGE", 13, 1, Type.Unsinged8));
	}
	
	@Override
	public String paramToString(ProfibusInterface pb, BlockParameter param, byte[] data) {
		if (param == parameters.get(0)) {
			
			// parse unit
			String unit = "";
			if(unitCache == null)
				unitCache = super.readParameter(pb, parameters.get(1));
			if(unitCache.length == 2) {
				int unitInt = ByteBuffer.wrap(unitCache).order(ByteOrder.BIG_ENDIAN).getShort();
				switch(unitInt) {
				case 1000:
					unit = "K";
					break;
				case 1001:
					unit = "°C";
					break;
				case 1002:
					unit = "°F";
					break;
				}
			}
			
			// parse value
			float value = ByteBuffer.wrap(data, 0, 4).order(ByteOrder.LITTLE_ENDIAN).getFloat();
			return String.format("%.2f", value) + unit;
		}
		
		return super.paramToString(pb, param, data);
	}
	
}
