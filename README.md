# OverlayCompatBridge

- [中文](#中文)
- [English](#english)

## 中文

### 这是什么

`OverlayCompatBridge` 是给普通 Mindustry 客户端使用的 OverlayUI 兼容模组。

它的作用很简单：

- 让支持 OverlayUI 的客户端模组，在非 MindustryX 客户端里也能正常显示悬浮窗口。
- 提供统一的窗口管理器、拖动、缩放、固定、开关和位置记忆体验。
- 尽量保持接近 MindustryX OverlayUI 的使用感受。

### 适合谁

- 你在用普通 Mindustry 客户端。
- 你装了某些写明支持 `OverlayUI` 的模组。
- 这些模组在 vanilla 客户端里本来没有悬浮窗口入口，或者你想让它们继续使用 Overlay 风格界面。

### 怎么用

1. 把 `OverlayCompatBridge.jar` 放进 `mods` 文件夹。
2. 启动游戏并启用模组。
3. 进入主界面或地图后，查看屏幕左侧中部的齿轮按钮。
4. 点击齿轮，或直接按默认快捷键 `Z`，打开 OverlayUI 管理器。
5. 在管理器里启用、固定、拖动或调整各个窗口。

### 你会看到什么

- 屏幕左侧中部有一个固定齿轮按钮。
- 打开后，可以看到一个统一的窗口管理界面。
- 支持该接口的模组，会把自己的面板注册到这里。

### 注意

- 这不是完整的 MindustryX 替代品。
- 它主要负责 OverlayUI 窗口兼容，不负责 MindustryX 的其他扩展能力。
- 如果某个模组本身还依赖其他 MindustryX 专属功能，那么仅安装本模组并不一定足够。

### 给模组作者

开发接入说明见 [overlaydoc_zh_CN.md](overlaydoc_zh_CN.md)。

## English

### What It Is

`OverlayCompatBridge` is an OverlayUI compatibility mod for regular Mindustry clients.

Its job is straightforward:

- Let OverlayUI-capable client mods show floating windows on non-MindustryX clients.
- Provide one shared window manager with drag, resize, pin, toggle, and saved layout behavior.
- Keep the user experience close to native MindustryX OverlayUI where practical.

### Who It Is For

- You are using a regular Mindustry client.
- You have mods that mention `OverlayUI` support.
- You want those mods to keep their overlay-style windows without switching to MindustryX.

### How To Use It

1. Put `OverlayCompatBridge.jar` into your `mods` folder.
2. Launch the game and enable the mod.
3. On the menu or in-game HUD, look for the gear button at the left-middle edge of the screen.
4. Click the gear, or press the default `Z` hotkey, to open the OverlayUI manager.
5. Enable, pin, drag, or resize windows from the manager.

### What You Will See

- A fixed gear button at the left-middle side of the screen.
- A shared overlay window manager when opened.
- Any mod that supports the interface can register its own panel there.

### Notes

- This is not a full MindustryX replacement.
- It focuses on OverlayUI window compatibility only.
- If a mod also depends on other MindustryX-only systems, this bridge alone may not be enough.

### For Mod Authors

Developer integration notes are in [overlaydoc_en_US.md](overlaydoc_en_US.md).
