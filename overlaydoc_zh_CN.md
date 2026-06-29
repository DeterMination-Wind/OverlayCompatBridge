# OverlayCompatBridge 接入文档

## 目标

如果你的模组原本支持 MindustryX 的 OverlayUI，这个兼容层可以让它在普通 Mindustry 客户端里继续使用 Overlay 风格窗口。

推荐的目标行为：

- 在 MindustryX 中继续走原有路径。
- 在普通客户端且安装了 OverlayCompatBridge 时，正常注册 Overlay 窗口。
- 在完全没有 OverlayUI 的环境中，不崩溃，并回退到普通对话框或关闭该功能。

## 建议的接入方式

- 优先使用运行时检测，而不是把 OverlayUI 当成强制依赖。
- 如果你的模组只需要注册窗口，检测 `mindustryX.features.ui.OverlayUI` 是否存在通常就够了。
- 如果还区分 MindustryX 专属路径，请额外检测完整 MindustryX 运行时，而不是只看 `OverlayUI` 类名。

## 兼容层提供的常用能力

- 统一的 `OverlayUI.INSTANCE`
- 打开/关闭编辑器
- 注册窗口
- 窗口启用与固定状态
- 窗口拖动、缩放、位置记忆

## 推荐流程

1. 在客户端 UI 已经准备好之后再尝试注册窗口。
2. 先判断是否存在 OverlayUI。
3. 如果存在，就注册窗口并设置可见条件、自动高度、是否可缩放。
4. 如果不存在，就回退到你原有的普通 UI 方案。

## 设计建议

- 不要把 OverlayUI 相关类型放进公共 API、静态字段或静态初始化里。
- 不要把此兼容层当成 MindustryX 全量 API。
- 不要假设安装了 OverlayCompatBridge 就一定拥有其他 `mindustryX.*` 功能。

## 面向玩家的说明建议

如果你的模组接入了本兼容层，建议在 README 或发布页明确告诉玩家：

- 普通客户端可配合 `OverlayCompatBridge` 使用 Overlay 窗口。
- 默认打开方式是点击屏幕左侧中部齿轮，或按 `Z`。
- 如果未安装兼容层，模组会改用普通窗口，或者仅关闭 Overlay 相关功能。

## 兼容范围说明

这个兼容层主要解决的是 OverlayUI 风格窗口的可用性问题，不承诺提供：

- VarsX
- SettingsV2
- Marker API
- ShareFeature
- 其他 MindustryX 专属系统
