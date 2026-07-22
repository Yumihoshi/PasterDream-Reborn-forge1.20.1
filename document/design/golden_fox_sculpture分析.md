# Golden Fox Sculpture 方块搬运分析

## 概述

`golden_fox_sculpture`（金狐狸雕像）是一个 GeckoLib 动画方块，作为召唤 **Golden Fox（金狐狸）实体** 的仪式核心。目前搬运为空壳——方块注册、模型、动画、纹理已就位，但核心交互逻辑缺失。

---

## ID 映射依赖

| 原作 ID | 新 ID | 类型 | 搬运状态 |
|---------|-------|------|---------|
| `flower_12` | `misty_dreaming_lotus` | 方块 | ✅ 已搬运（DoublePlantBlock） |
| `golden_fox_sculpture` | `golden_fox_sculpture` | 方块 | ⚠️ 空壳 |
| `golden_fox` | 未登记 | 实体（GoldenFoxEntity） | ❌ 未搬运 |
| `ding_0` | `ding_0` | 音效 | ✅ 已搬运 |
| `healing_spell_particle` | 未登记 | 粒子 | ❌ 未搬运 |
| `yellow_smoke_particle` | 未登记 | 粒子 | ❌ 未搬运 |
| `meltdream_liquid_bucket` | `meltdream_liquid_bucket` | 物品 | ✅ 已搬运 |
| `dream_coin_1` | `melt_dream_coin_pile` | 物品 | ✅ 已搬运 |
| `meltdream_crystal_0` | `melt_dream_crystal_fragment` | 物品 | ✅ 已搬运 |
| `memento_item_11` | 未登记 | 方块 | 待查（仪式用方块） |
| `cradle_in_ones_arms` | `kaichu_omamori` | 物品 | ✅ 已搬运 |
| `meltdream_chest` | `melt_dream_crystal_chest` | 方块 | ✅ 已搬运 |

---

## 一、雕像方块：GoldenFoxSculptureBlock

**原作基类**: `BaseEntityBlock` + `EntityBlock`

| 属性 | 值 |
|------|-----|
| 音效 | `SoundType.CALCITE` |
| 硬度 | 5f |
| 渲染类型 | `ENTITYBLOCK_ANIMATED` (GeckoLib) |
| 碰撞箱 N/S | `box(0, 0, 4, 16, 13, 12)` |
| 碰撞箱 E/W | `box(4, 0, 0, 12, 13, 16)` |

**BlockState 属性**: `FACING` + `ANIMATION`(IntegerProperty 0~1)

**右击交互**: 直接调用 `GoldenFoxSculpturePr0Procedure.execute()`

### 仪式逻辑（GoldenFoxSculpturePr0Procedure）

右击雕像时判定，**全部条件满足**触发召唤：

1. 四个对角 9 格处均为 `misty_dreaming_lotus`（迷梦冶梦莲）
2. 主手持有 `minecraft:glow_berries`
3. 时间在黎明（dayTime % 24000 ∈ [0, 450]）

**触发效果**：消耗浆果 → 破坏 4 花 + 雕像 → 生成 GoldenFox 实体 → 播放音效（fox.ambient / ding_0）→ 生成粒子

**条件不满足**：提示"雕像没有反应..."

---

## 二、金狐狸实体：GoldenFoxEntity（未搬运）

### 实体属性

| 属性 | 值 |
|------|-----|
| 基类 | `PathfinderMob` |
| AI | `setNoAi(true)`（无自主移动） |
| 生命 | 8 |
| 速度 | 0（静止不动） |
| 伤害 | 3 |
| 跟随距离 | 16 |

**伤害免疫**: 火、箭、药水、摔落、仙人掌、溺水、雷、爆炸、三叉戟、铁砧、龙息、凋零——几乎不受任何伤害

**纹理切换**: 通过 `TEXTURE` DataParameter 动态切换（默认 `"gloden_fox_light"`），模型取 `textures/entities/{texture}.png`

### 物品转换功能（GoldenFoxPr0Procedure）⚠️ 核心机制

