package common;

import org.json.JSONArray;
import org.json.JSONObject;

public class VehicleCommand {

    public static String getPing(boolean control) {
        JSONObject ping = new JSONObject();
        ping.put("cmd", "ping");
        ping.put("ctl", control?1:0);
        return ping.toString();
    }

    public static JSONObject getSet(Vehicle v) {
        JSONArray list = new JSONArray();
        for (int i = 0; i < v.channels.size(); i++) {
            int cur = v.channels.get(i).current;
            int pas = v.channels.get(i).lastKnown;
            if (cur != pas) {
                JSONObject entry = new JSONObject();
                entry.put("c", i+1);
                entry.put("v", v.channels.get(i).current);
                list.put(entry);
            }
        }
        JSONObject bundle = new JSONObject();
        bundle.put("cmd", "set");
        bundle.put("list", list);
        return (list.length() > 0) ? bundle : null;
    }

}
