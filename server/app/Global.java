import java.net.UnknownHostException;
import java.util.TimeZone;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import assets.MorphiaObject;
import play.GlobalSettings;
import play.Logger;

public class Global extends GlobalSettings {

    @Override
    public void onStart(play.Application arg0) {
        super.beforeStart(arg0);

        System.setProperty("user.timezone", "GMT");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

        Logger.debug("** onStart **");
        try {
            MorphiaObject.mongo = new Mongo("db", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        MorphiaObject.morphia = new Morphia();
        MorphiaObject.datastore = MorphiaObject.morphia.createDatastore(MorphiaObject.mongo, "ar");
        MorphiaObject.datastore.ensureIndexes();
        MorphiaObject.datastore.ensureCaps();

        Logger.debug("** Morphia datastore: " + MorphiaObject.datastore.getDB());
    }
}