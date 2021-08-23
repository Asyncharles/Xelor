package net.xelor.client;

import net.xelor.client.structures.energy.light.LightData;
import net.xelor.client.house.RoomModel;
import net.xelor.client.house.RoomType;

import java.util.List;

import static net.xelor.client.XelorModule.LOGGER;

public class Debug {

    public static void debug() {
        final long a = System.currentTimeMillis();

        LightData data = new LightData(1, 2, new RoomModel(1, RoomType.BATHROOM, true, 3), false);
        LightData data1 = new LightData(3, 4, new RoomModel(2, RoomType.MASTER_BEDROOM, true, 86), false);
        LightData data2 = new LightData(5, 6, new RoomModel(2, RoomType.CORRIDOR, false, 5), false);
        LightData data3 = new LightData(7, 8, new RoomModel(2, RoomType.RESTROOM, false, 4), false);
        LightData data4 = new LightData(9, 10, new RoomModel(2, RoomType.BEDROOM, true, 18), false);

        LightData[] d = new LightData[] {data, data1, data2, data3, data4};

        for (int i = 0; i < d.length; i++) {
            final long b = a + 100 * i;
            LOGGER.info(b + "  " + d[i].getPrimaryValue());
            XelorModule.getInstance().getLightDataHandler().addData(b, d[i]);
        }

        try {
            List<LightData> dataList = XelorModule.getInstance().getLightDataHandler().retrieveData(a + 201L, a + 301L);
            for (LightData dl : dataList) {
                LOGGER.info(String.valueOf(dl.getPrimaryValue()));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
