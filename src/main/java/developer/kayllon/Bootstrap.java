package developer.kayllon;

import ch.bailu.gtk.adw.Application;
import ch.bailu.gtk.adw.ApplicationWindow;
import ch.bailu.gtk.gio.ApplicationFlags;
import ch.bailu.gtk.type.Str;
import ch.bailu.gtk.type.Strs;

public class Bootstrap {

    public static void main(String[] args) {

        var application = new Application(new Str("developer.kayllon.playing-with-gtk4"), ApplicationFlags.FLAGS_NONE);

        application.onActivate(() -> {

            var window = new ApplicationWindow(application);

            window.setTitle(new Str("Playing with GTK4"));

            window.show();

        });

        var result = application.run(args.length, new Strs(args));

        System.exit(result);
    }

}