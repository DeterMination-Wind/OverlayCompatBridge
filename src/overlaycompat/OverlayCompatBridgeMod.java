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
    private KeyBind overlayBind;

    public OverlayCompatBridgeMod() {
        Events.on(ClientLoadEvent.class, event -> {
            ensureKeybind();
            Time.runTask(1f, () -> {
                try {
                    OverlayUI.INSTANCE.init();
                    Log.info("[OverlayCompatBridge] OverlayUI initialized.");
                } catch (Throwable t) {
                    Log.err("[OverlayCompatBridge] OverlayUI init failed.");
                    Log.err(t);
                }
            });
        });

        Events.run(Trigger.update, this::update);
    }

    private void ensureKeybind() {
        if (overlayBind != null) return;
        overlayBind = KeyBind.add("overlayUI", KeyCode.z, "mindustryX");
    }

    private void update() {
        if (Vars.headless || Core.scene == null) return;
        ensureKeybind();

        if (Core.scene.hasField()) return;
        if (overlayBind == null || !Core.input.keyTap(overlayBind)) return;

        if (Vars.control != null && Vars.control.input != null
            && Core.input.keyTap(Binding.schematicFlipX)
            && !Vars.control.input.selectPlans.isEmpty()) {
            return;
        }

        if (!Core.input.ctrl()) {
            OverlayUI.INSTANCE.toggle();
        }
    }
}
