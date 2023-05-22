package developer.kayllon;

import ch.bailu.gtk.adw.*;
import ch.bailu.gtk.adw.Application;
import ch.bailu.gtk.adw.ApplicationWindow;
import ch.bailu.gtk.gio.ApplicationFlags;
import ch.bailu.gtk.gtk.*;
import ch.bailu.gtk.gtk.HeaderBar;
import ch.bailu.gtk.type.Str;
import ch.bailu.gtk.type.Strs;

public class Bootstrap {

    public static void main(String[] args) {

        var application = new Application(new Str("developer.kayllon.playing-with-gtk4"), ApplicationFlags.FLAGS_NONE);

        application.onStartup(() -> StyleManager.getDefault().setColorScheme(ColorScheme.PREFER_DARK));
        application.onActivate(() -> {

            var row = new ActionRow();
            row.setTitle(new Str("Click Me"));
            row.setActivatable(true);
            row.setSelectable(false);
            row.onActivate(() -> System.out.println("Hello World"));

            var rowTwo = new ActionRow();
            row.setTitle(new Str("Enable Dark Mode"));
            row.setSubtitle(new Str("This option will enable dark mode"));

            var status = StyleManager.getDefault().getDark();

            var darkSwitch = new Switch();
            darkSwitch.setValign(Align.CENTER);
            darkSwitch.setState(status);

            darkSwitch.onStateSet((preferDark) -> {
                if (preferDark) {
                    StyleManager.getDefault().setColorScheme(ColorScheme.PREFER_LIGHT);
                } else {
                    StyleManager.getDefault().setColorScheme(ColorScheme.PREFER_DARK);
                }
                return false;
            });

            rowTwo.addSuffix(darkSwitch);

            var listBox = new ListBox();
            listBox.setMarginStart(32);
            listBox.setMarginTop(32);
            listBox.setMarginBottom(32);
            listBox.setMarginEnd(32);
            listBox.addCssClass(new Str("boxed-list"));

            listBox.append(row);
            listBox.append(rowTwo);

            var label = new Label(new Str("Something Cool"));
            label.setMarginStart(32);
            label.setMarginTop(32);
            label.setMarginBottom(32);
            label.setMarginEnd(32);
            label.addCssClass(new Str("accent"));

            var content = new Box(Orientation.VERTICAL, 0);

            var header = new HeaderBar();
            header.setTitleWidget(new WindowTitle(new Str("Playing with GTK-4"), new Str("")));
            content.append(header);
            content.append(label);
            content.append(listBox);

            var window = new ApplicationWindow(application);
            window.setDefaultSize(640, 480);
            window.setContent(content);
            window.present();
        });

        application.run(args.length, new Strs(args));
        application.unref();
    }

}