右键点击金狐狸，根据手持物品触发不同的 **消失→15 tick 延迟→生成产物** 流程：

| 手持物品 | 产物 | 产物生成方式 |
|---------|------|------------|
| `minecraft:golden_apple` | `minecraft:enchanted_golden_apple` | 掉落物实体 |
| `minecraft:bucket` | `meltdream_liquid_bucket` | 掉落物实体 |
| `dream_coin_1` (melt_dream_coin_pile) | `meltdream_chest` (melt_dream_crystal_chest) | 放置方块 |
| `meltdream_crystal_0` 或 `memento_item_11` | `cradle_in_ones_arms` (kaichu_omamori) | 掉落物实体 |

所有转换统一：消耗手持物品 → 实体消失 → 粒子（YELLOW_SMOKE ×32 + CLOUD ×12）→ 播放 fox.ambient → 15 tick 后生成产物 → 提示"金色狐狸在完成了你许下的愿望之后消失了..."

### 动画

GeckoLib 双控制器：`movement`（idle loop "idle"） + `procedure`（播放一次性动画后重置）

### 渲染

- 模型：`geo/golden_fox.geo.json`
- 动画：`animations/golden_fox.animation.json`
- 纹理：动态，NBT 存储 texture 字段

---

## 三、当前雕像方块缺失（相对于原作）

### 已完成的

- 方块注册 + BlockItem（GoldenFoxSculptureItem, GeoItem）
- BlockEntity + GeckoLib 动画（idle loop "0"）
- 方块/物品渲染器（GeoBlockRenderer / GeoItemRenderer）
- Geo 模型 + 纹理 + 动画 JSON
- BlockState JSON（四朝向粒子模型）
- 战利品表（自掉落）、标签（镐挖掘）、语言文件

### 缺失

| 序号 | 缺失项 | 说明 |
|------|--------|------|
| 1 | `ANIMATION` BlockState 属性 | IntegerProperty 0~1，用于触发一次性动画 |
| 2 | `use()` 交互方法 | 右击调用仪式判定 |
| 3 | 仪式判定逻辑 | 原作 GoldenFoxSculpturePr0Procedure |
| 4 | 半透明渲染 | 两个渲染器都需要 `getRenderType()` → `entityTranslucent` |
| 5 | ClientSetRenderLayer 注册 | 未注册 translucent |
| 6 | 容器/库存 | 9格漏斗可用，非核心（GUI 不打开），可省略 |

---

## 四、搬运方案建议

### 整体链路

```
GoldenFoxSculptureBlock.use()
  ├── 仪式判定（检查 misty_dreaming_lotus ×4 + glow_berries + 时间）
  │   ├── 失败 → 提示消息
  │   └── 成功 → 破坏方块 + 召唤 GoldenFoxEntity
  │
  └── GoldenFoxEntity.mobInteract()
        └── 物品转换（数据驱动）
```

### 关于数据驱动改造

用户提到物品转换应改为数据驱动。当前硬编码了 4 组映射。建议方案：

**方案 A — 配方 JSON（推荐）**
使用自定义配方类型 `golden_fox_trade`，类似原版 `barter`（猪灵交易）：

```json
{
  "type": "pasterdream:golden_fox_trade",
  "ingredient": { "item": "minecraft:golden_apple" },
  "result": { "item": "minecraft:enchanted_golden_apple" },
  "action": "drop_item"
}
```

action 类型：`drop_item` / `place_block`，对应两种产物生成方式。

**方案 B — datapack loot table**

`golden_fox_trade/{input_item}.json`，loot table 格式。

**方案 C — 标签 + Map**

用物品标签分组 + 硬编码 Map，灵活度中等。

### 分步实施

1. **先搬运金狐狸实体**（GoldenFoxEntity + 模型 + 动画 + 纹理 + 渲染器）
2. **补全雕像交互逻辑**（仪式判定 + 召唤实体）
3. **数据驱动改造**（物品转换从硬编码改为配方/loot table 驱动）
4. **补全半透明渲染**
5. **粒子**：`healing_spell_particle` / `yellow_smoke_particle` 需搬运或用原版粒子替代
