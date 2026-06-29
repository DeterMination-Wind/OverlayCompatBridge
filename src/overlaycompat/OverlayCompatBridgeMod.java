package overlaycompat;

import arc.Core;
import arc.Events;
import arc.input.KeyBind;
import arc.input.KeyCode;
import arc.util.Log;
import arc.util.Time;
import mindustry.Vars;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.game.EventType.Trigger;
import mindustry.input.Binding;
import mindustry.mod.Mod;
import mindustryX.features.ui.OverlayUI;

public class OverlayCompatBridgeMod extends Mod {
    private static boolean installed;
    private KeyBind overlayBind;

    public OverlayCompatBridgeMod() {
        if (installed) return;
        installed = true;
        Log.info("[OverlayCompatBridge] mod constructed. headless=" + Vars.headless);
        Events.on(ClientLoadEvent.class, event -> {
            Log.info("[OverlayCompatBridge] ClientLoadEvent. " + runtimeState());
            scheduleInit("client-load");
        });

        Events.run(Trigger.update, this::update);
        scheduleInit("bootstrap");
    }

    private void ensureKeybind() {
        if (overlayBind != null) return;
        overlayBind = KeyBind.add("overlayUI", KeyCode.z, "mindustryX");
        Log.info("[OverlayCompatBridge] keybind registered: overlayUI default=Z category=mindustryX");
    }

    private void update() {
        if (Vars.headless || Core.scene == null) return;
        ensureKeybind();
        if (!OverlayUI.INSTANCE.isAttached()) {
            initializeOverlay("update");
        }

        if (Core.scene.hasField()) {
            if (overlayBind != null && Core.input.keyTap(overlayBind)) {
                Log.info("[OverlayCompatBridge] toggle ignored: text field focused. " + runtimeState());
            }
            return;
        }
        if (overlayBind == null || !Core.input.keyTap(overlayBind)) return;

        if (Vars.control != null && Vars.control.input != null
            && Core.input.keyTap(Binding.schematicFlipX)
            && !Vars.control.input.selectPlans.isEmpty()) {
            Log.info("[OverlayCompatBridge] toggle ignored: schematic flip shortcut is active.");
            return;
        }

        if (!Core.input.ctrl()) {
            Log.info("[OverlayCompatBridge] toggle key pressed.");
            if (!OverlayUI.INSTANCE.isAttached()) {
                initializeOverlay("toggle");
            }
            OverlayUI.INSTANCE.toggle();
        } else {
            Log.info("[OverlayCompatBridge] toggle ignored: ctrl is held.");
        }
    }

    private void scheduleInit(String source) {
        ensureKeybind();
        Time.runTask(1f, () -> initializeOverlay(source));
    }

    private void initializeOverlay(String source) {
        if (Vars.headless) return;
        if (Core.scene == null) {
            Log.info("[OverlayCompatBridge] init postponed from " + source + ": scene is null. " + runtimeState());
            return;
        }
        if (OverlayUI.INSTANCE.isAttached()) return;

        try {
            Log.info("[OverlayCompatBridge] initializing OverlayUI from " + source + ". " + runtimeState());
            OverlayUI.INSTANCE.init();
            OverlayUI.INSTANCE.debugLogState("after-init-" + source);
            Log.info("[OverlayCompatBridge] OverlayUI initialized from " + source + ".");
        } catch (Throwable t) {
            Log.err("[OverlayCompatBridge] OverlayUI init failed from " + source + ".", t);
        }
    }

    private static String runtimeState() {
        boolean scene = Core.scene != null;
        boolean settings = Core.settings != null;
        boolean ui = Vars.ui != null;
        boolean hudGroup = ui && Vars.ui.hudGroup != null;
        boolean hudfrag = ui && Vars.ui.hudfrag != null;
        boolean hudShown = hudfrag && Vars.ui.hudfrag.shown;
        boolean menu = Vars.state != null && Vars.state.isMenu();
        String size = Core.graphics == null ? "unknown" : Core.graphics.getWidth() + "x" + Core.graphics.getHeight();
        return "scene=" + scene
            + ", settings=" + settings
            + ", ui=" + ui
            + ", hudGroup=" + hudGroup
            + ", hudfrag=" + hudfrag
            + ", hudShown=" + hudShown
            + ", menu=" + menu
            + ", graphics=" + size;
    }
}
