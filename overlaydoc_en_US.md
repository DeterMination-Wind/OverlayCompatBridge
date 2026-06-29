# OverlayCompatBridge Integration Guide

## Goal

If your mod already supports MindustryX OverlayUI, this bridge lets the same overlay-style windows keep working on regular Mindustry clients.

Recommended target behavior:

- Keep the existing path on MindustryX.
- Register overlay windows normally on regular clients when OverlayCompatBridge is installed.
- Avoid crashes when no OverlayUI is available, and fall back to a normal dialog or disable only the overlay feature.

## Recommended Integration Style

- Prefer runtime detection instead of making OverlayUI a hard requirement.
- If your mod only needs window registration, checking whether `mindustryX.features.ui.OverlayUI` exists is usually enough.
- If you also maintain a separate MindustryX-only path, detect a full MindustryX runtime separately instead of treating the shared `OverlayUI` class name as proof.

## Common Capabilities Provided By The Bridge

- Shared `OverlayUI.INSTANCE`
- Opening and closing the editor
- Window registration
- Enabled and pinned window state
- Dragging, resizing, and saved window layout

## Recommended Flow

1. Wait until client UI is ready before registering windows.
2. Check whether OverlayUI is available.
3. If it is available, register the window and configure visibility, auto height, and resize behavior.
4. If it is not available, fall back to your normal UI path.

## Design Advice

- Do not place OverlayUI types in public API, static fields, or static initialization paths.
- Do not treat this bridge as the full MindustryX API surface.
- Do not assume that installing OverlayCompatBridge also provides unrelated `mindustryX.*` features.

## Suggested Player-Facing Messaging

If your mod integrates with this bridge, tell users clearly in your README or release page:

- Regular clients can use overlay windows with `OverlayCompatBridge`.
- The default entry is the gear button at the left-middle side of the screen, or the `Z` hotkey.
- Without the bridge, the mod will either use a normal dialog or disable only the overlay-specific feature.

## Compatibility Scope

This bridge is focused on OverlayUI-style window compatibility. It does not promise:

- VarsX
- SettingsV2
- Marker API
- ShareFeature
- Other MindustryX-only systems
